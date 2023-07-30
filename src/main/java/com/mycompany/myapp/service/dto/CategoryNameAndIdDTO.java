package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.User;

/**
 * A DTO CategoryNameAndIdDTO.
 */
public class CategoryNameAndIdDTO {

    private Long id;

    private String name;
    private Double score = 10000.0;

    private Boolean isFirst;

    public CategoryNameAndIdDTO() {
        // Empty constructor needed for Jackson.
    }

    public CategoryNameAndIdDTO(Category category) {
        this.id = category.getId();
        // Customize it here if you need, or not, firstName/lastName/etc
        this.name = category.getName();

        if(category.getScore() != null){
            this.score = category.getScore();
        }

        if(category.getFirst() != null){
            this.isFirst = category.getFirst();
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

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }

    @Override
    public String toString() {
        return "CategoryNameAndIdDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", score=" + score +
            ", isFirst=" + isFirst +
            '}';
    }
}
