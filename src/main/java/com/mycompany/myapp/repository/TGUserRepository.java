package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TGUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TGUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TGUserRepository extends JpaRepository<TGUser, Long> {}
