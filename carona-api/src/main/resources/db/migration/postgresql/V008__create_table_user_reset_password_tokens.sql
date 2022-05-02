CREATE TABLE tab_user_reset_password_token
(
    id         BIGSERIAL NOT NULL,
    user_id    BIGINT    NOT NULL,
    token      CHARACTER VARYING(500) DEFAULT NULL,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL     DEFAULT NOW(),
    updated_at TIMESTAMP              DEFAULT NULL
);

ALTER TABLE tab_user_reset_password_token
    ADD CONSTRAINT urpt_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT urpt_token_uk UNIQUE (token),
    ADD CONSTRAINT urpt_user_fk
        FOREIGN KEY (user_id) REFERENCES tab_users (id);

CREATE INDEX urpt_token_idx ON tab_user_reset_password_token (token);