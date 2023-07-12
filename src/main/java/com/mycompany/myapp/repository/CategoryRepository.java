package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Category;
import java.util.List;
import java.util.Optional;

import com.mycompany.myapp.service.dto.CategoryNameAndIdDTO;
import com.mycompany.myapp.service.dto.CategoryWithCountChanellsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Category entity.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(
        value = "select distinct category from Category category left join fetch category.chanellIds left join fetch category.linksByCategoryInTopIds",
        countQuery = "select count(distinct category) from Category category"
    )
    Page<Category> findAllWithEagerRelationships(Pageable pageable);

    @Query(
        "select distinct category from Category category left join fetch category.chanellIds left join fetch category.linksByCategoryInTopIds"
    )
    List<Category> findAllWithEagerRelationships();

    @Query(
        "select category from Category category left join fetch category.chanellIds left join fetch category.linksByCategoryInTopIds where category.id =:id"
    )
    Optional<Category> findOneWithEagerRelationships(@Param("id") Long id);

    @Query( "select category from Category category where size(category.chanellIds) > 0 " +
        "   and category.boolean1 = true   and  category.string1 =:city")
    List<CategoryWithCountChanellsDTO> findCategoriesHaveChanellsAndBool1True(@Param("city") String city);

    @Query( "select category from Category category where size(category.chanellIds) > 0 " +
        "   and category.boolean1 = true")
    List<CategoryWithCountChanellsDTO> findCategoriesHaveChanellsAndBool1True();

}
