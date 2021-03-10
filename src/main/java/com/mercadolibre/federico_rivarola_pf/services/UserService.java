package com.mercadolibre.federico_rivarola_pf.services;

import com.mercadolibre.federico_rivarola_pf.model.Subsidiary;
import com.mercadolibre.federico_rivarola_pf.model.User;
import com.mercadolibre.federico_rivarola_pf.repositories.IUserRepository;
import com.mercadolibre.federico_rivarola_pf.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUser(username);
    }

    @Override
    public Subsidiary getSubsidiaryByUser(String username) {
        return userRepository.getSubsidiary(username);
    }

    @Override
    public Boolean validateCredentials(String username, String password) throws ResponseStatusException {
        User u = userRepository.findUser(username);
        if (u != null) {
            if (u.getPassword().equals(password)) {
                return true;
            }
        }
        //invalid credentials for pwd incorrect, user not exist, etc..
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }


}
