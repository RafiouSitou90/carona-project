CREATE TABLE tab_roles
(
    id         BIGSERIAL              NOT NULL,
    name       CHARACTER VARYING(100) NOT NULL,
    created_at TIMESTAMP              NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP                       DEFAULT NULL
);

ALTER TABLE tab_roles
    ADD CONSTRAINT role_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT role_name_uk UNIQUE (name);
