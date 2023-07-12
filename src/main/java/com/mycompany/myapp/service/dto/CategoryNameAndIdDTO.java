package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.User;

/**
 * A DTO CategoryNameAndIdDTO.
 */
public class CategoryNameAndIdDTO {

    private Long id;

    private String name;

    public CategoryNameAndIdDTO() {
        // Empty constructor needed for Jackson.
    }

    public CategoryNameAndIdDTO(Category category) {
        this.id = category.getId();
        // Customize it here if you need, or not, firstName/lastName/etc
        this.name = category.getName();
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

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryNameAndIdDTO{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            "}";
    }
}
