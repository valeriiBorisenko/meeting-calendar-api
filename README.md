# 📅 Meeting Calendar API
A simple REST API to manage meetings: create, view, update and delete.

---

### ✨ Features

- Create a meeting — with title, date, time, level (type of meeting), participant emails, and optional description.
- Get all meetings — retrieve a list of all meetings in the system.
- Update a meeting — modify existing meeting data.
- Delete a meeting — remove a meeting by its ID.

---

### 🛠 Tech Stack

- Java 17+
- Spring Boot (Web, Data JPA, Validation)
- MySQL (main DB), H2 (for testing)
- Lombok
- Maven
- Swagger

---

### 📦 DTOs

#### `MeetingDTOForm`
Used to create or update meetings.

| Field             | Type        | Validation                              |
|-------------------|-------------|------------------------------------------|
| `id`              | `Long`      | Optional (for updates)                   |
| `title`           | `String`    | Required, not blank                      |
| `date`            | `LocalDate` | Required, today or in the future         |
| `time`            | `LocalTime` | Required                                 |
| `level`           | `String`    | Required (type of meeting)               |
| `participantsEmails` | `String` | Required, list of emails (comma-separated) |
| `description`     | `String`    | Optional                                 |

#### `MeetingDTOView`
Returned when reading meeting data. Has the same fields as `MeetingDTOForm`.

---

### ⚙️ Configuration

- By default, H2 in-memory database is used for local testing.
- To use MySQL, update your `application-dev.properties` with:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/meetings-calendar
spring.datasource.username=your_user
spring.datasource.password=your_password
```
---

### 📖 API Docs

Once the app is running, you can view interactive Swagger docs at:

```
http://localhost:8080/swagger-ui.html
```
