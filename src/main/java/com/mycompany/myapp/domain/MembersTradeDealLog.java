package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A MembersTradeDealLog.
 */
@Entity
@Table(name = "members_trade_deal_log")
public class MembersTradeDealLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * айди текущего пользователя
     */
    @ApiModelProperty(value = "айди текущего пользователя")
    @Column(name = "tg_user_id_current")
    private Long tgUserIdCurrent;

    /**
     * предложение цена
     */
    @ApiModelProperty(value = "предложение цена")
    @Column(name = "price_offer")
    private Double priceOffer;

    /**
     * дата текущая
     */
    @ApiModelProperty(value = "дата текущая")
    @Column(name = "jhi_current_date")
    private ZonedDateTime currentDate;

    /**
     * победитель?
     */
    @ApiModelProperty(value = "победитель?")
    @Column(name = "is_winner")
    private Boolean isWinner;

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
    @JsonIgnoreProperties(value = { "membersTradeDealLogs", "tradeShop" }, allowSetters = true)
    private MembersTradeDeal membersTradeDeal;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MembersTradeDealLog id(Long id) {
        this.id = id;
        return this;
    }

    public Long getTgUserIdCurrent() {
        return this.tgUserIdCurrent;
    }

    public MembersTradeDealLog tgUserIdCurrent(Long tgUserIdCurrent) {
        this.tgUserIdCurrent = tgUserIdCurrent;
        return this;
    }

    public void setTgUserIdCurrent(Long tgUserIdCurrent) {
        this.tgUserIdCurrent = tgUserIdCurrent;
    }

    public Double getPriceOffer() {
        return this.priceOffer;
    }

    public MembersTradeDealLog priceOffer(Double priceOffer) {
        this.priceOffer = priceOffer;
        return this;
    }

    public void setPriceOffer(Double priceOffer) {
        this.priceOffer = priceOffer;
    }

    public ZonedDateTime getCurrentDate() {
        return this.currentDate;
    }

    public MembersTradeDealLog currentDate(ZonedDateTime currentDate) {
        this.currentDate = currentDate;
        return this;
    }

    public void setCurrentDate(ZonedDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public Boolean getIsWinner() {
        return this.isWinner;
    }

    public MembersTradeDealLog isWinner(Boolean isWinner) {
        this.isWinner = isWinner;
        return this;
    }

    public void setIsWinner(Boolean isWinner) {
        this.isWinner = isWinner;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public MembersTradeDealLog isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public MembersTradeDealLog date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public MembersTradeDealLog date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public MembersTradeDealLog long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public MembersTradeDealLog string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public MembersTradeDealLog boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public MembersTradeDeal getMembersTradeDeal() {
        return this.membersTradeDeal;
    }

    public MembersTradeDealLog membersTradeDeal(MembersTradeDeal membersTradeDeal) {
        this.setMembersTradeDeal(membersTradeDeal);
        return this;
    }

    public void setMembersTradeDeal(MembersTradeDeal membersTradeDeal) {
        this.membersTradeDeal = membersTradeDeal;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MembersTradeDealLog)) {
            return false;
        }
        return id != null && id.equals(((MembersTradeDealLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MembersTradeDealLog{" +
            "id=" + getId() +
            ", tgUserIdCurrent=" + getTgUserIdCurrent() +
            ", priceOffer=" + getPriceOffer() +
            ", currentDate='" + getCurrentDate() + "'" +
            ", isWinner='" + getIsWinner() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
