
    create table restaurant_schema.cozinha (
       id bigserial not null,
        nome varchar(255) not null,
        primary key (id)
    );

    create table restaurant_schema.restaurante (
       	id bigserial not null,
        email_owner varchar(255) not null,
        nome varchar(255) not null,
        taxa_frete numeric(38,2) not null,
        cozinha_id bigint not null,
        primary key (id)
    );

    alter table if exists restaurant_schema.restaurante 
       add constraint FK76grk4roudh659skcgbnanthi 
       foreign key (cozinha_id) 
       references restaurant_schema.cozinha;
