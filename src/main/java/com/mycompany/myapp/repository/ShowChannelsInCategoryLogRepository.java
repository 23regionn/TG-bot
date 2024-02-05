package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ShowChannelsInCategoryLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ShowChannelsInCategoryLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShowChannelsInCategoryLogRepository extends JpaRepository<ShowChannelsInCategoryLog, Long> {}
