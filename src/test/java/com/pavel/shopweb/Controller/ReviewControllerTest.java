package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.ReviewDto;
import com.pavel.shopweb.Service.ReviewService;
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
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private MultipartFile multipartFile;

    @Test
    void getAllReview() throws Exception{
        List<ReviewDto> reviewDtos = new ArrayList<>();
        BDDMockito.given(reviewService.GetAllReview(0,10)).willReturn(reviewDtos);
        mockMvc
                .perform(get("/api/v1/review")
                        .content(new ObjectMapper().writeValueAsBytes(reviewDtos))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void getReviewById() throws Exception{
        ReviewDto reviewDto = new ReviewDto();
        BDDMockito.given(reviewService.GetReviewByProduct(1L)).willReturn(reviewDto);
        mockMvc
                .perform(get("/api/v1/review/product/{product_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(reviewDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteReview() throws Exception{
        ReviewDto reviewDto = new ReviewDto();
        BDDMockito.given(reviewService.DeleteReview(1L)).willReturn(reviewDto);
        mockMvc
                .perform(delete("/api/v1/review/{review_id}", 1L))
                .andExpect(status().isOk());
    }
}