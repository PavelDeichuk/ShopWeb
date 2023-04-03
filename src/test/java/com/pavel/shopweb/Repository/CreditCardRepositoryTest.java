package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.CreditCardEntity;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardRepositoryTest {

    @MockBean
    private CreditCardRepository creditCardRepository;

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
    void findbyUserIdCredit(){
        UsersEntity users = UsersEntity.builder().id(1L).build();
        List<CreditCardEntity> creditCardEntities = new ArrayList<>();
        given(creditCardRepository.findByUsersEntity(users)).willReturn(creditCardEntities);
        List<CreditCardEntity> creditCardEntitiesTest = creditCardRepository.findByUsersEntity(users);
        assertEquals(creditCardEntitiesTest, creditCardEntities);
    }

    @Test
    void creditcardSave(){
        CreditCardEntity creditCard = new CreditCardEntity();
        given(creditCardRepository.save(creditCard)).willReturn(creditCard);
        CreditCardEntity creditCardEntity = creditCardRepository.save(creditCard);
        assertEquals(creditCardEntity, creditCard);
    }

}