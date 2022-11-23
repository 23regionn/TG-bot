package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.LinksByCategoryInTop;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the LinksByCategoryInTop entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LinksByCategoryInTopRepository extends JpaRepository<LinksByCategoryInTop, Long> {}
