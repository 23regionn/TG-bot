package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CategoryLog;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CategoryLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoryLogRepository extends JpaRepository<CategoryLog, Long> {
    @Query(
        "SELECT new com.mycompany.myapp.service.dto.category_log" +
        ".AllCategoryLogDTO(cl.catId, cl.name, COUNT(cl), COUNT(DISTINCT cl.chatId), COUNT(cl.cityId) ) FROM CategoryLog cl " +
        " GROUP BY cl.catId, cl.name"
    )
    List<com.mycompany.myapp.service.dto.category_log.AllCategoryLogDTO> getAllCategoryClickCounts();
}
