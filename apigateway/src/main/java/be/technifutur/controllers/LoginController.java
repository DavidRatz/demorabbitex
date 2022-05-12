package be.technifutur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.technifutur.models.form.LoginForm;
import be.technifutur.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService service;
    @PostMapping
    public String login(@RequestBody LoginForm form){
        return service.login(form);
    }
}
