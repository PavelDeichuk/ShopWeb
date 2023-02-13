package com.pavel.shopweb.Service.impl;

import com.pavel.shopweb.Dto.QuestionDto;
import com.pavel.shopweb.Entity.*;
import com.pavel.shopweb.Exception.BadRequestException;
import com.pavel.shopweb.Exception.NotFoundException;
import com.pavel.shopweb.Mapper.QuestionMapper;
import com.pavel.shopweb.Repository.ProductRepository;
import com.pavel.shopweb.Repository.QuestionRepository;
import com.pavel.shopweb.Repository.UsersRepository;
import com.pavel.shopweb.Service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final UsersRepository usersRepository;

    private final ProductRepository productRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, UsersRepository usersRepository, ProductRepository productRepository) {
        this.questionRepository = questionRepository;
        this.usersRepository = usersRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<QuestionDto> GetAllQuestion(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<QuestionEntity> questionEntities = questionRepository.findAll(pageable);
        if(questionEntities.isEmpty()){
            throw new BadRequestException("Question list is empty!");
        }
        return questionEntities
                .stream()
                .map(QuestionMapper.INSTANCE::QUESTION_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto GetQuestionByProduct(Long product_id) {
        ProductEntity productEntity = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product_id");
                });
        QuestionEntity questionEntity = questionRepository.findByProductEntity(productEntity);
        return QuestionMapper.INSTANCE.QUESTION_DTO(questionEntity);
    }

    @Override
    public QuestionDto AddCommentToQuestion(Long question_id, Long user_id) {
        QuestionEntity question = questionRepository
                .findById(question_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for question id!");
                });
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id");
                });
        List<UsersEntity> usersEntities = question.getUsersEntities();
        usersEntities.add(users);
        question.setUsersEntities(usersEntities);
        questionRepository.save(question);
        return QuestionMapper.INSTANCE.QUESTION_DTO(question);
    }

    @Override
    public QuestionDto CreateQuestion(Long product_id, Long user_id, MultipartFile multipartFile, QuestionEntity questionEntity) {
        UsersEntity users = usersRepository
                .findById(user_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for user_id!");
                });
        ProductEntity productEntity = productRepository
                .findById(product_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for product id!");
                });
        if(multipartFile != null){
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(multipartFile.getOriginalFilename());
            imageEntity.setImage(imageEntity.getImage());
            imageEntity.setSize(multipartFile.getSize());
            imageEntity.setQuestionEntities(Arrays.asList(questionEntity));
            questionEntity.setImageEntity(imageEntity);
        }
        questionEntity.setProductEntity(productEntity);
        questionEntity.setUsersEntity(users);
        QuestionEntity question = questionRepository.save(questionEntity);
        return QuestionMapper.INSTANCE.QUESTION_DTO(question);
    }

    @Override
    public QuestionDto EditQuestion(Long question_id, MultipartFile multipartFile, QuestionEntity questionEntity) {
        QuestionEntity question = questionRepository
                .findById(question_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found question id!");
                });
        if(!multipartFile.isEmpty()){
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setName(multipartFile.getOriginalFilename());
            imageEntity.setImage(imageEntity.getImage());
            imageEntity.setSize(multipartFile.getSize());
            imageEntity.setQuestionEntities(Arrays.asList(questionEntity));
            question.setImageEntity(imageEntity);
        }
        question.setName(questionEntity.getName());
        question.setDescription(questionEntity.getDescription());
        QuestionEntity questionSave = questionRepository.save(question);
        return QuestionMapper.INSTANCE.QUESTION_DTO(questionSave);
    }

    @Override
    public QuestionDto DeleteQuestion(Long question_id) {
        QuestionEntity questionEntity = questionRepository
                .findById(question_id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Not found for question id!");
                });
        questionRepository.deleteById(question_id);
        return QuestionMapper.INSTANCE.QUESTION_DTO(questionEntity);
    }
}
