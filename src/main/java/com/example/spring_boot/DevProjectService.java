package com.example.spring_boot;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevProjectService {

    private final DevProjectRepository repository;
    private final AiService aiService;

    public DevProjectService(DevProjectRepository repository, AiService aiService) {
        this.repository = repository;
        this.aiService = aiService;
    }

    public List<DevProject> getAllProjects() {
        return repository.findAll();
    }

    public Optional<DevProject> getProjectById(Integer id) {
        return repository.findById(id);
    }

    public DevProject createProject(DevProject project) {
        String prompt = String.format(
            "I am building a project called \"%s\". Description: %s. " +
            "Give me a concise tech stack recommendation. " +
            "Format your response exactly like this:\n" +
            "Backend: [choice] - [one sentence why]\n" +
            "Frontend: [choice] - [one sentence why]\n" +
            "Database: [choice] - [one sentence why]\n" +
            "Real-time/Messaging: [choice or N/A] - [one sentence why]\n" +
            "Deployment: [choice] - [one sentence why]\n" +
            "Key Libraries: [2-3 essential ones]\n\n" +
            "Keep each line short. No extra sections or long explanations.",
            project.getName(),
            project.getDescription()
        );

        String recommendation = aiService.chat(prompt);
        project.setTechStackRecommendation(recommendation);
        return repository.save(project);
    }

    public void deleteProject(Integer id) {
        repository.deleteById(id);
    }
}
