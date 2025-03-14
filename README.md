# Система бронирования билетов (Ticket Booking System)

## Используемые технологии:

### Backend:
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **JWT**
- **Lombok**
- **Swagger**

### База данных:
- **PostgreSQL**

---

## Функциональные требования:

### Регистрация и авторизация пользователей:
- Пользователи могут регистрироваться и авторизоваться в системе.
- Роли: `USER` (обычный пользователь) и `ADMIN` (администратор).

### Управление мероприятиями (только для администратора):
- Администратор может **добавлять, редактировать и удалять** мероприятия.
- Мероприятие содержит:
    - Название
    - Описание
    - Дату
    - Время
    - Место
    - Количество доступных билетов

### Бронирование билетов:
- Пользователь может просматривать **список доступных мероприятий**.
- Пользователь может **забронировать билет** на мероприятие (если есть доступные билеты).
- Пользователь может **отменить бронь**.

### Просмотр бронирований:
- Пользователь может просматривать **список своих бронирований**.

### Фильтрация и сортировка:
- Пользователь может фильтровать мероприятия по **дате, месту и названию**.
- Пользователь может сортировать мероприятия по **дате**.

---

## Эндпоинты:

### 1. Регистрация пользователя
- **Метод:** `POST`
- **URL:** `/api/auth/register`

- **Тело запроса:**
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```

- **Валидация:**
    - username и password не должны быть пустыми.
    - username должен быть уникальным.

- **Ответ:**
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```

### 2. Авторизация пользователя
- **Метод:** `POST`
- **URL:** `/api/auth/login`

- **Тело запроса:**
    ```json
    {
    "username": "string",
    "password": "string"
    }
    ```

- **Ответ:**
    ```json
    {
    "token": "string"  
    }
    ```

### 3. Добавление мероприятия (только для администратора)
- **Метод:** `POST`
- **URL:** `/api/events`

- **Тело запроса:**
    ```json
    {
    "title": "string",
    "description": "string",
    "date": "string (формат: yyyy-MM-dd)",
    "time": "string (формат: HH:mm)",
    "location": "string",
    "availableTickets": "number"
    }
    ```

- **Валидация:**
    - Все поля обязательны.
    - availableTickets должно быть положительным числом.

- **Ответ:**
    ```json
    {
    "id": "number",
    "title": "string",
    "description": "string",
    "date": "string",
    "time": "string",
    "location": "string",
    "availableTickets": "number" 
    }
    ```

### 4. Просмотр списка мероприятий
- **Метод:** `GET`
- **URL:** `/api/events`

- **Параметры запроса:**
    - date (опционально) - фильтрация по дате.
    - location (опционально) - фильтрация по месту.
    - sort (опционально) - сортировка по дате (asc или desc).

- **Ответ:**
    ```json
    [
    {
    "id": "number",
    "title": "string",
    "description": "string",
    "date": "string",
    "time": "string",
    "location": "string",
    "availableTickets": "number"
    }
    ]
    ```

### 5. Бронирование билета
- **Метод:** `POST`
- **URL:** `/api/bookings`

- **Тело запроса:**
    ```json
    {
    "eventId": "number"
    }
    ```

- **Ответ:**
    ```json
    {
    "id": "number",
    "eventId": "number",
    "userId": "number",
    "bookingDate": "string (формат: yyyy-MM-dd'T'HH:mm:ss)" 
    }
    ```

### 6. Отмена бронирования
- **Метод:** `DELETE`
- **URL:** `/api/bookings/{id}`

- **Ответ:**
    ```json
    {
    "message": "Booking canceled successfully" 
    }
    ```

### 7. Авторизация пользователя
- **Метод:** `GET`
- **URL:** `/api/bookings`

- **Ответ:**
    ```json
    [
    {
    "id": "number",
    "eventId": "number",
    "userId": "number",
    "bookingDate": "string"
    }
    ]
    ```

---

## Требования к Docker:
- Создать **Dockerfile** для сборки Spring Boot приложения.
- Создать **docker-compose.yaml** для развертывания приложения и базы данных PostgreSQL.

---

## Дополнительные требования:
- Использовать JWT для аутентификации.
- Написать интеграционные и unit-тесты.
- Документировать API с использованием Swagger.
