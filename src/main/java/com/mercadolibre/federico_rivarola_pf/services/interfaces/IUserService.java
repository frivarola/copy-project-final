package com.mercadolibre.federico_rivarola_pf.services.interfaces;


import com.mercadolibre.federico_rivarola_pf.dtos.UserDTO;
import com.mercadolibre.federico_rivarola_pf.model.Subsidiary;
import com.mercadolibre.federico_rivarola_pf.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity createUser(String username, String pwd, String idSubsidiary);
    UserDTO authUser(String username, String pwd);
    List<User> getAll();
    User getUserByUsername(String username);
    Subsidiary getSubsidiaryByUser(String username);
    Boolean validateCredentials(String username, String password);
}
