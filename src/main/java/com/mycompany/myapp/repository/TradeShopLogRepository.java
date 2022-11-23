package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TradeShopLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TradeShopLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TradeShopLogRepository extends JpaRepository<TradeShopLog, Long> {}
