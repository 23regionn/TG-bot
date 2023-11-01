package com.mycompany.myapp.service.statistics;

import com.mycompany.myapp.repository.CategoryLogRepository;
import com.mycompany.myapp.service.dto.category_log.AllCategoryLogDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryLogService {

    private final CategoryLogRepository categoryLogRepository;

    public CategoryLogService(CategoryLogRepository categoryLogRepository) {
        this.categoryLogRepository = categoryLogRepository;
    }

    public List<AllCategoryLogDTO> getAllCategoryLogsCount() {
        return categoryLogRepository.getAllCategoryClickCounts();
    }
}
