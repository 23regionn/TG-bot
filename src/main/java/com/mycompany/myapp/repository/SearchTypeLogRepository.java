package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.SearchTypeLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SearchTypeLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SearchTypeLogRepository extends JpaRepository<SearchTypeLog, Long> {}
