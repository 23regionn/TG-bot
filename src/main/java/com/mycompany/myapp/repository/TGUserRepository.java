package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TGUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Spring Data SQL repository for the TGUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TGUserRepository extends JpaRepository<TGUser, Long> {

    TGUser findByChatId(Long chatId);

    Set<TGUser> findAllByChatId(Long chatId);

    @Query("select t from TGUser t " + " where (t.chatId = :chatId)" + " and  (t.isDelete = true ) ")
    Set<TGUser> getByChatIdAndDeleteTrue(@Param("chatId") Long chatId);


}
