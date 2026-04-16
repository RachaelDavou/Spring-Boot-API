# Spring Boot API

A Spring Boot REST API that takes a project idea and returns an AI-generated tech stack recommendation using OpenAI. 


## Requirements

- Java 21
- OpenAI API key — get one at https://platform.openai.com/api-keys


## How to Run

### Option 1 — IntelliJ (recommended)

1. Open the project in IntelliJ
2. Go to **Run > Edit Configurations**
3. Under **Environment variables**, add: `OPENAI_API_KEY=sk-your-key-here`
4. Run the application.

### Option 2 — PowerShell

Find your Java 21 installation path:

```powershell
where java
```

```powershell
$env:JAVA_HOME="C:\path\to\your\jdk21"
$env:OPENAI_API_KEY="sk-your-key-here"
.\mvnw.cmd spring-boot:run
```

### Option 3 — Windows CMD

Find your Java 21 installation path:
```cmd
where java
```

```cmd
set JAVA_HOME=C:\path\to\your\jdk21
set OPENAI_API_KEY=sk-your-key-here
mvnw.cmd spring-boot:run
```


The app starts on `http://localhost:8080`.


## Testing the API

### Browser UI

Open `http://localhost:8080` in any browser. The page has forms for all endpoints.

### Swagger UI

Open `http://localhost:8080/swagger-ui.html` for interactive docs.

### Postman

- Method: `POST`
- URL: `http://localhost:8080/api/v1/projects`
- Headers: `Content-Type: application/json`
- Body → raw → JSON:

```json
{
  "name": "Real-time chat app",
  "description": "A web app where users can create rooms and chat in real time"
}
```

## How It Works

Every request goes through the same Spring Boot layers:

1. A request hits a **Controller** (`AiController` or `DevProjectController`)
2. The controller calls a **Service** which contains the business logic
3. If AI is needed, the service calls **AiService**, which uses Spring AI's `ChatClient` to send the prompt to OpenAI
4. If data needs to be saved or retrieved, the service uses the **Repository** to read/write to the H2 in-memory database
5. The result is returned to the caller as JSON

**Quick AI Chat** (`POST /api/ai/chat`) — skips the database and sends your prompt straight to OpenAI and returns the response.

**Dev Project** (`POST /api/v1/projects`) — builds a prompt from your input, calls OpenAI, saves the project + recommendation to the database, and returns the saved record.

**Get Projects** (`GET /api/v1/projects`) — reads all saved projects from the database and returns them as a JSON list.



## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/ai/chat` | Quick AI chat - ask anything |
| POST | `/api/v1/projects` | Submit a project, get AI recommendation |
| GET | `/api/v1/projects` | Get all saved projects |
| GET | `/api/v1/projects/{id}` | Get a project by ID |
| DELETE | `/api/v1/projects/{id}` | Delete a project |
