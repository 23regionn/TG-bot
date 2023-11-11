package com.mycompany.myapp.service;

import com.mycompany.myapp.repository.CountChannelClickPageLogRepository;
import com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO;
import com.mycompany.myapp.service.dto.statistics.count_channel_click.CountChannelClickPageByDatesDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountChannelClickPageLogService {

    private final CountChannelClickPageLogRepository countChannelClickPageLogRepository;

    public CountChannelClickPageLogService(CountChannelClickPageLogRepository countChannelClickPageLogRepository) {
        this.countChannelClickPageLogRepository = countChannelClickPageLogRepository;
    }

    public List<StatisticsByPageNumberCountDTO> getStatisticsChannelPagesLogByDatesForCity(CountChannelClickPageByDatesDTO dto) {
        return countChannelClickPageLogRepository.getStatisticsChannelPagesLogByDatesForCity(
            dto.getIdCategory(),
            dto.getIdCity(),
            dto.getStartDate().plusDays(1l),
            dto.getEndDate().plusDays(1l)
        );
    }

    public List<StatisticsByPageNumberCountDTO> getStatisticsChannelPagesLogByDates(CountChannelClickPageByDatesDTO dto) {
        return countChannelClickPageLogRepository.getStatisticsChannelPagesLogByDates(
            dto.getIdCategory(),
            dto.getStartDate().plusDays(1l),
            dto.getEndDate().plusDays(1l)
        );
    }
}
