package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Category;
import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.domain.TGUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Spring Data SQL repository for the Chanell entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChanellRepository extends JpaRepository<Chanell, Long> {

    @Query("select c from Chanell c " + " where c.priceDiapozon = :priceDiapozon")
    Set<Chanell> getAllByPriceDiapozon(@Param("priceDiapozon") Double chatId);

    @Query("select c from Chanell c " + " where c.categoryIds = :categoryId")
    Set<Chanell> getByCategoryId(@Param("categoryId") Set<Category> categoryId);
//    Set<Chanell> getByCategoryId(@Param("categoryId") Long categoryId);

    @Query("select c from Chanell c " + " where c.tGUser = :tgUser")
    List<Chanell> getAllByTGUser(@Param("tgUser") TGUser tgUser);
}
