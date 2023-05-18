package com.ozil.reborn.entities

import lombok.Getter
import lombok.Setter
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "labels")
@Getter
@Setter
open class Label : BasicEntity() {


    @Column(name = "name")
    open var name: String? = "";

    @Column(name = "color")
    open var color: String? = "#000000";
}
