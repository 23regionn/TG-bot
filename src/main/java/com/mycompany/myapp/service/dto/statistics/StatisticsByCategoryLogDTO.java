package com.mycompany.myapp.service.dto.statistics;

import java.time.ZonedDateTime;

/**
 * A StatisticsByCategoryLogDTO.
 */
public class StatisticsByCategoryLogDTO {

    private Long idCategory;
    private String categoryName;
    private Long countClickTotal;
    private Long countClickTotalByUniChatId;
    private Long countClickByCity;
    private Long countClickNotByCity;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

    public StatisticsByCategoryLogDTO() {}

    public StatisticsByCategoryLogDTO(
        Long idCategory,
        String categoryName,
        Long countClickTotal,
        Long countClickTotalByUniChatId,
        Long countClickByCity,
        Long countClickNotByCity,
        ZonedDateTime startDate,
        ZonedDateTime endDate
    ) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.countClickTotal = countClickTotal;
        this.countClickTotalByUniChatId = countClickTotalByUniChatId;
        this.countClickByCity = countClickByCity;
        this.countClickNotByCity = countClickNotByCity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public StatisticsByCategoryLogDTO(
        Long idCategory,
        String categoryName,
        Long countClickTotal,
        Long countClickTotalByUniChatId,
        Long countClickByCity
    ) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.countClickTotal = countClickTotal;
        this.countClickTotalByUniChatId = countClickTotalByUniChatId;
        this.countClickByCity = countClickByCity;
        this.countClickNotByCity = (countClickTotal - countClickByCity);
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

    public Long getCountClickTotal() {
        return countClickTotal;
    }

    public void setCountClickTotal(Long countClickTotal) {
        this.countClickTotal = countClickTotal;
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

    public Long getCountClickNotByCity() {
        return countClickNotByCity;
    }

    public void setCountClickNotByCity(Long countClickNotByCity) {
        this.countClickNotByCity = countClickNotByCity;
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
        return (
            "StatisticsByCategoryLogDTO{" +
            "idCategory=" +
            idCategory +
            ", categoryName='" +
            categoryName +
            '\'' +
            ", countClickTotal=" +
            countClickTotal +
            ", countClickTotalByUniChatId=" +
            countClickTotalByUniChatId +
            ", countClickByCity=" +
            countClickByCity +
            ", countClickNotByCity=" +
            countClickNotByCity +
            ", startDate=" +
            startDate +
            ", endDate=" +
            endDate +
            '}'
        );
    }
}
