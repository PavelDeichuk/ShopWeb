package com.pavel.shopweb.Service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pavel.shopweb.Dto.UsersDto;
import com.pavel.shopweb.Entity.UsersEntity;
import com.pavel.shopweb.Repository.UsersRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import com.pavel.shopweb.Service.EmailService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UsersServiceImplTest {
    @Mock
    private UsersRepository usersRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UsersServiceImpl usersServiceImpl;

    @MockBean
    private BindingResult bindingResult;

    @Test
    public void GetAllUsersTest() {
        List<UsersEntity> usersEntityList = new ArrayList<>();
        usersEntityList.add(null);
        PageImpl<UsersEntity> pageImpl = new PageImpl<>(usersEntityList);
        when(usersRepository.findAll((Pageable) any())).thenReturn(pageImpl);
        assertEquals(1, usersServiceImpl.GetAllUsers(1, 3).size());
        verify(usersRepository).findAll((Pageable) any());
    }

    @Test
    public void GetUserByIdTest() {
        UsersEntity usersEntity = UsersEntity.builder().id(1L).build();
        when(usersRepository.findById(1L)).thenReturn(Optional.of(usersEntity));
        UsersDto usersDto = usersServiceImpl.GetUserById(1L);
        assertEquals(usersDto.getId(), usersEntity.getId());
        verify(usersRepository).findById(1L);
    }

    @Test
    public void CreateUserTest() {
       UsersEntity users = UsersEntity.builder().id(1L).mfa(false).build();
       when(usersRepository.save(users)).thenReturn(users);
       when(emailService.SendMessage("test","test","test")).thenReturn(false);
       UsersDto usersDto = usersServiceImpl.CreateUser(users, bindingResult);
       assertEquals(usersDto.getId(), users.getId());
    }

    @Test
    public void ActivateUserTest() {
        UsersEntity users = UsersEntity.builder().id(1L).build();
        when(usersRepository.findByActivation("test")).thenReturn(Optional.of(users));
        UsersDto usersDto = usersServiceImpl.ActivateUser("test");
        assertEquals(usersDto.getId(), users.getId());
        verify(usersRepository).findByActivation("test");
    }

    @Test
    public void ResetPasswordTest(){
        UsersEntity users = UsersEntity.builder().id(1L).build();
        when(usersRepository.findByEmail("test")).thenReturn(Optional.of(users));
        UsersDto usersDto = usersServiceImpl.ResetPassword("test");
        assertEquals(usersDto.getId(), users.getId());
        verify(usersRepository).findByEmail("test");
    }

    @Test
    public void ChangePasswordTest(){
        UsersEntity users = UsersEntity.builder().id(1L).password("test").password2("test").build();
        when(usersRepository.findByResetToken("test")).thenReturn(Optional.of(users));
        UsersDto usersDto = usersServiceImpl.ChangePassword("test", users);
        assertEquals(usersDto.getId(), users.getId());
        verify(usersRepository).findByResetToken("test");
    }

    @Test
    public void EditUserTest(){
        UsersEntity users = UsersEntity.builder().id(1L).username("test").build();
        when(usersRepository.save(users)).thenReturn(users);
        when(usersRepository.findById(1L)).thenReturn(Optional.of(users));
        UsersDto usersDto = usersServiceImpl.EditUser(users.getId(), users);
        assertEquals(usersDto.getId(), users.getId());
        verify(usersRepository).save(users);
    }

    @Test
    public void UsernameIsNullValidationTest(){
        UsersEntity users = UsersEntity.builder().username(null).email("test@mail.ru").password("Testasdasda2*").password2("Testasdasda2*").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(users);
        ConstraintViolation<UsersEntity> violation = violations.stream().findFirst().orElseThrow(() -> new RuntimeException("Отсутствует ошибка валидации"));
        assertEquals("Username cannot be null!", violation.getMessageTemplate());
    }

    @Test
    public void UsernameSizeMinTest(){
        UsersEntity users = UsersEntity.builder().username("a").email("test@mail.ru").password("Testasdasda2*").password2("Testasdasda2*").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(users);
        ConstraintViolation<UsersEntity> violation = violations.stream().findFirst().orElseThrow(() -> new RuntimeException("Отсутствует ошибка валидации"));
        assertEquals("Size username is min 3 max 28!", violation.getMessageTemplate());
    }

    @Test
    public void UsernameSizeMaxTest(){
        UsersEntity users = UsersEntity.builder().username("ajksdhjkashdjkashdjkhasjdhjadasdaeqw@1312312").email("test@mail.ru").password("Testasdasda2*").password2("Testasdasda2*").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(users);
        ConstraintViolation<UsersEntity> violation = violations.stream().findFirst().orElseThrow(() -> new RuntimeException("Отсутствует ошибка валидации"));
        assertEquals("Size username is min 3 max 28!", violation.getMessageTemplate());
    }

    @Test
    public void PasswordIsNullValidationTest(){
        UsersEntity users = UsersEntity.builder().username("test").email("test@mail.ru").password(null).password2("Test1321adas*").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(users);
        ConstraintViolation<UsersEntity> violation = violations.stream().findFirst().orElseThrow(() -> new RuntimeException("Отсутствует ошибка валидации"));
        assertEquals("Password cannot be null!", violation.getMessageTemplate());
    }

    @Test
    public void PasswordPatternTest(){
        UsersEntity users = UsersEntity.builder().username("test").email("test@mail.ru").password("Test123121").password2("Test17327137127*").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(users);
        ConstraintViolation<UsersEntity> violation = violations.stream().findFirst().orElseThrow(() -> new RuntimeException("Отсутствует ошибка валидации"));
        assertEquals("Min 1 uppercase letter.\n" +
                "Min 1 lowercase letter\n" +
                "Min 1 special character.\n" +
                "Min 1 number.\n" +
                "Min 8 characters.",violation.getMessageTemplate());
    }

    @Test
    public void EmailIsNullValidationTest(){
        UsersEntity users = UsersEntity.builder().username("test").email(null).password("Testingasdas1*").password2("Testingasdas1*").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(users);
        ConstraintViolation<UsersEntity> violation = violations.stream().findFirst().orElseThrow(() -> new RuntimeException("Отсутствует ошибка валидации"));
        assertEquals("Email cannot be null!", violation.getMessageTemplate());
    }

    @Test
    public void EmailPatternTest(){
        UsersEntity users = UsersEntity.builder().username("test").email("testasd").password("Testingasdas1*").password2("Testingasdas1*").build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UsersEntity>> violations = validator.validate(users);
        ConstraintViolation<UsersEntity> violation = violations.stream().findFirst().orElseThrow(() -> new RuntimeException("Отсутствует ошибка валидации"));
        assertEquals("Error validation email!", violation.getMessageTemplate());
    }

}