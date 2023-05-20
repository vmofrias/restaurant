ALTER TABLE IF EXISTS restaurant_schema.restaurante 
ADD CONSTRAINT unique_restaurante_nome UNIQUE (nome);

ALTER TABLE IF EXISTS restaurant_schema.cozinha 
ADD CONSTRAINT unique_cozinha_nome UNIQUE (nome);
