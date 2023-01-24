package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Chanell.
 */
@Entity
@Table(name = "chanell")
public class Chanell implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "score")
    private Double score;

    @Column(name = "status")
    private String status;

    @Column(name = "count_subscribers")
    private Long countSubscribers;

    @Column(name = "quaility_from_another_sources")
    private Double quailityFromAnotherSources;

    @Column(name = "price_diapozon")
    private Double priceDiapozon;

    @Column(name = "is_moderate")
    private Boolean isModerate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "show_chanell_in_top_by_category")
    private Boolean showChanellInTopByCategory;

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "approved_admin")
    private Long approvedAdmin;

    /**
     * удаленный
     */
    @ApiModelProperty(value = "удаленный")
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * текущая дата
     */
    @ApiModelProperty(value = "текущая дата")
    @Column(name = "jhi_current_date")
    private ZonedDateTime currentDate;

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

    @OneToMany(mappedBy = "chanell")
    @JsonIgnoreProperties(value = { "linksByCategoryInTopLogs", "chanell", "categoryIds" }, allowSetters = true)
    private Set<LinksByCategoryInTop> linksByCategoryInTops = new HashSet<>();

    @OneToMany(mappedBy = "chanell")
    @JsonIgnoreProperties(value = { "chanell" }, allowSetters = true)
    private Set<ChanellLog> chanellLogs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "balance", "chanells", "offerFromCostumers", "reviews", "pays", "tGUserLogs" }, allowSetters = true)
    private TGUser tGUser;

    @ManyToMany(mappedBy = "chanellIds")
    @JsonIgnoreProperties(value = { "categoryLogs", "chanellIds", "linksByCategoryInTopIds" }, allowSetters = true)
    private Set<Category> categoryIds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chanell id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Chanell name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return this.link;
    }

    public Chanell link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Double getScore() {
        return this.score;
    }

    public Chanell score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getStatus() {
        return this.status;
    }

    public Chanell status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCountSubscribers() {
        return this.countSubscribers;
    }

    public Chanell countSubscribers(Long countSubscribers) {
        this.countSubscribers = countSubscribers;
        return this;
    }

    public void setCountSubscribers(Long countSubscribers) {
        this.countSubscribers = countSubscribers;
    }

    public Double getQuailityFromAnotherSources() {
        return this.quailityFromAnotherSources;
    }

    public Chanell quailityFromAnotherSources(Double quailityFromAnotherSources) {
        this.quailityFromAnotherSources = quailityFromAnotherSources;
        return this;
    }

    public void setQuailityFromAnotherSources(Double quailityFromAnotherSources) {
        this.quailityFromAnotherSources = quailityFromAnotherSources;
    }

    public Double getPriceDiapozon() {
        return this.priceDiapozon;
    }

    public Chanell priceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
        return this;
    }

    public void setPriceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
    }

    public Boolean getIsModerate() {
        return this.isModerate;
    }

    public Chanell isModerate(Boolean isModerate) {
        this.isModerate = isModerate;
        return this;
    }

    public void setIsModerate(Boolean isModerate) {
        this.isModerate = isModerate;
    }

    public Boolean getShowChanellInTopByCategory() {
        return this.showChanellInTopByCategory;
    }

    public Chanell showChanellInTopByCategory(Boolean showChanellInTopByCategory) {
        this.showChanellInTopByCategory = showChanellInTopByCategory;
        return this;
    }

    public void setShowChanellInTopByCategory(Boolean showChanellInTopByCategory) {
        this.showChanellInTopByCategory = showChanellInTopByCategory;
    }

    public String getRegion() {
        return this.region;
    }

    public Chanell region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return this.city;
    }

    public Chanell city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public Chanell isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getCurrentDate() {
        return this.currentDate;
    }

    public Chanell currentDate(ZonedDateTime currentDate) {
        this.currentDate = currentDate;
        return this;
    }

    public void setCurrentDate(ZonedDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public Chanell date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public Chanell date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public Chanell long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public Chanell string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public Chanell boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Set<LinksByCategoryInTop> getLinksByCategoryInTops() {
        return this.linksByCategoryInTops;
    }

    public Chanell linksByCategoryInTops(Set<LinksByCategoryInTop> linksByCategoryInTops) {
        this.setLinksByCategoryInTops(linksByCategoryInTops);
        return this;
    }

    public Chanell addLinksByCategoryInTop(LinksByCategoryInTop linksByCategoryInTop) {
        this.linksByCategoryInTops.add(linksByCategoryInTop);
        linksByCategoryInTop.setChanell(this);
        return this;
    }

    public Chanell removeLinksByCategoryInTop(LinksByCategoryInTop linksByCategoryInTop) {
        this.linksByCategoryInTops.remove(linksByCategoryInTop);
        linksByCategoryInTop.setChanell(null);
        return this;
    }

    public void setLinksByCategoryInTops(Set<LinksByCategoryInTop> linksByCategoryInTops) {
        if (this.linksByCategoryInTops != null) {
            this.linksByCategoryInTops.forEach(i -> i.setChanell(null));
        }
        if (linksByCategoryInTops != null) {
            linksByCategoryInTops.forEach(i -> i.setChanell(this));
        }
        this.linksByCategoryInTops = linksByCategoryInTops;
    }

    public Set<ChanellLog> getChanellLogs() {
        return this.chanellLogs;
    }

    public Chanell chanellLogs(Set<ChanellLog> chanellLogs) {
        this.setChanellLogs(chanellLogs);
        return this;
    }

    public Chanell addChanellLog(ChanellLog chanellLog) {
        this.chanellLogs.add(chanellLog);
        chanellLog.setChanell(this);
        return this;
    }

    public Chanell removeChanellLog(ChanellLog chanellLog) {
        this.chanellLogs.remove(chanellLog);
        chanellLog.setChanell(null);
        return this;
    }

    public void setChanellLogs(Set<ChanellLog> chanellLogs) {
        if (this.chanellLogs != null) {
            this.chanellLogs.forEach(i -> i.setChanell(null));
        }
        if (chanellLogs != null) {
            chanellLogs.forEach(i -> i.setChanell(this));
        }
        this.chanellLogs = chanellLogs;
    }

    public TGUser getTGUser() {
        return this.tGUser;
    }

    public Chanell tGUser(TGUser tGUser) {
        this.setTGUser(tGUser);
        return this;
    }

    public Boolean getModerate() {
        return isModerate;
    }

    public void setModerate(Boolean moderate) {
        isModerate = moderate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public TGUser gettGUser() {
        return tGUser;
    }

    public void settGUser(TGUser tGUser) {
        this.tGUser = tGUser;
    }

    public Chanell isActive(Boolean isActive) {
        this.setActive(isActive);
        return this;
    }

    public void setTGUser(TGUser tGUser) {
        this.tGUser = tGUser;
    }

    public Set<Category> getCategoryIds() {
        return this.categoryIds;
    }

    public Chanell categoryIds(Set<Category> categories) {
        this.setCategoryIds(categories);
        return this;
    }

    public Chanell addCategoryId(Category category) {
        this.categoryIds.add(category);
        category.getChanellIds().add(this);
        return this;
    }

    public Chanell removeCategoryId(Category category) {
        this.categoryIds.remove(category);
        category.getChanellIds().remove(this);
        return this;
    }

    public void setCategoryIds(Set<Category> categories) {
        if (this.categoryIds != null) {
            this.categoryIds.forEach(i -> i.removeChanellId(this));
        }
        if (categories != null) {
            categories.forEach(i -> i.addChanellId(this));
        }
        this.categoryIds = categories;
    }

    public Long getApprovedAdmin() {
        return approvedAdmin;
    }

    public void setApprovedAdmin(Long approvedAdmin) {
        this.approvedAdmin = approvedAdmin;
    }

    public Chanell approvedAdmin(Long approvedAdmin) {
        this.setApprovedAdmin(approvedAdmin);
        return this;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Chanell)) {
            return false;
        }
        return id != null && id.equals(((Chanell) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
    // prettier-ignore
    @Override
    public String toString() {
        return "Chanell{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", link='" + getLink() + "'" +
            ", score=" + getScore() +
            ", status='" + getStatus() + "'" +
            ", countSubscribers=" + getCountSubscribers() +
            ", quailityFromAnotherSources=" + getQuailityFromAnotherSources() +
            ", priceDiapozon=" + getPriceDiapozon() +
            ", isModerate='" + getIsModerate() + "'" +
            ", showChanellInTopByCategory='" + getShowChanellInTopByCategory() + "'" +
            ", region='" + getRegion() + "'" +
            ", city='" + getCity() + "'" +
            ", approvedAdmin=" + approvedAdmin +
            ", isDelete='" + getIsDelete() + "'" +
            ", currentDate='" + getCurrentDate() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
