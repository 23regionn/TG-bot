package com.mycompany.myapp.service.statistics;

import com.mycompany.myapp.repository.CategoryLogRepository;
import com.mycompany.myapp.service.dto.statistics.StatisticsByCategoryLogDTO;
import com.mycompany.myapp.service.dto.statistics.StatisticsCategoryLogByDatesDTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
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
