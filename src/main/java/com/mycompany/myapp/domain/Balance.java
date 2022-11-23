package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Balance.
 */
@Entity
@Table(name = "balance")
public class Balance implements Serializable {

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

    @OneToMany(mappedBy = "balance")
    @JsonIgnoreProperties(value = { "balance" }, allowSetters = true)
    private Set<BalanceLog> balanceLogs = new HashSet<>();

    @JsonIgnoreProperties(value = { "balance", "chanells", "offerFromCostumers", "reviews", "pays", "tGUserLogs" }, allowSetters = true)
    @OneToOne(mappedBy = "balance")
    private TGUser tGUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Balance id(Long id) {
        this.id = id;
        return this;
    }

    public Double getBalace() {
        return this.balace;
    }

    public Balance balace(Double balace) {
        this.balace = balace;
        return this;
    }

    public void setBalace(Double balace) {
        this.balace = balace;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Balance userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getFrostSum() {
        return this.frostSum;
    }

    public Balance frostSum(Double frostSum) {
        this.frostSum = frostSum;
        return this;
    }

    public void setFrostSum(Double frostSum) {
        this.frostSum = frostSum;
    }

    public ZonedDateTime getDateLastAddBalance() {
        return this.dateLastAddBalance;
    }

    public Balance dateLastAddBalance(ZonedDateTime dateLastAddBalance) {
        this.dateLastAddBalance = dateLastAddBalance;
        return this;
    }

    public void setDateLastAddBalance(ZonedDateTime dateLastAddBalance) {
        this.dateLastAddBalance = dateLastAddBalance;
    }

    public ZonedDateTime getDateLastMinusFromBalance() {
        return this.dateLastMinusFromBalance;
    }

    public Balance dateLastMinusFromBalance(ZonedDateTime dateLastMinusFromBalance) {
        this.dateLastMinusFromBalance = dateLastMinusFromBalance;
        return this;
    }

    public void setDateLastMinusFromBalance(ZonedDateTime dateLastMinusFromBalance) {
        this.dateLastMinusFromBalance = dateLastMinusFromBalance;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public Balance date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public Balance date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public Balance long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public Balance string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public Balance boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Set<BalanceLog> getBalanceLogs() {
        return this.balanceLogs;
    }

    public Balance balanceLogs(Set<BalanceLog> balanceLogs) {
        this.setBalanceLogs(balanceLogs);
        return this;
    }

    public Balance addBalanceLog(BalanceLog balanceLog) {
        this.balanceLogs.add(balanceLog);
        balanceLog.setBalance(this);
        return this;
    }

    public Balance removeBalanceLog(BalanceLog balanceLog) {
        this.balanceLogs.remove(balanceLog);
        balanceLog.setBalance(null);
        return this;
    }

    public void setBalanceLogs(Set<BalanceLog> balanceLogs) {
        if (this.balanceLogs != null) {
            this.balanceLogs.forEach(i -> i.setBalance(null));
        }
        if (balanceLogs != null) {
            balanceLogs.forEach(i -> i.setBalance(this));
        }
        this.balanceLogs = balanceLogs;
    }

    public TGUser getTGUser() {
        return this.tGUser;
    }

    public Balance tGUser(TGUser tGUser) {
        this.setTGUser(tGUser);
        return this;
    }

    public void setTGUser(TGUser tGUser) {
        if (this.tGUser != null) {
            this.tGUser.setBalance(null);
        }
        if (tGUser != null) {
            tGUser.setBalance(this);
        }
        this.tGUser = tGUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Balance)) {
            return false;
        }
        return id != null && id.equals(((Balance) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Balance{" +
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
