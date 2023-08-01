package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.City;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Spring Data SQL repository for the City entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    /*@Query("select c.city from Chanell c JOIN c.categoryIds cat" + " where c.city is not null and size(cat.chanellIds) > 0")
    Set<String> getCitiesNames();*/
}
