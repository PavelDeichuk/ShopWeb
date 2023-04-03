package com.pavel.shopweb.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.shopweb.Dto.ImageDto;
import com.pavel.shopweb.Service.ImageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    @Test
    void getImage() throws Exception {
        ImageDto imageDto = new ImageDto();
        BDDMockito.given(imageService.GetImageByName("test")).willReturn(imageDto.getImage());
        mockMvc
                .perform(get("/api/v1/image/{image_name}", "test")
                        .content(new ObjectMapper().writeValueAsBytes(imageDto.getImage())))
                .andExpect(status().isOk());
    }

    @Test
    void DeleteImage() throws Exception {
        ImageDto imageDto = new ImageDto();
        BDDMockito.given(imageService.DeleteImage(1L)).willReturn(imageDto);
        mockMvc
                .perform(delete("/api/v1/image/{image_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(imageDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}