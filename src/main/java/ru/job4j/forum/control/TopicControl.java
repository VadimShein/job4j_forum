package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.service.TopicService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TopicControl {
    private final TopicService topicService;

    public TopicControl(TopicService topics) {
        this.topicService = topics;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("topics", topicService.getAll());
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }

    @GetMapping("/createTopic")
    public String create(Model model) {
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "createTopic";
    }

    @GetMapping("/editTopic")
    public String editTopic(Model model, HttpServletRequest req) {
        if (req.getParameter("topicId") != null) {
            model.addAttribute("topic", topicService.findTopicById(Integer.parseInt(req.getParameter("topicId"))));
        }
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "editTopic";
    }

    @PostMapping("/saveTopic")
    public String save(@ModelAttribute Topic topic) {
        topicService.saveTopic(topic);
        return "redirect:/";
    }
}