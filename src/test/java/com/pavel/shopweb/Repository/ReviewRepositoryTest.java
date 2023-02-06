package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Entity.ReviewEntity;
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
class ReviewRepositoryTest {

    @MockBean
    private ReviewRepository reviewRepository;

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
    void findAllReview(){
        List<ReviewEntity> reviewEntities = new ArrayList<>();
        given(reviewRepository.findAll()).willReturn(reviewEntities);
        List<ReviewEntity> reviewEntitiesTest = reviewRepository.findAll();
        assertEquals(reviewEntitiesTest, reviewEntities);
    }

    @Test
    void findReviewById(){
        ReviewEntity reviewEntity = new ReviewEntity();
        given(reviewRepository.findById(1L)).willReturn(Optional.of(reviewEntity));
        Optional<ReviewEntity> reviewIdTest = reviewRepository.findById(1L);
        assertEquals(reviewIdTest.get(), reviewEntity);
    }

    @Test
    void findByProductEntity() {
        ReviewEntity reviewEntity = new ReviewEntity();
        ProductEntity productEntity = new ProductEntity();
        given(reviewRepository.findByProductEntity(productEntity)).willReturn(reviewEntity);
        ReviewEntity reviewEntityTest = reviewRepository.findByProductEntity(productEntity);
        assertEquals(reviewEntityTest, reviewEntity);
    }

    @Test
    void saveReview(){
        ReviewEntity reviewEntity = new ReviewEntity();
        given(reviewRepository.save(reviewEntity)).willReturn(reviewEntity);
        ReviewEntity reviewTest = reviewRepository.save(reviewEntity);
        assertEquals(reviewTest, reviewEntity);
    }
}