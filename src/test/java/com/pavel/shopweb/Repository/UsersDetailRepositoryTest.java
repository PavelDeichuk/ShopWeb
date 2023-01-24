package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.UsersDetailEntity;
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
class UsersDetailRepositoryTest {

    @MockBean
    private UsersDetailRepository usersDetailRepository;

    @MockBean
    private UsersEntity usersEntity;

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
    void findByUsersEntity() {
        UsersDetailEntity usersDetailEntity = new UsersDetailEntity();
        given(usersDetailRepository.findByUsersEntity(usersEntity)).willReturn(usersDetailEntity);
        UsersDetailEntity usersDetailTest = usersDetailRepository.findByUsersEntity(usersEntity);
        assertEquals(usersDetailTest, usersDetailEntity);
    }

    @Test
    void SaveUsersEntity(){
        UsersDetailEntity usersDetail = new UsersDetailEntity();
        given(usersDetailRepository.save(usersDetail)).willReturn(usersDetail);
        UsersDetailEntity usersDetailTest = usersDetailRepository.save(usersDetail);
        assertEquals(usersDetailTest, usersDetail);
    }
}