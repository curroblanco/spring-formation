package es.urjc.code.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MimeTypeUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class SwearingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn200StatusCodeAndTrue() throws Exception {
        String textWithSwearing = "Era un domingo en la tarde y fui a los gilipipas de choque";

        final ResultActions result = mockMvc.perform(
                get("/evaluate")
                        .param("comment", textWithSwearing)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(content().string(Boolean.TRUE.toString()));
    }

    @Test
    public void shouldReturn200StatusCodeAndFalse() throws Exception {
        String textWithSwearing = "Era un domingo en la tarde y fui a los coches de choque";

        final ResultActions result = mockMvc.perform(
                get("/evaluate")
                        .param("comment", textWithSwearing)
                        .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

        result.andExpect(status().isOk());
        result.andExpect(content().string(Boolean.FALSE.toString()));
    }
}