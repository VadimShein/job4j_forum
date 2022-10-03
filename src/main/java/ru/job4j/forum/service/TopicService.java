package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.repository.TopicRepository;

@Service
public class TopicService {
    final TopicRepository topicRepository;

    TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Iterable<Topic> getAll() {
        return topicRepository.findAll();
    }


    public Topic findTopicById(int id) {
        return topicRepository.findTopicById(id);
    }

    public void saveTopic(Topic topic) {
        topicRepository.save(topic);
    }
}
