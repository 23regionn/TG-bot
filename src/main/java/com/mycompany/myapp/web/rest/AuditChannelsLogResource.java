package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.AuditChannelsLog;
import com.mycompany.myapp.repository.AuditChannelsLogRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.AuditChannelsLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AuditChannelsLogResource {

    private final Logger log = LoggerFactory.getLogger(AuditChannelsLogResource.class);

    private static final String ENTITY_NAME = "auditChannelsLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuditChannelsLogRepository auditChannelsLogRepository;

    public AuditChannelsLogResource(AuditChannelsLogRepository auditChannelsLogRepository) {
        this.auditChannelsLogRepository = auditChannelsLogRepository;
    }

    /**
     * {@code POST  /audit-channels-logs} : Create a new auditChannelsLog.
     *
     * @param auditChannelsLog the auditChannelsLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auditChannelsLog, or with status {@code 400 (Bad Request)} if the auditChannelsLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    //    @PostMapping("/audit-channels-logs")
    public ResponseEntity<AuditChannelsLog> createAuditChannelsLog(@RequestBody AuditChannelsLog auditChannelsLog)
        throws URISyntaxException {
        log.debug("REST request to save AuditChannelsLog : {}", auditChannelsLog);
        if (auditChannelsLog.getId() != null) {
            throw new BadRequestAlertException("A new auditChannelsLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuditChannelsLog result = auditChannelsLogRepository.save(auditChannelsLog);
        return ResponseEntity
            .created(new URI("/api/audit-channels-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /audit-channels-logs/:id} : Updates an existing auditChannelsLog.
     *
     * @param id the id of the auditChannelsLog to save.
     * @param auditChannelsLog the auditChannelsLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditChannelsLog,
     * or with status {@code 400 (Bad Request)} if the auditChannelsLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auditChannelsLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    //    @PutMapping("/audit-channels-logs/{id}")
    public ResponseEntity<AuditChannelsLog> updateAuditChannelsLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AuditChannelsLog auditChannelsLog
    ) throws URISyntaxException {
        log.debug("REST request to update AuditChannelsLog : {}, {}", id, auditChannelsLog);
        if (auditChannelsLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, auditChannelsLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!auditChannelsLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AuditChannelsLog result = auditChannelsLogRepository.save(auditChannelsLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, auditChannelsLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /audit-channels-logs/:id} : Partial updates given fields of an existing auditChannelsLog, field will ignore if it is null
     *
     * @param id the id of the auditChannelsLog to save.
     * @param auditChannelsLog the auditChannelsLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditChannelsLog,
     * or with status {@code 400 (Bad Request)} if the auditChannelsLog is not valid,
     * or with status {@code 404 (Not Found)} if the auditChannelsLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the auditChannelsLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    //    @PatchMapping(value = "/audit-channels-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<AuditChannelsLog> partialUpdateAuditChannelsLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AuditChannelsLog auditChannelsLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update AuditChannelsLog partially : {}, {}", id, auditChannelsLog);
        if (auditChannelsLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, auditChannelsLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!auditChannelsLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AuditChannelsLog> result = auditChannelsLogRepository
            .findById(auditChannelsLog.getId())
            .map(
                existingAuditChannelsLog -> {
                    if (auditChannelsLog.getDateLog() != null) {
                        existingAuditChannelsLog.setDateLog(auditChannelsLog.getDateLog());
                    }
                    if (auditChannelsLog.getComment() != null) {
                        existingAuditChannelsLog.setComment(auditChannelsLog.getComment());
                    }
                    if (auditChannelsLog.getContacts() != null) {
                        existingAuditChannelsLog.setContacts(auditChannelsLog.getContacts());
                    }
                    if (auditChannelsLog.getEndPublicDate() != null) {
                        existingAuditChannelsLog.setEndPublicDate(auditChannelsLog.getEndPublicDate());
                    }
                    if (auditChannelsLog.getIdChannel() != null) {
                        existingAuditChannelsLog.setIdChannel(auditChannelsLog.getIdChannel());
                    }
                    if (auditChannelsLog.getIsModerate() != null) {
                        existingAuditChannelsLog.setIsModerate(auditChannelsLog.getIsModerate());
                    }
                    if (auditChannelsLog.getIsPay() != null) {
                        existingAuditChannelsLog.setIsPay(auditChannelsLog.getIsPay());
                    }
                    if (auditChannelsLog.getLastPayDate() != null) {
                        existingAuditChannelsLog.setLastPayDate(auditChannelsLog.getLastPayDate());
                    }
                    if (auditChannelsLog.getLink() != null) {
                        existingAuditChannelsLog.setLink(auditChannelsLog.getLink());
                    }
                    if (auditChannelsLog.getNameChannel() != null) {
                        existingAuditChannelsLog.setNameChannel(auditChannelsLog.getNameChannel());
                    }
                    if (auditChannelsLog.getPriceForPay() != null) {
                        existingAuditChannelsLog.setPriceForPay(auditChannelsLog.getPriceForPay());
                    }
                    if (auditChannelsLog.getStartDate() != null) {
                        existingAuditChannelsLog.setStartDate(auditChannelsLog.getStartDate());
                    }
                    if (auditChannelsLog.getCountSubscribers() != null) {
                        existingAuditChannelsLog.setCountSubscribers(auditChannelsLog.getCountSubscribers());
                    }
                    if (auditChannelsLog.getCountViews() != null) {
                        existingAuditChannelsLog.setCountViews(auditChannelsLog.getCountViews());
                    }
                    if (auditChannelsLog.getOldComment() != null) {
                        existingAuditChannelsLog.setOldComment(auditChannelsLog.getOldComment());
                    }
                    if (auditChannelsLog.getOldContacts() != null) {
                        existingAuditChannelsLog.setOldContacts(auditChannelsLog.getOldContacts());
                    }
                    if (auditChannelsLog.getOldEndPublicDate() != null) {
                        existingAuditChannelsLog.setOldEndPublicDate(auditChannelsLog.getOldEndPublicDate());
                    }
                    if (auditChannelsLog.getOldIsModerate() != null) {
                        existingAuditChannelsLog.setOldIsModerate(auditChannelsLog.getOldIsModerate());
                    }
                    if (auditChannelsLog.getOldIsPay() != null) {
                        existingAuditChannelsLog.setOldIsPay(auditChannelsLog.getOldIsPay());
                    }
                    if (auditChannelsLog.getOldLastPayDate() != null) {
                        existingAuditChannelsLog.setOldLastPayDate(auditChannelsLog.getOldLastPayDate());
                    }
                    if (auditChannelsLog.getOldLink() != null) {
                        existingAuditChannelsLog.setOldLink(auditChannelsLog.getOldLink());
                    }
                    if (auditChannelsLog.getOldNameChannel() != null) {
                        existingAuditChannelsLog.setOldNameChannel(auditChannelsLog.getOldNameChannel());
                    }
                    if (auditChannelsLog.getOldPriceForPay() != null) {
                        existingAuditChannelsLog.setOldPriceForPay(auditChannelsLog.getOldPriceForPay());
                    }
                    if (auditChannelsLog.getOldStartDate() != null) {
                        existingAuditChannelsLog.setOldStartDate(auditChannelsLog.getOldStartDate());
                    }
                    if (auditChannelsLog.getOldCountSubscribers() != null) {
                        existingAuditChannelsLog.setOldCountSubscribers(auditChannelsLog.getOldCountSubscribers());
                    }
                    if (auditChannelsLog.getOldCountViews() != null) {
                        existingAuditChannelsLog.setOldCountViews(auditChannelsLog.getOldCountViews());
                    }

                    return existingAuditChannelsLog;
                }
            )
            .map(auditChannelsLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, auditChannelsLog.getId().toString())
        );
    }

    /**
     * {@code GET  /audit-channels-logs} : get all the auditChannelsLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auditChannelsLogs in body.
     */
    @GetMapping("/audit-channels-logs")
    public List<AuditChannelsLog> getAllAuditChannelsLogs() {
        log.debug("REST request to get all AuditChannelsLogs");
        return auditChannelsLogRepository.findAll();
    }

    /**
     * {@code GET  /audit-channels-logs/:id} : get the "id" auditChannelsLog.
     *
     * @param id the id of the auditChannelsLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auditChannelsLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/audit-channels-logs/{id}")
    public ResponseEntity<AuditChannelsLog> getAuditChannelsLog(@PathVariable Long id) {
        log.debug("REST request to get AuditChannelsLog : {}", id);
        Optional<AuditChannelsLog> auditChannelsLog = auditChannelsLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(auditChannelsLog);
    }

    /**
     * {@code DELETE  /audit-channels-logs/:id} : delete the "id" auditChannelsLog.
     *
     * @param id the id of the auditChannelsLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    //    @DeleteMapping("/audit-channels-logs/{id}")
    public ResponseEntity<Void> deleteAuditChannelsLog(@PathVariable Long id) {
        log.debug("REST request to delete AuditChannelsLog : {}", id);
        auditChannelsLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/audit-channels-logs/by-date/{date}")
    public List<AuditChannelsLog> getAllAuditChannelsLogsByDate(@PathVariable LocalDate date) {
        log.debug("REST request to get all AuditChannelsLogs  by dates");
        return auditChannelsLogRepository.findAllByDateLog(date);
    }
}
