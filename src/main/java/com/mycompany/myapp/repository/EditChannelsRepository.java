package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.EditChannels;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EditChannels entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EditChannelsRepository extends JpaRepository<EditChannels, Long> {}
