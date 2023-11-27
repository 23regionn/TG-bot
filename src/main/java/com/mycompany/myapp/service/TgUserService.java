package com.mycompany.myapp.service;

import com.mycompany.myapp.repository.TGUserRepository;
import com.mycompany.myapp.service.dto.tgUsers.SearchAnyByDatesDTO;
import com.mycompany.myapp.service.dto.tgUsers.StatisticsTgUserDTO;
import com.mycompany.myapp.service.dto.tgUsers.TgUsersCountDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TgUserService {

    private final TGUserRepository tgUserRepository;

    public TgUserService(TGUserRepository tgUserRepository) {
        this.tgUserRepository = tgUserRepository;
    }

    public Long getTGUserCount() {
        return tgUserRepository.getTGUserCount();
    }

    public TgUsersCountDTO getTGUserCountByDates(SearchAnyByDatesDTO request) {
        TgUsersCountDTO log = tgUserRepository
            .getTGUserCountByDates(request.getStartDate().plusDays(1l), request.getEndDate().plusDays(1l))
            .orElse(new TgUsersCountDTO());

        log.setStartDate(request.getStartDate().plusDays(1l));
        log.setEndDate(request.getEndDate().plusDays(1l));
        return log;
    }

    public List<StatisticsTgUserDTO> getAllTgUsersForStatistics() {
        return tgUserRepository.getAllTgUsersForStatistics();
    }
}
