package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.City;
import java.time.ZonedDateTime;
import java.util.Set;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the City entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    /** Запросы для логики бота */
    //Запрос на получение списка городов для бота, потом из них вырезается первая буква
    @Query(
        "select cit.cityName from City cit JOIN cit.relCategoryCities relCatCit JOIN relCatCit.category cat " +
        " join relCatCit.relCategoryCityChannels  relCatCitCh join relCatCitCh.chanell chan " +
        " where chan.isModerate = true and chan.endPublicDate > :currentDate and relCatCitCh.size > 0  " +
        " and relCatCitCh.isShowChannel = true " +
        " and relCatCit.isShow = true " +
        " and cat.isShow = true"
    )
    Set<String> getCitiesNamesNewRel(@Param("currentDate") ZonedDateTime currentDate);

    // Список городов по первой букве
    @Query(
        "select cit from City cit JOIN cit.relCategoryCities relCatCit JOIN relCatCit.category cat " +
        " join relCatCit.relCategoryCityChannels  relCatCitCh join relCatCitCh.chanell chan " +
        " where SUBSTRING(cit.cityName, 1, 1) = :firstLetter " +
        " and chan.isModerate = true and chan.endPublicDate > :currentDate and relCatCitCh.size > 0  " +
        " and relCatCitCh.isShowChannel = true " +
        " and relCatCit.isShow = true " +
        " and cat.isShow = true"
    )
    Set<City> getCitiesByFirstLetterNewRel(@Param("firstLetter") String firstLetter, @Param("currentDate") ZonedDateTime currentDate);
}
