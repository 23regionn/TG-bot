package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.City;
import com.mycompany.myapp.domain.SearchTypeLog;
import com.mycompany.myapp.repository.CityRepository;
import com.mycompany.myapp.repository.SearchTypeLogRepository;
import com.mycompany.myapp.repository.TGUserRepository;
import com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO;
import com.mycompany.myapp.service.dto.statistics.StatisticsBySearchTypesDTO;
import com.mycompany.myapp.service.dto.statistics.for_city.BaseCityStatisticsDTO;
import com.mycompany.myapp.service.dto.tgUsers.SearchAnyByDatesDTO;
import com.mycompany.myapp.service.dto.tgUsers.SearchTypeCountDTO;
import com.mycompany.myapp.service.dto.tgUsers.TgUsersCountDTO;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SearchTypeLogService {

    private final SearchTypeLogRepository searchTypeLogRepository;
    private final CityRepository cityRepository;

    public SearchTypeLogService(SearchTypeLogRepository searchTypeLogRepository, CityRepository cityRepository) {
        this.searchTypeLogRepository = searchTypeLogRepository;
        this.cityRepository = cityRepository;
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

    public StatisticsBySearchTypesDTO searchTypeLogDetail() {
        return new StatisticsBySearchTypesDTO(
            searchTypeLogRepository.searchTypeLogCountInlineQuery(),
            searchTypeLogRepository.searchTypeLogCountPagesCategoryQuery(),
            searchTypeLogRepository.searchTypeLogCountPagesCategoryForCityQuery()
        );
    }

    public SearchTypeCountDTO getSearchTypeInlineRequestCountByDates(SearchAnyByDatesDTO request) {
        SearchTypeCountDTO log = searchTypeLogRepository
            .searchTypeLogCountInlineQueryByDates(request.getStartDate().plusDays(1l), request.getEndDate().plusDays(1l))
            .orElse(new SearchTypeCountDTO());

        log.setStartDate(request.getStartDate().plusDays(1l));
        log.setEndDate(request.getEndDate().plusDays(1l));
        return log;
    }

    public SearchTypeCountDTO getSearchTypePagesCategoryRequestCountByDates(SearchAnyByDatesDTO request) {
        SearchTypeCountDTO log = searchTypeLogRepository
            .getSearchTypePagesCategoryRequestCountByDates(request.getStartDate().plusDays(1l), request.getEndDate().plusDays(1l))
            .orElse(new SearchTypeCountDTO());

        log.setStartDate(request.getStartDate().plusDays(1l));
        log.setEndDate(request.getEndDate().plusDays(1l));
        return log;
    }

    public SearchTypeCountDTO getSearchTypePagesCategoryRequestCountForCityByDates(SearchAnyByDatesDTO request) {
        SearchTypeCountDTO log = searchTypeLogRepository
            .getSearchTypePagesCategoryRequestCountForCityByDates(request.getStartDate().plusDays(1l), request.getEndDate().plusDays(1l))
            .orElse(new SearchTypeCountDTO());

        log.setStartDate(request.getStartDate().plusDays(1l));
        log.setEndDate(request.getEndDate().plusDays(1l));
        return log;
    }

    public List<StatisticsByPageNumberCountDTO> searchTypeLogCountByPageNumber() {
        return searchTypeLogRepository.searchTypeLogCountByPageNumber();
    }

    public List<StatisticsByPageNumberCountDTO> searchTypeLogCountByPageNumberByDates(SearchAnyByDatesDTO request) {
        List<StatisticsByPageNumberCountDTO> searchList = searchTypeLogRepository.searchTypeLogCountByPageNumberByDates(
            request.getStartDate().plusDays(1l),
            request.getEndDate().plusDays(1l)
        );
        return searchList;
    }

    public List<BaseCityStatisticsDTO> getAllCityBaseStatistics() {
        List<City> cities = cityRepository.findAll();
        List<BaseCityStatisticsDTO> anyDto = searchTypeLogRepository.getAllCityBaseStatistics();

        for (BaseCityStatisticsDTO dto : anyDto) {
            for (City city : cities) {
                if (dto.getIdCity().equals(city.getId())) {
                    dto.setNameCity(city.getCityName());
                    break;
                }
            }
        }
        return anyDto;
    }

    public BaseCityStatisticsDTO getCityBaseStatisticsByDate(SearchAnyByDatesDTO request, Long idCity) {
        return searchTypeLogRepository.getCityBaseStatisticsByDate(
            idCity,
            request.getStartDate().plusDays(1l),
            request.getEndDate().plusDays(1l)
        );
    }

    public List<StatisticsByPageNumberCountDTO> getCityBaseStatisticsByDateForCity(SearchAnyByDatesDTO request, Long idCity) {
        List<StatisticsByPageNumberCountDTO> searchList = searchTypeLogRepository.getCityBaseStatisticsByDateForCity(
            idCity,
            request.getStartDate().plusDays(1l),
            request.getEndDate().plusDays(1l)
        );
        return searchList;
    }
}
