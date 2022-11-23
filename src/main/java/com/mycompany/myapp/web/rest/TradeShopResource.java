package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.TradeShop;
import com.mycompany.myapp.repository.TradeShopRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TradeShop}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TradeShopResource {

    private final Logger log = LoggerFactory.getLogger(TradeShopResource.class);

    private static final String ENTITY_NAME = "tradeShop";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TradeShopRepository tradeShopRepository;

    public TradeShopResource(TradeShopRepository tradeShopRepository) {
        this.tradeShopRepository = tradeShopRepository;
    }

    /**
     * {@code POST  /trade-shops} : Create a new tradeShop.
     *
     * @param tradeShop the tradeShop to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tradeShop, or with status {@code 400 (Bad Request)} if the tradeShop has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trade-shops")
    public ResponseEntity<TradeShop> createTradeShop(@RequestBody TradeShop tradeShop) throws URISyntaxException {
        log.debug("REST request to save TradeShop : {}", tradeShop);
        if (tradeShop.getId() != null) {
            throw new BadRequestAlertException("A new tradeShop cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TradeShop result = tradeShopRepository.save(tradeShop);
        return ResponseEntity
            .created(new URI("/api/trade-shops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trade-shops/:id} : Updates an existing tradeShop.
     *
     * @param id the id of the tradeShop to save.
     * @param tradeShop the tradeShop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeShop,
     * or with status {@code 400 (Bad Request)} if the tradeShop is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tradeShop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trade-shops/{id}")
    public ResponseEntity<TradeShop> updateTradeShop(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeShop tradeShop
    ) throws URISyntaxException {
        log.debug("REST request to update TradeShop : {}, {}", id, tradeShop);
        if (tradeShop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeShop.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tradeShopRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TradeShop result = tradeShopRepository.save(tradeShop);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tradeShop.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /trade-shops/:id} : Partial updates given fields of an existing tradeShop, field will ignore if it is null
     *
     * @param id the id of the tradeShop to save.
     * @param tradeShop the tradeShop to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeShop,
     * or with status {@code 400 (Bad Request)} if the tradeShop is not valid,
     * or with status {@code 404 (Not Found)} if the tradeShop is not found,
     * or with status {@code 500 (Internal Server Error)} if the tradeShop couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/trade-shops/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TradeShop> partialUpdateTradeShop(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeShop tradeShop
    ) throws URISyntaxException {
        log.debug("REST request to partial update TradeShop partially : {}, {}", id, tradeShop);
        if (tradeShop.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeShop.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tradeShopRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TradeShop> result = tradeShopRepository
            .findById(tradeShop.getId())
            .map(
                existingTradeShop -> {
                    if (tradeShop.getCategory() != null) {
                        existingTradeShop.setCategory(tradeShop.getCategory());
                    }
                    if (tradeShop.getPriceDiapozon() != null) {
                        existingTradeShop.setPriceDiapozon(tradeShop.getPriceDiapozon());
                    }
                    if (tradeShop.getCurrentPrice() != null) {
                        existingTradeShop.setCurrentPrice(tradeShop.getCurrentPrice());
                    }
                    if (tradeShop.getWhiceLineFromAllCountLines() != null) {
                        existingTradeShop.setWhiceLineFromAllCountLines(tradeShop.getWhiceLineFromAllCountLines());
                    }
                    if (tradeShop.getTgUserIdWinner() != null) {
                        existingTradeShop.setTgUserIdWinner(tradeShop.getTgUserIdWinner());
                    }
                    if (tradeShop.getInWhatDateWillPostThisLinks() != null) {
                        existingTradeShop.setInWhatDateWillPostThisLinks(tradeShop.getInWhatDateWillPostThisLinks());
                    }
                    if (tradeShop.getDateFinishTorgs() != null) {
                        existingTradeShop.setDateFinishTorgs(tradeShop.getDateFinishTorgs());
                    }
                    if (tradeShop.getIsDelete() != null) {
                        existingTradeShop.setIsDelete(tradeShop.getIsDelete());
                    }
                    if (tradeShop.getDate1() != null) {
                        existingTradeShop.setDate1(tradeShop.getDate1());
                    }
                    if (tradeShop.getDate2() != null) {
                        existingTradeShop.setDate2(tradeShop.getDate2());
                    }
                    if (tradeShop.getLong1() != null) {
                        existingTradeShop.setLong1(tradeShop.getLong1());
                    }
                    if (tradeShop.getString1() != null) {
                        existingTradeShop.setString1(tradeShop.getString1());
                    }
                    if (tradeShop.getBoolean1() != null) {
                        existingTradeShop.setBoolean1(tradeShop.getBoolean1());
                    }

                    return existingTradeShop;
                }
            )
            .map(tradeShopRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tradeShop.getId().toString())
        );
    }

    /**
     * {@code GET  /trade-shops} : get all the tradeShops.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tradeShops in body.
     */
    @GetMapping("/trade-shops")
    public List<TradeShop> getAllTradeShops() {
        log.debug("REST request to get all TradeShops");
        return tradeShopRepository.findAll();
    }

    /**
     * {@code GET  /trade-shops/:id} : get the "id" tradeShop.
     *
     * @param id the id of the tradeShop to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tradeShop, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trade-shops/{id}")
    public ResponseEntity<TradeShop> getTradeShop(@PathVariable Long id) {
        log.debug("REST request to get TradeShop : {}", id);
        Optional<TradeShop> tradeShop = tradeShopRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tradeShop);
    }

    /**
     * {@code DELETE  /trade-shops/:id} : delete the "id" tradeShop.
     *
     * @param id the id of the tradeShop to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trade-shops/{id}")
    public ResponseEntity<Void> deleteTradeShop(@PathVariable Long id) {
        log.debug("REST request to delete TradeShop : {}", id);
        tradeShopRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
