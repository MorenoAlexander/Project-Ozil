package com.ozil.reborn.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
open class User : BasicEntity() {

    @Column(name = "username", nullable = false, unique = true)
    var username: String? = null

    @Column(name = "password", nullable = false, )
    private var password: String? = null

    @Column(name = "email", nullable = false, unique = true)
    var email: String? = null



}