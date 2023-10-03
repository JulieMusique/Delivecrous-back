// Source code is decompiled from a .class file using FernFlower decompiler.
package com.imt.framework.web.delivecrous.repositories;

import com.imt.framework.web.delivecrous.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
   @Query("select l from User l where l.id = :identifiant")
   List<User> getUserWithIdFilter(@Param("identifiant") Long identifiant);
}
