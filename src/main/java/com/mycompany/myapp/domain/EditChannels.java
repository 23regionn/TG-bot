package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A EditChannels.
 */
@Entity
@Table(name = "edit_channels")
public class EditChannels implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_message")
    private Long idMessage;

    @Column(name = "id_channel")
    private Long idChannel;

    @Column(name = "date_create_message")
    private ZonedDateTime dateCreateMessage;

    @Column(name = "last_name_channel")
    private String lastNameChannel;

    @Column(name = "new_name_channel")
    private String newNameChannel;

    @Column(name = "is_aprove_change")
    private String isAproveChange;

    @Column(name = "last_link_to_channel")
    private String lastLinkToChannel;

    @Column(name = "newlast_link_to_channel")
    private String newlastLinkToChannel;

    @Column(name = "last_price_channel")
    private Double lastPriceChannel;

    @Column(name = "new_price_channel")
    private Double newPriceChannel;

    @Column(name = "add_description_about_channel")
    private String addDescriptionAboutChannel;

    @Column(name = "current_description_channel")
    private String currentDescriptionChannel;

    @Column(name = "add_region_channel")
    private String addRegionChannel;

    @Column(name = "edit_region_channel")
    private String editRegionChannel;

    @Column(name = "add_city_channel")
    private String addCityChannel;

    @Column(name = "edit_city_channel")
    private String editCityChannel;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "is_approved_chanhes")
    private Boolean isApprovedChanhes;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    private String status;

    @Column(name = "service_field_1")
    private String serviceField1;

    @Column(name = "service_field_2")
    private String serviceField2;

    @Column(name = "service_field_3")
    private String serviceField3;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EditChannels id(Long id) {
        this.id = id;
        return this;
    }

    public Long getIdMessage() {
        return this.idMessage;
    }

    public EditChannels idMessage(Long idMessage) {
        this.idMessage = idMessage;
        return this;
    }

    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public Long getIdChannel() {
        return this.idChannel;
    }

    public EditChannels idChannel(Long idChannel) {
        this.idChannel = idChannel;
        return this;
    }

    public void setIdChannel(Long idChannel) {
        this.idChannel = idChannel;
    }

    public ZonedDateTime getDateCreateMessage() {
        return this.dateCreateMessage;
    }

    public EditChannels dateCreateMessage(ZonedDateTime dateCreateMessage) {
        this.dateCreateMessage = dateCreateMessage;
        return this;
    }

    public void setDateCreateMessage(ZonedDateTime dateCreateMessage) {
        this.dateCreateMessage = dateCreateMessage;
    }

    public String getLastNameChannel() {
        return this.lastNameChannel;
    }

    public EditChannels lastNameChannel(String lastNameChannel) {
        this.lastNameChannel = lastNameChannel;
        return this;
    }

    public void setLastNameChannel(String lastNameChannel) {
        this.lastNameChannel = lastNameChannel;
    }

    public String getNewNameChannel() {
        return this.newNameChannel;
    }

    public EditChannels newNameChannel(String newNameChannel) {
        this.newNameChannel = newNameChannel;
        return this;
    }

    public void setNewNameChannel(String newNameChannel) {
        this.newNameChannel = newNameChannel;
    }

    public String getIsAproveChange() {
        return this.isAproveChange;
    }

    public EditChannels isAproveChange(String isAproveChange) {
        this.isAproveChange = isAproveChange;
        return this;
    }

    public void setIsAproveChange(String isAproveChange) {
        this.isAproveChange = isAproveChange;
    }

    public String getLastLinkToChannel() {
        return this.lastLinkToChannel;
    }

    public EditChannels lastLinkToChannel(String lastLinkToChannel) {
        this.lastLinkToChannel = lastLinkToChannel;
        return this;
    }

    public void setLastLinkToChannel(String lastLinkToChannel) {
        this.lastLinkToChannel = lastLinkToChannel;
    }

    public String getNewlastLinkToChannel() {
        return this.newlastLinkToChannel;
    }

    public EditChannels newlastLinkToChannel(String newlastLinkToChannel) {
        this.newlastLinkToChannel = newlastLinkToChannel;
        return this;
    }

    public void setNewlastLinkToChannel(String newlastLinkToChannel) {
        this.newlastLinkToChannel = newlastLinkToChannel;
    }

    public Double getLastPriceChannel() {
        return this.lastPriceChannel;
    }

    public EditChannels lastPriceChannel(Double lastPriceChannel) {
        this.lastPriceChannel = lastPriceChannel;
        return this;
    }

    public void setLastPriceChannel(Double lastPriceChannel) {
        this.lastPriceChannel = lastPriceChannel;
    }

    public Double getNewPriceChannel() {
        return this.newPriceChannel;
    }

    public EditChannels newPriceChannel(Double newPriceChannel) {
        this.newPriceChannel = newPriceChannel;
        return this;
    }

    public void setNewPriceChannel(Double newPriceChannel) {
        this.newPriceChannel = newPriceChannel;
    }

    public String getAddDescriptionAboutChannel() {
        return this.addDescriptionAboutChannel;
    }

    public EditChannels addDescriptionAboutChannel(String addDescriptionAboutChannel) {
        this.addDescriptionAboutChannel = addDescriptionAboutChannel;
        return this;
    }

    public void setAddDescriptionAboutChannel(String addDescriptionAboutChannel) {
        this.addDescriptionAboutChannel = addDescriptionAboutChannel;
    }

    public String getCurrentDescriptionChannel() {
        return this.currentDescriptionChannel;
    }

    public EditChannels currentDescriptionChannel(String currentDescriptionChannel) {
        this.currentDescriptionChannel = currentDescriptionChannel;
        return this;
    }

    public void setCurrentDescriptionChannel(String currentDescriptionChannel) {
        this.currentDescriptionChannel = currentDescriptionChannel;
    }

    public String getAddRegionChannel() {
        return this.addRegionChannel;
    }

    public EditChannels addRegionChannel(String addRegionChannel) {
        this.addRegionChannel = addRegionChannel;
        return this;
    }

    public void setAddRegionChannel(String addRegionChannel) {
        this.addRegionChannel = addRegionChannel;
    }

    public String getEditRegionChannel() {
        return this.editRegionChannel;
    }

    public EditChannels editRegionChannel(String editRegionChannel) {
        this.editRegionChannel = editRegionChannel;
        return this;
    }

    public void setEditRegionChannel(String editRegionChannel) {
        this.editRegionChannel = editRegionChannel;
    }

    public String getAddCityChannel() {
        return this.addCityChannel;
    }

    public EditChannels addCityChannel(String addCityChannel) {
        this.addCityChannel = addCityChannel;
        return this;
    }

    public void setAddCityChannel(String addCityChannel) {
        this.addCityChannel = addCityChannel;
    }

    public String getEditCityChannel() {
        return this.editCityChannel;
    }

    public EditChannels editCityChannel(String editCityChannel) {
        this.editCityChannel = editCityChannel;
        return this;
    }

    public void setEditCityChannel(String editCityChannel) {
        this.editCityChannel = editCityChannel;
    }

    public Long getUserId() {
        return this.userId;
    }

    public EditChannels userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public EditChannels userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getIsApprovedChanhes() {
        return this.isApprovedChanhes;
    }

    public EditChannels isApprovedChanhes(Boolean isApprovedChanhes) {
        this.isApprovedChanhes = isApprovedChanhes;
        return this;
    }

    public void setIsApprovedChanhes(Boolean isApprovedChanhes) {
        this.isApprovedChanhes = isApprovedChanhes;
    }

    public String getComment() {
        return this.comment;
    }

    public EditChannels comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return this.status;
    }

    public EditChannels status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceField1() {
        return this.serviceField1;
    }

    public EditChannels serviceField1(String serviceField1) {
        this.serviceField1 = serviceField1;
        return this;
    }

    public void setServiceField1(String serviceField1) {
        this.serviceField1 = serviceField1;
    }

    public String getServiceField2() {
        return this.serviceField2;
    }

    public EditChannels serviceField2(String serviceField2) {
        this.serviceField2 = serviceField2;
        return this;
    }

    public void setServiceField2(String serviceField2) {
        this.serviceField2 = serviceField2;
    }

    public String getServiceField3() {
        return this.serviceField3;
    }

    public EditChannels serviceField3(String serviceField3) {
        this.serviceField3 = serviceField3;
        return this;
    }

    public void setServiceField3(String serviceField3) {
        this.serviceField3 = serviceField3;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EditChannels)) {
            return false;
        }
        return id != null && id.equals(((EditChannels) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EditChannels{" +
            "id=" + getId() +
            ", idMessage=" + getIdMessage() +
            ", idChannel=" + getIdChannel() +
            ", dateCreateMessage='" + getDateCreateMessage() + "'" +
            ", lastNameChannel='" + getLastNameChannel() + "'" +
            ", newNameChannel='" + getNewNameChannel() + "'" +
            ", isAproveChange='" + getIsAproveChange() + "'" +
            ", lastLinkToChannel='" + getLastLinkToChannel() + "'" +
            ", newlastLinkToChannel='" + getNewlastLinkToChannel() + "'" +
            ", lastPriceChannel=" + getLastPriceChannel() +
            ", newPriceChannel=" + getNewPriceChannel() +
            ", addDescriptionAboutChannel='" + getAddDescriptionAboutChannel() + "'" +
            ", currentDescriptionChannel='" + getCurrentDescriptionChannel() + "'" +
            ", addRegionChannel='" + getAddRegionChannel() + "'" +
            ", editRegionChannel='" + getEditRegionChannel() + "'" +
            ", addCityChannel='" + getAddCityChannel() + "'" +
            ", editCityChannel='" + getEditCityChannel() + "'" +
            ", userId=" + getUserId() +
            ", userName='" + getUserName() + "'" +
            ", isApprovedChanhes='" + getIsApprovedChanhes() + "'" +
            ", comment='" + getComment() + "'" +
            ", status='" + getStatus() + "'" +
            ", serviceField1='" + getServiceField1() + "'" +
            ", serviceField2='" + getServiceField2() + "'" +
            ", serviceField3='" + getServiceField3() + "'" +
            "}";
    }
}
