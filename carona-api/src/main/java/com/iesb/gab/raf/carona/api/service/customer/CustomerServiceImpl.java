package com.iesb.gab.raf.carona.api.service.customer;

import com.iesb.gab.raf.carona.api.dto.customer.CustomerFullDetailsDto;
import com.iesb.gab.raf.carona.api.entity.address.Address;
import com.iesb.gab.raf.carona.api.entity.builder.customer.CustomerBuilder;
import com.iesb.gab.raf.carona.api.entity.builder.user.ConfirmationTokenBuilder;
import com.iesb.gab.raf.carona.api.entity.customer.Customer;
import com.iesb.gab.raf.carona.api.entity.user.ConfirmationToken;
import com.iesb.gab.raf.carona.api.entity.user.Role;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.event.customer.CustomerAccountConfirmedEvent;
import com.iesb.gab.raf.carona.api.event.customer.CustomerCreatedEvent;
import com.iesb.gab.raf.carona.api.exception.ResourceAlreadyExistsException;
import com.iesb.gab.raf.carona.api.payload.request.customer.CustomerCreateRequest;
import com.iesb.gab.raf.carona.api.repository.customer.CustomerRepository;
import com.iesb.gab.raf.carona.api.service.jwt.ConfirmationTokenService;
import com.iesb.gab.raf.carona.api.service.user.RoleService;
import com.iesb.gab.raf.carona.api.service.user.UserService;

import lombok.AllArgsConstructor;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.iesb.gab.raf.carona.api.service.jwt.ConfirmationTokenService.EXPIRATION_SECONDS;

@Service
@AllArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final static String RESOURCE_NAME = "Customer";

    private final UserService userService;
    private final RoleService roleService;
    private final ConfirmationTokenService confirmationTokenService;
    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public CustomerFullDetailsDto save(CustomerCreateRequest customerCreateRequest) throws ResourceAlreadyExistsException {

        if (customerRepository.existsByCpf(customerCreateRequest.getCpf())) {
            throw new ResourceAlreadyExistsException(RESOURCE_NAME, "CPF", customerCreateRequest.getCpf());
        }

        if (customerRepository.existsByPhoneNumber(customerCreateRequest.getPhoneNumber())) {
            throw new ResourceAlreadyExistsException(RESOURCE_NAME, "Phone Number",
                    customerCreateRequest.getPhoneNumber());
        }

        User user = userService.save(customerCreateRequest.getUser(), getCustomerRoles());

        Customer customer = CustomerBuilder.builder()
                .withLogin(user)
                .withCpf(customerCreateRequest.getCpf())
                .withFirstName(customerCreateRequest.getFirstName())
                .withLastName(customerCreateRequest.getLastName())
                .withPhoneNumber(customerCreateRequest.getPhoneNumber())
                .withAddress(new Address())
                .build();

        customer = customerRepository.save(customer);

        customer.getLogin().setConfirmationToken(saveAuthorUserConfirmationToken(customer));
        publishCustomerCreatedEvent(customer);

        return new CustomerFullDetailsDto(customer);
    }

    @Override
    public void confirmAccount(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getByToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        Instant expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(Instant.now())) {
            throw new IllegalStateException("Confirmation Token expired");
        }

        confirmationToken = confirmationTokenService.setConfirmedAt(token);
        publishCustomerConfirmedEvent(confirmationToken.getUser().getCustomer());
    }

    private void publishCustomerCreatedEvent(final Customer customer) {
        CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent(customer, this);
        eventPublisher.publishEvent(customerCreatedEvent);
    }

    private void publishCustomerConfirmedEvent(final Customer customer) {
        CustomerAccountConfirmedEvent customerAccountConfirmedEvent = new CustomerAccountConfirmedEvent(customer, this);
        eventPublisher.publishEvent(customerAccountConfirmedEvent);
    }

    private Set<Role> getCustomerRoles() {
        Set<Role> roles = new HashSet<>();
        Arrays.stream(ROLE_CUSTOMERS).toList().forEach(role -> roles.add(roleService.getOrSaveByName(role)));

        return roles;
    }

    private ConfirmationToken saveAuthorUserConfirmationToken(Customer customer) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = ConfirmationTokenBuilder.builder()
                .withUser(customer.getLogin())
                .withToken(token)
                .withExpiresAt(Instant.now().plusSeconds(EXPIRATION_SECONDS))
                .withConfirmedAt(null)
                .build();

        return confirmationTokenService.save(confirmationToken);
    }
}
