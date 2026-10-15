# Projeto de Estudo e Prática com Spring Boot

## Descrição

Este projeto nasceu com a intenção de unir teoria e prática em um processo de aprendizado do Spring Boot. A ideia central é estudar o conteúdo do projeto, num ritmo de aprendizado de cada semana, e ao mesmo tempo transformar os conhecimentos em um projeto real desenvolvido do início ao fim com Spring Boot.

O objetivo principal não é atingir um resultado perfeito desde o início, mas manter a consistência. Em semanas em que o estudo for mais intenso, o projeto evolui mais rapidamente; em semanas em que houver menos tempo, ele continua progredindo em etapas menores, sem perder o foco. A meta é construir uma base sólida de conhecimento e, ao final, entregar uma aplicação funcional que represente a aplicação prática dos conceitos aprendidos.

## Descrição Completa do Projeto

Este projeto tem como objetivo principal servir como ambiente de aprendizado prático e realista para consolidar os conhecimentos adquiridos em Java 17, Maven 3.9.6 e Spring Boot, usando como base um problema de negócio concreto: o cadastro e a gestão de empresas e trabalhadores.

A aplicação será uma solução backend para registrar empresas, pessoas, cargos, vínculos empregatícios, salários, endereços e faturamento anual. O foco é construir uma base sólida de desenvolvimento com regras de negócio claras, persistência em banco de dados, camada de serviço, controle de acesso a dados e organização de código.

A ideia do sistema é permitir que o usuário cadastre uma empresa, registre seus dados cadastrais, identifique sua situação atual e associe pessoas a ela em diferentes papéis. Também será possível cadastrar uma pessoa, informar seu CPF, data de nascimento, status e, quando ela for um empregado, registrar salário, cargo, data de início do vínculo e tempo de atuação naquela função. O sistema também deve permitir relacionar esse empregado à empresa e identificar qual cargo ocupa e qual empresa faz parte.

Além disso, a aplicação deve cuidar de dados complementares importantes, como endereço da pessoa e endereço da empresa. A modelagem será pensada para que a mesma tabela de endereço possa servir tanto para pessoa quanto para empresa, diferenciando o tipo da entidade e o identificador da mesma. Também será possível registrar faturamento anual por empresa, com a finalidade de avaliar o desempenho do negócio em um período específico.

Em termos práticos, a aplicação será capaz de:

- cadastrar uma empresa com CNPJ, razão social e situação atual;
- cadastrar uma pessoa com CPF, nome, data de nascimento e status;
- associar uma pessoa a uma empresa em um vínculo empregatício;
- registrar cargo, salário e tempo de atuação;
- consultar qual empresa uma pessoa trabalha;
- consultar quantos empregados uma empresa possui;
- consultar o salário de um empregado por CPF;
- consultar o faturamento anual de uma empresa por CNPJ;
- manter a consistência dos dados por meio de relações entre tabelas;
- trabalhar com dados locais em H2, em uma primeira versão de desenvolvimento.

O projeto será desenvolvido como um estudo guiado, com foco em aprender e praticar os principais conceitos de desenvolvimento backend com Java e Spring Boot. Em outras palavras, ele terá valor didático e técnico ao mesmo tempo: além de servir como base de aprendizagem, também será uma aplicação funcional com regras relevantes de negócio e estrutura organizada para evolução futura.

## Artefatos esperados do projeto

Ao longo do desenvolvimento, espera-se que o projeto tenha os seguintes artefatos:

- Repositório GitHub para versionamento do projeto;
- README com a descrição do sistema, objetivos, regras e modelagem inicial;
- Estrutura Maven para gerenciamento das dependências do projeto;
- Arquivo de configuração do Spring Boot com banco H2;
- Estrutura de pacotes organizada em camadas, como model, repository, service, controller e dto;
- Entidades JPA para representar empresa, pessoa, cargo, vínculo, endereço e faturamento;
- Repositórios para acesso ao banco de dados;
- Serviços para encapsular as regras de negócio;
- Controladores REST para expor endpoints da aplicação;
- DTOs para transportar dados entre camadas;
- Scripts SQL ou modelagem inicial para a estrutura do banco H2;
- Testes unitários e de integração para validar regras e endpoints;
- Documentação da API, com exemplos de uso e contratos de entrada e saída;
- Versionamento e commits organizados ao longo do processo de aprendizagem.

A expectativa final é que o projeto seja um backend funcional, bem organizado, com regras de negócio claras e com potencial de crescimento. Ele servirá como marco de aprendizado para a construção de sistemas reais, preservando o objetivo principal do projeto: aprender fazendo, de forma prática, consistente e progressiva.

## Visão Geral

- Objetivo principal: aprender de forma prática e consistente usando Spring Boot como base de desenvolvimento.
- Público-alvo: estudantes de programação e iniciantes que desejam aprender Java/Spring em um fluxo realista.
- Diferencial: combinação de estudo teórico, prática gradual e desenvolvimento de um projeto completo.
- Status do projeto: Em desenvolvimento

## Ideia Conceitual do Projeto

O projeto será uma aplicação backend com Spring Boot que pode evoluir ao longo das 12 semanas, cobrindo gradualmente os principais conceitos do livro e da tecnologia.

A aplicação pode ser pensada como um sistema de gestão para um cenário real, por exemplo:

- Cadastro de usuários
- Login e autenticação
- Gestão de produtos, tarefas, pedidos ou clientes
- Persistência em banco de dados
- Validações de regras de negócio
- API REST para comunicação com o frontend ou outros serviços
- Testes automatizados
- Documentação da API

Essa estrutura permite praticar desde os conceitos mais básicos até temas mais avançados como segurança, persistência, testes, arquitetura e boas práticas.

## Objetivos do Projeto

- Estudar e absorver os conceitos do livro de forma contínua.
- Aplicar os conteúdos em um projeto funcional do zero.
- Evoluir em etapas ao longo das 12 semanas.
- Desenvolver um projeto completo usando Spring Boot.
- Aprender a resolver problemas reais de desenvolvimento com lógica, organização e boas práticas.
- Construir disciplina de estudo e consistência.

## Funcionalidades Planejadas e Status

- [x] Configuração inicial do projeto Spring Boot e estrutura em camadas
- [x] Cadastro e consulta de empresas por identificador
- [x] Cadastro e consulta de pessoas por identificador
- [x] Cadastro e gestão de vínculo, cargo, salário e datas
- [x] Cadastro e gestão de endereço para pessoa e empresa
- [x] Consulta da empresa e do salário pelo CPF
- [x] Contagem de vínculos ativos por empresa
- [x] Cadastro e consulta de faturamento anual
- [x] Persistência local com H2
- [x] Validações de dados e regras de negócio principais
- [x] API REST e tratamento padronizado de erros
- [x] Testes de integração da API e da especificação OpenAPI
- [x] Documentação interativa Swagger/OpenAPI
- [ ] Listagem detalhada de pessoas/vínculos por empresa e paginação
- [ ] Histórico de cargos e cálculo do tempo no cargo
- [ ] Autenticação/autorização
- [ ] Migrações versionadas e configuração de produção
- [ ] Deploy em ambiente simples

## Regras do Sistema

O sistema deve permitir o cadastro completo de empresas e membros, mantendo a relação entre eles e permitindo consultas por diferentes critérios.

### Cadastro de empresa

- Deve ser possível cadastrar uma empresa informando:
  - CNPJ
  - razão social
  - situação atual da empresa (ativa ou inativa)

### Cadastro de membro

- Deve ser possível cadastrar uma pessoa informando:
  - nome
  - CPF
  - data de nascimento
  - situação do empregado na empresa
- Quando a pessoa for cadastrada como empregado, ela pode ter:
  - salário
  - cargo
  - tempo de exercício no cargo
  - vínculo com uma empresa

### Vínculo empregado-empresa

- Um membro pode estar relacionado a uma empresa como empregado, diretor ou outro cargo definido.
- A partir desse vínculo, o sistema deve permitir obter:
  - o cargo ocupado
  - o salário recebido
  - há quanto tempo a pessoa exerce aquele cargo

### Endereço

- A pessoa pode ter um endereço.
- A empresa também pode ter um endereço.
- A tabela de endereços deve registrar o tipo do endereço para identificar se pertence a uma pessoa ou a uma empresa.

### Consultas principais

- Informar o CPF de um empregado e descobrir a empresa em que ele trabalha.
- Informar o CNPJ de uma empresa e descobrir quantos empregados estão cadastrados nela.
- Informar o CPF de um empregado e descobrir qual é o seu salário.
- Informar o CNPJ da empresa e descobrir seu faturamento anual.

## Tecnologias

- Linguagem: Java 17
- Framework: Spring Boot
- Banco de dados: H2 Database
- Autenticação: ainda não implementada (evolução futura)
- API: REST
- Testes: JUnit, Mockito
- Documentação: Swagger/OpenAPI
- Ferramentas: Maven, Git, GitHub

## Estrutura do Banco de Dados H2 (rascunho inicial)

Abaixo está a proposta inicial da modelagem do banco em H2 para o sistema de cadastro de empresas, pessoas, vínculos empregatícios, salários e endereços. Esta estrutura ainda está em fase de idealização, então pode sofrer ajustes conforme o projeto evoluir.

### 1. Tabela `empresa`

```sql
CREATE TABLE empresa (
    id_empresa BIGINT PRIMARY KEY AUTO_INCREMENT,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    razao_social VARCHAR(150) NOT NULL,
    situacao VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

Campos:

- `id_empresa` — identificador da empresa
- `cnpj` — CNPJ da empresa
- `razao_social` — nome da empresa
- `situacao` — situação cadastral, como `ATIVA`, `INATIVA`, `SUSPENSA`
- `data_cadastro` — data em que a empresa foi cadastrada

### 2. Tabela `pessoa`

```sql
CREATE TABLE pessoa (
    id_pessoa BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_nascimento DATE,
    situacao VARCHAR(20) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

Campos:

- `id_pessoa` — identificador da pessoa
- `nome` — nome completo
- `cpf` — CPF da pessoa
- `data_nascimento` — data de nascimento
- `situacao` — situação da pessoa no sistema ou no vínculo com empresa
- `data_cadastro` — data do cadastro

### 3. Tabela `cargo`

```sql
CREATE TABLE cargo (
    id_cargo BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao VARCHAR(255)
);
```

Campos:

- `id_cargo` — identificador do cargo
- `nome` — nome do cargo, por exemplo: `EMPREGADO`, `DIRETOR`, `SUPERVISOR`
- `descricao` — descrição do cargo

### 4. Tabela `vinculo_emprego`

```sql
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
