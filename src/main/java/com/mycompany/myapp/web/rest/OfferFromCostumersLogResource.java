package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.OfferFromCostumersLog;
import com.mycompany.myapp.repository.OfferFromCostumersLogRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.OfferFromCostumersLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OfferFromCostumersLogResource {

    private final Logger log = LoggerFactory.getLogger(OfferFromCostumersLogResource.class);

    private static final String ENTITY_NAME = "offerFromCostumersLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferFromCostumersLogRepository offerFromCostumersLogRepository;

    public OfferFromCostumersLogResource(OfferFromCostumersLogRepository offerFromCostumersLogRepository) {
        this.offerFromCostumersLogRepository = offerFromCostumersLogRepository;
    }

    /**
     * {@code POST  /offer-from-costumers-logs} : Create a new offerFromCostumersLog.
     *
     * @param offerFromCostumersLog the offerFromCostumersLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerFromCostumersLog, or with status {@code 400 (Bad Request)} if the offerFromCostumersLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-from-costumers-logs")
    public ResponseEntity<OfferFromCostumersLog> createOfferFromCostumersLog(@RequestBody OfferFromCostumersLog offerFromCostumersLog)
        throws URISyntaxException {
        log.debug("REST request to save OfferFromCostumersLog : {}", offerFromCostumersLog);
        if (offerFromCostumersLog.getId() != null) {
            throw new BadRequestAlertException("A new offerFromCostumersLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferFromCostumersLog result = offerFromCostumersLogRepository.save(offerFromCostumersLog);
        return ResponseEntity
            .created(new URI("/api/offer-from-costumers-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-from-costumers-logs/:id} : Updates an existing offerFromCostumersLog.
     *
     * @param id the id of the offerFromCostumersLog to save.
     * @param offerFromCostumersLog the offerFromCostumersLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerFromCostumersLog,
     * or with status {@code 400 (Bad Request)} if the offerFromCostumersLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerFromCostumersLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-from-costumers-logs/{id}")
    public ResponseEntity<OfferFromCostumersLog> updateOfferFromCostumersLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OfferFromCostumersLog offerFromCostumersLog
    ) throws URISyntaxException {
        log.debug("REST request to update OfferFromCostumersLog : {}, {}", id, offerFromCostumersLog);
        if (offerFromCostumersLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, offerFromCostumersLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!offerFromCostumersLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OfferFromCostumersLog result = offerFromCostumersLogRepository.save(offerFromCostumersLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, offerFromCostumersLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /offer-from-costumers-logs/:id} : Partial updates given fields of an existing offerFromCostumersLog, field will ignore if it is null
     *
     * @param id the id of the offerFromCostumersLog to save.
     * @param offerFromCostumersLog the offerFromCostumersLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerFromCostumersLog,
     * or with status {@code 400 (Bad Request)} if the offerFromCostumersLog is not valid,
     * or with status {@code 404 (Not Found)} if the offerFromCostumersLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the offerFromCostumersLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/offer-from-costumers-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<OfferFromCostumersLog> partialUpdateOfferFromCostumersLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OfferFromCostumersLog offerFromCostumersLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update OfferFromCostumersLog partially : {}, {}", id, offerFromCostumersLog);
        if (offerFromCostumersLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, offerFromCostumersLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!offerFromCostumersLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OfferFromCostumersLog> result = offerFromCostumersLogRepository
            .findById(offerFromCostumersLog.getId())
            .map(
                existingOfferFromCostumersLog -> {
                    if (offerFromCostumersLog.getText() != null) {
                        existingOfferFromCostumersLog.setText(offerFromCostumersLog.getText());
                    }
                    if (offerFromCostumersLog.getIsDelete() != null) {
                        existingOfferFromCostumersLog.setIsDelete(offerFromCostumersLog.getIsDelete());
                    }
                    if (offerFromCostumersLog.getAdminId() != null) {
                        existingOfferFromCostumersLog.setAdminId(offerFromCostumersLog.getAdminId());
                    }
                    if (offerFromCostumersLog.getIsActive() != null) {
                        existingOfferFromCostumersLog.setIsActive(offerFromCostumersLog.getIsActive());
                    }
                    if (offerFromCostumersLog.getDate1() != null) {
                        existingOfferFromCostumersLog.setDate1(offerFromCostumersLog.getDate1());
                    }
                    if (offerFromCostumersLog.getDate2() != null) {
                        existingOfferFromCostumersLog.setDate2(offerFromCostumersLog.getDate2());
                    }
                    if (offerFromCostumersLog.getLong1() != null) {
                        existingOfferFromCostumersLog.setLong1(offerFromCostumersLog.getLong1());
                    }
                    if (offerFromCostumersLog.getString1() != null) {
                        existingOfferFromCostumersLog.setString1(offerFromCostumersLog.getString1());
                    }
                    if (offerFromCostumersLog.getBoolean1() != null) {
                        existingOfferFromCostumersLog.setBoolean1(offerFromCostumersLog.getBoolean1());
                    }

                    return existingOfferFromCostumersLog;
                }
            )
            .map(offerFromCostumersLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, offerFromCostumersLog.getId().toString())
        );
    }

    /**
     * {@code GET  /offer-from-costumers-logs} : get all the offerFromCostumersLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerFromCostumersLogs in body.
     */
    @GetMapping("/offer-from-costumers-logs")
    public List<OfferFromCostumersLog> getAllOfferFromCostumersLogs() {
        log.debug("REST request to get all OfferFromCostumersLogs");
        return offerFromCostumersLogRepository.findAll();
    }

    /**
     * {@code GET  /offer-from-costumers-logs/:id} : get the "id" offerFromCostumersLog.
     *
     * @param id the id of the offerFromCostumersLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerFromCostumersLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-from-costumers-logs/{id}")
    public ResponseEntity<OfferFromCostumersLog> getOfferFromCostumersLog(@PathVariable Long id) {
        log.debug("REST request to get OfferFromCostumersLog : {}", id);
        Optional<OfferFromCostumersLog> offerFromCostumersLog = offerFromCostumersLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(offerFromCostumersLog);
    }

    /**
     * {@code DELETE  /offer-from-costumers-logs/:id} : delete the "id" offerFromCostumersLog.
     *
     * @param id the id of the offerFromCostumersLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-from-costumers-logs/{id}")
    public ResponseEntity<Void> deleteOfferFromCostumersLog(@PathVariable Long id) {
        log.debug("REST request to delete OfferFromCostumersLog : {}", id);
        offerFromCostumersLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
