package com.ozil.plugins

import com.ozil.pages.todoListPage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun Application.configureRouting() {
    routing {
        // Serve static files (CSS, JS, HTMX)
        static("/static") {
            resources("static")
        }

        // Home page
        get("/") {
            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title { +"Project Ozil - Ktor + HTMX" }
                    link(rel = "stylesheet", href = "/static/css/style.css")
                    script(src = "https://unpkg.com/htmx.org@1.9.10") {}
                }
                body {
                    div {
                        id = "content"
                        h1 { +"Welcome to Project Ozil" }
                        p { +"A clean Ktor + HTMX + SQLite application" }

                        button {
                            attributes["hx-get"] = "/api/hello"
                            attributes["hx-target"] = "#result"
                            attributes["hx-swap"] = "innerHTML"
                            +"Click me (HTMX Demo)"
                        }

                        div {
                            id = "result"
                            style = "margin-top: 20px; padding: 10px; border: 1px solid #ddd;"
                        }
                    }
                }
            }
        }

        get("/todo/{todoListId}") {
            val id = call.parameters["todoListId"]?.toIntOrNull() ?: return@get call.respondText(
                "Missing or invalid id", status = HttpStatusCode.BadRequest
            )

            try {
                val todo = getTodoListById(id) ?: return@get call.respondText(
                    "Todo list not found", status = HttpStatusCode.NotFound
                )

                call.respondHtml(HttpStatusCode.OK) {
                    todoListPage(todo)
                }

            } catch (e: NoSuchElementException) {
                call.respondText("Error fetching todo list: ${e.message}", status = HttpStatusCode.InternalServerError)
            }
        }
        // API endpoint example
        get("/api/hello") {
            call.respondText(
                "<p>Hello from Ktor API! Time: ${System.currentTimeMillis()}</p>",
                ContentType.Text.Html
            )
        }

        // Example: Get all items from database
        get("/api/items") {
            val items = getAllItems()
            call.respondText(buildString {
                append("<ul>")
                items.forEach { item ->
                    append("<li>${item.name}</li>")
                }
                append("</ul>")
            }, ContentType.Text.Html)
        }

        get("/api/todo-items/{listId}") {
            val listId = call.parameters["listId"]?.toIntOrNull() ?: return@get call.respondText(
                "Invalid listId format", status = HttpStatusCode.BadRequest
            )
            val todoItems = getTodoItemsByListId(listId)
            call.respondText(buildString {
                append("<ul>")
                todoItems.forEach { item ->
                    append("<li>${item.name} list</li>")
                }
                append("</ul>")
            }, ContentType.Text.Html)
        }
    }
}
