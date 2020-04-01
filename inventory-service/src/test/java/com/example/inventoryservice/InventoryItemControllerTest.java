package com.example.inventoryservice;

import com.example.inventoryservice.controllers.InventoryItemController;
import com.example.inventoryservice.entities.InventoryItem;
import com.example.inventoryservice.services.InterfaceInventoryItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(InventoryItemController.class)
public class InventoryItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterfaceInventoryItem inventoryItemService;

    @Autowired
    private ObjectMapper objectMapper;

    private InventoryItem inventoryItem;

    @Before
    public  void setup(){
        inventoryItem = new InventoryItem();
        inventoryItem.setIdInventory((long) 1);
        inventoryItem.setProductCode("P001");
        inventoryItem.setAvailableQuantity(5);
    }

    @Test
    public void testFindAllProducts() throws Exception {
        List<InventoryItem> listInventory = Arrays.asList(inventoryItem);
        doReturn(listInventory).when(inventoryItemService).findAllProducts();
        this.mockMvc.perform(get("/api/inventory/findAllProducts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idInventory",is(1)))
                .andExpect(jsonPath("$[0].productCode",is(inventoryItem.getProductCode())));
    }

    @Test
    public void testFindInventoryByProductCode() throws Exception {
        doReturn(Optional.of(inventoryItem)).when(inventoryItemService).findByProductCode(anyString());

        this.mockMvc.perform(get("/api/inventory/findByProductCode/P001").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productCode",is(inventoryItem.getProductCode())));
    }

    @Test
    public void testSaveInventory() throws Exception {
        doReturn(inventoryItem).when(inventoryItemService).saveOrUpdateProductCode(any(InventoryItem.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/inventory/saveProductCode")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsBytes(inventoryItem));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idInventory",is(1)))
                .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(inventoryItem)));
    }

    @Test
    public void testDeleteInventory() throws Exception {
        Long idInventory = inventoryItem.getIdInventory();


        doReturn(Optional.of(inventoryItem)).when(inventoryItemService).findById(anyLong());
        doNothing().when(inventoryItemService).delete(idInventory);
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/inventory/deleteInventory/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
