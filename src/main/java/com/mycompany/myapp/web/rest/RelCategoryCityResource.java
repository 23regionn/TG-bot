package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.RelCategoryCity;
import com.mycompany.myapp.repository.RelCategoryCityRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.RelCategoryCity}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RelCategoryCityResource {

    private final Logger log = LoggerFactory.getLogger(RelCategoryCityResource.class);

    private static final String ENTITY_NAME = "relCategoryCity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelCategoryCityRepository relCategoryCityRepository;

    public RelCategoryCityResource(RelCategoryCityRepository relCategoryCityRepository) {
        this.relCategoryCityRepository = relCategoryCityRepository;
    }

    /**
     * {@code POST  /rel-category-cities} : Create a new relCategoryCity.
     *
     * @param relCategoryCity the relCategoryCity to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relCategoryCity, or with status {@code 400 (Bad Request)} if the relCategoryCity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rel-category-cities")
    public ResponseEntity<RelCategoryCity> createRelCategoryCity(@RequestBody RelCategoryCity relCategoryCity) throws URISyntaxException {
        log.debug("REST request to save RelCategoryCity : {}", relCategoryCity);
        if (relCategoryCity.getId() != null) {
            throw new BadRequestAlertException("A new relCategoryCity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelCategoryCity result = relCategoryCityRepository.save(relCategoryCity);
        return ResponseEntity
            .created(new URI("/api/rel-category-cities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rel-category-cities/:id} : Updates an existing relCategoryCity.
     *
     * @param id the id of the relCategoryCity to save.
     * @param relCategoryCity the relCategoryCity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relCategoryCity,
     * or with status {@code 400 (Bad Request)} if the relCategoryCity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relCategoryCity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rel-category-cities/{id}")
    public ResponseEntity<RelCategoryCity> updateRelCategoryCity(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RelCategoryCity relCategoryCity
    ) throws URISyntaxException {
        log.debug("REST request to update RelCategoryCity : {}, {}", id, relCategoryCity);
        if (relCategoryCity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relCategoryCity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relCategoryCityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RelCategoryCity result = relCategoryCityRepository.save(relCategoryCity);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relCategoryCity.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /rel-category-cities/:id} : Partial updates given fields of an existing relCategoryCity, field will ignore if it is null
     *
     * @param id the id of the relCategoryCity to save.
     * @param relCategoryCity the relCategoryCity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relCategoryCity,
     * or with status {@code 400 (Bad Request)} if the relCategoryCity is not valid,
     * or with status {@code 404 (Not Found)} if the relCategoryCity is not found,
     * or with status {@code 500 (Internal Server Error)} if the relCategoryCity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rel-category-cities/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<RelCategoryCity> partialUpdateRelCategoryCity(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RelCategoryCity relCategoryCity
    ) throws URISyntaxException {
        log.debug("REST request to partial update RelCategoryCity partially : {}, {}", id, relCategoryCity);
        if (relCategoryCity.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, relCategoryCity.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!relCategoryCityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RelCategoryCity> result = relCategoryCityRepository
            .findById(relCategoryCity.getId())
            .map(
                existingRelCategoryCity -> {
                    if (relCategoryCity.getIsShow() != null) {
                        existingRelCategoryCity.setIsShow(relCategoryCity.getIsShow());
                    }
                    if (relCategoryCity.getScore() != null) {
                        existingRelCategoryCity.setScore(relCategoryCity.getScore());
                    }
                    if (relCategoryCity.getIsFirst() != null) {
                        existingRelCategoryCity.setIsFirst(relCategoryCity.getIsFirst());
                    }

                    return existingRelCategoryCity;
                }
            )
            .map(relCategoryCityRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relCategoryCity.getId().toString())
        );
    }

    /**
     * {@code GET  /rel-category-cities} : get all the relCategoryCities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relCategoryCities in body.
     */
    @GetMapping("/rel-category-cities")
    public List<RelCategoryCity> getAllRelCategoryCities() {
        log.debug("REST request to get all RelCategoryCities");
        return relCategoryCityRepository.findAll();
    }

    /**
     * {@code GET  /rel-category-cities/:id} : get the "id" relCategoryCity.
     *
     * @param id the id of the relCategoryCity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relCategoryCity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rel-category-cities/{id}")
    public ResponseEntity<RelCategoryCity> getRelCategoryCity(@PathVariable Long id) {
        log.debug("REST request to get RelCategoryCity : {}", id);
        Optional<RelCategoryCity> relCategoryCity = relCategoryCityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(relCategoryCity);
    }

    /**
     * {@code DELETE  /rel-category-cities/:id} : delete the "id" relCategoryCity.
     *
     * @param id the id of the relCategoryCity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rel-category-cities/{id}")
    public ResponseEntity<Void> deleteRelCategoryCity(@PathVariable Long id) {
        log.debug("REST request to delete RelCategoryCity : {}", id);
        relCategoryCityRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
