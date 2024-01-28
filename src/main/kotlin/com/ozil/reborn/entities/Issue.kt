package com.ozil.reborn.entities

import lombok.Getter
import lombok.Setter
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "issues")
@Getter
@Setter
open class Issue(
    @ManyToOne
    @JoinColumn(name = "project_id", foreignKey = ForeignKey(name = "fk_issue_project"))
    open var project: Project,

    @ManyToMany
    @JoinTable(
        name = "issue_labels",
        joinColumns = [JoinColumn(name = "issue_id")],
        inverseJoinColumns = [JoinColumn(name = "label_id")]
    )
    open var labels: MutableList<Label> = ArrayList(),
    @Column(name = "title")
    open var Title: String = "",

    @Column(name = "description")
    open var Description: String = "",

    @OneToOne()
    @JoinColumn(name = "assignee_id", foreignKey = ForeignKey(name = "fk_issue_assignee"))
    open var assignee: User? = null,

    @OneToOne()
    @JoinColumn(name = "reporter_id", foreignKey = ForeignKey(name = "fk_issue_reporter"))
    open var reporter: User? = null,

    ) : GUIDEntity();
