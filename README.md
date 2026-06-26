# Automation Framework

Автоматизированные UI-тесты для Mail.ru.
Автоматизация Api-тестов pet store

## Технологии

- Java 21
- Selenium WebDriver
- JUnit 5
- Google Guice
- Allure Report
- Maven
- AssertJ
- Rest Assured
- Jackson
- JSON Schema Validator

## Запуск тестов

### Перед запуском тестов

Для запуска тестов API требуется включение VPN сервисов

Для запуска тестов UI рекомендуется не пользоваться VPN сервисами

#### Важно

```
Внесите данные аккаунта в maven.config
```


### Все тесты

```bash
mvn clean test
```

### Тесты только модуля UI

```bash
mvn -pl ui-mail-ipr test
```

### Тесты только модуля API

```bash
mvn -pl api-ipr test
```

### Конкретный тест

```bash
mvn -Dtest=SentEmailTest test
```

## Генерация Allure отчета

### Общий allure отчет

```bash
allure serve ui-mail-ipr/target/allure-results api-ipr/target/allure-results
```

### Allure отчет для UI

```bash
allure serve ui-mail-ipr/target/allure-results
```

### Allure отчет для API

```bash
allure serve api-ipr/target/allure-results
```

## Параллельный запуск

В проекте используется JUnit 5 Parallel Execution.

Настройки:

```properties
junit.jupiter.execution.parallel.enabled = true
junit.jupiter.execution.parallel.mode.default = concurrent
junit.jupiter.execution.parallel.mode.classes.default = same_thread
junit.jupiter.execution.parallel.config.strategy = fixed
junit.jupiter.execution.parallel.config.fixed.parallelism = 4
```