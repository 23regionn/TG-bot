package com.mycompany.myapp.service.dto.relCategoryCityChannels;

/**
 * A RelCategoryCityChannelsCreateDTO.
 */
public class RelCategoryCityChannelsCreateDTO {

    private Double scoreChannel;
    private Boolean isShowChannel;
    private Long idChannel;
    private Long idRel;
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

    public Long getIdRel() {
        return idRel;
    }

    public void setIdRel(Long idRel) {
        this.idRel = idRel;
    }

    public Double getScoreChannel() {
        return this.scoreChannel;
    }

    public RelCategoryCityChannelsCreateDTO scoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
        return this;
    }

    public void setScoreChannel(Double scoreChannel) {
        this.scoreChannel = scoreChannel;
    }

    public Boolean getIsShowChannel() {
        return this.isShowChannel;
    }

    public RelCategoryCityChannelsCreateDTO isShowChannel(Boolean isShowChannel) {
        this.isShowChannel = isShowChannel;
        return this;
    }

    public void setIsShowChannel(Boolean isShowChannel) {
        this.isShowChannel = isShowChannel;
    }

    @Override
    public String toString() {
        return (
            "RelCategoryCityChannelsCreateDTO{" +
            "scoreChannel=" +
            scoreChannel +
            ", isShowChannel=" +
            isShowChannel +
            ", idChannel=" +
            idChannel +
            ", idRel=" +
            idRel +
            ", comment='" +
            comment +
            '\'' +
            '}'
        );
    }
}
