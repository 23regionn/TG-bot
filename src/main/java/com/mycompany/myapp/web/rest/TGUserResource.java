package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TGUser;
import com.mycompany.myapp.repository.TGUserRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.TGUser}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TGUserResource {

    private final Logger log = LoggerFactory.getLogger(TGUserResource.class);

    private static final String ENTITY_NAME = "tGUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TGUserRepository tGUserRepository;

    public TGUserResource(TGUserRepository tGUserRepository) {
        this.tGUserRepository = tGUserRepository;
    }

    /**
     * {@code POST  /tg-users} : Create a new tGUser.
     *
     * @param tGUser the tGUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tGUser, or with status {@code 400 (Bad Request)} if the tGUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tg-users")
    public ResponseEntity<TGUser> createTGUser(@RequestBody TGUser tGUser) throws URISyntaxException {
        log.debug("REST request to save TGUser : {}", tGUser);
        if (tGUser.getId() != null) {
            throw new BadRequestAlertException("A new tGUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TGUser result = tGUserRepository.save(tGUser);
        return ResponseEntity
            .created(new URI("/api/tg-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tg-users/:id} : Updates an existing tGUser.
     *
     * @param id the id of the tGUser to save.
     * @param tGUser the tGUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tGUser,
     * or with status {@code 400 (Bad Request)} if the tGUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tGUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tg-users/{id}")
    public ResponseEntity<TGUser> updateTGUser(@PathVariable(value = "id", required = false) final Long id, @RequestBody TGUser tGUser)
        throws URISyntaxException {
        log.debug("REST request to update TGUser : {}, {}", id, tGUser);
        if (tGUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tGUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tGUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TGUser result = tGUserRepository.save(tGUser);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tGUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tg-users/:id} : Partial updates given fields of an existing tGUser, field will ignore if it is null
     *
     * @param id the id of the tGUser to save.
     * @param tGUser the tGUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tGUser,
     * or with status {@code 400 (Bad Request)} if the tGUser is not valid,
     * or with status {@code 404 (Not Found)} if the tGUser is not found,
     * or with status {@code 500 (Internal Server Error)} if the tGUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tg-users/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TGUser> partialUpdateTGUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TGUser tGUser
    ) throws URISyntaxException {
        log.debug("REST request to partial update TGUser partially : {}, {}", id, tGUser);
        if (tGUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tGUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tGUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TGUser> result = tGUserRepository
            .findById(tGUser.getId())
            .map(
                existingTGUser -> {
                    if (tGUser.getIdTgUser() != null) {
                        existingTGUser.setIdTgUser(tGUser.getIdTgUser());
                    }
                    if (tGUser.getFirstName() != null) {
                        existingTGUser.setFirstName(tGUser.getFirstName());
                    }
                    if (tGUser.getRegistrationDate() != null) {
                        existingTGUser.setRegistrationDate(tGUser.getRegistrationDate());
                    }
                    if (tGUser.getUserRole() != null) {
                        existingTGUser.setUserRole(tGUser.getUserRole());
                    }
                    if (tGUser.getIsAdmin() != null) {
                        existingTGUser.setIsAdmin(tGUser.getIsAdmin());
                    }
                    if (tGUser.getScore() != null) {
                        existingTGUser.setScore(tGUser.getScore());
                    }
                    if (tGUser.getIsBlocked() != null) {
                        existingTGUser.setIsBlocked(tGUser.getIsBlocked());
                    }
                    if (tGUser.getChatId() != null) {
                        existingTGUser.setChatId(tGUser.getChatId());
                    }
                    if (tGUser.getIsDelete() != null) {
                        existingTGUser.setIsDelete(tGUser.getIsDelete());
                    }
                    if (tGUser.getDate1() != null) {
                        existingTGUser.setDate1(tGUser.getDate1());
                    }
                    if (tGUser.getDate2() != null) {
                        existingTGUser.setDate2(tGUser.getDate2());
                    }
                    if (tGUser.getLong1() != null) {
                        existingTGUser.setLong1(tGUser.getLong1());
                    }
                    if (tGUser.getString1() != null) {
                        existingTGUser.setString1(tGUser.getString1());
                    }
                    if (tGUser.getBoolean1() != null) {
                        existingTGUser.setBoolean1(tGUser.getBoolean1());
                    }

                    return existingTGUser;
                }
            )
            .map(tGUserRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tGUser.getId().toString())
        );
    }

    /**
     * {@code GET  /tg-users} : get all the tGUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tGUsers in body.
     */
    @GetMapping("/tg-users")
    public List<TGUser> getAllTGUsers() {
        log.debug("REST request to get all TGUsers");
        return tGUserRepository.findAll();
    }

    /**
     * {@code GET  /tg-users/:id} : get the "id" tGUser.
     *
     * @param id the id of the tGUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tGUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tg-users/{id}")
    public ResponseEntity<TGUser> getTGUser(@PathVariable Long id) {
        log.debug("REST request to get TGUser : {}", id);
        Optional<TGUser> tGUser = tGUserRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tGUser);
    }

    /**
     * {@code DELETE  /tg-users/:id} : delete the "id" tGUser.
     *
     * @param id the id of the tGUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tg-users/{id}")
    public ResponseEntity<Void> deleteTGUser(@PathVariable Long id) {
        log.debug("REST request to delete TGUser : {}", id);
        tGUserRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
