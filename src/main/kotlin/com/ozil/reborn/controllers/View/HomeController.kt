package com.ozil.reborn.controllers.View

import com.ozil.reborn.repositories.ProjectRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller("/")
@CrossOrigin("https://localhost:3000")
class HomeController(val repository: ProjectRepository) {

    @RequestMapping(value = [""], method = [RequestMethod.GET])
    fun home(model : Model): String {
        val data = repository.findAll();

        model.addAttribute("projects", data);
        return "home"
    }

    @RequestMapping(value= [""], method = [RequestMethod.PUT])
    // disable authentication for this endpoint
    fun put(model : Model): String {

        return "viewtemp/put"
    }
}
