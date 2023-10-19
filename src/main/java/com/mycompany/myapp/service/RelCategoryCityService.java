package com.mycompany.myapp.service;

import static com.mycompany.myapp.service.Constants.*;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.repository.*;
import com.mycompany.myapp.service.dto.relCategoryChannel.RelCategoryChannelsCreateDTO;
import com.mycompany.myapp.service.dto.relCategoryCity.RelCategoryCityCreateDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RelCategoryCityService {

    private final RelCategoryCityRepository relCategoryCityRepository;
    private final ChanellRepository chanellRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    public RelCategoryCityService(
        ChanellRepository chanellRepository,
        CategoryRepository categoryRepository,
        RelCategoryCityRepository relCategoryCityRepository,
        CityRepository cityRepository
    ) {
        this.chanellRepository = chanellRepository;
        this.categoryRepository = categoryRepository;
        this.relCategoryCityRepository = relCategoryCityRepository;
        this.cityRepository = cityRepository;
    }

    public List<RelCategoryCity> getAllByCityId(Long cityId) {
        City city = cityRepository
            .findById(cityId)
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(CITY_NOT_FOUND, CITY_NAME, ID_NOT_FOUND);
                }
            );
        return relCategoryCityRepository.getAllByCity(city);
    }

    public RelCategoryCity createRelCategoryCity(RelCategoryCityCreateDTO entity) {
        City city = cityRepository
            .findById(entity.getIdCity())
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(CITY_NOT_FOUND, CITY_NAME, ID_NOT_FOUND);
                }
            );
        Category category = categoryRepository
            .findById(entity.getIdCat())
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(CATEGORY_NOT_FOUND, CATEGORY_NAME, ID_NOT_FOUND);
                }
            );

        RelCategoryCity relCategoryCity = new RelCategoryCity();
        relCategoryCity.setCategory(category);
        relCategoryCity.setCity(city);
        relCategoryCity.setComment(entity.getComment());
        relCategoryCity.setScore(entity.getScore());
        relCategoryCity.setIsFirst(entity.getIsFirst());
        relCategoryCity.setIsShow(entity.getIsShow());
        return relCategoryCityRepository.save(relCategoryCity);
    }
}
