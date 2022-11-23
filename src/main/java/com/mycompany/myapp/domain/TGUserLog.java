package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

/**
 * A TGUserLog.
 */
@Entity
@Table(name = "tg_user_log")
public class TGUserLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_tg_user")
    private Long idTgUser;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "registration_date")
    private ZonedDateTime registrationDate;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "score")
    private Double score;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "chat_id")
    private Long chatId;

    /**
     * удаленный
     */
    @ApiModelProperty(value = "удаленный")
    @Column(name = "is_delete")
    private Boolean isDelete;

    @Column(name = "date_1")
    private ZonedDateTime date1;

    @Column(name = "date_2")
    private ZonedDateTime date2;

    @Column(name = "long_1")
    private Long long1;

    @Column(name = "string_1")
    private String string1;

    @Column(name = "boolean_1")
    private Boolean boolean1;

    @ManyToOne
    @JsonIgnoreProperties(value = { "balance", "chanells", "offerFromCostumers", "reviews", "pays", "tGUserLogs" }, allowSetters = true)
    private TGUser tGUser;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TGUserLog id(Long id) {
        this.id = id;
        return this;
    }

    public Long getIdTgUser() {
        return this.idTgUser;
    }

    public TGUserLog idTgUser(Long idTgUser) {
        this.idTgUser = idTgUser;
        return this;
    }

    public void setIdTgUser(Long idTgUser) {
        this.idTgUser = idTgUser;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public TGUserLog firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ZonedDateTime getRegistrationDate() {
        return this.registrationDate;
    }

    public TGUserLog registrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public void setRegistrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUserRole() {
        return this.userRole;
    }

    public TGUserLog userRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Boolean getIsAdmin() {
        return this.isAdmin;
    }

    public TGUserLog isAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Double getScore() {
        return this.score;
    }

    public TGUserLog score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getIsBlocked() {
        return this.isBlocked;
    }

    public TGUserLog isBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
        return this;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Long getChatId() {
        return this.chatId;
    }

    public TGUserLog chatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public TGUserLog isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public TGUserLog date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public TGUserLog date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public TGUserLog long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public TGUserLog string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public TGUserLog boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public TGUser getTGUser() {
        return this.tGUser;
    }

    public TGUserLog tGUser(TGUser tGUser) {
        this.setTGUser(tGUser);
        return this;
    }

    public void setTGUser(TGUser tGUser) {
        this.tGUser = tGUser;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TGUserLog)) {
            return false;
        }
        return id != null && id.equals(((TGUserLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TGUserLog{" +
            "id=" + getId() +
            ", idTgUser=" + getIdTgUser() +
            ", firstName='" + getFirstName() + "'" +
            ", registrationDate='" + getRegistrationDate() + "'" +
            ", userRole='" + getUserRole() + "'" +
            ", isAdmin='" + getIsAdmin() + "'" +
            ", score=" + getScore() +
            ", isBlocked='" + getIsBlocked() + "'" +
            ", chatId=" + getChatId() +
            ", isDelete='" + getIsDelete() + "'" +
            ", date1='" + getDate1() + "'" +
            ", date2='" + getDate2() + "'" +
            ", long1=" + getLong1() +
            ", string1='" + getString1() + "'" +
            ", boolean1='" + getBoolean1() + "'" +
            "}";
    }
}
