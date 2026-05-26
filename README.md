# ms-aluno

Microsserviço de gerenciamento de alunos do SSA (Sistema de Suporte Acadêmico).

## Tecnologias

- Java 21
- Spring Boot 4.0.6
- Spring Data JPA
- PostgreSQL
- Lombok
- Eureka Client (Service Discovery)
- SpringDoc OpenAPI 3 (Swagger UI)
- Maven

## Funcionalidades

- **Cadastro de alunos**
- **Listagem paginada de alunos**
- **Busca de aluno por ID**
- **Atualização de dados do aluno**
- **Inativação de aluno**

## Endpoints

| Método | Rota                      | Descrição                        |
|--------|---------------------------|----------------------------------|
| GET    | `/alunos`                 | Lista todos os alunos (paginado) |
| GET    | `/alunos/{id}`            | Busca aluno por ID               |
| POST   | `/alunos`                 | Cadastra novo aluno              |
| PUT    | `/alunos/{id}`            | Atualiza dados do aluno          |
| PATCH  | `/alunos/{id}/inativar`   | Inativa o aluno                  |

## Pré-requisitos

- JDK 21+
- Maven 3.9+
- PostgreSQL

## Configuração

1. Crie o banco de dados PostgreSQL:
```sql
CREATE DATABASE ssa_aluno;
```

2. Configure as credenciais em `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ssa_aluno
spring.datasource.username=postgres
spring.datasource.password=suasenha
```

## Execução

```bash
mvn spring-boot:run
```

A aplicação iniciará em `http://localhost:8083`.

## Swagger UI

Acesse a documentação interativa dos endpoints em:

```
http://localhost:8083/swagger-ui/index.html
```

## Exemplos de uso

### Cadastrar aluno
```bash
curl -X POST http://localhost:8083/alunos \
  -H "Content-Type: application/json" \
  -d '{
    "matricula": "2024001",
    "nome": "Daniel Neves",
    "email": "daniel@ucsal.edu.br",
    "telefone": "71999999999",
    "periodo": 3,
    "status": true,
    "cursoId": 1,
    "cursoNome": "Sistemas de Informação"
  }'
```

### Listar todos os alunos
```bash
curl -X GET http://localhost:8083/alunos
```

### Buscar aluno por ID
```bash
curl -X GET http://localhost:8083/alunos/1
```

### Atualizar aluno
```bash
curl -X PUT http://localhost:8083/alunos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "matricula": "2024001",
    "nome": "Daniel Neves Atualizado",
    "email": "daniel@ucsal.edu.br",
    "telefone": "71999999999",
    "periodo": 4,
    "status": true,
    "cursoId": 1,
    "cursoNome": "Sistemas de Informação"
  }'
```

### Inativar aluno
```bash
curl -X PATCH http://localhost:8083/alunos/1/inativar
```

## Estrutura do Projeto

```
ms-aluno/
├── pom.xml
├── schema.sql
└── src/main/java/com/sistemassa/aluno/
    ├── MsAlunoApplication.java
    ├── entity/
    │   └── Aluno.java
    ├── dto/
    │   ├── AlunoRequestDTO.java
    │   └── AlunoResponseDTO.java
    ├── repository/
    │   └── AlunoRepository.java
    ├── service/
    │   └── AlunoService.java
    └── controller/
        └── AlunoController.java
```

## Service Discovery

O ms-aluno se registra automaticamente no **Eureka Server** ao iniciar.
Certifique-se de que o `service-discovery` esteja rodando na porta `8761` antes de subir este serviço.

## Observação

O `cursoId` e `cursoNome` são referências simples ao `ms-academico`,
sem join direto entre serviços, seguindo o padrão de microsserviços.
