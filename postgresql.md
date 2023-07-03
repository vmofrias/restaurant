# PostgreSQL

## Variáveis de Ambiente

Caso queira executar o *PostgreSQL* via container pelo docker-compose, você vai precisar definir os valores das seguintes variáveis de ambiente no arquivo **postgres-db.env**, que se encontra no diretório **/config** do projeto.   

| *POSTGRES*  |  *DESCRIÇÃO* |
|---|---|
|  `POSTGRES_USER` | Define o nome do usuário que irá ser criado com os poderes de Super Usuário do banco.  |  
| `POSTGRES_PASSWORD`  | Define a senha para este usuário. |
| `POSTGRES_DB`  |  Define o nome para o database que será criado quando a imagem for iniciada pela primeira vez. Se não for especificado, o valor do POSTGRES_USER será usado para isso.|


#### - O nome para o database que está sendo utilizado como padrão no projeto é **`restaurant`**.

## Como subir o PostgreSQL

#### - Digite o seguinte comando utilizando o docker-compose.yml:

```bash
  docker compose up postgres-db
```

#### ou

#### - Utilize-o em seu modo nativo fazendo o download do mesmo em [PostgreSQL Downloads](https://www.postgresql.org/download/). 
