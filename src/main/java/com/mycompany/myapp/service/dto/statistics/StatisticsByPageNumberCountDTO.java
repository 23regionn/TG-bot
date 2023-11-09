package com.mycompany.myapp.service.dto.statistics;

/**
 * A StatisticsByPageNumberCountDTO.
 */
public class StatisticsByPageNumberCountDTO {

    private Long pageNumber;
    private Long count;

    public StatisticsByPageNumberCountDTO() {}

    public StatisticsByPageNumberCountDTO(Long pageNumber, Long count) {
        this.pageNumber = pageNumber;
        this.count = count;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
