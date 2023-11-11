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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "registration_date")
    private ZonedDateTime registrationDate;

    @Column(name = "user_role")
    private String userRole;

    @Column(name = "current_step")
    private String currentStep;

    @Column(name = "id_current_channel_action")
    private Long idCurrentChannelAction;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "score")
    private Double score;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

    @Column(name = "chat_id")
    private Long chatId;

    @ApiModelProperty(value = "удаленный")
    @Column(name = "is_delete")
    private Boolean isDelete;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public TGUser userName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public Set<TGUserLog> gettGUserLogs() {
        return tGUserLogs;
    }

    public void settGUserLogs(Set<TGUserLog> tGUserLogs) {
        this.tGUserLogs = tGUserLogs;
    }

    public TGUser currentStep(String currentStep) {
        this.currentStep = currentStep;
        return this;
    }

    public Long getIdCurrentChannelAction() {
        return idCurrentChannelAction;
    }

    public void setIdCurrentChannelAction(Long idCurrentChannelAction) {
        this.idCurrentChannelAction = idCurrentChannelAction;
    }

    public TGUser idCurrentChannelAction(Long idCurrentChannelAction) {
        this.idCurrentChannelAction = idCurrentChannelAction;
        return this;
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

    @Override
    public String toString() {
        return (
            "TGUser{" +
            "id=" +
            id +
            ", userName='" +
            userName +
            '\'' +
            ", firstName='" +
            firstName +
            '\'' +
            ", registrationDate=" +
            registrationDate +
            ", userRole='" +
            userRole +
            '\'' +
            ", currentStep='" +
            currentStep +
            '\'' +
            ", idCurrentChannelAction=" +
            idCurrentChannelAction +
            ", isAdmin=" +
            isAdmin +
            ", isDelete=" +
            isDelete +
            ", score=" +
            score +
            ", isBlocked=" +
            isBlocked +
            ", chatId=" +
            chatId +
            ", balance=" +
            balance +
            ", offerFromCostumers=" +
            offerFromCostumers +
            ", reviews=" +
            reviews +
            ", pays=" +
            pays +
            ", tGUserLogs=" +
            tGUserLogs +
            '}'
        );
    }
}
