package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.SearchTypeLog;
import com.mycompany.myapp.repository.SearchTypeLogRepository;
import com.mycompany.myapp.repository.TGUserRepository;
import com.mycompany.myapp.service.dto.tgUsers.SearchAnyByDatesDTO;
import com.mycompany.myapp.service.dto.tgUsers.SearchTypeCountDTO;
import com.mycompany.myapp.service.dto.tgUsers.TgUsersCountDTO;
import java.time.ZonedDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchTypeLogService {

    private final SearchTypeLogRepository searchTypeLogRepository;

    public SearchTypeLogService(SearchTypeLogRepository searchTypeLogRepository) {
        this.searchTypeLogRepository = searchTypeLogRepository;
    }

    public Long searchTypeLogCount() {
        return searchTypeLogRepository.searchTypeLogCount();
    }

    public SearchTypeCountDTO getCountByDates(SearchAnyByDatesDTO request) {
        SearchTypeCountDTO log = searchTypeLogRepository
            .searchTypeLogCountByDates(request.getStartDate().plusDays(1l), request.getEndDate().plusDays(1l))
            .orElse(new SearchTypeCountDTO());

        log.setStartDate(request.getStartDate().plusDays(1l));
        log.setEndDate(request.getEndDate().plusDays(1l));
        return log;
    }
}
