package com.iesb.gab.raf.carona.api.service.car;

import com.iesb.gab.raf.carona.api.dto.car.CarDto;
import com.iesb.gab.raf.carona.api.entity.builder.car.CarBuilder;
import com.iesb.gab.raf.carona.api.entity.car.Car;
import com.iesb.gab.raf.carona.api.entity.user.Role;
import com.iesb.gab.raf.carona.api.entity.user.User;
import com.iesb.gab.raf.carona.api.exception.ResourceAccessDenied;
import com.iesb.gab.raf.carona.api.exception.ResourceAlreadyExistsException;
import com.iesb.gab.raf.carona.api.exception.ResourceNotFoundException;
import com.iesb.gab.raf.carona.api.payload.request.car.CarRequest;
import com.iesb.gab.raf.carona.api.repository.car.CarRepository;
import com.iesb.gab.raf.carona.api.repository.user.UserRepository;
import com.iesb.gab.raf.carona.api.service.user.RoleService;
import com.iesb.gab.raf.carona.api.util.Utils;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class CarServiceImpl implements CarService {

    private final static String RESOURCE_NAME = "Car";

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public CarDto save(CarRequest carRequest) throws ResourceAlreadyExistsException {

        User user = Utils.getUserAuthenticated();

        if (user == null) {
            throw new RuntimeException("Login required");
        }

        if (carRepository.existsByLicensePlateIgnoreCase(carRequest.getLicensePlate())) {
            throw new ResourceAlreadyExistsException(RESOURCE_NAME, "License Plate", carRequest.getLicensePlate());
        }

        Car car = CarBuilder.builder()
                .withModel(carRequest.getModel())
                .withLicensePlate(carRequest.getLicensePlate())
                .withColor(carRequest.getColor())
                .withNbSeats(carRequest.getNbSeats())
                .withDriver(user.getCustomer())
                .build();

        Set<Role> roles = getDriverRoles();
        roles.forEach(role -> user.getRoles().add(role));
        userRepository.save(user);

        return new CarDto(carRepository.save(car));
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException, ResourceAccessDenied {
        Car car = getCarOrThrowException(id);
        Utils.denyAccessUnlessGranted(
                car.getDriver().getLogin(),
                "Access denied! You don't have access to delete this car"
        );

        carRepository.delete(car);
    }

    private Set<Role> getDriverRoles() {
        Set<Role> roles = new HashSet<>();
        Arrays.stream(ROLE_DRIVERS).toList().forEach(role -> roles.add(roleService.getOrSaveByName(role)));

        return roles;
    }

    private Car getCarOrThrowException(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "Id", id));
    }
}
