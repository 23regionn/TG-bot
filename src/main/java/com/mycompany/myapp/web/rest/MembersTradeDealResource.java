package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MembersTradeDeal;
import com.mycompany.myapp.repository.MembersTradeDealRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.MembersTradeDeal}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MembersTradeDealResource {

    private final Logger log = LoggerFactory.getLogger(MembersTradeDealResource.class);

    private static final String ENTITY_NAME = "membersTradeDeal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MembersTradeDealRepository membersTradeDealRepository;

    public MembersTradeDealResource(MembersTradeDealRepository membersTradeDealRepository) {
        this.membersTradeDealRepository = membersTradeDealRepository;
    }

    /**
     * {@code POST  /members-trade-deals} : Create a new membersTradeDeal.
     *
     * @param membersTradeDeal the membersTradeDeal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new membersTradeDeal, or with status {@code 400 (Bad Request)} if the membersTradeDeal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/members-trade-deals")
    public ResponseEntity<MembersTradeDeal> createMembersTradeDeal(@RequestBody MembersTradeDeal membersTradeDeal)
        throws URISyntaxException {
        log.debug("REST request to save MembersTradeDeal : {}", membersTradeDeal);
        if (membersTradeDeal.getId() != null) {
            throw new BadRequestAlertException("A new membersTradeDeal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MembersTradeDeal result = membersTradeDealRepository.save(membersTradeDeal);
        return ResponseEntity
            .created(new URI("/api/members-trade-deals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /members-trade-deals/:id} : Updates an existing membersTradeDeal.
     *
     * @param id the id of the membersTradeDeal to save.
     * @param membersTradeDeal the membersTradeDeal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated membersTradeDeal,
     * or with status {@code 400 (Bad Request)} if the membersTradeDeal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the membersTradeDeal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/members-trade-deals/{id}")
    public ResponseEntity<MembersTradeDeal> updateMembersTradeDeal(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MembersTradeDeal membersTradeDeal
    ) throws URISyntaxException {
        log.debug("REST request to update MembersTradeDeal : {}, {}", id, membersTradeDeal);
        if (membersTradeDeal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, membersTradeDeal.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!membersTradeDealRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MembersTradeDeal result = membersTradeDealRepository.save(membersTradeDeal);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, membersTradeDeal.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /members-trade-deals/:id} : Partial updates given fields of an existing membersTradeDeal, field will ignore if it is null
     *
     * @param id the id of the membersTradeDeal to save.
     * @param membersTradeDeal the membersTradeDeal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated membersTradeDeal,
     * or with status {@code 400 (Bad Request)} if the membersTradeDeal is not valid,
     * or with status {@code 404 (Not Found)} if the membersTradeDeal is not found,
     * or with status {@code 500 (Internal Server Error)} if the membersTradeDeal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/members-trade-deals/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<MembersTradeDeal> partialUpdateMembersTradeDeal(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MembersTradeDeal membersTradeDeal
    ) throws URISyntaxException {
        log.debug("REST request to partial update MembersTradeDeal partially : {}, {}", id, membersTradeDeal);
        if (membersTradeDeal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, membersTradeDeal.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!membersTradeDealRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MembersTradeDeal> result = membersTradeDealRepository
            .findById(membersTradeDeal.getId())
            .map(
                existingMembersTradeDeal -> {
                    if (membersTradeDeal.getTgUserIdCurrent() != null) {
                        existingMembersTradeDeal.setTgUserIdCurrent(membersTradeDeal.getTgUserIdCurrent());
                    }
                    if (membersTradeDeal.getPriceOffer() != null) {
                        existingMembersTradeDeal.setPriceOffer(membersTradeDeal.getPriceOffer());
                    }
                    if (membersTradeDeal.getCurrentDate() != null) {
                        existingMembersTradeDeal.setCurrentDate(membersTradeDeal.getCurrentDate());
                    }
                    if (membersTradeDeal.getIsWinner() != null) {
                        existingMembersTradeDeal.setIsWinner(membersTradeDeal.getIsWinner());
                    }
                    if (membersTradeDeal.getIsDelete() != null) {
                        existingMembersTradeDeal.setIsDelete(membersTradeDeal.getIsDelete());
                    }
                    if (membersTradeDeal.getDate1() != null) {
                        existingMembersTradeDeal.setDate1(membersTradeDeal.getDate1());
                    }
                    if (membersTradeDeal.getDate2() != null) {
                        existingMembersTradeDeal.setDate2(membersTradeDeal.getDate2());
                    }
                    if (membersTradeDeal.getLong1() != null) {
                        existingMembersTradeDeal.setLong1(membersTradeDeal.getLong1());
                    }
                    if (membersTradeDeal.getString1() != null) {
                        existingMembersTradeDeal.setString1(membersTradeDeal.getString1());
                    }
                    if (membersTradeDeal.getBoolean1() != null) {
                        existingMembersTradeDeal.setBoolean1(membersTradeDeal.getBoolean1());
                    }

                    return existingMembersTradeDeal;
                }
            )
            .map(membersTradeDealRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, membersTradeDeal.getId().toString())
        );
    }

    /**
     * {@code GET  /members-trade-deals} : get all the membersTradeDeals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of membersTradeDeals in body.
     */
    @GetMapping("/members-trade-deals")
    public List<MembersTradeDeal> getAllMembersTradeDeals() {
        log.debug("REST request to get all MembersTradeDeals");
        return membersTradeDealRepository.findAll();
    }

    /**
     * {@code GET  /members-trade-deals/:id} : get the "id" membersTradeDeal.
     *
     * @param id the id of the membersTradeDeal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the membersTradeDeal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/members-trade-deals/{id}")
    public ResponseEntity<MembersTradeDeal> getMembersTradeDeal(@PathVariable Long id) {
        log.debug("REST request to get MembersTradeDeal : {}", id);
        Optional<MembersTradeDeal> membersTradeDeal = membersTradeDealRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(membersTradeDeal);
    }

    /**
     * {@code DELETE  /members-trade-deals/:id} : delete the "id" membersTradeDeal.
     *
     * @param id the id of the membersTradeDeal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/members-trade-deals/{id}")
    public ResponseEntity<Void> deleteMembersTradeDeal(@PathVariable Long id) {
        log.debug("REST request to delete MembersTradeDeal : {}", id);
        membersTradeDealRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
