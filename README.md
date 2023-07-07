
# Projeto Restaurante

Projeto básico com foco nas boas práticas, tratamento de exceptions, testes automatizados e segurança. 

## Pré-requisitos

* Java 17
* Maven
* PostgreSQL
* Docker/Podman (Opcional)

## Variáveis de Ambiente

Para rodar essa aplicação, você vai precisar definir os valores das seguintes variáveis de ambiente no local de execução do projeto. 

| *APPLICATION*  |  *DESCRIÇÃO* |
|---|---|
| `db_host`  | Define o host na URL de conexão do banco de dados.|
| `db_name`  | Define o nome do database a ser acessado na URL de conexão do banco de dados. |
|  `db_user` | Define o nome do usuário que irá fazer acesso ao banco de dados.  |  
| `db_password`  | Define a senha deste usuário. |

#### - Os valores *default* para as variáveis `db_host` e `db_name`, são `localhost` e `restaurant`, respectivamente.

## Como subir o projeto

#### - Clone o projeto com o Git e navegue até o diretório do mesmo.

#### - Configure e execute o [PostgreSQL](./postgresql.md).

#### - Faça o build da aplicação com o *Maven*:

```bash
  mvn clean install
```

#### - Em seguida, execute o artefato da aplicação com o seguinte comando:

```bash
  java -Ddb_host=localhost -Ddb_name=restaurant -Ddb_user=ldskadmin -Ddb_password=arrozdoce -jar target/restaurant-2023.07.01.b-SNAPSHOT.jar
```

#### ou

#### - Caso não tenha modificado os valores *default* do `db_host` e `db_name`:

```bash
  java -Ddb_user=ldskadmin -Ddb_password=arrozdoce -jar target/restaurant-2023.07.01.b-SNAPSHOT.jar
```
    
## Documentação da API

`http://localhost:8080/swagger-ui/index.html`

### Notas da versão do projeto (2023.07.01.a) - 

* Spring Security: implementação de Usuário e Roles; persistência de usuários no banco de dados com senha encriptada utilizando BCrypt e criação de UserDetailsService customizado.

* Criação de estrutura para autenticação e autorização de usuários utilizando token JWT.
