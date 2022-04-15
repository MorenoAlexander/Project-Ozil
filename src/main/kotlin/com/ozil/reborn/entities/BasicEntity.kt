package com.ozil.reborn.entities

import java.util.*
import javax.persistence.*

@MappedSuperclass
open class BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    open var id: Long? = null

    @Column(name = "created_at", nullable = false)
    open var createdAt: Date? = null

    @Column(name = "updated_at", nullable = false)
    open var updatedAt: Date? = null

}