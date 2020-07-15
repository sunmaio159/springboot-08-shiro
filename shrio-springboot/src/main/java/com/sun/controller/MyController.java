package com.sun.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @RequestMapping({"/","index"})
    public String toIndex(Model model){
        model.addAttribute("msg","hello shrio");
        return "index";
    }

    @RequestMapping({"/user/add"})
    public String add(Model model){
        model.addAttribute("msg","hello shrio");
        return "/user/add";
    }

    @RequestMapping({"/user/update"})
    public String update(Model model){
        model.addAttribute("msg","hello shrio");
        return "/user/update";
    }

    @RequestMapping({"/toLogin"})
    public String toLogin(Model model){
        model.addAttribute("msg","hello shrio");
        return "login";
    }

    @RequestMapping({"/login"})
    public String login(String username,String password,Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try{
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }

    @RequestMapping({"/unauthorizedUrl"})
    @ResponseBody
    public String unauthorizedUrl(){
        return "未授权页面";
    }
}
