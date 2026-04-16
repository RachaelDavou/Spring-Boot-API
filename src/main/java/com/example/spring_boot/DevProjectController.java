package com.example.spring_boot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class DevProjectController {

    private final DevProjectService service;

    public DevProjectController(DevProjectService service) {
        this.service = service;
    }

    @GetMapping
    public List<DevProject> getAllProjects() {
        return service.getAllProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevProject> getProjectById(@PathVariable Integer id) {
        return service.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DevProject createProject(@RequestBody CreateProjectRequest request) {
        DevProject project = new DevProject(request.getName(), request.getDescription());
        return service.createProject(project);
    }

    public static class CreateProjectRequest {
        private String name;
        private String description;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Integer id) {
        service.deleteProject(id);
    }
}
