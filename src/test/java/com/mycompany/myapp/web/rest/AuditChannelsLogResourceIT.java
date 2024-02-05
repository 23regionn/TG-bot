package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.AuditChannelsLog;
import com.mycompany.myapp.repository.AuditChannelsLogRepository;
import java.time.Instant;
import java.time.LocalDate;
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
 * Integration tests for the {@link AuditChannelsLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AuditChannelsLogResourceIT {

    private static final LocalDate DEFAULT_DATE_LOG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_LOG = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_END_PUBLIC_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_PUBLIC_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_ID_CHANNEL = 1L;
    private static final Long UPDATED_ID_CHANNEL = 2L;

    private static final Boolean DEFAULT_IS_MODERATE = false;
    private static final Boolean UPDATED_IS_MODERATE = true;

    private static final Boolean DEFAULT_IS_PAY = false;
    private static final Boolean UPDATED_IS_PAY = true;

    private static final ZonedDateTime DEFAULT_LAST_PAY_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_PAY_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_NAME_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_PRICE_FOR_PAY = "AAAAAAAAAA";
    private static final String UPDATED_PRICE_FOR_PAY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_START_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_COUNT_SUBSCRIBERS = 1L;
    private static final Long UPDATED_COUNT_SUBSCRIBERS = 2L;

    private static final Long DEFAULT_COUNT_VIEWS = 1L;
    private static final Long UPDATED_COUNT_VIEWS = 2L;

    private static final String ENTITY_API_URL = "/api/audit-channels-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AuditChannelsLogRepository auditChannelsLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAuditChannelsLogMockMvc;

    private AuditChannelsLog auditChannelsLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuditChannelsLog createEntity(EntityManager em) {
        AuditChannelsLog auditChannelsLog = new AuditChannelsLog()
            .dateLog(DEFAULT_DATE_LOG)
            .comment(DEFAULT_COMMENT)
            .contacts(DEFAULT_CONTACTS)
            .endPublicDate(DEFAULT_END_PUBLIC_DATE)
            .idChannel(DEFAULT_ID_CHANNEL)
            .isModerate(DEFAULT_IS_MODERATE)
            .isPay(DEFAULT_IS_PAY)
            .lastPayDate(DEFAULT_LAST_PAY_DATE)
            .link(DEFAULT_LINK)
            .nameChannel(DEFAULT_NAME_CHANNEL)
            .priceForPay(DEFAULT_PRICE_FOR_PAY)
            .startDate(DEFAULT_START_DATE)
            .countSubscribers(DEFAULT_COUNT_SUBSCRIBERS)
            .countViews(DEFAULT_COUNT_VIEWS);
        return auditChannelsLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AuditChannelsLog createUpdatedEntity(EntityManager em) {
        AuditChannelsLog auditChannelsLog = new AuditChannelsLog()
            .dateLog(UPDATED_DATE_LOG)
            .comment(UPDATED_COMMENT)
            .contacts(UPDATED_CONTACTS)
            .endPublicDate(UPDATED_END_PUBLIC_DATE)
            .idChannel(UPDATED_ID_CHANNEL)
            .isModerate(UPDATED_IS_MODERATE)
            .isPay(UPDATED_IS_PAY)
            .lastPayDate(UPDATED_LAST_PAY_DATE)
            .link(UPDATED_LINK)
            .nameChannel(UPDATED_NAME_CHANNEL)
            .priceForPay(UPDATED_PRICE_FOR_PAY)
            .startDate(UPDATED_START_DATE)
            .countSubscribers(UPDATED_COUNT_SUBSCRIBERS)
            .countViews(UPDATED_COUNT_VIEWS);
        return auditChannelsLog;
    }

    @BeforeEach
    public void initTest() {
        auditChannelsLog = createEntity(em);
    }

    @Test
    @Transactional
    void createAuditChannelsLog() throws Exception {
        int databaseSizeBeforeCreate = auditChannelsLogRepository.findAll().size();
        // Create the AuditChannelsLog
        restAuditChannelsLogMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(auditChannelsLog))
            )
            .andExpect(status().isCreated());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeCreate + 1);
        AuditChannelsLog testAuditChannelsLog = auditChannelsLogList.get(auditChannelsLogList.size() - 1);
        assertThat(testAuditChannelsLog.getDateLog()).isEqualTo(DEFAULT_DATE_LOG);
        assertThat(testAuditChannelsLog.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testAuditChannelsLog.getContacts()).isEqualTo(DEFAULT_CONTACTS);
        assertThat(testAuditChannelsLog.getEndPublicDate()).isEqualTo(DEFAULT_END_PUBLIC_DATE);
        assertThat(testAuditChannelsLog.getIdChannel()).isEqualTo(DEFAULT_ID_CHANNEL);
        assertThat(testAuditChannelsLog.getIsModerate()).isEqualTo(DEFAULT_IS_MODERATE);
        assertThat(testAuditChannelsLog.getIsPay()).isEqualTo(DEFAULT_IS_PAY);
        assertThat(testAuditChannelsLog.getLastPayDate()).isEqualTo(DEFAULT_LAST_PAY_DATE);
        assertThat(testAuditChannelsLog.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testAuditChannelsLog.getNameChannel()).isEqualTo(DEFAULT_NAME_CHANNEL);
        assertThat(testAuditChannelsLog.getPriceForPay()).isEqualTo(DEFAULT_PRICE_FOR_PAY);
        assertThat(testAuditChannelsLog.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testAuditChannelsLog.getCountSubscribers()).isEqualTo(DEFAULT_COUNT_SUBSCRIBERS);
        assertThat(testAuditChannelsLog.getCountViews()).isEqualTo(DEFAULT_COUNT_VIEWS);
    }

    @Test
    @Transactional
    void createAuditChannelsLogWithExistingId() throws Exception {
        // Create the AuditChannelsLog with an existing ID
        auditChannelsLog.setId(1L);

        int databaseSizeBeforeCreate = auditChannelsLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuditChannelsLogMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(auditChannelsLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAuditChannelsLogs() throws Exception {
        // Initialize the database
        auditChannelsLogRepository.saveAndFlush(auditChannelsLog);

        // Get all the auditChannelsLogList
        restAuditChannelsLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auditChannelsLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateLog").value(hasItem(DEFAULT_DATE_LOG.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].contacts").value(hasItem(DEFAULT_CONTACTS)))
            .andExpect(jsonPath("$.[*].endPublicDate").value(hasItem(sameInstant(DEFAULT_END_PUBLIC_DATE))))
            .andExpect(jsonPath("$.[*].idChannel").value(hasItem(DEFAULT_ID_CHANNEL.intValue())))
            .andExpect(jsonPath("$.[*].isModerate").value(hasItem(DEFAULT_IS_MODERATE.booleanValue())))
            .andExpect(jsonPath("$.[*].isPay").value(hasItem(DEFAULT_IS_PAY.booleanValue())))
            .andExpect(jsonPath("$.[*].lastPayDate").value(hasItem(sameInstant(DEFAULT_LAST_PAY_DATE))))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].nameChannel").value(hasItem(DEFAULT_NAME_CHANNEL)))
            .andExpect(jsonPath("$.[*].priceForPay").value(hasItem(DEFAULT_PRICE_FOR_PAY)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(sameInstant(DEFAULT_START_DATE))))
            .andExpect(jsonPath("$.[*].countSubscribers").value(hasItem(DEFAULT_COUNT_SUBSCRIBERS.intValue())))
            .andExpect(jsonPath("$.[*].countViews").value(hasItem(DEFAULT_COUNT_VIEWS.intValue())));
    }

    @Test
    @Transactional
    void getAuditChannelsLog() throws Exception {
        // Initialize the database
        auditChannelsLogRepository.saveAndFlush(auditChannelsLog);

        // Get the auditChannelsLog
        restAuditChannelsLogMockMvc
            .perform(get(ENTITY_API_URL_ID, auditChannelsLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(auditChannelsLog.getId().intValue()))
            .andExpect(jsonPath("$.dateLog").value(DEFAULT_DATE_LOG.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.contacts").value(DEFAULT_CONTACTS))
            .andExpect(jsonPath("$.endPublicDate").value(sameInstant(DEFAULT_END_PUBLIC_DATE)))
            .andExpect(jsonPath("$.idChannel").value(DEFAULT_ID_CHANNEL.intValue()))
            .andExpect(jsonPath("$.isModerate").value(DEFAULT_IS_MODERATE.booleanValue()))
            .andExpect(jsonPath("$.isPay").value(DEFAULT_IS_PAY.booleanValue()))
            .andExpect(jsonPath("$.lastPayDate").value(sameInstant(DEFAULT_LAST_PAY_DATE)))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
            .andExpect(jsonPath("$.nameChannel").value(DEFAULT_NAME_CHANNEL))
            .andExpect(jsonPath("$.priceForPay").value(DEFAULT_PRICE_FOR_PAY))
            .andExpect(jsonPath("$.startDate").value(sameInstant(DEFAULT_START_DATE)))
            .andExpect(jsonPath("$.countSubscribers").value(DEFAULT_COUNT_SUBSCRIBERS.intValue()))
            .andExpect(jsonPath("$.countViews").value(DEFAULT_COUNT_VIEWS.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingAuditChannelsLog() throws Exception {
        // Get the auditChannelsLog
        restAuditChannelsLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewAuditChannelsLog() throws Exception {
        // Initialize the database
        auditChannelsLogRepository.saveAndFlush(auditChannelsLog);

        int databaseSizeBeforeUpdate = auditChannelsLogRepository.findAll().size();

        // Update the auditChannelsLog
        AuditChannelsLog updatedAuditChannelsLog = auditChannelsLogRepository.findById(auditChannelsLog.getId()).get();
        // Disconnect from session so that the updates on updatedAuditChannelsLog are not directly saved in db
        em.detach(updatedAuditChannelsLog);
        updatedAuditChannelsLog
            .dateLog(UPDATED_DATE_LOG)
            .comment(UPDATED_COMMENT)
            .contacts(UPDATED_CONTACTS)
            .endPublicDate(UPDATED_END_PUBLIC_DATE)
            .idChannel(UPDATED_ID_CHANNEL)
            .isModerate(UPDATED_IS_MODERATE)
            .isPay(UPDATED_IS_PAY)
            .lastPayDate(UPDATED_LAST_PAY_DATE)
            .link(UPDATED_LINK)
            .nameChannel(UPDATED_NAME_CHANNEL)
            .priceForPay(UPDATED_PRICE_FOR_PAY)
            .startDate(UPDATED_START_DATE)
            .countSubscribers(UPDATED_COUNT_SUBSCRIBERS)
            .countViews(UPDATED_COUNT_VIEWS);

        restAuditChannelsLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAuditChannelsLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAuditChannelsLog))
            )
            .andExpect(status().isOk());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeUpdate);
        AuditChannelsLog testAuditChannelsLog = auditChannelsLogList.get(auditChannelsLogList.size() - 1);
        assertThat(testAuditChannelsLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
        assertThat(testAuditChannelsLog.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testAuditChannelsLog.getContacts()).isEqualTo(UPDATED_CONTACTS);
        assertThat(testAuditChannelsLog.getEndPublicDate()).isEqualTo(UPDATED_END_PUBLIC_DATE);
        assertThat(testAuditChannelsLog.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testAuditChannelsLog.getIsModerate()).isEqualTo(UPDATED_IS_MODERATE);
        assertThat(testAuditChannelsLog.getIsPay()).isEqualTo(UPDATED_IS_PAY);
        assertThat(testAuditChannelsLog.getLastPayDate()).isEqualTo(UPDATED_LAST_PAY_DATE);
        assertThat(testAuditChannelsLog.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testAuditChannelsLog.getNameChannel()).isEqualTo(UPDATED_NAME_CHANNEL);
        assertThat(testAuditChannelsLog.getPriceForPay()).isEqualTo(UPDATED_PRICE_FOR_PAY);
        assertThat(testAuditChannelsLog.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAuditChannelsLog.getCountSubscribers()).isEqualTo(UPDATED_COUNT_SUBSCRIBERS);
        assertThat(testAuditChannelsLog.getCountViews()).isEqualTo(UPDATED_COUNT_VIEWS);
    }

    @Test
    @Transactional
    void putNonExistingAuditChannelsLog() throws Exception {
        int databaseSizeBeforeUpdate = auditChannelsLogRepository.findAll().size();
        auditChannelsLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuditChannelsLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, auditChannelsLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(auditChannelsLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAuditChannelsLog() throws Exception {
        int databaseSizeBeforeUpdate = auditChannelsLogRepository.findAll().size();
        auditChannelsLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditChannelsLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(auditChannelsLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAuditChannelsLog() throws Exception {
        int databaseSizeBeforeUpdate = auditChannelsLogRepository.findAll().size();
        auditChannelsLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditChannelsLogMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(auditChannelsLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAuditChannelsLogWithPatch() throws Exception {
        // Initialize the database
        auditChannelsLogRepository.saveAndFlush(auditChannelsLog);

        int databaseSizeBeforeUpdate = auditChannelsLogRepository.findAll().size();

        // Update the auditChannelsLog using partial update
        AuditChannelsLog partialUpdatedAuditChannelsLog = new AuditChannelsLog();
        partialUpdatedAuditChannelsLog.setId(auditChannelsLog.getId());

        partialUpdatedAuditChannelsLog
            .comment(UPDATED_COMMENT)
            .contacts(UPDATED_CONTACTS)
            .idChannel(UPDATED_ID_CHANNEL)
            .isPay(UPDATED_IS_PAY)
            .lastPayDate(UPDATED_LAST_PAY_DATE)
            .link(UPDATED_LINK)
            .startDate(UPDATED_START_DATE)
            .countViews(UPDATED_COUNT_VIEWS);

        restAuditChannelsLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAuditChannelsLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAuditChannelsLog))
            )
            .andExpect(status().isOk());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeUpdate);
        AuditChannelsLog testAuditChannelsLog = auditChannelsLogList.get(auditChannelsLogList.size() - 1);
        assertThat(testAuditChannelsLog.getDateLog()).isEqualTo(DEFAULT_DATE_LOG);
        assertThat(testAuditChannelsLog.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testAuditChannelsLog.getContacts()).isEqualTo(UPDATED_CONTACTS);
        assertThat(testAuditChannelsLog.getEndPublicDate()).isEqualTo(DEFAULT_END_PUBLIC_DATE);
        assertThat(testAuditChannelsLog.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testAuditChannelsLog.getIsModerate()).isEqualTo(DEFAULT_IS_MODERATE);
        assertThat(testAuditChannelsLog.getIsPay()).isEqualTo(UPDATED_IS_PAY);
        assertThat(testAuditChannelsLog.getLastPayDate()).isEqualTo(UPDATED_LAST_PAY_DATE);
        assertThat(testAuditChannelsLog.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testAuditChannelsLog.getNameChannel()).isEqualTo(DEFAULT_NAME_CHANNEL);
        assertThat(testAuditChannelsLog.getPriceForPay()).isEqualTo(DEFAULT_PRICE_FOR_PAY);
        assertThat(testAuditChannelsLog.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAuditChannelsLog.getCountSubscribers()).isEqualTo(DEFAULT_COUNT_SUBSCRIBERS);
        assertThat(testAuditChannelsLog.getCountViews()).isEqualTo(UPDATED_COUNT_VIEWS);
    }

    @Test
    @Transactional
    void fullUpdateAuditChannelsLogWithPatch() throws Exception {
        // Initialize the database
        auditChannelsLogRepository.saveAndFlush(auditChannelsLog);

        int databaseSizeBeforeUpdate = auditChannelsLogRepository.findAll().size();

        // Update the auditChannelsLog using partial update
        AuditChannelsLog partialUpdatedAuditChannelsLog = new AuditChannelsLog();
        partialUpdatedAuditChannelsLog.setId(auditChannelsLog.getId());

        partialUpdatedAuditChannelsLog
            .dateLog(UPDATED_DATE_LOG)
            .comment(UPDATED_COMMENT)
            .contacts(UPDATED_CONTACTS)
            .endPublicDate(UPDATED_END_PUBLIC_DATE)
            .idChannel(UPDATED_ID_CHANNEL)
            .isModerate(UPDATED_IS_MODERATE)
            .isPay(UPDATED_IS_PAY)
            .lastPayDate(UPDATED_LAST_PAY_DATE)
            .link(UPDATED_LINK)
            .nameChannel(UPDATED_NAME_CHANNEL)
            .priceForPay(UPDATED_PRICE_FOR_PAY)
            .startDate(UPDATED_START_DATE)
            .countSubscribers(UPDATED_COUNT_SUBSCRIBERS)
            .countViews(UPDATED_COUNT_VIEWS);

        restAuditChannelsLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAuditChannelsLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAuditChannelsLog))
            )
            .andExpect(status().isOk());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeUpdate);
        AuditChannelsLog testAuditChannelsLog = auditChannelsLogList.get(auditChannelsLogList.size() - 1);
        assertThat(testAuditChannelsLog.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
        assertThat(testAuditChannelsLog.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testAuditChannelsLog.getContacts()).isEqualTo(UPDATED_CONTACTS);
        assertThat(testAuditChannelsLog.getEndPublicDate()).isEqualTo(UPDATED_END_PUBLIC_DATE);
        assertThat(testAuditChannelsLog.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testAuditChannelsLog.getIsModerate()).isEqualTo(UPDATED_IS_MODERATE);
        assertThat(testAuditChannelsLog.getIsPay()).isEqualTo(UPDATED_IS_PAY);
        assertThat(testAuditChannelsLog.getLastPayDate()).isEqualTo(UPDATED_LAST_PAY_DATE);
        assertThat(testAuditChannelsLog.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testAuditChannelsLog.getNameChannel()).isEqualTo(UPDATED_NAME_CHANNEL);
        assertThat(testAuditChannelsLog.getPriceForPay()).isEqualTo(UPDATED_PRICE_FOR_PAY);
        assertThat(testAuditChannelsLog.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAuditChannelsLog.getCountSubscribers()).isEqualTo(UPDATED_COUNT_SUBSCRIBERS);
        assertThat(testAuditChannelsLog.getCountViews()).isEqualTo(UPDATED_COUNT_VIEWS);
    }

    @Test
    @Transactional
    void patchNonExistingAuditChannelsLog() throws Exception {
        int databaseSizeBeforeUpdate = auditChannelsLogRepository.findAll().size();
        auditChannelsLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuditChannelsLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, auditChannelsLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(auditChannelsLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAuditChannelsLog() throws Exception {
        int databaseSizeBeforeUpdate = auditChannelsLogRepository.findAll().size();
        auditChannelsLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditChannelsLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(auditChannelsLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAuditChannelsLog() throws Exception {
        int databaseSizeBeforeUpdate = auditChannelsLogRepository.findAll().size();
        auditChannelsLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditChannelsLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(auditChannelsLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AuditChannelsLog in the database
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAuditChannelsLog() throws Exception {
        // Initialize the database
        auditChannelsLogRepository.saveAndFlush(auditChannelsLog);

        int databaseSizeBeforeDelete = auditChannelsLogRepository.findAll().size();

        // Delete the auditChannelsLog
        restAuditChannelsLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, auditChannelsLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AuditChannelsLog> auditChannelsLogList = auditChannelsLogRepository.findAll();
        assertThat(auditChannelsLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
