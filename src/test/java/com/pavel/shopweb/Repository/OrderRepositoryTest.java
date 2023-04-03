package com.pavel.shopweb.Repository;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @MockBean
    private OrderRepository orderRepository;

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
    void getAllOrderTest(){
        List<OrderEntity> orderEntities = new ArrayList<>();
        given(orderRepository.findAll()).willReturn(orderEntities);
        List<OrderEntity> orderEntitiesTest = orderRepository.findAll();
        assertEquals(orderEntitiesTest, orderEntities);
    }

    @Test
    void getOrderByIdTest(){
        OrderEntity order = OrderEntity.builder().id(1L).build();
        given(orderRepository.findById(1L)).willReturn(Optional.of(order));
        Optional<OrderEntity> orderTest = orderRepository.findById(1L);
        assertEquals(orderTest.get().getId(), order.getId());
    }

    @Test
    void saveOrderTest(){
        OrderEntity order = new OrderEntity();
        given(orderRepository.save(order)).willReturn(order);
        OrderEntity orderEntityTest = orderRepository.save(order);
        assertEquals(orderEntityTest, order);
    }
}