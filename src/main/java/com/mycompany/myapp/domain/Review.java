package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A Review.
 */
@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * отзыв активен или не активен = прошел или не прошел
     */
    @ApiModelProperty(value = "отзыв активен или не активен = прошел или не прошел")
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * текст отзыва
     */
    @ApiModelProperty(value = "текст отзыва")
    @Column(name = "text")
    private String text;

    /**
     * айди пользователя который написал
     */
    @ApiModelProperty(value = "айди пользователя который написал")
    @Column(name = "user_id")
    private Long userId;

    /**
     * ссылка на канал продавца рекламы от пользователя
     */
    @ApiModelProperty(value = "ссылка на канал продавца рекламы от пользователя")
    @Column(name = "link_to_saller")
    private String linkToSaller;

    /**
     * ссылка на админа, который пропустил отзыв
     */
    @ApiModelProperty(value = "ссылка на админа, который пропустил отзыв")
    @Column(name = "admin_id")
    private Long adminId;

    /**
     * отзыв положительный или негативный
     */
    @ApiModelProperty(value = "отзыв положительный или негативный")
    @Column(name = "is_negative")
    private Boolean isNegative;

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
    @JsonIgnoreProperties(value = { "balance", "chanells", "offerFromCostumers", "reviews", "pays", "tGUserLogs" }, allowSetters = true)
    private TGUser tGUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Review id(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public Review isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getText() {
        return this.text;
    }

    public Review text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Review userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLinkToSaller() {
        return this.linkToSaller;
    }

    public Review linkToSaller(String linkToSaller) {
        this.linkToSaller = linkToSaller;
        return this;
    }

    public void setLinkToSaller(String linkToSaller) {
        this.linkToSaller = linkToSaller;
    }

    public Long getAdminId() {
        return this.adminId;
    }

    public Review adminId(Long adminId) {
        this.adminId = adminId;
        return this;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Boolean getIsNegative() {
        return this.isNegative;
    }

    public Review isNegative(Boolean isNegative) {
        this.isNegative = isNegative;
        return this;
    }

    public void setIsNegative(Boolean isNegative) {
        this.isNegative = isNegative;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public Review isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public Review date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public Review date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public Review long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public Review string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public Review boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public TGUser getTGUser() {
        return this.tGUser;
    }

    public Review tGUser(TGUser tGUser) {
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
        if (!(o instanceof Review)) {
            return false;
        }
        return id != null && id.equals(((Review) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Review{" +
            "id=" + getId() +
            ", isActive='" + getIsActive() + "'" +
            ", text='" + getText() + "'" +
            ", userId=" + getUserId() +
            ", linkToSaller='" + getLinkToSaller() + "'" +
            ", adminId=" + getAdminId() +
            ", isNegative='" + getIsNegative() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
