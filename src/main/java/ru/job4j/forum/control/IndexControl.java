package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;

@Controller
public class IndexControl {
    private final PostService postService;

    public IndexControl(PostService posts) {
        this.postService = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", postService.getAll());
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }
}