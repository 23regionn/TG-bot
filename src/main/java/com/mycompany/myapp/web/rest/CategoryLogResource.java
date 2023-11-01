package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.CategoryLog;
import com.mycompany.myapp.repository.CategoryLogRepository;
import com.mycompany.myapp.service.dto.category_log.AllCategoryLogDTO;
import com.mycompany.myapp.service.statistics.CategoryLogService;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.CategoryLog}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CategoryLogResource {

    private final Logger log = LoggerFactory.getLogger(CategoryLogResource.class);

    private static final String ENTITY_NAME = "categoryLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryLogRepository categoryLogRepository;
    private final CategoryLogService categoryLogService;

    public CategoryLogResource(CategoryLogRepository categoryLogRepository, CategoryLogService categoryLogService) {
        this.categoryLogRepository = categoryLogRepository;
        this.categoryLogService = categoryLogService;
    }

    /**
     * {@code POST  /category-logs} : Create a new categoryLog.
     *
     * @param categoryLog the categoryLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryLog, or with status {@code 400 (Bad Request)} if the categoryLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/category-logs")
    public ResponseEntity<CategoryLog> createCategoryLog(@RequestBody CategoryLog categoryLog) throws URISyntaxException {
        log.debug("REST request to save CategoryLog : {}", categoryLog);
        if (categoryLog.getId() != null) {
            throw new BadRequestAlertException("A new categoryLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryLog result = categoryLogRepository.save(categoryLog);
        return ResponseEntity
            .created(new URI("/api/category-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /category-logs/:id} : Updates an existing categoryLog.
     *
     * @param id the id of the categoryLog to save.
     * @param categoryLog the categoryLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryLog,
     * or with status {@code 400 (Bad Request)} if the categoryLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/category-logs/{id}")
    public ResponseEntity<CategoryLog> updateCategoryLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoryLog categoryLog
    ) throws URISyntaxException {
        log.debug("REST request to update CategoryLog : {}, {}", id, categoryLog);
        if (categoryLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CategoryLog result = categoryLogRepository.save(categoryLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, categoryLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /category-logs/:id} : Partial updates given fields of an existing categoryLog, field will ignore if it is null
     *
     * @param id the id of the categoryLog to save.
     * @param categoryLog the categoryLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryLog,
     * or with status {@code 400 (Bad Request)} if the categoryLog is not valid,
     * or with status {@code 404 (Not Found)} if the categoryLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the categoryLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/category-logs/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<CategoryLog> partialUpdateCategoryLog(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoryLog categoryLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update CategoryLog partially : {}, {}", id, categoryLog);
        if (categoryLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CategoryLog> result = categoryLogRepository
            .findById(categoryLog.getId())
            .map(
                existingCategoryLog -> {
                    if (categoryLog.getName() != null) {
                        existingCategoryLog.setName(categoryLog.getName());
                    }

                    return existingCategoryLog;
                }
            )
            .map(categoryLogRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, categoryLog.getId().toString())
        );
    }

    /**
     * {@code GET  /category-logs} : get all the categoryLogs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryLogs in body.
     */
    @GetMapping("/category-logs")
    public List<CategoryLog> getAllCategoryLogs() {
        log.debug("REST request to get all CategoryLogs");
        return categoryLogRepository.findAll();
    }

    /**
     * {@code GET  /category-logs/:id} : get the "id" categoryLog.
     *
     * @param id the id of the categoryLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/category-logs/{id}")
    public ResponseEntity<CategoryLog> getCategoryLog(@PathVariable Long id) {
        log.debug("REST request to get CategoryLog : {}", id);
        Optional<CategoryLog> categoryLog = categoryLogRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(categoryLog);
    }

    /**
     * {@code DELETE  /category-logs/:id} : delete the "id" categoryLog.
     *
     * @param id the id of the categoryLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/category-logs/{id}")
    public ResponseEntity<Void> deleteCategoryLog(@PathVariable Long id) {
        log.debug("REST request to delete CategoryLog : {}", id);
        categoryLogRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/category-logs-test")
    public List<AllCategoryLogDTO> getAllCategoryLogsTest() {
        log.debug("REST request to get all AllCategoryLogDTO");
        return categoryLogService.getAllCategoryLogsCount();
    }
}
