package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A AuditChannelsLog.
 */
@Entity
@Table(name = "audit_channels_log")
public class AuditChannelsLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_log")
    private LocalDate dateLog;

    @Column(name = "comment")
    private String comment;

    @Column(name = "contacts")
    private String contacts;

    @Column(name = "end_public_date")
    private ZonedDateTime endPublicDate;

    @Column(name = "id_channel")
    private Long idChannel;

    @Column(name = "is_moderate")
    private Boolean isModerate;

    @Column(name = "is_pay")
    private Boolean isPay;

    @Column(name = "last_pay_date")
    private ZonedDateTime lastPayDate;

    @Column(name = "link")
    private String link;

    @Column(name = "name_channel")
    private String nameChannel;

    @Column(name = "price_for_pay")
    private String priceForPay;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "count_subscribers")
    private Long countSubscribers;

    @Column(name = "count_views")
    private Long countViews;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuditChannelsLog id(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getDateLog() {
        return this.dateLog;
    }

    public AuditChannelsLog dateLog(LocalDate dateLog) {
        this.dateLog = dateLog;
        return this;
    }

    public void setDateLog(LocalDate dateLog) {
        this.dateLog = dateLog;
    }

    public String getComment() {
        return this.comment;
    }

    public AuditChannelsLog comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContacts() {
        return this.contacts;
    }

    public AuditChannelsLog contacts(String contacts) {
        this.contacts = contacts;
        return this;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public ZonedDateTime getEndPublicDate() {
        return this.endPublicDate;
    }

    public AuditChannelsLog endPublicDate(ZonedDateTime endPublicDate) {
        this.endPublicDate = endPublicDate;
        return this;
    }

    public void setEndPublicDate(ZonedDateTime endPublicDate) {
        this.endPublicDate = endPublicDate;
    }

    public Long getIdChannel() {
        return this.idChannel;
    }

    public AuditChannelsLog idChannel(Long idChannel) {
        this.idChannel = idChannel;
        return this;
    }

    public void setIdChannel(Long idChannel) {
        this.idChannel = idChannel;
    }

    public Boolean getIsModerate() {
        return this.isModerate;
    }

    public AuditChannelsLog isModerate(Boolean isModerate) {
        this.isModerate = isModerate;
        return this;
    }

    public void setIsModerate(Boolean isModerate) {
        this.isModerate = isModerate;
    }

    public Boolean getIsPay() {
        return this.isPay;
    }

    public AuditChannelsLog isPay(Boolean isPay) {
        this.isPay = isPay;
        return this;
    }

    public void setIsPay(Boolean isPay) {
        this.isPay = isPay;
    }

    public ZonedDateTime getLastPayDate() {
        return this.lastPayDate;
    }

    public AuditChannelsLog lastPayDate(ZonedDateTime lastPayDate) {
        this.lastPayDate = lastPayDate;
        return this;
    }

    public void setLastPayDate(ZonedDateTime lastPayDate) {
        this.lastPayDate = lastPayDate;
    }

    public String getLink() {
        return this.link;
    }

    public AuditChannelsLog link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNameChannel() {
        return this.nameChannel;
    }

    public AuditChannelsLog nameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
        return this;
    }

    public void setNameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
    }

    public String getPriceForPay() {
        return this.priceForPay;
    }

    public AuditChannelsLog priceForPay(String priceForPay) {
        this.priceForPay = priceForPay;
        return this;
    }

    public void setPriceForPay(String priceForPay) {
        this.priceForPay = priceForPay;
    }

    public ZonedDateTime getStartDate() {
        return this.startDate;
    }

    public AuditChannelsLog startDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public Long getCountSubscribers() {
        return this.countSubscribers;
    }

    public AuditChannelsLog countSubscribers(Long countSubscribers) {
        this.countSubscribers = countSubscribers;
        return this;
    }

    public void setCountSubscribers(Long countSubscribers) {
        this.countSubscribers = countSubscribers;
    }

    public Long getCountViews() {
        return this.countViews;
    }

    public AuditChannelsLog countViews(Long countViews) {
        this.countViews = countViews;
        return this;
    }

    public void setCountViews(Long countViews) {
        this.countViews = countViews;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuditChannelsLog)) {
            return false;
        }
        return id != null && id.equals(((AuditChannelsLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AuditChannelsLog{" +
            "id=" + getId() +
            ", dateLog='" + getDateLog() + "'" +
            ", comment='" + getComment() + "'" +
            ", contacts='" + getContacts() + "'" +
            ", endPublicDate='" + getEndPublicDate() + "'" +
            ", idChannel=" + getIdChannel() +
            ", isModerate='" + getIsModerate() + "'" +
            ", isPay='" + getIsPay() + "'" +
            ", lastPayDate='" + getLastPayDate() + "'" +
            ", link='" + getLink() + "'" +
            ", nameChannel='" + getNameChannel() + "'" +
            ", priceForPay='" + getPriceForPay() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", countSubscribers=" + getCountSubscribers() +
            ", countViews=" + getCountViews() +
            "}";
    }
}
