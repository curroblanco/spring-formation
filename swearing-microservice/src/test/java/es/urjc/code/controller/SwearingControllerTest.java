package es.urjc.code.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.urjc.code.dto.MessageDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage(textWithSwearing);
        ObjectMapper objectMapper = new ObjectMapper();
        final ResultActions result = mockMvc.perform(
                post("/evaluate")
                        .content(objectMapper.writeValueAsString(messageDto))
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(content().string(Boolean.TRUE.toString()));
    }

    @Test
    public void shouldReturn200StatusCodeAndFalse() throws Exception {
        String textWithoutSwearing = "Era un domingo en la tarde y fui a los coches de choque";
        MessageDto messageDto = new MessageDto();
        messageDto.setMessage(textWithoutSwearing);
        ObjectMapper objectMapper = new ObjectMapper();
        final ResultActions result = mockMvc.perform(
                post("/evaluate")
                        .content(objectMapper.writeValueAsString(messageDto))
                        .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(content().string(Boolean.FALSE.toString()));
    }
}