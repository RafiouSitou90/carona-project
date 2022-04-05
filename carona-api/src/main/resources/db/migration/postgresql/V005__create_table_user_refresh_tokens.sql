CREATE TABLE tab_user_refresh_tokens
(
    id         BIGSERIAL              NOT NULL,
    user_id    BIGINT                 NOT NULL,
    token      CHARACTER VARYING(500) NOT NULL,
    expires_at TIMESTAMP              NOT NULL,
    created_at TIMESTAMP              NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP                       DEFAULT NULL
);

ALTER TABLE tab_user_refresh_tokens
    ADD CONSTRAINT urt_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT urt_token_uk UNIQUE (token),
    ADD CONSTRAINT urt_user_fk
        FOREIGN KEY (user_id) REFERENCES tab_users (id);

CREATE INDEX urt_token_idx ON tab_user_refresh_tokens (token);
