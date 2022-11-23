package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MembersTradeDeal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MembersTradeDeal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MembersTradeDealRepository extends JpaRepository<MembersTradeDeal, Long> {}
