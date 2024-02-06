package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ShowChannelsInCategoryLog;
import com.mycompany.myapp.repository.ShowChannelsInCategoryLogRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ShowChannelsInCategoryLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ShowChannelsInCategoryLogResource {

    private final Logger log = LoggerFactory.getLogger(ShowChannelsInCategoryLogResource.class);

    private static final String ENTITY_NAME = "showChannelsInCategoryLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ShowChannelsInCategoryLogRepository showChannelsInCategoryLogRepository;

    public ShowChannelsInCategoryLogResource(ShowChannelsInCategoryLogRepository showChannelsInCategoryLogRepository) {
        this.showChannelsInCategoryLogRepository = showChannelsInCategoryLogRepository;
    }

    /**
     * {@code POST  /show-channels-in-category-logs} : Create a new showChannelsInCategoryLog.
     *
     * @param showChannelsInCategoryLog the showChannelsInCategoryLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new showChannelsInCategoryLog, or with status {@code 400 (Bad Request)} if the showChannelsInCategoryLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/show-channels-in-category-logs")
    public ResponseEntity<ShowChannelsInCategoryLog> createShowChannelsInCategoryLog(
        @RequestBody ShowChannelsInCategoryLog showChannelsInCategoryLog
    ) throws URISyntaxException {
        log.debug("REST request to save ShowChannelsInCategoryLog : {}", showChannelsInCategoryLog);
        if (showChannelsInCategoryLog.getId() != null) {
            throw new BadRequestAlertException("A new showChannelsInCategoryLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ShowChannelsInCategoryLog result = showChannelsInCategoryLogRepository.save(showChannelsInCategoryLog);
        return ResponseEntity
            .created(new URI("/api/show-channels-in-category-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /show-channels-in-category-logs/:id} : Updates an existing showChannelsInCategoryLog.
     *
     * @param id the id of the showChannelsInCategoryLog to save.
     * @param showChannelsInCategoryLog the showChannelsInCategoryLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated showChannelsInCategoryLog,
     * or with status {@code 400 (Bad Request)} if the showChannelsInCategoryLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the showChannelsInCategoryLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/show-channels-in-category-logs/{id}")
    public ResponseEntity<ShowChannelsInCategoryLog> updateShowChannelsInCategoryLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShowChannelsInCategoryLog showChannelsInCategoryLog
    ) throws URISyntaxException {
        log.debug("REST request to update ShowChannelsInCategoryLog : {}, {}", id, showChannelsInCategoryLog);
        if (showChannelsInCategoryLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, showChannelsInCategoryLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!showChannelsInCategoryLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ShowChannelsInCategoryLog result = showChannelsInCategoryLogRepository.save(showChannelsInCategoryLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, showChannelsInCategoryLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /show-channels-in-category-logs/:id} : Partial updates given fields of an existing showChannelsInCategoryLog, field will ignore if it is null
     *
     * @param id the id of the showChannelsInCategoryLog to save.
     * @param showChannelsInCategoryLog the showChannelsInCategoryLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated showChannelsInCategoryLog,
     * or with status {@code 400 (Bad Request)} if the showChannelsInCategoryLog is not valid,
     * or with status {@code 404 (Not Found)} if the showChannelsInCategoryLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the showChannelsInCategoryLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/show-channels-in-category-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ShowChannelsInCategoryLog> partialUpdateShowChannelsInCategoryLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ShowChannelsInCategoryLog showChannelsInCategoryLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update ShowChannelsInCategoryLog partially : {}, {}", id, showChannelsInCategoryLog);
        if (showChannelsInCategoryLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, showChannelsInCategoryLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!showChannelsInCategoryLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ShowChannelsInCategoryLog> result = showChannelsInCategoryLogRepository
            .findById(showChannelsInCategoryLog.getId())
            .map(
                existingShowChannelsInCategoryLog -> {
                    if (showChannelsInCategoryLog.getIdChannel() != null) {
                        existingShowChannelsInCategoryLog.setIdChannel(showChannelsInCategoryLog.getIdChannel());
                    }
                    if (showChannelsInCategoryLog.getNameChannel() != null) {
                        existingShowChannelsInCategoryLog.setNameChannel(showChannelsInCategoryLog.getNameChannel());
                    }
                    if (showChannelsInCategoryLog.getIdCategory() != null) {
                        existingShowChannelsInCategoryLog.setIdCategory(showChannelsInCategoryLog.getIdCategory());
                    }
                    if (showChannelsInCategoryLog.getNameCategory() != null) {
                        existingShowChannelsInCategoryLog.setNameCategory(showChannelsInCategoryLog.getNameCategory());
                    }
                    if (showChannelsInCategoryLog.getIsShowChannel() != null) {
                        existingShowChannelsInCategoryLog.setIsShowChannel(showChannelsInCategoryLog.getIsShowChannel());
                    }
                    if (showChannelsInCategoryLog.getScoreChannel() != null) {
                        existingShowChannelsInCategoryLog.setScoreChannel(showChannelsInCategoryLog.getScoreChannel());
                    }
                    if (showChannelsInCategoryLog.getComment() != null) {
                        existingShowChannelsInCategoryLog.setComment(showChannelsInCategoryLog.getComment());
                    }
                    if (showChannelsInCategoryLog.getOldIsShowChannel() != null) {
                        existingShowChannelsInCategoryLog.setOldIsShowChannel(showChannelsInCategoryLog.getOldIsShowChannel());
                    }
                    if (showChannelsInCategoryLog.getOldScoreChannel() != null) {
                        existingShowChannelsInCategoryLog.setOldScoreChannel(showChannelsInCategoryLog.getOldScoreChannel());
                    }
                    if (showChannelsInCategoryLog.getOldComment() != null) {
                        existingShowChannelsInCategoryLog.setOldComment(showChannelsInCategoryLog.getOldComment());
                    }
                    if (showChannelsInCategoryLog.getDateLog() != null) {
                        existingShowChannelsInCategoryLog.setDateLog(showChannelsInCategoryLog.getDateLog());
                    }

                    return existingShowChannelsInCategoryLog;
                }
            )
            .map(showChannelsInCategoryLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, showChannelsInCategoryLog.getId().toString())
        );
    }

    /**
     * {@code GET  /show-channels-in-category-logs} : get all the showChannelsInCategoryLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of showChannelsInCategoryLogs in body.
     */
    @GetMapping("/show-channels-in-category-logs")
    public List<ShowChannelsInCategoryLog> getAllShowChannelsInCategoryLogs() {
        log.debug("REST request to get all ShowChannelsInCategoryLogs");
        return showChannelsInCategoryLogRepository.findAll();
    }

    /**
     * {@code GET  /show-channels-in-category-logs/:id} : get the "id" showChannelsInCategoryLog.
     *
     * @param id the id of the showChannelsInCategoryLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the showChannelsInCategoryLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/show-channels-in-category-logs/{id}")
    public ResponseEntity<ShowChannelsInCategoryLog> getShowChannelsInCategoryLog(@PathVariable Long id) {
        log.debug("REST request to get ShowChannelsInCategoryLog : {}", id);
        Optional<ShowChannelsInCategoryLog> showChannelsInCategoryLog = showChannelsInCategoryLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(showChannelsInCategoryLog);
    }

    /**
     * {@code DELETE  /show-channels-in-category-logs/:id} : delete the "id" showChannelsInCategoryLog.
     *
     * @param id the id of the showChannelsInCategoryLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/show-channels-in-category-logs/{id}")
    public ResponseEntity<Void> deleteShowChannelsInCategoryLog(@PathVariable Long id) {
        log.debug("REST request to delete ShowChannelsInCategoryLog : {}", id);
        showChannelsInCategoryLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
