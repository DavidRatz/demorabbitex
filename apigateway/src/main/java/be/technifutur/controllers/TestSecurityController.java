package be.technifutur.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class TestSecurityController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/authenticated")
    public String getConnected(){
        return "connecté";
    }
}
