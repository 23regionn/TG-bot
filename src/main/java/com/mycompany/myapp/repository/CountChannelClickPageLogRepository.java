package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CountChannelClickPageLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CountChannelClickPageLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CountChannelClickPageLogRepository extends JpaRepository<CountChannelClickPageLog, Long> {}
