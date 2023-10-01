package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.RelCategoryCityChannels;
import com.mycompany.myapp.repository.RelCategoryCityChannelsRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RelCategoryCityChannelsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RelCategoryCityChannelsResourceIT {

    private static final Double DEFAULT_SCORE_CHANNEL = 1D;
    private static final Double UPDATED_SCORE_CHANNEL = 2D;

    private static final Boolean DEFAULT_IS_SHOW_CHANNEL = false;
    private static final Boolean UPDATED_IS_SHOW_CHANNEL = true;

    private static final String ENTITY_API_URL = "/api/rel-category-city-channels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RelCategoryCityChannelsRepository relCategoryCityChannelsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelCategoryCityChannelsMockMvc;

    private RelCategoryCityChannels relCategoryCityChannels;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelCategoryCityChannels createEntity(EntityManager em) {
        RelCategoryCityChannels relCategoryCityChannels = new RelCategoryCityChannels()
            .scoreChannel(DEFAULT_SCORE_CHANNEL)
            .isShowChannel(DEFAULT_IS_SHOW_CHANNEL);
        return relCategoryCityChannels;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelCategoryCityChannels createUpdatedEntity(EntityManager em) {
        RelCategoryCityChannels relCategoryCityChannels = new RelCategoryCityChannels()
            .scoreChannel(UPDATED_SCORE_CHANNEL)
            .isShowChannel(UPDATED_IS_SHOW_CHANNEL);
        return relCategoryCityChannels;
    }

    @BeforeEach
    public void initTest() {
        relCategoryCityChannels = createEntity(em);
    }

    @Test
    @Transactional
    void createRelCategoryCityChannels() throws Exception {
        int databaseSizeBeforeCreate = relCategoryCityChannelsRepository.findAll().size();
        // Create the RelCategoryCityChannels
        restRelCategoryCityChannelsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCityChannels))
            )
            .andExpect(status().isCreated());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeCreate + 1);
        RelCategoryCityChannels testRelCategoryCityChannels = relCategoryCityChannelsList.get(relCategoryCityChannelsList.size() - 1);
        assertThat(testRelCategoryCityChannels.getScoreChannel()).isEqualTo(DEFAULT_SCORE_CHANNEL);
        assertThat(testRelCategoryCityChannels.getIsShowChannel()).isEqualTo(DEFAULT_IS_SHOW_CHANNEL);
    }

    @Test
    @Transactional
    void createRelCategoryCityChannelsWithExistingId() throws Exception {
        // Create the RelCategoryCityChannels with an existing ID
        relCategoryCityChannels.setId(1L);

        int databaseSizeBeforeCreate = relCategoryCityChannelsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelCategoryCityChannelsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCityChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRelCategoryCityChannels() throws Exception {
        // Initialize the database
        relCategoryCityChannelsRepository.saveAndFlush(relCategoryCityChannels);

        // Get all the relCategoryCityChannelsList
        restRelCategoryCityChannelsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relCategoryCityChannels.getId().intValue())))
            .andExpect(jsonPath("$.[*].scoreChannel").value(hasItem(DEFAULT_SCORE_CHANNEL.doubleValue())))
            .andExpect(jsonPath("$.[*].isShowChannel").value(hasItem(DEFAULT_IS_SHOW_CHANNEL.booleanValue())));
    }

    @Test
    @Transactional
    void getRelCategoryCityChannels() throws Exception {
        // Initialize the database
        relCategoryCityChannelsRepository.saveAndFlush(relCategoryCityChannels);

        // Get the relCategoryCityChannels
        restRelCategoryCityChannelsMockMvc
            .perform(get(ENTITY_API_URL_ID, relCategoryCityChannels.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relCategoryCityChannels.getId().intValue()))
            .andExpect(jsonPath("$.scoreChannel").value(DEFAULT_SCORE_CHANNEL.doubleValue()))
            .andExpect(jsonPath("$.isShowChannel").value(DEFAULT_IS_SHOW_CHANNEL.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingRelCategoryCityChannels() throws Exception {
        // Get the relCategoryCityChannels
        restRelCategoryCityChannelsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRelCategoryCityChannels() throws Exception {
        // Initialize the database
        relCategoryCityChannelsRepository.saveAndFlush(relCategoryCityChannels);

        int databaseSizeBeforeUpdate = relCategoryCityChannelsRepository.findAll().size();

        // Update the relCategoryCityChannels
        RelCategoryCityChannels updatedRelCategoryCityChannels = relCategoryCityChannelsRepository
            .findById(relCategoryCityChannels.getId())
            .get();
        // Disconnect from session so that the updates on updatedRelCategoryCityChannels are not directly saved in db
        em.detach(updatedRelCategoryCityChannels);
        updatedRelCategoryCityChannels.scoreChannel(UPDATED_SCORE_CHANNEL).isShowChannel(UPDATED_IS_SHOW_CHANNEL);

        restRelCategoryCityChannelsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRelCategoryCityChannels.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRelCategoryCityChannels))
            )
            .andExpect(status().isOk());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeUpdate);
        RelCategoryCityChannels testRelCategoryCityChannels = relCategoryCityChannelsList.get(relCategoryCityChannelsList.size() - 1);
        assertThat(testRelCategoryCityChannels.getScoreChannel()).isEqualTo(UPDATED_SCORE_CHANNEL);
        assertThat(testRelCategoryCityChannels.getIsShowChannel()).isEqualTo(UPDATED_IS_SHOW_CHANNEL);
    }

    @Test
    @Transactional
    void putNonExistingRelCategoryCityChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityChannelsRepository.findAll().size();
        relCategoryCityChannels.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelCategoryCityChannelsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, relCategoryCityChannels.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCityChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRelCategoryCityChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityChannelsRepository.findAll().size();
        relCategoryCityChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryCityChannelsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCityChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRelCategoryCityChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityChannelsRepository.findAll().size();
        relCategoryCityChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryCityChannelsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCityChannels))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRelCategoryCityChannelsWithPatch() throws Exception {
        // Initialize the database
        relCategoryCityChannelsRepository.saveAndFlush(relCategoryCityChannels);

        int databaseSizeBeforeUpdate = relCategoryCityChannelsRepository.findAll().size();

        // Update the relCategoryCityChannels using partial update
        RelCategoryCityChannels partialUpdatedRelCategoryCityChannels = new RelCategoryCityChannels();
        partialUpdatedRelCategoryCityChannels.setId(relCategoryCityChannels.getId());

        partialUpdatedRelCategoryCityChannels.scoreChannel(UPDATED_SCORE_CHANNEL);

        restRelCategoryCityChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelCategoryCityChannels.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRelCategoryCityChannels))
            )
            .andExpect(status().isOk());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeUpdate);
        RelCategoryCityChannels testRelCategoryCityChannels = relCategoryCityChannelsList.get(relCategoryCityChannelsList.size() - 1);
        assertThat(testRelCategoryCityChannels.getScoreChannel()).isEqualTo(UPDATED_SCORE_CHANNEL);
        assertThat(testRelCategoryCityChannels.getIsShowChannel()).isEqualTo(DEFAULT_IS_SHOW_CHANNEL);
    }

    @Test
    @Transactional
    void fullUpdateRelCategoryCityChannelsWithPatch() throws Exception {
        // Initialize the database
        relCategoryCityChannelsRepository.saveAndFlush(relCategoryCityChannels);

        int databaseSizeBeforeUpdate = relCategoryCityChannelsRepository.findAll().size();

        // Update the relCategoryCityChannels using partial update
        RelCategoryCityChannels partialUpdatedRelCategoryCityChannels = new RelCategoryCityChannels();
        partialUpdatedRelCategoryCityChannels.setId(relCategoryCityChannels.getId());

        partialUpdatedRelCategoryCityChannels.scoreChannel(UPDATED_SCORE_CHANNEL).isShowChannel(UPDATED_IS_SHOW_CHANNEL);

        restRelCategoryCityChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelCategoryCityChannels.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRelCategoryCityChannels))
            )
            .andExpect(status().isOk());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeUpdate);
        RelCategoryCityChannels testRelCategoryCityChannels = relCategoryCityChannelsList.get(relCategoryCityChannelsList.size() - 1);
        assertThat(testRelCategoryCityChannels.getScoreChannel()).isEqualTo(UPDATED_SCORE_CHANNEL);
        assertThat(testRelCategoryCityChannels.getIsShowChannel()).isEqualTo(UPDATED_IS_SHOW_CHANNEL);
    }

    @Test
    @Transactional
    void patchNonExistingRelCategoryCityChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityChannelsRepository.findAll().size();
        relCategoryCityChannels.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelCategoryCityChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, relCategoryCityChannels.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCityChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRelCategoryCityChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityChannelsRepository.findAll().size();
        relCategoryCityChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryCityChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCityChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRelCategoryCityChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityChannelsRepository.findAll().size();
        relCategoryCityChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryCityChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCityChannels))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RelCategoryCityChannels in the database
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRelCategoryCityChannels() throws Exception {
        // Initialize the database
        relCategoryCityChannelsRepository.saveAndFlush(relCategoryCityChannels);

        int databaseSizeBeforeDelete = relCategoryCityChannelsRepository.findAll().size();

        // Delete the relCategoryCityChannels
        restRelCategoryCityChannelsMockMvc
            .perform(delete(ENTITY_API_URL_ID, relCategoryCityChannels.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelCategoryCityChannels> relCategoryCityChannelsList = relCategoryCityChannelsRepository.findAll();
        assertThat(relCategoryCityChannelsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
