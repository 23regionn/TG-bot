package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AuditChannelsLog;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AuditChannelsLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditChannelsLogRepository extends JpaRepository<AuditChannelsLog, Long> {
    List<AuditChannelsLog> findAllByDateLog(LocalDate dateLog);
}
