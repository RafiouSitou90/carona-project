CREATE TABLE tab_users
(
    id                         BIGSERIAL              NOT NULL,
    username                   CHARACTER VARYING(255) NOT NULL,
    email                      CHARACTER VARYING(255) NOT NULL,
    password                   CHARACTER VARYING(255) NOT NULL,
    is_enabled                 BOOLEAN                         DEFAULT TRUE,
    is_non_expired             BOOLEAN                         DEFAULT TRUE,
    is_non_locked              BOOLEAN                         DEFAULT TRUE,
    is_credentials_non_expired BOOLEAN                         DEFAULT TRUE,
    created_at                 TIMESTAMP              NOT NULL DEFAULT NOW(),
    updated_at                 TIMESTAMP                       DEFAULT NULL
);

ALTER TABLE tab_users
    ADD CONSTRAINT user_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT user_username_uk UNIQUE (username),
    ADD CONSTRAINT user_email_uk UNIQUE (email);

CREATE INDEX user_username_idx ON tab_users (username);
CREATE INDEX user_email_idx ON tab_users (email);
