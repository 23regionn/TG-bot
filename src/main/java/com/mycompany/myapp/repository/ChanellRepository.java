package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.domain.City;
import com.mycompany.myapp.domain.TGUser;
import com.mycompany.myapp.service.dto.channel.ChannelInfoDTO;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    @Query(
//        "SELECT new com.mycompany.myapp.service.dto.channel" +
//        ".ChannelInfoDTO(chan.id, chan.name, chan.link, chan.isModerate, chan.contacts, chan.startDate," +
//        "chan.lastPayDate, chan.endPublicDate, chan.comment, chan.priceForPay, chan.isPay) " +
//        "FROM Chanell chan " +
//        "where lower(chan.name) like concat('%',concat(lower(?1),'%')) " +
//        "and lower(chan.link) like concat('%',concat(lower(?2),'%')) " +
//        "and ((chan.lastPayDate > (?3) and chan.lastPayDate < (?4)) or (chan.lastPayDate is null))" +
//        "and ((chan.endPublicDate  > (?5) and chan.endPublicDate < (?6)) or (chan.endPublicDate is null)) "
//    )
//    Page<ChannelInfoDTO> findChannelInfoDTOPages(
//        String name,
//        String link,
//        ZonedDateTime startDateS,
//        ZonedDateTime startDateE,
//        ZonedDateTime endDateS,
//        ZonedDateTime endDateE,
//        Pageable firstPageWithTwoElements
//    );



    /// ///
    @Query(
        "SELECT new com.mycompany.myapp.service.dto.channel.ChannelInfoDTO(" +
            "chan.id, chan.name, chan.link, chan.isModerate, chan.contacts, " +
            "chan.startDate, chan.lastPayDate, chan.endPublicDate, chan.comment, " +
            "chan.priceForPay, chan.isPay) " +
            "FROM Chanell chan " +
            "WHERE (:name = '' OR lower(chan.name) LIKE lower(concat('%', :name, '%'))) " +
            "AND (:link = '' OR lower(chan.link) LIKE lower(concat('%', :link, '%'))) " +
            "AND (chan.lastPayDate IS NULL OR (chan.lastPayDate >= :startDateS AND chan.lastPayDate <= :startDateE)) " +
            "AND (chan.endPublicDate IS NULL OR (chan.endPublicDate >= :endDateS AND chan.endPublicDate <= :endDateE))"
    )
    Page<ChannelInfoDTO> findChannelInfoDTOPages(
        @Param("name") String name,
        @Param("link") String link,
        @Param("startDateS") ZonedDateTime startDateS,
        @Param("startDateE") ZonedDateTime startDateE,
        @Param("endDateS") ZonedDateTime endDateS,
        @Param("endDateE") ZonedDateTime endDateE,
        Pageable pageable
    );

    @Query(value = "SELECT COUNT(*) FROM chanell", nativeQuery = true)
    long countAllChannels();

    @Query(value = "SELECT * FROM chanell LIMIT 5", nativeQuery = true)
    List<Chanell> findAnyChannels();

    // Простой запрос без фильтров по датам
    @Query(
        "SELECT new com.mycompany.myapp.service.dto.channel.ChannelInfoDTO(" +
            "chan.id, chan.name, chan.link, chan.isModerate, chan.contacts, " +
            "chan.startDate, chan.lastPayDate, chan.endPublicDate, chan.comment, " +
            "chan.priceForPay, chan.isPay) " +
            "FROM Chanell chan " +
            "WHERE (:name = '' OR lower(chan.name) LIKE lower(concat('%', :name, '%'))) " +
            "AND (:link = '' OR lower(chan.link) LIKE lower(concat('%', :link, '%')))"
    )
    Page<ChannelInfoDTO> findChannelsWithoutDateFilters(
        @Param("name") String name,
        @Param("link") String link,
        Pageable pageable
    );

    // Запрос только для записей с null датами
    @Query(
        "SELECT new com.mycompany.myapp.service.dto.channel.ChannelInfoDTO(" +
            "chan.id, chan.name, chan.link, chan.isModerate, chan.contacts, " +
            "chan.startDate, chan.lastPayDate, chan.endPublicDate, chan.comment, " +
            "chan.priceForPay, chan.isPay) " +
            "FROM Chanell chan " +
            "WHERE (:name = '' OR lower(chan.name) LIKE lower(concat('%', :name, '%'))) " +
            "AND (:link = '' OR lower(chan.link) LIKE lower(concat('%', :link, '%'))) " +
            "AND (chan.lastPayDate IS NULL) " +
            "AND (chan.endPublicDate IS NULL)"
    )
    Page<ChannelInfoDTO> findChannelsWithNullDates(
        @Param("name") String name,
        @Param("link") String link,
        Pageable pageable
    );

    // Запрос для записей с любыми датами
    @Query(
        "SELECT new com.mycompany.myapp.service.dto.channel.ChannelInfoDTO(" +
            "chan.id, chan.name, chan.link, chan.isModerate, chan.contacts, " +
            "chan.startDate, chan.lastPayDate, chan.endPublicDate, chan.comment, " +
            "chan.priceForPay, chan.isPay) " +
            "FROM Chanell chan " +
            "WHERE (:name = '' OR lower(chan.name) LIKE lower(concat('%', :name, '%'))) " +
            "AND (:link = '' OR lower(chan.link) LIKE lower(concat('%', :link, '%'))) " +
            "AND (chan.lastPayDate IS NOT NULL OR chan.endPublicDate IS NOT NULL)"
    )
    Page<ChannelInfoDTO> findChannelsWithAnyDates(
        @Param("name") String name,
        @Param("link") String link,
        Pageable pageable
    );
}
