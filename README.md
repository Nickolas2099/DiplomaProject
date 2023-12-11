# DIPLOMA PROJECT

## api

| address                   | request | action      |
|---------------------------|---------|-------------|
| /api/v1/users             | get     | getAllUsers |
| /api/v1/users/{id}        | get     | getUserById |
| /api/v1/users/add         | post    | addUser     |
| /api/v1/users/update      | patch   | updateUser  |
| /api/v1/users/{id}/delete | delete  |  deleteUser |

## database relations

### user <-manyToOne-> user_role <-ontToMany-> role