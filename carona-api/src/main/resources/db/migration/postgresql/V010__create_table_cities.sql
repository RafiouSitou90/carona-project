CREATE TABLE tab_cities
(
    id         BIGSERIAL              NOT NULL,
    name       CHARACTER VARYING(255) NOT NULL,
    created_at TIMESTAMP              NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP                       DEFAULT NULL
);

ALTER TABLE tab_cities
    ADD CONSTRAINT city_id_pk PRIMARY KEY (id),
    ADD CONSTRAINT city_name_pk UNIQUE (name);

CREATE INDEX city_name_idx ON tab_cities (name);

-- INSERT
INSERT INTO tab_cities (name, created_at)
VALUES ('Águas Claras', NOW()),
       ('Arniqueira', NOW()),
       ('Asa Sul', NOW()),
       ('Asa Norte', NOW()),
       ('Brasilândia', NOW()),
       ('Candangolândia', NOW()),
       ('Ceilândia', NOW()),
       ('Cruzeiro', NOW()),
       ('Fercal', NOW()),
       ('Gama', NOW()),
       ('Guará I', NOW()),
       ('Guará II', NOW()),
       ('Itapoã', NOW()),
       ('Jardim Botânico', NOW()),
       ('Lago Norte', NOW()),
       ('Lago Sul', NOW()),
       ('Núcleo Bandeirante', NOW()),
       ('Paranoá', NOW()),
       ('Park Way', NOW()),
       ('Planaltina', NOW()),
       ('Plano Piloto', NOW()),
       ('Recanto das Emas', NOW()),
       ('Riacho Fundo I', NOW()),
       ('Riacho Fundo II', NOW()),
       ('Samambaia', NOW()),
       ('Santa Maria', NOW()),
       ('São Sebastião', NOW()),
       ('SCIA', NOW()),
       ('SIA', NOW()),
       ('Sobradinho I', NOW()),
       ('Sobradinho II', NOW()),
       ('Sol Nascente/Pôr do Sol', NOW()),
       ('Sudoeste/Octogonal', NOW()),
       ('Taguatinga', NOW()),
       ('Taguatinga Centro', NOW()),
       ('Taguatinga Norte', NOW()),
       ('Taguatinga Sul', NOW()),
       ('Varjão', NOW()),
       ('Vicente Pires', NOW());
