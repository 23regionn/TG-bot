package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A RelCategoryCity.
 */
@Entity
@Table(name = "rel_category_city")
public class RelCategoryCity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "is_show")
    private Boolean isShow = false;

    @NotNull
    @Column(name = "score")
    private Double score = 10000.0;

    @NotNull
    @Column(name = "is_first")
    private Boolean isFirst = false;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = { "relCategoryCities" }, allowSetters = true)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnoreProperties(value = { "relCategoryCities" }, allowSetters = true)
    private City city;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "relCategoryCity")
    @JsonIgnoreProperties(value = { "relCategoryCity" }, allowSetters = true)
    private Set<RelCategoryCityChannels> relCategoryCityChannels = new HashSet<>();

    public Set<RelCategoryCityChannels> getRelCategoryCityChannels() {
        return relCategoryCityChannels;
    }

    public void setRelCategoryCityChannels(Set<RelCategoryCityChannels> relCategoryCityChannels) {
        this.relCategoryCityChannels = relCategoryCityChannels;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RelCategoryCity id(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getIsShow() {
        return this.isShow;
    }

    public RelCategoryCity isShow(Boolean isShow) {
        this.isShow = isShow;
        return this;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

    public Double getScore() {
        return this.score;
    }

    public RelCategoryCity score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getIsFirst() {
        return this.isFirst;
    }

    public RelCategoryCity isFirst(Boolean isFirst) {
        this.isFirst = isFirst;
        return this;
    }

    public void setIsFirst(Boolean isFirst) {
        this.isFirst = isFirst;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelCategoryCity)) {
            return false;
        }
        return id != null && id.equals(((RelCategoryCity) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelCategoryCity{" +
            "id=" + getId() +
            ", isShow='" + getIsShow() + "'" +
            ", score=" + getScore() +
            ", isFirst='" + getIsFirst() + "'" +
            "}";
    }
}
