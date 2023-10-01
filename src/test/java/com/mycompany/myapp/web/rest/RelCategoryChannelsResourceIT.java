package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.RelCategoryChannels;
import com.mycompany.myapp.repository.RelCategoryChannelsRepository;
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
 * Integration tests for the {@link RelCategoryChannelsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RelCategoryChannelsResourceIT {

    private static final Double DEFAULT_SCORE_CHANNEL = 1D;
    private static final Double UPDATED_SCORE_CHANNEL = 2D;

    private static final Boolean DEFAULT_IS_SHOW_CHANNEL = false;
    private static final Boolean UPDATED_IS_SHOW_CHANNEL = true;

    private static final String ENTITY_API_URL = "/api/rel-category-channels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RelCategoryChannelsRepository relCategoryChannelsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelCategoryChannelsMockMvc;

    private RelCategoryChannels relCategoryChannels;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelCategoryChannels createEntity(EntityManager em) {
        RelCategoryChannels relCategoryChannels = new RelCategoryChannels()
            .scoreChannel(DEFAULT_SCORE_CHANNEL)
            .isShowChannel(DEFAULT_IS_SHOW_CHANNEL);
        return relCategoryChannels;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelCategoryChannels createUpdatedEntity(EntityManager em) {
        RelCategoryChannels relCategoryChannels = new RelCategoryChannels()
            .scoreChannel(UPDATED_SCORE_CHANNEL)
            .isShowChannel(UPDATED_IS_SHOW_CHANNEL);
        return relCategoryChannels;
    }

    @BeforeEach
    public void initTest() {
        relCategoryChannels = createEntity(em);
    }

    @Test
    @Transactional
    void createRelCategoryChannels() throws Exception {
        int databaseSizeBeforeCreate = relCategoryChannelsRepository.findAll().size();
        // Create the RelCategoryChannels
        restRelCategoryChannelsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(relCategoryChannels))
            )
            .andExpect(status().isCreated());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeCreate + 1);
        RelCategoryChannels testRelCategoryChannels = relCategoryChannelsList.get(relCategoryChannelsList.size() - 1);
        assertThat(testRelCategoryChannels.getScoreChannel()).isEqualTo(DEFAULT_SCORE_CHANNEL);
        assertThat(testRelCategoryChannels.getIsShowChannel()).isEqualTo(DEFAULT_IS_SHOW_CHANNEL);
    }

    @Test
    @Transactional
    void createRelCategoryChannelsWithExistingId() throws Exception {
        // Create the RelCategoryChannels with an existing ID
        relCategoryChannels.setId(1L);

        int databaseSizeBeforeCreate = relCategoryChannelsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelCategoryChannelsMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(relCategoryChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRelCategoryChannels() throws Exception {
        // Initialize the database
        relCategoryChannelsRepository.saveAndFlush(relCategoryChannels);

        // Get all the relCategoryChannelsList
        restRelCategoryChannelsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relCategoryChannels.getId().intValue())))
            .andExpect(jsonPath("$.[*].scoreChannel").value(hasItem(DEFAULT_SCORE_CHANNEL.doubleValue())))
            .andExpect(jsonPath("$.[*].isShowChannel").value(hasItem(DEFAULT_IS_SHOW_CHANNEL.booleanValue())));
    }

    @Test
    @Transactional
    void getRelCategoryChannels() throws Exception {
        // Initialize the database
        relCategoryChannelsRepository.saveAndFlush(relCategoryChannels);

        // Get the relCategoryChannels
        restRelCategoryChannelsMockMvc
            .perform(get(ENTITY_API_URL_ID, relCategoryChannels.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relCategoryChannels.getId().intValue()))
            .andExpect(jsonPath("$.scoreChannel").value(DEFAULT_SCORE_CHANNEL.doubleValue()))
            .andExpect(jsonPath("$.isShowChannel").value(DEFAULT_IS_SHOW_CHANNEL.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingRelCategoryChannels() throws Exception {
        // Get the relCategoryChannels
        restRelCategoryChannelsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRelCategoryChannels() throws Exception {
        // Initialize the database
        relCategoryChannelsRepository.saveAndFlush(relCategoryChannels);

        int databaseSizeBeforeUpdate = relCategoryChannelsRepository.findAll().size();

        // Update the relCategoryChannels
        RelCategoryChannels updatedRelCategoryChannels = relCategoryChannelsRepository.findById(relCategoryChannels.getId()).get();
        // Disconnect from session so that the updates on updatedRelCategoryChannels are not directly saved in db
        em.detach(updatedRelCategoryChannels);
        updatedRelCategoryChannels.scoreChannel(UPDATED_SCORE_CHANNEL).isShowChannel(UPDATED_IS_SHOW_CHANNEL);

        restRelCategoryChannelsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRelCategoryChannels.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRelCategoryChannels))
            )
            .andExpect(status().isOk());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeUpdate);
        RelCategoryChannels testRelCategoryChannels = relCategoryChannelsList.get(relCategoryChannelsList.size() - 1);
        assertThat(testRelCategoryChannels.getScoreChannel()).isEqualTo(UPDATED_SCORE_CHANNEL);
        assertThat(testRelCategoryChannels.getIsShowChannel()).isEqualTo(UPDATED_IS_SHOW_CHANNEL);
    }

    @Test
    @Transactional
    void putNonExistingRelCategoryChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryChannelsRepository.findAll().size();
        relCategoryChannels.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelCategoryChannelsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, relCategoryChannels.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRelCategoryChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryChannelsRepository.findAll().size();
        relCategoryChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryChannelsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRelCategoryChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryChannelsRepository.findAll().size();
        relCategoryChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryChannelsMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(relCategoryChannels))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRelCategoryChannelsWithPatch() throws Exception {
        // Initialize the database
        relCategoryChannelsRepository.saveAndFlush(relCategoryChannels);

        int databaseSizeBeforeUpdate = relCategoryChannelsRepository.findAll().size();

        // Update the relCategoryChannels using partial update
        RelCategoryChannels partialUpdatedRelCategoryChannels = new RelCategoryChannels();
        partialUpdatedRelCategoryChannels.setId(relCategoryChannels.getId());

        restRelCategoryChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelCategoryChannels.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRelCategoryChannels))
            )
            .andExpect(status().isOk());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeUpdate);
        RelCategoryChannels testRelCategoryChannels = relCategoryChannelsList.get(relCategoryChannelsList.size() - 1);
        assertThat(testRelCategoryChannels.getScoreChannel()).isEqualTo(DEFAULT_SCORE_CHANNEL);
        assertThat(testRelCategoryChannels.getIsShowChannel()).isEqualTo(DEFAULT_IS_SHOW_CHANNEL);
    }

    @Test
    @Transactional
    void fullUpdateRelCategoryChannelsWithPatch() throws Exception {
        // Initialize the database
        relCategoryChannelsRepository.saveAndFlush(relCategoryChannels);

        int databaseSizeBeforeUpdate = relCategoryChannelsRepository.findAll().size();

        // Update the relCategoryChannels using partial update
        RelCategoryChannels partialUpdatedRelCategoryChannels = new RelCategoryChannels();
        partialUpdatedRelCategoryChannels.setId(relCategoryChannels.getId());

        partialUpdatedRelCategoryChannels.scoreChannel(UPDATED_SCORE_CHANNEL).isShowChannel(UPDATED_IS_SHOW_CHANNEL);

        restRelCategoryChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelCategoryChannels.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRelCategoryChannels))
            )
            .andExpect(status().isOk());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeUpdate);
        RelCategoryChannels testRelCategoryChannels = relCategoryChannelsList.get(relCategoryChannelsList.size() - 1);
        assertThat(testRelCategoryChannels.getScoreChannel()).isEqualTo(UPDATED_SCORE_CHANNEL);
        assertThat(testRelCategoryChannels.getIsShowChannel()).isEqualTo(UPDATED_IS_SHOW_CHANNEL);
    }

    @Test
    @Transactional
    void patchNonExistingRelCategoryChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryChannelsRepository.findAll().size();
        relCategoryChannels.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelCategoryChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, relCategoryChannels.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRelCategoryChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryChannelsRepository.findAll().size();
        relCategoryChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRelCategoryChannels() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryChannelsRepository.findAll().size();
        relCategoryChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryChannels))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RelCategoryChannels in the database
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRelCategoryChannels() throws Exception {
        // Initialize the database
        relCategoryChannelsRepository.saveAndFlush(relCategoryChannels);

        int databaseSizeBeforeDelete = relCategoryChannelsRepository.findAll().size();

        // Delete the relCategoryChannels
        restRelCategoryChannelsMockMvc
            .perform(delete(ENTITY_API_URL_ID, relCategoryChannels.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelCategoryChannels> relCategoryChannelsList = relCategoryChannelsRepository.findAll();
        assertThat(relCategoryChannelsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
