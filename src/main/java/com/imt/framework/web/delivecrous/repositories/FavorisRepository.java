package com.imt.framework.web.delivecrous.repositories;

import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.entities.Favoris;
import com.imt.framework.web.delivecrous.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavorisRepository extends JpaRepository<Favoris, Long> {
    List<Favoris> findByUser(Users user);

    Favoris findByUserAndPlat(Users user, Dish plat);

}