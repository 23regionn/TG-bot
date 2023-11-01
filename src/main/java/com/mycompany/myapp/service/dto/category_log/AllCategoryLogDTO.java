package com.mycompany.myapp.service.dto.category_log;

/**
 * A AllCategoryLogDTO.
 */
public class AllCategoryLogDTO {

    private Long idCategory;
    private String categoryName;
    private Long countClickTotal;
    private Long countClickTotalByUniChatId;
    private Long countClickByCity;
    private Long countClickNotByCity;

    public AllCategoryLogDTO(
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

    @Override
    public String toString() {
        return (
            "AllCategoryLogDTO{" +
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
            '}'
        );
    }
}
