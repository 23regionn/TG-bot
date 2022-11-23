package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Chanell;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Chanell entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChanellRepository extends JpaRepository<Chanell, Long> {}
