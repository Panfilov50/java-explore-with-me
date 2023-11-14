# java-explore-with-me


## Основной модуль

Основной модуль содержит всё необходимое для работы продукта, такое как:

- Модели данных для событий, пользователей и т.д.
- Репозитории для доступа к базе данных
- Сервисы для бизнес-логики
- Контроллеры для обработки запросов
- Конфигурации для подключения к базе данных и другим ресурсам

API основного сервиса разделено на три части:

- Административная - для администраторов сервиса. Она позволяет управлять всеми сущностями в базе данных, просматривать статистику по сервису, а также блокировать и разблокировать пользователей.
- Закрытая - будет доступна только авторизованным пользователям. Она позволяет создавать, редактировать и удалять свои события.
- Публичная - будет доступна без регистрации любому пользователю сети. Она позволяет просматривать список событий, фильтровать их по категориям и местоположению, а также просматривать детали конкретного события.


## Модуль статистики

Модуль статистики хранит количество просмотров и позволяет делать различные выборки для анализа работы приложения. Он использует отдельную базу данных H2 для хранения статистических данных. Он также предоставляет API для получения статистики по разным параметрам.

## Реализация дополнительной функциональности: location

Администрирование - возможность для администратора добавлять конкретные локации:города, театры, концертные залы и другие в виде координат (широта, долгота, радиус).
Локация как добавляет ее администратор Location: id, lat, lon, radius, name, address, type.
Получение списка этих локаций.
Возможность поиска событий в конкретной локации.

## Эндпоинты сервиса
### Эндпоинты административного доступа:

| Класс                 | Метод   | Путь                           |
|-----------------------|---------|--------------------------------|
| CategoryController    | POST    | /admin/categories              |
| CategoryController    | DELETE  | /admin/categories/{catId}      | 
| CategoryController    | PATCH   | /admin/categories/{catId}      | 
| CategoryController    | DELETE  | /admin/comments/{commId}       | 
| CategoryController    | GET     | /admin/comments/users/{userId} | 
| CompilationController | GET     | /compilations/{compId}         |
| CompilationController | POST    | /admin/compilations            |
| CompilationController | DELETE  | /admin/compilations/{compId}   |
| CompilationController | PATCH   | /admin/compilations/{compId}   |
| EventController       | GET     | /admin/events                  |
| EventController       | PATCH   | /admin/events/{eventId}        |
| LocationController    | POST    | /admin/locations               |
| LocationController    | PATCH   | /admin/locations/{locationId}  |
| LocationController    | DELETE  | /admin/locations/{locationId}  |
| LocationController    | GET     | /admin/locations/{locationId}  |
| LocationController    | GET     | /admin/locations               |
| UserController        | GET     | /admin/users                   |
| UserController        | POST    | /admin/users                   |
| UserController        | DELETE  | /admin/users/{userId}          |


### Эндпоинты закрытого доступа:
| Класс             | Метод  | Путь                                        |
|-------------------|--------|---------------------------------------------|
| EventController   | POST   | /users/{userId}/events                      |
| EventController   | GET    | /users/{userId}/events/{eventId}            |
| EventController   | PATCH  | /users/{userId}/events/{eventId}            |
| UserController    | GET    | /users/{userId}/events                      |
| UserController    | GET    | /users/{userId}/events/{eventId}/requests   |
| UserController    | PATCH  | /users/{userId}/events/{eventId}/requests   |
| UserController    | GET    | /users/{userId}/requests                    |
| UserController    | POST   | /users/{userId}/requests                    |
| UserController    | PATCH  | /users/{userId}/requests/{requestId}/cancel |


### Эндпоинты публичного доступа:
| Класс                 | Метод | Путь                   |
|-----------------------|-------|------------------------|
| CategoryController    | GET   | /categories            |
| CategoryController    | GET   | /categories/{catId}    |
| CompilationController | GET   | /compilations          |
| CompilationController | GET   | /compilations/{compId} |
| EventController       | GET   | /events                |
| EventController       | GET   | /locations/events      |
| EventController       | GET   | /events/{id}           |
| LocationController    | GET   | /locations             |

## Используемые технологии:
- Java 11
- Spring Boot
- PostgreSQL
- Hibernate
- Maven
- Lombok
- Postman
- Docker

https://github.com/Panfilov50/java-explore-with-me/pull/4
