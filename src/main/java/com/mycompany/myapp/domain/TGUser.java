package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A TGUser.
 */
@Entity
@Table(name = "tg_user")
public class TGUser implements Serializable {

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

    @JsonIgnoreProperties(value = { "balanceLogs", "tGUser" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Balance balance;

    @OneToMany(mappedBy = "tGUser")
    @JsonIgnoreProperties(value = { "linksByCategoryInTops", "chanellLogs", "tGUser", "categoryIds" }, allowSetters = true)
    private Set<Chanell> chanells = new HashSet<>();

    @OneToMany(mappedBy = "tGUser")
    @JsonIgnoreProperties(value = { "offerFromCostumersLogs", "tGUser" }, allowSetters = true)
    private Set<OfferFromCostumers> offerFromCostumers = new HashSet<>();

    @OneToMany(mappedBy = "tGUser")
    @JsonIgnoreProperties(value = { "tGUser" }, allowSetters = true)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "tGUser")
    @JsonIgnoreProperties(value = { "tGUser" }, allowSetters = true)
    private Set<Pays> pays = new HashSet<>();

    @OneToMany(mappedBy = "tGUser")
    @JsonIgnoreProperties(value = { "tGUser" }, allowSetters = true)
    private Set<TGUserLog> tGUserLogs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TGUser id(Long id) {
        this.id = id;
        return this;
    }

    public Long getIdTgUser() {
        return this.idTgUser;
    }

    public TGUser idTgUser(Long idTgUser) {
        this.idTgUser = idTgUser;
        return this;
    }

    public void setIdTgUser(Long idTgUser) {
        this.idTgUser = idTgUser;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public TGUser firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ZonedDateTime getRegistrationDate() {
        return this.registrationDate;
    }

    public TGUser registrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public void setRegistrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUserRole() {
        return this.userRole;
    }

    public TGUser userRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Boolean getIsAdmin() {
        return this.isAdmin;
    }

    public TGUser isAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Double getScore() {
        return this.score;
    }

    public TGUser score(Double score) {
        this.score = score;
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Boolean getIsBlocked() {
        return this.isBlocked;
    }

    public TGUser isBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
        return this;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Long getChatId() {
        return this.chatId;
    }

    public TGUser chatId(Long chatId) {
        this.chatId = chatId;
        return this;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Boolean getIsDelete() {
        return this.isDelete;
    }

    public TGUser isDelete(Boolean isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getDate1() {
        return this.date1;
    }

    public TGUser date1(ZonedDateTime date1) {
        this.date1 = date1;
        return this;
    }

    public void setDate1(ZonedDateTime date1) {
        this.date1 = date1;
    }

    public ZonedDateTime getDate2() {
        return this.date2;
    }

    public TGUser date2(ZonedDateTime date2) {
        this.date2 = date2;
        return this;
    }

    public void setDate2(ZonedDateTime date2) {
        this.date2 = date2;
    }

    public Long getLong1() {
        return this.long1;
    }

    public TGUser long1(Long long1) {
        this.long1 = long1;
        return this;
    }

    public void setLong1(Long long1) {
        this.long1 = long1;
    }

    public String getString1() {
        return this.string1;
    }

    public TGUser string1(String string1) {
        this.string1 = string1;
        return this;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public Boolean getBoolean1() {
        return this.boolean1;
    }

    public TGUser boolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
        return this;
    }

    public void setBoolean1(Boolean boolean1) {
        this.boolean1 = boolean1;
    }

    public Balance getBalance() {
        return this.balance;
    }

    public TGUser balance(Balance balance) {
        this.setBalance(balance);
        return this;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public Set<Chanell> getChanells() {
        return this.chanells;
    }

    public TGUser chanells(Set<Chanell> chanells) {
        this.setChanells(chanells);
        return this;
    }

    public TGUser addChanell(Chanell chanell) {
        this.chanells.add(chanell);
        chanell.setTGUser(this);
        return this;
    }

    public TGUser removeChanell(Chanell chanell) {
        this.chanells.remove(chanell);
        chanell.setTGUser(null);
        return this;
    }

    public void setChanells(Set<Chanell> chanells) {
        if (this.chanells != null) {
            this.chanells.forEach(i -> i.setTGUser(null));
        }
        if (chanells != null) {
            chanells.forEach(i -> i.setTGUser(this));
        }
        this.chanells = chanells;
    }

    public Set<OfferFromCostumers> getOfferFromCostumers() {
        return this.offerFromCostumers;
    }

    public TGUser offerFromCostumers(Set<OfferFromCostumers> offerFromCostumers) {
        this.setOfferFromCostumers(offerFromCostumers);
        return this;
    }

    public TGUser addOfferFromCostumers(OfferFromCostumers offerFromCostumers) {
        this.offerFromCostumers.add(offerFromCostumers);
        offerFromCostumers.setTGUser(this);
        return this;
    }

    public TGUser removeOfferFromCostumers(OfferFromCostumers offerFromCostumers) {
        this.offerFromCostumers.remove(offerFromCostumers);
        offerFromCostumers.setTGUser(null);
        return this;
    }

    public void setOfferFromCostumers(Set<OfferFromCostumers> offerFromCostumers) {
        if (this.offerFromCostumers != null) {
            this.offerFromCostumers.forEach(i -> i.setTGUser(null));
        }
        if (offerFromCostumers != null) {
            offerFromCostumers.forEach(i -> i.setTGUser(this));
        }
        this.offerFromCostumers = offerFromCostumers;
    }

    public Set<Review> getReviews() {
        return this.reviews;
    }

    public TGUser reviews(Set<Review> reviews) {
        this.setReviews(reviews);
        return this;
    }

    public TGUser addReview(Review review) {
        this.reviews.add(review);
        review.setTGUser(this);
        return this;
    }

    public TGUser removeReview(Review review) {
        this.reviews.remove(review);
        review.setTGUser(null);
        return this;
    }

    public void setReviews(Set<Review> reviews) {
        if (this.reviews != null) {
            this.reviews.forEach(i -> i.setTGUser(null));
        }
        if (reviews != null) {
            reviews.forEach(i -> i.setTGUser(this));
        }
        this.reviews = reviews;
    }

    public Set<Pays> getPays() {
        return this.pays;
    }

    public TGUser pays(Set<Pays> pays) {
        this.setPays(pays);
        return this;
    }

    public TGUser addPays(Pays pays) {
        this.pays.add(pays);
        pays.setTGUser(this);
        return this;
    }

    public TGUser removePays(Pays pays) {
        this.pays.remove(pays);
        pays.setTGUser(null);
        return this;
    }

    public void setPays(Set<Pays> pays) {
        if (this.pays != null) {
            this.pays.forEach(i -> i.setTGUser(null));
        }
        if (pays != null) {
            pays.forEach(i -> i.setTGUser(this));
        }
        this.pays = pays;
    }

    public Set<TGUserLog> getTGUserLogs() {
        return this.tGUserLogs;
    }

    public TGUser tGUserLogs(Set<TGUserLog> tGUserLogs) {
        this.setTGUserLogs(tGUserLogs);
        return this;
    }

    public TGUser addTGUserLog(TGUserLog tGUserLog) {
        this.tGUserLogs.add(tGUserLog);
        tGUserLog.setTGUser(this);
        return this;
    }

    public TGUser removeTGUserLog(TGUserLog tGUserLog) {
        this.tGUserLogs.remove(tGUserLog);
        tGUserLog.setTGUser(null);
        return this;
    }

    public void setTGUserLogs(Set<TGUserLog> tGUserLogs) {
        if (this.tGUserLogs != null) {
            this.tGUserLogs.forEach(i -> i.setTGUser(null));
        }
        if (tGUserLogs != null) {
            tGUserLogs.forEach(i -> i.setTGUser(this));
        }
        this.tGUserLogs = tGUserLogs;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TGUser)) {
            return false;
        }
        return id != null && id.equals(((TGUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TGUser{" +
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
