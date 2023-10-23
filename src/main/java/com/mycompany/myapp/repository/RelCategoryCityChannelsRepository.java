package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.RelCategoryCity;
import com.mycompany.myapp.domain.RelCategoryCityChannels;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RelCategoryCityChannels entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelCategoryCityChannelsRepository extends JpaRepository<RelCategoryCityChannels, Long> {
    List<RelCategoryCityChannels> getAllByRelCategoryCity(RelCategoryCity relCategoryCity);
}
