package com.mycompany.myapp.service.dto.statistics.for_city;

import java.time.ZonedDateTime;

/**
 * A StatisticsByCategoryCityLogDTO.
 */
public class StatisticsByCategoryCityLogDTO {

    private Long idCategory;
    private String categoryName;
    private Long idCity;
    private String cityName;
    private Long countClickTotalByUniChatId;
    private Long countClickByCity;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

    public StatisticsByCategoryCityLogDTO() {}

    public StatisticsByCategoryCityLogDTO(
        Long idCategory,
        String categoryName,
        Long idCity,
        String cityName,
        Long countClickTotalByUniChatId,
        Long countClickByCity
    ) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.idCity = idCity;
        this.cityName = cityName;
        this.countClickTotalByUniChatId = countClickTotalByUniChatId;
        this.countClickByCity = countClickByCity;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCountClickTotalByUniChatId() {
        return countClickTotalByUniChatId;
    }

    public void setCountClickTotalByUniChatId(Long countClickTotalByUniChatId) {
        this.countClickTotalByUniChatId = countClickTotalByUniChatId;
    }

    public Long getCountClickByCity() {
        return countClickByCity;
    }

    public void setCountClickByCity(Long countClickByCity) {
        this.countClickByCity = countClickByCity;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }
}
