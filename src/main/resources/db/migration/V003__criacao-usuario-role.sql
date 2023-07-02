CREATE TABLE restaurant_schema.role (
  id SERIAL,
  name VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE restaurant_schema.usuario (
  id BIGSERIAL,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE restaurant_schema.usuario_roles (
  usuario_id BIGINT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (usuario_id, role_id),
  CONSTRAINT fk_usuario_roles_usuario FOREIGN KEY (usuario_id) REFERENCES restaurant_schema.usuario (id),
  CONSTRAINT fk_usuario_roles_role FOREIGN KEY (role_id) REFERENCES restaurant_schema.role (id)
);