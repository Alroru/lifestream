package com.alroru.lifestream.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeRendersAnimalList() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("Lombriz de tierra común")))
                .andExpect(content().string(containsString("detail")));
    }

    @Test
    void homeAppliesNameFilter() throws Exception {
        mockMvc.perform(get("/").param("name", "lobo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Lobo ibérico")));
    }

    @Test
    void homeRendersFilterDropdowns() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<select name=\"habitat\"")))
                .andExpect(content().string(containsString("<select name=\"diet\"")))
                .andExpect(content().string(containsString("<select name=\"conservationStatus\"")))
                .andExpect(content().string(containsString("Carnívoro")));
    }

    @Test
    void homeKeepsSelectedDiet() throws Exception {
        mockMvc.perform(get("/").param("diet", "Carnívoro"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("value=\"Carnívoro\" selected")));
    }
}
