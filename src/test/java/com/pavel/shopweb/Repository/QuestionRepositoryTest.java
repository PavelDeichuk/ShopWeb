package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Entity.QuestionEntity;
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
class QuestionRepositoryTest {

    @MockBean
    private QuestionRepository questionRepository;

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
    void findByProductEntity() {
        ProductEntity product = new ProductEntity();
        QuestionEntity question = new QuestionEntity();
        given(questionRepository.findByProductEntity(product)).willReturn(question);
        QuestionEntity questionEntity = questionRepository.findByProductEntity(product);
        assertEquals(questionEntity, question);
    }

    @Test
    void questionSave(){
        QuestionEntity question = new QuestionEntity();
        given(questionRepository.save(question)).willReturn(question);
        QuestionEntity questionEntity = questionRepository.save(question);
        assertEquals(questionEntity, question);
    }
}