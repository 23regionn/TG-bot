package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ShowChannelsInCategoryLog;
import com.mycompany.myapp.domain.ShowChannelsInCityLog;
import com.mycompany.myapp.repository.ShowChannelsInCityLogRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ShowChannelsInCityLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ShowChannelsInCityLogResource {

    private final Logger log = LoggerFactory.getLogger(ShowChannelsInCityLogResource.class);

    private static final String ENTITY_NAME = "showChannelsInCityLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShowChannelsInCityLogRepository showChannelsInCityLogRepository;

    public ShowChannelsInCityLogResource(ShowChannelsInCityLogRepository showChannelsInCityLogRepository) {
        this.showChannelsInCityLogRepository = showChannelsInCityLogRepository;
    }

    /**
     * {@code POST  /show-channels-in-city-logs} : Create a new showChannelsInCityLog.
     *
     * @param showChannelsInCityLog the showChannelsInCityLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new showChannelsInCityLog, or with status {@code 400 (Bad Request)} if the showChannelsInCityLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    //    @PostMapping("/show-channels-in-city-logs")
    public ResponseEntity<ShowChannelsInCityLog> createShowChannelsInCityLog(@RequestBody ShowChannelsInCityLog showChannelsInCityLog)
        throws URISyntaxException {
        log.debug("REST request to save ShowChannelsInCityLog : {}", showChannelsInCityLog);
        if (showChannelsInCityLog.getId() != null) {
            throw new BadRequestAlertException("A new showChannelsInCityLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShowChannelsInCityLog result = showChannelsInCityLogRepository.save(showChannelsInCityLog);
        return ResponseEntity
            .created(new URI("/api/show-channels-in-city-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /show-channels-in-city-logs/:id} : Updates an existing showChannelsInCityLog.
     *
     * @param id the id of the showChannelsInCityLog to save.
     * @param showChannelsInCityLog the showChannelsInCityLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated showChannelsInCityLog,
     * or with status {@code 400 (Bad Request)} if the showChannelsInCityLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the showChannelsInCityLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    //    @PutMapping("/show-channels-in-city-logs/{id}")
    public ResponseEntity<ShowChannelsInCityLog> updateShowChannelsInCityLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShowChannelsInCityLog showChannelsInCityLog
    ) throws URISyntaxException {
        log.debug("REST request to update ShowChannelsInCityLog : {}, {}", id, showChannelsInCityLog);
        if (showChannelsInCityLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, showChannelsInCityLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!showChannelsInCityLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ShowChannelsInCityLog result = showChannelsInCityLogRepository.save(showChannelsInCityLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, showChannelsInCityLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /show-channels-in-city-logs/:id} : Partial updates given fields of an existing showChannelsInCityLog, field will ignore if it is null
     *
     * @param id the id of the showChannelsInCityLog to save.
     * @param showChannelsInCityLog the showChannelsInCityLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated showChannelsInCityLog,
     * or with status {@code 400 (Bad Request)} if the showChannelsInCityLog is not valid,
     * or with status {@code 404 (Not Found)} if the showChannelsInCityLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the showChannelsInCityLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    //    @PatchMapping(value = "/show-channels-in-city-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ShowChannelsInCityLog> partialUpdateShowChannelsInCityLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShowChannelsInCityLog showChannelsInCityLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update ShowChannelsInCityLog partially : {}, {}", id, showChannelsInCityLog);
        if (showChannelsInCityLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, showChannelsInCityLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!showChannelsInCityLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ShowChannelsInCityLog> result = showChannelsInCityLogRepository
            .findById(showChannelsInCityLog.getId())
            .map(
                existingShowChannelsInCityLog -> {
                    if (showChannelsInCityLog.getIdChannel() != null) {
                        existingShowChannelsInCityLog.setIdChannel(showChannelsInCityLog.getIdChannel());
                    }
                    if (showChannelsInCityLog.getNameChannel() != null) {
                        existingShowChannelsInCityLog.setNameChannel(showChannelsInCityLog.getNameChannel());
                    }
                    if (showChannelsInCityLog.getIdCategory() != null) {
                        existingShowChannelsInCityLog.setIdCategory(showChannelsInCityLog.getIdCategory());
                    }
                    if (showChannelsInCityLog.getNameCategory() != null) {
                        existingShowChannelsInCityLog.setNameCategory(showChannelsInCityLog.getNameCategory());
                    }
                    if (showChannelsInCityLog.getIdCity() != null) {
                        existingShowChannelsInCityLog.setIdCity(showChannelsInCityLog.getIdCity());
                    }
                    if (showChannelsInCityLog.getNameCity() != null) {
                        existingShowChannelsInCityLog.setNameCity(showChannelsInCityLog.getNameCity());
                    }
                    if (showChannelsInCityLog.getIsShowChannel() != null) {
                        existingShowChannelsInCityLog.setIsShowChannel(showChannelsInCityLog.getIsShowChannel());
                    }
                    if (showChannelsInCityLog.getScoreChannel() != null) {
                        existingShowChannelsInCityLog.setScoreChannel(showChannelsInCityLog.getScoreChannel());
                    }
                    if (showChannelsInCityLog.getComment() != null) {
                        existingShowChannelsInCityLog.setComment(showChannelsInCityLog.getComment());
                    }
                    if (showChannelsInCityLog.getOldIsShowChannel() != null) {
                        existingShowChannelsInCityLog.setOldIsShowChannel(showChannelsInCityLog.getOldIsShowChannel());
                    }
                    if (showChannelsInCityLog.getOldScoreChannel() != null) {
                        existingShowChannelsInCityLog.setOldScoreChannel(showChannelsInCityLog.getOldScoreChannel());
                    }
                    if (showChannelsInCityLog.getOldComment() != null) {
                        existingShowChannelsInCityLog.setOldComment(showChannelsInCityLog.getOldComment());
                    }
                    if (showChannelsInCityLog.getDateLog() != null) {
                        existingShowChannelsInCityLog.setDateLog(showChannelsInCityLog.getDateLog());
                    }

                    return existingShowChannelsInCityLog;
                }
            )
            .map(showChannelsInCityLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, showChannelsInCityLog.getId().toString())
        );
    }

    /**
     * {@code GET  /show-channels-in-city-logs} : get all the showChannelsInCityLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of showChannelsInCityLogs in body.
     */
    @GetMapping("/show-channels-in-city-logs")
    public List<ShowChannelsInCityLog> getAllShowChannelsInCityLogs() {
        log.debug("REST request to get all ShowChannelsInCityLogs");
        return showChannelsInCityLogRepository.findAll();
    }

    /**
     * {@code GET  /show-channels-in-city-logs/:id} : get the "id" showChannelsInCityLog.
     *
     * @param id the id of the showChannelsInCityLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the showChannelsInCityLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/show-channels-in-city-logs/{id}")
    public ResponseEntity<ShowChannelsInCityLog> getShowChannelsInCityLog(@PathVariable Long id) {
        log.debug("REST request to get ShowChannelsInCityLog : {}", id);
        Optional<ShowChannelsInCityLog> showChannelsInCityLog = showChannelsInCityLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(showChannelsInCityLog);
    }

    /**
     * {@code DELETE  /show-channels-in-city-logs/:id} : delete the "id" showChannelsInCityLog.
     *
     * @param id the id of the showChannelsInCityLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    //    @DeleteMapping("/show-channels-in-city-logs/{id}")
    public ResponseEntity<Void> deleteShowChannelsInCityLog(@PathVariable Long id) {
        log.debug("REST request to delete ShowChannelsInCityLog : {}", id);
        showChannelsInCityLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/show-channels-in-city-logs/by-date/{date}")
    public List<ShowChannelsInCityLog> getAllShowChannelsInCityLogsByDate(@PathVariable LocalDate date) {
        log.debug("REST request to get all ShowChannelsInCategoryLogs by dates");
        return showChannelsInCityLogRepository.findAllByDateLog(date);
    }
}
