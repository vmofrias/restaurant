DELETE FROM restaurant_schema.restaurante;
DELETE FROM restaurant_schema.cozinha;

ALTER SEQUENCE restaurant_schema.restaurante_id_seq RESTART 1;
ALTER SEQUENCE restaurant_schema.cozinha_id_seq RESTART 1;

INSERT INTO restaurant_schema.cozinha(nome) VALUES ('Brasileira');
INSERT INTO restaurant_schema.cozinha(nome) VALUES ('Japonesa');
INSERT INTO restaurant_schema.cozinha(nome) VALUES ('Italiana');
INSERT INTO restaurant_schema.cozinha(nome) VALUES ('Coreana');
INSERT INTO restaurant_schema.cozinha(nome) VALUES ('Mexicana');
INSERT INTO restaurant_schema.cozinha(nome) VALUES ('Francesa');
INSERT INTO restaurant_schema.cozinha(nome) VALUES ('Chinesa');
INSERT INTO restaurant_schema.cozinha(nome) VALUES ('Norte Americana');

INSERT INTO restaurant_schema.restaurante(email_owner, nome, taxa_frete, cozinha_id)  VALUES ('vmofrias@gmail.com', 'Restaurante do Vinicius', 10.0, 1);
INSERT INTO restaurant_schema.restaurante(email_owner, nome, taxa_frete, cozinha_id)  VALUES ('ldskmain@gmail.com', 'Restaurante do LDSK', 15.0, 2);
INSERT INTO restaurant_schema.restaurante(email_owner, nome, taxa_frete, cozinha_id)  VALUES ('bia@gmail.com', 'Restaurante da Bibia', 5.0, 3);