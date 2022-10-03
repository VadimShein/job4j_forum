package ru.job4j.forum;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.forum.model.Topic;
import ru.job4j.forum.service.TopicService;

import java.time.LocalDateTime;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class TopicControlTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topics;

    @Test
    @WithMockUser
    public void shouldReturnCreateTopicPage() throws Exception {
        this.mockMvc.perform(get("/createTopic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("createTopic"));
    }

    @Test
    @WithMockUser
    public void shouldReturnEditTopicPage() throws Exception {
        when(topics.findTopicById(1)).thenReturn(new Topic(1, "Discussion 1", "Description 1",
                LocalDateTime.of(2022, 10, 2, 22, 24, 48)));
        Topic expected = new Topic(1, "Discussion 1", "Description 1",
                LocalDateTime.of(2022, 10, 2, 22, 24, 48));
        this.mockMvc.perform(get("/editTopic?topicId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editTopic"))
                .andExpect(MockMvcResultMatchers.model().attribute("topic", expected));
    }

    @Test
    @WithMockUser
    public void shouldReturnTopicPage() throws Exception {
        when(topics.findTopicById(1)).thenReturn(new Topic(1, "Discussion 1", "Description 1",
                LocalDateTime.of(2022, 10, 2, 22, 24, 48)));
        Topic expected = new Topic(1, "Discussion 1", "Description 1",
                LocalDateTime.of(2022, 10, 2, 22, 24, 48));
        this.mockMvc.perform(get("/topic?topicId=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("topic"))
                .andExpect(MockMvcResultMatchers.model().attribute("topic", expected));
    }

    @Test
    @WithMockUser
    public void shouldSaveTopic() throws Exception {
        this.mockMvc.perform(post("/saveTopic")
                .param("name", "Discussion 1")
                .param("description", "Description 1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Topic> topic = ArgumentCaptor.forClass(Topic.class);
        Mockito.verify(topics).saveTopic(topic.capture());
        assertThat(topic.getValue().getName(), is("Discussion 1"));
        assertThat(topic.getValue().getDescription(), is("Description 1"));
    }
}
