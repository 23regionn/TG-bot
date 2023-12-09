package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.City;
import com.mycompany.myapp.domain.RelCategoryCity;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RelCategoryCity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelCategoryCityRepository extends JpaRepository<RelCategoryCity, Long> {
    List<RelCategoryCity> getAllByCity(City city);

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.relCategoryCity" +
        ".RelCategoryCityInfoDTO(rel.id, rel.category.name, rel.city.cityName) " +
        "FROM RelCategoryCity rel where rel.id = :id"
    )
    com.mycompany.myapp.service.dto.relCategoryCity.RelCategoryCityInfoDTO getInfoDTOById(@Param("id") Long id);

    Boolean existsByCategoryAndCity(Category category, City city);
}
