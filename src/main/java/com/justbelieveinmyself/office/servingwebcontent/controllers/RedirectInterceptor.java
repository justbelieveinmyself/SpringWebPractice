package com.justbelieveinmyself.office.servingwebcontent.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

public class RedirectInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String arg = request.getQueryString() != null ? request.getQueryString() : "";
            String url = request.getRequestURL().toString() + "?" + arg;
            response.setHeader("Turbolinks-Location", url);
        }
    }
}