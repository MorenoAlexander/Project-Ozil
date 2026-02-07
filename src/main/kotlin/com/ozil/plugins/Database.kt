package com.ozil.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.time.ZoneOffset

// Database table definition
object Items : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    override val primaryKey = PrimaryKey(id)
}

object TodoLists : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val createdAt = uinteger("created_at")
    val updatedAt = uinteger("updated_at")
    override val primaryKey = PrimaryKey(id)
}

object TodoItems : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 512)
    val createdAt = uinteger("created_at")
    val updatedAt = uinteger("updated_at")
    override val primaryKey = PrimaryKey(id)
    val todoListId = integer("todo_list_id").references(TodoLists.id)
}

// Data class for items
data class Item(val id: Int, val name: String)

data class TodoList(val id: Int, val name: String, val createdAt: UInt, val updatedAt: UInt)
data class TodoItem(val id: Int, val name: String, val createdAt: UInt, val updatedAt: UInt, val todoListId: Int)


fun Application.configureDatabase() {
    // Connect to SQLite database
    Database.connect("jdbc:sqlite:./data.db", "org.sqlite.JDBC")
    
    // Create table if it doesn't exist
    transaction {
        SchemaUtils.create(Items)
        SchemaUtils.create(TodoLists)
        SchemaUtils.create(TodoItems)

        if (TodoLists.selectAll().count() == 0L) {
            TodoLists.insert {
                it[name] = "Sample Todo List"
                it[createdAt] = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toUInt()
                it[updatedAt] = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toUInt()
            }
        }
        
        // Add some sample data if table is empty
        if (Items.selectAll().count() == 0L) {
            Items.insert {
                it[name] = "Sample Item 1"
            }
            Items.insert {
                it[name] = "Sample Item 2"
            }
            Items.insert {
                it[name] = "Sample Item 3"
            }
        }
    }
}

fun getAllItems(): List<Item> {
    return transaction {
        Items.selectAll().map {
            Item(it[Items.id], it[Items.name])
        }
    }
}

fun getTodoListById(id : Int) : TodoList {
    return transaction {
        TodoLists.select { TodoLists.id eq id }.map {
            TodoList(
                id = it[TodoLists.id],
                name = it[TodoLists.name],
                createdAt = it[TodoLists.createdAt],
                updatedAt = it[TodoLists.updatedAt]
            )
        }.firstOrNull() ?: throw NoSuchElementException("TodoList with id $id not found")
    }
}

fun getTodoItemsByListId(listId : Int) : List<TodoItem> {
    return transaction {
        TodoItems.select { TodoItems.todoListId eq listId }.map {
            TodoItem(
                id = it[TodoItems.id],
                name = it[TodoItems.name],
                createdAt = it[TodoItems.createdAt],
                updatedAt = it[TodoItems.updatedAt],
                todoListId = it[TodoItems.todoListId]
            )
        }
    }
}
