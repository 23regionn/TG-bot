package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.CountChannelClickPageLog;
import com.mycompany.myapp.repository.CountChannelClickPageLogRepository;
import com.mycompany.myapp.service.CountChannelClickPageLogService;
import com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO;
import com.mycompany.myapp.service.dto.statistics.count_channel_click.CountChannelClickPageByDatesDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.CountChannelClickPageLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CountChannelClickPageLogResource {

    private final Logger log = LoggerFactory.getLogger(CountChannelClickPageLogResource.class);

    private static final String ENTITY_NAME = "countChannelClickPageLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CountChannelClickPageLogRepository countChannelClickPageLogRepository;
    private final CountChannelClickPageLogService countChannelClickPageLogService;

    public CountChannelClickPageLogResource(
        CountChannelClickPageLogRepository countChannelClickPageLogRepository,
        CountChannelClickPageLogService countChannelClickPageLogService
    ) {
        this.countChannelClickPageLogRepository = countChannelClickPageLogRepository;
        this.countChannelClickPageLogService = countChannelClickPageLogService;
    }

    /**
     * {@code POST  /count-channel-click-page-logs} : Create a new countChannelClickPageLog.
     *
     * @param countChannelClickPageLog the countChannelClickPageLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new countChannelClickPageLog, or with status {@code 400 (Bad Request)} if the countChannelClickPageLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/count-channel-click-page-logs")
    public ResponseEntity<CountChannelClickPageLog> createCountChannelClickPageLog(
        @RequestBody CountChannelClickPageLog countChannelClickPageLog
    ) throws URISyntaxException {
        log.debug("REST request to save CountChannelClickPageLog : {}", countChannelClickPageLog);
        if (countChannelClickPageLog.getId() != null) {
            throw new BadRequestAlertException("A new countChannelClickPageLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CountChannelClickPageLog result = countChannelClickPageLogRepository.save(countChannelClickPageLog);
        return ResponseEntity
            .created(new URI("/api/count-channel-click-page-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /count-channel-click-page-logs/:id} : Updates an existing countChannelClickPageLog.
     *
     * @param id the id of the countChannelClickPageLog to save.
     * @param countChannelClickPageLog the countChannelClickPageLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated countChannelClickPageLog,
     * or with status {@code 400 (Bad Request)} if the countChannelClickPageLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the countChannelClickPageLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/count-channel-click-page-logs/{id}")
    public ResponseEntity<CountChannelClickPageLog> updateCountChannelClickPageLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CountChannelClickPageLog countChannelClickPageLog
    ) throws URISyntaxException {
        log.debug("REST request to update CountChannelClickPageLog : {}, {}", id, countChannelClickPageLog);
        if (countChannelClickPageLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, countChannelClickPageLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!countChannelClickPageLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CountChannelClickPageLog result = countChannelClickPageLogRepository.save(countChannelClickPageLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, countChannelClickPageLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /count-channel-click-page-logs/:id} : Partial updates given fields of an existing countChannelClickPageLog, field will ignore if it is null
     *
     * @param id the id of the countChannelClickPageLog to save.
     * @param countChannelClickPageLog the countChannelClickPageLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated countChannelClickPageLog,
     * or with status {@code 400 (Bad Request)} if the countChannelClickPageLog is not valid,
     * or with status {@code 404 (Not Found)} if the countChannelClickPageLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the countChannelClickPageLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/count-channel-click-page-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<CountChannelClickPageLog> partialUpdateCountChannelClickPageLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CountChannelClickPageLog countChannelClickPageLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update CountChannelClickPageLog partially : {}, {}", id, countChannelClickPageLog);
        if (countChannelClickPageLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, countChannelClickPageLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!countChannelClickPageLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CountChannelClickPageLog> result = countChannelClickPageLogRepository
            .findById(countChannelClickPageLog.getId())
            .map(
                existingCountChannelClickPageLog -> {
                    if (countChannelClickPageLog.getChatId() != null) {
                        existingCountChannelClickPageLog.setChatId(countChannelClickPageLog.getChatId());
                    }
                    if (countChannelClickPageLog.getDateLog() != null) {
                        existingCountChannelClickPageLog.setDateLog(countChannelClickPageLog.getDateLog());
                    }
                    if (countChannelClickPageLog.getIdChannel() != null) {
                        existingCountChannelClickPageLog.setIdChannel(countChannelClickPageLog.getIdChannel());
                    }
                    if (countChannelClickPageLog.getPageNumber() != null) {
                        existingCountChannelClickPageLog.setPageNumber(countChannelClickPageLog.getPageNumber());
                    }
                    if (countChannelClickPageLog.getIdCategory() != null) {
                        existingCountChannelClickPageLog.setIdCategory(countChannelClickPageLog.getIdCategory());
                    }
                    if (countChannelClickPageLog.getIdCity() != null) {
                        existingCountChannelClickPageLog.setIdCity(countChannelClickPageLog.getIdCity());
                    }

                    return existingCountChannelClickPageLog;
                }
            )
            .map(countChannelClickPageLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, countChannelClickPageLog.getId().toString())
        );
    }

    /**
     * {@code GET  /count-channel-click-page-logs} : get all the countChannelClickPageLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of countChannelClickPageLogs in body.
     */
    @GetMapping("/count-channel-click-page-logs")
    public List<CountChannelClickPageLog> getAllCountChannelClickPageLogs() {
        log.debug("REST request to get all CountChannelClickPageLogs");
        return countChannelClickPageLogRepository.findAll();
    }

    /**
     * {@code GET  /count-channel-click-page-logs/:id} : get the "id" countChannelClickPageLog.
     *
     * @param id the id of the countChannelClickPageLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the countChannelClickPageLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/count-channel-click-page-logs/{id}")
    public ResponseEntity<CountChannelClickPageLog> getCountChannelClickPageLog(@PathVariable Long id) {
        log.debug("REST request to get CountChannelClickPageLog : {}", id);
        Optional<CountChannelClickPageLog> countChannelClickPageLog = countChannelClickPageLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(countChannelClickPageLog);
    }

    /**
     * {@code DELETE  /count-channel-click-page-logs/:id} : delete the "id" countChannelClickPageLog.
     *
     * @param id the id of the countChannelClickPageLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/count-channel-click-page-logs/{id}")
    public ResponseEntity<Void> deleteCountChannelClickPageLog(@PathVariable Long id) {
        log.debug("REST request to delete CountChannelClickPageLog : {}", id);
        countChannelClickPageLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/count-channel-click-page-logs/detail/page-number/by-dates")
    public ResponseEntity<List<StatisticsByPageNumberCountDTO>> getStatisticsChannelPagesLogByDates(
        @RequestBody CountChannelClickPageByDatesDTO request
    ) {
        log.debug("REST request to get count /count-channel-click-page-logs/detail/page-number/by-dates ");
        return ResponseEntity.ok(countChannelClickPageLogService.getStatisticsChannelPagesLogByDates(request));
    }

    @PostMapping("/count-channel-click-page-logs/detail/for-city/page-number/by-dates")
    public ResponseEntity<List<StatisticsByPageNumberCountDTO>> getStatisticsChannelPagesLogByDatesForCity(
        @RequestBody CountChannelClickPageByDatesDTO request
    ) {
        log.debug("REST request to get count /count-channel-click-page-logs/detail/for-city/page-number/by-dates ");
        return ResponseEntity.ok(countChannelClickPageLogService.getStatisticsChannelPagesLogByDatesForCity(request));
    }
}
