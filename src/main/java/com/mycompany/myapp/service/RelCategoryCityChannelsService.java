package com.mycompany.myapp.service;

import static com.mycompany.myapp.service.Constants.*;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.repository.*;
import com.mycompany.myapp.service.dto.relCategoryChannel.RelCategoryChannelsCreateDTO;
import com.mycompany.myapp.service.dto.relCategoryCity.RelCategoryCityCreateDTO;
import com.mycompany.myapp.service.dto.relCategoryCityChannels.RelCategoryCityChannelsCreateDTO;
import com.mycompany.myapp.web.rest.RelCategoryCityChannelsResource;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RelCategoryCityChannelsService {

    private final RelCategoryCityRepository relCategoryCityRepository;
    private final RelCategoryCityChannelsRepository relCategoryCityChannelsRepository;
    private final ShowChannelsInCityLogRepository showChannelsInCityLogRepository;
    private final ChanellRepository chanellRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    public RelCategoryCityChannelsService(
        ChanellRepository chanellRepository,
        CategoryRepository categoryRepository,
        RelCategoryCityRepository relCategoryCityRepository,
        CityRepository cityRepository,
        RelCategoryCityChannelsRepository relCategoryCityChannelsRepository,
        ShowChannelsInCityLogRepository showChannelsInCityLogRepository
    ) {
        this.chanellRepository = chanellRepository;
        this.categoryRepository = categoryRepository;
        this.relCategoryCityRepository = relCategoryCityRepository;
        this.cityRepository = cityRepository;
        this.relCategoryCityChannelsRepository = relCategoryCityChannelsRepository;
        this.showChannelsInCityLogRepository = showChannelsInCityLogRepository;
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

        Category category = relCategoryCity != null ? relCategoryCity.getCategory() : null;
        City city = relCategoryCity != null ? relCategoryCity.getCity() : null;

        if (!relCategoryCityChannelsRepository.existsByChanellAndRelCategoryCity(chanell, relCategoryCity)) {
            RelCategoryCityChannels relCategoryCityChannels = new RelCategoryCityChannels();
            relCategoryCityChannels.setComment(createDTO.getComment());
            relCategoryCityChannels.setScoreChannel(createDTO.getScoreChannel());
            relCategoryCityChannels.setIsShowChannel(createDTO.getIsShowChannel());
            relCategoryCityChannels.setChanell(chanell);
            relCategoryCityChannels.setRelCategoryCity(relCategoryCity);

            ShowChannelsInCityLog audit = new ShowChannelsInCityLog();
            if (chanell != null) {
                audit.setIdChannel(chanell.getId());
                audit.setNameChannel(chanell.getName());
            }

            if (category != null) {
                audit.setIdCategory(category.getId());
                audit.setNameCategory(category.getName());
            }

            if (city != null) {
                audit.setIdCity(city.getId());
                audit.setNameCity(city.getCityName());
            }

            audit.setComment(createDTO.getComment());
            audit.setScoreChannel(createDTO.getScoreChannel());
            audit.setIsShowChannel(createDTO.getIsShowChannel());

            audit.setDateLog(LocalDate.now());
            showChannelsInCityLogRepository.save(audit);

            return relCategoryCityChannelsRepository.save(relCategoryCityChannels);
        } else {
            throw new BadRequestAlertException("Канал уже существет", "Нельзя создать дубль ", "Есть в БД");
        }
    }

    public ShowChannelsInCityLog setAuditAfterUpdateRecord(ShowChannelsInCityLog audit, RelCategoryCityChannels rel) {
        Chanell chanell = rel.getChanell();
        RelCategoryCity categoryCity = rel.getRelCategoryCity();
        Category category = categoryCity != null ? categoryCity.getCategory() : null;
        City city = categoryCity != null ? categoryCity.getCity() : null;

        if (chanell != null) {
            audit.setIdChannel(chanell.getId());
            audit.setNameChannel(chanell.getName());
        }

        if (category != null) {
            audit.setIdCategory(category.getId());
            audit.setNameCategory(category.getName());
        }

        if (city != null) {
            audit.setIdCity(city.getId());
            audit.setNameCity(city.getCityName());
        }

        audit.setComment(rel.getComment());
        audit.setScoreChannel(rel.getScoreChannel());
        audit.setIsShowChannel(rel.getIsShowChannel());

        audit.setDateLog(LocalDate.now());
        return showChannelsInCityLogRepository.save(audit);
    }
}
