package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AuthorityMemRepository {
    private static final AtomicInteger COUNT = new AtomicInteger();
    private final HashMap<Integer, Authority> authorities = new HashMap<>();

    public AuthorityMemRepository() {
        save(new Authority("ROLE_USER"));
    }

    public Authority findByAuthority(String authority) {
        for (Map.Entry<Integer, Authority> pair : authorities.entrySet()) {
            if (pair.getValue().getAuthority().equals(authority)) {
                return pair.getValue();
            }
        }
        return null;
    }

    public void save(Authority authority) {
        if (authority.getId() == 0) {
            authority.setId(COUNT.incrementAndGet());
        }
        authorities.put(authority.getId(), authority);
    }
}
