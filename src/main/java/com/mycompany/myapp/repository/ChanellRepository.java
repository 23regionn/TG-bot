package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.domain.City;
import com.mycompany.myapp.domain.TGUser;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Chanell entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChanellRepository extends JpaRepository<Chanell, Long> {
    @Query("select c from Chanell c " + " where c.priceDiapozon = :priceDiapozon")
    Set<Chanell> getAllByPriceDiapozon(@Param("priceDiapozon") Double chatId);

    @Query("select c from Chanell c " + " where c.tGUser = :tgUser")
    List<Chanell> getAllByTGUser(@Param("tgUser") TGUser tgUser);

    // Rest- запросы для фронта
    @Query("SELECT new com.mycompany.myapp.service.dto.ChannelNameAndIDDTO(chan.id, chan.name) " + "FROM Chanell chan")
    List<com.mycompany.myapp.service.dto.ChannelNameAndIDDTO> getAllChanellsNamesAndIdDTO();

    @Query(
        "SELECT new com.mycompany.myapp.service.dto.channel" +
        ".ChannelInfoDTO(chan.id, chan.name, chan.link, chan.isModerate, chan.contacts, chan.startDate," +
        "chan.lastPayDate, chan.endPublicDate, chan.comment, chan.priceForPay, chan.isPay) " +
        "FROM Chanell chan"
    )
    List<com.mycompany.myapp.service.dto.channel.ChannelInfoDTO> getAllChanellsInfoDTO();
}
