package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TGUserLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TGUserLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TGUserLogRepository extends JpaRepository<TGUserLog, Long> {}
