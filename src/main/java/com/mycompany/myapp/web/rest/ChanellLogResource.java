package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ChanellLog;
import com.mycompany.myapp.repository.ChanellLogRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ChanellLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ChanellLogResource {

    private final Logger log = LoggerFactory.getLogger(ChanellLogResource.class);

    private static final String ENTITY_NAME = "chanellLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChanellLogRepository chanellLogRepository;

    public ChanellLogResource(ChanellLogRepository chanellLogRepository) {
        this.chanellLogRepository = chanellLogRepository;
    }

    /**
     * {@code POST  /chanell-logs} : Create a new chanellLog.
     *
     * @param chanellLog the chanellLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chanellLog, or with status {@code 400 (Bad Request)} if the chanellLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chanell-logs")
    public ResponseEntity<ChanellLog> createChanellLog(@RequestBody ChanellLog chanellLog) throws URISyntaxException {
        log.debug("REST request to save ChanellLog : {}", chanellLog);
        if (chanellLog.getId() != null) {
            throw new BadRequestAlertException("A new chanellLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChanellLog result = chanellLogRepository.save(chanellLog);
        return ResponseEntity
            .created(new URI("/api/chanell-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chanell-logs/:id} : Updates an existing chanellLog.
     *
     * @param id the id of the chanellLog to save.
     * @param chanellLog the chanellLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chanellLog,
     * or with status {@code 400 (Bad Request)} if the chanellLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chanellLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chanell-logs/{id}")
    public ResponseEntity<ChanellLog> updateChanellLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ChanellLog chanellLog
    ) throws URISyntaxException {
        log.debug("REST request to update ChanellLog : {}, {}", id, chanellLog);
        if (chanellLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chanellLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chanellLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ChanellLog result = chanellLogRepository.save(chanellLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, chanellLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /chanell-logs/:id} : Partial updates given fields of an existing chanellLog, field will ignore if it is null
     *
     * @param id the id of the chanellLog to save.
     * @param chanellLog the chanellLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chanellLog,
     * or with status {@code 400 (Bad Request)} if the chanellLog is not valid,
     * or with status {@code 404 (Not Found)} if the chanellLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the chanellLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/chanell-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ChanellLog> partialUpdateChanellLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ChanellLog chanellLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update ChanellLog partially : {}, {}", id, chanellLog);
        if (chanellLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chanellLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chanellLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ChanellLog> result = chanellLogRepository
            .findById(chanellLog.getId())
            .map(
                existingChanellLog -> {
                    if (chanellLog.getName() != null) {
                        existingChanellLog.setName(chanellLog.getName());
                    }
                    if (chanellLog.getLink() != null) {
                        existingChanellLog.setLink(chanellLog.getLink());
                    }
                    if (chanellLog.getScore() != null) {
                        existingChanellLog.setScore(chanellLog.getScore());
                    }
                    if (chanellLog.getStatus() != null) {
                        existingChanellLog.setStatus(chanellLog.getStatus());
                    }
                    if (chanellLog.getCountSubscribers() != null) {
                        existingChanellLog.setCountSubscribers(chanellLog.getCountSubscribers());
                    }
                    if (chanellLog.getQuailityFromAnotherSources() != null) {
                        existingChanellLog.setQuailityFromAnotherSources(chanellLog.getQuailityFromAnotherSources());
                    }
                    if (chanellLog.getPriceDiapozon() != null) {
                        existingChanellLog.setPriceDiapozon(chanellLog.getPriceDiapozon());
                    }
                    if (chanellLog.getIsModerate() != null) {
                        existingChanellLog.setIsModerate(chanellLog.getIsModerate());
                    }
                    if (chanellLog.getShowChanellInTopByCategory() != null) {
                        existingChanellLog.setShowChanellInTopByCategory(chanellLog.getShowChanellInTopByCategory());
                    }
                    if (chanellLog.getRegion() != null) {
                        existingChanellLog.setRegion(chanellLog.getRegion());
                    }
                    if (chanellLog.getCity() != null) {
                        existingChanellLog.setCity(chanellLog.getCity());
                    }
                    if (chanellLog.getIsDelete() != null) {
                        existingChanellLog.setIsDelete(chanellLog.getIsDelete());
                    }
                    if (chanellLog.getCurrentDate() != null) {
                        existingChanellLog.setCurrentDate(chanellLog.getCurrentDate());
                    }
                    if (chanellLog.getDate1() != null) {
                        existingChanellLog.setDate1(chanellLog.getDate1());
                    }
                    if (chanellLog.getDate2() != null) {
                        existingChanellLog.setDate2(chanellLog.getDate2());
                    }
                    if (chanellLog.getLong1() != null) {
                        existingChanellLog.setLong1(chanellLog.getLong1());
                    }
                    if (chanellLog.getString1() != null) {
                        existingChanellLog.setString1(chanellLog.getString1());
                    }
                    if (chanellLog.getBoolean1() != null) {
                        existingChanellLog.setBoolean1(chanellLog.getBoolean1());
                    }

                    return existingChanellLog;
                }
            )
            .map(chanellLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, chanellLog.getId().toString())
        );
    }

    /**
     * {@code GET  /chanell-logs} : get all the chanellLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chanellLogs in body.
     */
    @GetMapping("/chanell-logs")
    public List<ChanellLog> getAllChanellLogs() {
        log.debug("REST request to get all ChanellLogs");
        return chanellLogRepository.findAll();
    }

    /**
     * {@code GET  /chanell-logs/:id} : get the "id" chanellLog.
     *
     * @param id the id of the chanellLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chanellLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chanell-logs/{id}")
    public ResponseEntity<ChanellLog> getChanellLog(@PathVariable Long id) {
        log.debug("REST request to get ChanellLog : {}", id);
        Optional<ChanellLog> chanellLog = chanellLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(chanellLog);
    }

    /**
     * {@code DELETE  /chanell-logs/:id} : delete the "id" chanellLog.
     *
     * @param id the id of the chanellLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chanell-logs/{id}")
    public ResponseEntity<Void> deleteChanellLog(@PathVariable Long id) {
        log.debug("REST request to delete ChanellLog : {}", id);
        chanellLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
