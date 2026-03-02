package com.company.projectmanagement.service;

import com.company.projectmanagement.entity.Project;
import com.company.projectmanagement.entity.User;
import com.company.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project, User createdBy){
        project.setCreatedBy(createdBy);
        project.setCreatedAt(LocalDateTime.now());
        if (project.getStatus() == null) project.setStatus("CREATED");
        return projectRepository.save(project);
    }
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
    public Project findById(Long id) {
        return projectRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Project Didnot found with this ID" + id));
    }
    public void DeleteProject(Long id){
        if(!projectRepository.existsById(id)){
            throw new RuntimeException("Project not found to delete");
        }
        projectRepository.deleteById(id);
    }
    public Optional<Project> getProjectByName(String name) {
        return projectRepository.findByName(name);
    }
}

