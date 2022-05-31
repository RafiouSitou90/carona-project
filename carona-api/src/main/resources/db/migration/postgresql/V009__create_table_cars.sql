CREATE TABLE tab_cars
(
    id            BIGSERIAL              NOT NULL,
    model         CHARACTER VARYING(255) NOT NULL,
    license_plate CHARACTER VARYING(20)  NOT NULL,
    color         CHARACTER VARYING(100) NOT NULL,
    nb_seats      INTEGER                NOT NULL,
    driver_id     BIGINT                 NOT NULL,
    created_at    TIMESTAMP              NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMP                       DEFAULT NULL
);

ALTER TABLE tab_cars
    ADD CONSTRAINT car_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT car_license_plate_uk UNIQUE (license_plate),
    ADD CONSTRAINT car_driver_fk
        FOREIGN KEY (driver_id) REFERENCES tab_customers (id);
