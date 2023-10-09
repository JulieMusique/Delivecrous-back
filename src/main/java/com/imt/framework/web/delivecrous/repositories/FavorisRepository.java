package com.imt.framework.web.delivecrous.repositories;

import com.imt.framework.web.delivecrous.entities.Dish;
import com.imt.framework.web.delivecrous.entities.Favoris;
import com.imt.framework.web.delivecrous.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface FavorisRepository extends JpaRepository<Favoris, Long> {
    List<Favoris> findByUser(User user);
    Favoris findByUserAndPlat(User user, Dish plat);

}
