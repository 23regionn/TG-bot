package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.RelCategoryChannels;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RelCategoryChannels entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelCategoryChannelsRepository extends JpaRepository<RelCategoryChannels, Long> {}
