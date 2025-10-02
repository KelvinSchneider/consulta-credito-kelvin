[TYPESCRIPT__BADGE]: https://img.shields.io/badge/typescript-D4FAFF?style=for-the-badge&logo=typescript
[ANGULAR__BADGE]: https://img.shields.io/badge/Angular-red?style=for-the-badge&logo=angular
[JAVA]: https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white
[SPRINGBOOT]: https://img.shields.io/badge/spring%20boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[DOCKER]: https://img.shields.io/badge/docker-blue?style=for-the-badge&logo=docker&logoColor=white
[KAFKA]: https://img.shields.io/badge/kafka-gray?style=for-the-badge&logo=kafka&logoColor=white

<h1 align="center" style="font-weight: bold;">DESENVOLVIMENTO DE API DE CONSULTA DE CRÉDITOS 💻</h1>

![angular][ANGULAR__BADGE]
![typescript][TYPESCRIPT__BADGE]
![Java][JAVA]
![Spring Boot][SPRINGBOOT]
![docker][DOCKER]
![kafka][KAFKA]

<h2 id="started">📌 Sobre</h2>
Este projeto demonstra uma arquitetura de microsserviços completa, containerizada com Docker e Docker Compose. A solução é composta por um Frontend em Angular, um Backend em Spring Boot (Java 21), e um sistema de mensageria Apache Kafka/Zookeeper.

<h2 id="started"> 🛠️ Tecnologias Utilizadas </h2>
<p>- Frontend: Angular (Containerizado com Nginx)</p>
<p>- Backend: Java 21 (Spring Boot / Maven)</p>
<p>- Mensageria: Apache Kafka e Zookeeper (Bitnami Images)</p>
<p>- Orquestração: Docker e Docker Compose (v3.8)</p>
<p>- Banco de Dados: PostgreSQL 16 (Provisionado Localmente)</p>

<h2 id="pre"> ☁️ Pré-requisitos</h2>
<p>Para rodar o projeto, você deve ter os seguintes softwares instalados e configurados em sua máquina:</p>

<p>Docker Desktop: Necessário para construir e executar os containers.</p>
<p>Docker Compose: Usado para orquestrar todos os serviços de uma vez.</p>
<p>PostgreSQL Local: Uma instância de PostgreSQL rodando em sua máquina (na porta 5432) para servir como banco de dados principal.</p>
<p>Java/Maven (Opcional, para desenvolvimento): Para editar e rodar a API fora do Docker.</p>

<h2 id="pre">⚙️ Configuração do Banco de Dados</h2>
<p>O banco de dados é provisionado localmente.</p>
<p>Crie um banco de dados em sua instância local de PostgreSQL com as seguintes credenciais (conforme configurado no docker-compose.yml):</p>

<p>Nome do DB: db_credito</p>
<p>Usuário: db_credito</p>
<p>Senha: db_credito</p>

<p>Popule o DB: Utilize os scripts INSERT para popular o banco de dados db_credito com dados de teste.</p>

```bash
CREATE TABLE credito (
    id BIGSERIAL PRIMARY KEY,
    numero_credito VARCHAR(50) NOT NULL,
    numero_nfse VARCHAR(50) NOT NULL,
    data_constituicao DATE NOT NULL,
    valor_issqn NUMERIC(15, 2) NOT NULL,
    tipo_credito VARCHAR(50) NOT NULL,
    simples_nacional BOOLEAN NOT NULL,
    aliquota NUMERIC(5, 2) NOT NULL,
    valor_faturado NUMERIC(15, 2) NOT NULL,
    valor_deducao NUMERIC(15, 2) NOT NULL,
    base_calculo NUMERIC(15, 2) NOT NULL
);

-- Índices para otimizar buscas
CREATE INDEX idx_credito_numero_nfse ON credito (numero_nfse);
CREATE INDEX idx_credito_numero_credito ON credito (numero_credito);


---

INSERT INTO credito (numero_credito, numero_nfse, data_constituicao, valor_issqn, tipo_credito, simples_nacional, aliquota, valor_faturado, valor_deducao, base_calculo)
VALUES
('123456', '7891011', '2024-02-25', 1500.75, 'ISSQN', true, 5.0, 30000.00, 5000.00, 25000.00),
('789012', '7891011', '2024-02-26', 1200.50, 'ISSQN', false, 4.5, 25000.00, 4000.00, 21000.00),
('654321', '1122334', '2024-01-15', 800.50, 'Outros', true, 3.5, 20000.00, 3000.00, 17000.00);
```


<h2 id="start">▶️ Como Rodar o Projeto</h2>
<p>Siga os passos abaixo na pasta raiz do projeto (consulta-credito-kelvin) para subir toda a aplicação.</p>

<p>1. Iniciar os Containers</p>
<p>Este comando irá construir as imagens do Frontend e do Backend a partir dos respectivos Dockerfiles, fazer o pull do Kafka e Zookeeper, e iniciar todos os serviços:</p>

```bash
docker-compose up --build
```

<p>2. Acessar a Aplicação</p>
<p>Aguarde até que os logs parem de rolar (indicando que o Spring Boot e o Nginx estão "UP").</p>

<p>Frontend (Angular):</p>

<p>Acesse o endereço no seu navegador: http://localhost:4200</p>

<h2 id="routes">📍 API's do projeto:</h2>

| api               | descrição                                          
|----------------------|-----------------------------------------------------
| <kbd>/api/creditos</kbd>     | Retorna todos os créditos
| <kbd>/api/creditos/{numeroNfse}</kbd>     | Retorna uma lista de créditos constituídos com base no número da NFS-e
| <kbd>/api/creditos/credito/{numeroCredito}</kbd>     | Retorna os detalhes de um crédito constituído específico com base no número do crédito constituído.

<h2 id="arq">🌐 Arquitetura e Comunicação</h2>
<p>Serviço	Porta Exposta	Comunicação Interna	Função</p>
<p>Frontend	4200	Fala com backend:8080 (via Nginx Proxy)	Interface do usuário em Angular.</p>
<p>Backend	8080	Fala com DB Local (host.docker.internal:5432) e com kafka:29092	API de consulta de crédito (Spring Boot).</p>
<p>Kafka	9092	Fala com zookeeper:2181	Gerencia a fila de mensagens (produtor/consumidor).</p>
