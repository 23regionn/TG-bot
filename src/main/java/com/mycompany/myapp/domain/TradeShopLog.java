package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A TradeShopLog.
 */
@Entity
@Table(name = "trade_shop_log")
public class TradeShopLog implements Serializable {

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

    public TradeShopLog id(Long id) {
        this.id = id;
        return this;
    }

    public String getCategory() {
        return this.category;
    }

    public TradeShopLog category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPriceDiapozon() {
        return this.priceDiapozon;
    }

    public TradeShopLog priceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
        return this;
    }

    public void setPriceDiapozon(Double priceDiapozon) {
        this.priceDiapozon = priceDiapozon;
    }

    public Double getCurrentPrice() {
        return this.currentPrice;
    }

    public TradeShopLog currentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
        return this;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Long getWhiceLineFromAllCountLines() {
        return this.whiceLineFromAllCountLines;
    }

    public TradeShopLog whiceLineFromAllCountLines(Long whiceLineFromAllCountLines) {
        this.whiceLineFromAllCountLines = whiceLineFromAllCountLines;
        return this;
    }

    public void setWhiceLineFromAllCountLines(Long whiceLineFromAllCountLines) {
        this.whiceLineFromAllCountLines = whiceLineFromAllCountLines;
    }

    public Long getTgUserIdWinner() {
        return this.tgUserIdWinner;
    }

    public TradeShopLog tgUserIdWinner(Long tgUserIdWinner) {
        this.tgUserIdWinner = tgUserIdWinner;
        return this;
    }

    public void setTgUserIdWinner(Long tgUserIdWinner) {
        this.tgUserIdWinner = tgUserIdWinner;
    }

    public Long getInWhatDateWillPostThisLinks() {
        return this.inWhatDateWillPostThisLinks;
    }

    public TradeShopLog inWhatDateWillPostThisLinks(Long inWhatDateWillPostThisLinks) {
        this.inWhatDateWillPostThisLinks = inWhatDateWillPostThisLinks;
        return this;
    }

    public void setInWhatDateWillPostThisLinks(Long inWhatDateWillPostThisLinks) {
        this.inWhatDateWillPostThisLinks = inWhatDateWillPostThisLinks;
    }

    public ZonedDateTime getDateFinishTorgs() {
        return this.dateFinishTorgs;
    }

    public TradeShopLog dateFinishTorgs(ZonedDateTime dateFinishTorgs) {
        this.dateFinishTorgs = dateFinishTorgs;
        return this;
    }

    public void setDateFinishTorgs(ZonedDateTime dateFinishTorgs) {
        this.dateFinishTorgs = dateFinishTorgs;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public TradeShopLog isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public TradeShopLog date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public TradeShopLog date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public TradeShopLog long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public TradeShopLog string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public TradeShopLog boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public TradeShop getTradeShop() {
        return this.tradeShop;
    }

    public TradeShopLog tradeShop(TradeShop tradeShop) {
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
        if (!(o instanceof TradeShopLog)) {
            return false;
        }
        return id != null && id.equals(((TradeShopLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TradeShopLog{" +
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
