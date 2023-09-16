package com.justbelieveinmyself.office.servingwebcontent.services;

import com.justbelieveinmyself.office.servingwebcontent.domain.Role;
import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import com.justbelieveinmyself.office.servingwebcontent.repos.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
class JpaUserDetailsServiceTest {
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private MailSender mailSender;
    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;
    @Test
    void addUser() {
        User user = new User();

        user.setActive(false);
        user.setEmail("some@mail.ru");
        boolean isUserAdded = jpaUserDetailsService.addUser(user);
        assertTrue(isUserAdded);
        assertTrue(user.isActive());
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        assertNotNull(user.getActivationCode());
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(mailSender, Mockito.times(1)).send(
                ArgumentMatchers.contains(user.getEmail()),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
        );
    }
    @Test
    void addAlreadyExistUser(){
        User user = new User();
        user.setUsername("Vadim");

        Mockito.doReturn(user).when(userRepository).findByUsername("Vadim");

        boolean isUserAdded = jpaUserDetailsService.addUser(user);
        assertFalse(isUserAdded);
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername("Vadim");
        Mockito.verify(userRepository, Mockito.times(0)).save(user);
        Mockito.verify(mailSender, Mockito.times(0)).send(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
        );
    }

    @Test
    void activateUser() {
        User user = new User();
        user.setActivationCode("12345");
        Mockito.doReturn(user).when(userRepository).findByActivationCode("12345");
        boolean isActivated = jpaUserDetailsService.activateUser(user.getActivationCode());
        assertTrue(isActivated);
        assertNull(user.getActivationCode());
        Mockito.verify(userRepository, Mockito.times(1)).findByActivationCode("12345");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);

    }
    @Test
    void activateUserNoSuchActivationCode() {
        String fakeActivationCode = "123";
        Mockito.doReturn(null).when(userRepository).findByActivationCode(fakeActivationCode);
        boolean isActivated = jpaUserDetailsService.activateUser(fakeActivationCode);
        assertFalse(isActivated);
        Mockito.verify(userRepository, Mockito.times(1)).findByActivationCode(fakeActivationCode);
        assertNotNull(fakeActivationCode);
        Mockito.verify(userRepository, Mockito.times(0)).save(
                ArgumentMatchers.any(User.class)
        );

    }
}