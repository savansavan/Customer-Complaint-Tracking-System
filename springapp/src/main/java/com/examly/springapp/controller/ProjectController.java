package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Project;
import com.examly.springapp.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        return new ResponseEntity<>(projectService.addProject(project), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project project) {
        Project updated = projectService.updateProject(id, project);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/page/{page}/{size}")
    public Page<Project> getProjectsPage(
            @PathVariable int page,
            @PathVariable int size,
            @RequestParam(defaultValue = "projectId") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return projectService.getProjectsPage(page, size, sortField, sortDir);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getProjectsByStatus(@PathVariable String status) {
        List<Project> list = projectService.getProjectsByStatus(status);
        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No projects found with status: " + status);
        }
        return ResponseEntity.ok(list);
    }
}
