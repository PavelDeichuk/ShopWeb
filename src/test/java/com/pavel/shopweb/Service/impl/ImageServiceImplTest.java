package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Entity.ImageEntity;
import com.pavel.shopweb.Repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class ImageServiceImplTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @Test
    void getImageByName() {
        ImageEntity image = new ImageEntity();
        when(imageRepository.findByName("test")).thenReturn(Optional.of(image));
        byte[] bytes = imageService.GetImageByName("test");
        assertEquals(bytes, image.getImage());
        verify(imageRepository).findByName("test");
    }
}