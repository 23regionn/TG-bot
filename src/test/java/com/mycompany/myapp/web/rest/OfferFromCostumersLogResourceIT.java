package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.OfferFromCostumersLog;
import com.mycompany.myapp.repository.OfferFromCostumersLogRepository;
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
 * Integration tests for the {@link OfferFromCostumersLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OfferFromCostumersLogResourceIT {

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

    private static final String ENTITY_API_URL = "/api/offer-from-costumers-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OfferFromCostumersLogRepository offerFromCostumersLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOfferFromCostumersLogMockMvc;

    private OfferFromCostumersLog offerFromCostumersLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferFromCostumersLog createEntity(EntityManager em) {
        OfferFromCostumersLog offerFromCostumersLog = new OfferFromCostumersLog()
            .text(DEFAULT_TEXT)
            .isDelete(DEFAULT_IS_DELETE)
            .adminId(DEFAULT_ADMIN_ID)
            .isActive(DEFAULT_IS_ACTIVE)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return offerFromCostumersLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OfferFromCostumersLog createUpdatedEntity(EntityManager em) {
        OfferFromCostumersLog offerFromCostumersLog = new OfferFromCostumersLog()
            .text(UPDATED_TEXT)
            .isDelete(UPDATED_IS_DELETE)
            .adminId(UPDATED_ADMIN_ID)
            .isActive(UPDATED_IS_ACTIVE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return offerFromCostumersLog;
    }

    @BeforeEach
    public void initTest() {
        offerFromCostumersLog = createEntity(em);
    }

    @Test
    @Transactional
    void createOfferFromCostumersLog() throws Exception {
        int databaseSizeBeforeCreate = offerFromCostumersLogRepository.findAll().size();
        // Create the OfferFromCostumersLog
        restOfferFromCostumersLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumersLog))
            )
            .andExpect(status().isCreated());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeCreate + 1);
        OfferFromCostumersLog testOfferFromCostumersLog = offerFromCostumersLogList.get(offerFromCostumersLogList.size() - 1);
        assertThat(testOfferFromCostumersLog.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testOfferFromCostumersLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testOfferFromCostumersLog.getAdminId()).isEqualTo(DEFAULT_ADMIN_ID);
        assertThat(testOfferFromCostumersLog.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOfferFromCostumersLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testOfferFromCostumersLog.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testOfferFromCostumersLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testOfferFromCostumersLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testOfferFromCostumersLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createOfferFromCostumersLogWithExistingId() throws Exception {
        // Create the OfferFromCostumersLog with an existing ID
        offerFromCostumersLog.setId(1L);

        int databaseSizeBeforeCreate = offerFromCostumersLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfferFromCostumersLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumersLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOfferFromCostumersLogs() throws Exception {
        // Initialize the database
        offerFromCostumersLogRepository.saveAndFlush(offerFromCostumersLog);

        // Get all the offerFromCostumersLogList
        restOfferFromCostumersLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(offerFromCostumersLog.getId().intValue())))
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
    void getOfferFromCostumersLog() throws Exception {
        // Initialize the database
        offerFromCostumersLogRepository.saveAndFlush(offerFromCostumersLog);

        // Get the offerFromCostumersLog
        restOfferFromCostumersLogMockMvc
            .perform(get(ENTITY_API_URL_ID, offerFromCostumersLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(offerFromCostumersLog.getId().intValue()))
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
    void getNonExistingOfferFromCostumersLog() throws Exception {
        // Get the offerFromCostumersLog
        restOfferFromCostumersLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewOfferFromCostumersLog() throws Exception {
        // Initialize the database
        offerFromCostumersLogRepository.saveAndFlush(offerFromCostumersLog);

        int databaseSizeBeforeUpdate = offerFromCostumersLogRepository.findAll().size();

        // Update the offerFromCostumersLog
        OfferFromCostumersLog updatedOfferFromCostumersLog = offerFromCostumersLogRepository.findById(offerFromCostumersLog.getId()).get();
        // Disconnect from session so that the updates on updatedOfferFromCostumersLog are not directly saved in db
        em.detach(updatedOfferFromCostumersLog);
        updatedOfferFromCostumersLog
            .text(UPDATED_TEXT)
            .isDelete(UPDATED_IS_DELETE)
            .adminId(UPDATED_ADMIN_ID)
            .isActive(UPDATED_IS_ACTIVE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restOfferFromCostumersLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOfferFromCostumersLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedOfferFromCostumersLog))
            )
            .andExpect(status().isOk());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeUpdate);
        OfferFromCostumersLog testOfferFromCostumersLog = offerFromCostumersLogList.get(offerFromCostumersLogList.size() - 1);
        assertThat(testOfferFromCostumersLog.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testOfferFromCostumersLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testOfferFromCostumersLog.getAdminId()).isEqualTo(UPDATED_ADMIN_ID);
        assertThat(testOfferFromCostumersLog.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOfferFromCostumersLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testOfferFromCostumersLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testOfferFromCostumersLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testOfferFromCostumersLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testOfferFromCostumersLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingOfferFromCostumersLog() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersLogRepository.findAll().size();
        offerFromCostumersLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferFromCostumersLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, offerFromCostumersLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumersLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOfferFromCostumersLog() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersLogRepository.findAll().size();
        offerFromCostumersLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfferFromCostumersLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumersLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOfferFromCostumersLog() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersLogRepository.findAll().size();
        offerFromCostumersLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfferFromCostumersLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumersLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOfferFromCostumersLogWithPatch() throws Exception {
        // Initialize the database
        offerFromCostumersLogRepository.saveAndFlush(offerFromCostumersLog);

        int databaseSizeBeforeUpdate = offerFromCostumersLogRepository.findAll().size();

        // Update the offerFromCostumersLog using partial update
        OfferFromCostumersLog partialUpdatedOfferFromCostumersLog = new OfferFromCostumersLog();
        partialUpdatedOfferFromCostumersLog.setId(offerFromCostumersLog.getId());

        partialUpdatedOfferFromCostumersLog
            .text(UPDATED_TEXT)
            .adminId(UPDATED_ADMIN_ID)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1);

        restOfferFromCostumersLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOfferFromCostumersLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOfferFromCostumersLog))
            )
            .andExpect(status().isOk());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeUpdate);
        OfferFromCostumersLog testOfferFromCostumersLog = offerFromCostumersLogList.get(offerFromCostumersLogList.size() - 1);
        assertThat(testOfferFromCostumersLog.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testOfferFromCostumersLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testOfferFromCostumersLog.getAdminId()).isEqualTo(UPDATED_ADMIN_ID);
        assertThat(testOfferFromCostumersLog.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOfferFromCostumersLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testOfferFromCostumersLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testOfferFromCostumersLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testOfferFromCostumersLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testOfferFromCostumersLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateOfferFromCostumersLogWithPatch() throws Exception {
        // Initialize the database
        offerFromCostumersLogRepository.saveAndFlush(offerFromCostumersLog);

        int databaseSizeBeforeUpdate = offerFromCostumersLogRepository.findAll().size();

        // Update the offerFromCostumersLog using partial update
        OfferFromCostumersLog partialUpdatedOfferFromCostumersLog = new OfferFromCostumersLog();
        partialUpdatedOfferFromCostumersLog.setId(offerFromCostumersLog.getId());

        partialUpdatedOfferFromCostumersLog
            .text(UPDATED_TEXT)
            .isDelete(UPDATED_IS_DELETE)
            .adminId(UPDATED_ADMIN_ID)
            .isActive(UPDATED_IS_ACTIVE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restOfferFromCostumersLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOfferFromCostumersLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOfferFromCostumersLog))
            )
            .andExpect(status().isOk());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeUpdate);
        OfferFromCostumersLog testOfferFromCostumersLog = offerFromCostumersLogList.get(offerFromCostumersLogList.size() - 1);
        assertThat(testOfferFromCostumersLog.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testOfferFromCostumersLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testOfferFromCostumersLog.getAdminId()).isEqualTo(UPDATED_ADMIN_ID);
        assertThat(testOfferFromCostumersLog.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOfferFromCostumersLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testOfferFromCostumersLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testOfferFromCostumersLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testOfferFromCostumersLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testOfferFromCostumersLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingOfferFromCostumersLog() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersLogRepository.findAll().size();
        offerFromCostumersLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfferFromCostumersLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, offerFromCostumersLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumersLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOfferFromCostumersLog() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersLogRepository.findAll().size();
        offerFromCostumersLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfferFromCostumersLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumersLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOfferFromCostumersLog() throws Exception {
        int databaseSizeBeforeUpdate = offerFromCostumersLogRepository.findAll().size();
        offerFromCostumersLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfferFromCostumersLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(offerFromCostumersLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OfferFromCostumersLog in the database
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOfferFromCostumersLog() throws Exception {
        // Initialize the database
        offerFromCostumersLogRepository.saveAndFlush(offerFromCostumersLog);

        int databaseSizeBeforeDelete = offerFromCostumersLogRepository.findAll().size();

        // Delete the offerFromCostumersLog
        restOfferFromCostumersLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, offerFromCostumersLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OfferFromCostumersLog> offerFromCostumersLogList = offerFromCostumersLogRepository.findAll();
        assertThat(offerFromCostumersLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
