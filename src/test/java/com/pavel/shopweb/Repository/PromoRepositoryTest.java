package com.pavel.shopweb.Repository;

import com.pavel.shopweb.Entity.PromoEntity;
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
class PromoRepositoryTest {

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
    private PromoRepository promoRepository;

    @Test
    void findByName() {
        PromoEntity promo = new PromoEntity();
        given(promoRepository.findByName("test")).willReturn(Optional.of(promo));
        Optional<PromoEntity> promoEntityTest = promoRepository.findByName("test");
        assertEquals(promoEntityTest.get(), promo);
    }

    @Test
    void savePromo(){
        PromoEntity promo = new PromoEntity();
        given(promoRepository.save(promo)).willReturn(promo);
        PromoEntity promoEntityTest = promoRepository.save(promo);
        assertEquals(promoEntityTest, promo);
    }
}