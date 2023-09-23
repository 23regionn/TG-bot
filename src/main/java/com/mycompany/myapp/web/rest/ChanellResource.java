package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.repository.ChanellRepository;
import com.mycompany.myapp.service.ChannelService;
import com.mycompany.myapp.service.dto.ChanellPostDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Chanell}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ChanellResource {

    private final Logger log = LoggerFactory.getLogger(ChanellResource.class);

    private static final String ENTITY_NAME = "chanell";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChanellRepository chanellRepository;
    private final ChannelService channelService;

    public ChanellResource(ChanellRepository chanellRepository, ChannelService channelService) {
        this.chanellRepository = chanellRepository;
        this.channelService = channelService;
    }

    /**
     * {@code POST  /chanells} : Create a new chanell.
     *
     * @param chanell the chanell to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chanell, or with status {@code 400 (Bad Request)} if the chanell has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chanells")
    public ResponseEntity<Chanell> createChanell(@RequestBody Chanell chanell) throws URISyntaxException {
        log.debug("REST request to save Chanell : {}", chanell);
        if (chanell.getId() != null) {
            throw new BadRequestAlertException("A new chanell cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Chanell result = chanellRepository.save(chanell);
        return ResponseEntity
            .created(new URI("/api/chanells/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chanells/:id} : Updates an existing chanell.
     *
     * @param id the id of the chanell to save.
     * @param chanell the chanell to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chanell,
     * or with status {@code 400 (Bad Request)} if the chanell is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chanell couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chanells/{id}")
    public ResponseEntity<Chanell> updateChanell(@PathVariable(value = "id", required = false) final Long id, @RequestBody Chanell chanell)
        throws URISyntaxException {
        log.debug("REST request to update Chanell : {}, {}", id, chanell);
        if (chanell.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chanell.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chanellRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Chanell result = chanellRepository.save(chanell);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, chanell.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /chanells/:id} : Partial updates given fields of an existing chanell, field will ignore if it is null
     *
     * @param id the id of the chanell to save.
     * @param chanell the chanell to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chanell,
     * or with status {@code 400 (Bad Request)} if the chanell is not valid,
     * or with status {@code 404 (Not Found)} if the chanell is not found,
     * or with status {@code 500 (Internal Server Error)} if the chanell couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/chanells/{id}")
    public ResponseEntity<Chanell> partialUpdateChanell(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Chanell chanell
    ) throws URISyntaxException {
        log.debug("REST request to partial update Chanell partially : {}, {}", id, chanell);
        if (chanell.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, chanell.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!chanellRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Chanell> result = chanellRepository
            .findById(chanell.getId())
            .map(
                existingChanell -> {
                    if (chanell.getName() != null) {
                        existingChanell.setName(chanell.getName());
                    }
                    if (chanell.getLink() != null) {
                        existingChanell.setLink(chanell.getLink());
                    }
                    if (chanell.getScore() != null) {
                        existingChanell.setScore(chanell.getScore());
                    }
                    if (chanell.getStatus() != null) {
                        existingChanell.setStatus(chanell.getStatus());
                    }
                    if (chanell.getCountSubscribers() != null) {
                        existingChanell.setCountSubscribers(chanell.getCountSubscribers());
                    }
                    if (chanell.getQuailityFromAnotherSources() != null) {
                        existingChanell.setQuailityFromAnotherSources(chanell.getQuailityFromAnotherSources());
                    }
                    if (chanell.getPriceDiapozon() != null) {
                        existingChanell.setPriceDiapozon(chanell.getPriceDiapozon());
                    }
                    if (chanell.getIsModerate() != null) {
                        existingChanell.setIsModerate(chanell.getIsModerate());
                    }
                    if (chanell.getShowChanellInTopByCategory() != null) {
                        existingChanell.setShowChanellInTopByCategory(chanell.getShowChanellInTopByCategory());
                    }
                    if (chanell.getRegion() != null) {
                        existingChanell.setRegion(chanell.getRegion());
                    }
                    if (chanell.getCity() != null) {
                        existingChanell.setCity(chanell.getCity());
                    }
                    if (chanell.getIsDelete() != null) {
                        existingChanell.setIsDelete(chanell.getIsDelete());
                    }
                    if (chanell.getCurrentDate() != null) {
                        existingChanell.setCurrentDate(chanell.getCurrentDate());
                    }
                    if (chanell.getDate1() != null) {
                        existingChanell.setDate1(chanell.getDate1());
                    }
                    if (chanell.getDate2() != null) {
                        existingChanell.setDate2(chanell.getDate2());
                    }
                    if (chanell.getLong1() != null) {
                        existingChanell.setLong1(chanell.getLong1());
                    }
                    if (chanell.getString1() != null) {
                        existingChanell.setString1(chanell.getString1());
                    }
                    if (chanell.getBoolean1() != null) {
                        existingChanell.setBoolean1(chanell.getBoolean1());
                    }
                    if (chanell.getIsPay() != null) {
                        existingChanell.setIsPay(chanell.getIsPay());
                    }
                    if (chanell.getPriceForPay() != null) {
                        existingChanell.setPriceForPay(chanell.getPriceForPay());
                    }
                    if (chanell.getStartDate() != null) {
                        existingChanell.setStartDate(chanell.getStartDate());
                    }
                    if (chanell.getLastPayDate() != null) {
                        existingChanell.setLastPayDate(chanell.getLastPayDate());
                    }
                    if (chanell.getEndPublicDate() != null) {
                        existingChanell.setEndPublicDate(chanell.getEndPublicDate());
                    }
                    if (chanell.getComment() != null) {
                        existingChanell.setComment(chanell.getComment());
                    }
                    if (chanell.getContacts() != null) {
                        existingChanell.setContacts(chanell.getContacts());
                    }

                    return existingChanell;
                }
            )
            .map(chanellRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, chanell.getId().toString())
        );
    }

    /**
     * {@code GET  /chanells} : get all the chanells.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chanells in body.
     */
    @GetMapping("/chanells")
    public List<Chanell> getAllChanells() {
        log.debug("REST request to get all Chanells");
        return chanellRepository.findAll();
    }

    /**
     * {@code GET  /chanells/:id} : get the "id" chanell.
     *
     * @param id the id of the chanell to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chanell, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chanells/{id}")
    public ResponseEntity<Chanell> getChanell(@PathVariable Long id) {
        log.debug("REST request to get Chanell : {}", id);
        Optional<Chanell> chanell = chanellRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(chanell);
    }

    @GetMapping("/chanells/by-category-id/{id}")
    public ResponseEntity<List<Chanell>> getChannelsByCategoryId(@PathVariable Long id) {
        log.debug("REST request to get Chanell : {}", id);
        List<Chanell> chanells = channelService.getChannelsByCategoryId(id);
        return new ResponseEntity<>(chanells, HttpStatus.OK);
    }

    /**
     * {@code DELETE  /chanells/:id} : delete the "id" chanell.
     *
     * @param id the id of the chanell to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chanells/{id}")
    public ResponseEntity<Void> deleteChanell(@PathVariable Long id) {
        log.debug("REST request to delete Chanell : {}", id);
        chanellRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/city-names")
    public Set<String> getAllCityNames() {
        log.debug("REST request to get all City Names");
        return chanellRepository.getCitiesNames();
    }

    @GetMapping("/city-names-by-first-letter")
    public Set<String> getAllCitiesByFirstLetter() {
        log.debug("REST request to get all City Names");
        return chanellRepository.getCitiesByFirstLetter("С");
    }

    @PostMapping("/chanells/with-category-id")
    public ResponseEntity<Chanell> createChannelWithCategoryId(@RequestBody ChanellPostDTO chanellPostDTO) throws URISyntaxException {
        log.debug("REST request to save ChanellPostDTO from page with channels Category : {}", chanellPostDTO);

        Chanell result = channelService.createChannelByCategory(chanellPostDTO);
        return ResponseEntity
            .created(new URI("/api/chanells/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/chanells/by-city-id-and-category-id/{cityId}/{categoryId}")
    public ResponseEntity<List<Chanell>> getChannelsByCityIdAndCategoryId(@PathVariable Long cityId, @PathVariable Long categoryId) {
        log.debug("REST request to get Chanells by City and Category : {} {}", cityId, categoryId);
        List<Chanell> chanells = channelService.getChannelsByCityIdAndCategoryId(cityId, categoryId);
        return new ResponseEntity<>(chanells, HttpStatus.OK);
    }
}
