package com.justbelieveinmyself.office.servingwebcontent.controllers;

import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import com.justbelieveinmyself.office.servingwebcontent.domain.dto.CaptchaResponseDto;
import com.justbelieveinmyself.office.servingwebcontent.services.JpaUserDetailsService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
@Controller
public class RegisterController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Value("${recaptcha.secret}")
    private String secret;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JpaUserDetailsService jpaUserDetailsService;

    @GetMapping("/registration")
    public String register(){
        return "registration";
    }
    @PostMapping("/registration")
    public String registerNewUser(@RequestParam(name = "password2") String passwordConfirm,
                                  @RequestParam(name = "g-recaptcha-response") String captchaResponse,
                                  @Valid User user, BindingResult bindingResult, Model model){
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.EMPTY_LIST, CaptchaResponseDto.class);// also may to put map
        if(!response.isSuccess()){
            model.addAttribute("captchaError", "Fill captcha!");
        }
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if(isConfirmEmpty){
            model.addAttribute("password2Error", "Password confirmation can't be empty");
        }
        boolean isPasswordsDifferent = !Objects.equals(user.getPassword(), passwordConfirm);
        if(isPasswordsDifferent){
            model.addAttribute("passwordError", "Passwords are different!");
        }
        if(bindingResult.hasErrors() || isConfirmEmpty || isPasswordsDifferent || !response.isSuccess()){
            Map<String, String> errorsMap = ControllerUtil.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);
                return "registration";
        }
        if(!jpaUserDetailsService.addUser(user)){
            model.addAttribute("usernameError", "User with that name already exists!");
            return "registration";
        }
        return "redirect:/login";
    }
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = jpaUserDetailsService.activateUser(code);
        if (isActivated){
            model.addAttribute("message", "User successfully activated.");
        }else{
            model.addAttribute("message", "Activation code is not found!");
        }
        return "login";
    }
}
