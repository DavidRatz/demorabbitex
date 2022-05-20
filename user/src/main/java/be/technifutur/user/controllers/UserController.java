package be.technifutur.user.controllers;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import be.technifutur.sharedclass.dtos.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

    private final PasswordEncoder encoder;
    
    public UserController(PasswordEncoder encoder){
        this.encoder=encoder;
    }
    

    @GetMapping
    public Object getByUsername(@RequestParam String username){
        return new UserDTO(username, encoder.encode("pass"), List.of("USER"));
    }
}