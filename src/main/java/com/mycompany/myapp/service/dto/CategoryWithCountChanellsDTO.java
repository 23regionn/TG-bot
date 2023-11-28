package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.Category;
import java.util.Objects;

public class CategoryWithCountChanellsDTO {

    String name;
    long id;

    //    Long long1 = 10000l;
    long countChannelsInCategory;

    public CategoryWithCountChanellsDTO(Category category) {
        if (category == null) {
            return;
        }
        if (category.getName() != null) {
            this.name = category.getName();
        }
        if (category.getId() != null) {
            this.id = category.getId();
        }

        /*if(category.getLong1() != null){
            this.long1 = category.getLong1();
        }*/
        if (category.getChanellIds() != null) {
            countChannelsInCategory = category.getChanellIds().size();
        }
    }

    public CategoryWithCountChanellsDTO(Category category, long countChan) {
        if (category == null) {
            return;
        }
        if (category.getName() != null) {
            this.name = category.getName();
        }
        if (category.getId() != null) {
            this.id = category.getId();
        }

        /*if(category.getLong1() != null){
            this.long1 = category.getLong1();
        }*/
        if (category.getChanellIds() != null) {
            this.countChannelsInCategory = countChan;
        }
    }

    public long getCountChannelsInCategory() {
        return countChannelsInCategory;
    }

    public void setCountChannelsInCategory(long countChannelsInCategory) {
        this.countChannelsInCategory = countChannelsInCategory;
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
        return (
            "CategoryWithCountChanellsDTO{" +
            "name='" +
            name +
            '\'' +
            ", id=" +
            id +
            //            ", long1=" + long1 +
            ", countChannelsInCategory=" +
            countChannelsInCategory +
            '}'
        );
    }
}
