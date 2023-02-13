package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.QuestionDto;
import com.pavel.shopweb.Service.QuestionService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private MultipartFile multipartFile;

    @Test
    void getAllQuestion() throws Exception {
        List<QuestionDto> questionDtos = new ArrayList<>();
        BDDMockito.given(questionService.GetAllQuestion(0,10))
                .willReturn(questionDtos);
        mockMvc
                .perform(get("/api/v1/question")
                        .content(new ObjectMapper().writeValueAsBytes(questionDtos))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void getQuestionByProduct() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        BDDMockito.given(questionService.GetQuestionByProduct(1L))
                .willReturn(questionDto);
        mockMvc
                .perform(get("/api/v1/question/product/{product_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(questionDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void addCommentQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        BDDMockito.given(questionService.AddCommentToQuestion(1L, 1L))
                .willReturn(questionDto);
        mockMvc
                .perform(get("/api/v1/question/{question_id}/user/{user_id}", 1L, 1L)
                        .content(new ObjectMapper().writeValueAsBytes(questionDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
    @Test
    void deleteQuestion() throws Exception {
        QuestionDto questionDto = new QuestionDto();
        BDDMockito.given(questionService.DeleteQuestion(1L)).willReturn(questionDto);
        mockMvc
                .perform(delete("/api/v1/question/{question_id}", 1L)
                        .content(new ObjectMapper().writeValueAsBytes(questionDto))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}