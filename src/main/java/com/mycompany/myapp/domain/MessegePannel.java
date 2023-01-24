package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A MessegePannel.
 */
@Entity
@Table(name = "messege_pannel")
public class MessegePannel implements Serializable {

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

    @Column(name = "text_message")
    private String textMessage;

    @Column(name = "id_admin")
    private Long idAdmin;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    private String status;

    @Column(name = "service_field_1")
    private String serviceField1; // чат айди пользователя (ЗАПОЛНЯЕТСЯ ТОЛЬКО КОГДА НЕ ЗАПОЛНЯЕТСЯ КАНАЛ АЙДИ)

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

    public MessegePannel id(Long id) {
        this.id = id;
        return this;
    }

    public Long getIdMessage() {
        return this.idMessage;
    }

    public MessegePannel idMessage(Long idMessage) {
        this.idMessage = idMessage;
        return this;
    }

    public void setIdMessage(Long idMessage) {
        this.idMessage = idMessage;
    }

    public Long getIdChannel() {
        return this.idChannel;
    }

    public MessegePannel idChannel(Long idChannel) {
        this.idChannel = idChannel;
        return this;
    }

    public void setIdChannel(Long idChannel) {
        this.idChannel = idChannel;
    }

    public ZonedDateTime getDateCreateMessage() {
        return this.dateCreateMessage;
    }

    public MessegePannel dateCreateMessage(ZonedDateTime dateCreateMessage) {
        this.dateCreateMessage = dateCreateMessage;
        return this;
    }

    public void setDateCreateMessage(ZonedDateTime dateCreateMessage) {
        this.dateCreateMessage = dateCreateMessage;
    }

    public String getTextMessage() {
        return this.textMessage;
    }

    public MessegePannel textMessage(String textMessage) {
        this.textMessage = textMessage;
        return this;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Long getIdAdmin() {
        return this.idAdmin;
    }

    public MessegePannel idAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
        return this;
    }

    public void setIdAdmin(Long idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getComment() {
        return this.comment;
    }

    public MessegePannel comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return this.status;
    }

    public MessegePannel status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceField1() {
        return this.serviceField1;
    }

    public MessegePannel serviceField1(String serviceField1) {
        this.serviceField1 = serviceField1;
        return this;
    }

    public void setServiceField1(String serviceField1) {
        this.serviceField1 = serviceField1;
    }

    public String getServiceField2() {
        return this.serviceField2;
    }

    public MessegePannel serviceField2(String serviceField2) {
        this.serviceField2 = serviceField2;
        return this;
    }

    public void setServiceField2(String serviceField2) {
        this.serviceField2 = serviceField2;
    }

    public String getServiceField3() {
        return this.serviceField3;
    }

    public MessegePannel serviceField3(String serviceField3) {
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
        if (!(o instanceof MessegePannel)) {
            return false;
        }
        return id != null && id.equals(((MessegePannel) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessegePannel{" +
            "id=" + getId() +
            ", idMessage=" + getIdMessage() +
            ", idChannel=" + getIdChannel() +
            ", dateCreateMessage='" + getDateCreateMessage() + "'" +
            ", textMessage='" + getTextMessage() + "'" +
            ", idAdmin=" + getIdAdmin() +
            ", comment='" + getComment() + "'" +
            ", status='" + getStatus() + "'" +
            ", serviceField1='" + getServiceField1() + "'" +
            ", serviceField2='" + getServiceField2() + "'" +
            ", serviceField3='" + getServiceField3() + "'" +
            "}";
    }
}
