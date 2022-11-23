package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A MembersTradeDeal.
 */
@Entity
@Table(name = "members_trade_deal")
public class MembersTradeDeal implements Serializable {

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

    @OneToMany(mappedBy = "membersTradeDeal")
    @JsonIgnoreProperties(value = { "membersTradeDeal" }, allowSetters = true)
    private Set<MembersTradeDealLog> membersTradeDealLogs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "membersTradeDeals", "tradeShopLogs" }, allowSetters = true)
    private TradeShop tradeShop;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MembersTradeDeal id(Long id) {
        this.id = id;
        return this;
    }

    public Long getTgUserIdCurrent() {
        return this.tgUserIdCurrent;
    }

    public MembersTradeDeal tgUserIdCurrent(Long tgUserIdCurrent) {
        this.tgUserIdCurrent = tgUserIdCurrent;
        return this;
    }

    public void setTgUserIdCurrent(Long tgUserIdCurrent) {
        this.tgUserIdCurrent = tgUserIdCurrent;
    }

    public Double getPriceOffer() {
        return this.priceOffer;
    }

    public MembersTradeDeal priceOffer(Double priceOffer) {
        this.priceOffer = priceOffer;
        return this;
    }

    public void setPriceOffer(Double priceOffer) {
        this.priceOffer = priceOffer;
    }

    public ZonedDateTime getCurrentDate() {
        return this.currentDate;
    }

    public MembersTradeDeal currentDate(ZonedDateTime currentDate) {
        this.currentDate = currentDate;
        return this;
    }

    public void setCurrentDate(ZonedDateTime currentDate) {
        this.currentDate = currentDate;
    }

    public Boolean getIsWinner() {
        return this.isWinner;
    }

    public MembersTradeDeal isWinner(Boolean isWinner) {
        this.isWinner = isWinner;
        return this;
    }

    public void setIsWinner(Boolean isWinner) {
        this.isWinner = isWinner;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public MembersTradeDeal isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public MembersTradeDeal date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public MembersTradeDeal date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public MembersTradeDeal long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public MembersTradeDeal string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public MembersTradeDeal boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Set<MembersTradeDealLog> getMembersTradeDealLogs() {
        return this.membersTradeDealLogs;
    }

    public MembersTradeDeal membersTradeDealLogs(Set<MembersTradeDealLog> membersTradeDealLogs) {
        this.setMembersTradeDealLogs(membersTradeDealLogs);
        return this;
    }

    public MembersTradeDeal addMembersTradeDealLog(MembersTradeDealLog membersTradeDealLog) {
        this.membersTradeDealLogs.add(membersTradeDealLog);
        membersTradeDealLog.setMembersTradeDeal(this);
        return this;
    }

    public MembersTradeDeal removeMembersTradeDealLog(MembersTradeDealLog membersTradeDealLog) {
        this.membersTradeDealLogs.remove(membersTradeDealLog);
        membersTradeDealLog.setMembersTradeDeal(null);
        return this;
    }

    public void setMembersTradeDealLogs(Set<MembersTradeDealLog> membersTradeDealLogs) {
        if (this.membersTradeDealLogs != null) {
            this.membersTradeDealLogs.forEach(i -> i.setMembersTradeDeal(null));
        }
        if (membersTradeDealLogs != null) {
            membersTradeDealLogs.forEach(i -> i.setMembersTradeDeal(this));
        }
        this.membersTradeDealLogs = membersTradeDealLogs;
    }

    public TradeShop getTradeShop() {
        return this.tradeShop;
    }

    public MembersTradeDeal tradeShop(TradeShop tradeShop) {
        this.setTradeShop(tradeShop);
        return this;
    }

    public void setTradeShop(TradeShop tradeShop) {
        this.tradeShop = tradeShop;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MembersTradeDeal)) {
            return false;
        }
        return id != null && id.equals(((MembersTradeDeal) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MembersTradeDeal{" +
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
