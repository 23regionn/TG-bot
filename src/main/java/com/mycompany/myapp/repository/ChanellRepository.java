package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.domain.City;
import com.mycompany.myapp.domain.TGUser;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Chanell entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChanellRepository extends JpaRepository<Chanell, Long> {
    @Query("select c from Chanell c " + " where c.priceDiapozon = :priceDiapozon")
    Set<Chanell> getAllByPriceDiapozon(@Param("priceDiapozon") Double chatId);

    /* @Query("select c from Chanell c " + " where c.categoryIds = :categoryId")
    Set<Chanell> getByCategoryId(@Param("categoryId") Set<Category> categoryId);*/
    //    Set<Chanell> getByCategoryId(@Param("categoryId") Long categoryId);

    @Query("select c from Chanell c " + " where c.tGUser = :tgUser")
    List<Chanell> getAllByTGUser(@Param("tgUser") TGUser tgUser);

    @Query("select c from Chanell c " + " where c.city is not null")
    List<Chanell> getChannelsWithCities();

    @Query("select c from Chanell c " + " where c.city = :city")
    List<Chanell> findAllByCity(@Param("city") String city);

    @Query("select c.city from Chanell c JOIN c.categoryIds cat" + " where c.city is not null and size(cat.chanellIds) > 0")
    Set<String> getCitiesNames();

    @Query("select c.city from Chanell c JOIN c.categoryIds cat where SUBSTRING(c.city, 1, 1) = :firstLetter and size(cat.chanellIds) > 0")
    Set<String> getCitiesByFirstLetter(@Param("firstLetter") String firstLetter);

    @Query("SELECT DISTINCT ch FROM Chanell ch " + "JOIN ch.categoryIds cat " + "WHERE ch.city = :city AND cat.id = :category")
    List<Chanell> findChannelsByCityAndCategory(@Param("city") String city, @Param("category") Long category);

    @Query(
        "SELECT DISTINCT ch FROM Chanell ch " +
        "JOIN ch.categoryIds cat join ch.cityEntity ce " +
        "WHERE ce.id = :cityId AND cat.id = :category and ch.endPublicDate > :currentDate "
    )
    List<Chanell> findChannelsByCityIDAndCategory(
        @Param("cityId") Long cityId,
        @Param("category") Long category,
        @Param("currentDate") ZonedDateTime currentDate
    );

    List<Chanell> getAllByCategoryIdsAndCityEntity(Category category, City cityEntity);
}
