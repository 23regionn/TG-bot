package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.OfferFromCostumers;
import com.mycompany.myapp.repository.OfferFromCostumersRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.OfferFromCostumers}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OfferFromCostumersResource {

    private final Logger log = LoggerFactory.getLogger(OfferFromCostumersResource.class);

    private static final String ENTITY_NAME = "offerFromCostumers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfferFromCostumersRepository offerFromCostumersRepository;

    public OfferFromCostumersResource(OfferFromCostumersRepository offerFromCostumersRepository) {
        this.offerFromCostumersRepository = offerFromCostumersRepository;
    }

    /**
     * {@code POST  /offer-from-costumers} : Create a new offerFromCostumers.
     *
     * @param offerFromCostumers the offerFromCostumers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new offerFromCostumers, or with status {@code 400 (Bad Request)} if the offerFromCostumers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/offer-from-costumers")
    public ResponseEntity<OfferFromCostumers> createOfferFromCostumers(@RequestBody OfferFromCostumers offerFromCostumers)
        throws URISyntaxException {
        log.debug("REST request to save OfferFromCostumers : {}", offerFromCostumers);
        if (offerFromCostumers.getId() != null) {
            throw new BadRequestAlertException("A new offerFromCostumers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OfferFromCostumers result = offerFromCostumersRepository.save(offerFromCostumers);
        return ResponseEntity
            .created(new URI("/api/offer-from-costumers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /offer-from-costumers/:id} : Updates an existing offerFromCostumers.
     *
     * @param id the id of the offerFromCostumers to save.
     * @param offerFromCostumers the offerFromCostumers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerFromCostumers,
     * or with status {@code 400 (Bad Request)} if the offerFromCostumers is not valid,
     * or with status {@code 500 (Internal Server Error)} if the offerFromCostumers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/offer-from-costumers/{id}")
    public ResponseEntity<OfferFromCostumers> updateOfferFromCostumers(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OfferFromCostumers offerFromCostumers
    ) throws URISyntaxException {
        log.debug("REST request to update OfferFromCostumers : {}, {}", id, offerFromCostumers);
        if (offerFromCostumers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, offerFromCostumers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!offerFromCostumersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OfferFromCostumers result = offerFromCostumersRepository.save(offerFromCostumers);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, offerFromCostumers.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /offer-from-costumers/:id} : Partial updates given fields of an existing offerFromCostumers, field will ignore if it is null
     *
     * @param id the id of the offerFromCostumers to save.
     * @param offerFromCostumers the offerFromCostumers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated offerFromCostumers,
     * or with status {@code 400 (Bad Request)} if the offerFromCostumers is not valid,
     * or with status {@code 404 (Not Found)} if the offerFromCostumers is not found,
     * or with status {@code 500 (Internal Server Error)} if the offerFromCostumers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/offer-from-costumers/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<OfferFromCostumers> partialUpdateOfferFromCostumers(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OfferFromCostumers offerFromCostumers
    ) throws URISyntaxException {
        log.debug("REST request to partial update OfferFromCostumers partially : {}, {}", id, offerFromCostumers);
        if (offerFromCostumers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, offerFromCostumers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!offerFromCostumersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OfferFromCostumers> result = offerFromCostumersRepository
            .findById(offerFromCostumers.getId())
            .map(
                existingOfferFromCostumers -> {
                    if (offerFromCostumers.getText() != null) {
                        existingOfferFromCostumers.setText(offerFromCostumers.getText());
                    }
                    if (offerFromCostumers.getIsDelete() != null) {
                        existingOfferFromCostumers.setIsDelete(offerFromCostumers.getIsDelete());
                    }
                    if (offerFromCostumers.getAdminId() != null) {
                        existingOfferFromCostumers.setAdminId(offerFromCostumers.getAdminId());
                    }
                    if (offerFromCostumers.getIsActive() != null) {
                        existingOfferFromCostumers.setIsActive(offerFromCostumers.getIsActive());
                    }
                    if (offerFromCostumers.getDate1() != null) {
                        existingOfferFromCostumers.setDate1(offerFromCostumers.getDate1());
                    }
                    if (offerFromCostumers.getDate2() != null) {
                        existingOfferFromCostumers.setDate2(offerFromCostumers.getDate2());
                    }
                    if (offerFromCostumers.getLong1() != null) {
                        existingOfferFromCostumers.setLong1(offerFromCostumers.getLong1());
                    }
                    if (offerFromCostumers.getString1() != null) {
                        existingOfferFromCostumers.setString1(offerFromCostumers.getString1());
                    }
                    if (offerFromCostumers.getBoolean1() != null) {
                        existingOfferFromCostumers.setBoolean1(offerFromCostumers.getBoolean1());
                    }

                    return existingOfferFromCostumers;
                }
            )
            .map(offerFromCostumersRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, offerFromCostumers.getId().toString())
        );
    }

    /**
     * {@code GET  /offer-from-costumers} : get all the offerFromCostumers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of offerFromCostumers in body.
     */
    @GetMapping("/offer-from-costumers")
    public List<OfferFromCostumers> getAllOfferFromCostumers() {
        log.debug("REST request to get all OfferFromCostumers");
        return offerFromCostumersRepository.findAll();
    }

    /**
     * {@code GET  /offer-from-costumers/:id} : get the "id" offerFromCostumers.
     *
     * @param id the id of the offerFromCostumers to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the offerFromCostumers, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/offer-from-costumers/{id}")
    public ResponseEntity<OfferFromCostumers> getOfferFromCostumers(@PathVariable Long id) {
        log.debug("REST request to get OfferFromCostumers : {}", id);
        Optional<OfferFromCostumers> offerFromCostumers = offerFromCostumersRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(offerFromCostumers);
    }

    /**
     * {@code DELETE  /offer-from-costumers/:id} : delete the "id" offerFromCostumers.
     *
     * @param id the id of the offerFromCostumers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/offer-from-costumers/{id}")
    public ResponseEntity<Void> deleteOfferFromCostumers(@PathVariable Long id) {
        log.debug("REST request to delete OfferFromCostumers : {}", id);
        offerFromCostumersRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
