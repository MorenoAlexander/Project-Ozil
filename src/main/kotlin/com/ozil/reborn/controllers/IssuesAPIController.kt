package com.ozil.reborn.controllers

import com.ozil.reborn.entities.Issue
import com.ozil.reborn.repositories.IssueRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin(origins = ["*"])
class IssuesAPIController(val issuesRepository: IssueRepository) {

    @GetMapping("/api/issues")
    fun getIssues(): List<Issue> = issuesRepository.findAll();

    @GetMapping("$routeRoot/{id}")
    fun getIssueById(@PathVariable id: String): Issue = issuesRepository.getIssueById(UUID.fromString(id)).get();

    @PostMapping(routeRoot)
    fun addIssue(issue: Issue) {
        issuesRepository.save(issue);
    }

    @PutMapping("$routeRoot/{id}")
    fun updateIssue(@PathVariable id: String, issue: Issue) {
        issuesRepository.save(issue);
    }

    @DeleteMapping("$routeRoot/{id}")
    fun deleteIssue(@PathVariable id: String) {
        issuesRepository.deleteById(UUID.fromString(id));
    }


    companion object {
        private const val routeRoot = "/api/issues";
    }
}
