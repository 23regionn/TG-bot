package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.*;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A ChanellPostDTO.
 */
public class ChanellPostDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idCat;
    private String name;
    private String link;
    private Double score;
    private Boolean isModerate;
    private String contacts;
    private ZonedDateTime lastPayDate;
    private ZonedDateTime endPublicDate;
    private String comment;
    private Double priceForPay;
    private Boolean isPay;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Double getPriceForPay() {
        return priceForPay;
    }

    public void setPriceForPay(Double priceForPay) {
        this.priceForPay = priceForPay;
    }

    public Boolean getIsPay() {
        return isPay;
    }

    public void setIsPay(Boolean pay) {
        isPay = pay;
    }

    public String getName() {
        return this.name;
    }

    public ChanellPostDTO name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return this.link;
    }

    public ChanellPostDTO link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Double getScore() {
        return this.score;
    }

    public ChanellPostDTO score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getIsModerate() {
        return this.isModerate;
    }

    public ChanellPostDTO isModerate(Boolean isModerate) {
        this.isModerate = isModerate;
        return this;
    }

    public void setIsModerate(Boolean isModerate) {
        this.isModerate = isModerate;
    }

    public String getContacts() {
        return contacts;
    }

    public ZonedDateTime getLastPayDate() {
        return lastPayDate;
    }

    public void setLastPayDate(ZonedDateTime lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public ZonedDateTime getEndPublicDate() {
        return endPublicDate;
    }

    public void setEndPublicDate(ZonedDateTime endPublicDate) {
        this.endPublicDate = endPublicDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Chanell{" +
            ", name='" + getName() + "'" +
            ", link='" + getLink() + "'" +
            ", score=" + getScore() +
            ", isModerate='" + getIsModerate() + "'" +
            ", lastPayDate='" + getLastPayDate() + "'" +
            ", endPublicDate='" + getEndPublicDate() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}
