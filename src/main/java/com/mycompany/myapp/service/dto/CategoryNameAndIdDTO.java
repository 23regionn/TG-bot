package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.User;

/**
 * A DTO CategoryNameAndIdDTO.
 */
public class CategoryNameAndIdDTO {

    private Long id;

    private String name;

    private Long long1 = 10000l;

    public CategoryNameAndIdDTO() {
        // Empty constructor needed for Jackson.
    }

    public CategoryNameAndIdDTO(Category category) {
        this.id = category.getId();
        // Customize it here if you need, or not, firstName/lastName/etc
        this.name = category.getName();

        if(category.getLong1() != null){
            this.long1 = category.getLong1();
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

    public Long getLong1() {
        return long1;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    @Override
    public String toString() {
        return "CategoryNameAndIdDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", long1=" + long1 +
            '}';
    }
}
