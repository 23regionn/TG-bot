package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ShowChannelsInCityLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ShowChannelsInCityLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShowChannelsInCityLogRepository extends JpaRepository<ShowChannelsInCityLog, Long> {}
