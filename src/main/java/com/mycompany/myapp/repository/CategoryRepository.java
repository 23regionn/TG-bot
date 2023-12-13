package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.service.dto.CategoryNameAndIdDTO;
import com.mycompany.myapp.service.dto.CategoryWithCountChanellsDTO;
import com.mycompany.myapp.service.dto.category_name.CategoryAndNameDTO;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data SQL repository for the Category entity.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /*@Query(
        value = "select distinct category from Category category left join fetch category.chanellIds left join fetch category.linksByCategoryInTopIds",
        countQuery = "select count(distinct category) from Category category"
    )
    Page<Category> findAllWithEagerRelationships(Pageable pageable);*/

    @Query("select distinct category from Category category left join fetch category.linksByCategoryInTopIds")
    List<Category> findAllWithEagerRelationships();

    @Query("SELECT category FROM Category category " + "WHERE category.id = :id ")
    Optional<Category> findOneWithEagerRelationships(@Param("id") Long id);

    /** Запросы для логики бота */
    // Запрос используется в боте для получения списка всех категорий к отображению (в режиме инлайн и при листалке)
    @Query(
        "SELECT DISTINCT c FROM Category c JOIN c.relCategoryChannels rcc  JOIN rcc.chanell ch WHERE c.isShow = true " +
        "AND rcc.isShowChannel = true and rcc.scoreChannel is not null and ch.isModerate = true and ch.endPublicDate > :currentDate " +
        "AND c.score is not null and c.isShow is not null and c.isFirst is not null " +
        "ORDER BY c.isFirst DESC, c.score DESC"
    )
    List<CategoryNameAndIdDTO> findCategoriesForSearchMethodsBot(@Param("currentDate") ZonedDateTime currentDate);

    // Запрос нужен для получения Количества каналов в конкретной категории
    @Query(
        "SELECT new com.mycompany.myapp.service.dto.CategoryWithCountChanellsDTO(c, COUNT(rcc)) " +
        "FROM Category c JOIN c.relCategoryChannels rcc  JOIN rcc.chanell ch  " +
        "WHERE c.id = :id " +
        "AND rcc.isShowChannel = true and rcc.scoreChannel is not null and ch.isModerate = true and ch.endPublicDate > :currentDate " +
        "GROUP BY c"
    )
    com.mycompany.myapp.service.dto.CategoryWithCountChanellsDTO findCategoryByIdWithoutCity(
        @Param("id") Long id,
        @Param("currentDate") ZonedDateTime currentDate
    );

    // тест для инлайн режима
    @Query("select c from Category c")
    List<CategoryNameAndIdDTO> findAllNames();

    //Запрос выдает список категорий по конкретному городу
    @Query(
        "select DISTINCT new com.mycompany.myapp.service.dto.CategoryNameAndIdDTO(relCatCit.id, cat.name, relCatCit.score, relCatCit.isFirst )" +
        " from Category cat join cat.relCategoryCities relCatCit join relCatCit.city city " +
        " join relCatCit.relCategoryCityChannels relCCCh join relCCCh.chanell ch" +
        " where city.id = :cityId " +
        " and ch.isModerate = true and ch.endPublicDate > :currentDate " +
        " and relCCCh.size > 0 and relCCCh.isShowChannel = true " +
        " and relCatCit.isShow = true "
    )
    List<CategoryNameAndIdDTO> findCategoriesByCityIdNew(@Param("cityId") Long cityId, @Param("currentDate") ZonedDateTime currentDate);

    // Запрос на получение категории и города по релсу
    @Query(
        "SELECT new com.mycompany.myapp.service.dto.category_name.CategoryAndNameDTO(c.id, c.name, city.id, city.cityName) " +
        " from Category c join c.relCategoryCities rc join rc.city city " +
        " where rc.id = :relCategoryCityId"
    )
    Optional<CategoryAndNameDTO> findByRelCategoryCitiesId(@Param("relCategoryCityId") Long relCategoryCityId);
}
