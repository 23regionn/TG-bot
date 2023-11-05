package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SearchTypeLog;
import com.mycompany.myapp.service.dto.tgUsers.SearchTypeCountDTO;
import com.mycompany.myapp.service.dto.tgUsers.TgUsersCountDTO;
import java.time.ZonedDateTime;
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
}
