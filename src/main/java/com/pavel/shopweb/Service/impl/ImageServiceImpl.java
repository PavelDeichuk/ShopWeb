package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.ImageDto;
import com.pavel.shopweb.Entity.ImageEntity;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.ImageMapper;
import com.pavel.shopweb.Repository.ImageRepository;
import com.pavel.shopweb.Service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public byte[] GetImageByName(String name) {
        ImageEntity image = imageRepository
                .findByName(name)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for name image");
                });
        return image.getImage();
    };

    @Override
    public ImageDto DeleteImage(Long image_id) {
        ImageEntity image = imageRepository
                .findById(image_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for image id!");
                });
        imageRepository.deleteById(image_id);
        return ImageMapper.INSTANCE.IMAGE_DTO(image);
    }
}
