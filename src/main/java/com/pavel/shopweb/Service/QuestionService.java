package com.pavel.shopweb.Service;

import com.pavel.shopweb.Dto.QuestionDto;
import com.pavel.shopweb.Entity.QuestionEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> GetAllQuestion(int page, int size);

    QuestionDto GetQuestionByProduct(Long product_id);

    QuestionDto AddCommentToQuestion(Long question_id, Long user_id);

    QuestionDto CreateQuestion(Long product_id, Long user_id, MultipartFile multipartFile, QuestionEntity questionEntity);

    QuestionDto EditQuestion(Long question_id, MultipartFile multipartFile, QuestionEntity questionEntity);

    QuestionDto DeleteQuestion(Long question_id);
}
