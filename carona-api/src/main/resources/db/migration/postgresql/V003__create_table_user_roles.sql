CREATE TABLE tab_user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);

ALTER TABLE tab_user_roles
    ADD CONSTRAINT ur_id_pk PRIMARY KEY (user_id, role_id),
    ADD CONSTRAINT ur_user_fk
        FOREIGN KEY (user_id) REFERENCES tab_users (id),
    ADD CONSTRAINT ur_role_fk
        FOREIGN KEY (role_id) REFERENCES tab_roles (id);
