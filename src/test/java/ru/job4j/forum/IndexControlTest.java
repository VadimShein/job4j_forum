package ru.job4j.forum;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.forum.model.Topic;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class IndexControlTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        Calendar calendar1 = new GregorianCalendar(2022, Calendar.OCTOBER, 2, 22, 24, 48);
        Calendar calendar2 = new GregorianCalendar(2022, Calendar.OCTOBER, 3, 20, 12, 37);
        Collection<Topic> expected = List.of(
                new Topic(1,"Тема 1", "Описание темы", calendar1),
                new Topic(2,"Тема 2", "Описание темы", calendar2));

        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attribute("topics", expected));
    }
}