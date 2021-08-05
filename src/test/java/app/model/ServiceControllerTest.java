package app.model;

import app.controller.ServiceController;
import app.service.UserService;
import app.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.context.SpringBootTest;

/*import static org.hamcrest.Matchers.is;*/
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ServiceControllerTest {

    @Mock
    User user;

    @Mock
    UserServiceImpl userServiceMock;

    @InjectMocks
    ServiceController userController;

    private MockMvc mockMvc;

    /*@Spy
    private UserServiceImpl userService;
    */
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGet() throws Exception {

        int userId = 3;

        mockMvc.perform(
                get("/user/" + userId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("data.age", is(15)))
                .andExpect(jsonPath("data.firstName", is("bob")))
                .andExpect(jsonPath("data.id", is(userId)))
                .andExpect(jsonPath("success", is(true)));
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(
                post("/user")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(new User()))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("success", is(true)));
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(
                put("/user")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(new User())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("success", is(true)));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(
                delete("/user/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("success", is(true)));
    }
}
