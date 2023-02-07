package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.DiscountEntity;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DiscountRepositoryTest {

    @MockBean
    private DiscountRepository discountRepository;
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
    void findAllDiscount(){
        List<DiscountEntity> discountEntities = new ArrayList<>();
        given(discountRepository.findAll()).willReturn(discountEntities);
        List<DiscountEntity> discountAll = discountRepository.findAll();
        assertEquals(discountAll, discountEntities);
    }

    @Test
    void saveDiscount(){
        DiscountEntity discount = new DiscountEntity();
        given(discountRepository.save(discount)).willReturn(discount);
        DiscountEntity discountEntity = discountRepository.save(discount);
        assertEquals(discountEntity, discount);
    }
}