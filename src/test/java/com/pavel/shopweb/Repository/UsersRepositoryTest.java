package com.pavel.shopweb.Repository;

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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsersRepositoryTest {

    @MockBean
    private UsersRepository usersRepository;

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
    void FindAllUsers(){
        List<UsersEntity> users = new ArrayList<>();
        given(usersRepository.findAll()).willReturn(users);
        List<UsersEntity> usersEntitiesTest = usersRepository.findAll();
        assertEquals(users, usersEntitiesTest);
    }

    @Test
    void FindByUserId(){
        UsersEntity users = new UsersEntity();
        given(usersRepository.findById(1L)).willReturn(Optional.of(users));
        Optional<UsersEntity> usersEntity = usersRepository.findById(1L);
        assertEquals(usersEntity.get(), users);
    }

    @Test
    void findByUsername() {
        UsersEntity users = new UsersEntity();
        users.setUsername("test");
        given(usersRepository.findByUsername("test")).willReturn(Optional.of(users));
        Optional<UsersEntity> usersTest = usersRepository.findByUsername("test");
        assertEquals(usersTest.get().getUsername(), users.getUsername());
    }

    @Test
    void findByEmail() {
        UsersEntity users = new UsersEntity();
        given(usersRepository.findByEmail("test")).willReturn(Optional.of(users));
        Optional<UsersEntity> usersEntity = usersRepository.findByEmail("test");
        assertEquals(usersEntity.get(), users);
    }

    @Test
    void findByActivation() {
        UsersEntity users = new UsersEntity();
        given(usersRepository.findByActivation("test")).willReturn(Optional.of(users));
        Optional<UsersEntity> usersEntity = usersRepository.findByActivation("test");
        assertEquals(usersEntity.get(), users);
    }

    @Test
    void findByResetToken() {
        UsersEntity users = new UsersEntity();
        given(usersRepository.findByResetToken("test")).willReturn(Optional.of(users));
        Optional<UsersEntity> usersEntity = usersRepository.findByResetToken("test");
        assertEquals(usersEntity.get(), users);
    }

    @Test
    void SaveUser(){
        UsersEntity users = new UsersEntity();
        given(usersRepository.save(users)).willReturn(users);
        UsersEntity usersEntity = usersRepository.save(users);
        assertEquals(usersEntity, users);
    }
}