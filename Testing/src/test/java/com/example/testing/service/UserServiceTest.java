package com.example.testing.service;

import com.example.testing.model.User;
import com.example.testing.repository.UserRepository;
import com.example.testing.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.*;

import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.GeneratedValue;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
//@RequiredArgsConstructor
public class UserServiceTest {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

//    @Mock
//    private User user;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

//    @Rule
//    public MockitoRule initRule = MockitoJUnit.rule();


//    private static ValidatorFactory validatorFactory;
//    private static Validator validator;

    @BeforeAll
    public void beforall(){
        System.out.println("adasdsad");
    }

//    @Nested
    @BeforeEach
    public void beforeEach(){
        System.out.println(ANSI_RESET +"BeforeEach:");

        MockitoAnnotations.initMocks(this);
    }

//    public UserServiceTest(){
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void isNameValid(){
        String name = "aaaaaaaaaa";
        int age = 19;
        String phone = "+37443434343";
        String code = phone.substring(0,4);
        User user = User.builder()
                .name(name)
                .age(age)
                .phone(phone)
                .build();
        userService.add(user);
        Assertions.assertFalse(name.length()>12);
        System.out.println(ANSI_GREEN + "name length less than 12");
        Assertions.assertFalse(age<18);
        Assertions.assertTrue(code.equals("+374"));
        System.out.println(ANSI_GREEN + "Armenian number");
        System.out.println(ANSI_GREEN + "age is more than 18");
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaaaa","bbbbbb"} )
//    @EnabledOnOs(value = OS.MAC, disabledReason = "Enabled only on Macs")
    public void shouldCreateUser(String name){
        User user = User.builder()
                .name(name)
                .age(35)
                .phone("+37443434343")
                .build();
//        doNothing().when(userService).add(user);
        userService.add(user);

//        Assertions.assertSame(userService.add(user).getStatusCode(), HttpStatus.CREATED);
//        Assertions.assertFalse(userService.getAll().isEmpty());
//            Assertions.assertEquals(2,userService.getAll().size());
        System.out.println(ANSI_GREEN + "user saved");
    }

    @Test
    @EnabledOnOs(value = OS.MAC, disabledReason = "Enabled only on Macs")
    public void shouldUpdateUser(){
        long id = 138L;
        User user = User.builder()
                .id(id)
                .name("Poxos")
                .age(35)
                .phone("+37443434343")
                .build();
//        Assertions.assertTrue(userService.getAll().isEmpty());
        doNothing().when(userService).add(user);
        userService.add(user);
//        Assertions.assertSame(userService.add(user).getStatusCode(), HttpStatus.OK);

//        Assertions.assertSame(save, ResponseEntity.status(HttpStatus.CREATED).build());
//        Assertions.assertSame(save, ResponseEntity.status(HttpStatus.CREATED).build());
//        Assertions.assertNotEquals(userService.add(user), ResponseEntity.status(HttpStatus.CREATED).build());
//        Assertions.assertEquals(2,userService.getAll().size());
        System.out.println(ANSI_GREEN +"user updated");
    }

    @Test
    public void userSaveTest(){
        User user = User.builder()
                .name("Petros")
                .age(35)
                .phone("+37443434343")
                .build();

        when(userRepository.save(any(User.class))).thenReturn(user);
//        doNothing().when(userService).saveUser(user);
        userRepository.save(user);
        verify(userRepository,times(1)).save(user);


    }

    @Test
    public boolean isExistById(){
        User user = User.builder()
                .id(1L)
                .name("Petros")
                .age(35)
                .phone("+37443434343")
                .build();
//        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Assertions.assertEquals(true, userRepository.existsById(1L));
        System.out.println("Exist");
        return userRepository.existsById(1L);
    }

    @Test
    public void shouldDeleteUser(){
        long id = 136L;
        Assertions.assertFalse(userRepository.existsById(id));
        User user = userService.findById(id);
//        Assertions.assertFalse(userService.getAll().isEmpty());
        when(userService.deleteUser(user)).thenReturn(ResponseEntity.status(HttpStatus.OK).build());
        Assertions.assertSame(userService.deleteUser(user).getStatusCode(), HttpStatus.OK);
        userService.deleteUser(user);
        System.err.println("User deleted");
    }

    @Test
    public void shouldReturnUserById() throws Exception{
        User user = User.builder()
                .id(1L)
                .name("Pualos")
                .age(23)
                .phone("+37499966933")
                .build();
        given(userService.findPhoneByName("Pualos")).willReturn(
                user.getPhone()
        );
//         isExistById();
        Assertions.assertSame(userService.findPhoneByName("Pualos"),user.getPhone());
        System.out.println(ANSI_GREEN + userService.findPhoneByName("Pualos"));
    }


    @AfterEach
    public void afterEach(){
        System.out.println(ANSI_RESET + "AfterEach:");
    }
}
