package com.mycompany.myapp.service.dto.statistics.for_city;

/**
 * A CityLogForUserDTO.
 */
public class CityLogForUserDTO {

    private Long idCity;
    private String cityName;
    private Long countClickTotal;

    public CityLogForUserDTO() {}

    public CityLogForUserDTO(Long idCity, String cityName, Long countClickTotal) {
        this.idCity = idCity;
        this.cityName = cityName;
        this.countClickTotal = countClickTotal;
    }

    public Long getIdCity() {
        return idCity;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCountClickTotal() {
        return countClickTotal;
    }

    public void setCountClickTotal(Long countClickTotal) {
        this.countClickTotal = countClickTotal;
    }

    @Override
    public String toString() {
        return "CityLogForUserDTO{" + "idCity=" + idCity + ", countClickTotal=" + countClickTotal + '}';
    }
}
