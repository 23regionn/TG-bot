package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SearchTypeLog;
import com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO;
import com.mycompany.myapp.service.dto.statistics.for_city.BaseCityStatisticsDTO;
import com.mycompany.myapp.service.dto.tgUsers.SearchTypeCountDTO;
import com.mycompany.myapp.service.dto.tgUsers.TgUsersCountDTO;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SearchTypeLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SearchTypeLogRepository extends JpaRepository<SearchTypeLog, Long> {
    @Query("SELECT COUNT(search) FROM SearchTypeLog search")
    Long searchTypeLogCount();

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.tgUsers.SearchTypeCountDTO(COUNT(search)) FROM SearchTypeLog search " +
        "  where search.dateLog >= :startDate and search.dateLog <= :endDate"
    )
    Optional<SearchTypeCountDTO> searchTypeLogCountByDates(
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );

    @Query("SELECT COUNT(search) FROM SearchTypeLog search where search.inlineSearch = true")
    Long searchTypeLogCountInlineQuery();

    @Query("SELECT COUNT(search) FROM SearchTypeLog search where search.pageSearch = true and search.idCity is null")
    Long searchTypeLogCountPagesCategoryQuery();

    @Query("SELECT COUNT(search) FROM SearchTypeLog search where search.pageSearch = true and search.idCity is not null")
    Long searchTypeLogCountPagesCategoryForCityQuery();

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.tgUsers.SearchTypeCountDTO(COUNT(search)) FROM SearchTypeLog search " +
        "  where search.dateLog >= :startDate and search.dateLog <= :endDate and search.inlineSearch = true "
    )
    Optional<SearchTypeCountDTO> searchTypeLogCountInlineQueryByDates(
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.tgUsers.SearchTypeCountDTO(COUNT(search)) FROM SearchTypeLog search " +
        "  where search.dateLog >= :startDate and search.dateLog <= :endDate and search.pageSearch = true and search.idCity is null"
    )
    Optional<SearchTypeCountDTO> getSearchTypePagesCategoryRequestCountByDates(
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.tgUsers.SearchTypeCountDTO(COUNT(search)) FROM SearchTypeLog search " +
        "  where search.dateLog >= :startDate and search.dateLog <= :endDate and search.pageSearch = true and search.idCity is not null"
    )
    Optional<SearchTypeCountDTO> getSearchTypePagesCategoryRequestCountForCityByDates(
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO( search.pageNumber, COUNT(search)) " +
        " FROM SearchTypeLog search where search.idCity is null and " +
        " search.pageSearch is true GROUP BY search.pageNumber"
    )
    List<StatisticsByPageNumberCountDTO> searchTypeLogCountByPageNumber();

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO( search.pageNumber, COUNT(search)) " +
        " FROM SearchTypeLog search where search.idCity is null and " +
        " search.pageSearch is true and search.dateLog >= :startDate and search.dateLog <= :endDate GROUP BY search.pageNumber"
    )
    List<StatisticsByPageNumberCountDTO> searchTypeLogCountByPageNumberByDates(
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.statistics.for_city.BaseCityStatisticsDTO( search.idCity, COUNT(search.pageNumber)," +
        " COUNT(DISTINCT search.chatId)) " +
        " FROM SearchTypeLog search " +
        " where search.pageSearch = true and search.pageNumber = 1" +
        " and search.idCity is not null  and search.idCity is not null GROUP BY search.idCity"
    )
    List<BaseCityStatisticsDTO> getAllCityBaseStatistics();

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.statistics.for_city.BaseCityStatisticsDTO( search.idCity, COUNT(search.pageNumber)," +
        " COUNT(DISTINCT search.chatId)) " +
        " FROM SearchTypeLog search " +
        " where search.pageSearch = true and search.pageNumber = 1" +
        " and search.idCity = :idCity and search.dateLog >= :startDate and search.dateLog <= :endDate " +
        " and search.idCity is not null GROUP BY search.idCity"
    )
    BaseCityStatisticsDTO getCityBaseStatisticsByDate(
        @Param("idCity") Long idCity,
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO( search.pageNumber, COUNT(search)) " +
        " FROM SearchTypeLog search where search.idCity = :idCity and " +
        " search.pageSearch is true and search.dateLog >= :startDate and search.dateLog <= :endDate GROUP BY search.pageNumber"
    )
    List<StatisticsByPageNumberCountDTO> getCityBaseStatisticsByDateForCity(
        @Param("idCity") Long idCity,
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );
}
