package com.ozil.reborn.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "sprints")
open class Sprint : GUIDEntity() {

    @Column(name = "name", nullable = false)
    open var name: String? = null



}
