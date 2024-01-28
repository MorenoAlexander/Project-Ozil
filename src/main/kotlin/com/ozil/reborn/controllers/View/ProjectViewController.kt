package com.ozil.reborn.controllers.View

import com.ozil.reborn.repositories.ProjectRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import java.util.*


@Controller
class ProjectViewController(val repository: ProjectRepository) {

    @RequestMapping(value = ["/projects/{id}"], method = [RequestMethod.GET])
    fun Index(@PathVariable id: String, model : Model) : String {

        val data = repository.getProjectById(UUID.fromString(id))

        if (!data.isPresent) {
            return "404"
        }

        model.addAttribute("project", data.get())
        return "project"
    }
}
