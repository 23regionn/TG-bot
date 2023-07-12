package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

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

    @Column(name = "count_chanell_in_category")
    private Long countChanellInCategory;

    /**
     * удаленный
     */
    @ApiModelProperty(value = "удаленный")
    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "date_1")
    private ZonedDateTime date1;

    @Column(name = "date_2")
    private ZonedDateTime date2;

    @Column(name = "long_1")
    private Long long1;      // Рейтинг

    @Column(name = "string_1")
    private String string1;

    @Column(name = "boolean_1")
    private Boolean boolean1; // отображать или нет

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private Set<CategoryLog> categoryLogs = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_category__chanell_id",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "chanell_id_id")
    )
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

    public Long getCountChanellInCategory() {
        return this.countChanellInCategory;
    }

    public Category countChanellInCategory(Long countChanellInCategory) {
        this.countChanellInCategory = countChanellInCategory;
        return this;
    }

    public void setCountChanellInCategory(Long countChanellInCategory) {
        this.countChanellInCategory = countChanellInCategory;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public Category isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public Category date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public Category date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public Category long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public Category string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public Category boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Set<CategoryLog> getCategoryLogs() {
        return this.categoryLogs;
    }

    public Category categoryLogs(Set<CategoryLog> categoryLogs) {
        this.setCategoryLogs(categoryLogs);
        return this;
    }

    public Category addCategoryLog(CategoryLog categoryLog) {
        this.categoryLogs.add(categoryLog);
        categoryLog.setCategory(this);
        return this;
    }

    public Category removeCategoryLog(CategoryLog categoryLog) {
        this.categoryLogs.remove(categoryLog);
        categoryLog.setCategory(null);
        return this;
    }

    public void setCategoryLogs(Set<CategoryLog> categoryLogs) {
        if (this.categoryLogs != null) {
            this.categoryLogs.forEach(i -> i.setCategory(null));
        }
        if (categoryLogs != null) {
            categoryLogs.forEach(i -> i.setCategory(this));
        }
        this.categoryLogs = categoryLogs;
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

    // prettier-ignore
    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", countChanellInCategory=" + getCountChanellInCategory() +
            ", isDelete='" + getIsDelete() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
