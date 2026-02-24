# Lembretes

Aplicacao full-stack para gerenciar lembretes com autenticacao JWT, gamificacao (XP, moedas e niveis) e suporte a criacao de lembretes via IA (integracao com n8n).

**Visao Geral**
O projeto e dividido em dois modulos.
- `backend/lembrete`: API REST em Spring Boot.
- `frontend/lembretes`: SPA em Angular.

**Principais Funcionalidades**
- Cadastro e login de usuarios com JWT.
- CRUD de lembretes e conclusao de tarefas.
- Geracao de lembretes a partir de texto livre via IA (n8n).
- Gamificacao por XP, moedas e nivel do usuario.
- Perfil de jogo com resumo de progresso.

**Stack**
- Backend: Spring Boot 4.1.0-SNAPSHOT, Java 21, Spring Security (JWT), Spring Data JPA, Flyway, WebMvc/WebFlux, MapStruct, Lombok, PostgreSQL.
- Frontend: Angular 21, RxJS, TailwindCSS, lucide-angular, jwt-decode.
- Infra local: Docker Compose para Postgres e pgAdmin.
- Integracao IA: n8n via webhook HTTP.

**Estrutura de Pastas**
- `backend/lembrete/src/main/java/.../controller`: Controllers REST.
- `backend/lembrete/src/main/java/.../service`: Regras de negocio e integracoes.
- `backend/lembrete/src/main/java/.../entity`: Entidades JPA.
- `backend/lembrete/src/main/resources/db/migration`: Migracoes Flyway.
- `frontend/lembretes/src/app/features`: Modulos e componentes do app.

**Como Rodar Localmente**
1. Suba o Postgres com Docker.

```bash
cd backend/lembrete
docker compose up -d postgres
```

2. Inicie o backend.

```bash
cd backend/lembrete
./mvnw spring-boot:run
```

No Windows, use `mvnw.cmd`.

3. Inicie o frontend.

```bash
cd frontend/lembretes
npm install
npm start
```

**Configuracao**
- Backend usa `backend/lembrete/src/main/resources/application.properties`.
- Principais chaves:

```properties
app.jwt.secret=...               # segredo JWT
spring.datasource.url=...        # JDBC do Postgres
spring.datasource.username=...
spring.datasource.password=...
```

Recomenda-se substituir os valores padrao em ambientes nao locais.

**Integracao com IA (n8n)**
- A API faz chamadas para o webhook definido em `N8nClientService`.
- Arquivo: `backend/lembrete/src/main/java/io/github/adrianovictorn/lembrete/service/N8nClientService.java`.
- Ajuste a `baseUrl` conforme seu ambiente.

**Endpoints Principais**
- `POST /api/user/cadastrar` cria usuario.
- `POST /api/auth/login` autentica e retorna token JWT.
- `POST /api/lembrete/cadastrar` cria lembrete.
- `POST /api/lembrete/ai` cria lembrete via IA.
- `GET /api/lembrete/listar` lista lembretes.
- `PATCH /api/lembrete/concluido/{id}` conclui lembrete.
- `GET /api/game-profile/buscar/autenticado` retorna perfil de jogo do usuario autenticado.

**Fluxo Basico de Uso**
1. Cadastre o usuario.
2. Faca login e obtenha o `acess_token`.
3. Envie o token no header `Authorization: Bearer <token>`.
4. Crie lembretes manualmente ou via IA.
5. Conclua lembretes para ganhar XP e moedas.

**Testes**
- Backend:

```bash
cd backend/lembrete
./mvnw test
```

- Frontend:

```bash
cd frontend/lembretes
npm test
```

**Notas Importantes**
- O frontend consome a API em `http://localhost:8080` (URLs estao hardcoded nos services). Se mudar a porta, atualize os services ou use proxy.
- As migracoes Flyway rodam automaticamente na inicializacao do backend.
- Existe um README padrao do Angular em `frontend/lembretes/README.md` com comandos adicionais.
