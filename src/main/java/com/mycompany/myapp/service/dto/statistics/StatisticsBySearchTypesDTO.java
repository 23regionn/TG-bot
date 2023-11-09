package com.mycompany.myapp.service.dto.statistics;

import java.time.ZonedDateTime;

/**
 * A StatisticsBySearchTypesDTO.
 */
public class StatisticsBySearchTypesDTO {

    private Long countTypeLogCountInlineQuery;
    private Long countSearchTypeLogCountPagesCategoryQuery;
    private Long countSearchTypeLogCountPagesCategoryForCityQuery;

    public StatisticsBySearchTypesDTO(
        Long countTypeLogCountInlineQuery,
        Long countSearchTypeLogCountPagesCategoryQuery,
        Long countSearchTypeLogCountPagesCategoryForCityQuery
    ) {
        this.countTypeLogCountInlineQuery = countTypeLogCountInlineQuery;
        this.countSearchTypeLogCountPagesCategoryQuery = countSearchTypeLogCountPagesCategoryQuery;
        this.countSearchTypeLogCountPagesCategoryForCityQuery = countSearchTypeLogCountPagesCategoryForCityQuery;
    }

    public Long getCountTypeLogCountInlineQuery() {
        return countTypeLogCountInlineQuery;
    }

    public void setCountTypeLogCountInlineQuery(Long countTypeLogCountInlineQuery) {
        this.countTypeLogCountInlineQuery = countTypeLogCountInlineQuery;
    }

    public Long getCountSearchTypeLogCountPagesCategoryQuery() {
        return countSearchTypeLogCountPagesCategoryQuery;
    }

    public void setCountSearchTypeLogCountPagesCategoryQuery(Long countSearchTypeLogCountPagesCategoryQuery) {
        this.countSearchTypeLogCountPagesCategoryQuery = countSearchTypeLogCountPagesCategoryQuery;
    }

    public Long getCountSearchTypeLogCountPagesCategoryForCityQuery() {
        return countSearchTypeLogCountPagesCategoryForCityQuery;
    }

    public void setCountSearchTypeLogCountPagesCategoryForCityQuery(Long countSearchTypeLogCountPagesCategoryForCityQuery) {
        this.countSearchTypeLogCountPagesCategoryForCityQuery = countSearchTypeLogCountPagesCategoryForCityQuery;
    }
}
