package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Entity.ImageEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Repository.ImageRepository;
import com.pavel.shopweb.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ImageRepository imageRepository;

    @MockBean
    private MultipartFile multipartFile;


    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void getAllProduct() {
        List<ProductEntity> productEntityList = new ArrayList<>();
        productEntityList.add(null);
        PageImpl<ProductEntity> pageImpl = new PageImpl<>(productEntityList);
        when(productRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, productService.GetAllProduct(1, 3).size());
        verify(productRepository).findAll((Pageable) any());
    }

    @Test
    void getProductById() {
        ProductEntity product = new ProductEntity();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        ProductDto productDto = productService.GetProductById(1L);
        assertEquals(productDto.getId(), product.getId());
        verify(productRepository).findById(1L);
    }

    @Test
    void createProduct() {
        ProductEntity product = ProductEntity.builder().id(1L).build();
        when(productRepository.findByName("test")).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        ProductDto productDto = productService.CreateProduct(product);
        assertEquals(productDto.getId(), product.getId());
    }

    @Test
    void createImageProduct() throws IOException {
        ProductEntity product = new ProductEntity();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        ProductDto productDto = productService.CreateImageProduct(1L, multipartFile);
        assertEquals(productDto.getId(), product.getId());
    }

    @Test
    void editImageProduct() throws IOException{
        ProductEntity product = new ProductEntity();
        ImageEntity image = ImageEntity.builder().id(1L).build();
        product.setImageEntity(image);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(imageRepository.findById(1L)).thenReturn(Optional.of(image));
        when(imageRepository.save(image)).thenReturn(image);
        ProductDto productDto = productService.EditImageProduct(1L, multipartFile);
        assertEquals(productDto.getId(), product.getId());
    }

    @Test
    void editProduct() {
        ProductEntity product = new ProductEntity();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        ProductDto productDto = productService.EditProduct(1L, product);
        assertEquals(productDto.getId(), product.getId());
    }
}