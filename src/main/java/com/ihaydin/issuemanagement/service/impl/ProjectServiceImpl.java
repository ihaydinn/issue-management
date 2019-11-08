package com.ihaydin.issuemanagement.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihaydin.issuemanagement.dto.ProjectDto;
import com.ihaydin.issuemanagement.entity.Issue;
import com.ihaydin.issuemanagement.entity.Project;
import com.ihaydin.issuemanagement.repository.IssueRepository;
import com.ihaydin.issuemanagement.repository.ProjectRepository;
import com.ihaydin.issuemanagement.service.IssueService;
import com.ihaydin.issuemanagement.service.ProjectService;
import com.ihaydin.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public ProjectDto save(ProjectDto project) {
       /* if (project.getProjectCode() == null){
            throw new IllegalArgumentException("Project code cannot be null!");
        }*/
       Project projectCheck = projectRepository.getByProjectCode(project.getProjectCode());
       if (projectCheck!=null)
           throw new IllegalArgumentException("Hataaa!!");

        Project p = modelMapper.map(project, Project.class);
        p = projectRepository.save(p);
        project.setId(p.getId());
        return project;
    }

    @Override
    public ProjectDto getById(Long id) {
        Project p = projectRepository.getOne(id);
        return modelMapper.map(p, ProjectDto.class);
    }

    @Override
    public ProjectDto getByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<ProjectDto> getByProjectCodeContains(String projectCode) {
        return null;
    }

    @Override
    public TPage<ProjectDto> getAllPageable(Pageable pageable){
        Page<Project> data = projectRepository.findAll(pageable);
        TPage<ProjectDto> response = new TPage<ProjectDto>();
        response.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProjectDto[].class)));
        return response;
    }

    @Override
    public ProjectDto update(Long id, ProjectDto project) {
        Project projectDb = projectRepository.getOne(id);
        if (projectDb == null)
            throw new IllegalArgumentException("Proje Kodu Hataaa!!");

        Project projectCheck = projectRepository.getByProjectCodeAndIdNot(project.getProjectCode(), id);
        if (projectCheck!=null && projectCheck.getId() != projectDb.getId())
            throw new IllegalArgumentException("Hataaa!!");

        projectDb.setProjectCode(project.getProjectCode());
        projectDb.setProjectName(project.getProjectName());
        projectRepository.save(projectDb);
        return modelMapper.map(projectDb, ProjectDto.class);
    }

    @Override
    public Boolean delete(ProjectDto project) {
        return null;
    }

    public Boolean delete(Long id) {
        projectRepository.deleteById(id);
        return true;
    }
}
