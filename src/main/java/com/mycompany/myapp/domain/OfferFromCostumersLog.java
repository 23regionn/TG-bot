package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A OfferFromCostumersLog.
 */
@Entity
@Table(name = "offer_from_costumers_log")
public class OfferFromCostumersLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * текст предложения, обратная связь
     */
    @ApiModelProperty(value = "текст предложения, обратная связь")
    @Column(name = "text")
    private String text;

    /**
     * удаленный
     */
    @ApiModelProperty(value = "удаленный")
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * ссылка на админа, который пропустил отзыв
     */
    @ApiModelProperty(value = "ссылка на админа, который пропустил отзыв")
    @Column(name = "admin_id")
    private Long adminId;

    /**
     * предложение = обработали или нет
     */
    @ApiModelProperty(value = "предложение = обработали или нет")
    @Column(name = "is_active")
    private Boolean isActive;

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
    @JsonIgnoreProperties(value = { "offerFromCostumersLogs", "tGUser" }, allowSetters = true)
    private OfferFromCostumers offerFromCostumers;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OfferFromCostumersLog id(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return this.text;
    }

    public OfferFromCostumersLog text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public OfferFromCostumersLog isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Long getAdminId() {
        return this.adminId;
    }

    public OfferFromCostumersLog adminId(Long adminId) {
        this.adminId = adminId;
        return this;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public OfferFromCostumersLog isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public OfferFromCostumersLog date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public OfferFromCostumersLog date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public OfferFromCostumersLog long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public OfferFromCostumersLog string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public OfferFromCostumersLog boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public OfferFromCostumers getOfferFromCostumers() {
        return this.offerFromCostumers;
    }

    public OfferFromCostumersLog offerFromCostumers(OfferFromCostumers offerFromCostumers) {
        this.setOfferFromCostumers(offerFromCostumers);
        return this;
    }

    public void setOfferFromCostumers(OfferFromCostumers offerFromCostumers) {
        this.offerFromCostumers = offerFromCostumers;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfferFromCostumersLog)) {
            return false;
        }
        return id != null && id.equals(((OfferFromCostumersLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OfferFromCostumersLog{" +
            "id=" + getId() +
            ", text='" + getText() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            ", adminId=" + getAdminId() +
            ", isActive='" + getIsActive() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
