package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    private static final String IMAGE_NAME = "/{image_name}";

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = IMAGE_NAME, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public byte[] GetImage(@PathVariable String image_name){
        return imageService.GetImageByName(image_name);
    }
}
