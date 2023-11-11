package com.mycompany.myapp.service.dto.statistics.category;

import java.time.ZonedDateTime;

/**
 * A CategoryLogForUserDTO.
 */
public class CategoryLogForUserDTO {

    private Long idCategory;
    private String categoryName;
    private Long countClickTotal;
    private Long countClickByCity;
    private Long countClickNotByCity;

    public CategoryLogForUserDTO() {}

    public CategoryLogForUserDTO(Long idCategory, String categoryName, Long countClickTotal, Long countClickByCity) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.countClickTotal = countClickTotal;
        this.countClickByCity = countClickByCity;
        this.countClickNotByCity = countClickTotal - countClickByCity;
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
}
