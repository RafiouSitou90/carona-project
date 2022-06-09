CREATE TABLE tab_rides_programs
(
    id             BIGSERIAL             NOT NULL,
    city_from      BIGINT                NOT NULL,
    city_to        BIGINT                NOT NULL,
    car_id         BIGINT                NOT NULL,
    day            CHARACTER VARYING(20) NOT NULL,
    departure_time CHARACTER VARYING(5)  NOT NULL,
    is_active      BOOLEAN                        DEFAULT TRUE,
    is_full        BOOLEAN                        DEFAULT FALSE,
    created_at     TIMESTAMP             NOT NULL DEFAULT NOW(),
    updated_at     TIMESTAMP                      DEFAULT NULL
);

ALTER TABLE tab_rides_programs
    ADD CONSTRAINT rp_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT rp_from_city_fk
        FOREIGN KEY (city_from) REFERENCES tab_cities (id),
    ADD CONSTRAINT rp_from_to_fk
        FOREIGN KEY (city_to) REFERENCES tab_cities (id),
    ADD CONSTRAINT rp_car_fk
        FOREIGN KEY (car_id) REFERENCES tab_cars (id);