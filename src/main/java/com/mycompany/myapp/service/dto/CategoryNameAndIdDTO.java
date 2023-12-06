package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Category;

/**
 * A DTO CategoryNameAndIdDTO.
 */
public class CategoryNameAndIdDTO {

    private Long id;

    private String name;
    //    private Double score = 10000.0;
    private Double score;

    private Boolean isFirst;

    public CategoryNameAndIdDTO() {
        // Empty constructor needed for Jackson.
    }

    public CategoryNameAndIdDTO(Category category) {
        this.id = category.getId();
        // Customize it here if you need, or not, firstName/lastName/etc
        this.name = category.getName();

        if (category.getScore() != null) {
            this.score = category.getScore();
        }

        if (category.getIsFirst() != null) {
            this.isFirst = category.getIsFirst();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setLogin(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Boolean isFirst) {
        this.isFirst = isFirst;
    }

    @Override
    public String toString() {
        return "CategoryNameAndIdDTO{" + "id=" + id + ", name='" + name + '\'' + ", score=" + score + ", isFirst=" + isFirst + '}';
    }
}
