# Техническое задание на разработку системы "Financial Ledger Management"

## Описание проекта

Система "Financial Ledger Management" предназначена для автоматизации учета финансовых потоков компании. Она позволяет управлять счетами, учитывать доходы и расходы, автоматизировать процессы планирования и интеграции с внешними API для получения данных.

---

## Цели проекта

1. Автоматизация учета доходов и расходов.
2. Упрощение управления финансовыми счетами и транзакциями.
3. Оптимизация процессов, связанных с планированием финансов.
4. Интеграция с внешними API для автоматической синхронизации данных.

---

## Основные функции системы

### 1. Управление счетами
- Создание, редактирование и удаление счетов.
- Отображение текущего баланса счета.

### 2. Управление транзакциями
- Добавление доходов и расходов.
- Ручное распределение по категориям.
- Автоматическая категоризация транзакций на основе ключевых слов.
- Поддержка повторяющихся транзакций (ежемесячные, еженедельные).

### 3. Уведомления
- Email-уведомления о низком балансе.
- Уведомления о предстоящих повторяющихся платежах.

### 4. Финансовые операции
- Переводы между счетами.
- Учёт долгов и погашений.

### 5. Интеграция с внешними API
- Получение данных о банковских транзакциях.
- Синхронизация данных о состоянии счетов.

### 6. Логирование
- Логирование операций пользователей.
- Хранение истории изменений транзакций и счетов.

---

## Требования к системе

### Функциональные требования
1. Система должна предоставлять REST API для управления всеми сущностями.
2. Пользователь должен иметь возможность авторизоваться через JWT.
3. Все операции должны быть логированы для возможности аудита.
4. Система должна поддерживать повторяющиеся задачи для автоматизации транзакций.
5. Поддержка интеграции с внешними банковскими API через OpenBank.

### Нефункциональные требования
1. **Производительность:** 
   - Время обработки запроса не должно превышать 300 мс при стандартной нагрузке (до 100 запросов/сек).
2. **Надёжность:**
   - Система должна корректно восстанавливать состояние после сбоев.
3. **Масштабируемость:**
   - Возможность горизонтального масштабирования через Docker и Kubernetes.
4. **Документированность:**
   - Использовать Swagger для документации API.

---

## Архитектура системы

### 1. Компоненты системы
- **База данных:** PostgreSQL
- **Сервер приложений:** Spring Boot
- **Безопасность:** Spring Security с JWT
- **Очереди задач:** `@Scheduled` (в будущем можно добавить RabbitMQ или Kafka для сложных сценариев)
- **Интеграция:** REST API с внешними банковскими сервисами
- **Контейнеризация:** Docker для упаковки приложения

### 2. Основные сущности

#### Таблица `Account` (Счета)
- `id` (UUID) — уникальный идентификатор.
- `name` (String) — название счета.
- `balance` (Decimal) — текущий баланс.
- `currency` (String) — валюта счета.

#### Таблица `Transaction` (Транзакции)
- `id` (UUID) — уникальный идентификатор.
- `account_id` (UUID) — ссылка на счет.
- `type` (Enum) — тип транзакции: доход/расход.
- `amount` (Decimal) — сумма.
- `category_id` (UUID) — категория транзакции.
- `description` (String) — описание.
- `date` (Timestamp) — дата транзакции.

#### Таблица `Category` (Категории)
- `id` (UUID) — уникальный идентификатор.
- `name` (String) — название категории.

#### Таблица `ScheduledTransaction` (Запланированные транзакции)
- `id` (UUID) — уникальный идентификатор.
- `account_id` (UUID) — ссылка на счет.
- `type` (Enum) — тип транзакции: доход/расход.
- `amount` (Decimal) — сумма.
- `category_id` (UUID) — категория транзакции.
- `description` (String) — описание.
- `next_run_date` (Timestamp) — дата следующего выполнения.
- `interval` (Enum) — интервал: ежедневно, еженедельно, ежемесячно.

---

## Стек технологий

### Backend
- **Язык:** Java 17
- **Фреймворк:** Spring Boot (Spring Data JPA, Spring Security, Spring Web, Spring Mail)
- **Библиотеки:** MapStruct, Lombok, Hibernate Validator
- **База данных:** PostgreSQL
- **Миграции:** Liquibase
- **Асинхронность:** `@Scheduled`, CompletableFuture
- **Логирование:** Logback + SLF4J

### API
- **Формат:** RESTful API
- **Документация:** Swagger/OpenAPI 3

### Интеграции
- **API:** OpenBank API или аналоги для синхронизации данных

### DevOps
- **Контейнеризация:** Docker
- **CI/CD:** GitHub Actions или Jenkins
- **Мониторинг:** Spring Boot Actuator (возможно, Prometheus/Grafana в будущем)

### Тестирование
- **Юнит-тесты:** JUnit 5
- **Мокирование:** Mockito
- **Интеграционные тесты:** Spring Boot Test

---

## API-эндпоинты

### 1. Авторизация
- `POST /auth/login`: Авторизация пользователя, возвращает JWT токен.
- `POST /auth/register`: Регистрация нового пользователя.

### 2. Управление счетами
- `GET /accounts`: Получить список всех счетов.
- `POST /accounts`: Создать новый счет.
- `PUT /accounts/{id}`: Обновить информацию о счете.
- `DELETE /accounts/{id}`: Удалить счёт.

### 3. Управление транзакциями
- `GET /transactions`: Получить список транзакций.
- `POST /transactions`: Создать новую транзакцию.
- `PUT /transactions/{id}`: Обновить информацию о транзакции.
- `DELETE /transactions/{id}`: Удалить транзакцию.

### 4. Управление категориями
- `GET /categories`: Получить список всех категорий.
- `POST /categories`: Создать новую категорию.
- `PUT /categories/{id}`: Обновить категорию.
- `DELETE /categories/{id}`: Удалить категорию.

### 5. Запланированные транзакции
- `GET /scheduled-transactions`: Получить список запланированных транзакций.
- `POST /scheduled-transactions`: Создать новую запланированную транзакцию.
- `PUT /scheduled-transactions/{id}`: Обновить запланированную транзакцию.
- `DELETE /scheduled-transactions/{id}`: Удалить запланированную транзакцию.

---

## Этапы реализации

1. **Неделя 1:** Разработка каркаса приложения, настройка базы данных и API для работы со счетами.
2. **Неделя 2:** Добавление функционала транзакций, автоматизации через `@Scheduled`.
3. **Неделя 3:** Интеграция с внешними API, добавление автоматической синхронизации данных.
4. **Неделя 4:** Тестирование, деплой в Docker, настройка CI/CD.

---

## Финальный результат

Готовая система для учета финансовых операций, управления счетами и автоматизации транзакций с возможностью интеграции с внешними API.

