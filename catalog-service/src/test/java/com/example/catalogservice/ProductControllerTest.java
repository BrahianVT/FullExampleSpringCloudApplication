package com.example.catalogservice;

import com.example.catalogservice.controllers.ProductController;
import com.example.catalogservice.entities.Product;
import com.example.catalogservice.services.ProductServiceInterface;
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


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceInterface productService;

    @Autowired
    ObjectMapper objectMapper;

    Product product, product2;
    List<Product> listProducts;
    @Before
    public void initProduct(){
        product = new Product();
        product.setId(67l);
        product.setCode("CPR02");
        product.setDescription("TestDescription");
        product.setName("ProductTest");
        product.setPrice(343.3);

        product2 = new Product();
        product2.setId(6l);
        product2.setCode("CP2");
        product2.setDescription("TestDescription2");
        product2.setName("ProductTest2");
        product2.setPrice(4543);
        listProducts = Arrays.asList(product, product2);

    }
    @Test
    public void testFindAllProducts() throws Exception {

        doReturn(listProducts).when(productService).findAllProducts();

        mockMvc.perform(get("/api/products/findAllProducts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(67)))
                .andExpect(jsonPath("$[0].code", is(product.getCode())))
                .andExpect(jsonPath("$[0].price",is(343.3)));
    }

    @Test
    public void testFindProductsByDescription()throws Exception {
        List<Product> productsByDescription = Arrays.asList(product);
        doReturn(productsByDescription).when(productService).findProductByDescription(anyString());

        mockMvc.perform(get("/api/products/findProductByDescription/TestDescription")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(67)))
                .andExpect(jsonPath("$[0].description",is("TestDescription")));
    }

    @Test
    public void testFindProductByCode() throws Exception {
        doReturn(Optional.of(product2)).when(productService).findProductByCode(anyString());

        mockMvc.perform(get("/api/products/findProductByCode/CP2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(product2.getCode())));
    }

    @Test
    public void testFindProductsByName() throws Exception {
        List<Product> productsByName = Arrays.asList(product2);
        doReturn(productsByName).when(productService).findProductByName(anyString());

        mockMvc.perform(get("/api/products/findProductByName/ProductTest2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(product2.getName())));


    }

    @Test
    public void testSaveProduct() throws Exception {
        doReturn(product).when(productService).saveOrUpdateProduct(any(Product.class));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products/saveProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsBytes(product));

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(67)))
                .andExpect(jsonPath("$.code", is(product.getCode())));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Long idProduct = product2.getId();
        doReturn(Optional.of(product2)).when(productService).findByIdProduct(anyLong());
        doNothing().when(productService).deleteProduct(idProduct);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/deleteProduct/6")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
