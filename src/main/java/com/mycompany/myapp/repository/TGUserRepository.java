package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TGUser;
import com.mycompany.myapp.service.dto.tgUsers.StatisticsTgUserDTO;
import com.mycompany.myapp.service.dto.tgUsers.TgUsersCountDTO;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TGUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TGUserRepository extends JpaRepository<TGUser, Long> {
    TGUser findByChatId(Long chatId);

    Set<TGUser> findAllByChatId(Long chatId);

    @Query(
        " SELECT u FROM TGUser u WHERE (:firstName = '' OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND (:userName = '' OR LOWER(u.userName) LIKE LOWER(CONCAT('%', :userName, '%'))) AND (u.registrationDate BETWEEN :startDate AND :endDate)"
    )
    Page<TGUser> findTgUsersWithFilters(
        @Param("firstName") String firstName,
        @Param("userName") String userName,
        @Param("startDate") ZonedDateTime startDate,
        @Param("endDate") ZonedDateTime endDate,
        Pageable pageable
    );

    @Query("select t from TGUser t " + " where (t.chatId = :chatId)" + " and  (t.isDelete = true ) ")
    Set<TGUser> getByChatIdAndDeleteTrue(@Param("chatId") Long chatId);

    @Query("select t from TGUser t " + " where (t.chatId = :chatId)" + " and  (t.isDelete = false ) ")
    TGUser getOneChatIdAndDeleteFalse(@Param("chatId") Long chatId);

    @Query("select t from TGUser t " + " where t.isAdmin = true")
    Set<TGUser> getAllAdmins();

    @Query("SELECT COUNT(DISTINCT tgUser.chatId ) FROM TGUser tgUser")
    Long getTGUserCount();

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.tgUsers.TgUsersCountDTO(COUNT(DISTINCT tgUser.chatId )) FROM TGUser tgUser " +
        "  where tgUser.registrationDate >= :startDate and tgUser.registrationDate <= :endDate"
    )
    Optional<TgUsersCountDTO> getTGUserCountByDates(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate);

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.tgUsers" +
        ".StatisticsTgUserDTO(tgUser.id, tgUser.firstName, tgUser.userName, tgUser.registrationDate, tgUser.chatId )" +
        " FROM TGUser tgUser "
    )
    List<StatisticsTgUserDTO> getAllTgUsersForStatistics();
}
