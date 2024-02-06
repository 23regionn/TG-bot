package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A ShowChannelsInCityLog.
 */
@Entity
@Table(name = "show_channels_in_city_log")
public class ShowChannelsInCityLog implements Serializable {

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

    @Column(name = "id_city")
    private Long idCity;

    @Column(name = "name_city")
    private String nameCity;

    @Column(name = "is_show_channel")
    private Boolean isShowChannel;

    @Column(name = "score_channel")
    private Double scoreChannel;

    @Column(name = "comment")
    private String comment;

    @Column(name = "old_is_show_channel")
    private Boolean oldIsShowChannel;

    @Column(name = "old_score_channel")
    private Double oldScoreChannel;

    @Column(name = "old_comment")
    private String oldComment;

    @Column(name = "date_log")
    private LocalDate dateLog;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShowChannelsInCityLog id(Long id) {
        this.id = id;
        return this;
    }

    public Long getIdChannel() {
        return this.idChannel;
    }

    public ShowChannelsInCityLog idChannel(Long idChannel) {
        this.idChannel = idChannel;
        return this;
    }

    public void setIdChannel(Long idChannel) {
        this.idChannel = idChannel;
    }

    public String getNameChannel() {
        return this.nameChannel;
    }

    public ShowChannelsInCityLog nameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
        return this;
    }

    public void setNameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
    }

    public Long getIdCategory() {
        return this.idCategory;
    }

    public ShowChannelsInCityLog idCategory(Long idCategory) {
        this.idCategory = idCategory;
        return this;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return this.nameCategory;
    }

    public ShowChannelsInCityLog nameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
        return this;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Long getIdCity() {
        return this.idCity;
    }

    public ShowChannelsInCityLog idCity(Long idCity) {
        this.idCity = idCity;
        return this;
    }

    public void setIdCity(Long idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return this.nameCity;
    }

    public ShowChannelsInCityLog nameCity(String nameCity) {
        this.nameCity = nameCity;
        return this;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public Boolean getIsShowChannel() {
        return this.isShowChannel;
    }

    public ShowChannelsInCityLog isShowChannel(Boolean isShowChannel) {
        this.isShowChannel = isShowChannel;
        return this;
    }

    public void setIsShowChannel(Boolean isShowChannel) {
        this.isShowChannel = isShowChannel;
    }

    public Double getScoreChannel() {
        return this.scoreChannel;
    }

    public ShowChannelsInCityLog scoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
        return this;
    }

    public void setScoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
    }

    public String getComment() {
        return this.comment;
    }

    public ShowChannelsInCityLog comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getOldIsShowChannel() {
        return this.oldIsShowChannel;
    }

    public ShowChannelsInCityLog oldIsShowChannel(Boolean oldIsShowChannel) {
        this.oldIsShowChannel = oldIsShowChannel;
        return this;
    }

    public void setOldIsShowChannel(Boolean oldIsShowChannel) {
        this.oldIsShowChannel = oldIsShowChannel;
    }

    public Double getOldScoreChannel() {
        return this.oldScoreChannel;
    }

    public ShowChannelsInCityLog oldScoreChannel(Double oldScoreChannel) {
        this.oldScoreChannel = oldScoreChannel;
        return this;
    }

    public void setOldScoreChannel(Double oldScoreChannel) {
        this.oldScoreChannel = oldScoreChannel;
    }

    public String getOldComment() {
        return this.oldComment;
    }

    public ShowChannelsInCityLog oldComment(String oldComment) {
        this.oldComment = oldComment;
        return this;
    }

    public void setOldComment(String oldComment) {
        this.oldComment = oldComment;
    }

    public LocalDate getDateLog() {
        return this.dateLog;
    }

    public ShowChannelsInCityLog dateLog(LocalDate dateLog) {
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
        if (!(o instanceof ShowChannelsInCityLog)) {
            return false;
        }
        return id != null && id.equals(((ShowChannelsInCityLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ShowChannelsInCityLog{" +
            "id=" + getId() +
            ", idChannel=" + getIdChannel() +
            ", nameChannel='" + getNameChannel() + "'" +
            ", idCategory=" + getIdCategory() +
            ", nameCategory='" + getNameCategory() + "'" +
            ", idCity=" + getIdCity() +
            ", nameCity='" + getNameCity() + "'" +
            ", isShowChannel='" + getIsShowChannel() + "'" +
            ", scoreChannel=" + getScoreChannel() +
            ", comment='" + getComment() + "'" +
            ", oldIsShowChannel='" + getOldIsShowChannel() + "'" +
            ", oldScoreChannel=" + getOldScoreChannel() +
            ", oldComment='" + getOldComment() + "'" +
            ", dateLog='" + getDateLog() + "'" +
            "}";
    }
}
