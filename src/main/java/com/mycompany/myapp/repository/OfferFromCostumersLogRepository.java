package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OfferFromCostumersLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OfferFromCostumersLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferFromCostumersLogRepository extends JpaRepository<OfferFromCostumersLog, Long> {}
