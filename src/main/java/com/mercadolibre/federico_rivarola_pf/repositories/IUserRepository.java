package com.mercadolibre.federico_rivarola_pf.repositories;

import com.mercadolibre.federico_rivarola_pf.model.Subsidiary;
import com.mercadolibre.federico_rivarola_pf.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u where u.username = :username")
    User findUser(@Param("username")String username);

    @Query("SELECT u.subsidiary FROM User u where u.username = :username")
    Subsidiary getSubsidiary(@Param("username")String username);

}
