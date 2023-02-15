package com.ozil.reborn.entities

import lombok.Getter
import lombok.Setter
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "project")
@Getter
@Setter
open class Project  : GUIDEntity(){

    @Column(name = "name", nullable = false)
    open var name: String? = null


    @Column(name = "description", nullable = true, length = 1000)
    open var description: String? = ""



    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
    open var stories : MutableList<Story> = ArrayList()

}
