package com.mycompany.myapp.service.dto.relCategoryCity;

/**
 * A RelCategoryCityCreateDTO.
 */
public class RelCategoryCityCreateDTO {

    private Double score;
    private Boolean isShow;
    private Boolean isFirst;
    private Long idCity;
    private Long idCat;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public Double getScore() {
        return this.score;
    }

    public RelCategoryCityCreateDTO score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getIsShow() {
        return this.isShow;
    }

    public RelCategoryCityCreateDTO isShow(Boolean isShow) {
        this.isShow = isShow;
        return this;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Boolean getIsFirst() {
        return this.isFirst;
    }

    public RelCategoryCityCreateDTO isFirst(Boolean isFirst) {
        this.isFirst = isFirst;
        return this;
    }

    public void setIsFirst(Boolean isFirst) {
        this.isFirst = isFirst;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    @Override
    public String toString() {
        return (
            "RelCategoryCityCreateDTO{" +
            "score=" +
            score +
            ", isShow=" +
            isShow +
            ", isFirst=" +
            isFirst +
            ", idCity=" +
            idCity +
            ", idCat=" +
            idCat +
            ", comment='" +
            comment +
            '\'' +
            '}'
        );
    }
}
