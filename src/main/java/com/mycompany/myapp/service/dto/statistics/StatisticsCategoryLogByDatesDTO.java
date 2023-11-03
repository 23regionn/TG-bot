package com.mycompany.myapp.service.dto.statistics;

import java.time.ZonedDateTime;

/**
 * A StatisticsCategoryLogByDatesDTO.
 */
public class StatisticsCategoryLogByDatesDTO {

    private Long id;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "StatisticsCategoryLogByDatesDTO{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
}
