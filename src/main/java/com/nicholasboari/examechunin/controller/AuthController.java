package com.nicholasboari.examechunin.controller;

import com.nicholasboari.examechunin.domain.User;
import com.nicholasboari.examechunin.requests.Login;
import com.nicholasboari.examechunin.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
    public String login(@RequestBody Login login) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.login(), login.password());
        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var user = (User) authenticate.getPrincipal();
        return tokenService.generateToken(user);
    }

}
