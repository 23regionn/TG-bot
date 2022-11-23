package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A TradeShop.
 */
@Entity
@Table(name = "trade_shop")
public class TradeShop implements Serializable {

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
     * текущая цена
     */
    @ApiModelProperty(value = "текущая цена")
    @Column(name = "current_price")
    private Double currentPrice;

    /**
     * место- какая строка из пяти
     */
    @ApiModelProperty(value = "место- какая строка из пяти")
    @Column(name = "whice_line_from_all_count_lines")
    private Long whiceLineFromAllCountLines;

    /**
     * айди текущего победителя
     */
    @ApiModelProperty(value = "айди текущего победителя")
    @Column(name = "tg_user_id_winner")
    private Long tgUserIdWinner;

    /**
     * дата размещения
     */
    @ApiModelProperty(value = "дата размещения")
    @Column(name = "in_what_date_will_post_this_links")
    private Long inWhatDateWillPostThisLinks;

    /**
     * дата окончания торгов
     */
    @ApiModelProperty(value = "дата окончания торгов")
    @Column(name = "date_finish_torgs")
    private ZonedDateTime dateFinishTorgs;

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

    @OneToMany(mappedBy = "tradeShop")
    @JsonIgnoreProperties(value = { "membersTradeDealLogs", "tradeShop" }, allowSetters = true)
    private Set<MembersTradeDeal> membersTradeDeals = new HashSet<>();

    @OneToMany(mappedBy = "tradeShop")
    @JsonIgnoreProperties(value = { "tradeShop" }, allowSetters = true)
    private Set<TradeShopLog> tradeShopLogs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TradeShop id(Long id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public TradeShop category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPriceDiapozon() {
        return this.priceDiapozon;
    }

    public TradeShop priceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
        return this;
    }

    public void setPriceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
    }

    public Double getCurrentPrice() {
        return this.currentPrice;
    }

    public TradeShop currentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getWhiceLineFromAllCountLines() {
        return this.whiceLineFromAllCountLines;
    }

    public TradeShop whiceLineFromAllCountLines(Long whiceLineFromAllCountLines) {
        this.whiceLineFromAllCountLines = whiceLineFromAllCountLines;
        return this;
    }

    public void setWhiceLineFromAllCountLines(Long whiceLineFromAllCountLines) {
        this.whiceLineFromAllCountLines = whiceLineFromAllCountLines;
    }

    public Long getTgUserIdWinner() {
        return this.tgUserIdWinner;
    }

    public TradeShop tgUserIdWinner(Long tgUserIdWinner) {
        this.tgUserIdWinner = tgUserIdWinner;
        return this;
    }

    public void setTgUserIdWinner(Long tgUserIdWinner) {
        this.tgUserIdWinner = tgUserIdWinner;
    }

    public Long getInWhatDateWillPostThisLinks() {
        return this.inWhatDateWillPostThisLinks;
    }

    public TradeShop inWhatDateWillPostThisLinks(Long inWhatDateWillPostThisLinks) {
        this.inWhatDateWillPostThisLinks = inWhatDateWillPostThisLinks;
        return this;
    }

    public void setInWhatDateWillPostThisLinks(Long inWhatDateWillPostThisLinks) {
        this.inWhatDateWillPostThisLinks = inWhatDateWillPostThisLinks;
    }

    public ZonedDateTime getDateFinishTorgs() {
        return this.dateFinishTorgs;
    }

    public TradeShop dateFinishTorgs(ZonedDateTime dateFinishTorgs) {
        this.dateFinishTorgs = dateFinishTorgs;
        return this;
    }

    public void setDateFinishTorgs(ZonedDateTime dateFinishTorgs) {
        this.dateFinishTorgs = dateFinishTorgs;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public TradeShop isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public TradeShop date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public TradeShop date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public TradeShop long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public TradeShop string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public TradeShop boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Set<MembersTradeDeal> getMembersTradeDeals() {
        return this.membersTradeDeals;
    }

    public TradeShop membersTradeDeals(Set<MembersTradeDeal> membersTradeDeals) {
        this.setMembersTradeDeals(membersTradeDeals);
        return this;
    }

    public TradeShop addMembersTradeDeal(MembersTradeDeal membersTradeDeal) {
        this.membersTradeDeals.add(membersTradeDeal);
        membersTradeDeal.setTradeShop(this);
        return this;
    }

    public TradeShop removeMembersTradeDeal(MembersTradeDeal membersTradeDeal) {
        this.membersTradeDeals.remove(membersTradeDeal);
        membersTradeDeal.setTradeShop(null);
        return this;
    }

    public void setMembersTradeDeals(Set<MembersTradeDeal> membersTradeDeals) {
        if (this.membersTradeDeals != null) {
            this.membersTradeDeals.forEach(i -> i.setTradeShop(null));
        }
        if (membersTradeDeals != null) {
            membersTradeDeals.forEach(i -> i.setTradeShop(this));
        }
        this.membersTradeDeals = membersTradeDeals;
    }

    public Set<TradeShopLog> getTradeShopLogs() {
        return this.tradeShopLogs;
    }

    public TradeShop tradeShopLogs(Set<TradeShopLog> tradeShopLogs) {
        this.setTradeShopLogs(tradeShopLogs);
        return this;
    }

    public TradeShop addTradeShopLog(TradeShopLog tradeShopLog) {
        this.tradeShopLogs.add(tradeShopLog);
        tradeShopLog.setTradeShop(this);
        return this;
    }

    public TradeShop removeTradeShopLog(TradeShopLog tradeShopLog) {
        this.tradeShopLogs.remove(tradeShopLog);
        tradeShopLog.setTradeShop(null);
        return this;
    }

    public void setTradeShopLogs(Set<TradeShopLog> tradeShopLogs) {
        if (this.tradeShopLogs != null) {
            this.tradeShopLogs.forEach(i -> i.setTradeShop(null));
        }
        if (tradeShopLogs != null) {
            tradeShopLogs.forEach(i -> i.setTradeShop(this));
        }
        this.tradeShopLogs = tradeShopLogs;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TradeShop)) {
            return false;
        }
        return id != null && id.equals(((TradeShop) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TradeShop{" +
            "id=" + getId() +
            ", category='" + getCategory() + "'" +
            ", priceDiapozon=" + getPriceDiapozon() +
            ", currentPrice=" + getCurrentPrice() +
            ", whiceLineFromAllCountLines=" + getWhiceLineFromAllCountLines() +
            ", tgUserIdWinner=" + getTgUserIdWinner() +
            ", inWhatDateWillPostThisLinks=" + getInWhatDateWillPostThisLinks() +
            ", dateFinishTorgs='" + getDateFinishTorgs() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
