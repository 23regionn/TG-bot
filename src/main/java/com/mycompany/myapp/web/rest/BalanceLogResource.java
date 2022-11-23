package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.BalanceLog;
import com.mycompany.myapp.repository.BalanceLogRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.BalanceLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BalanceLogResource {

    private final Logger log = LoggerFactory.getLogger(BalanceLogResource.class);

    private static final String ENTITY_NAME = "balanceLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BalanceLogRepository balanceLogRepository;

    public BalanceLogResource(BalanceLogRepository balanceLogRepository) {
        this.balanceLogRepository = balanceLogRepository;
    }

    /**
     * {@code POST  /balance-logs} : Create a new balanceLog.
     *
     * @param balanceLog the balanceLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new balanceLog, or with status {@code 400 (Bad Request)} if the balanceLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/balance-logs")
    public ResponseEntity<BalanceLog> createBalanceLog(@RequestBody BalanceLog balanceLog) throws URISyntaxException {
        log.debug("REST request to save BalanceLog : {}", balanceLog);
        if (balanceLog.getId() != null) {
            throw new BadRequestAlertException("A new balanceLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BalanceLog result = balanceLogRepository.save(balanceLog);
        return ResponseEntity
            .created(new URI("/api/balance-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /balance-logs/:id} : Updates an existing balanceLog.
     *
     * @param id the id of the balanceLog to save.
     * @param balanceLog the balanceLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated balanceLog,
     * or with status {@code 400 (Bad Request)} if the balanceLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the balanceLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/balance-logs/{id}")
    public ResponseEntity<BalanceLog> updateBalanceLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BalanceLog balanceLog
    ) throws URISyntaxException {
        log.debug("REST request to update BalanceLog : {}, {}", id, balanceLog);
        if (balanceLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, balanceLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!balanceLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        BalanceLog result = balanceLogRepository.save(balanceLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, balanceLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /balance-logs/:id} : Partial updates given fields of an existing balanceLog, field will ignore if it is null
     *
     * @param id the id of the balanceLog to save.
     * @param balanceLog the balanceLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated balanceLog,
     * or with status {@code 400 (Bad Request)} if the balanceLog is not valid,
     * or with status {@code 404 (Not Found)} if the balanceLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the balanceLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/balance-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BalanceLog> partialUpdateBalanceLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BalanceLog balanceLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update BalanceLog partially : {}, {}", id, balanceLog);
        if (balanceLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, balanceLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!balanceLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BalanceLog> result = balanceLogRepository
            .findById(balanceLog.getId())
            .map(
                existingBalanceLog -> {
                    if (balanceLog.getBalace() != null) {
                        existingBalanceLog.setBalace(balanceLog.getBalace());
                    }
                    if (balanceLog.getUserId() != null) {
                        existingBalanceLog.setUserId(balanceLog.getUserId());
                    }
                    if (balanceLog.getFrostSum() != null) {
                        existingBalanceLog.setFrostSum(balanceLog.getFrostSum());
                    }
                    if (balanceLog.getDateLastAddBalance() != null) {
                        existingBalanceLog.setDateLastAddBalance(balanceLog.getDateLastAddBalance());
                    }
                    if (balanceLog.getDateLastMinusFromBalance() != null) {
                        existingBalanceLog.setDateLastMinusFromBalance(balanceLog.getDateLastMinusFromBalance());
                    }
                    if (balanceLog.getDate1() != null) {
                        existingBalanceLog.setDate1(balanceLog.getDate1());
                    }
                    if (balanceLog.getDate2() != null) {
                        existingBalanceLog.setDate2(balanceLog.getDate2());
                    }
                    if (balanceLog.getLong1() != null) {
                        existingBalanceLog.setLong1(balanceLog.getLong1());
                    }
                    if (balanceLog.getString1() != null) {
                        existingBalanceLog.setString1(balanceLog.getString1());
                    }
                    if (balanceLog.getBoolean1() != null) {
                        existingBalanceLog.setBoolean1(balanceLog.getBoolean1());
                    }

                    return existingBalanceLog;
                }
            )
            .map(balanceLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, balanceLog.getId().toString())
        );
    }

    /**
     * {@code GET  /balance-logs} : get all the balanceLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of balanceLogs in body.
     */
    @GetMapping("/balance-logs")
    public List<BalanceLog> getAllBalanceLogs() {
        log.debug("REST request to get all BalanceLogs");
        return balanceLogRepository.findAll();
    }

    /**
     * {@code GET  /balance-logs/:id} : get the "id" balanceLog.
     *
     * @param id the id of the balanceLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the balanceLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/balance-logs/{id}")
    public ResponseEntity<BalanceLog> getBalanceLog(@PathVariable Long id) {
        log.debug("REST request to get BalanceLog : {}", id);
        Optional<BalanceLog> balanceLog = balanceLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(balanceLog);
    }

    /**
     * {@code DELETE  /balance-logs/:id} : delete the "id" balanceLog.
     *
     * @param id the id of the balanceLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/balance-logs/{id}")
    public ResponseEntity<Void> deleteBalanceLog(@PathVariable Long id) {
        log.debug("REST request to delete BalanceLog : {}", id);
        balanceLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
