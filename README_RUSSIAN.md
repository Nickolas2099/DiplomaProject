# Конструктор запросов

## Разработчик
Антоненко Николай

## О чем проект

Основной задачей проекта является формирование select запросов к подключаемым базам данных на основе выбранных параметров.
В настоящее время поддерживаются базы Postgres и MySQL. Также в приложении реализованы небольшие дополнительные функции, 
например сохранение истории запросов.

## Ближайшие нововведения
### Конструктор запросов
- Доделать группировки
- Доделать JOIN таблиц
### Роли
У пользователей уже есть роли, однако необходимо ограничить доступ к некоторым страницам
для обычных user

## Как запустить

1. скачать содержимое репозитория
2. выполнить docker команду для запуска базы данных:
    - docker run --name pg-db -p <port>:<port> -e POSTGRES_PASSWORD=pgpass -d -v "path to init script":/docker-entrypoint-initdb.d postgres

## Api

| address                             | request | action                                  | 
|-------------------------------------|---------|-----------------------------------------|
| /api/v1/auth/register               | post    | registration (return jwt)               |
| /api/v1/auth/authenticate           | post    | authorization (return jwt)              |
| ---                                 | ---     | ---                                     |
| /api/v1/users                       | get     | get all users                           |
| /api/v1/users/{id}                  | get     | get user by id                          |
| /api/v1/users                       | post    | add user                                |
| /api/v1/users                       | patch   | update user                             |
| /api/v1/users/{id}                  | delete  | delete user                             |
| ---                                 | ---     | ---                                     |
| /api/v1/connectedDbs                | get     | get all DBs                             |
| /api/v1/connectedDbs/{id}           | get     | get DB by id                            |
| /api/v1/connectedDbs                | post    | add DB                                  |
| /api/v1/connectedDbs                | patch   | update DB                               |
| /api/v1/connectedDbs/{id}           | delete  | delete DB                               |
| /api/v1/connectedDbs/testConnection | post    | try to open session with DB             |
| /api/v1/connectedDbs/db             | get     | get all data from DB (deprecated)       |
| /api/v1/connectedDbs/table          | get     | return structure of transferred DB      |
| /api/v1/connectedDbs/select         | post    | return selection based on parameters    |
| ---                                 | ---     | ---                                     |
| /api/v1/action/recent               | get     | get recent queries                      |
| ---                                 | ---     | ---                                     |
| /api/v1/export/excelFile            | post    | return excel file based on query result |

## Структура базы данных

![Entity Relation Diagram](/src/main/resources/files/images/ERD.png)
