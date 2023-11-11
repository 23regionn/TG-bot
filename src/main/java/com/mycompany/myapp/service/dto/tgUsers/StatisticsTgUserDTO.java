package com.mycompany.myapp.service.dto.tgUsers;

import java.time.ZonedDateTime;

/**
 * A StatisticsTgUserDTO.
 */
public class StatisticsTgUserDTO {

    private Long id;
    private String firstName;
    private String userName;
    private ZonedDateTime registrationDate;
    private Long chatId;

    public StatisticsTgUserDTO() {}

    public StatisticsTgUserDTO(Long id, String firstName, String userName, ZonedDateTime registrationDate, Long chatId) {
        this.id = id;
        this.firstName = firstName;
        this.userName = userName;
        this.registrationDate = registrationDate;
        this.chatId = chatId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ZonedDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(ZonedDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
