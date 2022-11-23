package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.OfferFromCostumers;
import com.mycompany.myapp.repository.OfferFromCostumersRepository;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
 * Integration tests for the {@link OfferFromCostumersResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OfferFromCostumersResourceIT {

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETE = false;
    private static final Boolean UPDATED_IS_DELETE = true;

    private static final Long DEFAULT_ADMIN_ID = 1L;
    private static final Long UPDATED_ADMIN_ID = 2L;

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final ZonedDateTime DEFAULT_DATE_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_LONG_1 = 1L;
    private static final Long UPDATED_LONG_1 = 2L;

    private static final String DEFAULT_STRING_1 = "AAAAAAAAAA";
    private static final String UPDATED_STRING_1 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_BOOLEAN_1 = false;
    private static final Boolean UPDATED_BOOLEAN_1 = true;

    private static final String ENTITY_API_URL = "/api/offer-from-costumers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OfferFromCostumersRepository offerFromCostumersRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOfferFromCostumersMockMvc;

    private OfferFromCostumers offerFromCostumers;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferFromCostumers createEntity(EntityManager em) {
        OfferFromCostumers offerFromCostumers = new OfferFromCostumers()
            .text(DEFAULT_TEXT)
            .isDelete(DEFAULT_IS_DELETE)
            .adminId(DEFAULT_ADMIN_ID)
            .isActive(DEFAULT_IS_ACTIVE)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return offerFromCostumers;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferFromCostumers createUpdatedEntity(EntityManager em) {
        OfferFromCostumers offerFromCostumers = new OfferFromCostumers()
            .text(UPDATED_TEXT)
            .isDelete(UPDATED_IS_DELETE)
            .adminId(UPDATED_ADMIN_ID)
            .isActive(UPDATED_IS_ACTIVE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return offerFromCostumers;
    }

    @BeforeEach
    public void initTest() {
        offerFromCostumers = createEntity(em);
    }

    @Test
    @Transactional
    void createOfferFromCostumers() throws Exception {
        int databaseSizeBeforeCreate = offerFromCostumersRepository.findAll().size();
        // Create the OfferFromCostumers
        restOfferFromCostumersMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(offerFromCostumers))
            )
            .andExpect(status().isCreated());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeCreate + 1);
        OfferFromCostumers testOfferFromCostumers = offerFromCostumersList.get(offerFromCostumersList.size() - 1);
        assertThat(testOfferFromCostumers.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testOfferFromCostumers.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testOfferFromCostumers.getAdminId()).isEqualTo(DEFAULT_ADMIN_ID);
        assertThat(testOfferFromCostumers.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOfferFromCostumers.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testOfferFromCostumers.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testOfferFromCostumers.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testOfferFromCostumers.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testOfferFromCostumers.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createOfferFromCostumersWithExistingId() throws Exception {
        // Create the OfferFromCostumers with an existing ID
        offerFromCostumers.setId(1L);

        int databaseSizeBeforeCreate = offerFromCostumersRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferFromCostumersMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(offerFromCostumers))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOfferFromCostumers() throws Exception {
        // Initialize the database
        offerFromCostumersRepository.saveAndFlush(offerFromCostumers);

        // Get all the offerFromCostumersList
        restOfferFromCostumersMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerFromCostumers.getId().intValue())))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].adminId").value(hasItem(DEFAULT_ADMIN_ID.intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
    }

    @Test
    @Transactional
    void getOfferFromCostumers() throws Exception {
        // Initialize the database
        offerFromCostumersRepository.saveAndFlush(offerFromCostumers);

        // Get the offerFromCostumers
        restOfferFromCostumersMockMvc
            .perform(get(ENTITY_API_URL_ID, offerFromCostumers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerFromCostumers.getId().intValue()))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE.booleanValue()))
            .andExpect(jsonPath("$.adminId").value(DEFAULT_ADMIN_ID.intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingOfferFromCostumers() throws Exception {
        // Get the offerFromCostumers
        restOfferFromCostumersMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewOfferFromCostumers() throws Exception {
        // Initialize the database
        offerFromCostumersRepository.saveAndFlush(offerFromCostumers);

        int databaseSizeBeforeUpdate = offerFromCostumersRepository.findAll().size();

        // Update the offerFromCostumers
        OfferFromCostumers updatedOfferFromCostumers = offerFromCostumersRepository.findById(offerFromCostumers.getId()).get();
        // Disconnect from session so that the updates on updatedOfferFromCostumers are not directly saved in db
        em.detach(updatedOfferFromCostumers);
        updatedOfferFromCostumers
            .text(UPDATED_TEXT)
            .isDelete(UPDATED_IS_DELETE)
            .adminId(UPDATED_ADMIN_ID)
            .isActive(UPDATED_IS_ACTIVE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restOfferFromCostumersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOfferFromCostumers.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOfferFromCostumers))
            )
            .andExpect(status().isOk());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeUpdate);
        OfferFromCostumers testOfferFromCostumers = offerFromCostumersList.get(offerFromCostumersList.size() - 1);
        assertThat(testOfferFromCostumers.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testOfferFromCostumers.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testOfferFromCostumers.getAdminId()).isEqualTo(UPDATED_ADMIN_ID);
        assertThat(testOfferFromCostumers.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOfferFromCostumers.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testOfferFromCostumers.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testOfferFromCostumers.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testOfferFromCostumers.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testOfferFromCostumers.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingOfferFromCostumers() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersRepository.findAll().size();
        offerFromCostumers.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferFromCostumersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, offerFromCostumers.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumers))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOfferFromCostumers() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersRepository.findAll().size();
        offerFromCostumers.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfferFromCostumersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumers))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOfferFromCostumers() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersRepository.findAll().size();
        offerFromCostumers.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfferFromCostumersMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(offerFromCostumers))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOfferFromCostumersWithPatch() throws Exception {
        // Initialize the database
        offerFromCostumersRepository.saveAndFlush(offerFromCostumers);

        int databaseSizeBeforeUpdate = offerFromCostumersRepository.findAll().size();

        // Update the offerFromCostumers using partial update
        OfferFromCostumers partialUpdatedOfferFromCostumers = new OfferFromCostumers();
        partialUpdatedOfferFromCostumers.setId(offerFromCostumers.getId());

        partialUpdatedOfferFromCostumers.isDelete(UPDATED_IS_DELETE).long1(UPDATED_LONG_1).string1(UPDATED_STRING_1);

        restOfferFromCostumersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOfferFromCostumers.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOfferFromCostumers))
            )
            .andExpect(status().isOk());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeUpdate);
        OfferFromCostumers testOfferFromCostumers = offerFromCostumersList.get(offerFromCostumersList.size() - 1);
        assertThat(testOfferFromCostumers.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testOfferFromCostumers.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testOfferFromCostumers.getAdminId()).isEqualTo(DEFAULT_ADMIN_ID);
        assertThat(testOfferFromCostumers.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOfferFromCostumers.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testOfferFromCostumers.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testOfferFromCostumers.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testOfferFromCostumers.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testOfferFromCostumers.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateOfferFromCostumersWithPatch() throws Exception {
        // Initialize the database
        offerFromCostumersRepository.saveAndFlush(offerFromCostumers);

        int databaseSizeBeforeUpdate = offerFromCostumersRepository.findAll().size();

        // Update the offerFromCostumers using partial update
        OfferFromCostumers partialUpdatedOfferFromCostumers = new OfferFromCostumers();
        partialUpdatedOfferFromCostumers.setId(offerFromCostumers.getId());

        partialUpdatedOfferFromCostumers
            .text(UPDATED_TEXT)
            .isDelete(UPDATED_IS_DELETE)
            .adminId(UPDATED_ADMIN_ID)
            .isActive(UPDATED_IS_ACTIVE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restOfferFromCostumersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOfferFromCostumers.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOfferFromCostumers))
            )
            .andExpect(status().isOk());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeUpdate);
        OfferFromCostumers testOfferFromCostumers = offerFromCostumersList.get(offerFromCostumersList.size() - 1);
        assertThat(testOfferFromCostumers.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testOfferFromCostumers.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testOfferFromCostumers.getAdminId()).isEqualTo(UPDATED_ADMIN_ID);
        assertThat(testOfferFromCostumers.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOfferFromCostumers.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testOfferFromCostumers.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testOfferFromCostumers.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testOfferFromCostumers.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testOfferFromCostumers.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingOfferFromCostumers() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersRepository.findAll().size();
        offerFromCostumers.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferFromCostumersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, offerFromCostumers.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumers))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOfferFromCostumers() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersRepository.findAll().size();
        offerFromCostumers.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfferFromCostumersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumers))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOfferFromCostumers() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersRepository.findAll().size();
        offerFromCostumers.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfferFromCostumersMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumers))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OfferFromCostumers in the database
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOfferFromCostumers() throws Exception {
        // Initialize the database
        offerFromCostumersRepository.saveAndFlush(offerFromCostumers);

        int databaseSizeBeforeDelete = offerFromCostumersRepository.findAll().size();

        // Delete the offerFromCostumers
        restOfferFromCostumersMockMvc
            .perform(delete(ENTITY_API_URL_ID, offerFromCostumers.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferFromCostumers> offerFromCostumersList = offerFromCostumersRepository.findAll();
        assertThat(offerFromCostumersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
