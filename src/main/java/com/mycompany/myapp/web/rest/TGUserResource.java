package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.service.utils.PaginatorCheck.*;

import com.mycompany.myapp.domain.TGUser;
import com.mycompany.myapp.repository.TGUserRepository;
import com.mycompany.myapp.service.TgUserService;
import com.mycompany.myapp.service.dto.tgUsers.SearchAnyByDatesDTO;
import com.mycompany.myapp.service.dto.tgUsers.StatisticsTgUserDTO;
import com.mycompany.myapp.service.dto.tgUsers.TgUserDTO;
import com.mycompany.myapp.service.dto.tgUsers.TgUsersCountDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.TGUser}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TGUserResource {

    private final Logger log = LoggerFactory.getLogger(TGUserResource.class);

    private static final String ENTITY_NAME = "tGUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TGUserRepository tGUserRepository;
    private final TgUserService tgUserService;

    public TGUserResource(TGUserRepository tGUserRepository, TgUserService tgUserService) {
        this.tGUserRepository = tGUserRepository;
        this.tgUserService = tgUserService;
    }

    /**
     * {@code POST  /tg-users} : Create a new tGUser.
     *
     * @param tGUser the tGUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tGUser, or with status {@code 400 (Bad Request)} if the tGUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tg-users")
    public ResponseEntity<TGUser> createTGUser(@RequestBody TGUser tGUser) throws URISyntaxException {
        log.debug("REST request to save TGUser : {}", tGUser);
        if (tGUser.getId() != null) {
            throw new BadRequestAlertException("A new tGUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TGUser result = tGUserRepository.save(tGUser);
        return ResponseEntity
            .created(new URI("/api/tg-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tg-users/:id} : Updates an existing tGUser.
     *
     * @param id the id of the tGUser to save.
     * @param tGUser the tGUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tGUser,
     * or with status {@code 400 (Bad Request)} if the tGUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tGUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tg-users/{id}")
    public ResponseEntity<TGUser> updateTGUser(@PathVariable(value = "id", required = false) final Long id, @RequestBody TGUser tGUser)
        throws URISyntaxException {
        log.debug("REST request to update TGUser : {}, {}", id, tGUser);
        if (tGUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tGUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tGUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TGUser result = tGUserRepository.save(tGUser);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tGUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tg-users/:id} : Partial updates given fields of an existing tGUser, field will ignore if it is null
     *
     * @param id the id of the tGUser to save.
     * @param tGUser the tGUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tGUser,
     * or with status {@code 400 (Bad Request)} if the tGUser is not valid,
     * or with status {@code 404 (Not Found)} if the tGUser is not found,
     * or with status {@code 500 (Internal Server Error)} if the tGUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tg-users/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TGUser> partialUpdateTGUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TGUser tGUser
    ) throws URISyntaxException {
        log.debug("REST request to partial update TGUser partially : {}, {}", id, tGUser);
        if (tGUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tGUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tGUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TGUser> result = tGUserRepository
            .findById(tGUser.getId())
            .map(
                existingTGUser -> {
                    if (tGUser.getFirstName() != null) {
                        existingTGUser.setFirstName(tGUser.getFirstName());
                    }
                    if (tGUser.getRegistrationDate() != null) {
                        existingTGUser.setRegistrationDate(tGUser.getRegistrationDate());
                    }
                    if (tGUser.getUserRole() != null) {
                        existingTGUser.setUserRole(tGUser.getUserRole());
                    }
                    if (tGUser.getIsAdmin() != null) {
                        existingTGUser.setIsAdmin(tGUser.getIsAdmin());
                    }
                    if (tGUser.getScore() != null) {
                        existingTGUser.setScore(tGUser.getScore());
                    }
                    if (tGUser.getIsBlocked() != null) {
                        existingTGUser.setIsBlocked(tGUser.getIsBlocked());
                    }
                    if (tGUser.getChatId() != null) {
                        existingTGUser.setChatId(tGUser.getChatId());
                    }
                    if (tGUser.getIsDelete() != null) {
                        existingTGUser.setIsDelete(tGUser.getIsDelete());
                    }
                    return existingTGUser;
                }
            )
            .map(tGUserRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tGUser.getId().toString())
        );
    }

    /**
     * {@code GET  /tg-users} : get all the tGUsers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tGUsers in body.
     */
    @GetMapping("/tg-users")
    public ResponseEntity<List<TgUserDTO>> getAllTGUsers() {
        log.debug("REST request to get all TGUsers");
        return ResponseEntity.ok(tgUserService.getAllTgUsers());
    }

    @GetMapping("/tg-users-paginator")
    public Page<TgUserDTO> getTgUsersPage(
        @RequestParam Optional<Integer> numberPage,
        @RequestParam Optional<Integer> countElement,
        @RequestParam Optional<String> nameColumn,
        @RequestParam Optional<Integer> optionalSort,
        @RequestParam Optional<String> firstName,
        @RequestParam Optional<String> userName,
        @RequestParam Optional<String> registrationDate
    ) {
        log.debug("REST request to get TGUserDTO paging");

        int page = numberOfPage(numberPage);
        int count = countElementOnPage(countElement);
        Sort sort = sortColumn(nameColumn, optionalSort, "id");
        ZoneId zoneId = ZoneId.of("Europe/Moscow");

        // фильтр по registrationDate
        ZonedDateTime startDate;
        ZonedDateTime endDate;
        if (registrationDate.isPresent() && !registrationDate.get().isEmpty()) {
            LocalDate date = LocalDate.parse(registrationDate.get(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            startDate = date.atStartOfDay(zoneId);
            endDate = date.atTime(23, 59, 59).atZone(zoneId);
        } else {
            startDate = ZonedDateTime.parse("0001-01-01T00:00:00Z");
            endDate = ZonedDateTime.parse("9999-12-31T23:59:59Z");
        }

        return tgUserService.findTgUserDTOPages(
            firstName.orElse(""),
            userName.orElse(""),
            startDate,
            endDate,
            PageRequest.of(page, count, sort)
        );
    }

    /**
     * {@code GET  /tg-users/:id} : get the "id" tGUser.
     *
     * @param id the id of the tGUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tGUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tg-users/{id}")
    public ResponseEntity<TGUser> getTGUser(@PathVariable Long id) {
        log.debug("REST request to get TGUser : {}", id);
        Optional<TGUser> tGUser = tGUserRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tGUser);
    }

    /**
     * {@code DELETE  /tg-users/:id} : delete the "id" tGUser.
     *
     * @param id the id of the tGUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tg-users/{id}")
    public ResponseEntity<Void> deleteTGUser(@PathVariable Long id) {
        log.debug("REST request to delete TGUser : {}", id);
        tGUserRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/tg-users/count")
    public ResponseEntity<Long> getTGUserCount() {
        log.debug("REST request to get TGUser count ");
        Long count = tgUserService.getTGUserCount();
        return ResponseEntity.ok(count);
    }

    @PostMapping("/tg-users/count/by-dates")
    public ResponseEntity<TgUsersCountDTO> getCountSubscribersByDates(@RequestBody SearchAnyByDatesDTO request) {
        log.debug("REST request to get SearchAnyByDatesDTO : {}");
        TgUsersCountDTO countsByDates = tgUserService.getTGUserCountByDates(request);
        return ResponseEntity.ok(countsByDates);
    }

    @GetMapping("/tg-users/base-statistics")
    public ResponseEntity<List<StatisticsTgUserDTO>> getAllTgUsersForStatistics() {
        log.debug("REST request to get getAllTgUsersForStatistics ");
        return ResponseEntity.ok(tgUserService.getAllTgUsersForStatistics());
    }
}
