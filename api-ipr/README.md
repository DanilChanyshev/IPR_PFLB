# API Tests

Автоматизированные API-тесты для Swagger PetStore.

## Стек технологий

- Java 21
- Maven
- JUnit 5
- Rest Assured
- Google Guice
- Jackson
- AssertJ
- JSON Schema Validator
- Allure Report

---

## Архитектура

Тесты построены по принципу Service Layer.

Каждый API раздел представлен сервисом:

- PetRestService
- UserRestService
- StoreRestService

Сервисы инкапсулируют работу с REST-запросами и возвращают объекты Response
---

## Тестовые сценарии

### Pet

| Тест | Описание |
|--------|--------|
| PetLifeCycleTest | Полный жизненный цикл питомца |
| NotValidCreatingPetTest | Создание питомца с невалидными данными |
| CreatingDuplicatePetTest | Создание дубликата питомца |
| DeletedPetTest | Удаление питомца |
| UpdateStatusPetInStoreTest | Изменение статуса питомца |

### User

| Тест | Описание |
|--------|--------|
| CreateListUsersTest | Создание списка пользователей |
| AuthUserTest | Авторизация пользователя |
| AuthWithNotValidTest | Авторизация с невалидными данными |

---

## Запуск тестов

### Запуск всех тестов:

```bash
mvn -fae clean test // mvn -fae clean test // mvn -fae -DtestEmail=yourAcc DpasswordEmail=yourPass clean test
```

### Тесты только этого модуля

```bash
mvn -pl api-ipr test
```

### Запуск конкретного класса:

```bash
mvn -Dtest=PetLifeCycleTest test
```

---

## Проверка JSON Schema

Для проверки контрактов используется JSON Schema Validator.

Пример:

```java
.body(matchesJsonSchemaInClasspath(
        "schema/pet/CreatePetSchema.json"));
```

---

## Отчеты Allure

### Allure отчет данного модуля:

```bash
allure serve api-ipr/target/allure-results
```

## Параллельный запуск

В проекте используется JUnit 5 Parallel Execution.

### Настройки:

```properties
junit.jupiter.execution.parallel.enabled = true
junit.jupiter.execution.parallel.mode.default = concurrent
junit.jupiter.execution.parallel.mode.classes.default = concurrent
junit.jupiter.execution.parallel.config.strategy = fixed
junit.jupiter.execution.parallel.config.fixed.parallelism = 4
```

## Примечание

Для корректной работы со Swagger Pet-store - рекомендуется включить VPN.
