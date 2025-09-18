package com.mycompany.myapp.service;

import com.mycompany.myapp.repository.CategoryRepository;
import com.mycompany.myapp.repository.ChanellRepository;
import com.mycompany.myapp.repository.CityRepository;
import com.mycompany.myapp.service.dto.ChannelNameAndIDDTO;
import com.mycompany.myapp.service.dto.channel.ChannelInfoDTO;
import java.time.ZonedDateTime;
import java.util.List;

import com.mycompany.myapp.service.tg.nikita.ChannelTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChannelService {
    private final Logger log = LoggerFactory.getLogger(ChannelService.class);


    private final ChanellRepository chanellRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    public ChannelService(ChanellRepository chanellRepository, CategoryRepository categoryRepository, CityRepository cityRepository) {
        this.chanellRepository = chanellRepository;
        this.categoryRepository = categoryRepository;
        this.cityRepository = cityRepository;
    }

    public List<ChannelNameAndIDDTO> getAllChanellsNamesAndIdDTO() {
        return chanellRepository.getAllChanellsNamesAndIdDTO();
    }

    public List<ChannelInfoDTO> getAllChanellsInfoDTO() {
        return chanellRepository.getAllChanellsInfoDTO();
    }

    public Page<ChannelInfoDTO> findChannelInfoDTOPages(
        String name,
        String link,
        ZonedDateTime startDateS,
        ZonedDateTime startDateE,
        ZonedDateTime endDateS,
        ZonedDateTime endDateE,
        Pageable pageable
    ) {
        log.info("Параметры запроса:");
        log.info("name: {}", name);
        log.info("link: {}", link);
        log.info("startDateS: {}", startDateS);
        log.info("startDateE: {}", startDateE);
        log.info("endDateS: {}", endDateS);
        log.info("endDateE: {}", endDateE);

        // Если все даты null - используем упрощенный запрос
        if (startDateS == null && startDateE == null && endDateS == null && endDateE == null) {
            return chanellRepository.findChannelsWithoutDateFilters(
                name != null ? name : "",
                link != null ? link : "",
                pageable
            );
        }

        return chanellRepository.findChannelInfoDTOPages(
            name != null ? name : "",
            link != null ? link : "",
            startDateS,
            startDateE,
            endDateS,
            endDateE,
            pageable
        );
    }
}
