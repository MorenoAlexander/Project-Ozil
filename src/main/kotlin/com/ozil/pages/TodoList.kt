
package com.ozil.pages

import com.ozil.plugins.TodoList
import kotlinx.html.*

fun HTML.todoListPage(todoList: TodoList) {
    head {
        title { +"Todo List: ${todoList.name}" }
        link(rel = "stylesheet", href = "/static/css/style.css")
        script(src = "https://unpkg.com/htmx.org@1.9.10") {}
    }
    body {
        div {
            id = "content"
            h1 { +"Todo List" }
            p { +"This is a simple todo list page." }

            // Example of HTMX usage
            button {
                attributes["hx-get"] = "/api/todo-items/${todoList.id}"
                attributes["hx-target"] = "#todo-items"
                attributes["hx-swap"] = "innerHTML"
                +"Refresh Todo Items"
            }

            div {
                attributes["hx-get"] = "/api/todo-items/${todoList.id}"
                attributes["hx-trigger"] = "load"
                id = "todo-items"
                style = "margin-top: 20px; padding: 10px; border: 1px solid #ddd;"
                // Todo items will be loaded here via HTMX
            }
        }
    }
}