package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A Pays.
 */
@Entity
@Table(name = "pays")
public class Pays implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * дата оплаты подписки
     */
    @ApiModelProperty(value = "дата оплаты подписки")
    @Column(name = "date_pays_subscriptions")
    private ZonedDateTime datePaysSubscriptions;

    /**
     * за какой канал он оплатил, за ссылку на какой канал
     */
    @ApiModelProperty(value = "за какой канал он оплатил, за ссылку на какой канал")
    @Column(name = "link")
    private String link;

    /**
     * сумма оплаты
     */
    @ApiModelProperty(value = "сумма оплаты")
    @Column(name = "sum_for_pays")
    private Double sumForPays;

    /**
     * тип покупки
     */
    @ApiModelProperty(value = "тип покупки")
    @Column(name = "type_buy")
    private String typeBuy;

    /**
     * категория
     */
    @ApiModelProperty(value = "категория")
    @Column(name = "category")
    private String category;

    /**
     * тип покупки long
     */
    @ApiModelProperty(value = "тип покупки long")
    @Column(name = "type_buy_long")
    private Long typeBuyLong;

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
    @JsonIgnoreProperties(value = { "balance", "chanells", "offerFromCostumers", "reviews", "pays", "tGUserLogs" }, allowSetters = true)
    private TGUser tGUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pays id(Long id) {
        this.id = id;
        return this;
    }

    public ZonedDateTime getDatePaysSubscriptions() {
        return this.datePaysSubscriptions;
    }

    public Pays datePaysSubscriptions(ZonedDateTime datePaysSubscriptions) {
        this.datePaysSubscriptions = datePaysSubscriptions;
        return this;
    }

    public void setDatePaysSubscriptions(ZonedDateTime datePaysSubscriptions) {
        this.datePaysSubscriptions = datePaysSubscriptions;
    }

    public String getLink() {
        return this.link;
    }

    public Pays link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Double getSumForPays() {
        return this.sumForPays;
    }

    public Pays sumForPays(Double sumForPays) {
        this.sumForPays = sumForPays;
        return this;
    }

    public void setSumForPays(Double sumForPays) {
        this.sumForPays = sumForPays;
    }

    public String getTypeBuy() {
        return this.typeBuy;
    }

    public Pays typeBuy(String typeBuy) {
        this.typeBuy = typeBuy;
        return this;
    }

    public void setTypeBuy(String typeBuy) {
        this.typeBuy = typeBuy;
    }

    public String getCategory() {
        return this.category;
    }

    public Pays category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getTypeBuyLong() {
        return this.typeBuyLong;
    }

    public Pays typeBuyLong(Long typeBuyLong) {
        this.typeBuyLong = typeBuyLong;
        return this;
    }

    public void setTypeBuyLong(Long typeBuyLong) {
        this.typeBuyLong = typeBuyLong;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public Pays date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public Pays date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public Pays long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public Pays string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public Pays boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public TGUser getTGUser() {
        return this.tGUser;
    }

    public Pays tGUser(TGUser tGUser) {
        this.setTGUser(tGUser);
        return this;
    }

    public void setTGUser(TGUser tGUser) {
        this.tGUser = tGUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pays)) {
            return false;
        }
        return id != null && id.equals(((Pays) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pays{" +
            "id=" + getId() +
            ", datePaysSubscriptions='" + getDatePaysSubscriptions() + "'" +
            ", link='" + getLink() + "'" +
            ", sumForPays=" + getSumForPays() +
            ", typeBuy='" + getTypeBuy() + "'" +
            ", category='" + getCategory() + "'" +
            ", typeBuyLong=" + getTypeBuyLong() +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
