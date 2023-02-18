# ASAITEC - TECHNICAL TEST
## JSON samples 

#### add new user:

POST http://localhost:8080/asaitec/api/users
```
{
    "userName": "Alexandre Galvao",
    "password": "agPWD"
}
```

#### add permission to user:

POST http://localhost:8080/asaitec/api/users/{userId}/permissions
```
{
    "id": {permissionId}
}
```
