package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MembersTradeDealLog;
import com.mycompany.myapp.repository.MembersTradeDealLogRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.MembersTradeDealLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MembersTradeDealLogResource {

    private final Logger log = LoggerFactory.getLogger(MembersTradeDealLogResource.class);

    private static final String ENTITY_NAME = "membersTradeDealLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MembersTradeDealLogRepository membersTradeDealLogRepository;

    public MembersTradeDealLogResource(MembersTradeDealLogRepository membersTradeDealLogRepository) {
        this.membersTradeDealLogRepository = membersTradeDealLogRepository;
    }

    /**
     * {@code POST  /members-trade-deal-logs} : Create a new membersTradeDealLog.
     *
     * @param membersTradeDealLog the membersTradeDealLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new membersTradeDealLog, or with status {@code 400 (Bad Request)} if the membersTradeDealLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/members-trade-deal-logs")
    public ResponseEntity<MembersTradeDealLog> createMembersTradeDealLog(@RequestBody MembersTradeDealLog membersTradeDealLog)
        throws URISyntaxException {
        log.debug("REST request to save MembersTradeDealLog : {}", membersTradeDealLog);
        if (membersTradeDealLog.getId() != null) {
            throw new BadRequestAlertException("A new membersTradeDealLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MembersTradeDealLog result = membersTradeDealLogRepository.save(membersTradeDealLog);
        return ResponseEntity
            .created(new URI("/api/members-trade-deal-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /members-trade-deal-logs/:id} : Updates an existing membersTradeDealLog.
     *
     * @param id the id of the membersTradeDealLog to save.
     * @param membersTradeDealLog the membersTradeDealLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated membersTradeDealLog,
     * or with status {@code 400 (Bad Request)} if the membersTradeDealLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the membersTradeDealLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/members-trade-deal-logs/{id}")
    public ResponseEntity<MembersTradeDealLog> updateMembersTradeDealLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MembersTradeDealLog membersTradeDealLog
    ) throws URISyntaxException {
        log.debug("REST request to update MembersTradeDealLog : {}, {}", id, membersTradeDealLog);
        if (membersTradeDealLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, membersTradeDealLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!membersTradeDealLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MembersTradeDealLog result = membersTradeDealLogRepository.save(membersTradeDealLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, membersTradeDealLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /members-trade-deal-logs/:id} : Partial updates given fields of an existing membersTradeDealLog, field will ignore if it is null
     *
     * @param id the id of the membersTradeDealLog to save.
     * @param membersTradeDealLog the membersTradeDealLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated membersTradeDealLog,
     * or with status {@code 400 (Bad Request)} if the membersTradeDealLog is not valid,
     * or with status {@code 404 (Not Found)} if the membersTradeDealLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the membersTradeDealLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/members-trade-deal-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<MembersTradeDealLog> partialUpdateMembersTradeDealLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MembersTradeDealLog membersTradeDealLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update MembersTradeDealLog partially : {}, {}", id, membersTradeDealLog);
        if (membersTradeDealLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, membersTradeDealLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!membersTradeDealLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MembersTradeDealLog> result = membersTradeDealLogRepository
            .findById(membersTradeDealLog.getId())
            .map(
                existingMembersTradeDealLog -> {
                    if (membersTradeDealLog.getTgUserIdCurrent() != null) {
                        existingMembersTradeDealLog.setTgUserIdCurrent(membersTradeDealLog.getTgUserIdCurrent());
                    }
                    if (membersTradeDealLog.getPriceOffer() != null) {
                        existingMembersTradeDealLog.setPriceOffer(membersTradeDealLog.getPriceOffer());
                    }
                    if (membersTradeDealLog.getCurrentDate() != null) {
                        existingMembersTradeDealLog.setCurrentDate(membersTradeDealLog.getCurrentDate());
                    }
                    if (membersTradeDealLog.getIsWinner() != null) {
                        existingMembersTradeDealLog.setIsWinner(membersTradeDealLog.getIsWinner());
                    }
                    if (membersTradeDealLog.getIsDelete() != null) {
                        existingMembersTradeDealLog.setIsDelete(membersTradeDealLog.getIsDelete());
                    }
                    if (membersTradeDealLog.getDate1() != null) {
                        existingMembersTradeDealLog.setDate1(membersTradeDealLog.getDate1());
                    }
                    if (membersTradeDealLog.getDate2() != null) {
                        existingMembersTradeDealLog.setDate2(membersTradeDealLog.getDate2());
                    }
                    if (membersTradeDealLog.getLong1() != null) {
                        existingMembersTradeDealLog.setLong1(membersTradeDealLog.getLong1());
                    }
                    if (membersTradeDealLog.getString1() != null) {
                        existingMembersTradeDealLog.setString1(membersTradeDealLog.getString1());
                    }
                    if (membersTradeDealLog.getBoolean1() != null) {
                        existingMembersTradeDealLog.setBoolean1(membersTradeDealLog.getBoolean1());
                    }

                    return existingMembersTradeDealLog;
                }
            )
            .map(membersTradeDealLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, membersTradeDealLog.getId().toString())
        );
    }

    /**
     * {@code GET  /members-trade-deal-logs} : get all the membersTradeDealLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of membersTradeDealLogs in body.
     */
    @GetMapping("/members-trade-deal-logs")
    public List<MembersTradeDealLog> getAllMembersTradeDealLogs() {
        log.debug("REST request to get all MembersTradeDealLogs");
        return membersTradeDealLogRepository.findAll();
    }

    /**
     * {@code GET  /members-trade-deal-logs/:id} : get the "id" membersTradeDealLog.
     *
     * @param id the id of the membersTradeDealLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the membersTradeDealLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/members-trade-deal-logs/{id}")
    public ResponseEntity<MembersTradeDealLog> getMembersTradeDealLog(@PathVariable Long id) {
        log.debug("REST request to get MembersTradeDealLog : {}", id);
        Optional<MembersTradeDealLog> membersTradeDealLog = membersTradeDealLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(membersTradeDealLog);
    }

    /**
     * {@code DELETE  /members-trade-deal-logs/:id} : delete the "id" membersTradeDealLog.
     *
     * @param id the id of the membersTradeDealLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/members-trade-deal-logs/{id}")
    public ResponseEntity<Void> deleteMembersTradeDealLog(@PathVariable Long id) {
        log.debug("REST request to delete MembersTradeDealLog : {}", id);
        membersTradeDealLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
