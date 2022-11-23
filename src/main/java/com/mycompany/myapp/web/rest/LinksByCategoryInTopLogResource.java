package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.LinksByCategoryInTopLog;
import com.mycompany.myapp.repository.LinksByCategoryInTopLogRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.LinksByCategoryInTopLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LinksByCategoryInTopLogResource {

    private final Logger log = LoggerFactory.getLogger(LinksByCategoryInTopLogResource.class);

    private static final String ENTITY_NAME = "linksByCategoryInTopLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LinksByCategoryInTopLogRepository linksByCategoryInTopLogRepository;

    public LinksByCategoryInTopLogResource(LinksByCategoryInTopLogRepository linksByCategoryInTopLogRepository) {
        this.linksByCategoryInTopLogRepository = linksByCategoryInTopLogRepository;
    }

    /**
     * {@code POST  /links-by-category-in-top-logs} : Create a new linksByCategoryInTopLog.
     *
     * @param linksByCategoryInTopLog the linksByCategoryInTopLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new linksByCategoryInTopLog, or with status {@code 400 (Bad Request)} if the linksByCategoryInTopLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/links-by-category-in-top-logs")
    public ResponseEntity<LinksByCategoryInTopLog> createLinksByCategoryInTopLog(
        @RequestBody LinksByCategoryInTopLog linksByCategoryInTopLog
    ) throws URISyntaxException {
        log.debug("REST request to save LinksByCategoryInTopLog : {}", linksByCategoryInTopLog);
        if (linksByCategoryInTopLog.getId() != null) {
            throw new BadRequestAlertException("A new linksByCategoryInTopLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LinksByCategoryInTopLog result = linksByCategoryInTopLogRepository.save(linksByCategoryInTopLog);
        return ResponseEntity
            .created(new URI("/api/links-by-category-in-top-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /links-by-category-in-top-logs/:id} : Updates an existing linksByCategoryInTopLog.
     *
     * @param id the id of the linksByCategoryInTopLog to save.
     * @param linksByCategoryInTopLog the linksByCategoryInTopLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated linksByCategoryInTopLog,
     * or with status {@code 400 (Bad Request)} if the linksByCategoryInTopLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the linksByCategoryInTopLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/links-by-category-in-top-logs/{id}")
    public ResponseEntity<LinksByCategoryInTopLog> updateLinksByCategoryInTopLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LinksByCategoryInTopLog linksByCategoryInTopLog
    ) throws URISyntaxException {
        log.debug("REST request to update LinksByCategoryInTopLog : {}, {}", id, linksByCategoryInTopLog);
        if (linksByCategoryInTopLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, linksByCategoryInTopLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!linksByCategoryInTopLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LinksByCategoryInTopLog result = linksByCategoryInTopLogRepository.save(linksByCategoryInTopLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, linksByCategoryInTopLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /links-by-category-in-top-logs/:id} : Partial updates given fields of an existing linksByCategoryInTopLog, field will ignore if it is null
     *
     * @param id the id of the linksByCategoryInTopLog to save.
     * @param linksByCategoryInTopLog the linksByCategoryInTopLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated linksByCategoryInTopLog,
     * or with status {@code 400 (Bad Request)} if the linksByCategoryInTopLog is not valid,
     * or with status {@code 404 (Not Found)} if the linksByCategoryInTopLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the linksByCategoryInTopLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/links-by-category-in-top-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<LinksByCategoryInTopLog> partialUpdateLinksByCategoryInTopLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LinksByCategoryInTopLog linksByCategoryInTopLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update LinksByCategoryInTopLog partially : {}, {}", id, linksByCategoryInTopLog);
        if (linksByCategoryInTopLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, linksByCategoryInTopLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!linksByCategoryInTopLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LinksByCategoryInTopLog> result = linksByCategoryInTopLogRepository
            .findById(linksByCategoryInTopLog.getId())
            .map(
                existingLinksByCategoryInTopLog -> {
                    if (linksByCategoryInTopLog.getCategory() != null) {
                        existingLinksByCategoryInTopLog.setCategory(linksByCategoryInTopLog.getCategory());
                    }
                    if (linksByCategoryInTopLog.getPriceDiapozon() != null) {
                        existingLinksByCategoryInTopLog.setPriceDiapozon(linksByCategoryInTopLog.getPriceDiapozon());
                    }
                    if (linksByCategoryInTopLog.getLink() != null) {
                        existingLinksByCategoryInTopLog.setLink(linksByCategoryInTopLog.getLink());
                    }
                    if (linksByCategoryInTopLog.getChanellAdminId() != null) {
                        existingLinksByCategoryInTopLog.setChanellAdminId(linksByCategoryInTopLog.getChanellAdminId());
                    }
                    if (linksByCategoryInTopLog.getDatePostLinkStart() != null) {
                        existingLinksByCategoryInTopLog.setDatePostLinkStart(linksByCategoryInTopLog.getDatePostLinkStart());
                    }
                    if (linksByCategoryInTopLog.getDatePostLinkEnd() != null) {
                        existingLinksByCategoryInTopLog.setDatePostLinkEnd(linksByCategoryInTopLog.getDatePostLinkEnd());
                    }
                    if (linksByCategoryInTopLog.getPositionBetweenLinks() != null) {
                        existingLinksByCategoryInTopLog.setPositionBetweenLinks(linksByCategoryInTopLog.getPositionBetweenLinks());
                    }
                    if (linksByCategoryInTopLog.getShowLink() != null) {
                        existingLinksByCategoryInTopLog.setShowLink(linksByCategoryInTopLog.getShowLink());
                    }
                    if (linksByCategoryInTopLog.getIsDelete() != null) {
                        existingLinksByCategoryInTopLog.setIsDelete(linksByCategoryInTopLog.getIsDelete());
                    }
                    if (linksByCategoryInTopLog.getDate1() != null) {
                        existingLinksByCategoryInTopLog.setDate1(linksByCategoryInTopLog.getDate1());
                    }
                    if (linksByCategoryInTopLog.getDate2() != null) {
                        existingLinksByCategoryInTopLog.setDate2(linksByCategoryInTopLog.getDate2());
                    }
                    if (linksByCategoryInTopLog.getLong1() != null) {
                        existingLinksByCategoryInTopLog.setLong1(linksByCategoryInTopLog.getLong1());
                    }
                    if (linksByCategoryInTopLog.getString1() != null) {
                        existingLinksByCategoryInTopLog.setString1(linksByCategoryInTopLog.getString1());
                    }
                    if (linksByCategoryInTopLog.getBoolean1() != null) {
                        existingLinksByCategoryInTopLog.setBoolean1(linksByCategoryInTopLog.getBoolean1());
                    }

                    return existingLinksByCategoryInTopLog;
                }
            )
            .map(linksByCategoryInTopLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, linksByCategoryInTopLog.getId().toString())
        );
    }

    /**
     * {@code GET  /links-by-category-in-top-logs} : get all the linksByCategoryInTopLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of linksByCategoryInTopLogs in body.
     */
    @GetMapping("/links-by-category-in-top-logs")
    public List<LinksByCategoryInTopLog> getAllLinksByCategoryInTopLogs() {
        log.debug("REST request to get all LinksByCategoryInTopLogs");
        return linksByCategoryInTopLogRepository.findAll();
    }

    /**
     * {@code GET  /links-by-category-in-top-logs/:id} : get the "id" linksByCategoryInTopLog.
     *
     * @param id the id of the linksByCategoryInTopLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the linksByCategoryInTopLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/links-by-category-in-top-logs/{id}")
    public ResponseEntity<LinksByCategoryInTopLog> getLinksByCategoryInTopLog(@PathVariable Long id) {
        log.debug("REST request to get LinksByCategoryInTopLog : {}", id);
        Optional<LinksByCategoryInTopLog> linksByCategoryInTopLog = linksByCategoryInTopLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(linksByCategoryInTopLog);
    }

    /**
     * {@code DELETE  /links-by-category-in-top-logs/:id} : delete the "id" linksByCategoryInTopLog.
     *
     * @param id the id of the linksByCategoryInTopLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/links-by-category-in-top-logs/{id}")
    public ResponseEntity<Void> deleteLinksByCategoryInTopLog(@PathVariable Long id) {
        log.debug("REST request to delete LinksByCategoryInTopLog : {}", id);
        linksByCategoryInTopLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
