package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityMemRepository;
import ru.job4j.forum.repository.UserMemRepository;

@Service
public class UserService {
    private final UserMemRepository userRepository;
    private final AuthorityMemRepository authorityRepository;

    public UserService(UserMemRepository userRepository, AuthorityMemRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }

    public User findByUserName(String name) {
        return userRepository.findByUserName(name);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
