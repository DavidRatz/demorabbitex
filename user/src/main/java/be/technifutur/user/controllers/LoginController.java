package be.technifutur.user.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import be.technifutur.sharedclass.dtos.UserDTO;
import be.technifutur.sharedclass.forms.LoginForm;
import be.technifutur.user.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService service;
    
    public LoginController(LoginService service){
        this.service=service;
    }
    
    @PostMapping
    public String login(@RequestBody LoginForm form){
        return service.login(form);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(headers = "Authorization")
    public UserDTO validate(Authentication auth){
        return new UserDTO((String)auth.getPrincipal(),
                null,
                        auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList());
    }
}
