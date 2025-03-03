package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.service.utils.PaginatorCheck.*;

import com.mycompany.myapp.domain.AuditChannelsLog;
import com.mycompany.myapp.domain.Chanell;
import com.mycompany.myapp.repository.AuditChannelsLogRepository;
import com.mycompany.myapp.repository.ChanellRepository;
import com.mycompany.myapp.service.ChannelService;
import com.mycompany.myapp.service.dto.ChannelNameAndIDDTO;
import com.mycompany.myapp.service.dto.channel.ChannelInfoDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
    private final AuditChannelsLogRepository auditChannelsLogRepository;

    public ChanellResource(
        ChanellRepository chanellRepository,
        ChannelService channelService,
        AuditChannelsLogRepository auditChannelsLogRepository
    ) {
        this.chanellRepository = chanellRepository;
        this.channelService = channelService;
        this.auditChannelsLogRepository = auditChannelsLogRepository;
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
        chanell.setStartDate(ZonedDateTime.now());
        chanell.setLastPayDate(ZonedDateTime.now());
        Chanell result = chanellRepository.save(chanell);

        AuditChannelsLog audit = new AuditChannelsLog();
        audit.setComment(result.getComment());
        audit.setContacts(result.getContacts());
        audit.setCountViews(result.getCountViews());
        audit.setCountSubscribers(result.getCountSubscribers());
        audit.setLink(result.getLink());
        audit.setNameChannel(result.getName());
        audit.setIdChannel(result.getId());
        audit.setIsModerate(result.getIsModerate());
        audit.setIsPay(result.getIsPay());
        audit.setPriceForPay(result.getPriceForPay() == null ? null : String.valueOf(result.getPriceForPay()));
        audit.setStartDate(result.getStartDate());
        audit.setLastPayDate(result.getLastPayDate());
        audit.setEndPublicDate(result.getEndPublicDate());
        audit.setDateLog(LocalDate.now());
        auditChannelsLogRepository.save(audit);

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

    /*@PutMapping("/chanells/{id}")
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
    }*/
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

        AuditChannelsLog audit = new AuditChannelsLog();

        Optional<Chanell> result = chanellRepository
            .findById(chanell.getId())
            .map(
                existingChanell -> {
                    if (chanell.getName() != null) {
                        audit.setOldNameChannel(existingChanell.getName());
                        existingChanell.setName(chanell.getName());
                    }
                    if (chanell.getLink() != null) {
                        audit.setOldLink(existingChanell.getLink());
                        existingChanell.setLink(chanell.getLink());
                    }
                    if (chanell.getScore() != null) {
                        existingChanell.setScore(chanell.getScore());
                    }
                    if (chanell.getStatus() != null) {
                        existingChanell.setStatus(chanell.getStatus());
                    }
                    if (chanell.getCountSubscribers() != null) {
                        audit.setOldCountSubscribers(existingChanell.getCountSubscribers());
                        existingChanell.setCountSubscribers(chanell.getCountSubscribers());
                    }
                    if (chanell.getCountViews() != null) {
                        audit.setOldCountViews(existingChanell.getCountViews());
                        existingChanell.setCountViews(chanell.getCountViews());
                    }
                    if (chanell.getQuailityFromAnotherSources() != null) {
                        existingChanell.setQuailityFromAnotherSources(chanell.getQuailityFromAnotherSources());
                    }
                    if (chanell.getPriceDiapozon() != null) {
                        existingChanell.setPriceDiapozon(chanell.getPriceDiapozon());
                    }
                    if (chanell.getIsModerate() != null) {
                        audit.setOldIsModerate(existingChanell.getIsModerate());
                        existingChanell.setIsModerate(chanell.getIsModerate());
                    }
                    if (chanell.getShowChanellInTopByCategory() != null) {
                        existingChanell.setShowChanellInTopByCategory(chanell.getShowChanellInTopByCategory());
                    }
                    if (chanell.getIsPay() != null) {
                        audit.setOldIsPay(existingChanell.getIsPay());
                        existingChanell.setIsPay(chanell.getIsPay());
                    }
                    if (chanell.getPriceForPay() != null) {
                        audit.setOldPriceForPay(
                            existingChanell.getPriceForPay() == null ? null : String.valueOf(existingChanell.getPriceForPay())
                        );
                        existingChanell.setPriceForPay(chanell.getPriceForPay());
                    }
                    /*if (chanell.getStartDate() != null) {
                        existingChanell.setStartDate(chanell.getStartDate());
                    }
                    if (chanell.getLastPayDate() != null) {
                        existingChanell.setLastPayDate(chanell.getLastPayDate());
                    }*/
                    if (chanell.getEndPublicDate() != null) {
                        audit.setOldEndPublicDate(existingChanell.getEndPublicDate());
                        existingChanell.setEndPublicDate(chanell.getEndPublicDate());
                    }
                    if (chanell.getComment() != null) {
                        audit.setOldComment(existingChanell.getComment());
                        existingChanell.setComment(chanell.getComment());
                    }
                    if (chanell.getContacts() != null) {
                        audit.setOldContacts(existingChanell.getContacts());
                        existingChanell.setContacts(chanell.getContacts());
                    }
                    existingChanell.setLastPayDate(ZonedDateTime.now());

                    return existingChanell;
                }
            )
            .map(chanellRepository::save);

        if (result.isPresent()) {
            audit.setComment(chanell.getComment());
            audit.setContacts(chanell.getContacts());
            audit.setCountViews(chanell.getCountViews());
            audit.setCountSubscribers(chanell.getCountSubscribers());
            audit.setLink(chanell.getLink());
            audit.setNameChannel(chanell.getName());
            audit.setIdChannel(chanell.getId());
            audit.setIsModerate(chanell.getIsModerate());
            audit.setIsPay(chanell.getIsPay());
            audit.setPriceForPay(chanell.getPriceForPay() == null ? null : String.valueOf(chanell.getPriceForPay()));
            audit.setEndPublicDate(chanell.getEndPublicDate());
            audit.setDateLog(LocalDate.now());

            auditChannelsLogRepository.save(audit);
        }

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

    /**
     * {@code DELETE  /chanells/:id} : delete the "id" chanell.
     *
     * @param id the id of the chanell to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chanells/{id}")
    public ResponseEntity<Void> deleteChanell(@PathVariable Long id) {
        log.debug("REST request to delete Chanell : {}", id);
        //        chanellRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/chanells-only-id-and-name")
    public List<ChannelNameAndIDDTO> getAllChanellsNamesAndIdDTO() {
        log.debug("REST request to get all ChannelNameAndIDDTO");
        return channelService.getAllChanellsNamesAndIdDTO();
    }

    @GetMapping("/chanells-info")
    public List<ChannelInfoDTO> getAllChanellsInfoDTO() {
        log.debug("REST request to get all ChannelInfoDTO");
        return channelService.getAllChanellsInfoDTO();
    }

    @GetMapping("/channels-paginator")
    public Page<ChannelInfoDTO> getFirstPage(
        @RequestParam Optional<Integer> numberPage,
        @RequestParam Optional<Integer> countElement,
        @RequestParam Optional<String> nameColumn,
        @RequestParam Optional<Integer> optionalSort,
        @RequestParam Optional<String> name,
        @RequestParam Optional<String> link,
        @RequestParam Optional<String> lastPayDate,
        @RequestParam Optional<String> endPublicDate
        /*,
        @RequestParam Optional<String> inspectionInvIdName,
        @RequestParam Optional<String> entityHierarchyName,*/
        /*@RequestParam (required = false, defaultValue = "false") Boolean isPay,
        @RequestParam Optional<String> startDate,
        @RequestParam Optional<String> endDate*/
    ) {
        log.debug("REST request to get ChannelDTO paging");

        int page = numberOfPage(numberPage);
        int count = countElementOnPage(countElement);
        Sort sort = sortColumn(nameColumn, optionalSort, "id");

        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        LocalDate startDateSL = LocalDate.parse(
            lastPayDate.isPresent() ? lastPayDate.get() : LocalDate.ofEpochDay(365).toString(),
            lastPayDate.isPresent() ? DateTimeFormatter.ofPattern("dd.MM.yyyy") : DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );
        LocalDate startDateEL = LocalDate.parse(
            lastPayDate.isPresent() ? lastPayDate.get() : LocalDate.now().toString(),
            lastPayDate.isPresent() ? DateTimeFormatter.ofPattern("dd.MM.yyyy") : DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );
        ZonedDateTime startDateS = ZonedDateTime.of(
            startDateSL.getYear(),
            startDateSL.getMonthValue(),
            startDateSL.getDayOfMonth(),
            0,
            0,
            0,
            0,
            zoneId
        );
        ZonedDateTime startDateE = ZonedDateTime.of(
            startDateEL.getYear(),
            startDateEL.getMonthValue(),
            startDateEL.getDayOfMonth(),
            23,
            59,
            59,
            59,
            zoneId
        );
        LocalDate endDateSL = LocalDate.parse(
            endPublicDate.isPresent() ? endPublicDate.get() : LocalDate.ofEpochDay(365).toString(),
            endPublicDate.isPresent() ? DateTimeFormatter.ofPattern("dd.MM.yyyy") : DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );
        LocalDate endDateEL = LocalDate.parse(
            endPublicDate.isPresent() ? endPublicDate.get() : LocalDate.now().toString(),
            endPublicDate.isPresent() ? DateTimeFormatter.ofPattern("dd.MM.yyyy") : DateTimeFormatter.ofPattern("yyyy-MM-dd")
        );
        ZonedDateTime endDateS = ZonedDateTime.of(
            endDateSL.getYear(),
            endDateSL.getMonthValue(),
            endDateSL.getDayOfMonth(),
            0,
            0,
            0,
            0,
            zoneId
        );
        ZonedDateTime endDateE = ZonedDateTime.of(
            endDateEL.getYear(),
            endDateEL.getMonthValue(),
            endDateEL.getDayOfMonth(),
            23,
            59,
            59,
            59,
            zoneId
        );

        return channelService.findChannelInfoDTOPages(
            name.isPresent() ? name.get() : "",
            link.isPresent() ? link.get() : "",
            startDateS,
            startDateE,
            endDateS,
            endDateE,
            PageRequest.of(page, count, sort)
        );
    }
}
