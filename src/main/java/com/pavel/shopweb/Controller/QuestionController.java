package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.QuestionDto;
import com.pavel.shopweb.Entity.QuestionEntity;
import com.pavel.shopweb.Service.QuestionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    private static final String ADD_COMMENT_TO_QUESTION = "/{question_id}/user/{user_id}";

    private static final String CREATE_QUESTION = "/product/{product_id}/user/{user_id}";

    private static final String GET_QUESTION_PRODUCT_ID = "/product/{product_id}";

    private static final String QUESTION_ID = "/{question_id}";

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuestionDto> GetAllQuestion(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size){
        return questionService.GetAllQuestion(page, size);
    }

    @RequestMapping(value = GET_QUESTION_PRODUCT_ID,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestionDto GetQuestionByProduct(@PathVariable Long product_id){
        return questionService.GetQuestionByProduct(product_id);
    }

    @RequestMapping(value = ADD_COMMENT_TO_QUESTION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestionDto AddCommentQuestion(@PathVariable Long question_id,
                                          @PathVariable Long user_id){
        return questionService.AddCommentToQuestion(question_id, user_id);
    }

    @RequestMapping(value = CREATE_QUESTION, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestionDto CreateQuestion(@PathVariable Long product_id,
                                      @PathVariable Long user_id,
                                      @RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                      @RequestPart(value = "question") QuestionEntity questionEntity){
        return questionService.CreateQuestion(product_id, user_id, multipartFile, questionEntity);
    }

    @RequestMapping(value = QUESTION_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestionDto EditQuestion(@PathVariable Long question_id,
                                    @RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                    @RequestPart(value = "question") QuestionEntity questionEntity){
        return questionService.EditQuestion(question_id, multipartFile, questionEntity);
    }

    @RequestMapping(value = QUESTION_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestionDto DeleteQuestion(@PathVariable Long question_id){
        return questionService.DeleteQuestion(question_id);
    }
}
