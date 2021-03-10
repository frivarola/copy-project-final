package com.mercadolibre.federico_rivarola_pf.services.interfaces;


import com.mercadolibre.federico_rivarola_pf.model.Subsidiary;
import com.mercadolibre.federico_rivarola_pf.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    User getUserByUsername(String username);
    Subsidiary getSubsidiaryByUser(String username);
    Boolean validateCredentials(String username, String password);
}
