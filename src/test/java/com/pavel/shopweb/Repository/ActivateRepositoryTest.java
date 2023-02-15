package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.ActivateEntity;
import com.pavel.shopweb.Entity.PromoEntity;
import com.pavel.shopweb.Entity.UsersEntity;
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
class ActivateRepositoryTest {

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

    @MockBean
    private ActivateRepository activateRepository;

    @Test
    void findByUsersEntity() {
        ActivateEntity activateEntity = new ActivateEntity();
        UsersEntity users = new UsersEntity();
        given(activateRepository.findByUsersEntity(users)).willReturn(activateEntity);
        ActivateEntity activateEntityTest = activateRepository.findByUsersEntity(users);
        assertEquals(activateEntityTest, activateEntity);
    }

    @Test
    void findByUsersEntityAndPromoEntity() {
        ActivateEntity activateEntity = new ActivateEntity();
        UsersEntity users = new UsersEntity();
        PromoEntity promo = new PromoEntity();
        given(activateRepository.findByUsersEntityAndPromoEntity(users, promo)).willReturn(activateEntity);
        ActivateEntity activateEntityTest = activateRepository.findByUsersEntityAndPromoEntity(users, promo);
        assertEquals(activateEntityTest, activateEntity);
    }

    @Test
    void saveActivate(){
        ActivateEntity activateEntity = new ActivateEntity();
        given(activateRepository.save(activateEntity)).willReturn(activateEntity);
        ActivateEntity activateEntityTest = activateRepository.save(activateEntity);
        assertEquals(activateEntityTest, activateEntity);
    }
}