package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.service.dto.CategoryNameAndIdDTO;
import com.mycompany.myapp.service.dto.CategoryWithCountChanellsDTO;
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
        "SELECT category FROM Category category LEFT JOIN FETCH category.chanellIds chan LEFT JOIN FETCH chan.cityEntity ce " +
        "WHERE category.id = :id AND " +
        " (chan.cityEntity IS NULL )  and chan.endPublicDate > :currentDate "
    )
    Optional<Category> findOneWithEagerRelationships(@Param("id") Long id, @Param("currentDate") ZonedDateTime currentDate);

    /*    @Query(
        "SELECT category FROM Category category LEFT JOIN FETCH category.chanellIds chan WHERE category.id = :id AND" +
            " (chan.city = :city)"
    )
    Optional<Category> findOneWithEagerRelationships(@Param("id") Long id, @Param("city") String city);*/

    @Query(
        "SELECT category FROM Category category LEFT JOIN FETCH category.chanellIds chan LEFT JOIN FETCH chan.cityEntity ce " +
        "WHERE category.id = :id AND" +
        " (ce.id = :cityId)   and chan.endPublicDate > :currentDate "
    )
    Optional<Category> findOneWithEagerRelationshipsWithCityId(
        @Param("id") Long id,
        @Param("cityId") Long cityId,
        @Param("currentDate") ZonedDateTime currentDate
    );

    /*"SELECT new com.mycompany.myapp.service.dto.CategoryWithCountChanellsDTO(category, COUNT(chan)) " +
        "FROM Category category JOIN category.chanellIds chan JOIN chan.cityEntity ce " +
        "WHERE category.id = :id AND chan.cityEntity.id = :cityId " +
        "GROUP BY category"*/

    /*@Query( "select category from Category category where size(category.chanellIds) > 0 " +
        "   and category.boolean1 = true   and  category.string1 =:city")
    List<CategoryWithCountChanellsDTO> findCategoriesHaveChanellsAndBool1True(@Param("city") String city);*/

    /*@Query( "select DISTINCT category from Category category LEFT JOIN FETCH category.chanellIds chan where size(category.chanellIds) > 0 " +
        "   and category.isShow = true AND" +
        " (chan.city IS NULL OR chan.city = '')")
    List<CategoryNameAndIdDTO> findCategoriesHaveChanellsAndIsShowTrue();*/

    @Query(
        "select DISTINCT category from Category category LEFT JOIN FETCH category.chanellIds chan " +
        " LEFT JOIN FETCH chan.cityEntity ce where size(category.chanellIds) > 0  and chan.endPublicDate > :currentDate " +
        "   and category.isShow = true AND" +
        " (chan.cityEntity IS NULL )"
    )
    List<CategoryNameAndIdDTO> findCategoriesHaveChanellsAndIsShowTrue(@Param("currentDate") ZonedDateTime currentDate);

    /*@Query("SELECT DISTINCT cat FROM Category cat " +
        "JOIN cat.chanellIds ch " +
        "WHERE ch.city = :city and cat.isShow = true")
    List<CategoryNameAndIdDTO> findCategoriesByCity(@Param("city") String city);*/

    @Query(
        "SELECT DISTINCT cat FROM Category cat " +
        "JOIN cat.chanellIds ch join ch.cityEntity ce " +
        "WHERE ce.id = :cityId and cat.isShow = true and ch.endPublicDate > :currentDate "
    )
    List<CategoryNameAndIdDTO> findCategoriesByCityId(@Param("cityId") Long cityId, @Param("currentDate") ZonedDateTime currentDate);

    @Query("SELECT cat FROM Category cat " + "WHERE cat.id = :id")
    CategoryNameAndIdDTO findCategoryById(@Param("id") Long id);

    /*@Query(
        "SELECT category FROM Category category LEFT JOIN FETCH category.chanellIds chan " +
            " WHERE category.id = :id AND" +
            " (chan.city IS NULL OR chan.city = '')"
    )
    CategoryWithCountChanellsDTO findCategoryByIdWithoutCity(@Param("id") Long id); // глянуть*/

    /* @Query(
        "SELECT category FROM Category category LEFT JOIN FETCH category.chanellIds chan WHERE category.id = :id AND" +
            " (chan.city = :city)"
    )
    CategoryWithCountChanellsDTO findCategoryByIdWithCity(@Param("id") Long id, @Param("city") String city);*/

    /*@Query(
        "SELECT category FROM Category category JOIN category.chanellIds chan JOIN chan.cityEntity ce " +
            "WHERE category.id = :id AND" +
            " (chan.cityEntity.id = :cityId)"
    )
    CategoryWithCountChanellsDTO findCategoryByIdWithCityId(@Param("id") Long id, @Param("cityId") Long cityId);*/

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.CategoryWithCountChanellsDTO(category, COUNT(chan)) " +
        "FROM Category category JOIN category.chanellIds chan JOIN chan.cityEntity ce " +
        "WHERE category.id = :id AND chan.cityEntity.id = :cityId  and chan.endPublicDate > :currentDate " +
        "GROUP BY category"
    )
    com.mycompany.myapp.service.dto.CategoryWithCountChanellsDTO findCategoryByIdWithCityId(
        @Param("id") Long id,
        @Param("cityId") Long cityId,
        @Param("currentDate") ZonedDateTime currentDate
    );

    // ЗАПРОСЫ - REST
    // ЗАПРОСЫ - REST
    // ЗАПРОСЫ - REST
    @Query("SELECT DISTINCT cat FROM Category cat " + "JOIN cat.chanellIds ch join ch.cityEntity ce " + "WHERE ce.id = :cityId")
    List<Category> findCategoriesByCityId(@Param("cityId") Long cityId);

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
}
