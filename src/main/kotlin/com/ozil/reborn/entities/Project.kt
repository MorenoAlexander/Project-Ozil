package com.ozil.reborn.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "project")
open class Project  : GUIDEntity(){

    @Column(name = "name", nullable = false)
    open var name: String? = null


    @Column(name = "description", nullable = false)
    open var description: String? = ""



}