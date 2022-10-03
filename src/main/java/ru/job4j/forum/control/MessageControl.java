package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.service.MessageService;
import ru.job4j.forum.service.TopicService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MessageControl {
    private final TopicService topicService;
    private final MessageService messageService;

    public MessageControl(TopicService topicService, MessageService messageService) {
        this.topicService = topicService;
        this.messageService = messageService;
    }

    @PostMapping("/saveMessage")
    public String create(@ModelAttribute Topic topic, HttpServletRequest req, RedirectAttributes attr) {
        attr.addAttribute("topicId", topic.getId());
        Message message = null;
        if (req.getParameter("text") != null) {
            message = (new Message(req.getParameter("author"), req.getParameter("text")));
        }
        Topic dbTopic = topicService.findTopicById(topic.getId());
        message.setTopic(dbTopic);
        messageService.saveMessage(message);
        return "redirect:/topic";
    }

    @GetMapping("/topic")
    public String findMessagesByTopicId(Model model, @RequestParam("topicId") String topicId) {
        Iterable<Message> messages = messageService.findMessagesByTopicId(Integer.parseInt(topicId));
        Topic topic = topicService.findTopicById(Integer.parseInt(topicId));
        model.addAttribute("messages", messages);
        model.addAttribute("topic", topic);
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "topic";
    }
}
