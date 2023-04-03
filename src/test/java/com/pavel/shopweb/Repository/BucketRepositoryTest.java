package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.BucketEntity;
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
class BucketRepositoryTest {

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
    private BucketRepository bucketRepository;

    @Test
    void findByUsersEntity() {
        BucketEntity bucketEntity = new BucketEntity();
        UsersEntity users = new UsersEntity();
        given(bucketRepository.findByUsersEntity(users)).willReturn(bucketEntity);
        BucketEntity bucketEntityTest = bucketRepository.findByUsersEntity(users);
        assertEquals(bucketEntityTest, bucketEntity);
    }

    @Test
    void saveBucket(){
        BucketEntity bucketEntity = new BucketEntity();
        given(bucketRepository.save(bucketEntity)).willReturn(bucketEntity);
        BucketEntity bucketEntityTest = bucketRepository.save(bucketEntity);
        assertEquals(bucketEntityTest, bucketEntity);
    }
}