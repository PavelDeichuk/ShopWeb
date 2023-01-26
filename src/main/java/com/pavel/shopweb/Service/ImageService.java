package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.ImageDto;

public interface ImageService {


    byte[] GetImageByName(String name);

    ImageDto DeleteImage(Long image_id);
}
