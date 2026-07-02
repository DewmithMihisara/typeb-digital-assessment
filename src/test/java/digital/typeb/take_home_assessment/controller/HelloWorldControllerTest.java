package digital.typeb.take_home_assessment.controller;

import digital.typeb.take_home_assessment.service.HelloWorldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloWorldController.class)
@Import(HelloWorldService.class)
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsOkWithGreetingForNameInFirstHalfOfAlphabet() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "alice"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Hello Alice\"}"));
    }

    @Test
    void returnsBadRequestForNameInSecondHalfOfAlphabet() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", "nathan"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }

    @Test
    void returnsBadRequestWhenNameParameterIsMissing() throws Exception {
        mockMvc.perform(get("/hello-world"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }

    @Test
    void returnsBadRequestWhenNameParameterIsEmpty() throws Exception {
        mockMvc.perform(get("/hello-world").param("name", ""))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error\":\"Invalid Input\"}"));
    }
}
