package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MembersTradeDealLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MembersTradeDealLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MembersTradeDealLogRepository extends JpaRepository<MembersTradeDealLog, Long> {}
