package com.ozil.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

// Database table definition
object Items : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    override val primaryKey = PrimaryKey(id)
}

// Data class for items
data class Item(val id: Int, val name: String)

fun Application.configureDatabase() {
    // Connect to SQLite database
    Database.connect("jdbc:sqlite:./data.db", "org.sqlite.JDBC")
    
    // Create table if it doesn't exist
    transaction {
        SchemaUtils.create(Items)
        
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
