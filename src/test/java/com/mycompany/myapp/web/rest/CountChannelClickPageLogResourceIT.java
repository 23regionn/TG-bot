package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.CountChannelClickPageLog;
import com.mycompany.myapp.repository.CountChannelClickPageLogRepository;
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
 * Integration tests for the {@link CountChannelClickPageLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CountChannelClickPageLogResourceIT {

    private static final Long DEFAULT_CHAT_ID = 1L;
    private static final Long UPDATED_CHAT_ID = 2L;

    private static final ZonedDateTime DEFAULT_DATE_LOG = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_LOG = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_ID_CHANNEL = 1L;
    private static final Long UPDATED_ID_CHANNEL = 2L;

    private static final Long DEFAULT_PAGE_NUMBER = 1L;
    private static final Long UPDATED_PAGE_NUMBER = 2L;

    private static final Long DEFAULT_ID_CATEGORY = 1L;
    private static final Long UPDATED_ID_CATEGORY = 2L;

    private static final Long DEFAULT_ID_CITY = 1L;
    private static final Long UPDATED_ID_CITY = 2L;

    private static final String ENTITY_API_URL = "/api/count-channel-click-page-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CountChannelClickPageLogRepository countChannelClickPageLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCountChannelClickPageLogMockMvc;

    private CountChannelClickPageLog countChannelClickPageLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CountChannelClickPageLog createEntity(EntityManager em) {
        CountChannelClickPageLog countChannelClickPageLog = new CountChannelClickPageLog()
            .chatId(DEFAULT_CHAT_ID)
            .dateLog(DEFAULT_DATE_LOG)
            .idChannel(DEFAULT_ID_CHANNEL)
            .pageNumber(DEFAULT_PAGE_NUMBER)
            .idCategory(DEFAULT_ID_CATEGORY)
            .idCity(DEFAULT_ID_CITY);
        return countChannelClickPageLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CountChannelClickPageLog createUpdatedEntity(EntityManager em) {
        CountChannelClickPageLog countChannelClickPageLog = new CountChannelClickPageLog()
            .chatId(UPDATED_CHAT_ID)
            .dateLog(UPDATED_DATE_LOG)
            .idChannel(UPDATED_ID_CHANNEL)
            .pageNumber(UPDATED_PAGE_NUMBER)
            .idCategory(UPDATED_ID_CATEGORY)
            .idCity(UPDATED_ID_CITY);
        return countChannelClickPageLog;
    }

    @BeforeEach
    public void initTest() {
        countChannelClickPageLog = createEntity(em);
    }

    @Test
    @Transactional
    void createCountChannelClickPageLog() throws Exception {
        int databaseSizeBeforeCreate = countChannelClickPageLogRepository.findAll().size();
        // Create the CountChannelClickPageLog
        restCountChannelClickPageLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(countChannelClickPageLog))
            )
            .andExpect(status().isCreated());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeCreate + 1);
        CountChannelClickPageLog testCountChannelClickPageLog = countChannelClickPageLogList.get(countChannelClickPageLogList.size() - 1);
        assertThat(testCountChannelClickPageLog.getChatId()).isEqualTo(DEFAULT_CHAT_ID);
        assertThat(testCountChannelClickPageLog.getDateLog()).isEqualTo(DEFAULT_DATE_LOG);
        assertThat(testCountChannelClickPageLog.getIdChannel()).isEqualTo(DEFAULT_ID_CHANNEL);
        assertThat(testCountChannelClickPageLog.getPageNumber()).isEqualTo(DEFAULT_PAGE_NUMBER);
        assertThat(testCountChannelClickPageLog.getIdCategory()).isEqualTo(DEFAULT_ID_CATEGORY);
        assertThat(testCountChannelClickPageLog.getIdCity()).isEqualTo(DEFAULT_ID_CITY);
    }

    @Test
    @Transactional
    void createCountChannelClickPageLogWithExistingId() throws Exception {
        // Create the CountChannelClickPageLog with an existing ID
        countChannelClickPageLog.setId(1L);

        int databaseSizeBeforeCreate = countChannelClickPageLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCountChannelClickPageLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(countChannelClickPageLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCountChannelClickPageLogs() throws Exception {
        // Initialize the database
        countChannelClickPageLogRepository.saveAndFlush(countChannelClickPageLog);

        // Get all the countChannelClickPageLogList
        restCountChannelClickPageLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(countChannelClickPageLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].chatId").value(hasItem(DEFAULT_CHAT_ID.intValue())))
            .andExpect(jsonPath("$.[*].dateLog").value(hasItem(sameInstant(DEFAULT_DATE_LOG))))
            .andExpect(jsonPath("$.[*].idChannel").value(hasItem(DEFAULT_ID_CHANNEL.intValue())))
            .andExpect(jsonPath("$.[*].pageNumber").value(hasItem(DEFAULT_PAGE_NUMBER.intValue())))
            .andExpect(jsonPath("$.[*].idCategory").value(hasItem(DEFAULT_ID_CATEGORY.intValue())))
            .andExpect(jsonPath("$.[*].idCity").value(hasItem(DEFAULT_ID_CITY.intValue())));
    }

    @Test
    @Transactional
    void getCountChannelClickPageLog() throws Exception {
        // Initialize the database
        countChannelClickPageLogRepository.saveAndFlush(countChannelClickPageLog);

        // Get the countChannelClickPageLog
        restCountChannelClickPageLogMockMvc
            .perform(get(ENTITY_API_URL_ID, countChannelClickPageLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(countChannelClickPageLog.getId().intValue()))
            .andExpect(jsonPath("$.chatId").value(DEFAULT_CHAT_ID.intValue()))
            .andExpect(jsonPath("$.dateLog").value(sameInstant(DEFAULT_DATE_LOG)))
            .andExpect(jsonPath("$.idChannel").value(DEFAULT_ID_CHANNEL.intValue()))
            .andExpect(jsonPath("$.pageNumber").value(DEFAULT_PAGE_NUMBER.intValue()))
            .andExpect(jsonPath("$.idCategory").value(DEFAULT_ID_CATEGORY.intValue()))
            .andExpect(jsonPath("$.idCity").value(DEFAULT_ID_CITY.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingCountChannelClickPageLog() throws Exception {
        // Get the countChannelClickPageLog
        restCountChannelClickPageLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCountChannelClickPageLog() throws Exception {
        // Initialize the database
        countChannelClickPageLogRepository.saveAndFlush(countChannelClickPageLog);

        int databaseSizeBeforeUpdate = countChannelClickPageLogRepository.findAll().size();

        // Update the countChannelClickPageLog
        CountChannelClickPageLog updatedCountChannelClickPageLog = countChannelClickPageLogRepository
            .findById(countChannelClickPageLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedCountChannelClickPageLog are not directly saved in db
        em.detach(updatedCountChannelClickPageLog);
        updatedCountChannelClickPageLog
            .chatId(UPDATED_CHAT_ID)
            .dateLog(UPDATED_DATE_LOG)
            .idChannel(UPDATED_ID_CHANNEL)
            .pageNumber(UPDATED_PAGE_NUMBER)
            .idCategory(UPDATED_ID_CATEGORY)
            .idCity(UPDATED_ID_CITY);

        restCountChannelClickPageLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCountChannelClickPageLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedCountChannelClickPageLog))
            )
            .andExpect(status().isOk());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeUpdate);
        CountChannelClickPageLog testCountChannelClickPageLog = countChannelClickPageLogList.get(countChannelClickPageLogList.size() - 1);
        assertThat(testCountChannelClickPageLog.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testCountChannelClickPageLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
        assertThat(testCountChannelClickPageLog.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testCountChannelClickPageLog.getPageNumber()).isEqualTo(UPDATED_PAGE_NUMBER);
        assertThat(testCountChannelClickPageLog.getIdCategory()).isEqualTo(UPDATED_ID_CATEGORY);
        assertThat(testCountChannelClickPageLog.getIdCity()).isEqualTo(UPDATED_ID_CITY);
    }

    @Test
    @Transactional
    void putNonExistingCountChannelClickPageLog() throws Exception {
        int databaseSizeBeforeUpdate = countChannelClickPageLogRepository.findAll().size();
        countChannelClickPageLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCountChannelClickPageLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, countChannelClickPageLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(countChannelClickPageLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCountChannelClickPageLog() throws Exception {
        int databaseSizeBeforeUpdate = countChannelClickPageLogRepository.findAll().size();
        countChannelClickPageLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCountChannelClickPageLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(countChannelClickPageLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCountChannelClickPageLog() throws Exception {
        int databaseSizeBeforeUpdate = countChannelClickPageLogRepository.findAll().size();
        countChannelClickPageLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCountChannelClickPageLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(countChannelClickPageLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCountChannelClickPageLogWithPatch() throws Exception {
        // Initialize the database
        countChannelClickPageLogRepository.saveAndFlush(countChannelClickPageLog);

        int databaseSizeBeforeUpdate = countChannelClickPageLogRepository.findAll().size();

        // Update the countChannelClickPageLog using partial update
        CountChannelClickPageLog partialUpdatedCountChannelClickPageLog = new CountChannelClickPageLog();
        partialUpdatedCountChannelClickPageLog.setId(countChannelClickPageLog.getId());

        partialUpdatedCountChannelClickPageLog
            .chatId(UPDATED_CHAT_ID)
            .dateLog(UPDATED_DATE_LOG)
            .pageNumber(UPDATED_PAGE_NUMBER)
            .idCategory(UPDATED_ID_CATEGORY)
            .idCity(UPDATED_ID_CITY);

        restCountChannelClickPageLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCountChannelClickPageLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCountChannelClickPageLog))
            )
            .andExpect(status().isOk());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeUpdate);
        CountChannelClickPageLog testCountChannelClickPageLog = countChannelClickPageLogList.get(countChannelClickPageLogList.size() - 1);
        assertThat(testCountChannelClickPageLog.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testCountChannelClickPageLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
        assertThat(testCountChannelClickPageLog.getIdChannel()).isEqualTo(DEFAULT_ID_CHANNEL);
        assertThat(testCountChannelClickPageLog.getPageNumber()).isEqualTo(UPDATED_PAGE_NUMBER);
        assertThat(testCountChannelClickPageLog.getIdCategory()).isEqualTo(UPDATED_ID_CATEGORY);
        assertThat(testCountChannelClickPageLog.getIdCity()).isEqualTo(UPDATED_ID_CITY);
    }

    @Test
    @Transactional
    void fullUpdateCountChannelClickPageLogWithPatch() throws Exception {
        // Initialize the database
        countChannelClickPageLogRepository.saveAndFlush(countChannelClickPageLog);

        int databaseSizeBeforeUpdate = countChannelClickPageLogRepository.findAll().size();

        // Update the countChannelClickPageLog using partial update
        CountChannelClickPageLog partialUpdatedCountChannelClickPageLog = new CountChannelClickPageLog();
        partialUpdatedCountChannelClickPageLog.setId(countChannelClickPageLog.getId());

        partialUpdatedCountChannelClickPageLog
            .chatId(UPDATED_CHAT_ID)
            .dateLog(UPDATED_DATE_LOG)
            .idChannel(UPDATED_ID_CHANNEL)
            .pageNumber(UPDATED_PAGE_NUMBER)
            .idCategory(UPDATED_ID_CATEGORY)
            .idCity(UPDATED_ID_CITY);

        restCountChannelClickPageLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCountChannelClickPageLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCountChannelClickPageLog))
            )
            .andExpect(status().isOk());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeUpdate);
        CountChannelClickPageLog testCountChannelClickPageLog = countChannelClickPageLogList.get(countChannelClickPageLogList.size() - 1);
        assertThat(testCountChannelClickPageLog.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testCountChannelClickPageLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
        assertThat(testCountChannelClickPageLog.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testCountChannelClickPageLog.getPageNumber()).isEqualTo(UPDATED_PAGE_NUMBER);
        assertThat(testCountChannelClickPageLog.getIdCategory()).isEqualTo(UPDATED_ID_CATEGORY);
        assertThat(testCountChannelClickPageLog.getIdCity()).isEqualTo(UPDATED_ID_CITY);
    }

    @Test
    @Transactional
    void patchNonExistingCountChannelClickPageLog() throws Exception {
        int databaseSizeBeforeUpdate = countChannelClickPageLogRepository.findAll().size();
        countChannelClickPageLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCountChannelClickPageLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, countChannelClickPageLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(countChannelClickPageLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCountChannelClickPageLog() throws Exception {
        int databaseSizeBeforeUpdate = countChannelClickPageLogRepository.findAll().size();
        countChannelClickPageLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCountChannelClickPageLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(countChannelClickPageLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCountChannelClickPageLog() throws Exception {
        int databaseSizeBeforeUpdate = countChannelClickPageLogRepository.findAll().size();
        countChannelClickPageLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCountChannelClickPageLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(countChannelClickPageLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CountChannelClickPageLog in the database
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCountChannelClickPageLog() throws Exception {
        // Initialize the database
        countChannelClickPageLogRepository.saveAndFlush(countChannelClickPageLog);

        int databaseSizeBeforeDelete = countChannelClickPageLogRepository.findAll().size();

        // Delete the countChannelClickPageLog
        restCountChannelClickPageLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, countChannelClickPageLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CountChannelClickPageLog> countChannelClickPageLogList = countChannelClickPageLogRepository.findAll();
        assertThat(countChannelClickPageLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
