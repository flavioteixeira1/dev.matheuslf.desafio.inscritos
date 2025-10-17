Sistema de Gerenciamento de Projetos e Tarefas

Um sistema completo desenvolvido em Spring Boot para gerenciamento de projetos e tarefas, com autenticação JWT e controle de acesso baseado em roles.
✨ Funcionalidades
🔐 Autenticação e Autorização

    Registro de usuários com diferentes níveis de acesso

    Login com JWT (JSON Web Tokens)

    Controle de acesso baseado em roles (USER, ADMIN, SUPERVISOR)

    Senhas criptografadas com BCrypt

📋 Gerenciamento de Tarefas

    ✅ Criar, editar, visualizar e excluir tarefas

    🔍 Busca com filtros avançados (status, prioridade, projeto)

    📊 Atualização de status das tarefas

    🏷️ Prioridades (BAIXA, MÉDIA, ALTA)

    📅 Datas de vencimento

👥 Controle de Acesso por Role
Role	Permissões
USER	Criar, editar, visualizar e excluir próprias tarefas
SUPERVISOR	Todas permissões USER + gerenciar projetos
ADMIN	Todas permissões + exclusão de projetos
🛠️ Tecnologias Utilizadas

    Java 17+

    Spring Boot 3.x

    Spring Security com JWT

    Spring Data JPA

    H2 Database (desenvolvimento) / PostgreSQL (produção)

    SpringDoc OpenAPI 3 (Swagger)

    Maven

    Bean Validation

 

📚 Documentação da API
Acesse a documentação interativa:
text

http://localhost:8080/swagger-ui/index.html

🔑 Autenticação no Swagger

    Acesse o Swagger UI

    Clique no botão "Authorize" (cadeado)

    Insira seu token no formato: Bearer seu_token_jwt

🚀 Como Executar
Pré-requisitos

    Java 17 ou superior

    Maven 3.6+

    PostgreSQL (opcional para produção)

Passos para execução

    Clone o repositório

bash

git clone https://github.com/flavioteixeira1/dev.matheuslf.desafio.inscritos
cd dev.matheuslf.desafio.inscritos

    Configure o banco de dados (application.properties)

properties

# Para H2 (desenvolvimento)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# Para PostgreSQL (produção)
spring.datasource.url=jdbc:postgresql://localhost:5432/gerenciamento
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

    Execute a aplicação

bash

mvn spring-boot:run

    Acesse a aplicação

text

http://localhost:8080

📡 Endpoints Principais
Autenticação

    POST /autenticacao/registrar - Registrar novo usuário

    POST /autenticacao/logar - Login e obtenção de token

Tarefas

    GET /tasks - Listar tarefas (com filtros opcionais)

    POST /tasks - Criar nova tarefa

    GET /tasks/{id} - Buscar tarefa por ID

    PUT /tasks/{id}/status - Atualizar status da tarefa

    DELETE /tasks/{id} - Excluir tarefa

Projetos

    GET /projects - Listar projetos

    POST /projects - Criar projeto (ADMIN/SUPERVISOR)

    PUT /projects/{id} - Editar projeto (ADMIN/SUPERVISOR)

    DELETE /projects/{id} - Excluir projeto (ADMIN)

🔍 Exemplos de Uso
Registrar usuário
json

POST /autenticacao/registrar
{
    "name": "João Silva",
    "email": "joao@email.com",
    "password": "senha123",
    "role": "USER"
}

Login
json

POST /autenticacao/logar
{
    "email": "joao@email.com",
    "password": "senha123"
}

Criar tarefa
json

POST /tasks
{
    "title": "Implementar feature X",
    "description": "Desenvolver a funcionalidade X do sistema",
    "dueDate": "2024-12-31T23:59:59",
    "status": "TODO",
    "priority": "HIGH",
    "projecttotaskDTO": {
        "id": 1
    }
}

Buscar tarefas com filtros
text

GET /tasks?projectId=1&status=DOING&priority=HIGH

🗄️ Estrutura do Projeto
text

src/
├── main/
│   ├── java/
│   │   └── dev/matheuslf/desafio/inscritos/
│   │       ├── Controller/        # Controladores REST
│   │       ├── Services/          # Lógica de negócio
│   │       ├── Repository/        # Interfaces JPA
│   │       ├── SecurityConfig/    # Configurações de segurança
│   │       ├── dto/              # Objetos de transferência de dados
│   │       └── model/            # Entidades JPA
│   └── resources/
│       └── application.properties

🔒 Segurança

    Tokens JWT com expiração configurável

    BCrypt para hash de senhas

    CORS configurado

    Validação de dados com Bean Validation

    Proteção contra referências circulares com DTOs

🐛 Solução de Problemas
Problema comum: Recursão infinita no JSON

Solução implementada: Uso de DTOs para quebrar o ciclo de referências entre Task ↔ Project
Problema comum: Autenticação no Swagger

Solução: Configuração do security scheme Bearer Token no OpenAPI
📈 Próximas Melhorias

    Paginação nas consultas

    Cache de consultas frequentes

    Upload de arquivos para tarefas

    Notificações por email

    Métricas e monitoramento


