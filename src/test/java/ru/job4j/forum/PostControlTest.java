package ru.job4j.forum;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService posts;

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

    @Test
    @WithMockUser
    public void shouldSavePost() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name", "Discussion 1")
                .param("description", "Description 1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> post = ArgumentCaptor.forClass(Post.class);
        ArgumentCaptor<Message> message = ArgumentCaptor.forClass(Message.class);
        Mockito.verify(posts).save(post.capture(), message.capture());
        assertThat(post.getValue().getName(), is("Discussion 1"));
        assertThat(post.getValue().getDescription(), is("Description 1"));
    }
}
