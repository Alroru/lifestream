package com.alroru.lifestream.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listDefaultsToFirstPageOf20SortedById() throws Exception {
        mockMvc.perform(get("/api/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(103))
                .andExpect(jsonPath("$.content.length()").value(20))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].commonName").value("Lombriz de tierra común"))
                .andExpect(jsonPath("$.content[0].imageUrl").doesNotExist());
    }

    @Test
    void searchByNameFindsCommonAndScientificNames() throws Exception {
        mockMvc.perform(get("/api/animals").param("name", "lobo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].commonName").value("Lobo ibérico"));
    }

    @Test
    void filterByDiet() throws Exception {
        mockMvc.perform(get("/api/animals").param("diet", "Carnívoro").param("size", "200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(40));
    }

    @Test
    void detailReturnsFullAnimal() throws Exception {
        mockMvc.perform(get("/api/animals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commonName").value("Lombriz de tierra común"))
                .andExpect(jsonPath("$.scientificName").value("Lumbricus terrestris"))
                .andExpect(jsonPath("$.imageUrl").value("/images/animals/lombriz-de-tierra-comun.jpg"));
    }

    @Test
    void detailNotFoundReturns404() throws Exception {
        mockMvc.perform(get("/api/animals/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void countReturnsTotal() throws Exception {
        mockMvc.perform(get("/api/animals/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("103"));
    }
}
