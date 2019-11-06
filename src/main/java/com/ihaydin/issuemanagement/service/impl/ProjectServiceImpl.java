package com.ihaydin.issuemanagement.service.impl;

import com.ihaydin.issuemanagement.entity.Issue;
import com.ihaydin.issuemanagement.entity.Project;
import com.ihaydin.issuemanagement.repository.IssueRepository;
import com.ihaydin.issuemanagement.repository.ProjectRepository;
import com.ihaydin.issuemanagement.service.IssueService;
import com.ihaydin.issuemanagement.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project save(Project project) {

        if (project.getProjectCode() == null){
            throw new IllegalArgumentException("Project code cannot be null!");
        }

        return projectRepository.save(project);
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.getOne(id);
    }

    @Override
    public List<Project> getByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<Project> getByProjectCodeContains(String projectCode) {
        return null;
    }

    @Override
    public Page<Project> getAllPageable(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(Project project) {
        return null;
    }
}
