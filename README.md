
# Projeto Restaurante

Projeto básico com foco nas boas práticas, tratamento de exceptions, testes e segurança. 

## Pré-requisitos

* Java 17
* Maven
* PostgreSQL
* Docker/Podman (Opcional)

## Variáveis de Ambiente

### PostgreSQL

Caso queira executar o *PostgreSQL* via container pelo docker-compose, você vai precisar definir os valores das seguintes variáveis de ambiente no arquivo **postgres-db.env**, que se encontra no diretório **/config** do projeto.   

| *POSTGRES*  |  *DESCRIÇÃO* |
|---|---|
|  `POSTGRES_USER` | Define o nome do usuário que irá ser criado com os poderes de Super Usuário do banco.  |  
| `POSTGRES_PASSWORD`  | Define a senha para este usuário. |
| `POSTGRES_DB`  |  Define o nome para o database que será criado quando a imagem for iniciada pela primeira vez. Se não for especificado, o valor do POSTGRES_USER será usado para isso.|


#### - O nome para o database que está sendo utilizado como padrão no projeto é **`restaurant`**.

### Restaurant Application

Para rodar essa aplicação, você vai precisar definir os valores das seguintes variáveis de ambiente no local em que você irá executá-lo. 

| *APPLICATION*  |  *DESCRIÇÃO* |
|---|---|
| `db_host`  | Define o host na URL de conexão do banco de dados.|
| `db_name`  | Define o nome do database a ser acessado na URL de conexão do banco de dados. |
|  `db_user` | Define o nome do usuário que irá fazer acesso ao banco de dados.  |  
| `db_password`  | Define a senha deste usuário. |

#### - Os valores *default* para as variáveis `db_host` e `db_name` são `localhost` e `restaurant`, respectivamente.

#### - Caso não tenha configurado um novo usuário no banco de dados, insira as credenciais do Super Usuário que foi criado quando a imagem do *PostgreSQL* foi iniciada.

## Como subir a aplicação

#### - Faça o build da aplicação com o *Maven*:

```bash
  mvn clean install
```

#### - Em seguida, execute o artefato da aplicação com o seguinte comando:

```bash
  java -Ddb_host=localhost -Ddb_name=restaurant -Ddb_user=ldskadmin -Ddb_password=arrozdoce -jar target/restaurant-2023.03.05.a-SNAPSHOT.jar
```

#### ou

#### - Caso não tenha modificado os valores *default* do `db_host` e `db_name`:

```bash
  java -Ddb_user=ldskadmin -Ddb_password=arrozdoce -jar target/restaurant-2023.03.05.a-SNAPSHOT.jar
```
    
## Documentação da API

`http://localhost:8080/swagger-ui/index.html`

### Notas da versão do projeto (2023.03.05.a) - 

* Adicionada documentação do projeto e API (Swagger).
* Implementados testes unitários e de integração.
