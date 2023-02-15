package com.ozil.reborn.repositories;

import com.ozil.reborn.entities.Project
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

@org.springframework.stereotype.Repository
interface ProjectRepository : JpaRepository<Project, UUID> {

    fun getProjectByName(name: String): Optional<Project>
    fun findAllByOrderByCreatedAtDesc(): ArrayList<Project>

    fun findByName(name: String): Optional<Project>

    fun countDistinctById(id: UUID): Long

    fun findByOrderByNameAsc(sort: Sort): ArrayList<Project>



}
