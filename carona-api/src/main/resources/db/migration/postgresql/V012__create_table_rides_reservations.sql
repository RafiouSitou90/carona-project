CREATE TABLE tab_rides_reservations
(
    id              BIGSERIAL NOT NULL,
    ride_program_id BIGINT    NOT NULL,
    customer_id     BIGINT    NOT NULL,
    created_at      TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP          DEFAULT NULL
);

ALTER TABLE tab_rides_reservations
    ADD CONSTRAINT rr_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT rr_ride_program_fk
        FOREIGN KEY (ride_program_id) REFERENCES tab_rides_programs (id),
    ADD CONSTRAINT rp_customer_fk
        FOREIGN KEY (customer_id) REFERENCES tab_customers (id);