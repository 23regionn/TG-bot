package com.mycompany.myapp.service.dto.relCategoryChannel;

/**
 * A RelCategoryChannelsCreateDTO.
 */
public class RelCategoryChannelsCreateDTO {

    private Double scoreChannel;
    private Boolean isShowChannel;
    private Long idChannel;
    private Long idCat;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(Long idChannel) {
        this.idChannel = idChannel;
    }

    public Long getIdCat() {
        return idCat;
    }

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public Double getScoreChannel() {
        return this.scoreChannel;
    }

    public RelCategoryChannelsCreateDTO scoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
        return this;
    }

    public void setScoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
    }

    public Boolean getIsShowChannel() {
        return this.isShowChannel;
    }

    public RelCategoryChannelsCreateDTO isShowChannel(Boolean isShowChannel) {
        this.isShowChannel = isShowChannel;
        return this;
    }

    public void setIsShowChannel(Boolean isShowChannel) {
        this.isShowChannel = isShowChannel;
    }

    @Override
    public String toString() {
        return (
            "RelCategoryChannelsCreateDTO{" +
            "scoreChannel=" +
            scoreChannel +
            ", isShowChannel=" +
            isShowChannel +
            ", idChannel=" +
            idChannel +
            ", idCat=" +
            idCat +
            ", comment='" +
            comment +
            '\'' +
            '}'
        );
    }
}
