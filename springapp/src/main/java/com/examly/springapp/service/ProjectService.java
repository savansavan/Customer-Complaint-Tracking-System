package com.examly.springapp.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Project;

@Service
public class ProjectService {

    private List<Project> projects = new ArrayList<>();
    private Long idCounter = 1L;

    public Project addProject(Project project) {
        project.setProjectId(idCounter++);
        projects.add(project);
        return project;
    }


    public List<Project> getAllProjects() {
        return projects;
    }

 
    public Project getProjectById(Long id) {
        return projects.stream()
                .filter(p -> p.getProjectId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Project updateProject(Long id, Project project) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getProjectId().equals(id)) {
                project.setProjectId(id);
                projects.set(i, project);
                return project;
            }
        }
        return null;
    }

    public Page<Project> getProjectsPage(int page, int size, String sortField, String sortDir) {
        List<Project> sortedList = new ArrayList<>(projects);

        // Sorting
        Comparator<Project> comparator;
        switch (sortField.toLowerCase()) {
            case "projectname":
                comparator = Comparator.comparing(Project::getProjectName, String.CASE_INSENSITIVE_ORDER);
                break;
            case "status":
                comparator = Comparator.comparing(Project::getStatus, String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                comparator = Comparator.comparing(Project::getProjectId);
        }
        if ("desc".equalsIgnoreCase(sortDir)) {
            comparator = comparator.reversed();
        }
        sortedList.sort(comparator);

        int total = sortedList.size();
        int start = Math.min(page * size, total);
        int end = Math.min(start + size, total);

        List<Project> content = sortedList.subList(start, end);

        return new PageImpl<>(content, PageRequest.of(page, size), total);
    }

    public List<Project> getProjectsByStatus(String status) {
        return projects.stream()
                .filter(p -> p.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }
}
