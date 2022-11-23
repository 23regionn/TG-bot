package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ChanellLog;
import com.mycompany.myapp.repository.ChanellLogRepository;
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
 * Integration tests for the {@link ChanellLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChanellLogResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final Double DEFAULT_SCORE = 1D;
    private static final Double UPDATED_SCORE = 2D;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_COUNT_SUBSCRIBERS = 1L;
    private static final Long UPDATED_COUNT_SUBSCRIBERS = 2L;

    private static final Double DEFAULT_QUAILITY_FROM_ANOTHER_SOURCES = 1D;
    private static final Double UPDATED_QUAILITY_FROM_ANOTHER_SOURCES = 2D;

    private static final Double DEFAULT_PRICE_DIAPOZON = 1D;
    private static final Double UPDATED_PRICE_DIAPOZON = 2D;

    private static final Boolean DEFAULT_IS_MODERATE = false;
    private static final Boolean UPDATED_IS_MODERATE = true;

    private static final Boolean DEFAULT_SHOW_CHANELL_IN_TOP_BY_CATEGORY = false;
    private static final Boolean UPDATED_SHOW_CHANELL_IN_TOP_BY_CATEGORY = true;

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETE = false;
    private static final Boolean UPDATED_IS_DELETE = true;

    private static final ZonedDateTime DEFAULT_CURRENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CURRENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final String ENTITY_API_URL = "/api/chanell-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ChanellLogRepository chanellLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChanellLogMockMvc;

    private ChanellLog chanellLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChanellLog createEntity(EntityManager em) {
        ChanellLog chanellLog = new ChanellLog()
            .name(DEFAULT_NAME)
            .link(DEFAULT_LINK)
            .score(DEFAULT_SCORE)
            .status(DEFAULT_STATUS)
            .countSubscribers(DEFAULT_COUNT_SUBSCRIBERS)
            .quailityFromAnotherSources(DEFAULT_QUAILITY_FROM_ANOTHER_SOURCES)
            .priceDiapozon(DEFAULT_PRICE_DIAPOZON)
            .isModerate(DEFAULT_IS_MODERATE)
            .showChanellInTopByCategory(DEFAULT_SHOW_CHANELL_IN_TOP_BY_CATEGORY)
            .region(DEFAULT_REGION)
            .city(DEFAULT_CITY)
            .isDelete(DEFAULT_IS_DELETE)
            .currentDate(DEFAULT_CURRENT_DATE)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return chanellLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChanellLog createUpdatedEntity(EntityManager em) {
        ChanellLog chanellLog = new ChanellLog()
            .name(UPDATED_NAME)
            .link(UPDATED_LINK)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .countSubscribers(UPDATED_COUNT_SUBSCRIBERS)
            .quailityFromAnotherSources(UPDATED_QUAILITY_FROM_ANOTHER_SOURCES)
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .isModerate(UPDATED_IS_MODERATE)
            .showChanellInTopByCategory(UPDATED_SHOW_CHANELL_IN_TOP_BY_CATEGORY)
            .region(UPDATED_REGION)
            .city(UPDATED_CITY)
            .isDelete(UPDATED_IS_DELETE)
            .currentDate(UPDATED_CURRENT_DATE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return chanellLog;
    }

    @BeforeEach
    public void initTest() {
        chanellLog = createEntity(em);
    }

    @Test
    @Transactional
    void createChanellLog() throws Exception {
        int databaseSizeBeforeCreate = chanellLogRepository.findAll().size();
        // Create the ChanellLog
        restChanellLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(chanellLog)))
            .andExpect(status().isCreated());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeCreate + 1);
        ChanellLog testChanellLog = chanellLogList.get(chanellLogList.size() - 1);
        assertThat(testChanellLog.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testChanellLog.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testChanellLog.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testChanellLog.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testChanellLog.getCountSubscribers()).isEqualTo(DEFAULT_COUNT_SUBSCRIBERS);
        assertThat(testChanellLog.getQuailityFromAnotherSources()).isEqualTo(DEFAULT_QUAILITY_FROM_ANOTHER_SOURCES);
        assertThat(testChanellLog.getPriceDiapozon()).isEqualTo(DEFAULT_PRICE_DIAPOZON);
        assertThat(testChanellLog.getIsModerate()).isEqualTo(DEFAULT_IS_MODERATE);
        assertThat(testChanellLog.getShowChanellInTopByCategory()).isEqualTo(DEFAULT_SHOW_CHANELL_IN_TOP_BY_CATEGORY);
        assertThat(testChanellLog.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testChanellLog.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testChanellLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testChanellLog.getCurrentDate()).isEqualTo(DEFAULT_CURRENT_DATE);
        assertThat(testChanellLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testChanellLog.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testChanellLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testChanellLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testChanellLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createChanellLogWithExistingId() throws Exception {
        // Create the ChanellLog with an existing ID
        chanellLog.setId(1L);

        int databaseSizeBeforeCreate = chanellLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restChanellLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(chanellLog)))
            .andExpect(status().isBadRequest());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllChanellLogs() throws Exception {
        // Initialize the database
        chanellLogRepository.saveAndFlush(chanellLog);

        // Get all the chanellLogList
        restChanellLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chanellLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].countSubscribers").value(hasItem(DEFAULT_COUNT_SUBSCRIBERS.intValue())))
            .andExpect(jsonPath("$.[*].quailityFromAnotherSources").value(hasItem(DEFAULT_QUAILITY_FROM_ANOTHER_SOURCES.doubleValue())))
            .andExpect(jsonPath("$.[*].priceDiapozon").value(hasItem(DEFAULT_PRICE_DIAPOZON.doubleValue())))
            .andExpect(jsonPath("$.[*].isModerate").value(hasItem(DEFAULT_IS_MODERATE.booleanValue())))
            .andExpect(jsonPath("$.[*].showChanellInTopByCategory").value(hasItem(DEFAULT_SHOW_CHANELL_IN_TOP_BY_CATEGORY.booleanValue())))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].currentDate").value(hasItem(sameInstant(DEFAULT_CURRENT_DATE))))
            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
    }

    @Test
    @Transactional
    void getChanellLog() throws Exception {
        // Initialize the database
        chanellLogRepository.saveAndFlush(chanellLog);

        // Get the chanellLog
        restChanellLogMockMvc
            .perform(get(ENTITY_API_URL_ID, chanellLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chanellLog.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.countSubscribers").value(DEFAULT_COUNT_SUBSCRIBERS.intValue()))
            .andExpect(jsonPath("$.quailityFromAnotherSources").value(DEFAULT_QUAILITY_FROM_ANOTHER_SOURCES.doubleValue()))
            .andExpect(jsonPath("$.priceDiapozon").value(DEFAULT_PRICE_DIAPOZON.doubleValue()))
            .andExpect(jsonPath("$.isModerate").value(DEFAULT_IS_MODERATE.booleanValue()))
            .andExpect(jsonPath("$.showChanellInTopByCategory").value(DEFAULT_SHOW_CHANELL_IN_TOP_BY_CATEGORY.booleanValue()))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE.booleanValue()))
            .andExpect(jsonPath("$.currentDate").value(sameInstant(DEFAULT_CURRENT_DATE)))
            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingChanellLog() throws Exception {
        // Get the chanellLog
        restChanellLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewChanellLog() throws Exception {
        // Initialize the database
        chanellLogRepository.saveAndFlush(chanellLog);

        int databaseSizeBeforeUpdate = chanellLogRepository.findAll().size();

        // Update the chanellLog
        ChanellLog updatedChanellLog = chanellLogRepository.findById(chanellLog.getId()).get();
        // Disconnect from session so that the updates on updatedChanellLog are not directly saved in db
        em.detach(updatedChanellLog);
        updatedChanellLog
            .name(UPDATED_NAME)
            .link(UPDATED_LINK)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .countSubscribers(UPDATED_COUNT_SUBSCRIBERS)
            .quailityFromAnotherSources(UPDATED_QUAILITY_FROM_ANOTHER_SOURCES)
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .isModerate(UPDATED_IS_MODERATE)
            .showChanellInTopByCategory(UPDATED_SHOW_CHANELL_IN_TOP_BY_CATEGORY)
            .region(UPDATED_REGION)
            .city(UPDATED_CITY)
            .isDelete(UPDATED_IS_DELETE)
            .currentDate(UPDATED_CURRENT_DATE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restChanellLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedChanellLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedChanellLog))
            )
            .andExpect(status().isOk());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeUpdate);
        ChanellLog testChanellLog = chanellLogList.get(chanellLogList.size() - 1);
        assertThat(testChanellLog.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testChanellLog.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testChanellLog.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testChanellLog.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testChanellLog.getCountSubscribers()).isEqualTo(UPDATED_COUNT_SUBSCRIBERS);
        assertThat(testChanellLog.getQuailityFromAnotherSources()).isEqualTo(UPDATED_QUAILITY_FROM_ANOTHER_SOURCES);
        assertThat(testChanellLog.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testChanellLog.getIsModerate()).isEqualTo(UPDATED_IS_MODERATE);
        assertThat(testChanellLog.getShowChanellInTopByCategory()).isEqualTo(UPDATED_SHOW_CHANELL_IN_TOP_BY_CATEGORY);
        assertThat(testChanellLog.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testChanellLog.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testChanellLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testChanellLog.getCurrentDate()).isEqualTo(UPDATED_CURRENT_DATE);
        assertThat(testChanellLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testChanellLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testChanellLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testChanellLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testChanellLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingChanellLog() throws Exception {
        int databaseSizeBeforeUpdate = chanellLogRepository.findAll().size();
        chanellLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChanellLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, chanellLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chanellLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchChanellLog() throws Exception {
        int databaseSizeBeforeUpdate = chanellLogRepository.findAll().size();
        chanellLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChanellLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(chanellLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamChanellLog() throws Exception {
        int databaseSizeBeforeUpdate = chanellLogRepository.findAll().size();
        chanellLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChanellLogMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(chanellLog)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateChanellLogWithPatch() throws Exception {
        // Initialize the database
        chanellLogRepository.saveAndFlush(chanellLog);

        int databaseSizeBeforeUpdate = chanellLogRepository.findAll().size();

        // Update the chanellLog using partial update
        ChanellLog partialUpdatedChanellLog = new ChanellLog();
        partialUpdatedChanellLog.setId(chanellLog.getId());

        partialUpdatedChanellLog
            .name(UPDATED_NAME)
            .quailityFromAnotherSources(UPDATED_QUAILITY_FROM_ANOTHER_SOURCES)
            .isModerate(UPDATED_IS_MODERATE)
            .showChanellInTopByCategory(UPDATED_SHOW_CHANELL_IN_TOP_BY_CATEGORY)
            .region(UPDATED_REGION)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1);

        restChanellLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChanellLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedChanellLog))
            )
            .andExpect(status().isOk());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeUpdate);
        ChanellLog testChanellLog = chanellLogList.get(chanellLogList.size() - 1);
        assertThat(testChanellLog.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testChanellLog.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testChanellLog.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testChanellLog.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testChanellLog.getCountSubscribers()).isEqualTo(DEFAULT_COUNT_SUBSCRIBERS);
        assertThat(testChanellLog.getQuailityFromAnotherSources()).isEqualTo(UPDATED_QUAILITY_FROM_ANOTHER_SOURCES);
        assertThat(testChanellLog.getPriceDiapozon()).isEqualTo(DEFAULT_PRICE_DIAPOZON);
        assertThat(testChanellLog.getIsModerate()).isEqualTo(UPDATED_IS_MODERATE);
        assertThat(testChanellLog.getShowChanellInTopByCategory()).isEqualTo(UPDATED_SHOW_CHANELL_IN_TOP_BY_CATEGORY);
        assertThat(testChanellLog.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testChanellLog.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testChanellLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testChanellLog.getCurrentDate()).isEqualTo(DEFAULT_CURRENT_DATE);
        assertThat(testChanellLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testChanellLog.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testChanellLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testChanellLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testChanellLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateChanellLogWithPatch() throws Exception {
        // Initialize the database
        chanellLogRepository.saveAndFlush(chanellLog);

        int databaseSizeBeforeUpdate = chanellLogRepository.findAll().size();

        // Update the chanellLog using partial update
        ChanellLog partialUpdatedChanellLog = new ChanellLog();
        partialUpdatedChanellLog.setId(chanellLog.getId());

        partialUpdatedChanellLog
            .name(UPDATED_NAME)
            .link(UPDATED_LINK)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .countSubscribers(UPDATED_COUNT_SUBSCRIBERS)
            .quailityFromAnotherSources(UPDATED_QUAILITY_FROM_ANOTHER_SOURCES)
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .isModerate(UPDATED_IS_MODERATE)
            .showChanellInTopByCategory(UPDATED_SHOW_CHANELL_IN_TOP_BY_CATEGORY)
            .region(UPDATED_REGION)
            .city(UPDATED_CITY)
            .isDelete(UPDATED_IS_DELETE)
            .currentDate(UPDATED_CURRENT_DATE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restChanellLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedChanellLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedChanellLog))
            )
            .andExpect(status().isOk());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeUpdate);
        ChanellLog testChanellLog = chanellLogList.get(chanellLogList.size() - 1);
        assertThat(testChanellLog.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testChanellLog.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testChanellLog.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testChanellLog.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testChanellLog.getCountSubscribers()).isEqualTo(UPDATED_COUNT_SUBSCRIBERS);
        assertThat(testChanellLog.getQuailityFromAnotherSources()).isEqualTo(UPDATED_QUAILITY_FROM_ANOTHER_SOURCES);
        assertThat(testChanellLog.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testChanellLog.getIsModerate()).isEqualTo(UPDATED_IS_MODERATE);
        assertThat(testChanellLog.getShowChanellInTopByCategory()).isEqualTo(UPDATED_SHOW_CHANELL_IN_TOP_BY_CATEGORY);
        assertThat(testChanellLog.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testChanellLog.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testChanellLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testChanellLog.getCurrentDate()).isEqualTo(UPDATED_CURRENT_DATE);
        assertThat(testChanellLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testChanellLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testChanellLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testChanellLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testChanellLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingChanellLog() throws Exception {
        int databaseSizeBeforeUpdate = chanellLogRepository.findAll().size();
        chanellLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChanellLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, chanellLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(chanellLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchChanellLog() throws Exception {
        int databaseSizeBeforeUpdate = chanellLogRepository.findAll().size();
        chanellLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChanellLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(chanellLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamChanellLog() throws Exception {
        int databaseSizeBeforeUpdate = chanellLogRepository.findAll().size();
        chanellLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restChanellLogMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(chanellLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ChanellLog in the database
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteChanellLog() throws Exception {
        // Initialize the database
        chanellLogRepository.saveAndFlush(chanellLog);

        int databaseSizeBeforeDelete = chanellLogRepository.findAll().size();

        // Delete the chanellLog
        restChanellLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, chanellLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChanellLog> chanellLogList = chanellLogRepository.findAll();
        assertThat(chanellLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
