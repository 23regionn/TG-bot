package com.mycompany.myapp.service.dto.relCategoryCity;

/**
 * A RelCategoryCityInfoDTO.
 */

public class RelCategoryCityInfoDTO {

    private Long id;
    private String nameCategory;
    private String nameCity;

    public RelCategoryCityInfoDTO(Long id, String nameCategory, String nameCity) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.nameCity = nameCity;
    }

    public RelCategoryCityInfoDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
