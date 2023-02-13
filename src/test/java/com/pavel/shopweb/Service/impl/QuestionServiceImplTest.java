package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.QuestionDto;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Entity.QuestionEntity;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Repository.QuestionRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class QuestionServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private QuestionRepository questionRepository;

    @MockBean
    private MultipartFile multipartFile;

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Test
    void getAllQuestion() {
        List<QuestionEntity> questionEntities = new ArrayList<>();
        questionEntities.add(null);
        PageImpl<QuestionEntity> pageImpl = new PageImpl<>(questionEntities);
        when(questionRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, questionService.GetAllQuestion(1, 3).size());
        verify(questionRepository).findAll((Pageable) any());
    }

    @Test
    void getQuestionByProduct() {
        ProductEntity productEntity = new ProductEntity();
        QuestionEntity question = QuestionEntity.builder().id(1L).build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(questionRepository.findByProductEntity(productEntity)).thenReturn(question);
        QuestionDto questionDto = questionService.GetQuestionByProduct(1L);
        assertEquals(questionDto.getId(), question.getId());
    }

    @Test
    void createQuestion() {
        ProductEntity productEntity = new ProductEntity();
        UsersEntity users = new UsersEntity();
        QuestionEntity question = QuestionEntity.builder().id(1L).build();
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        when(questionRepository.save(question)).thenReturn(question);
        QuestionDto questionDto = questionService.CreateQuestion(1L, 1L, multipartFile, question);
        assertEquals(questionDto.getId(), question.getId());
    }

    @Test
    void editQuestion() {
        QuestionEntity question = QuestionEntity.builder().id(1L).build();
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(questionRepository.save(question)).thenReturn(question);
        QuestionDto questionDto = questionService.EditQuestion(1L, multipartFile, question);
        assertEquals(questionDto.getId(), question.getId());
    }
}