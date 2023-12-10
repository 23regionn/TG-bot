package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.RelCategoryChannels;
import com.mycompany.myapp.domain.RelCategoryCityChannels;
import com.mycompany.myapp.repository.RelCategoryCityChannelsRepository;
import com.mycompany.myapp.service.RelCategoryCityChannelsService;
import com.mycompany.myapp.service.dto.relCategoryChannel.RelCategoryChannelsCreateDTO;
import com.mycompany.myapp.service.dto.relCategoryCityChannels.RelCategoryCityChannelsCreateDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.RelCategoryCityChannels}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RelCategoryCityChannelsResource {

    private final Logger log = LoggerFactory.getLogger(RelCategoryCityChannelsResource.class);

    private static final String ENTITY_NAME = "relCategoryCityChannels";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelCategoryCityChannelsRepository relCategoryCityChannelsRepository;
    private final RelCategoryCityChannelsService relCategoryCityChannelsService;

    public RelCategoryCityChannelsResource(
        RelCategoryCityChannelsRepository relCategoryCityChannelsRepository,
        RelCategoryCityChannelsService relCategoryCityChannelsService
    ) {
        this.relCategoryCityChannelsRepository = relCategoryCityChannelsRepository;
        this.relCategoryCityChannelsService = relCategoryCityChannelsService;
    }

    /**
     * {@code POST  /rel-category-city-channels} : Create a new relCategoryCityChannels.
     *
     * @param relCategoryCityChannels the relCategoryCityChannels to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relCategoryCityChannels, or with status {@code 400 (Bad Request)} if the relCategoryCityChannels has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rel-category-city-channels")
    public ResponseEntity<RelCategoryCityChannels> createRelCategoryCityChannels(
        @RequestBody RelCategoryCityChannels relCategoryCityChannels
    ) throws URISyntaxException {
        log.debug("REST request to save RelCategoryCityChannels : {}", relCategoryCityChannels);
        if (relCategoryCityChannels.getId() != null) {
            throw new BadRequestAlertException("A new relCategoryCityChannels cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelCategoryCityChannels result = relCategoryCityChannelsRepository.save(relCategoryCityChannels);
        return ResponseEntity
            .created(new URI("/api/rel-category-city-channels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rel-category-city-channels/:id} : Updates an existing relCategoryCityChannels.
     *
     * @param id the id of the relCategoryCityChannels to save.
     * @param relCategoryCityChannels the relCategoryCityChannels to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relCategoryCityChannels,
     * or with status {@code 400 (Bad Request)} if the relCategoryCityChannels is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relCategoryCityChannels couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rel-category-city-channels/{id}")
    public ResponseEntity<RelCategoryCityChannels> updateRelCategoryCityChannels(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RelCategoryCityChannels relCategoryCityChannels
    ) throws URISyntaxException {
        log.debug("REST request to update RelCategoryCityChannels : {}, {}", id, relCategoryCityChannels);
        if (relCategoryCityChannels.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relCategoryCityChannels.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relCategoryCityChannelsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RelCategoryCityChannels result = relCategoryCityChannelsRepository.save(relCategoryCityChannels);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relCategoryCityChannels.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /rel-category-city-channels/:id} : Partial updates given fields of an existing relCategoryCityChannels, field will ignore if it is null
     *
     * @param id the id of the relCategoryCityChannels to save.
     * @param relCategoryCityChannels the relCategoryCityChannels to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relCategoryCityChannels,
     * or with status {@code 400 (Bad Request)} if the relCategoryCityChannels is not valid,
     * or with status {@code 404 (Not Found)} if the relCategoryCityChannels is not found,
     * or with status {@code 500 (Internal Server Error)} if the relCategoryCityChannels couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rel-category-city-channels/{id}")
    public ResponseEntity<RelCategoryCityChannels> partialUpdateRelCategoryCityChannels(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RelCategoryCityChannels relCategoryCityChannels
    ) throws URISyntaxException {
        log.debug("REST request to partial update RelCategoryCityChannels partially : {}, {}", id, relCategoryCityChannels);
        if (relCategoryCityChannels.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relCategoryCityChannels.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relCategoryCityChannelsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RelCategoryCityChannels> result = relCategoryCityChannelsRepository
            .findById(relCategoryCityChannels.getId())
            .map(
                existingRelCategoryCityChannels -> {
                    if (relCategoryCityChannels.getScoreChannel() != null) {
                        existingRelCategoryCityChannels.setScoreChannel(relCategoryCityChannels.getScoreChannel());
                    }
                    if (relCategoryCityChannels.getIsShowChannel() != null) {
                        existingRelCategoryCityChannels.setIsShowChannel(relCategoryCityChannels.getIsShowChannel());
                    }
                    if (relCategoryCityChannels.getComment() != null) {
                        existingRelCategoryCityChannels.setComment(relCategoryCityChannels.getComment());
                    }

                    return existingRelCategoryCityChannels;
                }
            )
            .map(relCategoryCityChannelsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relCategoryCityChannels.getId().toString())
        );
    }

    /**
     * {@code GET  /rel-category-city-channels} : get all the relCategoryCityChannels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relCategoryCityChannels in body.
     */
    @GetMapping("/rel-category-city-channels")
    public List<RelCategoryCityChannels> getAllRelCategoryCityChannels() {
        log.debug("REST request to get all RelCategoryCityChannels");
        return relCategoryCityChannelsRepository.findAll();
    }

    /**
     * {@code GET  /rel-category-city-channels/:id} : get the "id" relCategoryCityChannels.
     *
     * @param id the id of the relCategoryCityChannels to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relCategoryCityChannels, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rel-category-city-channels/{id}")
    public ResponseEntity<RelCategoryCityChannels> getRelCategoryCityChannels(@PathVariable Long id) {
        log.debug("REST request to get RelCategoryCityChannels : {}", id);
        Optional<RelCategoryCityChannels> relCategoryCityChannels = relCategoryCityChannelsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(relCategoryCityChannels);
    }

    /**
     * {@code DELETE  /rel-category-city-channels/:id} : delete the "id" relCategoryCityChannels.
     *
     * @param id the id of the relCategoryCityChannels to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rel-category-city-channels/{id}")
    public ResponseEntity<Void> deleteRelCategoryCityChannels(@PathVariable Long id) {
        log.debug("REST request to delete RelCategoryCityChannels : {}", id);
        relCategoryCityChannelsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/rel-category-city-channels/by-rel-city-and-category-id/{relCategoryCityId}")
    public List<RelCategoryCityChannels> getCategoryCityChannelsByRelCityCategory(@PathVariable Long relCategoryCityId) {
        log.debug("REST request to get all RelCategoryCityChannels by relCategoryCityId");
        return relCategoryCityChannelsService.getCategoryCityChannelsByRelCityCategory(relCategoryCityId);
    }

    @PostMapping("/rel-category-city-channels/by-ids")
    public ResponseEntity<RelCategoryCityChannels> createRelCategoryChannelsByIds(@RequestBody RelCategoryCityChannelsCreateDTO createDTO)
        throws URISyntaxException {
        log.debug("REST request to add RelCategoryCityChannelsCreateDTO : {}", createDTO);
        RelCategoryCityChannels result = relCategoryCityChannelsService.createNewRel(createDTO);
        return ResponseEntity
            .created(new URI("/api/rel-category-city-channels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
