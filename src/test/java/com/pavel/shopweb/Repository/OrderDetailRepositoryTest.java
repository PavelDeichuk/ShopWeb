package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.OrderDetailEntity;
import com.pavel.shopweb.Entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderDetailRepositoryTest {

    @MockBean
    private OrderDetailRepository orderDetailRepository;

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
    void findByOrderEntityTest() {
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        OrderEntity order = new OrderEntity();
        given(orderDetailRepository.findByOrderEntity(order)).willReturn(orderDetailEntity);
        OrderDetailEntity orderDetailTest = orderDetailRepository.findByOrderEntity(order);
        assertEquals(orderDetailTest, orderDetailEntity);
    }

    @Test
    void saveOrderDetailTest(){
        OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
        given(orderDetailRepository.save(orderDetailEntity)).willReturn(orderDetailEntity);
        OrderDetailEntity orderDetailTest = orderDetailRepository.save(orderDetailEntity);
        assertEquals(orderDetailTest, orderDetailEntity);
    }
}