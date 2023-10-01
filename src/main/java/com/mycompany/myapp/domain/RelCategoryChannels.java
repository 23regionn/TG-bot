package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A RelCategoryChannels.
 */
@Entity
@Table(name = "rel_category_channels")
public class RelCategoryChannels implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "score_channel")
    private Double scoreChannel;

    @Column(name = "is_show_channel")
    private Boolean isShowChannel;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    @JsonIgnoreProperties(value = { "relCategoryChannels" }, allowSetters = true)
    private Chanell chanell;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = { "relCategoryChannels" }, allowSetters = true)
    private Category category;

    public Chanell getChanell() {
        return chanell;
    }

    public void setChanell(Chanell chanell) {
        this.chanell = chanell;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RelCategoryChannels id(Long id) {
        this.id = id;
        return this;
    }

    public Double getScoreChannel() {
        return this.scoreChannel;
    }

    public RelCategoryChannels scoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
        return this;
    }

    public void setScoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
    }

    public Boolean getIsShowChannel() {
        return this.isShowChannel;
    }

    public RelCategoryChannels isShowChannel(Boolean isShowChannel) {
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
        if (!(o instanceof RelCategoryChannels)) {
            return false;
        }
        return id != null && id.equals(((RelCategoryChannels) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelCategoryChannels{" +
            "id=" + getId() +
            ", scoreChannel=" + getScoreChannel() +
            ", isShowChannel='" + getIsShowChannel() + "'" +
            "}";
    }
}
