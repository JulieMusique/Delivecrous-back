package com.imt.framework.web.delivecrous.repositories;

import com.imt.framework.web.delivecrous.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value="select * from users l where l.id = :identifiant",nativeQuery = true)
    List<User> getUserWithIdFilter(@Param("identifiant") Long identifiant);

    @Query(value="SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM users u WHERE u.email = :email", nativeQuery = true)
    boolean existsByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM users u WHERE u.email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

}