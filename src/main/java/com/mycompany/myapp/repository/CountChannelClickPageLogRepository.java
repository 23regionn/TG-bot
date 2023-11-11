package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CountChannelClickPageLog;
import com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CountChannelClickPageLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CountChannelClickPageLogRepository extends JpaRepository<CountChannelClickPageLog, Long> {
    @Query(
        "SELECT new com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO(c.pageNumber, COUNT(c)) " +
        "FROM CountChannelClickPageLog c " +
        "WHERE c.idCategory = :idCategory AND c.idCity = :idCity AND c.dateLog >= :startDate and c.dateLog <= :endDate " +
        "GROUP BY c.pageNumber"
    )
    List<StatisticsByPageNumberCountDTO> getStatisticsChannelPagesLogByDatesForCity(
        @Param("idCategory") Long idCategory,
        @Param("idCity") Long idCity,
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO(c.pageNumber, COUNT(c)) " +
        "FROM CountChannelClickPageLog c " +
        "WHERE c.idCategory = :idCategory AND c.idCity is null AND c.dateLog >= :startDate and c.dateLog <= :endDate " +
        "GROUP BY c.pageNumber"
    )
    List<StatisticsByPageNumberCountDTO> getStatisticsChannelPagesLogByDates(
        @Param("idCategory") Long idCategory,
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );
}
