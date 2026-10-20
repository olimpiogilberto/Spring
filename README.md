# Gestão de Empresas e Trabalhadores

## Visão geral

Este projeto é uma API REST desenvolvida em Java 17 com Spring Boot para gerir empresas, pessoas, vínculos empregatícios, endereços, salários e faturamento anual. A aplicação foi criada como um ambiente prático de estudo e desenvolvimento, com foco em consolidar conceitos de backend, JPA, validação, regras de negócio, testes e documentação de API.

O sistema permite:

- cadastrar empresas com CNPJ, razão social e situação cadastral;
- cadastrar pessoas com CPF, nome, data de nascimento e situação;
- associar pessoas a empresas por meio de vínculos empregatícios;
- registrar salário, cargo, data de início, status e endereço;
- consultar empresa por CPF, salário por CPF, contagem de empregados e faturamento anual;
- manter estrutura organizada em camadas com controllers, services, repositories, DTOs e exceptions;
- disponibilizar documentação automática com Swagger/OpenAPI.

## Objetivo do projeto

O objetivo principal é aprender fazendo. O projeto combina estudo teórico com prática real, seguindo uma evolução gradual de conceitos do Spring Boot e do desenvolvimento backend, sem perder o foco em uma solução funcional e bem estruturada.

## Status atual

O projeto já possui uma base funcional com os principais módulos implementados e testados:

- [x] Estrutura inicial do Spring Boot
- [x] Modelagem de empresas, pessoas, vínculos e faturamento
- [x] Persistência com H2
- [x] API REST com endpoints CRUD
- [x] Validações de dados e regras de negócio
- [x] Tratamento padronizado de erros
- [x] Documentação OpenAPI/Swagger
- [x] Testes de integração da API
- [ ] Listagem detalhada de pessoas e vínculos por empresa
- [ ] Histórico de cargos e cálculo de tempo no cargo
- [ ] Autenticação e autorização
- [ ] Migração versionada e configuração de produção
- [ ] Deploy em ambiente simples

## Tecnologias

- Java 17
- Spring Boot 3.5.3
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- Maven
- JUnit 5
- Mockito
- SpringDoc OpenAPI / Swagger UI

## Estrutura do projeto

```text
src/
├── main/
│   ├── java/br/com/giba/cadmembros/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── exception/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── service/
│   │   ├── validation/
│   │   └── ProjetoSpringApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/br/com/giba/cadmembros/
        └── GestaoApiIntegrationTests.java
```

## Requisitos

Antes de executar o projeto, verifique se o ambiente atende aos itens abaixo:

- Java 17
- Maven 3.9.3 ou superior
- Git
- IDE opcional: IntelliJ IDEA, VS Code, Eclipse

## Como executar

1. Clone o repositório:

```bash
git clone https://github.com/olimpiogilberto/Spring.git
cd Spring
```

2. Rode a aplicação com Maven:

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
mvnw.cmd spring-boot:run
```

3. A aplicação fica disponível em:

```text
http://localhost:8080
```

## Configuração da aplicação

O arquivo principal de configuração está em:

```text
src/main/resources/application.properties
```

Conteúdo atual:

```properties
spring.application.name=gestao-empresas
spring.datasource.url=jdbc:h2:mem:gestao;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

### Banco de dados H2

A aplicação usa H2 em memória para desenvolvimento local.

- URL JDBC: `jdbc:h2:mem:gestao`
- Usuário: `sa`
- Senha: vazia
- Console web: `http://localhost:8080/h2-console`

## Documentação da API

A API possui documentação automática com Swagger/OpenAPI.

- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Endpoints principais

### Empresas

Base: `/api/empresas`

- `POST /api/empresas` — cadastra uma empresa
- `GET /api/empresas` — lista empresas
- `GET /api/empresas/{id}` — busca empresa por ID
- `PUT /api/empresas/{id}` — atualiza empresa
- `DELETE /api/empresas/{id}` — exclui empresa
- `PUT /api/empresas/{id}/endereco` — atualiza endereço da empresa
- `DELETE /api/empresas/{id}/endereco` — remove endereço da empresa

### Pessoas

Base: `/api/pessoas`

- `POST /api/pessoas` — cadastra uma pessoa
- `GET /api/pessoas` — lista pessoas
- `GET /api/pessoas/{id}` — busca pessoa por ID
- `PUT /api/pessoas/{id}` — atualiza pessoa
- `DELETE /api/pessoas/{id}` — exclui pessoa
- `PUT /api/pessoas/{id}/endereco` — atualiza endereço da pessoa
- `DELETE /api/pessoas/{id}/endereco` — remove endereço da pessoa

### Vínculos

Base: `/api/vinculos`

- `POST /api/vinculos` — cadastra vínculo
- `GET /api/vinculos` — lista vínculos
- `GET /api/vinculos/{id}` — busca vínculo por ID
- `PUT /api/vinculos/{id}` — atualiza vínculo
- `DELETE /api/vinculos/{id}` — exclui vínculo

### Consultas

Base: `/api/consultas`

- `GET /api/consultas/empresa-por-cpf/{cpf}` — retorna a empresa do vínculo ativo da pessoa
- `GET /api/consultas/empregados/{cnpj}/contagem` — conta empregados ativos por CNPJ
- `GET /api/consultas/salario/{cpf}` — retorna salário do vínculo ativo da pessoa
- `GET /api/consultas/faturamento/{cnpj}?ano={ano}` — retorna faturamento anual da empresa

### Faturamento

Base: `/api/empresas/{cnpj}/faturamentos`

- `POST /api/empresas/{cnpj}/faturamentos` — cadastra faturamento anual
- `GET /api/empresas/{cnpj}/faturamentos` — lista faturamentos da empresa
- `PUT /api/empresas/{cnpj}/faturamentos/registro/{id}` — atualiza registro de faturamento
- `DELETE /api/empresas/{cnpj}/faturamentos/registro/{id}` — remove registro de faturamento

## Exemplos de payload

### Empresa

```json
{
  "cnpj": "11.222.333/0001-81",
  "razaoSocial": "Acme Ltda",
  "situacao": "ATIVA",
  "endereco": {
    "logradouro": "Rua Um",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "01000-000"
  }
}
```

### Pessoa

```json
{
  "cpf": "529.982.247-25",
  "nome": "Ana Souza",
  "dataNascimento": "1990-01-02",
  "situacao": "ATIVA"
}
```

### Vínculo empregatício

```json
{
  "pessoaId": 1,
  "empresaId": 1,
  "cargo": "EMPREGADO",
  "salario": 5250.50,
  "dataInicio": "2023-01-01",
  "status": "ATIVO"
}
```

### Faturamento anual

```json
{
  "ano": 2025,
  "valor": 1200000.00
}
```

## Regras de negócio principais

O sistema foi pensado para manter integridade entre as entidades e garantir consistência nas consultas.

### Cadastro de empresa

- CNPJ é obrigatório
- CNPJ deve ser válido
- CNPJ deve ser único
- `RAZAO_SOCIAL` é obrigatório
- `SITUACAO` deve estar em um valor válido, como `ATIVA` ou `INATIVA`

### Cadastro de pessoa

- CPF é obrigatório
- CPF deve ser válido
- CPF deve ser único
- Nome é obrigatório
- Data de nascimento pode ser informada
- Situação da pessoa deve refletir o estado do registro ou do vínculo

### Vínculo empregatício

- Pessoa e empresa devem existir
- O vínculo pode ter cargo, salário, status e data de início
- A aplicação aceita consultas por pessoa ou empresa para obter dados do vínculo ativo

### Endereço

- Endereço pode ser associado a pessoa ou empresa
- O mesmo modelo de entidade é reutilizado para representar o endereço em contextos diferentes

### Consultas principais

- consultar a empresa em que uma pessoa trabalha com base no CPF;
- contar empregados ativos por empresa com base no CNPJ;
- obter salário atual da pessoa pelo CPF;
- consultar faturamento anual pela empresa e ano.

## Validações e erros esperados

A aplicação utiliza validações de entrada e trata erros com respostas HTTP apropriadas:

- `400 Bad Request` — dados inválidos ou malformados
- `404 Not Found` — recurso não encontrado
- `409 Conflict` — conflito de dados, como CNPJ ou CPF duplicado

Além disso, a aplicação usa validadores para CPF e CNPJ, garantindo maior consistência nas regras de negócio.

## Testes

Os testes de integração cobrem o fluxo principal da API, incluindo:

- disponibilidade da documentação OpenAPI;
- cadastro de empresa, pessoa e vínculo;
- consultas por CPF e CNPJ;
- faturamento anual;
- validação de entradas inválidas;
- tratamento de erros e cenários de ausência de recurso.

Para executar os testes:

```bash
./mvnw test
```

No Windows:

```powershell
mvnw.cmd test
```

## Modelo de dados (rascunho inicial)

A estrutura inicial da base foi pensada para esse domínio e pode evoluir conforme o projeto avança.

```sql
CREATE TABLE empresa (
    id_empresa BIGINT PRIMARY KEY AUTO_INCREMENT,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    razao_social VARCHAR(150) NOT NULL,
    situacao VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pessoa (
    id_pessoa BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_nascimento DATE,
    situacao VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cargo (
    id_cargo BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255)
);

CREATE TABLE vinculo_emprego (
    id_vinculo BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_pessoa BIGINT NOT NULL,
    id_empresa BIGINT NOT NULL,
    id_cargo BIGINT NOT NULL,
    salario DECIMAL(10,2),
    data_inicio DATE,
    data_fim DATE,
    status VARCHAR(20) NOT NULL,
    tempo_cargo VARCHAR(50),

    CONSTRAINT fk_vinculo_pessoa
        FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),

    CONSTRAINT fk_vinculo_empresa
        FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa),

    CONSTRAINT fk_vinculo_cargo
        FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo)
);
```

## Roadmap futuro

- detalhamento de listagens e paginação;
- histórico de cargos e cálculo de tempo no cargo;
- autenticação e autorização;
- migrações versionadas com Flyway/Liquibase;
- configuração para produção;
- deploy em ambiente simples e documentação operacional.

## Observações finais

Este projeto funciona como uma base sólida de aprendizado em Spring Boot, combinando teoria e prática em um caso realista de negócio. A arquitetura foi organizada para evoluir gradualmente, permitindo adicionar novos módulos, regras e integrações sem perder clareza e consistência.

Se você está começando a estudar Spring Boot, este repositório é uma boa referência para entender como organizar uma API REST com persistência, regras de negócio, documentação e testes.


Campos:

- `id_vinculo` — identificador do vínculo
- `id_pessoa` — pessoa vinculada
- `id_empresa` — empresa vinculada
- `id_cargo` — cargo ocupado
- `salario` — salário do empregado
- `data_inicio` — início do vínculo
- `data_fim` — fim do vínculo, se houver
- `status` — situação do vínculo, como `ATIVO`, `INATIVO`
- `tempo_cargo` — tempo de atuação no cargo, campo auxiliar para cálculo ou exibição

### 5. Tabela `endereco`

```sql
CREATE TABLE endereco (
    id_endereco BIGINT PRIMARY KEY AUTO_INCREMENT,
    tipo_entidade VARCHAR(20) NOT NULL,
    id_entidade BIGINT NOT NULL,
    logradouro VARCHAR(200) NOT NULL,
    numero VARCHAR(20),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(9),
    pais VARCHAR(100) DEFAULT 'Brasil'
);
```

Campos:

- `id_endereco` — identificador do endereço
- `tipo_entidade` — informa se o endereço pertence a `PESSOA` ou `EMPRESA`
- `id_entidade` — identificador da pessoa ou empresa
- `logradouro` — rua ou avenida
- `numero` — número do endereço
- `complemento` — complemento
- `bairro` — bairro
- `cidade` — cidade
- `estado` — UF
- `cep` — CEP
- `pais` — país

### 6. Tabela `faturamento_anual`

```sql
CREATE TABLE faturamento_anual (
    id_faturamento BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_empresa BIGINT NOT NULL,
    ano INT NOT NULL,
    valor DECIMAL(15,2) NOT NULL,

    CONSTRAINT fk_faturamento_empresa
        FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa)
);
```

Campos:

- `id_faturamento` — identificador do faturamento
- `id_empresa` — empresa relacionada
- `ano` — ano do faturamento
- `valor` — valor registrado no período

### 7. Tabela `historico_cargo`

```sql
CREATE TABLE historico_cargo (
    id_historico BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_pessoa BIGINT NOT NULL,
    id_empresa BIGINT NOT NULL,
    id_cargo BIGINT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE,

    CONSTRAINT fk_historico_pessoa
        FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa),

    CONSTRAINT fk_historico_empresa
        FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa),

    CONSTRAINT fk_historico_cargo
        FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo)
);
```

Campos:

- `id_historico` — identificador do histórico
- `id_pessoa` — pessoa envolvida
- `id_empresa` — empresa envolvida
- `id_cargo` — cargo ocupado
- `data_inicio` — início do cargo
- `data_fim` — término do cargo

### 8. Tabela `empresa_funcionario`

```sql
CREATE TABLE empresa_funcionario (
    id_empresa BIGINT NOT NULL,
    id_pessoa BIGINT NOT NULL,
    PRIMARY KEY (id_empresa, id_pessoa),

    CONSTRAINT fk_empresa_funcionario_empresa
        FOREIGN KEY (id_empresa) REFERENCES empresa(id_empresa),

    CONSTRAINT fk_empresa_funcionario_pessoa
        FOREIGN KEY (id_pessoa) REFERENCES pessoa(id_pessoa)
);
```

Essa tabela funciona como uma tabela de relacionamento direto entre empresa e pessoa, útil para consultas rápidas.

## Relacionamentos Principais

- `empresa` 1:N `vinculo_emprego`
- `pessoa` 1:N `vinculo_emprego`
- `cargo` 1:N `vinculo_emprego`
- `empresa` 1:N `faturamento_anual`
- `empresa` 1:N `endereco` (por `tipo_entidade` + `id_entidade`)
- `pessoa` 1:N `endereco` (por `tipo_entidade` + `id_entidade`)
- `pessoa` 1:N `historico_cargo`
- `empresa` 1:N `historico_cargo`
- `cargo` 1:N `historico_cargo`

## Observações Sobre a Modelagem

- A estrutura acima é um rascunho conceitual para estudo e planejamento.
- O ideal é manter a separação de responsabilidades: pessoa, empresa, cargo, vínculo, endereço e faturamento.
- A tabela `endereco` usa `tipo_entidade` para permitir que o mesmo modelo sirva para pessoa e empresa.
- O sistema ainda pode evoluir para incluir outras regras, como:
  - validação de CPF/CNPJ
  - controle de datas e status
  - histórico de cargos e salários
  - cálculos de tempo de serviço
  - relatórios de faturamento por empresa

## Estrutura do Projeto

```text
projeto-estudo-spring/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/projeto/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       ├── config/
│   │   │       └── dto/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       └── java/
├── .gitignore
├── README.md
├── pom.xml
└── docker-compose.yml
```

## Requisitos

- Java 17
- Maven 3.9.6
- Banco H2
- IDE como IntelliJ IDEA
- Git

## Instalação

```powershell
# Clone o repositório
git clone https://github.com/olimpiogilberto/Spring.git

# Acesse a pasta do projeto
cd Spring

# Verifique a versão do Java
java -version

# Verifique as versões necessárias
java -version
mvn -v

# Compile, teste e rode com o Maven Wrapper (Windows)
.\mvnw.cmd clean verify
.\mvnw.cmd spring-boot:run
```

## Configuração

Por padrão, a aplicação usa H2 em memória. A configuração está em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:gestao;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Como o banco é em memória, os dados são descartados quando a aplicação é encerrada. `ddl-auto=update` é adequado ao estudo local; use migrações e uma configuração de banco apropriada antes de produção.

## Como Executar

```bash
.\mvnw.cmd spring-boot:run
```

A aplicação estará disponível em:

```text
http://localhost:8080
```

## Testes

```bash
.\mvnw.cmd test
```

## Roadmap de 12 Semanas

- [ ] Semana 1: Configuração do ambiente e primeiros conceitos de Java/Spring
- [ ] Semana 2: Estrutura do projeto e fundamentos de Spring Boot
- [ ] Semana 3: Modelagem de entidades e relacionamentos
- [ ] Semana 4: Repositórios e acesso ao banco de dados
- [ ] Semana 5: Serviços e regras de negócio
- [ ] Semana 6: Controllers e API REST
- [ ] Semana 7: Validações, DTOs e tratamento de erros
- [ ] Semana 8: Autenticação e segurança
- [ ] Semana 9: Testes unitários e de integração
- [ ] Semana 10: Documentação da API e melhorias de qualidade
- [ ] Semana 11: Refatoração e ajustes de arquitetura
- [ ] Semana 12: Finalização, validação e apresentação do projeto

## Critérios de Sucesso

- O projeto consegue ser executado sem erros.
- O backend tem estrutura organizada.
- As funcionalidades principais funcionam corretamente.
- O código é legível e bem organizado.
- O aprendizado do livro foi aplicado na prática.
- O projeto foi concluído mesmo com ritmo variável de estudo.

## Aprendizado Esperado

Este projeto busca consolidar conhecimentos em:

- Java
- Spring Boot
- Maven/Gradle
- Spring Data JPA
- Spring MVC
- Spring Security
- REST APIs
- Banco de dados
- Clean code e boas práticas
- Testes automatizados
- TDD
- Os padrões de projeto (ou design patterns)

## Licença

Este projeto será desenvolvido como material de estudo e prática pessoal. A licença pode ser definida posteriormente conforme a escolha do autor.

## Núcleo REST implementado

A implementação atual é a referência para os contratos abaixo. Usa Java 17, Spring Boot 3.5, Spring MVC, Spring Data JPA, Bean Validation e H2 em memória. Não requer credenciais: autenticação/autorização ainda não estão implementadas. Endereços opcionais têm relacionamento `OneToOne` com empresa ou pessoa; CPF e CNPJ são validados pelos dígitos verificadores e persistidos somente com dígitos. A modelagem SQL descrita anteriormente neste README é um rascunho conceitual, não um script executado pela aplicação.

### OpenAPI e Swagger UI

O Springdoc gera a especificação OpenAPI 3 a partir dos controllers, DTOs, validações e anotações:

```powershell
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
```

Com a aplicação em execução, acesse:

| Recurso | URL |
|---|---|
| Swagger UI interativa | [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) |
| Especificação OpenAPI JSON | [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) |
| Console H2 | [http://localhost:8080/h2-console](http://localhost:8080/h2-console) |

Use no console H2 JDBC URL `jdbc:h2:mem:gestao`, usuário `sa` e senha vazia.

### Contratos e endpoints REST

Todos os endpoints da aplicação usam o prefixo `/api`. Corpos e respostas usam JSON; datas usam `yyyy-MM-dd`. Os endpoints de listagem retornam arrays sem paginação nesta versão.

| Método | Endpoint | Descrição | Respostas de sucesso |
|---|---|---|---|
| `POST` | `/api/empresas` | Cadastra empresa | `201` + empresa |
| `GET` | `/api/empresas` | Lista empresas | `200` + array |
| `GET` | `/api/empresas/{id}` | Consulta por ID | `200` + empresa |
| `PUT` | `/api/empresas/{id}` | Substitui dados cadastrais | `200` + empresa |
| `DELETE` | `/api/empresas/{id}` | Exclui empresa | `204` |
| `PUT` | `/api/empresas/{id}/endereco` | Cria/substitui endereço | `200` + empresa |
| `DELETE` | `/api/empresas/{id}/endereco` | Remove endereço | `200` + empresa |
| `POST` | `/api/pessoas` | Cadastra pessoa | `201` + pessoa |
| `GET` | `/api/pessoas` | Lista pessoas | `200` + array |
| `GET` | `/api/pessoas/{id}` | Consulta por ID | `200` + pessoa |
| `PUT` | `/api/pessoas/{id}` | Substitui dados cadastrais | `200` + pessoa |
| `DELETE` | `/api/pessoas/{id}` | Exclui pessoa | `204` |
| `PUT` | `/api/pessoas/{id}/endereco` | Cria/substitui endereço | `200` + pessoa |
| `DELETE` | `/api/pessoas/{id}/endereco` | Remove endereço | `200` + pessoa |
| `POST` | `/api/vinculos` | Cadastra vínculo | `201` + vínculo |
| `GET` | `/api/vinculos` | Lista vínculos | `200` + array |
| `GET` | `/api/vinculos/{id}` | Consulta por ID | `200` + vínculo |
| `PUT` | `/api/vinculos/{id}` | Substitui vínculo | `200` + vínculo |
| `DELETE` | `/api/vinculos/{id}` | Exclui vínculo | `204` |
| `POST` | `/api/empresas/{cnpj}/faturamentos` | Cadastra faturamento anual | `201` + faturamento |
| `GET` | `/api/empresas/{cnpj}/faturamentos?ano={ano}` | Consulta faturamento anual | `200` + faturamento |
| `PUT` | `/api/empresas/{cnpj}/faturamentos/registro/{id}` | Atualiza faturamento pelo ID | `200` + faturamento |
| `DELETE` | `/api/empresas/{cnpj}/faturamentos/registro/{id}` | Exclui faturamento pelo ID | `204` |
| `GET` | `/api/consultas/empresa-por-cpf/{cpf}` | Empresa e vínculo ativo da pessoa | `200` + empresa/vínculo |
| `GET` | `/api/consultas/empregados/{cnpj}/contagem` | Conta vínculos ativos da empresa | `200` + contagem |
| `GET` | `/api/consultas/salario/{cpf}` | Salário do vínculo ativo | `200` + salário |
| `GET` | `/api/consultas/faturamento/{cnpj}?ano={ano}` | Consulta faturamento anual | `200` + faturamento |

### Exemplos de requisição

Cadastrar empresa (endereço opcional):

```json
{
  "cnpj": "11.222.333/0001-81",
  "razaoSocial": "Acme Ltda",
  "situacao": "ATIVA",
  "endereco": {
    "logradouro": "Rua Um",
    "numero": "123",
    "cidade": "São Paulo",
    "estado": "SP",
    "cep": "01000-000",
    "pais": "Brasil"
  }
}
```

Cadastrar pessoa:

```json
{
  "cpf": "529.982.247-25",
  "nome": "Ana Souza",
  "dataNascimento": "1990-01-02",
  "situacao": "ATIVA"
}
```

Cadastrar vínculo (IDs referem-se a cadastros existentes):

```json
{
  "pessoaId": 1,
  "empresaId": 1,
  "cargo": "EMPREGADO",
  "salario": 5250.50,
  "dataInicio": "2023-01-01",
  "dataFim": null,
  "status": "ATIVO"
}
```

Cadastrar faturamento:

```json
{
  "ano": 2025,
  "valor": 1200000.00
}
```

### Modelo, enums e regras de validação

- **Empresa:** `id` (gerado), `cnpj`, `razaoSocial`, `situacao` e `endereco` opcional. Situação: `ATIVA`, `INATIVA` ou `SUSPENSA`. CNPJ válido e único.
- **Pessoa:** `id` (gerado), `cpf`, `nome`, `dataNascimento`, `situacao` e `endereco` opcional. Situação: `ATIVA` ou `INATIVA`. CPF válido e único; nascimento deve ser uma data passada.
- **Endereço:** `logradouro`, `numero`, `complemento`, `bairro`, `cidade`, `estado`, `cep` e `pais`. Logradouro/cidade/UF obrigatórios; UF deve ter duas letras; CEP, quando informado, aceita `00000-000` ou `00000000`. O país omitido é salvo como `Brasil`.
- **Vínculo:** `id` (gerado), `pessoaId`, `empresaId`, `cargo`, `salario`, `dataInicio`, `dataFim` e `status`. Cargos: `PRESIDENTE`, `DIRETOR`, `RESPONSAVEL_LEGAL`, `PROCURADOR`, `CONSULTOR`, `ASSISTENTE`, `EMPREGADO`, `SUPERVISOR`. Status: `ATIVO` ou `ENCERRADO`. Salário não pode ser negativo; pessoa e empresa devem existir; cada pessoa pode ter somente um vínculo ativo. Data inicial não pode ser futura, data final não pode ser futura nem anterior à inicial; vínculo ativo não aceita data final e vínculo encerrado exige data final.
- **Faturamento anual:** `id` (gerado), `ano` e `valor`. Ano entre 1900 e 9999; valor não negativo; somente um registro por empresa/ano.
- Valores monetários são transmitidos como números decimais. Campos `id` são gerados pela API; não são necessários no cadastro.
- CPF/CNPJ podem conter máscara no envio; a resposta apresenta somente dígitos.
- Endereços são relacionamentos opcionais individuais com empresa ou pessoa, com integridade referencial. Não existe cadastro de histórico de cargo/salário separado nesta versão.

### Erros HTTP

Erros usam JSON com `timestamp`, `status`, `error` e `message`. Exemplo:

```json
{
  "timestamp": "2026-10-14T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "cpf: CPF inválido"
}
```

- `400 Bad Request`: campos inválidos, enum/deserialização inválida ou regra de datas.
- `404 Not Found`: ID, empresa ou pessoa não encontrada, ou consulta sem registro correspondente.
- `409 Conflict`: CPF/CNPJ duplicado, faturamento repetido para empresa/ano, vínculo ativo duplicado ou exclusão que viole relacionamentos existentes.

### Escopo atual e próximos passos

A implementação oferece CRUD, consultas, validação e documentação Swagger para as entidades listadas. Ainda não há autenticação/autorização, paginação, histórico de cargos, cálculo de tempo no cargo, migrações versionadas do banco, perfil de produção ou deploy configurado. O Spring Batch permanece desativado e não faz parte do fluxo atual.

Os testes de integração podem ser executados com `.\mvnw.cmd test`; eles usam H2 e validam os fluxos da API e a geração da especificação OpenAPI.
