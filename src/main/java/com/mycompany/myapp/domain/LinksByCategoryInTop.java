package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A LinksByCategoryInTop.
 */
@Entity
@Table(name = "links_by_category_in_top")
public class LinksByCategoryInTop implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * категория
     */
    @ApiModelProperty(value = "категория")
    @Column(name = "category")
    private String category;

    /**
     * ценовой диапозон
     */
    @ApiModelProperty(value = "ценовой диапозон")
    @Column(name = "price_diapozon")
    private Double priceDiapozon;

    /**
     * ссылку на канал
     */
    @ApiModelProperty(value = "ссылку на канал")
    @Column(name = "link")
    private String link;

    /**
     * ссылка на админа
     */
    @ApiModelProperty(value = "ссылка на админа")
    @Column(name = "chanell_admin_id")
    private Long chanellAdminId;

    /**
     * дата размещения ссылки
     */
    @ApiModelProperty(value = "дата размещения ссылки")
    @Column(name = "date_post_link_start")
    private ZonedDateTime datePostLinkStart;

    /**
     * дата окончания размещения
     */
    @ApiModelProperty(value = "дата окончания размещения")
    @Column(name = "date_post_link_end")
    private ZonedDateTime datePostLinkEnd;

    /**
     * место среди ссылок
     */
    @ApiModelProperty(value = "место среди ссылок")
    @Column(name = "position_between_links")
    private Long positionBetweenLinks;

    @Column(name = "show_link")
    private Boolean showLink;

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
    private Long long1;

    @Column(name = "string_1")
    private String string1;

    @Column(name = "boolean_1")
    private Boolean boolean1;

    @OneToMany(mappedBy = "linksByCategoryInTop")
    @JsonIgnoreProperties(value = { "linksByCategoryInTop" }, allowSetters = true)
    private Set<LinksByCategoryInTopLog> linksByCategoryInTopLogs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "linksByCategoryInTops", "chanellLogs", "tGUser", "categoryIds" }, allowSetters = true)
    private Chanell chanell;

    @ManyToMany(mappedBy = "linksByCategoryInTopIds")
    @JsonIgnoreProperties(value = { "categoryLogs", "chanellIds", "linksByCategoryInTopIds" }, allowSetters = true)
    private Set<Category> categoryIds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LinksByCategoryInTop id(Long id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public LinksByCategoryInTop category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPriceDiapozon() {
        return this.priceDiapozon;
    }

    public LinksByCategoryInTop priceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
        return this;
    }

    public void setPriceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
    }

    public String getLink() {
        return this.link;
    }

    public LinksByCategoryInTop link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getChanellAdminId() {
        return this.chanellAdminId;
    }

    public LinksByCategoryInTop chanellAdminId(Long chanellAdminId) {
        this.chanellAdminId = chanellAdminId;
        return this;
    }

    public void setChanellAdminId(Long chanellAdminId) {
        this.chanellAdminId = chanellAdminId;
    }

    public ZonedDateTime getDatePostLinkStart() {
        return this.datePostLinkStart;
    }

    public LinksByCategoryInTop datePostLinkStart(ZonedDateTime datePostLinkStart) {
        this.datePostLinkStart = datePostLinkStart;
        return this;
    }

    public void setDatePostLinkStart(ZonedDateTime datePostLinkStart) {
        this.datePostLinkStart = datePostLinkStart;
    }

    public ZonedDateTime getDatePostLinkEnd() {
        return this.datePostLinkEnd;
    }

    public LinksByCategoryInTop datePostLinkEnd(ZonedDateTime datePostLinkEnd) {
        this.datePostLinkEnd = datePostLinkEnd;
        return this;
    }

    public void setDatePostLinkEnd(ZonedDateTime datePostLinkEnd) {
        this.datePostLinkEnd = datePostLinkEnd;
    }

    public Long getPositionBetweenLinks() {
        return this.positionBetweenLinks;
    }

    public LinksByCategoryInTop positionBetweenLinks(Long positionBetweenLinks) {
        this.positionBetweenLinks = positionBetweenLinks;
        return this;
    }

    public void setPositionBetweenLinks(Long positionBetweenLinks) {
        this.positionBetweenLinks = positionBetweenLinks;
    }

    public Boolean getShowLink() {
        return this.showLink;
    }

    public LinksByCategoryInTop showLink(Boolean showLink) {
        this.showLink = showLink;
        return this;
    }

    public void setShowLink(Boolean showLink) {
        this.showLink = showLink;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public LinksByCategoryInTop isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public LinksByCategoryInTop date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public LinksByCategoryInTop date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public LinksByCategoryInTop long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public LinksByCategoryInTop string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public LinksByCategoryInTop boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Set<LinksByCategoryInTopLog> getLinksByCategoryInTopLogs() {
        return this.linksByCategoryInTopLogs;
    }

    public LinksByCategoryInTop linksByCategoryInTopLogs(Set<LinksByCategoryInTopLog> linksByCategoryInTopLogs) {
        this.setLinksByCategoryInTopLogs(linksByCategoryInTopLogs);
        return this;
    }

    public LinksByCategoryInTop addLinksByCategoryInTopLog(LinksByCategoryInTopLog linksByCategoryInTopLog) {
        this.linksByCategoryInTopLogs.add(linksByCategoryInTopLog);
        linksByCategoryInTopLog.setLinksByCategoryInTop(this);
        return this;
    }

    public LinksByCategoryInTop removeLinksByCategoryInTopLog(LinksByCategoryInTopLog linksByCategoryInTopLog) {
        this.linksByCategoryInTopLogs.remove(linksByCategoryInTopLog);
        linksByCategoryInTopLog.setLinksByCategoryInTop(null);
        return this;
    }

    public void setLinksByCategoryInTopLogs(Set<LinksByCategoryInTopLog> linksByCategoryInTopLogs) {
        if (this.linksByCategoryInTopLogs != null) {
            this.linksByCategoryInTopLogs.forEach(i -> i.setLinksByCategoryInTop(null));
        }
        if (linksByCategoryInTopLogs != null) {
            linksByCategoryInTopLogs.forEach(i -> i.setLinksByCategoryInTop(this));
        }
        this.linksByCategoryInTopLogs = linksByCategoryInTopLogs;
    }

    public Chanell getChanell() {
        return this.chanell;
    }

    public LinksByCategoryInTop chanell(Chanell chanell) {
        this.setChanell(chanell);
        return this;
    }

    public void setChanell(Chanell chanell) {
        this.chanell = chanell;
    }

    public Set<Category> getCategoryIds() {
        return this.categoryIds;
    }

    public LinksByCategoryInTop categoryIds(Set<Category> categories) {
        this.setCategoryIds(categories);
        return this;
    }

    public LinksByCategoryInTop addCategoryId(Category category) {
        this.categoryIds.add(category);
        category.getLinksByCategoryInTopIds().add(this);
        return this;
    }

    public LinksByCategoryInTop removeCategoryId(Category category) {
        this.categoryIds.remove(category);
        category.getLinksByCategoryInTopIds().remove(this);
        return this;
    }

    public void setCategoryIds(Set<Category> categories) {
        if (this.categoryIds != null) {
            this.categoryIds.forEach(i -> i.removeLinksByCategoryInTopId(this));
        }
        if (categories != null) {
            categories.forEach(i -> i.addLinksByCategoryInTopId(this));
        }
        this.categoryIds = categories;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinksByCategoryInTop)) {
            return false;
        }
        return id != null && id.equals(((LinksByCategoryInTop) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LinksByCategoryInTop{" +
            "id=" + getId() +
            ", category='" + getCategory() + "'" +
            ", priceDiapozon=" + getPriceDiapozon() +
            ", link='" + getLink() + "'" +
            ", chanellAdminId=" + getChanellAdminId() +
            ", datePostLinkStart='" + getDatePostLinkStart() + "'" +
            ", datePostLinkEnd='" + getDatePostLinkEnd() + "'" +
            ", positionBetweenLinks=" + getPositionBetweenLinks() +
            ", showLink='" + getShowLink() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
