package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A SearchTypeLog.
 */
@Entity
@Table(name = "search_type_log")
public class SearchTypeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "date_log")
    private ZonedDateTime dateLog;

    @Column(name = "inline_search")
    private Boolean inlineSearch;

    @Column(name = "page_search")
    private Boolean pageSearch;

    @Column(name = "page_number")
    private Long pageNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SearchTypeLog id(Long id) {
        this.id = id;
        return this;
    }

    public Long getChatId() {
        return this.chatId;
    }

    public SearchTypeLog chatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public ZonedDateTime getDateLog() {
        return this.dateLog;
    }

    public SearchTypeLog dateLog(ZonedDateTime dateLog) {
        this.dateLog = dateLog;
        return this;
    }

    public void setDateLog(ZonedDateTime dateLog) {
        this.dateLog = dateLog;
    }

    public Boolean getInlineSearch() {
        return this.inlineSearch;
    }

    public SearchTypeLog inlineSearch(Boolean inlineSearch) {
        this.inlineSearch = inlineSearch;
        return this;
    }

    public void setInlineSearch(Boolean inlineSearch) {
        this.inlineSearch = inlineSearch;
    }

    public Boolean getPageSearch() {
        return this.pageSearch;
    }

    public SearchTypeLog pageSearch(Boolean pageSearch) {
        this.pageSearch = pageSearch;
        return this;
    }

    public void setPageSearch(Boolean pageSearch) {
        this.pageSearch = pageSearch;
    }

    public Long getPageNumber() {
        return this.pageNumber;
    }

    public SearchTypeLog pageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SearchTypeLog)) {
            return false;
        }
        return id != null && id.equals(((SearchTypeLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SearchTypeLog{" +
            "id=" + getId() +
            ", chatId=" + getChatId() +
            ", dateLog='" + getDateLog() + "'" +
            ", inlineSearch='" + getInlineSearch() + "'" +
            ", pageSearch='" + getPageSearch() + "'" +
            ", pageNumber=" + getPageNumber() +
            "}";
    }
}
