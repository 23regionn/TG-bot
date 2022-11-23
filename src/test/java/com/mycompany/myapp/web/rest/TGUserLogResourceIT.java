package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TGUserLog;
import com.mycompany.myapp.repository.TGUserLogRepository;
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
 * Integration tests for the {@link TGUserLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TGUserLogResourceIT {

    private static final Long DEFAULT_ID_TG_USER = 1L;
    private static final Long UPDATED_ID_TG_USER = 2L;

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_REGISTRATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REGISTRATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_USER_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_USER_ROLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ADMIN = false;
    private static final Boolean UPDATED_IS_ADMIN = true;

    private static final Double DEFAULT_SCORE = 1D;
    private static final Double UPDATED_SCORE = 2D;

    private static final Boolean DEFAULT_IS_BLOCKED = false;
    private static final Boolean UPDATED_IS_BLOCKED = true;

    private static final Long DEFAULT_CHAT_ID = 1L;
    private static final Long UPDATED_CHAT_ID = 2L;

    private static final Boolean DEFAULT_IS_DELETE = false;
    private static final Boolean UPDATED_IS_DELETE = true;

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

    private static final String ENTITY_API_URL = "/api/tg-user-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TGUserLogRepository tGUserLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTGUserLogMockMvc;

    private TGUserLog tGUserLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TGUserLog createEntity(EntityManager em) {
        TGUserLog tGUserLog = new TGUserLog()
            .idTgUser(DEFAULT_ID_TG_USER)
            .firstName(DEFAULT_FIRST_NAME)
            .registrationDate(DEFAULT_REGISTRATION_DATE)
            .userRole(DEFAULT_USER_ROLE)
            .isAdmin(DEFAULT_IS_ADMIN)
            .score(DEFAULT_SCORE)
            .isBlocked(DEFAULT_IS_BLOCKED)
            .chatId(DEFAULT_CHAT_ID)
            .isDelete(DEFAULT_IS_DELETE)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return tGUserLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TGUserLog createUpdatedEntity(EntityManager em) {
        TGUserLog tGUserLog = new TGUserLog()
            .idTgUser(UPDATED_ID_TG_USER)
            .firstName(UPDATED_FIRST_NAME)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .userRole(UPDATED_USER_ROLE)
            .isAdmin(UPDATED_IS_ADMIN)
            .score(UPDATED_SCORE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .chatId(UPDATED_CHAT_ID)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return tGUserLog;
    }

    @BeforeEach
    public void initTest() {
        tGUserLog = createEntity(em);
    }

    @Test
    @Transactional
    void createTGUserLog() throws Exception {
        int databaseSizeBeforeCreate = tGUserLogRepository.findAll().size();
        // Create the TGUserLog
        restTGUserLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tGUserLog)))
            .andExpect(status().isCreated());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeCreate + 1);
        TGUserLog testTGUserLog = tGUserLogList.get(tGUserLogList.size() - 1);
        assertThat(testTGUserLog.getIdTgUser()).isEqualTo(DEFAULT_ID_TG_USER);
        assertThat(testTGUserLog.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testTGUserLog.getRegistrationDate()).isEqualTo(DEFAULT_REGISTRATION_DATE);
        assertThat(testTGUserLog.getUserRole()).isEqualTo(DEFAULT_USER_ROLE);
        assertThat(testTGUserLog.getIsAdmin()).isEqualTo(DEFAULT_IS_ADMIN);
        assertThat(testTGUserLog.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testTGUserLog.getIsBlocked()).isEqualTo(DEFAULT_IS_BLOCKED);
        assertThat(testTGUserLog.getChatId()).isEqualTo(DEFAULT_CHAT_ID);
        assertThat(testTGUserLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testTGUserLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testTGUserLog.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testTGUserLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testTGUserLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testTGUserLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createTGUserLogWithExistingId() throws Exception {
        // Create the TGUserLog with an existing ID
        tGUserLog.setId(1L);

        int databaseSizeBeforeCreate = tGUserLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTGUserLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tGUserLog)))
            .andExpect(status().isBadRequest());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTGUserLogs() throws Exception {
        // Initialize the database
        tGUserLogRepository.saveAndFlush(tGUserLog);

        // Get all the tGUserLogList
        restTGUserLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tGUserLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTgUser").value(hasItem(DEFAULT_ID_TG_USER.intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].registrationDate").value(hasItem(sameInstant(DEFAULT_REGISTRATION_DATE))))
            .andExpect(jsonPath("$.[*].userRole").value(hasItem(DEFAULT_USER_ROLE)))
            .andExpect(jsonPath("$.[*].isAdmin").value(hasItem(DEFAULT_IS_ADMIN.booleanValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].isBlocked").value(hasItem(DEFAULT_IS_BLOCKED.booleanValue())))
            .andExpect(jsonPath("$.[*].chatId").value(hasItem(DEFAULT_CHAT_ID.intValue())))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
    }

    @Test
    @Transactional
    void getTGUserLog() throws Exception {
        // Initialize the database
        tGUserLogRepository.saveAndFlush(tGUserLog);

        // Get the tGUserLog
        restTGUserLogMockMvc
            .perform(get(ENTITY_API_URL_ID, tGUserLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tGUserLog.getId().intValue()))
            .andExpect(jsonPath("$.idTgUser").value(DEFAULT_ID_TG_USER.intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.registrationDate").value(sameInstant(DEFAULT_REGISTRATION_DATE)))
            .andExpect(jsonPath("$.userRole").value(DEFAULT_USER_ROLE))
            .andExpect(jsonPath("$.isAdmin").value(DEFAULT_IS_ADMIN.booleanValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.doubleValue()))
            .andExpect(jsonPath("$.isBlocked").value(DEFAULT_IS_BLOCKED.booleanValue()))
            .andExpect(jsonPath("$.chatId").value(DEFAULT_CHAT_ID.intValue()))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE.booleanValue()))
            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTGUserLog() throws Exception {
        // Get the tGUserLog
        restTGUserLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTGUserLog() throws Exception {
        // Initialize the database
        tGUserLogRepository.saveAndFlush(tGUserLog);

        int databaseSizeBeforeUpdate = tGUserLogRepository.findAll().size();

        // Update the tGUserLog
        TGUserLog updatedTGUserLog = tGUserLogRepository.findById(tGUserLog.getId()).get();
        // Disconnect from session so that the updates on updatedTGUserLog are not directly saved in db
        em.detach(updatedTGUserLog);
        updatedTGUserLog
            .idTgUser(UPDATED_ID_TG_USER)
            .firstName(UPDATED_FIRST_NAME)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .userRole(UPDATED_USER_ROLE)
            .isAdmin(UPDATED_IS_ADMIN)
            .score(UPDATED_SCORE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .chatId(UPDATED_CHAT_ID)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restTGUserLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTGUserLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTGUserLog))
            )
            .andExpect(status().isOk());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeUpdate);
        TGUserLog testTGUserLog = tGUserLogList.get(tGUserLogList.size() - 1);
        assertThat(testTGUserLog.getIdTgUser()).isEqualTo(UPDATED_ID_TG_USER);
        assertThat(testTGUserLog.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testTGUserLog.getRegistrationDate()).isEqualTo(UPDATED_REGISTRATION_DATE);
        assertThat(testTGUserLog.getUserRole()).isEqualTo(UPDATED_USER_ROLE);
        assertThat(testTGUserLog.getIsAdmin()).isEqualTo(UPDATED_IS_ADMIN);
        assertThat(testTGUserLog.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testTGUserLog.getIsBlocked()).isEqualTo(UPDATED_IS_BLOCKED);
        assertThat(testTGUserLog.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testTGUserLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testTGUserLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTGUserLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testTGUserLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTGUserLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testTGUserLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingTGUserLog() throws Exception {
        int databaseSizeBeforeUpdate = tGUserLogRepository.findAll().size();
        tGUserLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTGUserLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tGUserLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tGUserLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTGUserLog() throws Exception {
        int databaseSizeBeforeUpdate = tGUserLogRepository.findAll().size();
        tGUserLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTGUserLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tGUserLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTGUserLog() throws Exception {
        int databaseSizeBeforeUpdate = tGUserLogRepository.findAll().size();
        tGUserLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTGUserLogMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tGUserLog)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTGUserLogWithPatch() throws Exception {
        // Initialize the database
        tGUserLogRepository.saveAndFlush(tGUserLog);

        int databaseSizeBeforeUpdate = tGUserLogRepository.findAll().size();

        // Update the tGUserLog using partial update
        TGUserLog partialUpdatedTGUserLog = new TGUserLog();
        partialUpdatedTGUserLog.setId(tGUserLog.getId());

        partialUpdatedTGUserLog
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .isAdmin(UPDATED_IS_ADMIN)
            .isBlocked(UPDATED_IS_BLOCKED)
            .date1(UPDATED_DATE_1)
            .long1(UPDATED_LONG_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restTGUserLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTGUserLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTGUserLog))
            )
            .andExpect(status().isOk());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeUpdate);
        TGUserLog testTGUserLog = tGUserLogList.get(tGUserLogList.size() - 1);
        assertThat(testTGUserLog.getIdTgUser()).isEqualTo(DEFAULT_ID_TG_USER);
        assertThat(testTGUserLog.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testTGUserLog.getRegistrationDate()).isEqualTo(UPDATED_REGISTRATION_DATE);
        assertThat(testTGUserLog.getUserRole()).isEqualTo(DEFAULT_USER_ROLE);
        assertThat(testTGUserLog.getIsAdmin()).isEqualTo(UPDATED_IS_ADMIN);
        assertThat(testTGUserLog.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testTGUserLog.getIsBlocked()).isEqualTo(UPDATED_IS_BLOCKED);
        assertThat(testTGUserLog.getChatId()).isEqualTo(DEFAULT_CHAT_ID);
        assertThat(testTGUserLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testTGUserLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTGUserLog.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testTGUserLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTGUserLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testTGUserLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateTGUserLogWithPatch() throws Exception {
        // Initialize the database
        tGUserLogRepository.saveAndFlush(tGUserLog);

        int databaseSizeBeforeUpdate = tGUserLogRepository.findAll().size();

        // Update the tGUserLog using partial update
        TGUserLog partialUpdatedTGUserLog = new TGUserLog();
        partialUpdatedTGUserLog.setId(tGUserLog.getId());

        partialUpdatedTGUserLog
            .idTgUser(UPDATED_ID_TG_USER)
            .firstName(UPDATED_FIRST_NAME)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .userRole(UPDATED_USER_ROLE)
            .isAdmin(UPDATED_IS_ADMIN)
            .score(UPDATED_SCORE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .chatId(UPDATED_CHAT_ID)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restTGUserLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTGUserLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTGUserLog))
            )
            .andExpect(status().isOk());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeUpdate);
        TGUserLog testTGUserLog = tGUserLogList.get(tGUserLogList.size() - 1);
        assertThat(testTGUserLog.getIdTgUser()).isEqualTo(UPDATED_ID_TG_USER);
        assertThat(testTGUserLog.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testTGUserLog.getRegistrationDate()).isEqualTo(UPDATED_REGISTRATION_DATE);
        assertThat(testTGUserLog.getUserRole()).isEqualTo(UPDATED_USER_ROLE);
        assertThat(testTGUserLog.getIsAdmin()).isEqualTo(UPDATED_IS_ADMIN);
        assertThat(testTGUserLog.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testTGUserLog.getIsBlocked()).isEqualTo(UPDATED_IS_BLOCKED);
        assertThat(testTGUserLog.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testTGUserLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testTGUserLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTGUserLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testTGUserLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTGUserLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testTGUserLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingTGUserLog() throws Exception {
        int databaseSizeBeforeUpdate = tGUserLogRepository.findAll().size();
        tGUserLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTGUserLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tGUserLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tGUserLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTGUserLog() throws Exception {
        int databaseSizeBeforeUpdate = tGUserLogRepository.findAll().size();
        tGUserLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTGUserLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tGUserLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTGUserLog() throws Exception {
        int databaseSizeBeforeUpdate = tGUserLogRepository.findAll().size();
        tGUserLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTGUserLogMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tGUserLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TGUserLog in the database
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTGUserLog() throws Exception {
        // Initialize the database
        tGUserLogRepository.saveAndFlush(tGUserLog);

        int databaseSizeBeforeDelete = tGUserLogRepository.findAll().size();

        // Delete the tGUserLog
        restTGUserLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, tGUserLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TGUserLog> tGUserLogList = tGUserLogRepository.findAll();
        assertThat(tGUserLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
