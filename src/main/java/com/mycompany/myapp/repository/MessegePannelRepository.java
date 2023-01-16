package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MessegePannel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the MessegePannel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessegePannelRepository extends JpaRepository<MessegePannel, Long> {}
