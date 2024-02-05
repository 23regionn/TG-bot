package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ShowChannelsInCategoryLog;
import com.mycompany.myapp.repository.ShowChannelsInCategoryLogRepository;
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
 * Integration tests for the {@link ShowChannelsInCategoryLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ShowChannelsInCategoryLogResourceIT {

    private static final Long DEFAULT_ID_CHANNEL = 1L;
    private static final Long UPDATED_ID_CHANNEL = 2L;

    private static final String DEFAULT_NAME_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CHANNEL = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_CATEGORY = 1L;
    private static final Long UPDATED_ID_CATEGORY = 2L;

    private static final String DEFAULT_NAME_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CATEGORY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SHOW_CHANNEL = false;
    private static final Boolean UPDATED_IS_SHOW_CHANNEL = true;

    private static final Double DEFAULT_SCORE_CHANNEL = 1D;
    private static final Double UPDATED_SCORE_CHANNEL = 2D;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_LOG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_LOG = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/show-channels-in-category-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ShowChannelsInCategoryLogRepository showChannelsInCategoryLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restShowChannelsInCategoryLogMockMvc;

    private ShowChannelsInCategoryLog showChannelsInCategoryLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShowChannelsInCategoryLog createEntity(EntityManager em) {
        ShowChannelsInCategoryLog showChannelsInCategoryLog = new ShowChannelsInCategoryLog()
            .idChannel(DEFAULT_ID_CHANNEL)
            .nameChannel(DEFAULT_NAME_CHANNEL)
            .idCategory(DEFAULT_ID_CATEGORY)
            .nameCategory(DEFAULT_NAME_CATEGORY)
            .isShowChannel(DEFAULT_IS_SHOW_CHANNEL)
            .scoreChannel(DEFAULT_SCORE_CHANNEL)
            .comment(DEFAULT_COMMENT)
            .dateLog(DEFAULT_DATE_LOG);
        return showChannelsInCategoryLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShowChannelsInCategoryLog createUpdatedEntity(EntityManager em) {
        ShowChannelsInCategoryLog showChannelsInCategoryLog = new ShowChannelsInCategoryLog()
            .idChannel(UPDATED_ID_CHANNEL)
            .nameChannel(UPDATED_NAME_CHANNEL)
            .idCategory(UPDATED_ID_CATEGORY)
            .nameCategory(UPDATED_NAME_CATEGORY)
            .isShowChannel(UPDATED_IS_SHOW_CHANNEL)
            .scoreChannel(UPDATED_SCORE_CHANNEL)
            .comment(UPDATED_COMMENT)
            .dateLog(UPDATED_DATE_LOG);
        return showChannelsInCategoryLog;
    }

    @BeforeEach
    public void initTest() {
        showChannelsInCategoryLog = createEntity(em);
    }

    @Test
    @Transactional
    void createShowChannelsInCategoryLog() throws Exception {
        int databaseSizeBeforeCreate = showChannelsInCategoryLogRepository.findAll().size();
        // Create the ShowChannelsInCategoryLog
        restShowChannelsInCategoryLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCategoryLog))
            )
            .andExpect(status().isCreated());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeCreate + 1);
        ShowChannelsInCategoryLog testShowChannelsInCategoryLog = showChannelsInCategoryLogList.get(
            showChannelsInCategoryLogList.size() - 1
        );
        assertThat(testShowChannelsInCategoryLog.getIdChannel()).isEqualTo(DEFAULT_ID_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getNameChannel()).isEqualTo(DEFAULT_NAME_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getIdCategory()).isEqualTo(DEFAULT_ID_CATEGORY);
        assertThat(testShowChannelsInCategoryLog.getNameCategory()).isEqualTo(DEFAULT_NAME_CATEGORY);
        assertThat(testShowChannelsInCategoryLog.getIsShowChannel()).isEqualTo(DEFAULT_IS_SHOW_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getScoreChannel()).isEqualTo(DEFAULT_SCORE_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testShowChannelsInCategoryLog.getDateLog()).isEqualTo(DEFAULT_DATE_LOG);
    }

    @Test
    @Transactional
    void createShowChannelsInCategoryLogWithExistingId() throws Exception {
        // Create the ShowChannelsInCategoryLog with an existing ID
        showChannelsInCategoryLog.setId(1L);

        int databaseSizeBeforeCreate = showChannelsInCategoryLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restShowChannelsInCategoryLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCategoryLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllShowChannelsInCategoryLogs() throws Exception {
        // Initialize the database
        showChannelsInCategoryLogRepository.saveAndFlush(showChannelsInCategoryLog);

        // Get all the showChannelsInCategoryLogList
        restShowChannelsInCategoryLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(showChannelsInCategoryLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idChannel").value(hasItem(DEFAULT_ID_CHANNEL.intValue())))
            .andExpect(jsonPath("$.[*].nameChannel").value(hasItem(DEFAULT_NAME_CHANNEL)))
            .andExpect(jsonPath("$.[*].idCategory").value(hasItem(DEFAULT_ID_CATEGORY.intValue())))
            .andExpect(jsonPath("$.[*].nameCategory").value(hasItem(DEFAULT_NAME_CATEGORY)))
            .andExpect(jsonPath("$.[*].isShowChannel").value(hasItem(DEFAULT_IS_SHOW_CHANNEL.booleanValue())))
            .andExpect(jsonPath("$.[*].scoreChannel").value(hasItem(DEFAULT_SCORE_CHANNEL.doubleValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateLog").value(hasItem(DEFAULT_DATE_LOG.toString())));
    }

    @Test
    @Transactional
    void getShowChannelsInCategoryLog() throws Exception {
        // Initialize the database
        showChannelsInCategoryLogRepository.saveAndFlush(showChannelsInCategoryLog);

        // Get the showChannelsInCategoryLog
        restShowChannelsInCategoryLogMockMvc
            .perform(get(ENTITY_API_URL_ID, showChannelsInCategoryLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(showChannelsInCategoryLog.getId().intValue()))
            .andExpect(jsonPath("$.idChannel").value(DEFAULT_ID_CHANNEL.intValue()))
            .andExpect(jsonPath("$.nameChannel").value(DEFAULT_NAME_CHANNEL))
            .andExpect(jsonPath("$.idCategory").value(DEFAULT_ID_CATEGORY.intValue()))
            .andExpect(jsonPath("$.nameCategory").value(DEFAULT_NAME_CATEGORY))
            .andExpect(jsonPath("$.isShowChannel").value(DEFAULT_IS_SHOW_CHANNEL.booleanValue()))
            .andExpect(jsonPath("$.scoreChannel").value(DEFAULT_SCORE_CHANNEL.doubleValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.dateLog").value(DEFAULT_DATE_LOG.toString()));
    }

    @Test
    @Transactional
    void getNonExistingShowChannelsInCategoryLog() throws Exception {
        // Get the showChannelsInCategoryLog
        restShowChannelsInCategoryLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewShowChannelsInCategoryLog() throws Exception {
        // Initialize the database
        showChannelsInCategoryLogRepository.saveAndFlush(showChannelsInCategoryLog);

        int databaseSizeBeforeUpdate = showChannelsInCategoryLogRepository.findAll().size();

        // Update the showChannelsInCategoryLog
        ShowChannelsInCategoryLog updatedShowChannelsInCategoryLog = showChannelsInCategoryLogRepository
            .findById(showChannelsInCategoryLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedShowChannelsInCategoryLog are not directly saved in db
        em.detach(updatedShowChannelsInCategoryLog);
        updatedShowChannelsInCategoryLog
            .idChannel(UPDATED_ID_CHANNEL)
            .nameChannel(UPDATED_NAME_CHANNEL)
            .idCategory(UPDATED_ID_CATEGORY)
            .nameCategory(UPDATED_NAME_CATEGORY)
            .isShowChannel(UPDATED_IS_SHOW_CHANNEL)
            .scoreChannel(UPDATED_SCORE_CHANNEL)
            .comment(UPDATED_COMMENT)
            .dateLog(UPDATED_DATE_LOG);

        restShowChannelsInCategoryLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedShowChannelsInCategoryLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedShowChannelsInCategoryLog))
            )
            .andExpect(status().isOk());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeUpdate);
        ShowChannelsInCategoryLog testShowChannelsInCategoryLog = showChannelsInCategoryLogList.get(
            showChannelsInCategoryLogList.size() - 1
        );
        assertThat(testShowChannelsInCategoryLog.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getNameChannel()).isEqualTo(UPDATED_NAME_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getIdCategory()).isEqualTo(UPDATED_ID_CATEGORY);
        assertThat(testShowChannelsInCategoryLog.getNameCategory()).isEqualTo(UPDATED_NAME_CATEGORY);
        assertThat(testShowChannelsInCategoryLog.getIsShowChannel()).isEqualTo(UPDATED_IS_SHOW_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getScoreChannel()).isEqualTo(UPDATED_SCORE_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testShowChannelsInCategoryLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
    }

    @Test
    @Transactional
    void putNonExistingShowChannelsInCategoryLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCategoryLogRepository.findAll().size();
        showChannelsInCategoryLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShowChannelsInCategoryLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, showChannelsInCategoryLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCategoryLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchShowChannelsInCategoryLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCategoryLogRepository.findAll().size();
        showChannelsInCategoryLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShowChannelsInCategoryLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCategoryLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamShowChannelsInCategoryLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCategoryLogRepository.findAll().size();
        showChannelsInCategoryLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShowChannelsInCategoryLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCategoryLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateShowChannelsInCategoryLogWithPatch() throws Exception {
        // Initialize the database
        showChannelsInCategoryLogRepository.saveAndFlush(showChannelsInCategoryLog);

        int databaseSizeBeforeUpdate = showChannelsInCategoryLogRepository.findAll().size();

        // Update the showChannelsInCategoryLog using partial update
        ShowChannelsInCategoryLog partialUpdatedShowChannelsInCategoryLog = new ShowChannelsInCategoryLog();
        partialUpdatedShowChannelsInCategoryLog.setId(showChannelsInCategoryLog.getId());

        partialUpdatedShowChannelsInCategoryLog
            .idChannel(UPDATED_ID_CHANNEL)
            .nameChannel(UPDATED_NAME_CHANNEL)
            .idCategory(UPDATED_ID_CATEGORY)
            .dateLog(UPDATED_DATE_LOG);

        restShowChannelsInCategoryLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedShowChannelsInCategoryLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShowChannelsInCategoryLog))
            )
            .andExpect(status().isOk());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeUpdate);
        ShowChannelsInCategoryLog testShowChannelsInCategoryLog = showChannelsInCategoryLogList.get(
            showChannelsInCategoryLogList.size() - 1
        );
        assertThat(testShowChannelsInCategoryLog.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getNameChannel()).isEqualTo(UPDATED_NAME_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getIdCategory()).isEqualTo(UPDATED_ID_CATEGORY);
        assertThat(testShowChannelsInCategoryLog.getNameCategory()).isEqualTo(DEFAULT_NAME_CATEGORY);
        assertThat(testShowChannelsInCategoryLog.getIsShowChannel()).isEqualTo(DEFAULT_IS_SHOW_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getScoreChannel()).isEqualTo(DEFAULT_SCORE_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testShowChannelsInCategoryLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
    }

    @Test
    @Transactional
    void fullUpdateShowChannelsInCategoryLogWithPatch() throws Exception {
        // Initialize the database
        showChannelsInCategoryLogRepository.saveAndFlush(showChannelsInCategoryLog);

        int databaseSizeBeforeUpdate = showChannelsInCategoryLogRepository.findAll().size();

        // Update the showChannelsInCategoryLog using partial update
        ShowChannelsInCategoryLog partialUpdatedShowChannelsInCategoryLog = new ShowChannelsInCategoryLog();
        partialUpdatedShowChannelsInCategoryLog.setId(showChannelsInCategoryLog.getId());

        partialUpdatedShowChannelsInCategoryLog
            .idChannel(UPDATED_ID_CHANNEL)
            .nameChannel(UPDATED_NAME_CHANNEL)
            .idCategory(UPDATED_ID_CATEGORY)
            .nameCategory(UPDATED_NAME_CATEGORY)
            .isShowChannel(UPDATED_IS_SHOW_CHANNEL)
            .scoreChannel(UPDATED_SCORE_CHANNEL)
            .comment(UPDATED_COMMENT)
            .dateLog(UPDATED_DATE_LOG);

        restShowChannelsInCategoryLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedShowChannelsInCategoryLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedShowChannelsInCategoryLog))
            )
            .andExpect(status().isOk());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeUpdate);
        ShowChannelsInCategoryLog testShowChannelsInCategoryLog = showChannelsInCategoryLogList.get(
            showChannelsInCategoryLogList.size() - 1
        );
        assertThat(testShowChannelsInCategoryLog.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getNameChannel()).isEqualTo(UPDATED_NAME_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getIdCategory()).isEqualTo(UPDATED_ID_CATEGORY);
        assertThat(testShowChannelsInCategoryLog.getNameCategory()).isEqualTo(UPDATED_NAME_CATEGORY);
        assertThat(testShowChannelsInCategoryLog.getIsShowChannel()).isEqualTo(UPDATED_IS_SHOW_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getScoreChannel()).isEqualTo(UPDATED_SCORE_CHANNEL);
        assertThat(testShowChannelsInCategoryLog.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testShowChannelsInCategoryLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
    }

    @Test
    @Transactional
    void patchNonExistingShowChannelsInCategoryLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCategoryLogRepository.findAll().size();
        showChannelsInCategoryLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restShowChannelsInCategoryLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, showChannelsInCategoryLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCategoryLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchShowChannelsInCategoryLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCategoryLogRepository.findAll().size();
        showChannelsInCategoryLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShowChannelsInCategoryLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCategoryLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamShowChannelsInCategoryLog() throws Exception {
        int databaseSizeBeforeUpdate = showChannelsInCategoryLogRepository.findAll().size();
        showChannelsInCategoryLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restShowChannelsInCategoryLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(showChannelsInCategoryLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ShowChannelsInCategoryLog in the database
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteShowChannelsInCategoryLog() throws Exception {
        // Initialize the database
        showChannelsInCategoryLogRepository.saveAndFlush(showChannelsInCategoryLog);

        int databaseSizeBeforeDelete = showChannelsInCategoryLogRepository.findAll().size();

        // Delete the showChannelsInCategoryLog
        restShowChannelsInCategoryLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, showChannelsInCategoryLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ShowChannelsInCategoryLog> showChannelsInCategoryLogList = showChannelsInCategoryLogRepository.findAll();
        assertThat(showChannelsInCategoryLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
