package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.ImageEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ImageRepositoryTest {

    @MockBean
    private ImageRepository imageRepository;

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
    void findByNameTest() {
        ImageEntity image = new ImageEntity();
        given(imageRepository.findByName("test")).willReturn(Optional.of(image));
        Optional<ImageEntity> imageTest = imageRepository.findByName("test");
        assertEquals(imageTest.get(), image);
    }

    @Test
    void findByImageTest() {
        ImageEntity image = new ImageEntity();
        byte[] bytes = new byte[0];
        given(imageRepository.findByImage(bytes)).willReturn(Optional.of(image));
        Optional<ImageEntity> imageTest = imageRepository.findByImage(bytes);
        assertEquals(imageTest.get(), image);
    }

    @Test
    void saveImageTest(){
        ImageEntity image = new ImageEntity();
        given(imageRepository.save(image)).willReturn(image);
        ImageEntity imageTest = imageRepository.save(image);
        assertEquals(imageTest, image);
    }
}