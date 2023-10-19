package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.City;
import com.mycompany.myapp.domain.RelCategoryCity;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RelCategoryCity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelCategoryCityRepository extends JpaRepository<RelCategoryCity, Long> {
    List<RelCategoryCity> getAllByCity(City city);
}
