package com.justbelieveinmyself.office.servingwebcontent.controllers;

import com.justbelieveinmyself.office.servingwebcontent.domain.Message;
import com.justbelieveinmyself.office.servingwebcontent.domain.Role;
import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import com.justbelieveinmyself.office.servingwebcontent.repos.MessageRepository;
import com.justbelieveinmyself.office.servingwebcontent.repos.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private MessageRepository messageRepository;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter
            , Map<String, Object> model
            , @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        Page<Message> page = (filter != null && !filter.isEmpty())? messageRepository.findByTag(filter, pageable) : messageRepository.findAll(pageable);
        model.put("page", page);
        model.put("url", "/main");
        model.put("filter", filter);
        return "main";
    }
    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User user
            , @Valid Message message
            , BindingResult bindingResult
            , Model model
            , @RequestParam("file") MultipartFile file
            , @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) throws IOException {
        message.setAuthor(user);
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtil.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        }else {
            saveFile(message, file);
            model.addAttribute("message", null);
            messageRepository.save(message);
        }
        Page<Message> messages = messageRepository.findAll(pageable);
        model.addAttribute("page", messages);
        model.addAttribute("url", "/main");
        return "main";
    }

    private void saveFile(Message message, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            message.setFilename(resultFilename);
        }
    }


    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @GetMapping
    public String home(){
        return "home";
    }
    @GetMapping("/user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser
            , @PathVariable User user
            , Model model
            , @RequestParam(required = false) Message message
            , @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        Page<Message> page = messageRepository.findByAuthor(user, pageable);
        model.addAttribute("userChannel", user);
        model.addAttribute("subscriptionsCount", user.getSubscriptions().size());
        model.addAttribute("subscribersCount", user.getSubscribers().size());
        model.addAttribute("isSubscriber", user.getSubscribers().contains(currentUser));
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("url", "/user-messages/"+user.getId());
        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String editMessage(
            @AuthenticationPrincipal User currentUser
            , @PathVariable Long user
            , @RequestParam("id") Message message
            , @RequestParam("text") String text
            , @RequestParam("tag") String tag
            , @RequestParam("file") MultipartFile file
    ) throws IOException {
        if(message.getAuthor().equals(currentUser)){
            if(!StringUtils.isEmpty(text)){
                message.setText(text);
            }
            if(!StringUtils.isEmpty(tag)){
                message.setTag(tag);
            }

            if(!file.isEmpty()) {
                saveFile(message, file);
            }
            messageRepository.save(message);
        }
        return "redirect:/user-messages/" + user;
    }

}
