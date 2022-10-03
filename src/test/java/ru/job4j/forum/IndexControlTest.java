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
import ru.job4j.forum.model.Topic;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class IndexControlTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        Collection<Topic> expected = List.of(new Topic(1,"Discussion 1", "Description 1",
                LocalDateTime.of(2022, 10, 2, 22, 24, 48)),
                new Topic(2,"Discussion 2", "Description 2",
                        LocalDateTime.of(2022, 10, 3, 20, 12, 37)));
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("topics", expected));
    }
}