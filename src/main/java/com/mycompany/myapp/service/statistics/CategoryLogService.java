package com.mycompany.myapp.service.statistics;

import com.mycompany.myapp.repository.CategoryLogRepository;
import com.mycompany.myapp.service.dto.statistics.StatisticsByCategoryLogDTO;
import com.mycompany.myapp.service.dto.statistics.StatisticsCategoryLogByDatesDTO;
import com.mycompany.myapp.service.dto.tgUsers.TgUsersCountDTO;
import java.time.ZonedDateTime;
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

    public List<StatisticsByCategoryLogDTO> getAllStatisticsByCategoryLogs() {
        return categoryLogRepository.getAllCategoryClickCounts();
    }

    public StatisticsByCategoryLogDTO getStatisticCategoryClickCountsByIDAndDate(StatisticsCategoryLogByDatesDTO request) {
        StatisticsByCategoryLogDTO log = categoryLogRepository
            .getStatisticCategoryClickCountsByIDAndDate(
                request.getId(),
                request.getStartDate().plusDays(1l),
                request.getEndDate().plusDays(1l)
            )
            .orElse(new StatisticsByCategoryLogDTO());

        log.setStartDate(request.getStartDate().plusDays(1l));
        log.setEndDate(request.getEndDate().plusDays(1l));
        return log;
    }
}
