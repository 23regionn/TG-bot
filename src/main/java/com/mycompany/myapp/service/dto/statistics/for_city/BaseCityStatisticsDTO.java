package com.mycompany.myapp.service.dto.statistics.for_city;

/**
 * A StatisticsBySearchTypesDTO.
 */
public class BaseCityStatisticsDTO {

    private Long idCity;
    private String nameCity;
    private Long countClickFirstPage;
    private Long countClickUsers;

    public BaseCityStatisticsDTO(Long idCity, Long countClickFirstPage, Long countClickUsers) {
        this.idCity = idCity;
        this.countClickFirstPage = countClickFirstPage;
        this.countClickUsers = countClickUsers;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public Long getCountClickFirstPage() {
        return countClickFirstPage;
    }

    public void setCountClickFirstPage(Long countClickFirstPage) {
        this.countClickFirstPage = countClickFirstPage;
    }

    public Long getCountClickUsers() {
        return countClickUsers;
    }

    public void setCountClickUsers(Long countClickUsers) {
        this.countClickUsers = countClickUsers;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
