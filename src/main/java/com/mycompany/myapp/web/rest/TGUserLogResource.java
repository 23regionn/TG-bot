package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TGUserLog;
import com.mycompany.myapp.repository.TGUserLogRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TGUserLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TGUserLogResource {

    private final Logger log = LoggerFactory.getLogger(TGUserLogResource.class);

    private static final String ENTITY_NAME = "tGUserLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TGUserLogRepository tGUserLogRepository;

    public TGUserLogResource(TGUserLogRepository tGUserLogRepository) {
        this.tGUserLogRepository = tGUserLogRepository;
    }

    /**
     * {@code POST  /tg-user-logs} : Create a new tGUserLog.
     *
     * @param tGUserLog the tGUserLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tGUserLog, or with status {@code 400 (Bad Request)} if the tGUserLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tg-user-logs")
    public ResponseEntity<TGUserLog> createTGUserLog(@RequestBody TGUserLog tGUserLog) throws URISyntaxException {
        log.debug("REST request to save TGUserLog : {}", tGUserLog);
        if (tGUserLog.getId() != null) {
            throw new BadRequestAlertException("A new tGUserLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TGUserLog result = tGUserLogRepository.save(tGUserLog);
        return ResponseEntity
            .created(new URI("/api/tg-user-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tg-user-logs/:id} : Updates an existing tGUserLog.
     *
     * @param id the id of the tGUserLog to save.
     * @param tGUserLog the tGUserLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tGUserLog,
     * or with status {@code 400 (Bad Request)} if the tGUserLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tGUserLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tg-user-logs/{id}")
    public ResponseEntity<TGUserLog> updateTGUserLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TGUserLog tGUserLog
    ) throws URISyntaxException {
        log.debug("REST request to update TGUserLog : {}, {}", id, tGUserLog);
        if (tGUserLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tGUserLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tGUserLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TGUserLog result = tGUserLogRepository.save(tGUserLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tGUserLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tg-user-logs/:id} : Partial updates given fields of an existing tGUserLog, field will ignore if it is null
     *
     * @param id the id of the tGUserLog to save.
     * @param tGUserLog the tGUserLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tGUserLog,
     * or with status {@code 400 (Bad Request)} if the tGUserLog is not valid,
     * or with status {@code 404 (Not Found)} if the tGUserLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the tGUserLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tg-user-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TGUserLog> partialUpdateTGUserLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TGUserLog tGUserLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update TGUserLog partially : {}, {}", id, tGUserLog);
        if (tGUserLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tGUserLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tGUserLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TGUserLog> result = tGUserLogRepository
            .findById(tGUserLog.getId())
            .map(
                existingTGUserLog -> {
                    if (tGUserLog.getIdTgUser() != null) {
                        existingTGUserLog.setIdTgUser(tGUserLog.getIdTgUser());
                    }
                    if (tGUserLog.getFirstName() != null) {
                        existingTGUserLog.setFirstName(tGUserLog.getFirstName());
                    }
                    if (tGUserLog.getRegistrationDate() != null) {
                        existingTGUserLog.setRegistrationDate(tGUserLog.getRegistrationDate());
                    }
                    if (tGUserLog.getUserRole() != null) {
                        existingTGUserLog.setUserRole(tGUserLog.getUserRole());
                    }
                    if (tGUserLog.getIsAdmin() != null) {
                        existingTGUserLog.setIsAdmin(tGUserLog.getIsAdmin());
                    }
                    if (tGUserLog.getScore() != null) {
                        existingTGUserLog.setScore(tGUserLog.getScore());
                    }
                    if (tGUserLog.getIsBlocked() != null) {
                        existingTGUserLog.setIsBlocked(tGUserLog.getIsBlocked());
                    }
                    if (tGUserLog.getChatId() != null) {
                        existingTGUserLog.setChatId(tGUserLog.getChatId());
                    }
                    if (tGUserLog.getIsDelete() != null) {
                        existingTGUserLog.setIsDelete(tGUserLog.getIsDelete());
                    }
                    if (tGUserLog.getDate1() != null) {
                        existingTGUserLog.setDate1(tGUserLog.getDate1());
                    }
                    if (tGUserLog.getDate2() != null) {
                        existingTGUserLog.setDate2(tGUserLog.getDate2());
                    }
                    if (tGUserLog.getLong1() != null) {
                        existingTGUserLog.setLong1(tGUserLog.getLong1());
                    }
                    if (tGUserLog.getString1() != null) {
                        existingTGUserLog.setString1(tGUserLog.getString1());
                    }
                    if (tGUserLog.getBoolean1() != null) {
                        existingTGUserLog.setBoolean1(tGUserLog.getBoolean1());
                    }

                    return existingTGUserLog;
                }
            )
            .map(tGUserLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tGUserLog.getId().toString())
        );
    }

    /**
     * {@code GET  /tg-user-logs} : get all the tGUserLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tGUserLogs in body.
     */
    @GetMapping("/tg-user-logs")
    public List<TGUserLog> getAllTGUserLogs() {
        log.debug("REST request to get all TGUserLogs");
        return tGUserLogRepository.findAll();
    }

    /**
     * {@code GET  /tg-user-logs/:id} : get the "id" tGUserLog.
     *
     * @param id the id of the tGUserLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tGUserLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tg-user-logs/{id}")
    public ResponseEntity<TGUserLog> getTGUserLog(@PathVariable Long id) {
        log.debug("REST request to get TGUserLog : {}", id);
        Optional<TGUserLog> tGUserLog = tGUserLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tGUserLog);
    }

    /**
     * {@code DELETE  /tg-user-logs/:id} : delete the "id" tGUserLog.
     *
     * @param id the id of the tGUserLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tg-user-logs/{id}")
    public ResponseEntity<Void> deleteTGUserLog(@PathVariable Long id) {
        log.debug("REST request to delete TGUserLog : {}", id);
        tGUserLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
