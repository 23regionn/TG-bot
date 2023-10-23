package com.mycompany.myapp.service;

import static com.mycompany.myapp.service.Constants.*;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.repository.*;
import com.mycompany.myapp.service.dto.relCategoryChannel.RelCategoryChannelsCreateDTO;
import com.mycompany.myapp.service.dto.relCategoryCity.RelCategoryCityCreateDTO;
import com.mycompany.myapp.service.dto.relCategoryCityChannels.RelCategoryCityChannelsCreateDTO;
import com.mycompany.myapp.web.rest.RelCategoryCityChannelsResource;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RelCategoryCityChannelsService {

    private final RelCategoryCityRepository relCategoryCityRepository;
    private final RelCategoryCityChannelsRepository relCategoryCityChannelsRepository;
    private final ChanellRepository chanellRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    public RelCategoryCityChannelsService(
        ChanellRepository chanellRepository,
        CategoryRepository categoryRepository,
        RelCategoryCityRepository relCategoryCityRepository,
        CityRepository cityRepository,
        RelCategoryCityChannelsRepository relCategoryCityChannelsRepository
    ) {
        this.chanellRepository = chanellRepository;
        this.categoryRepository = categoryRepository;
        this.relCategoryCityRepository = relCategoryCityRepository;
        this.cityRepository = cityRepository;
        this.relCategoryCityChannelsRepository = relCategoryCityChannelsRepository;
    }

    public List<RelCategoryCityChannels> getCategoryCityChannelsByRelCityCategory(Long relCategoryCityChannelId) {
        RelCategoryCity relCategoryCity = relCategoryCityRepository
            .findById(relCategoryCityChannelId)
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(REL_CATEGORY_CITY_NOT_FOUND, REL_CATEGORY_CITY, ID_NOT_FOUND);
                }
            );

        return relCategoryCityChannelsRepository.getAllByRelCategoryCity(relCategoryCity);
    }

    public RelCategoryCityChannels createNewRel(RelCategoryCityChannelsCreateDTO createDTO) {
        RelCategoryCity relCategoryCity = relCategoryCityRepository
            .findById(createDTO.getIdRel())
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(REL_CATEGORY_CITY_NOT_FOUND, REL_CATEGORY_CITY, ID_NOT_FOUND);
                }
            );

        Chanell chanell = chanellRepository
            .findById(createDTO.getIdChannel())
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(CHANNEL_NOT_FOUND, CHANNEL_NAME, ID_NOT_FOUND);
                }
            );

        RelCategoryCityChannels relCategoryCityChannels = new RelCategoryCityChannels();
        relCategoryCityChannels.setComment(createDTO.getComment());
        relCategoryCityChannels.setScoreChannel(createDTO.getScoreChannel());
        relCategoryCityChannels.setIsShowChannel(createDTO.getIsShowChannel());
        relCategoryCityChannels.setChanell(chanell);
        relCategoryCityChannels.setRelCategoryCity(relCategoryCity);

        return relCategoryCityChannelsRepository.save(relCategoryCityChannels);
    }
}
