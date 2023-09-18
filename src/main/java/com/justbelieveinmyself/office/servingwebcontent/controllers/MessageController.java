package com.justbelieveinmyself.office.servingwebcontent.controllers;

import com.justbelieveinmyself.office.servingwebcontent.domain.Message;
import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import com.justbelieveinmyself.office.servingwebcontent.domain.dto.MessageDto;
import com.justbelieveinmyself.office.servingwebcontent.services.MessageService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping("/main")
    public String main(
            @AuthenticationPrincipal User currentUser
            ,@RequestParam(required = false) String filter
            , Map<String, Object> model
            , @PageableDefault(sort = {"time"}, direction = Sort.Direction.DESC, size = 12) Pageable pageable){
        Page<MessageDto> page = messageService.getMessageList(currentUser, filter, pageable);
        model.put("page", page);
        model.put("url", "/main");
        model.put("filter", filter);
        return "main";
    }
    @PostMapping("/main")
    public String addMessage(@AuthenticationPrincipal User currentUser
            , @Valid Message message
            , BindingResult bindingResult
            , Model model
            , @RequestParam("file") MultipartFile file
            , @PageableDefault(sort = {"time"}, direction = Sort.Direction.DESC, size = 12) Pageable pageable) throws IOException {
        message.setAuthor(currentUser);
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtil.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        }else {
            ControllerUtil.saveFile(message, file);
            model.addAttribute("message", null);
            message.setTime(LocalDateTime.now());
            messageService.save(message);
        }
        Page<MessageDto> messages = messageService.getMessageList(currentUser, null, pageable);
        model.addAttribute("page", messages);
        model.addAttribute("url", "/main");
        return "main";
    }
    @GetMapping("/user-messages/{author}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser
            , @PathVariable User author
            , Model model
            , @RequestParam(required = false) Message message
            , @PageableDefault(sort = {"time"}, direction = Sort.Direction.DESC, size = 12) Pageable pageable){
        Page<MessageDto> page = messageService.findByAuthor(currentUser, author, pageable);
        model.addAttribute("userChannel", author);
        model.addAttribute("subscriptionsCount", author.getSubscriptions().size());
        model.addAttribute("subscribersCount", author.getSubscribers().size());
        model.addAttribute("isSubscriber", author.getSubscribers().contains(currentUser));
        model.addAttribute("page", page);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(author));
        model.addAttribute("url", "/user-messages/"+author.getId());
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
                ControllerUtil.saveFile(message, file);
            }
            message.setTime(LocalDateTime.now());
            messageService.save(message);
        }
        return "redirect:/user-messages/" + user;
    }
    @GetMapping("/messages/{message}/like")
    public String like(
            @AuthenticationPrincipal User user,
            @PathVariable Message message,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer
    ){
        Set<User> likes = message.getLikes();
        if(likes.contains(user)){
            likes.remove(user);
        }else{
            likes.add(user);
        }
        messageService.save(message);
        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();
        components.getQueryParams().entrySet().forEach(pair -> redirectAttributes.addAttribute(pair.getKey(), pair.getValue()));

        return "redirect:" + components.getPath();
    }
}
