package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "is_show")
    private Boolean isShow = false; // отображать или нет

    @NotNull
    @Column(name = "is_first")
    private Boolean isFirst = false;

    @NotNull
    @Column(name = "score")
    private Double score = 10000.0; // Рейтинг

    @ManyToMany
    @JoinTable(
        name = "rel_category__chanell_id",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "chanell_id_id")
    )
    //    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnoreProperties(value = { "linksByCategoryInTops", "chanellLogs", "tGUser", "categoryIds" }, allowSetters = true)
    private Set<Chanell> chanellIds = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_category__links_by_category_in_top_id",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "links_by_category_in_top_id_id")
    )
    @JsonIgnoreProperties(value = { "linksByCategoryInTopLogs", "chanell", "categoryIds" }, allowSetters = true)
    private Set<LinksByCategoryInTop> linksByCategoryInTopIds = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private Set<RelCategoryChannels> relCategoryChannels = new HashSet<>();

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private Set<RelCategoryCity> relCategoryCities = new HashSet<>();

    public Set<RelCategoryCity> getRelCategoryCities() {
        return relCategoryCities;
    }

    public void setRelCategoryCities(Set<RelCategoryCity> relCategoryCities) {
        this.relCategoryCities = relCategoryCities;
    }

    public Set<RelCategoryChannels> getRelCategoryChannels() {
        return relCategoryChannels;
    }

    public void setRelCategoryChannels(Set<RelCategoryChannels> relCategoryChannels) {
        this.relCategoryChannels = relCategoryChannels;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Chanell> getChanellIds() {
        return this.chanellIds;
    }

    public Category chanellIds(Set<Chanell> chanells) {
        this.setChanellIds(chanells);
        return this;
    }

    public Category addChanellId(Chanell chanell) {
        this.chanellIds.add(chanell);
        chanell.getCategoryIds().add(this);
        return this;
    }

    public Category removeChanellId(Chanell chanell) {
        this.chanellIds.remove(chanell);
        chanell.getCategoryIds().remove(this);
        return this;
    }

    public void setChanellIds(Set<Chanell> chanells) {
        this.chanellIds = chanells;
    }

    public Set<LinksByCategoryInTop> getLinksByCategoryInTopIds() {
        return this.linksByCategoryInTopIds;
    }

    public Category linksByCategoryInTopIds(Set<LinksByCategoryInTop> linksByCategoryInTops) {
        this.setLinksByCategoryInTopIds(linksByCategoryInTops);
        return this;
    }

    public Category addLinksByCategoryInTopId(LinksByCategoryInTop linksByCategoryInTop) {
        this.linksByCategoryInTopIds.add(linksByCategoryInTop);
        linksByCategoryInTop.getCategoryIds().add(this);
        return this;
    }

    public Category removeLinksByCategoryInTopId(LinksByCategoryInTop linksByCategoryInTop) {
        this.linksByCategoryInTopIds.remove(linksByCategoryInTop);
        linksByCategoryInTop.getCategoryIds().remove(this);
        return this;
    }

    public void setLinksByCategoryInTopIds(Set<LinksByCategoryInTop> linksByCategoryInTops) {
        this.linksByCategoryInTopIds = linksByCategoryInTops;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean show) {
        isShow = show;
    }

    public Boolean getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Boolean first) {
        isFirst = first;
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
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return (
            "Category{" + "id=" + id + ", name='" + name + '\'' + ", isShow=" + isShow + ", isFirst=" + isFirst + ", score=" + score + '}'
        );
    }
}
