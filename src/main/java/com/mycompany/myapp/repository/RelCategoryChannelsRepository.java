package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.domain.RelCategoryChannels;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RelCategoryChannels entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelCategoryChannelsRepository extends JpaRepository<RelCategoryChannels, Long> {
    List<RelCategoryChannels> getAllByCategory(Category category);
    List<RelCategoryChannels> getAllByCategory_Id(Long id);

    Boolean existsByCategoryAndChanell(Category category, Chanell chanell);
}
