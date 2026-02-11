# API Automation with RestAssured

![Java](https://img.shields.io/badge/Java-17-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![JUnit5](https://img.shields.io/badge/JUnit-5-green)
![CI](https://github.com/Edsong030/Automacao-em-java-restassured/actions/workflows/ci.yml/badge.svg)

---

## Tecnologias utilizadas

* Java 17
* Maven
* RestAssured
* JUnit 5
* Allure Report
* GitHub Actions (CI)

---

## Arquitetura do projeto

O projeto segue o padrão em camadas para facilitar manutenção e escalabilidade dos testes.

```
src/test/java
├── config        # Configurações base dos testes
├── model         # Objetos de requisição e payloads
├── service       # Camada de comunicação com a API
└── tests         # Casos de teste
```

---

## Cenários automatizados

### Usuários

* Criar usuário com sucesso
* Validar erro ao criar usuário duplicado
* Buscar usuário por ID
* Excluir usuário

---

## Como executar o projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/Edsong030/Automacao-em-java-restassured.git
```

### 2. Acessar a pasta do projeto

```bash
cd Automacao-em-java-restassured
```

### 3. Executar os testes

```bash
mvn clean test
```

---

## Relatório de testes com Allure

### Gerar relatório

```bash
allure serve target/allure-results
```

O Allure abrirá automaticamente o relatório no navegador.

---

## Integração contínua (CI)

O projeto possui pipeline no **GitHub Actions** que executa os testes automaticamente a cada push ou pull request.

---

## Boas práticas aplicadas

* Separação por camadas (config, model, service, tests)
* Reutilização de código
* Testes independentes
* Relatórios automatizados
* Execução em CI

---

## Autor

**Edson Gomes**
QA Automation Engineer

LinkedIn:
[https://www.linkedin.com/in/edson-gomes-494398208](https://www.linkedin.com/in/edson-gomes-494398208)
