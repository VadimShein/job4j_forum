package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.repository.MessageRepository;
import ru.job4j.forum.repository.TopicRepository;

@Service
public class MessageService {
    final TopicRepository topicRepository;
    final MessageRepository messageRepository;

    private MessageService(MessageRepository messageRepository, TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
        this.messageRepository = messageRepository;
    }
        public void saveMessage(Message message) {
            messageRepository.save(message);
    }

    public Iterable<Message> findMessagesByTopicId(int topicId) {
        return messageRepository.findMessagesByTopicId(topicId);
    }
}
