package com.mycompany.myapp.service;

import com.mycompany.myapp.repository.CategoryRepository;
import com.mycompany.myapp.repository.ChanellRepository;
import com.mycompany.myapp.repository.CityRepository;
import com.mycompany.myapp.service.dto.ChannelNameAndIDDTO;
import com.mycompany.myapp.service.dto.channel.ChannelInfoDTO;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChannelService {

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
        Pageable firstPageWithTwoElements
    ) {
        System.out.println(startDateS + " потом " + startDateE + " потом " + endDateS + " потом " + endDateE);

        return chanellRepository.findChannelInfoDTOPages(name, link, startDateS, startDateE, endDateS, endDateE, firstPageWithTwoElements);
    }
}
