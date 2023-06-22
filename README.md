![image](https://user-images.githubusercontent.com/107167711/226222830-db2f671b-3e9f-4bd5-bb1e-f339a85abe3a.png)

# Introdução

O **Forum Alura** é um projeto para o desafio **Challenge ONE Back End**, que consiste em criar uma **API REST**, usando ``Spring`` com Java e ``JPA`` com o ``Hibernate``, com o banco de dados ``MySQL``, aonde permite o usuário criar, mostrar, atualizar e deletar um tópico, ou seja, um **CRUD**.

A API REST contém os seguintes recursos:

- Criar um novo tópico
- Mostrar todos os tópicos criados
- Mostrar um tópico específico
- Atualizar um tópico
- Eliminar um tópico

E no final da implementação, a API terá as seguintes funcionalidades:

1. API com rotas implementadas seguindo as melhores práticas do modelo REST;
2. Validações realizadas seguindo as regras de negócio;
3. Implementação de uma base de dados para a persistência da informação;
4. Serviço de autenticação/autorização para restringir o acesso à informação usando JWT.

# Documentação

Antes de poder manipular os tópicos, é necessário gerar um JWT fornecendo um usuário e senha válidos como JSON no corpo da requisição:

## Gerando um JWT

**POST /login**
```json
{
	"usuario": "usuario@admin.com",
	"senha": "12345678"
}
```

Retorna um **JWT Token**
```JSON
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgZm9ydW0uY29tIiwic3ViIjoidXN1YXJpb0BhZG1pbi5jb20iLCJleHAiOjE2ODc0NDE4MzJ9.U5OKCOiYquc_Cdhd5EQlNCP1EenkpskU0EZrGt0nFcM"
}
```

O token tem que ser passado no Cabeçalho/Header de todas as requisições, usando o Cabeçalho `Authorization`, exemplo:

**GET /topico**
```HTTP
Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBUEkgZm9ydW0uY29tIiwic3ViIjoidXN1YXJpb0BhZG1pbi5jb20iLCJleHAiOjE2ODc0NDE4MzJ9.U5OKCOiYquc_Cdhd5EQlNCP1EenkpskU0EZrGt0nFcM
```

> Por padrão, o token tem uma duração de 2 horas.

## Criando, listando, atualizando e deletando os tópicos

## Listando os tópicos

### Retornando todos

**GET /topico**
**status** `200`
Retorna uma página de tópico, mostrando apenas os 10 primeiros tópico em ordem de data de criação.
```JSON

{
  "content": [
    {
      "id": 3,
      "titulo": "Test",
      "mensagem": "Testando 1234566",
      "autor": "Eu",
      "curso": "nenhum",
      "dataCriacao": "2023-06-19T12:47:24",
      "estado": "ABERTO"
    },
    {
      "id": 6,
      "titulo": "Test",
      "mensagem": "Testando 12345667",
      "autor": "Eu",
      "curso": "",
      "dataCriacao": "2023-06-19T12:57:09",
      "estado": "ABERTO"
    },
	...
    {
      "id": 25,
      "titulo": "Test2",
      "mensagem": "asdf",
      "autor": "Euu",
      "curso": "",
      "dataCriacao": "2023-06-19T13:11:01",
      "estado": "ABERTO"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 10,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalElements": 5,
  "totalPages": 1,
  "size": 10,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "first": true,
  "numberOfElements": 5,
  "empty": false
}
```

É possível customizar como será o retorno dos tópicos, fornecendo parâmetros pela URL., por exemplo:

**GET /topico?size=2**
Mostrando apenas 2 tópicos.
```JSON
{
  "content": [
    {
      "id": 3,
      "titulo": "Test",
      "mensagem": "Testando 1234566",
      "autor": "Eu",
      "curso": "nenhum",
      "dataCriacao": "2023-06-19T12:47:24",
      "estado": "ABERTO"
    },
    {
      "id": 6,
      "titulo": "Test",
      "mensagem": "Testando 12345667",
      "autor": "Eu",
      "curso": "",
      "dataCriacao": "2023-06-19T12:57:09",
      "estado": "ABERTO"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
	...
}
```

**GET /topico?page=1**
Retornando a segunda página.
```JSON
{
  "content": [
    {
      "id": 7,
      "titulo": "Test",
      "mensagem": "",
      "autor": "Eu",
      "curso": "",
      "dataCriacao": "2023-06-19T12:58:55",
      "estado": "ABERTO"
    },
    {
      "id": 15,
      "titulo": "Test",
      "mensagem": "asdf",
      "autor": "Euu",
      "curso": "",
      "dataCriacao": "2023-06-19T13:01:46",
      "estado": "ABERTO"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
...
```

### Retornando o tópico através do id

**GET /topico/{id}**
Retorna o tópico pelo id.
```JSON
{
  "id": 7,
  "titulo": "Test",
  "mensagem": "",
  "autor": "Eu",
  "curso": "",
  "dataCriacao": "2023-06-19T12:58:55",
  "estado": "ABERTO"
}
```

## Criando um novo tópico

**POST /topico**
**status** `201`
Cria um novo tópico, sendo as chaves `titulo`, `mensagem`, `autor` e `curso` obrigatórios.
```JSON
{
  "titulo": "Test",
  "mensagem": "Testando 123456",
  "autor": "Eu",
  "curso": "nenhum"
}
```

## Atualizando

**PUT /topico/{id}**
**status** `200`
Atualiza um tópico fornecido pelo id na URL, podendo atualizar o titulo, a mensagem ou o estado (podendo ser ABERTO ou FECHADO) do tópico, sendo todos eles opcionais.
```JSON
{
  "titulo": "Titulo atualizado"
}
```

## Deletando

**DELETE /topico/{id}**
**status** `204`
Deleta o tópico fornecido pelo id na URL.

## Erros status

`400` - Possível erro ao enviar o JSON como resposta, retornando uma mensagem de erro descrevendo o possível problema;
`403` - Acesso proibido, sendo o Token JWT inválido, expirado ou inexistente;
`404` - Não foi encontrado nenhum recurso no lado do servidor;
`5xx` - Erro no lado do servidor.

# Iniciando o projeto

O projeto usa o Gradle para fazer a build, verifique se a sua IDE é compativel com o Gradle e faça a build.

Depois, inicie a classe ``ForumAluraApplication``.

# Planos futuros

- Adicionar um cadastro de usuário;
- Implementar sistema de respostas no tópico.

# Recursos utilizados

- **Java** 17;
- [**Spring Boot**](https://spring.io/projects/spring-boot) 3.1.0;
- [**Spring Security**](https://spring.io/projects/spring-security) - fornece recursos e funcionalidades que facilitam a implementação de autenticação (autenticação de usuários) e autorização (controle de acesso) em aplicativos baseados em Spring;
- [**Lombok**](https://projectlombok.org/) - Reduz boilerplate na criação de classes;
- [**Flyway**](https://flywaydb.org/) - Controle de versão para o Banco de Dados;
- [**MySQL**](https://www.mysql.com/) - Banco de dados SQL;
- [**Spring Boot Starter Data JPA**](https://spring.io/guides/gs/accessing-data-jpa/) - Interface usada para implementar o Mapeamento Objeto-Relacional (ORM);
- [**Bean Validation**](https://beanvalidation.org/) - Oferece anotações para validar os dados, reduzindo o boilerplate e garantindo a integridade dos dados;
- [**Java JWT**](https://jwt.io/) 4.4.0 - Manipulação dos tokens JWT;

