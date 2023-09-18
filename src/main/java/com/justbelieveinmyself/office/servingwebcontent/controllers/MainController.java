package com.justbelieveinmyself.office.servingwebcontent.controllers;

import com.justbelieveinmyself.office.servingwebcontent.domain.Message;
import com.justbelieveinmyself.office.servingwebcontent.domain.Role;
import com.justbelieveinmyself.office.servingwebcontent.domain.User;
import com.justbelieveinmyself.office.servingwebcontent.domain.dto.MessageDto;
import com.justbelieveinmyself.office.servingwebcontent.repos.MessageRepository;
import com.justbelieveinmyself.office.servingwebcontent.repos.UserRepository;
import com.justbelieveinmyself.office.servingwebcontent.services.MessageService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class MainController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    @GetMapping
    public String home(){
        return "home";
    }
}
