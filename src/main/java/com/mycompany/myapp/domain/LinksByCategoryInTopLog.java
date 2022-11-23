package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A LinksByCategoryInTopLog.
 */
@Entity
@Table(name = "links_by_category_in_top_log")
public class LinksByCategoryInTopLog implements Serializable {

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "linksByCategoryInTopLogs", "chanell", "categoryIds" }, allowSetters = true)
    private LinksByCategoryInTop linksByCategoryInTop;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LinksByCategoryInTopLog id(Long id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public LinksByCategoryInTopLog category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPriceDiapozon() {
        return this.priceDiapozon;
    }

    public LinksByCategoryInTopLog priceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
        return this;
    }

    public void setPriceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
    }

    public String getLink() {
        return this.link;
    }

    public LinksByCategoryInTopLog link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getChanellAdminId() {
        return this.chanellAdminId;
    }

    public LinksByCategoryInTopLog chanellAdminId(Long chanellAdminId) {
        this.chanellAdminId = chanellAdminId;
        return this;
    }

    public void setChanellAdminId(Long chanellAdminId) {
        this.chanellAdminId = chanellAdminId;
    }

    public ZonedDateTime getDatePostLinkStart() {
        return this.datePostLinkStart;
    }

    public LinksByCategoryInTopLog datePostLinkStart(ZonedDateTime datePostLinkStart) {
        this.datePostLinkStart = datePostLinkStart;
        return this;
    }

    public void setDatePostLinkStart(ZonedDateTime datePostLinkStart) {
        this.datePostLinkStart = datePostLinkStart;
    }

    public ZonedDateTime getDatePostLinkEnd() {
        return this.datePostLinkEnd;
    }

    public LinksByCategoryInTopLog datePostLinkEnd(ZonedDateTime datePostLinkEnd) {
        this.datePostLinkEnd = datePostLinkEnd;
        return this;
    }

    public void setDatePostLinkEnd(ZonedDateTime datePostLinkEnd) {
        this.datePostLinkEnd = datePostLinkEnd;
    }

    public Long getPositionBetweenLinks() {
        return this.positionBetweenLinks;
    }

    public LinksByCategoryInTopLog positionBetweenLinks(Long positionBetweenLinks) {
        this.positionBetweenLinks = positionBetweenLinks;
        return this;
    }

    public void setPositionBetweenLinks(Long positionBetweenLinks) {
        this.positionBetweenLinks = positionBetweenLinks;
    }

    public Boolean getShowLink() {
        return this.showLink;
    }

    public LinksByCategoryInTopLog showLink(Boolean showLink) {
        this.showLink = showLink;
        return this;
    }

    public void setShowLink(Boolean showLink) {
        this.showLink = showLink;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public LinksByCategoryInTopLog isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public LinksByCategoryInTopLog date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public LinksByCategoryInTopLog date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public LinksByCategoryInTopLog long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public LinksByCategoryInTopLog string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public LinksByCategoryInTopLog boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public LinksByCategoryInTop getLinksByCategoryInTop() {
        return this.linksByCategoryInTop;
    }

    public LinksByCategoryInTopLog linksByCategoryInTop(LinksByCategoryInTop linksByCategoryInTop) {
        this.setLinksByCategoryInTop(linksByCategoryInTop);
        return this;
    }

    public void setLinksByCategoryInTop(LinksByCategoryInTop linksByCategoryInTop) {
        this.linksByCategoryInTop = linksByCategoryInTop;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinksByCategoryInTopLog)) {
            return false;
        }
        return id != null && id.equals(((LinksByCategoryInTopLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LinksByCategoryInTopLog{" +
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
