# Query Constructor

## What is it about?

The main task of the project is to form SELECT queries to connected databases based on selected parameters.
Postgres and MySQL are currently supported. Also, the application implies small additional functions.

## How to launch

1. download the project
2. perform docker command to turn on database:
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
#### more in the swagger file

## Database structure

![Entity Relation Diagram](/src/main/resources/files/images/ERD.png)

## database docker command

#### docker run --name pg-db -p 5433:"port" -e POSTGRES_PASSWORD=pgpass -d -v "path":/docker-entrypoint-initdb.d postgres