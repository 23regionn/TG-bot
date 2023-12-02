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

    @Column(name = "show_chanell_in_top_by_category")
    private Boolean showChanellInTopByCategory;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "approved_admin")
    private Long approvedAdmin;

    @OneToMany(mappedBy = "chanell")
    @JsonIgnoreProperties(value = { "linksByCategoryInTopLogs", "chanell", "categoryIds" }, allowSetters = true)
    private Set<LinksByCategoryInTop> linksByCategoryInTops = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnoreProperties(value = { "chanells" }, allowSetters = true)
    private City cityEntity;

    @OneToMany(mappedBy = "chanell")
    @JsonIgnoreProperties(value = { "chanell" }, allowSetters = true)
    private Set<RelCategoryChannels> relCategoryChannels = new HashSet<>();

    @OneToMany(mappedBy = "chanell")
    @JsonIgnoreProperties(value = { "chanell" }, allowSetters = true)
    private Set<RelCategoryCityChannels> relCategoryCityChannels = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnoreProperties(value = { "chanells" }, allowSetters = true)
    private Manager manager;

    @OneToMany(mappedBy = "chanell")
    @JsonIgnoreProperties(value = { "chanell" }, allowSetters = true)
    private Set<ChanellLog> chanellLogs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "balance", "chanells", "offerFromCostumers", "reviews", "pays", "tGUserLogs" }, allowSetters = true)
    private TGUser tGUser;

    @ManyToMany(mappedBy = "chanellIds")
    @JsonIgnoreProperties(value = { "categoryLogs", "chanellIds", "linksByCategoryInTopIds" }, allowSetters = true)
    private Set<Category> categoryIds = new HashSet<>();

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "last_pay_date")
    private ZonedDateTime lastPayDate;

    @Column(name = "end_public_date")
    private ZonedDateTime endPublicDate;

    @Column(name = "comment")
    private String comment;

    @Column(name = "price_for_pay")
    private Double priceForPay;

    @Column(name = "is_pay")
    private Boolean isPay;

    @Column(name = "is_chat")
    private Boolean isChat;

    public Boolean getIsChat() {
        return isChat;
    }

    public void setIsChat(Boolean isChat) {
        this.isChat = isChat;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Set<RelCategoryCityChannels> getRelCategoryCityChannels() {
        return relCategoryCityChannels;
    }

    public void setRelCategoryCityChannels(Set<RelCategoryCityChannels> relCategoryCityChannels) {
        this.relCategoryCityChannels = relCategoryCityChannels;
    }

    public Set<RelCategoryChannels> getRelCategoryChannels() {
        return relCategoryChannels;
    }

    public void setRelCategoryChannels(Set<RelCategoryChannels> relCategoryChannels) {
        this.relCategoryChannels = relCategoryChannels;
    }

    public Double getPriceForPay() {
        return priceForPay;
    }

    public void setPriceForPay(Double priceForPay) {
        this.priceForPay = priceForPay;
    }

    public Boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(Boolean pay) {
        isPay = pay;
    }

    public City getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(City cityId) {
        this.cityEntity = cityId;
    }

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

    public TGUser gettGUser() {
        return tGUser;
    }

    public void settGUser(TGUser tGUser) {
        this.tGUser = tGUser;
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

    public String getContacts() {
        return contacts;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getLastPayDate() {
        return lastPayDate;
    }

    public void setLastPayDate(ZonedDateTime lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public ZonedDateTime getEndPublicDate() {
        return endPublicDate;
    }

    public void setEndPublicDate(ZonedDateTime endPublicDate) {
        this.endPublicDate = endPublicDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
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
            ", approvedAdmin=" + approvedAdmin +
            ", contacts='" + getContacts() + "'" +
            ", cityId='" + getCityEntity() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", lastPayDate='" + getLastPayDate() + "'" +
            ", endPublicDate='" + getEndPublicDate() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}
