package ru.job4j.forum;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.forum.model.Post;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnCreatePage() throws Exception {
        this.mockMvc.perform(get("/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void shouldReturnEditPage() throws Exception {
        Post expected = new Post(1,"Discussion 1", "Description 1");
        this.mockMvc.perform(get("/edit?postId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(MockMvcResultMatchers.model().attribute("post", expected));

    }

    @Test
    @WithMockUser
    public void shouldReturnPostPage() throws Exception {
        Post expected = new Post(1,"Discussion 1", "Description 1");
        this.mockMvc.perform(get("/post?postId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(MockMvcResultMatchers.model().attribute("post", expected));
    }
}
