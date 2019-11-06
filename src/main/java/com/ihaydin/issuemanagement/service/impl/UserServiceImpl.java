package com.ihaydin.issuemanagement.service.impl;

import com.ihaydin.issuemanagement.entity.Project;
import com.ihaydin.issuemanagement.entity.User;
import com.ihaydin.issuemanagement.repository.ProjectRepository;
import com.ihaydin.issuemanagement.repository.UserRepository;
import com.ihaydin.issuemanagement.service.ProjectService;
import com.ihaydin.issuemanagement.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {

        if (user.getEmail() == null){
            throw new IllegalArgumentException("Username cannot be null!");
        }
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public Page<User> getAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
