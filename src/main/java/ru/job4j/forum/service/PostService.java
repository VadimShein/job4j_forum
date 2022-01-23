package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Iterable<Post> getAll() {
        return postRepository.findAll();
    }

    public Post findPostById(int id) {
        return postRepository.findPostById(id);
    }

    public void save(Post post, Message message) {
        if (message != null) {
            Post pst = findPostById(post.getId());
            pst.addMessage(message);
            postRepository.save(pst);
        } else {
            postRepository.save(post);
        }
    }
}