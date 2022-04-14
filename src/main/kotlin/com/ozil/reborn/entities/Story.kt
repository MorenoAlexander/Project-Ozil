package com.ozil.reborn.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "story")
open class Story : GUIDEntity() {

    @Column(name = "title", nullable = false)
    open var title: String? = ""

    @Column(name = "description", nullable = false)
    open var description: String? = ""


}