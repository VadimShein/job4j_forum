package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostControl {
    private final PostService postService;

    public PostControl(PostService service) {
        this.postService = service;
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Post post, HttpServletRequest req) {
        Message message = null;
        if (req.getParameter("text") != null) {
            message = (new Message(req.getParameter("author"), req.getParameter("text")));
        }
        postService.save(post, message);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "edit";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("postId") String postId) {
        Post post = postService.findPostById(Integer.parseInt(postId));
        model.addAttribute(post);
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "edit";
    }

    @GetMapping("/post")
    public String post(Model model, @RequestParam("postId") String postId) {
        Post post = postService.findPostById(Integer.parseInt(postId));
        model.addAttribute(post);
        model.addAttribute("userName", SecurityContextHolder.getContext().getAuthentication().getName());
        return "post";
    }
}
