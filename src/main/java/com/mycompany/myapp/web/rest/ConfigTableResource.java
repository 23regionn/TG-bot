package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ConfigTable;
import com.mycompany.myapp.repository.ConfigTableRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ConfigTable}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ConfigTableResource {

    private final Logger log = LoggerFactory.getLogger(ConfigTableResource.class);

    private static final String ENTITY_NAME = "configTable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConfigTableRepository configTableRepository;

    public ConfigTableResource(ConfigTableRepository configTableRepository) {
        this.configTableRepository = configTableRepository;
    }

    /**
     * {@code POST  /config-tables} : Create a new configTable.
     *
     * @param configTable the configTable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new configTable, or with status {@code 400 (Bad Request)} if the configTable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/config-tables")
    public ResponseEntity<ConfigTable> createConfigTable(@RequestBody ConfigTable configTable) throws URISyntaxException {
        log.debug("REST request to save ConfigTable : {}", configTable);
        if (configTable.getId() != null) {
            throw new BadRequestAlertException("A new configTable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConfigTable result = configTableRepository.save(configTable);
        return ResponseEntity
            .created(new URI("/api/config-tables/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /config-tables/:id} : Updates an existing configTable.
     *
     * @param id the id of the configTable to save.
     * @param configTable the configTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated configTable,
     * or with status {@code 400 (Bad Request)} if the configTable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the configTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/config-tables/{id}")
    public ResponseEntity<ConfigTable> updateConfigTable(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ConfigTable configTable
    ) throws URISyntaxException {
        log.debug("REST request to update ConfigTable : {}, {}", id, configTable);
        if (configTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, configTable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!configTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ConfigTable result = configTableRepository.save(configTable);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, configTable.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /config-tables/:id} : Partial updates given fields of an existing configTable, field will ignore if it is null
     *
     * @param id the id of the configTable to save.
     * @param configTable the configTable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated configTable,
     * or with status {@code 400 (Bad Request)} if the configTable is not valid,
     * or with status {@code 404 (Not Found)} if the configTable is not found,
     * or with status {@code 500 (Internal Server Error)} if the configTable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/config-tables/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ConfigTable> partialUpdateConfigTable(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ConfigTable configTable
    ) throws URISyntaxException {
        log.debug("REST request to partial update ConfigTable partially : {}, {}", id, configTable);
        if (configTable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, configTable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!configTableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ConfigTable> result = configTableRepository
            .findById(configTable.getId())
            .map(
                existingConfigTable -> {
                    if (configTable.getDateOne() != null) {
                        existingConfigTable.setDateOne(configTable.getDateOne());
                    }
                    if (configTable.getDateTwo() != null) {
                        existingConfigTable.setDateTwo(configTable.getDateTwo());
                    }
                    if (configTable.getLongOne() != null) {
                        existingConfigTable.setLongOne(configTable.getLongOne());
                    }
                    if (configTable.getStringOne() != null) {
                        existingConfigTable.setStringOne(configTable.getStringOne());
                    }
                    if (configTable.getBooleanOne() != null) {
                        existingConfigTable.setBooleanOne(configTable.getBooleanOne());
                    }
                    if (configTable.getBooleanTwo() != null) {
                        existingConfigTable.setBooleanTwo(configTable.getBooleanTwo());
                    }
                    if (configTable.getDate1() != null) {
                        existingConfigTable.setDate1(configTable.getDate1());
                    }
                    if (configTable.getDate2() != null) {
                        existingConfigTable.setDate2(configTable.getDate2());
                    }
                    if (configTable.getLong1() != null) {
                        existingConfigTable.setLong1(configTable.getLong1());
                    }
                    if (configTable.getString1() != null) {
                        existingConfigTable.setString1(configTable.getString1());
                    }
                    if (configTable.getBoolean1() != null) {
                        existingConfigTable.setBoolean1(configTable.getBoolean1());
                    }

                    return existingConfigTable;
                }
            )
            .map(configTableRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, configTable.getId().toString())
        );
    }

    /**
     * {@code GET  /config-tables} : get all the configTables.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of configTables in body.
     */
    @GetMapping("/config-tables")
    public List<ConfigTable> getAllConfigTables() {
        log.debug("REST request to get all ConfigTables");
        return configTableRepository.findAll();
    }

    /**
     * {@code GET  /config-tables/:id} : get the "id" configTable.
     *
     * @param id the id of the configTable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the configTable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/config-tables/{id}")
    public ResponseEntity<ConfigTable> getConfigTable(@PathVariable Long id) {
        log.debug("REST request to get ConfigTable : {}", id);
        Optional<ConfigTable> configTable = configTableRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(configTable);
    }

    /**
     * {@code DELETE  /config-tables/:id} : delete the "id" configTable.
     *
     * @param id the id of the configTable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/config-tables/{id}")
    public ResponseEntity<Void> deleteConfigTable(@PathVariable Long id) {
        log.debug("REST request to delete ConfigTable : {}", id);
        configTableRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
