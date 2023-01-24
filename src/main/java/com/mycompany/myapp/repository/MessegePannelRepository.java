package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MessegePannel;
import com.mycompany.myapp.domain.TGUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Spring Data SQL repository for the MessegePannel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessegePannelRepository extends JpaRepository<MessegePannel, Long> {

    @Query("select m from MessegePannel m " + " where (m.idChannel = :channelId)" + " and  (m.status = :status ) ")
    Set<MessegePannel> getByChannelIdAndStatus(@Param("channelId") Long channelId, @Param("status") String status);

    @Query("select m from MessegePannel m " + " where (m.serviceField1 = :serviceField1)" + " and  (m.status = :status ) ")
    Set<MessegePannel> getByChatIdAndStatus(@Param("serviceField1") String serviceField1, @Param("status") String status);
}
