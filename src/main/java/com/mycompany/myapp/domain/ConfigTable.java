package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A ConfigTable.
 */
@Entity
@Table(name = "config_table")
public class ConfigTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_one")
    private ZonedDateTime dateOne;

    @Column(name = "date_two")
    private ZonedDateTime dateTwo;

    @Column(name = "long_one")
    private Long longOne;

    @Column(name = "string_one")
    private String stringOne;

    @Column(name = "boolean_one")
    private Boolean booleanOne;

    @Column(name = "boolean_two")
    private Boolean booleanTwo;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConfigTable id(Long id) {
        this.id = id;
        return this;
    }

    public ZonedDateTime getDateOne() {
        return this.dateOne;
    }

    public ConfigTable dateOne(ZonedDateTime dateOne) {
        this.dateOne = dateOne;
        return this;
    }

    public void setDateOne(ZonedDateTime dateOne) {
        this.dateOne = dateOne;
    }

    public ZonedDateTime getDateTwo() {
        return this.dateTwo;
    }

    public ConfigTable dateTwo(ZonedDateTime dateTwo) {
        this.dateTwo = dateTwo;
        return this;
    }

    public void setDateTwo(ZonedDateTime dateTwo) {
        this.dateTwo = dateTwo;
    }

    public Long getLongOne() {
        return this.longOne;
    }

    public ConfigTable longOne(Long longOne) {
        this.longOne = longOne;
        return this;
    }

    public void setLongOne(Long longOne) {
        this.longOne = longOne;
    }

    public String getStringOne() {
        return this.stringOne;
    }

    public ConfigTable stringOne(String stringOne) {
        this.stringOne = stringOne;
        return this;
    }

    public void setStringOne(String stringOne) {
        this.stringOne = stringOne;
    }

    public Boolean getBooleanOne() {
        return this.booleanOne;
    }

    public ConfigTable booleanOne(Boolean booleanOne) {
        this.booleanOne = booleanOne;
        return this;
    }

    public void setBooleanOne(Boolean booleanOne) {
        this.booleanOne = booleanOne;
    }

    public Boolean getBooleanTwo() {
        return this.booleanTwo;
    }

    public ConfigTable booleanTwo(Boolean booleanTwo) {
        this.booleanTwo = booleanTwo;
        return this;
    }

    public void setBooleanTwo(Boolean booleanTwo) {
        this.booleanTwo = booleanTwo;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public ConfigTable date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public ConfigTable date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public ConfigTable long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public ConfigTable string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public ConfigTable boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConfigTable)) {
            return false;
        }
        return id != null && id.equals(((ConfigTable) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConfigTable{" +
            "id=" + getId() +
            ", dateOne='" + getDateOne() + "'" +
            ", dateTwo='" + getDateTwo() + "'" +
            ", longOne=" + getLongOne() +
            ", stringOne='" + getStringOne() + "'" +
            ", booleanOne='" + getBooleanOne() + "'" +
            ", booleanTwo='" + getBooleanTwo() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
