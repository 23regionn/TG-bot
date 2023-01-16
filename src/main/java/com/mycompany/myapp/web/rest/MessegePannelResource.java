package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MessegePannel;
import com.mycompany.myapp.repository.MessegePannelRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.MessegePannel}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MessegePannelResource {

    private final Logger log = LoggerFactory.getLogger(MessegePannelResource.class);

    private static final String ENTITY_NAME = "messegePannel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MessegePannelRepository messegePannelRepository;

    public MessegePannelResource(MessegePannelRepository messegePannelRepository) {
        this.messegePannelRepository = messegePannelRepository;
    }

    /**
     * {@code POST  /messege-pannels} : Create a new messegePannel.
     *
     * @param messegePannel the messegePannel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messegePannel, or with status {@code 400 (Bad Request)} if the messegePannel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/messege-pannels")
    public ResponseEntity<MessegePannel> createMessegePannel(@RequestBody MessegePannel messegePannel) throws URISyntaxException {
        log.debug("REST request to save MessegePannel : {}", messegePannel);
        if (messegePannel.getId() != null) {
            throw new BadRequestAlertException("A new messegePannel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MessegePannel result = messegePannelRepository.save(messegePannel);
        return ResponseEntity
            .created(new URI("/api/messege-pannels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /messege-pannels/:id} : Updates an existing messegePannel.
     *
     * @param id the id of the messegePannel to save.
     * @param messegePannel the messegePannel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messegePannel,
     * or with status {@code 400 (Bad Request)} if the messegePannel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the messegePannel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/messege-pannels/{id}")
    public ResponseEntity<MessegePannel> updateMessegePannel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MessegePannel messegePannel
    ) throws URISyntaxException {
        log.debug("REST request to update MessegePannel : {}, {}", id, messegePannel);
        if (messegePannel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, messegePannel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!messegePannelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MessegePannel result = messegePannelRepository.save(messegePannel);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, messegePannel.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /messege-pannels/:id} : Partial updates given fields of an existing messegePannel, field will ignore if it is null
     *
     * @param id the id of the messegePannel to save.
     * @param messegePannel the messegePannel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated messegePannel,
     * or with status {@code 400 (Bad Request)} if the messegePannel is not valid,
     * or with status {@code 404 (Not Found)} if the messegePannel is not found,
     * or with status {@code 500 (Internal Server Error)} if the messegePannel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/messege-pannels/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<MessegePannel> partialUpdateMessegePannel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MessegePannel messegePannel
    ) throws URISyntaxException {
        log.debug("REST request to partial update MessegePannel partially : {}, {}", id, messegePannel);
        if (messegePannel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, messegePannel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!messegePannelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MessegePannel> result = messegePannelRepository
            .findById(messegePannel.getId())
            .map(
                existingMessegePannel -> {
                    if (messegePannel.getIdMessage() != null) {
                        existingMessegePannel.setIdMessage(messegePannel.getIdMessage());
                    }
                    if (messegePannel.getIdChannel() != null) {
                        existingMessegePannel.setIdChannel(messegePannel.getIdChannel());
                    }
                    if (messegePannel.getDateCreateMessage() != null) {
                        existingMessegePannel.setDateCreateMessage(messegePannel.getDateCreateMessage());
                    }
                    if (messegePannel.getTextMessage() != null) {
                        existingMessegePannel.setTextMessage(messegePannel.getTextMessage());
                    }
                    if (messegePannel.getIdAdmin() != null) {
                        existingMessegePannel.setIdAdmin(messegePannel.getIdAdmin());
                    }
                    if (messegePannel.getComment() != null) {
                        existingMessegePannel.setComment(messegePannel.getComment());
                    }
                    if (messegePannel.getStatus() != null) {
                        existingMessegePannel.setStatus(messegePannel.getStatus());
                    }
                    if (messegePannel.getServiceField1() != null) {
                        existingMessegePannel.setServiceField1(messegePannel.getServiceField1());
                    }
                    if (messegePannel.getServiceField2() != null) {
                        existingMessegePannel.setServiceField2(messegePannel.getServiceField2());
                    }
                    if (messegePannel.getServiceField3() != null) {
                        existingMessegePannel.setServiceField3(messegePannel.getServiceField3());
                    }

                    return existingMessegePannel;
                }
            )
            .map(messegePannelRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, messegePannel.getId().toString())
        );
    }

    /**
     * {@code GET  /messege-pannels} : get all the messegePannels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of messegePannels in body.
     */
    @GetMapping("/messege-pannels")
    public List<MessegePannel> getAllMessegePannels() {
        log.debug("REST request to get all MessegePannels");
        return messegePannelRepository.findAll();
    }

    /**
     * {@code GET  /messege-pannels/:id} : get the "id" messegePannel.
     *
     * @param id the id of the messegePannel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the messegePannel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/messege-pannels/{id}")
    public ResponseEntity<MessegePannel> getMessegePannel(@PathVariable Long id) {
        log.debug("REST request to get MessegePannel : {}", id);
        Optional<MessegePannel> messegePannel = messegePannelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(messegePannel);
    }

    /**
     * {@code DELETE  /messege-pannels/:id} : delete the "id" messegePannel.
     *
     * @param id the id of the messegePannel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/messege-pannels/{id}")
    public ResponseEntity<Void> deleteMessegePannel(@PathVariable Long id) {
        log.debug("REST request to delete MessegePannel : {}", id);
        messegePannelRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
