package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CategoryLog;
import com.mycompany.myapp.service.dto.statistics.StatisticsByCategoryLogDTO;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CategoryLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryLogRepository extends JpaRepository<CategoryLog, Long> {
    @Query(
        "SELECT new com.mycompany.myapp.service.dto.statistics" +
        ".StatisticsByCategoryLogDTO(cl.catId, cl.name, COUNT(cl), COUNT(DISTINCT cl.chatId), COUNT(cl.cityId) ) FROM CategoryLog cl " +
        " GROUP BY cl.catId, cl.name"
    )
    List<StatisticsByCategoryLogDTO> getAllCategoryClickCounts();

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.statistics" +
        ".StatisticsByCategoryLogDTO(cl.catId, cl.name, COUNT(cl)," +
        " COUNT(DISTINCT cl.chatId), COUNT(cl.cityId)) FROM CategoryLog cl " +
        " where cl.dateLog >= :startDate and cl.dateLog <= :endDate and cl.catId = :id GROUP BY cl.catId, cl.name"
    )
    Optional<StatisticsByCategoryLogDTO> getStatisticCategoryClickCountsByIDAndDate(
        @Param("id") Long id,
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate
    );
}
