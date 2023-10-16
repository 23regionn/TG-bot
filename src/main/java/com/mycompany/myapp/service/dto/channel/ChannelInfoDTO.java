package com.mycompany.myapp.service.dto.channel;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A ChannelInfoDTO.
 */
public class ChannelInfoDTO {

    private Long id;
    private String name;
    private String link;
    private Boolean isModerate;
    private String contacts;
    private ZonedDateTime startDateState;
    private ZonedDateTime lastPayDate;
    private ZonedDateTime endPublicDate;
    private String comment;
    private Double priceForPay;
    private Boolean isPay;

    public ChannelInfoDTO(
        Long id,
        String name,
        String link,
        Boolean isModerate,
        String contacts,
        ZonedDateTime startDateState,
        ZonedDateTime lastPayDate,
        ZonedDateTime endPublicDate,
        String comment,
        Double priceForPay,
        Boolean isPay
    ) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.isModerate = isModerate;
        this.contacts = contacts;
        this.startDateState = startDateState;
        this.lastPayDate = lastPayDate;
        this.endPublicDate = endPublicDate;
        this.comment = comment;
        this.priceForPay = priceForPay;
        this.isPay = isPay;
    }

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

    public ChannelInfoDTO name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return this.link;
    }

    public ChannelInfoDTO link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Boolean getIsModerate() {
        return this.isModerate;
    }

    public ChannelInfoDTO isModerate(Boolean isModerate) {
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

    public ZonedDateTime getStartDateState() {
        return startDateState;
    }

    public void setStartDateState(ZonedDateTime startDateState) {
        this.startDateState = startDateState;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return (
            "ChannelInfoDTO{" +
            "id=" +
            id +
            ", name='" +
            name +
            '\'' +
            ", link='" +
            link +
            '\'' +
            ", isModerate=" +
            isModerate +
            ", contacts='" +
            contacts +
            '\'' +
            ", startDateState=" +
            startDateState +
            ", lastPayDate=" +
            lastPayDate +
            ", endPublicDate=" +
            endPublicDate +
            ", comment='" +
            comment +
            '\'' +
            ", priceForPay=" +
            priceForPay +
            ", isPay=" +
            isPay +
            '}'
        );
    }
}
