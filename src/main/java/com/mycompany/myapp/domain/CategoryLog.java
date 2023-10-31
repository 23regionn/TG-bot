package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A CategoryLog.
 */
@Entity
@Table(name = "category_log")
public class CategoryLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "cat_id")
    private Long catId;

    @Column(name = "date_log")
    private ZonedDateTime dateLog;

    @Column(name = "score")
    private Double score;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryLog id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public CategoryLog name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public ZonedDateTime getDateLog() {
        return dateLog;
    }

    public void setDateLog(ZonedDateTime dateLog) {
        this.dateLog = dateLog;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryLog)) {
            return false;
        }
        return id != null && id.equals(((CategoryLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return (
            "CategoryLog{" +
            "id=" +
            id +
            ", name='" +
            name +
            '\'' +
            ", cityId=" +
            cityId +
            ", cityName='" +
            cityName +
            '\'' +
            ", chatId=" +
            chatId +
            ", catId=" +
            catId +
            ", dateLog=" +
            dateLog +
            ", score=" +
            score +
            '}'
        );
    }
}
