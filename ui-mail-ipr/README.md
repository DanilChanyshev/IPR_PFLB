# UI Automation Framework

Автоматизированные UI-тесты для Mail.ru.

## Технологии

- Java 21
- Selenium WebDriver
- JUnit 5
- Google Guice
- Allure Report
- Maven
- AssertJ

## Запуск тестов

### Все тесты

```bash
mvn -DtestEmail=mylogin -DpasswordEmail=myPassword clean test
```

### Тесты только этого модуля

```bash
mvn -DtestEmail=mylogin -DpasswordEmail=myPassword -pl ui-mail-ipr test
```

### Конкретный тест

```bash
mvn -Dtest=SentEmailTest -DtestEmail=mylogin -DpasswordEmail=myPassword test
```

## Генерация Allure отчета

```bash
allure serve target/allure-results
```

## Конфигурация

Параметры браузера находятся в:

```text
factory/settings
```

Пример:

```java
ChromeSettings
```

### Pages

Содержат Page Object классы.

Пример:

```java
AuthPage,
InboxPage,
MailPage
        ...
```

### Components

Переиспользуемые UI-компоненты.

### Modules

Guice-модули для внедрения зависимостей.

### Extension

JUnit расширения:

```java
UiExtension
```

Отвечает за:
- создание WebDriver
- внедрение зависимостей через Guice
- закрытие браузера

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