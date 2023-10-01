package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.RelCategoryChannels;
import com.mycompany.myapp.repository.RelCategoryChannelsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.RelCategoryChannels}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RelCategoryChannelsResource {

    private final Logger log = LoggerFactory.getLogger(RelCategoryChannelsResource.class);

    private static final String ENTITY_NAME = "relCategoryChannels";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelCategoryChannelsRepository relCategoryChannelsRepository;

    public RelCategoryChannelsResource(RelCategoryChannelsRepository relCategoryChannelsRepository) {
        this.relCategoryChannelsRepository = relCategoryChannelsRepository;
    }

    /**
     * {@code POST  /rel-category-channels} : Create a new relCategoryChannels.
     *
     * @param relCategoryChannels the relCategoryChannels to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relCategoryChannels, or with status {@code 400 (Bad Request)} if the relCategoryChannels has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rel-category-channels")
    public ResponseEntity<RelCategoryChannels> createRelCategoryChannels(@RequestBody RelCategoryChannels relCategoryChannels)
        throws URISyntaxException {
        log.debug("REST request to save RelCategoryChannels : {}", relCategoryChannels);
        if (relCategoryChannels.getId() != null) {
            throw new BadRequestAlertException("A new relCategoryChannels cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelCategoryChannels result = relCategoryChannelsRepository.save(relCategoryChannels);
        return ResponseEntity
            .created(new URI("/api/rel-category-channels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rel-category-channels/:id} : Updates an existing relCategoryChannels.
     *
     * @param id the id of the relCategoryChannels to save.
     * @param relCategoryChannels the relCategoryChannels to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relCategoryChannels,
     * or with status {@code 400 (Bad Request)} if the relCategoryChannels is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relCategoryChannels couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rel-category-channels/{id}")
    public ResponseEntity<RelCategoryChannels> updateRelCategoryChannels(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RelCategoryChannels relCategoryChannels
    ) throws URISyntaxException {
        log.debug("REST request to update RelCategoryChannels : {}, {}", id, relCategoryChannels);
        if (relCategoryChannels.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relCategoryChannels.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relCategoryChannelsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RelCategoryChannels result = relCategoryChannelsRepository.save(relCategoryChannels);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relCategoryChannels.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /rel-category-channels/:id} : Partial updates given fields of an existing relCategoryChannels, field will ignore if it is null
     *
     * @param id the id of the relCategoryChannels to save.
     * @param relCategoryChannels the relCategoryChannels to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relCategoryChannels,
     * or with status {@code 400 (Bad Request)} if the relCategoryChannels is not valid,
     * or with status {@code 404 (Not Found)} if the relCategoryChannels is not found,
     * or with status {@code 500 (Internal Server Error)} if the relCategoryChannels couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rel-category-channels/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<RelCategoryChannels> partialUpdateRelCategoryChannels(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RelCategoryChannels relCategoryChannels
    ) throws URISyntaxException {
        log.debug("REST request to partial update RelCategoryChannels partially : {}, {}", id, relCategoryChannels);
        if (relCategoryChannels.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relCategoryChannels.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relCategoryChannelsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RelCategoryChannels> result = relCategoryChannelsRepository
            .findById(relCategoryChannels.getId())
            .map(
                existingRelCategoryChannels -> {
                    if (relCategoryChannels.getScoreChannel() != null) {
                        existingRelCategoryChannels.setScoreChannel(relCategoryChannels.getScoreChannel());
                    }
                    if (relCategoryChannels.getIsShowChannel() != null) {
                        existingRelCategoryChannels.setIsShowChannel(relCategoryChannels.getIsShowChannel());
                    }

                    return existingRelCategoryChannels;
                }
            )
            .map(relCategoryChannelsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relCategoryChannels.getId().toString())
        );
    }

    /**
     * {@code GET  /rel-category-channels} : get all the relCategoryChannels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relCategoryChannels in body.
     */
    @GetMapping("/rel-category-channels")
    public List<RelCategoryChannels> getAllRelCategoryChannels() {
        log.debug("REST request to get all RelCategoryChannels");
        return relCategoryChannelsRepository.findAll();
    }

    /**
     * {@code GET  /rel-category-channels/:id} : get the "id" relCategoryChannels.
     *
     * @param id the id of the relCategoryChannels to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relCategoryChannels, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rel-category-channels/{id}")
    public ResponseEntity<RelCategoryChannels> getRelCategoryChannels(@PathVariable Long id) {
        log.debug("REST request to get RelCategoryChannels : {}", id);
        Optional<RelCategoryChannels> relCategoryChannels = relCategoryChannelsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(relCategoryChannels);
    }

    /**
     * {@code DELETE  /rel-category-channels/:id} : delete the "id" relCategoryChannels.
     *
     * @param id the id of the relCategoryChannels to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rel-category-channels/{id}")
    public ResponseEntity<Void> deleteRelCategoryChannels(@PathVariable Long id) {
        log.debug("REST request to delete RelCategoryChannels : {}", id);
        relCategoryChannelsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
