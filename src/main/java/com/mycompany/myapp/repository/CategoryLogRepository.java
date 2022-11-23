package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CategoryLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CategoryLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryLogRepository extends JpaRepository<CategoryLog, Long> {}
