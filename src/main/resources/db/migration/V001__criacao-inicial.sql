CREATE TABLE restaurant_schema.cozinha (
	id BIGSERIAL NOT NULL,
    nome VARCHAR(60) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE restaurant_schema.restaurante (
	id BIGSERIAL NOT NULL,
    email_owner VARCHAR(100) NOT NULL,
    nome VARCHAR(80) NOT NULL,
    taxa_frete NUMERIC(38,2) NOT NULL,
    cozinha_id BIGINT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS restaurant_schema.restaurante 
ADD CONSTRAINT fk_restaurante_cozinha
FOREIGN KEY (cozinha_id) 
REFERENCES restaurant_schema.cozinha;