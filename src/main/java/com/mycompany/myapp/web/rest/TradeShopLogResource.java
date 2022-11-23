package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TradeShopLog;
import com.mycompany.myapp.repository.TradeShopLogRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TradeShopLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TradeShopLogResource {

    private final Logger log = LoggerFactory.getLogger(TradeShopLogResource.class);

    private static final String ENTITY_NAME = "tradeShopLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TradeShopLogRepository tradeShopLogRepository;

    public TradeShopLogResource(TradeShopLogRepository tradeShopLogRepository) {
        this.tradeShopLogRepository = tradeShopLogRepository;
    }

    /**
     * {@code POST  /trade-shop-logs} : Create a new tradeShopLog.
     *
     * @param tradeShopLog the tradeShopLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tradeShopLog, or with status {@code 400 (Bad Request)} if the tradeShopLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trade-shop-logs")
    public ResponseEntity<TradeShopLog> createTradeShopLog(@RequestBody TradeShopLog tradeShopLog) throws URISyntaxException {
        log.debug("REST request to save TradeShopLog : {}", tradeShopLog);
        if (tradeShopLog.getId() != null) {
            throw new BadRequestAlertException("A new tradeShopLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TradeShopLog result = tradeShopLogRepository.save(tradeShopLog);
        return ResponseEntity
            .created(new URI("/api/trade-shop-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trade-shop-logs/:id} : Updates an existing tradeShopLog.
     *
     * @param id the id of the tradeShopLog to save.
     * @param tradeShopLog the tradeShopLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeShopLog,
     * or with status {@code 400 (Bad Request)} if the tradeShopLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tradeShopLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trade-shop-logs/{id}")
    public ResponseEntity<TradeShopLog> updateTradeShopLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeShopLog tradeShopLog
    ) throws URISyntaxException {
        log.debug("REST request to update TradeShopLog : {}, {}", id, tradeShopLog);
        if (tradeShopLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeShopLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tradeShopLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TradeShopLog result = tradeShopLogRepository.save(tradeShopLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tradeShopLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /trade-shop-logs/:id} : Partial updates given fields of an existing tradeShopLog, field will ignore if it is null
     *
     * @param id the id of the tradeShopLog to save.
     * @param tradeShopLog the tradeShopLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeShopLog,
     * or with status {@code 400 (Bad Request)} if the tradeShopLog is not valid,
     * or with status {@code 404 (Not Found)} if the tradeShopLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the tradeShopLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/trade-shop-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TradeShopLog> partialUpdateTradeShopLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeShopLog tradeShopLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update TradeShopLog partially : {}, {}", id, tradeShopLog);
        if (tradeShopLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeShopLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tradeShopLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TradeShopLog> result = tradeShopLogRepository
            .findById(tradeShopLog.getId())
            .map(
                existingTradeShopLog -> {
                    if (tradeShopLog.getCategory() != null) {
                        existingTradeShopLog.setCategory(tradeShopLog.getCategory());
                    }
                    if (tradeShopLog.getPriceDiapozon() != null) {
                        existingTradeShopLog.setPriceDiapozon(tradeShopLog.getPriceDiapozon());
                    }
                    if (tradeShopLog.getCurrentPrice() != null) {
                        existingTradeShopLog.setCurrentPrice(tradeShopLog.getCurrentPrice());
                    }
                    if (tradeShopLog.getWhiceLineFromAllCountLines() != null) {
                        existingTradeShopLog.setWhiceLineFromAllCountLines(tradeShopLog.getWhiceLineFromAllCountLines());
                    }
                    if (tradeShopLog.getTgUserIdWinner() != null) {
                        existingTradeShopLog.setTgUserIdWinner(tradeShopLog.getTgUserIdWinner());
                    }
                    if (tradeShopLog.getInWhatDateWillPostThisLinks() != null) {
                        existingTradeShopLog.setInWhatDateWillPostThisLinks(tradeShopLog.getInWhatDateWillPostThisLinks());
                    }
                    if (tradeShopLog.getDateFinishTorgs() != null) {
                        existingTradeShopLog.setDateFinishTorgs(tradeShopLog.getDateFinishTorgs());
                    }
                    if (tradeShopLog.getIsDelete() != null) {
                        existingTradeShopLog.setIsDelete(tradeShopLog.getIsDelete());
                    }
                    if (tradeShopLog.getDate1() != null) {
                        existingTradeShopLog.setDate1(tradeShopLog.getDate1());
                    }
                    if (tradeShopLog.getDate2() != null) {
                        existingTradeShopLog.setDate2(tradeShopLog.getDate2());
                    }
                    if (tradeShopLog.getLong1() != null) {
                        existingTradeShopLog.setLong1(tradeShopLog.getLong1());
                    }
                    if (tradeShopLog.getString1() != null) {
                        existingTradeShopLog.setString1(tradeShopLog.getString1());
                    }
                    if (tradeShopLog.getBoolean1() != null) {
                        existingTradeShopLog.setBoolean1(tradeShopLog.getBoolean1());
                    }

                    return existingTradeShopLog;
                }
            )
            .map(tradeShopLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tradeShopLog.getId().toString())
        );
    }

    /**
     * {@code GET  /trade-shop-logs} : get all the tradeShopLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tradeShopLogs in body.
     */
    @GetMapping("/trade-shop-logs")
    public List<TradeShopLog> getAllTradeShopLogs() {
        log.debug("REST request to get all TradeShopLogs");
        return tradeShopLogRepository.findAll();
    }

    /**
     * {@code GET  /trade-shop-logs/:id} : get the "id" tradeShopLog.
     *
     * @param id the id of the tradeShopLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tradeShopLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trade-shop-logs/{id}")
    public ResponseEntity<TradeShopLog> getTradeShopLog(@PathVariable Long id) {
        log.debug("REST request to get TradeShopLog : {}", id);
        Optional<TradeShopLog> tradeShopLog = tradeShopLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tradeShopLog);
    }

    /**
     * {@code DELETE  /trade-shop-logs/:id} : delete the "id" tradeShopLog.
     *
     * @param id the id of the tradeShopLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trade-shop-logs/{id}")
    public ResponseEntity<Void> deleteTradeShopLog(@PathVariable Long id) {
        log.debug("REST request to delete TradeShopLog : {}", id);
        tradeShopLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
