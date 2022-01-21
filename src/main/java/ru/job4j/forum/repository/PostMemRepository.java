package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMemRepository {
    private static final AtomicInteger COUNT = new AtomicInteger();
    private final HashMap<Integer, Post> posts = new HashMap<>();

    public PostMemRepository() {
        Post post1 = new Post("Продаю машину ладу 01", "Хорошая машина");
        post1.addMessage(new Message(1, "user1", "message1"));
        post1.addMessage(new Message(2, "user2", "message2"));
        save(post1);
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(COUNT.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public Post findByPostId(int id) {
        return posts.get(id);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
