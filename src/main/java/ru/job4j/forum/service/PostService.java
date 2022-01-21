package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostMemRepository;

import java.util.Collection;

@Service
public class PostService {
    private final PostMemRepository postRepository;

    public PostService(PostMemRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Collection<Post> getAll() {
        return postRepository.findAll();
    }

    public Post findByPostId(int id) {
        return postRepository.findByPostId(id);
    }

    public void save(Post post, Message message) {
        if (message != null) {
            Post pst = findByPostId(post.getId());
            message.setId(pst.getCountMessages().incrementAndGet());
            pst.addMessage(message);
            postRepository.save(pst);
        } else {
            postRepository.save(post);
        }
    }
}