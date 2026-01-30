# Project Ozil

A clean skeleton web application built with:
- **Ktor** - Kotlin web framework for backend API
- **HTMX** - Modern frontend interactions without heavy JavaScript
- **SQLite** - Lightweight database for local development

## Getting Started

### Prerequisites
- Java 17 or higher
- Gradle (included via wrapper)

### Running the Application

```bash
./gradlew run
```

The application will start on `http://localhost:8080`

### Building the Application

```bash
./gradlew build
```

### Project Structure

```
src/
├── main/
│   ├── kotlin/com/ozil/
│   │   ├── Application.kt          # Main application entry point
│   │   └── plugins/
│   │       ├── Routing.kt          # HTTP routes and endpoints
│   │       └── Database.kt         # Database configuration and models
│   └── resources/
│       └── static/
│           └── css/
│               └── style.css       # Application styles
└── test/
    └── kotlin/com/ozil/            # Test files
```

## Features

- Clean Ktor server setup with Netty
- HTMX integration for dynamic frontend interactions
- SQLite database with Exposed ORM
- Example CRUD operations
- Static file serving for CSS and JS
- HTML DSL for server-side rendering

## API Endpoints

- `GET /` - Home page with HTMX demo
- `GET /api/hello` - Simple API endpoint example
- `GET /api/items` - Get all items from database

## Database

The application uses SQLite for local development. The database file (`data.db`) is created automatically on first run with sample data.

## Next Steps

1. Add your own routes in `Routing.kt`
2. Define your database models in `Database.kt`
3. Create additional frontend pages with HTMX
4. Add authentication if needed
5. Implement your business logic

## License

This project is a starter template. Use it as you wish!
