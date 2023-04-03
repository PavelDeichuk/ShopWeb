package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @MockBean
    private ProductRepository productRepository;

    @Container
    public static PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer("postgres:13.3")
                    .withUsername("root")
                    .withPassword("root")
                    .withDatabaseName("shop");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }


    @Test
    void findAllProductTest(){
        List<ProductEntity> productEntities = new ArrayList<>();
        given(productRepository.findAll()).willReturn(productEntities);
        List<ProductEntity> productListTest = productRepository.findAll();
        assertEquals(productListTest, productEntities);
    }

    @Test
    void findByProductId(){
        ProductEntity product = new ProductEntity();
        given(productRepository.findById(1L)).willReturn(Optional.of(product));
        Optional<ProductEntity> productTest = productRepository.findById(1L);
        assertEquals(productTest.get(), product);
    }
    @Test
    void findByName() {
        ProductEntity product = new ProductEntity();
        given(productRepository.findByName("test")).willReturn(Optional.of(product));
        Optional<ProductEntity> productTest = productRepository.findByName("test");
        assertEquals(productTest.get(), product);
    }

    @Test
    void saveProductTest(){
        ProductEntity product = new ProductEntity();
        given(productRepository.save(product)).willReturn(product);
        ProductEntity productTest = productRepository.save(product);
        assertEquals(productTest, product);
    }
}