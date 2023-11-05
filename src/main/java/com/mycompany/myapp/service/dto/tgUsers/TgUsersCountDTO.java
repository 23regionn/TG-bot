package com.mycompany.myapp.service.dto.tgUsers;

import java.time.ZonedDateTime;

public class TgUsersCountDTO {

    private Long count;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

    public TgUsersCountDTO() {}

    public TgUsersCountDTO(Long count) {
        this.count = count;
    }

    public TgUsersCountDTO(Long count, ZonedDateTime startDate, ZonedDateTime endDate) {
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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
        return "TgUsersCountDTO{" + "count=" + count + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
}
