package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.EditChannels;
import com.mycompany.myapp.repository.EditChannelsRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.EditChannels}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class EditChannelsResource {

    private final Logger log = LoggerFactory.getLogger(EditChannelsResource.class);

    private static final String ENTITY_NAME = "editChannels";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EditChannelsRepository editChannelsRepository;

    public EditChannelsResource(EditChannelsRepository editChannelsRepository) {
        this.editChannelsRepository = editChannelsRepository;
    }

    /**
     * {@code POST  /edit-channels} : Create a new editChannels.
     *
     * @param editChannels the editChannels to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new editChannels, or with status {@code 400 (Bad Request)} if the editChannels has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/edit-channels")
    public ResponseEntity<EditChannels> createEditChannels(@RequestBody EditChannels editChannels) throws URISyntaxException {
        log.debug("REST request to save EditChannels : {}", editChannels);
        if (editChannels.getId() != null) {
            throw new BadRequestAlertException("A new editChannels cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EditChannels result = editChannelsRepository.save(editChannels);
        return ResponseEntity
            .created(new URI("/api/edit-channels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /edit-channels/:id} : Updates an existing editChannels.
     *
     * @param id the id of the editChannels to save.
     * @param editChannels the editChannels to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated editChannels,
     * or with status {@code 400 (Bad Request)} if the editChannels is not valid,
     * or with status {@code 500 (Internal Server Error)} if the editChannels couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/edit-channels/{id}")
    public ResponseEntity<EditChannels> updateEditChannels(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EditChannels editChannels
    ) throws URISyntaxException {
        log.debug("REST request to update EditChannels : {}, {}", id, editChannels);
        if (editChannels.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, editChannels.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!editChannelsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EditChannels result = editChannelsRepository.save(editChannels);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, editChannels.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /edit-channels/:id} : Partial updates given fields of an existing editChannels, field will ignore if it is null
     *
     * @param id the id of the editChannels to save.
     * @param editChannels the editChannels to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated editChannels,
     * or with status {@code 400 (Bad Request)} if the editChannels is not valid,
     * or with status {@code 404 (Not Found)} if the editChannels is not found,
     * or with status {@code 500 (Internal Server Error)} if the editChannels couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/edit-channels/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<EditChannels> partialUpdateEditChannels(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EditChannels editChannels
    ) throws URISyntaxException {
        log.debug("REST request to partial update EditChannels partially : {}, {}", id, editChannels);
        if (editChannels.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, editChannels.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!editChannelsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EditChannels> result = editChannelsRepository
            .findById(editChannels.getId())
            .map(
                existingEditChannels -> {
                    if (editChannels.getIdMessage() != null) {
                        existingEditChannels.setIdMessage(editChannels.getIdMessage());
                    }
                    if (editChannels.getIdChannel() != null) {
                        existingEditChannels.setIdChannel(editChannels.getIdChannel());
                    }
                    if (editChannels.getDateCreateMessage() != null) {
                        existingEditChannels.setDateCreateMessage(editChannels.getDateCreateMessage());
                    }
                    if (editChannels.getLastNameChannel() != null) {
                        existingEditChannels.setLastNameChannel(editChannels.getLastNameChannel());
                    }
                    if (editChannels.getNewNameChannel() != null) {
                        existingEditChannels.setNewNameChannel(editChannels.getNewNameChannel());
                    }
                    if (editChannels.getIsAproveChange() != null) {
                        existingEditChannels.setIsAproveChange(editChannels.getIsAproveChange());
                    }
                    if (editChannels.getLastLinkToChannel() != null) {
                        existingEditChannels.setLastLinkToChannel(editChannels.getLastLinkToChannel());
                    }
                    if (editChannels.getNewlastLinkToChannel() != null) {
                        existingEditChannels.setNewlastLinkToChannel(editChannels.getNewlastLinkToChannel());
                    }
                    if (editChannels.getLastPriceChannel() != null) {
                        existingEditChannels.setLastPriceChannel(editChannels.getLastPriceChannel());
                    }
                    if (editChannels.getNewPriceChannel() != null) {
                        existingEditChannels.setNewPriceChannel(editChannels.getNewPriceChannel());
                    }
                    if (editChannels.getAddDescriptionAboutChannel() != null) {
                        existingEditChannels.setAddDescriptionAboutChannel(editChannels.getAddDescriptionAboutChannel());
                    }
                    if (editChannels.getCurrentDescriptionChannel() != null) {
                        existingEditChannels.setCurrentDescriptionChannel(editChannels.getCurrentDescriptionChannel());
                    }
                    if (editChannels.getAddRegionChannel() != null) {
                        existingEditChannels.setAddRegionChannel(editChannels.getAddRegionChannel());
                    }
                    if (editChannels.getEditRegionChannel() != null) {
                        existingEditChannels.setEditRegionChannel(editChannels.getEditRegionChannel());
                    }
                    if (editChannels.getAddCityChannel() != null) {
                        existingEditChannels.setAddCityChannel(editChannels.getAddCityChannel());
                    }
                    if (editChannels.getEditCityChannel() != null) {
                        existingEditChannels.setEditCityChannel(editChannels.getEditCityChannel());
                    }
                    if (editChannels.getUserId() != null) {
                        existingEditChannels.setUserId(editChannels.getUserId());
                    }
                    if (editChannels.getUserName() != null) {
                        existingEditChannels.setUserName(editChannels.getUserName());
                    }
                    if (editChannels.getIsApprovedChanhes() != null) {
                        existingEditChannels.setIsApprovedChanhes(editChannels.getIsApprovedChanhes());
                    }
                    if (editChannels.getComment() != null) {
                        existingEditChannels.setComment(editChannels.getComment());
                    }
                    if (editChannels.getStatus() != null) {
                        existingEditChannels.setStatus(editChannels.getStatus());
                    }
                    if (editChannels.getServiceField1() != null) {
                        existingEditChannels.setServiceField1(editChannels.getServiceField1());
                    }
                    if (editChannels.getServiceField2() != null) {
                        existingEditChannels.setServiceField2(editChannels.getServiceField2());
                    }
                    if (editChannels.getServiceField3() != null) {
                        existingEditChannels.setServiceField3(editChannels.getServiceField3());
                    }

                    return existingEditChannels;
                }
            )
            .map(editChannelsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, editChannels.getId().toString())
        );
    }

    /**
     * {@code GET  /edit-channels} : get all the editChannels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of editChannels in body.
     */
    @GetMapping("/edit-channels")
    public List<EditChannels> getAllEditChannels() {
        log.debug("REST request to get all EditChannels");
        return editChannelsRepository.findAll();
    }

    /**
     * {@code GET  /edit-channels/:id} : get the "id" editChannels.
     *
     * @param id the id of the editChannels to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the editChannels, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/edit-channels/{id}")
    public ResponseEntity<EditChannels> getEditChannels(@PathVariable Long id) {
        log.debug("REST request to get EditChannels : {}", id);
        Optional<EditChannels> editChannels = editChannelsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(editChannels);
    }

    /**
     * {@code DELETE  /edit-channels/:id} : delete the "id" editChannels.
     *
     * @param id the id of the editChannels to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/edit-channels/{id}")
    public ResponseEntity<Void> deleteEditChannels(@PathVariable Long id) {
        log.debug("REST request to delete EditChannels : {}", id);
        editChannelsRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
