package com.ihaydin.issuemanagement.service.impl;

import com.ihaydin.issuemanagement.entity.IssueHistory;
import com.ihaydin.issuemanagement.entity.Project;
import com.ihaydin.issuemanagement.repository.IssueHistoryRepository;
import com.ihaydin.issuemanagement.repository.IssueRepository;
import com.ihaydin.issuemanagement.repository.ProjectRepository;
import com.ihaydin.issuemanagement.service.IssueHistoryService;
import com.ihaydin.issuemanagement.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService {

    private final IssueHistoryRepository issueHistoryRepository;

    public IssueHistoryServiceImpl(IssueHistoryRepository issueHistoryRepository) {
        this.issueHistoryRepository = issueHistoryRepository;
    }

    @Override
    public IssueHistory save(IssueHistory issueHistory) {

        if (issueHistory.getDate() == null){
            throw new IllegalArgumentException("Issue cannot be null!");
        }

        issueHistory = issueHistoryRepository.save(issueHistory);

        return issueHistory;
    }

    @Override
    public IssueHistory getById(Long id) {
        return issueHistoryRepository.getOne(id);
    }

    @Override
    public Page<IssueHistory> getAllPageable(Pageable pageable) {
        return issueHistoryRepository.findAll(pageable);
    }

    @Override
    public Boolean delete(IssueHistory issueHistory) {
        issueHistoryRepository.delete(issueHistory);
        return Boolean.TRUE;
    }
}
