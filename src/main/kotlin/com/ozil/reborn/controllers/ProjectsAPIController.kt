package com.ozil.reborn.controllers

import com.ozil.reborn.controllers.ProjectsAPIController.Companion.routeRoot
import com.ozil.reborn.entities.Project
import com.ozil.reborn.repositories.ProjectRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin(origins = ["*"])
class ProjectsAPIController(val projectRepository: ProjectRepository) {

    @GetMapping(routeRoot)
    fun getProjects(): List<Project> = projectRepository.findAll();

    @PostMapping(routeRoot)
    fun addProject(project: Project) {
        projectRepository.save(project);
    }

    companion object {
        private const val routeRoot = "/api/projects";
    }
}
