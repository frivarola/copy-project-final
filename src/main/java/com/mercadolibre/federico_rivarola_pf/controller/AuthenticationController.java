package com.mercadolibre.federico_rivarola_pf.controller;

import com.mercadolibre.federico_rivarola_pf.dtos.UserDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.requests.CredentialsDTO;
import com.mercadolibre.federico_rivarola_pf.dtos.requests.NewUserDTO;
import com.mercadolibre.federico_rivarola_pf.services.UserService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
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
    public UserDTO login(@RequestBody CredentialsDTO credentials){
        return userService.authUser(credentials.getUsername(), credentials.getPwd());
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody NewUserDTO newUser){
        return userService.createUser(newUser.getUser(), bCryptPasswordEncoder.encode(newUser.getPassword()), newUser.getIdSubsidiary());
    }



}
