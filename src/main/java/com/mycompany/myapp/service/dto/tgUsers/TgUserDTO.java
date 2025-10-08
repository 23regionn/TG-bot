package com.mycompany.myapp.service.dto.tgUsers;

import com.mycompany.myapp.domain.TGUser;
import java.time.ZonedDateTime;

public class TgUserDTO {

    private Long id;
    private String firstName;
    private String userName;
    private Long chatId;
    private ZonedDateTime registrationDate;

    public TgUserDTO(String firstName, String userName, Long chatId, ZonedDateTime registrationDate) {
        this.firstName = firstName;
        this.userName = userName;
        this.chatId = chatId;
        this.registrationDate = registrationDate;
    }

    public static TgUserDTO fromEntity(TGUser user) {
        return new TgUserDTO(user.getFirstName(), user.getUserName(), user.getChatId(), user.getRegistrationDate());
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getChatId() {
        return chatId;
    }

    public ZonedDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return (
            "TgUserDTO{" +
            "id=" +
            id +
            "firstName='" +
            firstName +
            '\'' +
            ", userName='" +
            userName +
            '\'' +
            ", chatId=" +
            chatId +
            ", registrationDate=" +
            registrationDate +
            '}'
        );
    }
}
