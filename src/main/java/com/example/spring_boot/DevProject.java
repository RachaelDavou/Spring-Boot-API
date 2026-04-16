package com.example.spring_boot;

import jakarta.persistence.*;

@Entity
@Table(name = "dev_project")
public class DevProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String techStackRecommendation;

    public DevProject() {}

    public DevProject(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTechStackRecommendation() { return techStackRecommendation; }
    public void setTechStackRecommendation(String techStackRecommendation) {
        this.techStackRecommendation = techStackRecommendation;
    }
}
