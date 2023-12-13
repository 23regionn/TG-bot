package com.mycompany.myapp.service;

import static com.mycompany.myapp.service.Constants.*;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.City;
import com.mycompany.myapp.repository.CategoryRepository;
import com.mycompany.myapp.repository.ChanellRepository;
import com.mycompany.myapp.repository.CityRepository;
import com.mycompany.myapp.service.dto.InfoCategoryCityDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService {

    private final ChanellRepository chanellRepository;
    private final CategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    public CategoryService(ChanellRepository chanellRepository, CategoryRepository categoryRepository, CityRepository cityRepository) {
        this.chanellRepository = chanellRepository;
        this.categoryRepository = categoryRepository;
        this.cityRepository = cityRepository;
    }

    public InfoCategoryCityDTO getInfoAboutCategory(Long cityId, Long categoryId) {
        Category category = categoryRepository
            .findById(categoryId)
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(CATEGORY_NOT_FOUND, CATEGORY_NAME, ID_NOT_FOUND);
                }
            );

        City city = cityRepository
            .findById(cityId)
            .orElseThrow(
                () -> {
                    throw new BadRequestAlertException(CITY_NOT_FOUND, CITY_NAME, ID_NOT_FOUND);
                }
            );
        InfoCategoryCityDTO dto = new InfoCategoryCityDTO();
        dto.setIdCat(category.getId());
        dto.setCategoryName(category.getName());
        dto.setIdCity(city.getId());
        dto.setCityName(city.getCityName());
        return dto;
    }
}
