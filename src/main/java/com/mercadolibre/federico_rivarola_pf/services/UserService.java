package com.mercadolibre.federico_rivarola_pf.services;

import com.mercadolibre.federico_rivarola_pf.dtos.UserDTO;
import com.mercadolibre.federico_rivarola_pf.model.Subsidiary;
import com.mercadolibre.federico_rivarola_pf.model.User;
import com.mercadolibre.federico_rivarola_pf.repositories.ISubsidiaryRepository;
import com.mercadolibre.federico_rivarola_pf.repositories.IUserRepository;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for user management
 *
 * @author frivarola
 */
@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final ISubsidiaryRepository subsidiaryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(IUserRepository userRepository, ISubsidiaryRepository subsidiaryRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.subsidiaryRepository = subsidiaryRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * method for create user
     *
     * @param username
     * @param pwd
     * @return
     * @throws ResponseStatusException
     */
    @Override
    public ResponseEntity createUser(String username, String pwd, String idSubsidiary) throws ResponseStatusException {
        User u = new User();
        u.setUsername(username);
        u.setPassword(pwd);
        u.setSubsidiary(subsidiaryRepository.findById(idSubsidiary));

        if (u.getSubsidiary() != null) {
            userRepository.save(u);
            return new ResponseEntity<>("Se creo el usuario con exito.", HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid subsidiary");
        }

    }

    /**
     * method for authenticate user
     *
     * @param username
     * @param pwd
     * @return UserDTO with JWToken
     * @throws ResponseStatusException
     */
    @Override
    public UserDTO authUser(String username, String pwd) throws ResponseStatusException {
        UserDTO userDTO = null;

        if (validateCredentials(username, pwd)) {
            userDTO = new UserDTO();
            String token = getJWTToken(username);
            userDTO.setUsername(username);
            userDTO.setToken(token);
        }
        return userDTO;
    }

    /**
     * get all users on users table
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * get user by username
     *
     * @param username
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUser(username);
    }

    /**
     * get subsidiary by user
     *
     * @param username
     * @return
     */
    @Override
    public Subsidiary getSubsidiaryByUser(String username) {
        return userRepository.getSubsidiary(username);
    }

    /**
     * function for validate auth credentials
     *
     * @param username
     * @param password
     * @return
     * @throws ResponseStatusException
     */
    @Override
    public Boolean validateCredentials(String username, String password) throws ResponseStatusException {
        User u = userRepository.findUser(username);
        if (u != null) {
            if (bCryptPasswordEncoder.matches(password, u.getPassword())) {
                return true;
            }
        }
        //invalid credentials for pwd incorrect, user not exist, etc..
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }

    /**
     * method for create JWTToken
     *
     * @param username
     * @return
     */
    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("frivarolaJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }


}
