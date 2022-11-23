package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.BalanceLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BalanceLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BalanceLogRepository extends JpaRepository<BalanceLog, Long> {}
