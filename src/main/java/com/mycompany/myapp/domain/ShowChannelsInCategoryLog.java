package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A ShowChannelsInCategoryLog.
 */
@Entity
@Table(name = "show_channels_in_category_log")
public class ShowChannelsInCategoryLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_channel")
    private Long idChannel;

    @Column(name = "name_channel")
    private String nameChannel;

    @Column(name = "id_category")
    private Long idCategory;

    @Column(name = "name_category")
    private String nameCategory;

    @Column(name = "is_show_channel")
    private Boolean isShowChannel;

    @Column(name = "score_channel")
    private Double scoreChannel;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date_log")
    private LocalDate dateLog;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShowChannelsInCategoryLog id(Long id) {
        this.id = id;
        return this;
    }

    public Long getIdChannel() {
        return this.idChannel;
    }

    public ShowChannelsInCategoryLog idChannel(Long idChannel) {
        this.idChannel = idChannel;
        return this;
    }

    public void setIdChannel(Long idChannel) {
        this.idChannel = idChannel;
    }

    public String getNameChannel() {
        return this.nameChannel;
    }

    public ShowChannelsInCategoryLog nameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
        return this;
    }

    public void setNameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
    }

    public Long getIdCategory() {
        return this.idCategory;
    }

    public ShowChannelsInCategoryLog idCategory(Long idCategory) {
        this.idCategory = idCategory;
        return this;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return this.nameCategory;
    }

    public ShowChannelsInCategoryLog nameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
        return this;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Boolean getIsShowChannel() {
        return this.isShowChannel;
    }

    public ShowChannelsInCategoryLog isShowChannel(Boolean isShowChannel) {
        this.isShowChannel = isShowChannel;
        return this;
    }

    public void setIsShowChannel(Boolean isShowChannel) {
        this.isShowChannel = isShowChannel;
    }

    public Double getScoreChannel() {
        return this.scoreChannel;
    }

    public ShowChannelsInCategoryLog scoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
        return this;
    }

    public void setScoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
    }

    public String getComment() {
        return this.comment;
    }

    public ShowChannelsInCategoryLog comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDateLog() {
        return this.dateLog;
    }

    public ShowChannelsInCategoryLog dateLog(LocalDate dateLog) {
        this.dateLog = dateLog;
        return this;
    }

    public void setDateLog(LocalDate dateLog) {
        this.dateLog = dateLog;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShowChannelsInCategoryLog)) {
            return false;
        }
        return id != null && id.equals(((ShowChannelsInCategoryLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShowChannelsInCategoryLog{" +
            "id=" + getId() +
            ", idChannel=" + getIdChannel() +
            ", nameChannel='" + getNameChannel() + "'" +
            ", idCategory=" + getIdCategory() +
            ", nameCategory='" + getNameCategory() + "'" +
            ", isShowChannel='" + getIsShowChannel() + "'" +
            ", scoreChannel=" + getScoreChannel() +
            ", comment='" + getComment() + "'" +
            ", dateLog='" + getDateLog() + "'" +
            "}";
    }
}
