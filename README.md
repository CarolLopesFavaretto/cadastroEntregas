# Cadastro de Entregas
API para realizar cadastro de entregas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-D4FAFF?style=for-the-badge&logo=docker)

Proposta do desenvolvimento é efetuar uma api para cadastro de entregas.

## Índice

- [Instalaçao](#instalaçao)
- [API Endpoints](#api-endpoints)
- [Detalhes da Implementação](#detalhes-da-implementação)

## Instalaçao

1. Clone o repositorio:

```bash
git clone https://github.com/CarolLopesFavaretto/cadastroEntregas
```

2. Instale as dependências com Maven executando o comando abaixo na raiz do projeto:

```bash
mvn clean install
```

3. Execute o Docker Compose para iniciar os serviços:
    
```bash
docker-compose up
  ```

## API Endpoints
A API fornece os seguintes endpoints:


```markdown
POST v1/delivery - Cadastro de entrega
```
Request:
```json
{
  "quantityPackages": 0,
  "deliveryDeadline": "2023-10-01",
  "nameClient": "John Doe",
  "cpfClient": "00000000000",
  "address": {
    "zipCode": "12345-678",
    "uf": "SP",
    "city": "São Paulo",
    "district": "Centro",
    "street": "Rua Exemplo",
    "number": "123",
    "complement": "Apto 456"
  }
}
```

Response:
```json
{
  "id": "1",
  "quantityPackages": 0,
  "deliveryDeadline": "2023-10-01",
  "nameClient": "John Doe",
  "cpfClient": "00000000000",
  "address": {
    "zipCode": "12345-678",
    "uf": "SP",
    "city": "São Paulo",
    "district": "Centro",
    "street": "Rua Exemplo",
    "number": "123",
    "complement": "Apto 456"
  }
}
```
```
statusCode: 200
```

Error:
```json
{
  "statusCode": 400,
  "timestamp": "2024-06-06T21:05:15.301+00:00",
  "message": "Tipo de cfp invalido: 00000000000",
  "description": "uri=/delivery;client=0:0:0:0:0:0:0:1"
}
```

```markdown
GET v1/delivery/{id} - Busca entrega por id
```

Response:
```json
{
  "id": "1",
  "quantityPackages": 0,
  "deliveryDeadline": "2023-10-01",
  "nameClient": "John Doe",
  "cpfClient": "00000000000",
  "address": {
    "zipCode": "12345-678",
    "uf": "SP",
    "city": "São Paulo",
    "district": "Centro",
    "street": "Rua Exemplo",
    "number": "123",
    "complement": "Apto 456"
  }
}
```
```
statusCode: 200
```

Error:
```json
{
  "statusCode": 404,
  "timestamp": "2024-06-06T21:05:56.623+00:00",
  "message": "Delivery not found with id 2",
  "description": "uri=/delivery/2"
}
```

```markdown
PUT v1/delivery/{id} - Atualizacao do cadastro de entrega
```
Request:
```json
{
  "quantityPackages": 10,
  "deliveryDeadline": "2023-10-01",
  "nameClient": "John Doe",
  "cpfClient": "41246532808",
  "address": {
    "zipCode": "12345-678",
    "uf": "SP",
    "city": "São Paulo",
    "district": "Centro",
    "street": "Rua Exemplo",
    "number": "123",
    "complement": "Apto 456"
  }
}
```

Response:
```json
{
  "id": "1",
  "quantityPackages": 10,
  "deliveryDeadline": "2023-10-01",
  "nameClient": "John Doe",
  "cpfClient": "41246532808",
  "address": {
    "zipCode": "12345-678",
    "uf": "SP",
    "city": "São Paulo",
    "district": "Centro",
    "street": "Rua Exemplo",
    "number": "123",
    "complement": "Apto 456"
  }
}
```
```
statusCode: 200
```
Error:
```json
{
  "statusCode": 404,
  "timestamp": "2024-06-06T21:05:56.623+00:00",
  "message": "Delivery not found with id 2",
  "description": "uri=/delivery/2"
}
```

```markdown
DELETE v1/delivery/{id} - Deleta entrega por id
```

Response:
```json
{}
```
```
statusCode: 204
```

## Detalhes da Implementação
```markdown
- Arquitetura

A aplicação foi desenvolvida utilizando a arquitetura de camadas, 
separando as responsabilidades em: Controller, Service e Repository, facilita a manutenção do código e testes dos componentes.  

- Design

Optei por utilizar o padrão REST para a construção da API, 
garantindo uma interface simples e padronizada. 
A documentação da API é gerada automaticamente utilizando o springdoc-openapi.  

- Controle de Logs
Para o controle de logs e melhora na monitoração em ambientes foi utilizamos o SLF4J. 

- Tratamento de Exceções

O tratamento de exceções é realizado através de um 
@ControllerAdvice, garantindo que forneça feedback claro em caso de erros.

- Justificativa das Tecnologias Utilizadas

Spring Boot: Framework utilizado para desenvolvimento de aplicações Java, 
que facilita a configuração e inicialização do projeto, alem de ser a mais utilizada no mercado.

Maven: Gerenciador de dependências que simplifica a construção e gerenciamento do ciclo de vida do projeto.

Docker: Ferramenta de containerização que garante a portabilidade e consistência do ambiente de execução da aplicação.

PostgreSQL: Banco de dados relacional escolhido pela sua robustez e suporte a operações complexas.

Postman: Ferramenta utilizada para testar e documentar APIs.


- Boas Práticas de Programação

Testes Unitários: Implementação de testes unitários para garantir a qualidade e cobertura do código, assim como
a implementacao de teste de integração com Test Container.

Documentação: Uso de springdoc-openapi para gerar documentação automática da API.

Validação: Uso de anotações de validação (@Valid, @NotNull, etc.) para garantir a integridade dos dados recebidos pela API.
```