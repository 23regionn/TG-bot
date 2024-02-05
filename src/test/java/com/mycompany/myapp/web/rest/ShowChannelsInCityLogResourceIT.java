package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ShowChannelsInCityLog;
import com.mycompany.myapp.repository.ShowChannelsInCityLogRepository;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link ShowChannelsInCityLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ShowChannelsInCityLogResourceIT {

    private static final Long DEFAULT_ID_CHANNEL = 1L;
    private static final Long UPDATED_ID_CHANNEL = 2L;

    private static final String DEFAULT_NAME_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CHANNEL = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_CATEGORY = 1L;
    private static final Long UPDATED_ID_CATEGORY = 2L;

    private static final String DEFAULT_NAME_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CATEGORY = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_CITY = 1L;
    private static final Long UPDATED_ID_CITY = 2L;

    private static final String DEFAULT_NAME_CITY = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CITY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SHOW_CHANNEL = false;
    private static final Boolean UPDATED_IS_SHOW_CHANNEL = true;

    private static final Double DEFAULT_SCORE_CHANNEL = 1D;
    private static final Double UPDATED_SCORE_CHANNEL = 2D;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_LOG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_LOG = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/show-channels-in-city-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ShowChannelsInCityLogRepository showChannelsInCityLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShowChannelsInCityLogMockMvc;

    private ShowChannelsInCityLog showChannelsInCityLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShowChannelsInCityLog createEntity(EntityManager em) {
        ShowChannelsInCityLog showChannelsInCityLog = new ShowChannelsInCityLog()
            .idChannel(DEFAULT_ID_CHANNEL)
            .nameChannel(DEFAULT_NAME_CHANNEL)
            .idCategory(DEFAULT_ID_CATEGORY)
            .nameCategory(DEFAULT_NAME_CATEGORY)
            .idCity(DEFAULT_ID_CITY)
            .nameCity(DEFAULT_NAME_CITY)
            .isShowChannel(DEFAULT_IS_SHOW_CHANNEL)
            .scoreChannel(DEFAULT_SCORE_CHANNEL)
            .comment(DEFAULT_COMMENT)
            .dateLog(DEFAULT_DATE_LOG);
        return showChannelsInCityLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShowChannelsInCityLog createUpdatedEntity(EntityManager em) {
        ShowChannelsInCityLog showChannelsInCityLog = new ShowChannelsInCityLog()
            .idChannel(UPDATED_ID_CHANNEL)
            .nameChannel(UPDATED_NAME_CHANNEL)
            .idCategory(UPDATED_ID_CATEGORY)
            .nameCategory(UPDATED_NAME_CATEGORY)
            .idCity(UPDATED_ID_CITY)
            .nameCity(UPDATED_NAME_CITY)
            .isShowChannel(UPDATED_IS_SHOW_CHANNEL)
            .scoreChannel(UPDATED_SCORE_CHANNEL)
            .comment(UPDATED_COMMENT)
            .dateLog(UPDATED_DATE_LOG);
        return showChannelsInCityLog;
    }

    @BeforeEach
    public void initTest() {
        showChannelsInCityLog = createEntity(em);
    }

    @Test
    @Transactional
    void createShowChannelsInCityLog() throws Exception {
        int databaseSizeBeforeCreate = showChannelsInCityLogRepository.findAll().size();
        // Create the ShowChannelsInCityLog
        restShowChannelsInCityLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCityLog))
            )
            .andExpect(status().isCreated());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeCreate + 1);
        ShowChannelsInCityLog testShowChannelsInCityLog = showChannelsInCityLogList.get(showChannelsInCityLogList.size() - 1);
        assertThat(testShowChannelsInCityLog.getIdChannel()).isEqualTo(DEFAULT_ID_CHANNEL);
        assertThat(testShowChannelsInCityLog.getNameChannel()).isEqualTo(DEFAULT_NAME_CHANNEL);
        assertThat(testShowChannelsInCityLog.getIdCategory()).isEqualTo(DEFAULT_ID_CATEGORY);
        assertThat(testShowChannelsInCityLog.getNameCategory()).isEqualTo(DEFAULT_NAME_CATEGORY);
        assertThat(testShowChannelsInCityLog.getIdCity()).isEqualTo(DEFAULT_ID_CITY);
        assertThat(testShowChannelsInCityLog.getNameCity()).isEqualTo(DEFAULT_NAME_CITY);
        assertThat(testShowChannelsInCityLog.getIsShowChannel()).isEqualTo(DEFAULT_IS_SHOW_CHANNEL);
        assertThat(testShowChannelsInCityLog.getScoreChannel()).isEqualTo(DEFAULT_SCORE_CHANNEL);
        assertThat(testShowChannelsInCityLog.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testShowChannelsInCityLog.getDateLog()).isEqualTo(DEFAULT_DATE_LOG);
    }

    @Test
    @Transactional
    void createShowChannelsInCityLogWithExistingId() throws Exception {
        // Create the ShowChannelsInCityLog with an existing ID
        showChannelsInCityLog.setId(1L);

        int databaseSizeBeforeCreate = showChannelsInCityLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restShowChannelsInCityLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCityLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllShowChannelsInCityLogs() throws Exception {
        // Initialize the database
        showChannelsInCityLogRepository.saveAndFlush(showChannelsInCityLog);

        // Get all the showChannelsInCityLogList
        restShowChannelsInCityLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(showChannelsInCityLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idChannel").value(hasItem(DEFAULT_ID_CHANNEL.intValue())))
            .andExpect(jsonPath("$.[*].nameChannel").value(hasItem(DEFAULT_NAME_CHANNEL)))
            .andExpect(jsonPath("$.[*].idCategory").value(hasItem(DEFAULT_ID_CATEGORY.intValue())))
            .andExpect(jsonPath("$.[*].nameCategory").value(hasItem(DEFAULT_NAME_CATEGORY)))
            .andExpect(jsonPath("$.[*].idCity").value(hasItem(DEFAULT_ID_CITY.intValue())))
            .andExpect(jsonPath("$.[*].nameCity").value(hasItem(DEFAULT_NAME_CITY)))
            .andExpect(jsonPath("$.[*].isShowChannel").value(hasItem(DEFAULT_IS_SHOW_CHANNEL.booleanValue())))
            .andExpect(jsonPath("$.[*].scoreChannel").value(hasItem(DEFAULT_SCORE_CHANNEL.doubleValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateLog").value(hasItem(DEFAULT_DATE_LOG.toString())));
    }

    @Test
    @Transactional
    void getShowChannelsInCityLog() throws Exception {
        // Initialize the database
        showChannelsInCityLogRepository.saveAndFlush(showChannelsInCityLog);

        // Get the showChannelsInCityLog
        restShowChannelsInCityLogMockMvc
            .perform(get(ENTITY_API_URL_ID, showChannelsInCityLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(showChannelsInCityLog.getId().intValue()))
            .andExpect(jsonPath("$.idChannel").value(DEFAULT_ID_CHANNEL.intValue()))
            .andExpect(jsonPath("$.nameChannel").value(DEFAULT_NAME_CHANNEL))
            .andExpect(jsonPath("$.idCategory").value(DEFAULT_ID_CATEGORY.intValue()))
            .andExpect(jsonPath("$.nameCategory").value(DEFAULT_NAME_CATEGORY))
            .andExpect(jsonPath("$.idCity").value(DEFAULT_ID_CITY.intValue()))
            .andExpect(jsonPath("$.nameCity").value(DEFAULT_NAME_CITY))
            .andExpect(jsonPath("$.isShowChannel").value(DEFAULT_IS_SHOW_CHANNEL.booleanValue()))
            .andExpect(jsonPath("$.scoreChannel").value(DEFAULT_SCORE_CHANNEL.doubleValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.dateLog").value(DEFAULT_DATE_LOG.toString()));
    }

    @Test
    @Transactional
    void getNonExistingShowChannelsInCityLog() throws Exception {
        // Get the showChannelsInCityLog
        restShowChannelsInCityLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewShowChannelsInCityLog() throws Exception {
        // Initialize the database
        showChannelsInCityLogRepository.saveAndFlush(showChannelsInCityLog);

        int databaseSizeBeforeUpdate = showChannelsInCityLogRepository.findAll().size();

        // Update the showChannelsInCityLog
        ShowChannelsInCityLog updatedShowChannelsInCityLog = showChannelsInCityLogRepository.findById(showChannelsInCityLog.getId()).get();
        // Disconnect from session so that the updates on updatedShowChannelsInCityLog are not directly saved in db
        em.detach(updatedShowChannelsInCityLog);
        updatedShowChannelsInCityLog
            .idChannel(UPDATED_ID_CHANNEL)
            .nameChannel(UPDATED_NAME_CHANNEL)
            .idCategory(UPDATED_ID_CATEGORY)
            .nameCategory(UPDATED_NAME_CATEGORY)
            .idCity(UPDATED_ID_CITY)
            .nameCity(UPDATED_NAME_CITY)
            .isShowChannel(UPDATED_IS_SHOW_CHANNEL)
            .scoreChannel(UPDATED_SCORE_CHANNEL)
            .comment(UPDATED_COMMENT)
            .dateLog(UPDATED_DATE_LOG);

        restShowChannelsInCityLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedShowChannelsInCityLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedShowChannelsInCityLog))
            )
            .andExpect(status().isOk());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeUpdate);
        ShowChannelsInCityLog testShowChannelsInCityLog = showChannelsInCityLogList.get(showChannelsInCityLogList.size() - 1);
        assertThat(testShowChannelsInCityLog.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testShowChannelsInCityLog.getNameChannel()).isEqualTo(UPDATED_NAME_CHANNEL);
        assertThat(testShowChannelsInCityLog.getIdCategory()).isEqualTo(UPDATED_ID_CATEGORY);
        assertThat(testShowChannelsInCityLog.getNameCategory()).isEqualTo(UPDATED_NAME_CATEGORY);
        assertThat(testShowChannelsInCityLog.getIdCity()).isEqualTo(UPDATED_ID_CITY);
        assertThat(testShowChannelsInCityLog.getNameCity()).isEqualTo(UPDATED_NAME_CITY);
        assertThat(testShowChannelsInCityLog.getIsShowChannel()).isEqualTo(UPDATED_IS_SHOW_CHANNEL);
        assertThat(testShowChannelsInCityLog.getScoreChannel()).isEqualTo(UPDATED_SCORE_CHANNEL);
        assertThat(testShowChannelsInCityLog.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testShowChannelsInCityLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
    }

    @Test
    @Transactional
    void putNonExistingShowChannelsInCityLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCityLogRepository.findAll().size();
        showChannelsInCityLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShowChannelsInCityLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, showChannelsInCityLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCityLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchShowChannelsInCityLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCityLogRepository.findAll().size();
        showChannelsInCityLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShowChannelsInCityLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCityLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamShowChannelsInCityLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCityLogRepository.findAll().size();
        showChannelsInCityLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShowChannelsInCityLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCityLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateShowChannelsInCityLogWithPatch() throws Exception {
        // Initialize the database
        showChannelsInCityLogRepository.saveAndFlush(showChannelsInCityLog);

        int databaseSizeBeforeUpdate = showChannelsInCityLogRepository.findAll().size();

        // Update the showChannelsInCityLog using partial update
        ShowChannelsInCityLog partialUpdatedShowChannelsInCityLog = new ShowChannelsInCityLog();
        partialUpdatedShowChannelsInCityLog.setId(showChannelsInCityLog.getId());

        partialUpdatedShowChannelsInCityLog.dateLog(UPDATED_DATE_LOG);

        restShowChannelsInCityLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedShowChannelsInCityLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShowChannelsInCityLog))
            )
            .andExpect(status().isOk());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeUpdate);
        ShowChannelsInCityLog testShowChannelsInCityLog = showChannelsInCityLogList.get(showChannelsInCityLogList.size() - 1);
        assertThat(testShowChannelsInCityLog.getIdChannel()).isEqualTo(DEFAULT_ID_CHANNEL);
        assertThat(testShowChannelsInCityLog.getNameChannel()).isEqualTo(DEFAULT_NAME_CHANNEL);
        assertThat(testShowChannelsInCityLog.getIdCategory()).isEqualTo(DEFAULT_ID_CATEGORY);
        assertThat(testShowChannelsInCityLog.getNameCategory()).isEqualTo(DEFAULT_NAME_CATEGORY);
        assertThat(testShowChannelsInCityLog.getIdCity()).isEqualTo(DEFAULT_ID_CITY);
        assertThat(testShowChannelsInCityLog.getNameCity()).isEqualTo(DEFAULT_NAME_CITY);
        assertThat(testShowChannelsInCityLog.getIsShowChannel()).isEqualTo(DEFAULT_IS_SHOW_CHANNEL);
        assertThat(testShowChannelsInCityLog.getScoreChannel()).isEqualTo(DEFAULT_SCORE_CHANNEL);
        assertThat(testShowChannelsInCityLog.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testShowChannelsInCityLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
    }

    @Test
    @Transactional
    void fullUpdateShowChannelsInCityLogWithPatch() throws Exception {
        // Initialize the database
        showChannelsInCityLogRepository.saveAndFlush(showChannelsInCityLog);

        int databaseSizeBeforeUpdate = showChannelsInCityLogRepository.findAll().size();

        // Update the showChannelsInCityLog using partial update
        ShowChannelsInCityLog partialUpdatedShowChannelsInCityLog = new ShowChannelsInCityLog();
        partialUpdatedShowChannelsInCityLog.setId(showChannelsInCityLog.getId());

        partialUpdatedShowChannelsInCityLog
            .idChannel(UPDATED_ID_CHANNEL)
            .nameChannel(UPDATED_NAME_CHANNEL)
            .idCategory(UPDATED_ID_CATEGORY)
            .nameCategory(UPDATED_NAME_CATEGORY)
            .idCity(UPDATED_ID_CITY)
            .nameCity(UPDATED_NAME_CITY)
            .isShowChannel(UPDATED_IS_SHOW_CHANNEL)
            .scoreChannel(UPDATED_SCORE_CHANNEL)
            .comment(UPDATED_COMMENT)
            .dateLog(UPDATED_DATE_LOG);

        restShowChannelsInCityLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedShowChannelsInCityLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShowChannelsInCityLog))
            )
            .andExpect(status().isOk());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeUpdate);
        ShowChannelsInCityLog testShowChannelsInCityLog = showChannelsInCityLogList.get(showChannelsInCityLogList.size() - 1);
        assertThat(testShowChannelsInCityLog.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testShowChannelsInCityLog.getNameChannel()).isEqualTo(UPDATED_NAME_CHANNEL);
        assertThat(testShowChannelsInCityLog.getIdCategory()).isEqualTo(UPDATED_ID_CATEGORY);
        assertThat(testShowChannelsInCityLog.getNameCategory()).isEqualTo(UPDATED_NAME_CATEGORY);
        assertThat(testShowChannelsInCityLog.getIdCity()).isEqualTo(UPDATED_ID_CITY);
        assertThat(testShowChannelsInCityLog.getNameCity()).isEqualTo(UPDATED_NAME_CITY);
        assertThat(testShowChannelsInCityLog.getIsShowChannel()).isEqualTo(UPDATED_IS_SHOW_CHANNEL);
        assertThat(testShowChannelsInCityLog.getScoreChannel()).isEqualTo(UPDATED_SCORE_CHANNEL);
        assertThat(testShowChannelsInCityLog.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testShowChannelsInCityLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
    }

    @Test
    @Transactional
    void patchNonExistingShowChannelsInCityLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCityLogRepository.findAll().size();
        showChannelsInCityLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShowChannelsInCityLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, showChannelsInCityLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCityLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchShowChannelsInCityLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCityLogRepository.findAll().size();
        showChannelsInCityLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShowChannelsInCityLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCityLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamShowChannelsInCityLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCityLogRepository.findAll().size();
        showChannelsInCityLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShowChannelsInCityLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCityLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ShowChannelsInCityLog in the database
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteShowChannelsInCityLog() throws Exception {
        // Initialize the database
        showChannelsInCityLogRepository.saveAndFlush(showChannelsInCityLog);

        int databaseSizeBeforeDelete = showChannelsInCityLogRepository.findAll().size();

        // Delete the showChannelsInCityLog
        restShowChannelsInCityLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, showChannelsInCityLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShowChannelsInCityLog> showChannelsInCityLogList = showChannelsInCityLogRepository.findAll();
        assertThat(showChannelsInCityLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
