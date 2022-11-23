package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.LinksByCategoryInTop;
import com.mycompany.myapp.repository.LinksByCategoryInTopRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.LinksByCategoryInTop}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LinksByCategoryInTopResource {

    private final Logger log = LoggerFactory.getLogger(LinksByCategoryInTopResource.class);

    private static final String ENTITY_NAME = "linksByCategoryInTop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LinksByCategoryInTopRepository linksByCategoryInTopRepository;

    public LinksByCategoryInTopResource(LinksByCategoryInTopRepository linksByCategoryInTopRepository) {
        this.linksByCategoryInTopRepository = linksByCategoryInTopRepository;
    }

    /**
     * {@code POST  /links-by-category-in-tops} : Create a new linksByCategoryInTop.
     *
     * @param linksByCategoryInTop the linksByCategoryInTop to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new linksByCategoryInTop, or with status {@code 400 (Bad Request)} if the linksByCategoryInTop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/links-by-category-in-tops")
    public ResponseEntity<LinksByCategoryInTop> createLinksByCategoryInTop(@RequestBody LinksByCategoryInTop linksByCategoryInTop)
        throws URISyntaxException {
        log.debug("REST request to save LinksByCategoryInTop : {}", linksByCategoryInTop);
        if (linksByCategoryInTop.getId() != null) {
            throw new BadRequestAlertException("A new linksByCategoryInTop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LinksByCategoryInTop result = linksByCategoryInTopRepository.save(linksByCategoryInTop);
        return ResponseEntity
            .created(new URI("/api/links-by-category-in-tops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /links-by-category-in-tops/:id} : Updates an existing linksByCategoryInTop.
     *
     * @param id the id of the linksByCategoryInTop to save.
     * @param linksByCategoryInTop the linksByCategoryInTop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated linksByCategoryInTop,
     * or with status {@code 400 (Bad Request)} if the linksByCategoryInTop is not valid,
     * or with status {@code 500 (Internal Server Error)} if the linksByCategoryInTop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/links-by-category-in-tops/{id}")
    public ResponseEntity<LinksByCategoryInTop> updateLinksByCategoryInTop(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LinksByCategoryInTop linksByCategoryInTop
    ) throws URISyntaxException {
        log.debug("REST request to update LinksByCategoryInTop : {}, {}", id, linksByCategoryInTop);
        if (linksByCategoryInTop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, linksByCategoryInTop.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!linksByCategoryInTopRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LinksByCategoryInTop result = linksByCategoryInTopRepository.save(linksByCategoryInTop);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, linksByCategoryInTop.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /links-by-category-in-tops/:id} : Partial updates given fields of an existing linksByCategoryInTop, field will ignore if it is null
     *
     * @param id the id of the linksByCategoryInTop to save.
     * @param linksByCategoryInTop the linksByCategoryInTop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated linksByCategoryInTop,
     * or with status {@code 400 (Bad Request)} if the linksByCategoryInTop is not valid,
     * or with status {@code 404 (Not Found)} if the linksByCategoryInTop is not found,
     * or with status {@code 500 (Internal Server Error)} if the linksByCategoryInTop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/links-by-category-in-tops/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<LinksByCategoryInTop> partialUpdateLinksByCategoryInTop(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LinksByCategoryInTop linksByCategoryInTop
    ) throws URISyntaxException {
        log.debug("REST request to partial update LinksByCategoryInTop partially : {}, {}", id, linksByCategoryInTop);
        if (linksByCategoryInTop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, linksByCategoryInTop.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!linksByCategoryInTopRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LinksByCategoryInTop> result = linksByCategoryInTopRepository
            .findById(linksByCategoryInTop.getId())
            .map(
                existingLinksByCategoryInTop -> {
                    if (linksByCategoryInTop.getCategory() != null) {
                        existingLinksByCategoryInTop.setCategory(linksByCategoryInTop.getCategory());
                    }
                    if (linksByCategoryInTop.getPriceDiapozon() != null) {
                        existingLinksByCategoryInTop.setPriceDiapozon(linksByCategoryInTop.getPriceDiapozon());
                    }
                    if (linksByCategoryInTop.getLink() != null) {
                        existingLinksByCategoryInTop.setLink(linksByCategoryInTop.getLink());
                    }
                    if (linksByCategoryInTop.getChanellAdminId() != null) {
                        existingLinksByCategoryInTop.setChanellAdminId(linksByCategoryInTop.getChanellAdminId());
                    }
                    if (linksByCategoryInTop.getDatePostLinkStart() != null) {
                        existingLinksByCategoryInTop.setDatePostLinkStart(linksByCategoryInTop.getDatePostLinkStart());
                    }
                    if (linksByCategoryInTop.getDatePostLinkEnd() != null) {
                        existingLinksByCategoryInTop.setDatePostLinkEnd(linksByCategoryInTop.getDatePostLinkEnd());
                    }
                    if (linksByCategoryInTop.getPositionBetweenLinks() != null) {
                        existingLinksByCategoryInTop.setPositionBetweenLinks(linksByCategoryInTop.getPositionBetweenLinks());
                    }
                    if (linksByCategoryInTop.getShowLink() != null) {
                        existingLinksByCategoryInTop.setShowLink(linksByCategoryInTop.getShowLink());
                    }
                    if (linksByCategoryInTop.getIsDelete() != null) {
                        existingLinksByCategoryInTop.setIsDelete(linksByCategoryInTop.getIsDelete());
                    }
                    if (linksByCategoryInTop.getDate1() != null) {
                        existingLinksByCategoryInTop.setDate1(linksByCategoryInTop.getDate1());
                    }
                    if (linksByCategoryInTop.getDate2() != null) {
                        existingLinksByCategoryInTop.setDate2(linksByCategoryInTop.getDate2());
                    }
                    if (linksByCategoryInTop.getLong1() != null) {
                        existingLinksByCategoryInTop.setLong1(linksByCategoryInTop.getLong1());
                    }
                    if (linksByCategoryInTop.getString1() != null) {
                        existingLinksByCategoryInTop.setString1(linksByCategoryInTop.getString1());
                    }
                    if (linksByCategoryInTop.getBoolean1() != null) {
                        existingLinksByCategoryInTop.setBoolean1(linksByCategoryInTop.getBoolean1());
                    }

                    return existingLinksByCategoryInTop;
                }
            )
            .map(linksByCategoryInTopRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, linksByCategoryInTop.getId().toString())
        );
    }

    /**
     * {@code GET  /links-by-category-in-tops} : get all the linksByCategoryInTops.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of linksByCategoryInTops in body.
     */
    @GetMapping("/links-by-category-in-tops")
    public List<LinksByCategoryInTop> getAllLinksByCategoryInTops() {
        log.debug("REST request to get all LinksByCategoryInTops");
        return linksByCategoryInTopRepository.findAll();
    }

    /**
     * {@code GET  /links-by-category-in-tops/:id} : get the "id" linksByCategoryInTop.
     *
     * @param id the id of the linksByCategoryInTop to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the linksByCategoryInTop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/links-by-category-in-tops/{id}")
    public ResponseEntity<LinksByCategoryInTop> getLinksByCategoryInTop(@PathVariable Long id) {
        log.debug("REST request to get LinksByCategoryInTop : {}", id);
        Optional<LinksByCategoryInTop> linksByCategoryInTop = linksByCategoryInTopRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(linksByCategoryInTop);
    }

    /**
     * {@code DELETE  /links-by-category-in-tops/:id} : delete the "id" linksByCategoryInTop.
     *
     * @param id the id of the linksByCategoryInTop to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/links-by-category-in-tops/{id}")
    public ResponseEntity<Void> deleteLinksByCategoryInTop(@PathVariable Long id) {
        log.debug("REST request to delete LinksByCategoryInTop : {}", id);
        linksByCategoryInTopRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
