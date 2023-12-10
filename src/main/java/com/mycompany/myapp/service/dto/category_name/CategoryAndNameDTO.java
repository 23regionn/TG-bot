package com.mycompany.myapp.service.dto.category_name;

import com.mycompany.myapp.domain.Category;

/**
 * A DTO CategoryAndNameDTO.
 */
public class CategoryAndNameDTO {

    private Long idCat = 1l;

    private String nameCat = " ";

    private Long idCity = 1l;
    private String nameCity = " ";

    public CategoryAndNameDTO() {}

    public CategoryAndNameDTO(Long idCat, String nameCat, Long idCity, String nameCity) {
        this.idCat = idCat;
        this.nameCat = nameCat;
        this.idCity = idCity;
        this.nameCity = nameCity;
    }

    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    @Override
    public String toString() {
        return (
            "CategoryAndNameDTO{" +
            "idCat=" +
            idCat +
            ", nameCat='" +
            nameCat +
            '\'' +
            ", idCity=" +
            idCity +
            ", nameCity='" +
            nameCity +
            '\'' +
            '}'
        );
    }
}
