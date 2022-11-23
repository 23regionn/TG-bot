package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TradeShop;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TradeShop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TradeShopRepository extends JpaRepository<TradeShop, Long> {}
