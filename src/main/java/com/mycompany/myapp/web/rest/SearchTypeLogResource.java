package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.SearchTypeLog;
import com.mycompany.myapp.repository.SearchTypeLogRepository;
import com.mycompany.myapp.service.SearchTypeLogService;
import com.mycompany.myapp.service.dto.statistics.StatisticsByPageNumberCountDTO;
import com.mycompany.myapp.service.dto.statistics.StatisticsBySearchTypesDTO;
import com.mycompany.myapp.service.dto.tgUsers.SearchAnyByDatesDTO;
import com.mycompany.myapp.service.dto.tgUsers.SearchTypeCountDTO;
import com.mycompany.myapp.service.dto.tgUsers.TgUsersCountDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.SearchTypeLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SearchTypeLogResource {

    private final Logger log = LoggerFactory.getLogger(SearchTypeLogResource.class);

    private static final String ENTITY_NAME = "searchTypeLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SearchTypeLogRepository searchTypeLogRepository;
    private final SearchTypeLogService searchTypeLogService;

    public SearchTypeLogResource(SearchTypeLogRepository searchTypeLogRepository, SearchTypeLogService searchTypeLogService) {
        this.searchTypeLogRepository = searchTypeLogRepository;
        this.searchTypeLogService = searchTypeLogService;
    }

    /**
     * {@code POST  /search-type-logs} : Create a new searchTypeLog.
     *
     * @param searchTypeLog the searchTypeLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new searchTypeLog, or with status {@code 400 (Bad Request)} if the searchTypeLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/search-type-logs")
    public ResponseEntity<SearchTypeLog> createSearchTypeLog(@RequestBody SearchTypeLog searchTypeLog) throws URISyntaxException {
        log.debug("REST request to save SearchTypeLog : {}", searchTypeLog);
        if (searchTypeLog.getId() != null) {
            throw new BadRequestAlertException("A new searchTypeLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SearchTypeLog result = searchTypeLogRepository.save(searchTypeLog);
        return ResponseEntity
            .created(new URI("/api/search-type-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /search-type-logs/:id} : Updates an existing searchTypeLog.
     *
     * @param id the id of the searchTypeLog to save.
     * @param searchTypeLog the searchTypeLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated searchTypeLog,
     * or with status {@code 400 (Bad Request)} if the searchTypeLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the searchTypeLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/search-type-logs/{id}")
    public ResponseEntity<SearchTypeLog> updateSearchTypeLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SearchTypeLog searchTypeLog
    ) throws URISyntaxException {
        log.debug("REST request to update SearchTypeLog : {}, {}", id, searchTypeLog);
        if (searchTypeLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, searchTypeLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!searchTypeLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SearchTypeLog result = searchTypeLogRepository.save(searchTypeLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, searchTypeLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /search-type-logs/:id} : Partial updates given fields of an existing searchTypeLog, field will ignore if it is null
     *
     * @param id the id of the searchTypeLog to save.
     * @param searchTypeLog the searchTypeLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated searchTypeLog,
     * or with status {@code 400 (Bad Request)} if the searchTypeLog is not valid,
     * or with status {@code 404 (Not Found)} if the searchTypeLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the searchTypeLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/search-type-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<SearchTypeLog> partialUpdateSearchTypeLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SearchTypeLog searchTypeLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update SearchTypeLog partially : {}, {}", id, searchTypeLog);
        if (searchTypeLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, searchTypeLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!searchTypeLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SearchTypeLog> result = searchTypeLogRepository
            .findById(searchTypeLog.getId())
            .map(
                existingSearchTypeLog -> {
                    if (searchTypeLog.getChatId() != null) {
                        existingSearchTypeLog.setChatId(searchTypeLog.getChatId());
                    }
                    if (searchTypeLog.getDateLog() != null) {
                        existingSearchTypeLog.setDateLog(searchTypeLog.getDateLog());
                    }
                    if (searchTypeLog.getInlineSearch() != null) {
                        existingSearchTypeLog.setInlineSearch(searchTypeLog.getInlineSearch());
                    }
                    if (searchTypeLog.getPageSearch() != null) {
                        existingSearchTypeLog.setPageSearch(searchTypeLog.getPageSearch());
                    }
                    if (searchTypeLog.getPageNumber() != null) {
                        existingSearchTypeLog.setPageNumber(searchTypeLog.getPageNumber());
                    }
                    if (searchTypeLog.getIdCity() != null) {
                        existingSearchTypeLog.setIdCity(searchTypeLog.getIdCity());
                    }

                    return existingSearchTypeLog;
                }
            )
            .map(searchTypeLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, searchTypeLog.getId().toString())
        );
    }

    /**
     * {@code GET  /search-type-logs} : get all the searchTypeLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of searchTypeLogs in body.
     */
    @GetMapping("/search-type-logs")
    public List<SearchTypeLog> getAllSearchTypeLogs() {
        log.debug("REST request to get all SearchTypeLogs");
        return searchTypeLogRepository.findAll();
    }

    /**
     * {@code GET  /search-type-logs/:id} : get the "id" searchTypeLog.
     *
     * @param id the id of the searchTypeLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the searchTypeLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/search-type-logs/{id}")
    public ResponseEntity<SearchTypeLog> getSearchTypeLog(@PathVariable Long id) {
        log.debug("REST request to get SearchTypeLog : {}", id);
        Optional<SearchTypeLog> searchTypeLog = searchTypeLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(searchTypeLog);
    }

    /**
     * {@code DELETE  /search-type-logs/:id} : delete the "id" searchTypeLog.
     *
     * @param id the id of the searchTypeLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/search-type-logs/{id}")
    public ResponseEntity<Void> deleteSearchTypeLog(@PathVariable Long id) {
        log.debug("REST request to delete SearchTypeLog : {}", id);
        searchTypeLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/search-type-logs/count")
    public ResponseEntity<Long> getSearchTypeLogsCount() {
        log.debug("REST request to get all SearchTypeLogs count");
        Long count = searchTypeLogService.searchTypeLogCount();
        return ResponseEntity.ok(count);
    }

    @PostMapping("/search-type-logs/count/by-dates")
    public ResponseEntity<SearchTypeCountDTO> getSearchTypeLogsByDates(@RequestBody SearchAnyByDatesDTO request) {
        log.debug("REST request to get SearchAnyByDatesDTO : {}");
        SearchTypeCountDTO countsByDates = searchTypeLogService.getCountByDates(request);
        return ResponseEntity.ok(countsByDates);
    }

    @GetMapping("/search-type-logs/detail")
    public ResponseEntity<StatisticsBySearchTypesDTO> searchTypeLogCountByTypeRequests() {
        log.debug("REST request to get all SearchTypeLogs detail by typeRequests");
        StatisticsBySearchTypesDTO count = searchTypeLogService.searchTypeLogDetail();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/search-type-logs/detail/page-number")
    public ResponseEntity<List<StatisticsByPageNumberCountDTO>> searchTypeLogCountByPageNumber() {
        log.debug("REST request to get all SearchTypeLogs detail by typeRequests");
        return ResponseEntity.ok(searchTypeLogService.searchTypeLogCountByPageNumber());
    }

    @PostMapping("/search-type-logs/inline/count/by-dates")
    public ResponseEntity<SearchTypeCountDTO> getSearchTypeInlineRequestCountByDates(@RequestBody SearchAnyByDatesDTO request) {
        log.debug("REST request to get count inlineRequest SearchAnyByDatesDTO : {}");
        SearchTypeCountDTO countsByDates = searchTypeLogService.getSearchTypeInlineRequestCountByDates(request);
        return ResponseEntity.ok(countsByDates);
    }

    @PostMapping("/search-type-logs/pages-category/count/by-dates")
    public ResponseEntity<SearchTypeCountDTO> getSearchTypePagesCategoryRequestCountByDates(@RequestBody SearchAnyByDatesDTO request) {
        log.debug("REST request to get count pages-category SearchAnyByDatesDTO : {}");
        SearchTypeCountDTO countsByDates = searchTypeLogService.getSearchTypePagesCategoryRequestCountByDates(request);
        return ResponseEntity.ok(countsByDates);
    }

    @PostMapping("/search-type-logs/pages-category/for-city/count/by-dates")
    public ResponseEntity<SearchTypeCountDTO> getSearchTypePagesCategoryRequestCountForCityByDates(
        @RequestBody SearchAnyByDatesDTO request
    ) {
        log.debug("REST request to get count pages-category for-city  SearchAnyByDatesDTO : {}");
        SearchTypeCountDTO countsByDates = searchTypeLogService.getSearchTypePagesCategoryRequestCountForCityByDates(request);
        return ResponseEntity.ok(countsByDates);
    }

    @PostMapping("/search-type-logs/detail/page-number/by-dates")
    public ResponseEntity<List<StatisticsByPageNumberCountDTO>> searchTypeLogCountByPageNumberByDates(
        @RequestBody SearchAnyByDatesDTO request
    ) {
        log.debug("REST request to get count page-number by-dates SearchAnyByDatesDTO : {}");
        return ResponseEntity.ok(searchTypeLogService.searchTypeLogCountByPageNumberByDates(request));
    }
}
