package com.mycompany.myapp.service.dto.statistics.for_city;

import java.time.ZonedDateTime;

/**
 * A StatisticsCategoryLogByDatesDTO.
 */
public class StatisticsCategoryLogForCityByDatesDTO {

    private Long idCategory;
    private Long idCity;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
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

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    @Override
    public String toString() {
        return (
            "StatisticsCategoryLogForCityByDatesDTO{" +
            "idCategory=" +
            idCategory +
            ", idCity=" +
            idCity +
            ", startDate=" +
            startDate +
            ", endDate=" +
            endDate +
            '}'
        );
    }
}
