CREATE TABLE tab_customers
(
    id           BIGSERIAL              NOT NULL,
    cpf          CHARACTER VARYING(14)  NOT NULL,
    first_name   CHARACTER VARYING(255)          DEFAULT NULL,
    last_name    CHARACTER VARYING(255)          DEFAULT NULL,
    gender       CHARACTER VARYING(20)           DEFAULT NULL,
    phone_number CHARACTER VARYING(50)           DEFAULT NULL,
    avatar       CHARACTER VARYING(255)          DEFAULT NULL,
    login_id     BIGINT                 NOT NULL,
    address_id   BIGINT                          DEFAULT NULL,
    created_at   TIMESTAMP              NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMP                       DEFAULT NULL
);

ALTER TABLE tab_customers
    ADD CONSTRAINT customer_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT customer_phone_number_uk UNIQUE (phone_number),
    ADD CONSTRAINT customer_cpf_uk UNIQUE (cpf),
    ADD CONSTRAINT customer_login_fk
        FOREIGN KEY (login_id) REFERENCES tab_users (id),
    ADD CONSTRAINT customer_address_fk
        FOREIGN KEY (address_id) REFERENCES tab_addresses (id);
