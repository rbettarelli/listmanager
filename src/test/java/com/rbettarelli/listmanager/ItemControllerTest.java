package com.rbettarelli.listmanager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.rbettarelli.listmanager.controller.ItemController;
import com.rbettarelli.listmanager.model.Item;
import com.rbettarelli.listmanager.service.ItemService;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    void testGetAllItems() throws Exception {
        Item item = new Item();
        item.setId(1L);
        item.setTitle("Test Item");

        when(itemService.findAll()).thenReturn(Collections.singletonList(item));

        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Item"));
    }

    @Test
    void testCreateItem() throws Exception {
        Item item = new Item();
        item.setTitle("New Item");

        when(itemService.save(any(Item.class))).thenReturn(item);

        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"New Item\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Item"));
    }
}