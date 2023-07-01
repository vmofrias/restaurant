DELETE FROM restaurant_schema.restaurante;
DELETE FROM restaurant_schema.cozinha;

INSERT INTO restaurant_schema.cozinha VALUES (1, 'Brasileira');
INSERT INTO restaurant_schema.cozinha VALUES (2, 'Japonesa');
INSERT INTO restaurant_schema.cozinha VALUES (3, 'Italiana');
INSERT INTO restaurant_schema.cozinha VALUES (4, 'Coreana');
INSERT INTO restaurant_schema.cozinha VALUES (5, 'Mexicana');
INSERT INTO restaurant_schema.cozinha VALUES (6, 'Francesa');
INSERT INTO restaurant_schema.cozinha VALUES (7, 'Chinesa');
INSERT INTO restaurant_schema.cozinha VALUES (8, 'Norte Americana');

INSERT INTO restaurant_schema.restaurante  VALUES (1, 'vmofrias@gmail.com', 'Restaurante do Vinicius', 10.0, 1);
INSERT INTO restaurant_schema.restaurante  VALUES (2, 'ldskmain@gmail.com', 'Restaurante do LDSK', 15.0, 2);
INSERT INTO restaurant_schema.restaurante  VALUES (3, 'bia@gmail.com', 'Restaurante da Bibia', 5.0, 3);