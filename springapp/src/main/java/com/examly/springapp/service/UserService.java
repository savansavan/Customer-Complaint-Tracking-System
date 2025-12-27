package com.examly.springapp.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.User;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    private Long idCounter = 1L;

    public User addUser(User user) {
        user.setUserId(idCounter++);
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(u -> u.getUserId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User updateUser(Long id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(id)) {
                user.setUserId(id);
                users.set(i, user);
                return user;
            }
        }
        return null;
    }

    public List<User> getUsersByRole(String role) {
        return users.stream()
                .filter(u -> u.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public Page<User> getUsersPage(int page, int size) {
        int total = users.size();
        int start = Math.min(page * size, total);
        int end = Math.min(start + size, total);
        List<User> content = users.subList(start, end);
        return new PageImpl<>(content, PageRequest.of(page, size), total);
    }
}
