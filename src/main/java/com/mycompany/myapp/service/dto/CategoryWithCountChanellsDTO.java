package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Category;

import java.util.Objects;

public class CategoryWithCountChanellsDTO {

    String name;
    long id;
//    long countChannelsInCategory;


    public CategoryWithCountChanellsDTO(Category category){
        if(category == null){
            return;
        }
        if(category.getName() != null){
            this.name = category.getName();
        }
        if(category.getId() != null){
            this.id = category.getId();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryWithCountChanellsDTO that = (CategoryWithCountChanellsDTO) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "CategoryWithCountChanellsDTO{" +
            "name='" + name + '\'' +
            ", id=" + id +
            '}';
    }
}
