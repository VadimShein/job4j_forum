package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserMemRepository {
    private static final AtomicInteger COUNT = new AtomicInteger();
    private final HashMap<Integer, User> users = new HashMap<>();

    public UserMemRepository() {
    }

    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(COUNT.incrementAndGet());
        }
        users.put(user.getId(), user);
    }

    public User findByUserName(String name) {
        for (Map.Entry<Integer, User> pair : users.entrySet()) {
            if (pair.getValue().getUsername().equals(name)) {
                return pair.getValue();
            }
        }
        return null;
    }
}
