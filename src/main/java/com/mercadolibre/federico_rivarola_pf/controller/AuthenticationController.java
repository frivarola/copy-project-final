package com.mercadolibre.federico_rivarola_pf.controller;

import com.mercadolibre.federico_rivarola_pf.dtos.UserDTO;
import com.mercadolibre.federico_rivarola_pf.services.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/v1/user")
public class AuthenticationController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthenticationController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public UserDTO login(@RequestParam("user") String username, @RequestParam("password") String pwd){
        return userService.authUser(username, bCryptPasswordEncoder.encode(pwd));
    }

    @PostMapping("/create")
    public UserDTO create(@RequestParam("user") String username, @RequestParam("password") String pwd){
        return userService.authUser(username, bCryptPasswordEncoder.encode(pwd));
    }



}
