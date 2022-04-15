package com.ozil.reborn.controllers

import com.ozil.reborn.entities.Project
import com.ozil.reborn.repositories.ProjectRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@Controller
@CrossOrigin("https://localhost:3000")
class HomeController(val repository: ProjectRepository) {

    @RequestMapping(value = ["/"])
    fun home(model : Model): String {
        var data = repository.findAll();

        print(data[0].name);
        model.addAttribute("projects", repository.findAll());
        return "home"
    }

    @GetMapping("/api/projects")
    fun getProjects(): List<Project> {
        return repository.findAll();
    }

}