package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.City;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Spring Data SQL repository for the City entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    @Query("select c.cityName from City c JOIN c.chanells ch " +
        "JOIN ch.categoryIds cat" + " where ch.cityEntity is not null and size(cat.chanellIds) > 0")
    Set<String> getCitiesNames();

    @Query("select c from City c JOIN c.chanells ch JOIN ch.categoryIds cat where SUBSTRING(c.cityName, 1, 1) = :firstLetter and size(cat.chanellIds) > 0")
    Set<City> getCitiesByFirstLetter(@Param("firstLetter") String firstLetter);
}
