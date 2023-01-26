package com.pavel.shopweb.Dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private Long id;

    private String name;
    private byte[] image;

    private Long size;
}
