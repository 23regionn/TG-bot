package com.mycompany.myapp.service.dto.tgUsers;

import java.time.ZonedDateTime;

/**
 * A SearchAnyByDatesDTO.
 */
public class SearchAnyByDatesDTO {

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

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
        return "StatisticsCategoryLogByDatesDTO{" + " startDate=" + startDate + ", endDate=" + endDate + '}';
    }
}
