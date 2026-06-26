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

Запуск всех тестов:

```bash
mvn clean test
```

Запуск конкретного класса:

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

Генерация результатов:

```bash
mvn clean test
```

Построение отчета:

```bash
allure serve target/allure-results
```

## Параллельный запуск

В проекте используется JUnit 5 Parallel Execution.

Настройки:

```properties
junit.jupiter.execution.parallel.enabled = true
junit.jupiter.execution.parallel.mode.default = concurrent
junit.jupiter.execution.parallel.mode.classes.default = concurrent
junit.jupiter.execution.parallel.config.strategy = fixed
junit.jupiter.execution.parallel.config.fixed.parallelism = 4
```

## Примечание

Для корректной работы со Swagger Pet-store - рекомендуется включить VPN.
