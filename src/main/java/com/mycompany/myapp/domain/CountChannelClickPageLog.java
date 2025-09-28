package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A CountChannelClickPageLog.
 */
@Entity
@Table(name = "count_channel_click_page_log")
public class CountChannelClickPageLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "count_channel_click_seq", allocationSize = 1)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "date_log")
    private ZonedDateTime dateLog;

    @Column(name = "id_channel")
    private Long idChannel;

    @Column(name = "page_number")
    private Long pageNumber;

    @Column(name = "id_category")
    private Long idCategory;

    @Column(name = "id_city")
    private Long idCity;

    public CountChannelClickPageLog() {}

    public CountChannelClickPageLog(Long chatId, Long pageNumber, Long idCategory) {
        this.chatId = chatId;
        this.pageNumber = pageNumber;
        this.idCategory = idCategory;
        this.dateLog = ZonedDateTime.now().plusHours(3l);
    }

    public CountChannelClickPageLog(Long chatId, Long pageNumber, Long idCategory, Long idCity) {
        this.chatId = chatId;
        this.pageNumber = pageNumber;
        this.idCategory = idCategory;
        this.idCity = idCity;
        this.dateLog = ZonedDateTime.now().plusHours(3l);
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CountChannelClickPageLog id(Long id) {
        this.id = id;
        return this;
    }

    public Long getChatId() {
        return this.chatId;
    }

    public CountChannelClickPageLog chatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public ZonedDateTime getDateLog() {
        return this.dateLog;
    }

    public CountChannelClickPageLog dateLog(ZonedDateTime dateLog) {
        this.dateLog = dateLog;
        return this;
    }

    public void setDateLog(ZonedDateTime dateLog) {
        this.dateLog = dateLog;
    }

    public Long getIdChannel() {
        return this.idChannel;
    }

    public CountChannelClickPageLog idChannel(Long idChannel) {
        this.idChannel = idChannel;
        return this;
    }

    public void setIdChannel(Long idChannel) {
        this.idChannel = idChannel;
    }

    public Long getPageNumber() {
        return this.pageNumber;
    }

    public CountChannelClickPageLog pageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getIdCategory() {
        return this.idCategory;
    }

    public CountChannelClickPageLog idCategory(Long idCategory) {
        this.idCategory = idCategory;
        return this;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Long getIdCity() {
        return this.idCity;
    }

    public CountChannelClickPageLog idCity(Long idCity) {
        this.idCity = idCity;
        return this;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CountChannelClickPageLog)) {
            return false;
        }
        return id != null && id.equals(((CountChannelClickPageLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CountChannelClickPageLog{" +
            "id=" + getId() +
            ", chatId=" + getChatId() +
            ", dateLog='" + getDateLog() + "'" +
            ", idChannel=" + getIdChannel() +
            ", pageNumber=" + getPageNumber() +
            ", idCategory=" + getIdCategory() +
            ", idCity=" + getIdCity() +
            "}";
    }
}
