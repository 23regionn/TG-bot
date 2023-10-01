package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.RelCategoryCity;
import com.mycompany.myapp.repository.RelCategoryCityRepository;
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
 * Integration tests for the {@link RelCategoryCityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RelCategoryCityResourceIT {

    private static final Boolean DEFAULT_IS_SHOW = false;
    private static final Boolean UPDATED_IS_SHOW = true;

    private static final Double DEFAULT_SCORE = 1D;
    private static final Double UPDATED_SCORE = 2D;

    private static final Boolean DEFAULT_IS_FIRST = false;
    private static final Boolean UPDATED_IS_FIRST = true;

    private static final String ENTITY_API_URL = "/api/rel-category-cities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RelCategoryCityRepository relCategoryCityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRelCategoryCityMockMvc;

    private RelCategoryCity relCategoryCity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelCategoryCity createEntity(EntityManager em) {
        RelCategoryCity relCategoryCity = new RelCategoryCity().isShow(DEFAULT_IS_SHOW).score(DEFAULT_SCORE).isFirst(DEFAULT_IS_FIRST);
        return relCategoryCity;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RelCategoryCity createUpdatedEntity(EntityManager em) {
        RelCategoryCity relCategoryCity = new RelCategoryCity().isShow(UPDATED_IS_SHOW).score(UPDATED_SCORE).isFirst(UPDATED_IS_FIRST);
        return relCategoryCity;
    }

    @BeforeEach
    public void initTest() {
        relCategoryCity = createEntity(em);
    }

    @Test
    @Transactional
    void createRelCategoryCity() throws Exception {
        int databaseSizeBeforeCreate = relCategoryCityRepository.findAll().size();
        // Create the RelCategoryCity
        restRelCategoryCityMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(relCategoryCity))
            )
            .andExpect(status().isCreated());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeCreate + 1);
        RelCategoryCity testRelCategoryCity = relCategoryCityList.get(relCategoryCityList.size() - 1);
        assertThat(testRelCategoryCity.getIsShow()).isEqualTo(DEFAULT_IS_SHOW);
        assertThat(testRelCategoryCity.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testRelCategoryCity.getIsFirst()).isEqualTo(DEFAULT_IS_FIRST);
    }

    @Test
    @Transactional
    void createRelCategoryCityWithExistingId() throws Exception {
        // Create the RelCategoryCity with an existing ID
        relCategoryCity.setId(1L);

        int databaseSizeBeforeCreate = relCategoryCityRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRelCategoryCityMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(relCategoryCity))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRelCategoryCities() throws Exception {
        // Initialize the database
        relCategoryCityRepository.saveAndFlush(relCategoryCity);

        // Get all the relCategoryCityList
        restRelCategoryCityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(relCategoryCity.getId().intValue())))
            .andExpect(jsonPath("$.[*].isShow").value(hasItem(DEFAULT_IS_SHOW.booleanValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].isFirst").value(hasItem(DEFAULT_IS_FIRST.booleanValue())));
    }

    @Test
    @Transactional
    void getRelCategoryCity() throws Exception {
        // Initialize the database
        relCategoryCityRepository.saveAndFlush(relCategoryCity);

        // Get the relCategoryCity
        restRelCategoryCityMockMvc
            .perform(get(ENTITY_API_URL_ID, relCategoryCity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(relCategoryCity.getId().intValue()))
            .andExpect(jsonPath("$.isShow").value(DEFAULT_IS_SHOW.booleanValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.doubleValue()))
            .andExpect(jsonPath("$.isFirst").value(DEFAULT_IS_FIRST.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingRelCategoryCity() throws Exception {
        // Get the relCategoryCity
        restRelCategoryCityMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRelCategoryCity() throws Exception {
        // Initialize the database
        relCategoryCityRepository.saveAndFlush(relCategoryCity);

        int databaseSizeBeforeUpdate = relCategoryCityRepository.findAll().size();

        // Update the relCategoryCity
        RelCategoryCity updatedRelCategoryCity = relCategoryCityRepository.findById(relCategoryCity.getId()).get();
        // Disconnect from session so that the updates on updatedRelCategoryCity are not directly saved in db
        em.detach(updatedRelCategoryCity);
        updatedRelCategoryCity.isShow(UPDATED_IS_SHOW).score(UPDATED_SCORE).isFirst(UPDATED_IS_FIRST);

        restRelCategoryCityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRelCategoryCity.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedRelCategoryCity))
            )
            .andExpect(status().isOk());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeUpdate);
        RelCategoryCity testRelCategoryCity = relCategoryCityList.get(relCategoryCityList.size() - 1);
        assertThat(testRelCategoryCity.getIsShow()).isEqualTo(UPDATED_IS_SHOW);
        assertThat(testRelCategoryCity.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testRelCategoryCity.getIsFirst()).isEqualTo(UPDATED_IS_FIRST);
    }

    @Test
    @Transactional
    void putNonExistingRelCategoryCity() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityRepository.findAll().size();
        relCategoryCity.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelCategoryCityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, relCategoryCity.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCity))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRelCategoryCity() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityRepository.findAll().size();
        relCategoryCity.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryCityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCity))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRelCategoryCity() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityRepository.findAll().size();
        relCategoryCity.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryCityMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(relCategoryCity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRelCategoryCityWithPatch() throws Exception {
        // Initialize the database
        relCategoryCityRepository.saveAndFlush(relCategoryCity);

        int databaseSizeBeforeUpdate = relCategoryCityRepository.findAll().size();

        // Update the relCategoryCity using partial update
        RelCategoryCity partialUpdatedRelCategoryCity = new RelCategoryCity();
        partialUpdatedRelCategoryCity.setId(relCategoryCity.getId());

        partialUpdatedRelCategoryCity.isShow(UPDATED_IS_SHOW).score(UPDATED_SCORE);

        restRelCategoryCityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelCategoryCity.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRelCategoryCity))
            )
            .andExpect(status().isOk());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeUpdate);
        RelCategoryCity testRelCategoryCity = relCategoryCityList.get(relCategoryCityList.size() - 1);
        assertThat(testRelCategoryCity.getIsShow()).isEqualTo(UPDATED_IS_SHOW);
        assertThat(testRelCategoryCity.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testRelCategoryCity.getIsFirst()).isEqualTo(DEFAULT_IS_FIRST);
    }

    @Test
    @Transactional
    void fullUpdateRelCategoryCityWithPatch() throws Exception {
        // Initialize the database
        relCategoryCityRepository.saveAndFlush(relCategoryCity);

        int databaseSizeBeforeUpdate = relCategoryCityRepository.findAll().size();

        // Update the relCategoryCity using partial update
        RelCategoryCity partialUpdatedRelCategoryCity = new RelCategoryCity();
        partialUpdatedRelCategoryCity.setId(relCategoryCity.getId());

        partialUpdatedRelCategoryCity.isShow(UPDATED_IS_SHOW).score(UPDATED_SCORE).isFirst(UPDATED_IS_FIRST);

        restRelCategoryCityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRelCategoryCity.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRelCategoryCity))
            )
            .andExpect(status().isOk());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeUpdate);
        RelCategoryCity testRelCategoryCity = relCategoryCityList.get(relCategoryCityList.size() - 1);
        assertThat(testRelCategoryCity.getIsShow()).isEqualTo(UPDATED_IS_SHOW);
        assertThat(testRelCategoryCity.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testRelCategoryCity.getIsFirst()).isEqualTo(UPDATED_IS_FIRST);
    }

    @Test
    @Transactional
    void patchNonExistingRelCategoryCity() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityRepository.findAll().size();
        relCategoryCity.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRelCategoryCityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, relCategoryCity.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCity))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRelCategoryCity() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityRepository.findAll().size();
        relCategoryCity.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryCityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCity))
            )
            .andExpect(status().isBadRequest());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRelCategoryCity() throws Exception {
        int databaseSizeBeforeUpdate = relCategoryCityRepository.findAll().size();
        relCategoryCity.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRelCategoryCityMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(relCategoryCity))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RelCategoryCity in the database
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRelCategoryCity() throws Exception {
        // Initialize the database
        relCategoryCityRepository.saveAndFlush(relCategoryCity);

        int databaseSizeBeforeDelete = relCategoryCityRepository.findAll().size();

        // Delete the relCategoryCity
        restRelCategoryCityMockMvc
            .perform(delete(ENTITY_API_URL_ID, relCategoryCity.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RelCategoryCity> relCategoryCityList = relCategoryCityRepository.findAll();
        assertThat(relCategoryCityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
