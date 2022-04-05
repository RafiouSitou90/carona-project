CREATE TABLE tab_user_confirmation_tokens
(
    id           BIGSERIAL              NOT NULL,
    user_id      BIGINT                 NOT NULL,
    token        CHARACTER VARYING(500) NOT NULL,
    expires_at   TIMESTAMP              NOT NULL,
    confirmed_at TIMESTAMP                       DEFAULT NULL,
    created_at   TIMESTAMP              NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMP                       DEFAULT NULL
);

ALTER TABLE tab_user_confirmation_tokens
    ADD CONSTRAINT uct_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT uct_user_fk
        FOREIGN KEY (user_id) REFERENCES tab_users (id);
