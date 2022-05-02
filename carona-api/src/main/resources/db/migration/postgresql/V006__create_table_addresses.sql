CREATE TABLE tab_addresses
(
    id          BIGSERIAL NOT NULL,
    city        CHARACTER VARYING(100) DEFAULT NULL,
    state       CHARACTER VARYING(100) DEFAULT NULL,
    address     CHARACTER VARYING(255) DEFAULT NULL,
    postal_code CHARACTER VARYING(10)  DEFAULT NULL,
    created_at  TIMESTAMP NOT NULL     DEFAULT NOW(),
    updated_at  TIMESTAMP              DEFAULT NULL
);

ALTER TABLE tab_addresses
    ADD CONSTRAINT address_id_pk PRIMARY KEY (id);
