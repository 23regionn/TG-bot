package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ChanellLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ChanellLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChanellLogRepository extends JpaRepository<ChanellLog, Long> {}
