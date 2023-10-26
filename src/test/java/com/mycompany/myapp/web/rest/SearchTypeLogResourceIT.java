package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.SearchTypeLog;
import com.mycompany.myapp.repository.SearchTypeLogRepository;
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
 * Integration tests for the {@link SearchTypeLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SearchTypeLogResourceIT {

    private static final Long DEFAULT_CHAT_ID = 1L;
    private static final Long UPDATED_CHAT_ID = 2L;

    private static final ZonedDateTime DEFAULT_DATE_LOG = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_LOG = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_INLINE_SEARCH = false;
    private static final Boolean UPDATED_INLINE_SEARCH = true;

    private static final Boolean DEFAULT_PAGE_SEARCH = false;
    private static final Boolean UPDATED_PAGE_SEARCH = true;

    private static final Long DEFAULT_PAGE_NUMBER = 1L;
    private static final Long UPDATED_PAGE_NUMBER = 2L;

    private static final String ENTITY_API_URL = "/api/search-type-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SearchTypeLogRepository searchTypeLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSearchTypeLogMockMvc;

    private SearchTypeLog searchTypeLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SearchTypeLog createEntity(EntityManager em) {
        SearchTypeLog searchTypeLog = new SearchTypeLog()
            .chatId(DEFAULT_CHAT_ID)
            .dateLog(DEFAULT_DATE_LOG)
            .inlineSearch(DEFAULT_INLINE_SEARCH)
            .pageSearch(DEFAULT_PAGE_SEARCH)
            .pageNumber(DEFAULT_PAGE_NUMBER);
        return searchTypeLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SearchTypeLog createUpdatedEntity(EntityManager em) {
        SearchTypeLog searchTypeLog = new SearchTypeLog()
            .chatId(UPDATED_CHAT_ID)
            .dateLog(UPDATED_DATE_LOG)
            .inlineSearch(UPDATED_INLINE_SEARCH)
            .pageSearch(UPDATED_PAGE_SEARCH)
            .pageNumber(UPDATED_PAGE_NUMBER);
        return searchTypeLog;
    }

    @BeforeEach
    public void initTest() {
        searchTypeLog = createEntity(em);
    }

    @Test
    @Transactional
    void createSearchTypeLog() throws Exception {
        int databaseSizeBeforeCreate = searchTypeLogRepository.findAll().size();
        // Create the SearchTypeLog
        restSearchTypeLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(searchTypeLog)))
            .andExpect(status().isCreated());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeCreate + 1);
        SearchTypeLog testSearchTypeLog = searchTypeLogList.get(searchTypeLogList.size() - 1);
        assertThat(testSearchTypeLog.getChatId()).isEqualTo(DEFAULT_CHAT_ID);
        assertThat(testSearchTypeLog.getDateLog()).isEqualTo(DEFAULT_DATE_LOG);
        assertThat(testSearchTypeLog.getInlineSearch()).isEqualTo(DEFAULT_INLINE_SEARCH);
        assertThat(testSearchTypeLog.getPageSearch()).isEqualTo(DEFAULT_PAGE_SEARCH);
        assertThat(testSearchTypeLog.getPageNumber()).isEqualTo(DEFAULT_PAGE_NUMBER);
    }

    @Test
    @Transactional
    void createSearchTypeLogWithExistingId() throws Exception {
        // Create the SearchTypeLog with an existing ID
        searchTypeLog.setId(1L);

        int databaseSizeBeforeCreate = searchTypeLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSearchTypeLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(searchTypeLog)))
            .andExpect(status().isBadRequest());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSearchTypeLogs() throws Exception {
        // Initialize the database
        searchTypeLogRepository.saveAndFlush(searchTypeLog);

        // Get all the searchTypeLogList
        restSearchTypeLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(searchTypeLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].chatId").value(hasItem(DEFAULT_CHAT_ID.intValue())))
            .andExpect(jsonPath("$.[*].dateLog").value(hasItem(sameInstant(DEFAULT_DATE_LOG))))
            .andExpect(jsonPath("$.[*].inlineSearch").value(hasItem(DEFAULT_INLINE_SEARCH.booleanValue())))
            .andExpect(jsonPath("$.[*].pageSearch").value(hasItem(DEFAULT_PAGE_SEARCH.booleanValue())))
            .andExpect(jsonPath("$.[*].pageNumber").value(hasItem(DEFAULT_PAGE_NUMBER.intValue())));
    }

    @Test
    @Transactional
    void getSearchTypeLog() throws Exception {
        // Initialize the database
        searchTypeLogRepository.saveAndFlush(searchTypeLog);

        // Get the searchTypeLog
        restSearchTypeLogMockMvc
            .perform(get(ENTITY_API_URL_ID, searchTypeLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(searchTypeLog.getId().intValue()))
            .andExpect(jsonPath("$.chatId").value(DEFAULT_CHAT_ID.intValue()))
            .andExpect(jsonPath("$.dateLog").value(sameInstant(DEFAULT_DATE_LOG)))
            .andExpect(jsonPath("$.inlineSearch").value(DEFAULT_INLINE_SEARCH.booleanValue()))
            .andExpect(jsonPath("$.pageSearch").value(DEFAULT_PAGE_SEARCH.booleanValue()))
            .andExpect(jsonPath("$.pageNumber").value(DEFAULT_PAGE_NUMBER.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingSearchTypeLog() throws Exception {
        // Get the searchTypeLog
        restSearchTypeLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewSearchTypeLog() throws Exception {
        // Initialize the database
        searchTypeLogRepository.saveAndFlush(searchTypeLog);

        int databaseSizeBeforeUpdate = searchTypeLogRepository.findAll().size();

        // Update the searchTypeLog
        SearchTypeLog updatedSearchTypeLog = searchTypeLogRepository.findById(searchTypeLog.getId()).get();
        // Disconnect from session so that the updates on updatedSearchTypeLog are not directly saved in db
        em.detach(updatedSearchTypeLog);
        updatedSearchTypeLog
            .chatId(UPDATED_CHAT_ID)
            .dateLog(UPDATED_DATE_LOG)
            .inlineSearch(UPDATED_INLINE_SEARCH)
            .pageSearch(UPDATED_PAGE_SEARCH)
            .pageNumber(UPDATED_PAGE_NUMBER);

        restSearchTypeLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSearchTypeLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSearchTypeLog))
            )
            .andExpect(status().isOk());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeUpdate);
        SearchTypeLog testSearchTypeLog = searchTypeLogList.get(searchTypeLogList.size() - 1);
        assertThat(testSearchTypeLog.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testSearchTypeLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
        assertThat(testSearchTypeLog.getInlineSearch()).isEqualTo(UPDATED_INLINE_SEARCH);
        assertThat(testSearchTypeLog.getPageSearch()).isEqualTo(UPDATED_PAGE_SEARCH);
        assertThat(testSearchTypeLog.getPageNumber()).isEqualTo(UPDATED_PAGE_NUMBER);
    }

    @Test
    @Transactional
    void putNonExistingSearchTypeLog() throws Exception {
        int databaseSizeBeforeUpdate = searchTypeLogRepository.findAll().size();
        searchTypeLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSearchTypeLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, searchTypeLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(searchTypeLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSearchTypeLog() throws Exception {
        int databaseSizeBeforeUpdate = searchTypeLogRepository.findAll().size();
        searchTypeLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSearchTypeLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(searchTypeLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSearchTypeLog() throws Exception {
        int databaseSizeBeforeUpdate = searchTypeLogRepository.findAll().size();
        searchTypeLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSearchTypeLogMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(searchTypeLog)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSearchTypeLogWithPatch() throws Exception {
        // Initialize the database
        searchTypeLogRepository.saveAndFlush(searchTypeLog);

        int databaseSizeBeforeUpdate = searchTypeLogRepository.findAll().size();

        // Update the searchTypeLog using partial update
        SearchTypeLog partialUpdatedSearchTypeLog = new SearchTypeLog();
        partialUpdatedSearchTypeLog.setId(searchTypeLog.getId());

        partialUpdatedSearchTypeLog.chatId(UPDATED_CHAT_ID).inlineSearch(UPDATED_INLINE_SEARCH);

        restSearchTypeLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSearchTypeLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSearchTypeLog))
            )
            .andExpect(status().isOk());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeUpdate);
        SearchTypeLog testSearchTypeLog = searchTypeLogList.get(searchTypeLogList.size() - 1);
        assertThat(testSearchTypeLog.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testSearchTypeLog.getDateLog()).isEqualTo(DEFAULT_DATE_LOG);
        assertThat(testSearchTypeLog.getInlineSearch()).isEqualTo(UPDATED_INLINE_SEARCH);
        assertThat(testSearchTypeLog.getPageSearch()).isEqualTo(DEFAULT_PAGE_SEARCH);
        assertThat(testSearchTypeLog.getPageNumber()).isEqualTo(DEFAULT_PAGE_NUMBER);
    }

    @Test
    @Transactional
    void fullUpdateSearchTypeLogWithPatch() throws Exception {
        // Initialize the database
        searchTypeLogRepository.saveAndFlush(searchTypeLog);

        int databaseSizeBeforeUpdate = searchTypeLogRepository.findAll().size();

        // Update the searchTypeLog using partial update
        SearchTypeLog partialUpdatedSearchTypeLog = new SearchTypeLog();
        partialUpdatedSearchTypeLog.setId(searchTypeLog.getId());

        partialUpdatedSearchTypeLog
            .chatId(UPDATED_CHAT_ID)
            .dateLog(UPDATED_DATE_LOG)
            .inlineSearch(UPDATED_INLINE_SEARCH)
            .pageSearch(UPDATED_PAGE_SEARCH)
            .pageNumber(UPDATED_PAGE_NUMBER);

        restSearchTypeLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSearchTypeLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSearchTypeLog))
            )
            .andExpect(status().isOk());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeUpdate);
        SearchTypeLog testSearchTypeLog = searchTypeLogList.get(searchTypeLogList.size() - 1);
        assertThat(testSearchTypeLog.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testSearchTypeLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
        assertThat(testSearchTypeLog.getInlineSearch()).isEqualTo(UPDATED_INLINE_SEARCH);
        assertThat(testSearchTypeLog.getPageSearch()).isEqualTo(UPDATED_PAGE_SEARCH);
        assertThat(testSearchTypeLog.getPageNumber()).isEqualTo(UPDATED_PAGE_NUMBER);
    }

    @Test
    @Transactional
    void patchNonExistingSearchTypeLog() throws Exception {
        int databaseSizeBeforeUpdate = searchTypeLogRepository.findAll().size();
        searchTypeLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSearchTypeLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, searchTypeLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(searchTypeLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSearchTypeLog() throws Exception {
        int databaseSizeBeforeUpdate = searchTypeLogRepository.findAll().size();
        searchTypeLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSearchTypeLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(searchTypeLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSearchTypeLog() throws Exception {
        int databaseSizeBeforeUpdate = searchTypeLogRepository.findAll().size();
        searchTypeLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSearchTypeLogMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(searchTypeLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SearchTypeLog in the database
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSearchTypeLog() throws Exception {
        // Initialize the database
        searchTypeLogRepository.saveAndFlush(searchTypeLog);

        int databaseSizeBeforeDelete = searchTypeLogRepository.findAll().size();

        // Delete the searchTypeLog
        restSearchTypeLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, searchTypeLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SearchTypeLog> searchTypeLogList = searchTypeLogRepository.findAll();
        assertThat(searchTypeLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
