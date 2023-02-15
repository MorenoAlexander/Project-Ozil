package com.ozil.reborn.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "users")
open class User : BasicEntity() {

    @Column(name = "username", nullable = false, unique = true)
    open var username: String? = null

    @Column(name = "password", nullable = false, )
    private var password: String? = null

    @Column(name = "email", nullable = false, unique = true)
    open var email: String? = null



}