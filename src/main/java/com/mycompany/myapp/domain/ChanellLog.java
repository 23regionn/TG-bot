package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A ChanellLog.
 */
@Entity
@Table(name = "chanell_log")
public class ChanellLog implements Serializable {

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

    @Column(name = "region")
    private String region;

    @Column(name = "city")
    private String city;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "chan_id")
    private Long chanId;

    @Column(name = "cat_id")
    private Long catId;

    @Column(name = "date_log")
    private ZonedDateTime dateLog;

    @Column(name = "tg_bot_api")
    private Boolean tgBotApi;

    @Column(name = "web_api")
    private Boolean webApi;

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

    @ManyToOne
    @JsonIgnoreProperties(value = { "linksByCategoryInTops", "chanellLogs", "tGUser", "categoryIds" }, allowSetters = true)
    private Chanell chanell;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChanellLog id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public ChanellLog name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return this.link;
    }

    public ChanellLog link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Double getScore() {
        return this.score;
    }

    public ChanellLog score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getStatus() {
        return this.status;
    }

    public ChanellLog status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCountSubscribers() {
        return this.countSubscribers;
    }

    public ChanellLog countSubscribers(Long countSubscribers) {
        this.countSubscribers = countSubscribers;
        return this;
    }

    public void setCountSubscribers(Long countSubscribers) {
        this.countSubscribers = countSubscribers;
    }

    public Double getQuailityFromAnotherSources() {
        return this.quailityFromAnotherSources;
    }

    public ChanellLog quailityFromAnotherSources(Double quailityFromAnotherSources) {
        this.quailityFromAnotherSources = quailityFromAnotherSources;
        return this;
    }

    public void setQuailityFromAnotherSources(Double quailityFromAnotherSources) {
        this.quailityFromAnotherSources = quailityFromAnotherSources;
    }

    public Double getPriceDiapozon() {
        return this.priceDiapozon;
    }

    public ChanellLog priceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
        return this;
    }

    public void setPriceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
    }

    public Boolean getIsModerate() {
        return this.isModerate;
    }

    public ChanellLog isModerate(Boolean isModerate) {
        this.isModerate = isModerate;
        return this;
    }

    public void setIsModerate(Boolean isModerate) {
        this.isModerate = isModerate;
    }

    public Boolean getShowChanellInTopByCategory() {
        return this.showChanellInTopByCategory;
    }

    public ChanellLog showChanellInTopByCategory(Boolean showChanellInTopByCategory) {
        this.showChanellInTopByCategory = showChanellInTopByCategory;
        return this;
    }

    public void setShowChanellInTopByCategory(Boolean showChanellInTopByCategory) {
        this.showChanellInTopByCategory = showChanellInTopByCategory;
    }

    public String getRegion() {
        return this.region;
    }

    public ChanellLog region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return this.city;
    }

    public ChanellLog city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public ChanellLog isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getCurrentDate() {
        return this.currentDate;
    }

    public ChanellLog currentDate(ZonedDateTime currentDate) {
        this.currentDate = currentDate;
        return this;
    }

    public void setCurrentDate(ZonedDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public ChanellLog date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public ChanellLog date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public ChanellLog long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public ChanellLog string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public ChanellLog boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Chanell getChanell() {
        return this.chanell;
    }

    public ChanellLog chanell(Chanell chanell) {
        this.setChanell(chanell);
        return this;
    }

    public void setChanell(Chanell chanell) {
        this.chanell = chanell;
    }

    public Boolean getModerate() {
        return isModerate;
    }

    public void setModerate(Boolean moderate) {
        isModerate = moderate;
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

    public Long getChanId() {
        return chanId;
    }

    public void setChanId(Long chanId) {
        this.chanId = chanId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public ZonedDateTime getDateLog() {
        return dateLog;
    }

    public void setDateLog(ZonedDateTime dateLog) {
        this.dateLog = dateLog;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Boolean getTgBotApi() {
        return tgBotApi;
    }

    public void setTgBotApi(Boolean tgBotApi) {
        this.tgBotApi = tgBotApi;
    }

    public Boolean getWebApi() {
        return webApi;
    }

    public void setWebApi(Boolean webApi) {
        this.webApi = webApi;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChanellLog)) {
            return false;
        }
        return id != null && id.equals(((ChanellLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChanellLog{" +
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
            ", isDelete='" + getIsDelete() + "'" +
            ", currentDate='" + getCurrentDate() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            ", tgbotApi=" + tgBotApi +
            ", webApi=" + webApi +
            "}";
    }
}
