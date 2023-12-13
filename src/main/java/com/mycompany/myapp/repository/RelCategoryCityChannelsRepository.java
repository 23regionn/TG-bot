package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.*;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RelCategoryCityChannels entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelCategoryCityChannelsRepository extends JpaRepository<RelCategoryCityChannels, Long> {
    List<RelCategoryCityChannels> getAllByRelCategoryCity(RelCategoryCity relCategoryCity);

    //Запрос для получения кананлов по городу и категории
    @Query(
        "select relCCCh" +
        " from RelCategoryCityChannels relCCCh join relCCCh.chanell ch join relCCCh.relCategoryCity relCatCit " +
        " where ch.isModerate = true and ch.endPublicDate > :currentDate " +
        " and relCCCh.isShowChannel = true " +
        " and relCatCit.id = :relCatCit "
    )
    List<RelCategoryCityChannels> findRelCategoryCityChannelsByRelCategoryCityId(
        @Param("relCatCit") Long relCatCit,
        @Param("currentDate") ZonedDateTime currentDate
    );

    Boolean existsByChanellAndRelCategoryCity(Chanell chanell, RelCategoryCity relCategoryCity);
}
