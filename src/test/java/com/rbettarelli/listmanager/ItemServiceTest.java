package com.rbettarelli.listmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rbettarelli.listmanager.model.Item;
import com.rbettarelli.listmanager.repository.ItemRepository;
import com.rbettarelli.listmanager.service.ItemService;

public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    public ItemServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveItem() {
        Item item = new Item();
        item.setTitle("Test Item");

        when(itemRepository.save(item)).thenReturn(item);

        Item savedItem = itemService.save(item);
        assertNotNull(savedItem);
        assertEquals("Test Item", savedItem.getTitle());
    }

    @Test
    void testFindById() {
        Item item = new Item();
        item.setId(1L);
        item.setTitle("Test Item");

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Optional<Item> foundItem = itemService.findById(1L);
        assertTrue(foundItem.isPresent());
        assertEquals("Test Item", foundItem.get().getTitle());
    }

    @Test
    void testDeleteById() {
        itemService.deleteById(1L);
        verify(itemRepository, times(1)).deleteById(1L);
    }
}
