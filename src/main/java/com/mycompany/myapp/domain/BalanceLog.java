package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A BalanceLog.
 */
@Entity
@Table(name = "balance_log")
public class BalanceLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "balace")
    private Double balace;

    @Column(name = "user_id")
    private Long userId;

    /**
     * сумма заморозки для торгов
     */
    @ApiModelProperty(value = "сумма заморозки для торгов")
    @Column(name = "frost_sum")
    private Double frostSum;

    @Column(name = "date_last_add_balance")
    private ZonedDateTime dateLastAddBalance;

    @Column(name = "date_last_minus_from_balance")
    private ZonedDateTime dateLastMinusFromBalance;

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
    @JsonIgnoreProperties(value = { "balanceLogs", "tGUser" }, allowSetters = true)
    private Balance balance;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BalanceLog id(Long id) {
        this.id = id;
        return this;
    }

    public Double getBalace() {
        return this.balace;
    }

    public BalanceLog balace(Double balace) {
        this.balace = balace;
        return this;
    }

    public void setBalace(Double balace) {
        this.balace = balace;
    }

    public Long getUserId() {
        return this.userId;
    }

    public BalanceLog userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getFrostSum() {
        return this.frostSum;
    }

    public BalanceLog frostSum(Double frostSum) {
        this.frostSum = frostSum;
        return this;
    }

    public void setFrostSum(Double frostSum) {
        this.frostSum = frostSum;
    }

    public ZonedDateTime getDateLastAddBalance() {
        return this.dateLastAddBalance;
    }

    public BalanceLog dateLastAddBalance(ZonedDateTime dateLastAddBalance) {
        this.dateLastAddBalance = dateLastAddBalance;
        return this;
    }

    public void setDateLastAddBalance(ZonedDateTime dateLastAddBalance) {
        this.dateLastAddBalance = dateLastAddBalance;
    }

    public ZonedDateTime getDateLastMinusFromBalance() {
        return this.dateLastMinusFromBalance;
    }

    public BalanceLog dateLastMinusFromBalance(ZonedDateTime dateLastMinusFromBalance) {
        this.dateLastMinusFromBalance = dateLastMinusFromBalance;
        return this;
    }

    public void setDateLastMinusFromBalance(ZonedDateTime dateLastMinusFromBalance) {
        this.dateLastMinusFromBalance = dateLastMinusFromBalance;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public BalanceLog date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public BalanceLog date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public BalanceLog long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public BalanceLog string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public BalanceLog boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Balance getBalance() {
        return this.balance;
    }

    public BalanceLog balance(Balance balance) {
        this.setBalance(balance);
        return this;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BalanceLog)) {
            return false;
        }
        return id != null && id.equals(((BalanceLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BalanceLog{" +
            "id=" + getId() +
            ", balace=" + getBalace() +
            ", userId=" + getUserId() +
            ", frostSum=" + getFrostSum() +
            ", dateLastAddBalance='" + getDateLastAddBalance() + "'" +
            ", dateLastMinusFromBalance='" + getDateLastMinusFromBalance() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
