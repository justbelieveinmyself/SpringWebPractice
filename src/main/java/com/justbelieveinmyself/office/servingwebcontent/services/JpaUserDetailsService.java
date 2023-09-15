package com.justbelieveinmyself.office.servingwebcontent.service;

import com.justbelieveinmyself.office.servingwebcontent.domain.Role;
import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import com.justbelieveinmyself.office.servingwebcontent.repos.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final MailSender mailSender;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Value("${site.hostname}")
    private String hostname;


    public JpaUserDetailsService(UserRepository userRepository, MailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(8);
    }


    public boolean addUser(User user){
        User users = userRepository.findByUsername(user.getUsername());
        if(users != null){
            return false;
        }
        user.setActive(true);
        user.setTime(LocalDateTime.now());
        HashSet<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        sendActivationMessage(user);
        return true;
    }

    private void sendActivationMessage(User user) {
        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format("Hello, %s! \n" + "Welcome to Office. Please, visit next link: %s/activate/%s"
                    , user.getUsername(), hostname, user.getActivationCode());
            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);
        return true;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = !Objects.equals(email, userEmail);
        if(isEmailChanged){
            user.setEmail(email);
            if(!StringUtils.isEmpty(email)){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if(!StringUtils.isEmpty(password)){
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(user);
        if(isEmailChanged) {
            sendActivationMessage(user);
        }
    }

    public void subscribe(User currentUser, User user) {
        user.getSubscribers().add(currentUser);
        userRepository.save(user);
    }

    public void unsubscribe(User currentUser, User user) {
        user.getSubscribers().remove(currentUser);
        userRepository.save(user);
    }
}
