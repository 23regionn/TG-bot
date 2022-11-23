package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ConfigTable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ConfigTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConfigTableRepository extends JpaRepository<ConfigTable, Long> {}
