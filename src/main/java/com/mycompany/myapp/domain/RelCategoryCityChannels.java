package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A RelCategoryCityChannels.
 */
@Entity
@Table(name = "rel_category_city_channels")
public class RelCategoryCityChannels implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "rel_category_city_channels_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "score_channel")
    private Double scoreChannel = 10000.0;

    @NotNull
    @Column(name = "is_show_channel")
    private Boolean isShowChannel = false;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    @JsonIgnoreProperties(value = { "relCategoryCityChannels, relCategoryCity" }, allowSetters = true)
    private Chanell chanell;

    @ManyToOne
    @JoinColumn(name = "rel_category_city_id")
    @JsonIgnoreProperties(value = { "relCategoryCityChannels" }, allowSetters = true)
    private RelCategoryCity relCategoryCity;

    public Chanell getChanell() {
        return chanell;
    }

    public void setChanell(Chanell chanell) {
        this.chanell = chanell;
    }

    public RelCategoryCity getRelCategoryCity() {
        return relCategoryCity;
    }

    public void setRelCategoryCity(RelCategoryCity relCategoryCity) {
        this.relCategoryCity = relCategoryCity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RelCategoryCityChannels id(Long id) {
        this.id = id;
        return this;
    }

    public Double getScoreChannel() {
        return this.scoreChannel;
    }

    public RelCategoryCityChannels scoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
        return this;
    }

    public void setScoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
    }

    public Boolean getIsShowChannel() {
        return this.isShowChannel;
    }

    public RelCategoryCityChannels isShowChannel(Boolean isShowChannel) {
        this.isShowChannel = isShowChannel;
        return this;
    }

    public void setIsShowChannel(Boolean isShowChannel) {
        this.isShowChannel = isShowChannel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelCategoryCityChannels)) {
            return false;
        }
        return id != null && id.equals(((RelCategoryCityChannels) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelCategoryCityChannels{" +
            "id=" + getId() +
            ", scoreChannel=" + getScoreChannel() +
            ", isShowChannel='" + getIsShowChannel() + "'" +
            "}";
    }
}
