# REST API Specification (OpenAPI 3 / Swagger)

Interactive API documentation is published at: `http://localhost:8080/swagger-ui.html`

### Endpoints Overview:

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/auth/login` | Authenticate user & issue JWT bearer token | Public |
| `GET` | `/api/v1/incidents` | Fetch all incidents with filters (status, priority, category) | Authenticated |
| `POST` | `/api/v1/incidents` | Create new incident with automated SLA deadline calculation | Authenticated |
| `PUT` | `/api/v1/incidents/{id}/assign` | Assign incident to a support specialist agent | Agent / Admin |
| `PUT` | `/api/v1/incidents/{id}/status` | Transition status (`OPEN` ➔ `ASSIGNED` ➔ `IN_PROGRESS`) | Agent / Admin |
| `POST` | `/api/v1/incidents/{id}/resolve` | Record Root Cause Analysis (RCA) & resolve ticket | Agent / Admin |
| `POST` | `/api/v1/incidents/{id}/comments` | Add comment or internal agent note | Authenticated |
| `GET` | `/api/v1/analytics/metrics` | Fetch MTTR & SLA compliance metrics (Redis Cached) | Authenticated |
| `GET` | `/api/v1/kb` | Search Knowledge Base articles & RCA solutions | Authenticated |
