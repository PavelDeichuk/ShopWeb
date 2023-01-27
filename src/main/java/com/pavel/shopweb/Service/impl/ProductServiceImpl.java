package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Entity.ImageEntity;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.ProductMapper;
import com.pavel.shopweb.Repository.ImageRepository;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ImageRepository imageRepository;

    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<ProductDto> GetAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        if(productEntities.isEmpty()){
            throw new NotFoundException("List products is empty!");
        }
        return productEntities
                .stream()
                .map(ProductMapper.INSTANCE::PRODUCT_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto GetProductById(Long product_id) {
        ProductEntity product = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        return ProductMapper.INSTANCE.PRODUCT_DTO(product);
    }

    @Override
    public ProductDto CreateProduct(ProductEntity productEntity) {
        productRepository
                .findByName(productEntity.getName())
                .ifPresent(name -> {
                    throw new BadRequestException("product name is exist!");
                });
        ProductEntity product = productRepository.save(productEntity);
        return ProductMapper.INSTANCE.PRODUCT_DTO(product);
    }

    @Override
    public ProductDto CreateImageProduct(Long product_id, MultipartFile multipartFile) throws IOException {
        ProductEntity product = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        ImageEntity image = new ImageEntity();
        image.setName(multipartFile.getOriginalFilename());
        image.setImage(multipartFile.getBytes());
        image.setSize(multipartFile.getSize());
        image.setProductEntity(product);
        product.setImageEntity(image);
        productRepository.save(product);
        return ProductMapper.INSTANCE.PRODUCT_DTO(product);
    }

    @Override
    public ProductDto EditImageProduct(Long product_id, MultipartFile multipartFile) throws IOException {
        ProductEntity product = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        ImageEntity image = imageRepository
                .findById(product.getImageEntity().getId())
                .orElseThrow(() -> {
                    throw new NotFoundException("You not have image_id!");
                });
        imageRepository
                .findByImage(image.getImage())
                .ifPresent(imageByte -> {
                    throw new BadRequestException("Image is equals to bytes, please post post another image");
                });
        image.setName(multipartFile.getOriginalFilename());
        image.setImage(multipartFile.getBytes());
        image.setSize(multipartFile.getSize());
        imageRepository.save(image);
        return ProductMapper.INSTANCE.PRODUCT_DTO(product);
    }

    @Override
    public ProductDto EditProduct(Long product_id, ProductEntity productEntity) {
        ProductEntity product = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        productRepository
                .findByName(productEntity.getName())
                .ifPresent(name -> {
                    throw new BadRequestException("Name is exist!");
                });
        product.setName(productEntity.getName());
        product.setDescription(productEntity.getDescription());
        product.setPrice(productEntity.getPrice());
        productRepository.save(product);
        return ProductMapper.INSTANCE.PRODUCT_DTO(product);
    }

    @Override
    public ProductDto DeleteProduct(Long product_id) {
        ProductEntity product = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        productRepository.deleteById(product_id);
        return ProductMapper.INSTANCE.PRODUCT_DTO(product);
    }
}
