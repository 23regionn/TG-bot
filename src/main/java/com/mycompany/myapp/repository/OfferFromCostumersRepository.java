package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.OfferFromCostumers;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OfferFromCostumers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfferFromCostumersRepository extends JpaRepository<OfferFromCostumers, Long> {}
