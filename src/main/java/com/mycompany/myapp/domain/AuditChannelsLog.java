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

    @Column(name = "old_comment")
    private String oldComment;

    @Column(name = "old_contacts")
    private String oldContacts;

    @Column(name = "old_end_public_date")
    private ZonedDateTime oldEndPublicDate;

    @Column(name = "old_is_moderate")
    private Boolean oldIsModerate;

    @Column(name = "old_is_pay")
    private Boolean oldIsPay;

    @Column(name = "old_last_pay_date")
    private ZonedDateTime oldLastPayDate;

    @Column(name = "old_link")
    private String oldLink;

    @Column(name = "old_name_channel")
    private String oldNameChannel;

    @Column(name = "old_price_for_pay")
    private String oldPriceForPay;

    @Column(name = "old_start_date")
    private ZonedDateTime oldStartDate;

    @Column(name = "old_count_subscribers")
    private Long oldCountSubscribers;

    @Column(name = "old_count_views")
    private Long oldCountViews;

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

    public String getOldComment() {
        return this.oldComment;
    }

    public AuditChannelsLog oldComment(String oldComment) {
        this.oldComment = oldComment;
        return this;
    }

    public void setOldComment(String oldComment) {
        this.oldComment = oldComment;
    }

    public String getOldContacts() {
        return this.oldContacts;
    }

    public AuditChannelsLog oldContacts(String oldContacts) {
        this.oldContacts = oldContacts;
        return this;
    }

    public void setOldContacts(String oldContacts) {
        this.oldContacts = oldContacts;
    }

    public ZonedDateTime getOldEndPublicDate() {
        return this.oldEndPublicDate;
    }

    public AuditChannelsLog oldEndPublicDate(ZonedDateTime oldEndPublicDate) {
        this.oldEndPublicDate = oldEndPublicDate;
        return this;
    }

    public void setOldEndPublicDate(ZonedDateTime oldEndPublicDate) {
        this.oldEndPublicDate = oldEndPublicDate;
    }

    public Boolean getOldIsModerate() {
        return this.oldIsModerate;
    }

    public AuditChannelsLog oldIsModerate(Boolean oldIsModerate) {
        this.oldIsModerate = oldIsModerate;
        return this;
    }

    public void setOldIsModerate(Boolean oldIsModerate) {
        this.oldIsModerate = oldIsModerate;
    }

    public Boolean getOldIsPay() {
        return this.oldIsPay;
    }

    public AuditChannelsLog oldIsPay(Boolean oldIsPay) {
        this.oldIsPay = oldIsPay;
        return this;
    }

    public void setOldIsPay(Boolean oldIsPay) {
        this.oldIsPay = oldIsPay;
    }

    public ZonedDateTime getOldLastPayDate() {
        return this.oldLastPayDate;
    }

    public AuditChannelsLog oldLastPayDate(ZonedDateTime oldLastPayDate) {
        this.oldLastPayDate = oldLastPayDate;
        return this;
    }

    public void setOldLastPayDate(ZonedDateTime oldLastPayDate) {
        this.oldLastPayDate = oldLastPayDate;
    }

    public String getOldLink() {
        return this.oldLink;
    }

    public AuditChannelsLog oldLink(String oldLink) {
        this.oldLink = oldLink;
        return this;
    }

    public void setOldLink(String oldLink) {
        this.oldLink = oldLink;
    }

    public String getOldNameChannel() {
        return this.oldNameChannel;
    }

    public AuditChannelsLog oldNameChannel(String oldNameChannel) {
        this.oldNameChannel = oldNameChannel;
        return this;
    }

    public void setOldNameChannel(String oldNameChannel) {
        this.oldNameChannel = oldNameChannel;
    }

    public String getOldPriceForPay() {
        return this.oldPriceForPay;
    }

    public AuditChannelsLog oldPriceForPay(String oldPriceForPay) {
        this.oldPriceForPay = oldPriceForPay;
        return this;
    }

    public void setOldPriceForPay(String oldPriceForPay) {
        this.oldPriceForPay = oldPriceForPay;
    }

    public ZonedDateTime getOldStartDate() {
        return this.oldStartDate;
    }

    public AuditChannelsLog oldStartDate(ZonedDateTime oldStartDate) {
        this.oldStartDate = oldStartDate;
        return this;
    }

    public void setOldStartDate(ZonedDateTime oldStartDate) {
        this.oldStartDate = oldStartDate;
    }

    public Long getOldCountSubscribers() {
        return this.oldCountSubscribers;
    }

    public AuditChannelsLog oldCountSubscribers(Long oldCountSubscribers) {
        this.oldCountSubscribers = oldCountSubscribers;
        return this;
    }

    public void setOldCountSubscribers(Long oldCountSubscribers) {
        this.oldCountSubscribers = oldCountSubscribers;
    }

    public Long getOldCountViews() {
        return this.oldCountViews;
    }

    public AuditChannelsLog oldCountViews(Long oldCountViews) {
        this.oldCountViews = oldCountViews;
        return this;
    }

    public void setOldCountViews(Long oldCountViews) {
        this.oldCountViews = oldCountViews;
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
            ", oldComment='" + getOldComment() + "'" +
            ", oldContacts='" + getOldContacts() + "'" +
            ", oldEndPublicDate='" + getOldEndPublicDate() + "'" +
            ", oldIsModerate='" + getOldIsModerate() + "'" +
            ", oldIsPay='" + getOldIsPay() + "'" +
            ", oldLastPayDate='" + getOldLastPayDate() + "'" +
            ", oldLink='" + getOldLink() + "'" +
            ", oldNameChannel='" + getOldNameChannel() + "'" +
            ", oldPriceForPay='" + getOldPriceForPay() + "'" +
            ", oldStartDate='" + getOldStartDate() + "'" +
            ", oldCountSubscribers=" + getOldCountSubscribers() +
            ", oldCountViews=" + getOldCountViews() +
            "}";
    }
}
