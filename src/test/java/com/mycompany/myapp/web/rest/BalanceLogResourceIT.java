package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.BalanceLog;
import com.mycompany.myapp.repository.BalanceLogRepository;
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
 * Integration tests for the {@link BalanceLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BalanceLogResourceIT {

    private static final Double DEFAULT_BALACE = 1D;
    private static final Double UPDATED_BALACE = 2D;

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final Double DEFAULT_FROST_SUM = 1D;
    private static final Double UPDATED_FROST_SUM = 2D;

    private static final ZonedDateTime DEFAULT_DATE_LAST_ADD_BALANCE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_LAST_ADD_BALANCE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_LAST_MINUS_FROM_BALANCE = ZonedDateTime.ofInstant(
        Instant.ofEpochMilli(0L),
        ZoneOffset.UTC
    );
    private static final ZonedDateTime UPDATED_DATE_LAST_MINUS_FROM_BALANCE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final String ENTITY_API_URL = "/api/balance-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BalanceLogRepository balanceLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBalanceLogMockMvc;

    private BalanceLog balanceLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BalanceLog createEntity(EntityManager em) {
        BalanceLog balanceLog = new BalanceLog()
            .balace(DEFAULT_BALACE)
            .userId(DEFAULT_USER_ID)
            .frostSum(DEFAULT_FROST_SUM)
            .dateLastAddBalance(DEFAULT_DATE_LAST_ADD_BALANCE)
            .dateLastMinusFromBalance(DEFAULT_DATE_LAST_MINUS_FROM_BALANCE)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return balanceLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BalanceLog createUpdatedEntity(EntityManager em) {
        BalanceLog balanceLog = new BalanceLog()
            .balace(UPDATED_BALACE)
            .userId(UPDATED_USER_ID)
            .frostSum(UPDATED_FROST_SUM)
            .dateLastAddBalance(UPDATED_DATE_LAST_ADD_BALANCE)
            .dateLastMinusFromBalance(UPDATED_DATE_LAST_MINUS_FROM_BALANCE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return balanceLog;
    }

    @BeforeEach
    public void initTest() {
        balanceLog = createEntity(em);
    }

    @Test
    @Transactional
    void createBalanceLog() throws Exception {
        int databaseSizeBeforeCreate = balanceLogRepository.findAll().size();
        // Create the BalanceLog
        restBalanceLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(balanceLog)))
            .andExpect(status().isCreated());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeCreate + 1);
        BalanceLog testBalanceLog = balanceLogList.get(balanceLogList.size() - 1);
        assertThat(testBalanceLog.getBalace()).isEqualTo(DEFAULT_BALACE);
        assertThat(testBalanceLog.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testBalanceLog.getFrostSum()).isEqualTo(DEFAULT_FROST_SUM);
        assertThat(testBalanceLog.getDateLastAddBalance()).isEqualTo(DEFAULT_DATE_LAST_ADD_BALANCE);
        assertThat(testBalanceLog.getDateLastMinusFromBalance()).isEqualTo(DEFAULT_DATE_LAST_MINUS_FROM_BALANCE);
        assertThat(testBalanceLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testBalanceLog.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testBalanceLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testBalanceLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testBalanceLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createBalanceLogWithExistingId() throws Exception {
        // Create the BalanceLog with an existing ID
        balanceLog.setId(1L);

        int databaseSizeBeforeCreate = balanceLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBalanceLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(balanceLog)))
            .andExpect(status().isBadRequest());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBalanceLogs() throws Exception {
        // Initialize the database
        balanceLogRepository.saveAndFlush(balanceLog);

        // Get all the balanceLogList
        restBalanceLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(balanceLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].balace").value(hasItem(DEFAULT_BALACE.doubleValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].frostSum").value(hasItem(DEFAULT_FROST_SUM.doubleValue())))
            .andExpect(jsonPath("$.[*].dateLastAddBalance").value(hasItem(sameInstant(DEFAULT_DATE_LAST_ADD_BALANCE))))
            .andExpect(jsonPath("$.[*].dateLastMinusFromBalance").value(hasItem(sameInstant(DEFAULT_DATE_LAST_MINUS_FROM_BALANCE))))
            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
    }

    @Test
    @Transactional
    void getBalanceLog() throws Exception {
        // Initialize the database
        balanceLogRepository.saveAndFlush(balanceLog);

        // Get the balanceLog
        restBalanceLogMockMvc
            .perform(get(ENTITY_API_URL_ID, balanceLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(balanceLog.getId().intValue()))
            .andExpect(jsonPath("$.balace").value(DEFAULT_BALACE.doubleValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.frostSum").value(DEFAULT_FROST_SUM.doubleValue()))
            .andExpect(jsonPath("$.dateLastAddBalance").value(sameInstant(DEFAULT_DATE_LAST_ADD_BALANCE)))
            .andExpect(jsonPath("$.dateLastMinusFromBalance").value(sameInstant(DEFAULT_DATE_LAST_MINUS_FROM_BALANCE)))
            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingBalanceLog() throws Exception {
        // Get the balanceLog
        restBalanceLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBalanceLog() throws Exception {
        // Initialize the database
        balanceLogRepository.saveAndFlush(balanceLog);

        int databaseSizeBeforeUpdate = balanceLogRepository.findAll().size();

        // Update the balanceLog
        BalanceLog updatedBalanceLog = balanceLogRepository.findById(balanceLog.getId()).get();
        // Disconnect from session so that the updates on updatedBalanceLog are not directly saved in db
        em.detach(updatedBalanceLog);
        updatedBalanceLog
            .balace(UPDATED_BALACE)
            .userId(UPDATED_USER_ID)
            .frostSum(UPDATED_FROST_SUM)
            .dateLastAddBalance(UPDATED_DATE_LAST_ADD_BALANCE)
            .dateLastMinusFromBalance(UPDATED_DATE_LAST_MINUS_FROM_BALANCE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restBalanceLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBalanceLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBalanceLog))
            )
            .andExpect(status().isOk());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeUpdate);
        BalanceLog testBalanceLog = balanceLogList.get(balanceLogList.size() - 1);
        assertThat(testBalanceLog.getBalace()).isEqualTo(UPDATED_BALACE);
        assertThat(testBalanceLog.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBalanceLog.getFrostSum()).isEqualTo(UPDATED_FROST_SUM);
        assertThat(testBalanceLog.getDateLastAddBalance()).isEqualTo(UPDATED_DATE_LAST_ADD_BALANCE);
        assertThat(testBalanceLog.getDateLastMinusFromBalance()).isEqualTo(UPDATED_DATE_LAST_MINUS_FROM_BALANCE);
        assertThat(testBalanceLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testBalanceLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testBalanceLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testBalanceLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testBalanceLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingBalanceLog() throws Exception {
        int databaseSizeBeforeUpdate = balanceLogRepository.findAll().size();
        balanceLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBalanceLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, balanceLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(balanceLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBalanceLog() throws Exception {
        int databaseSizeBeforeUpdate = balanceLogRepository.findAll().size();
        balanceLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBalanceLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(balanceLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBalanceLog() throws Exception {
        int databaseSizeBeforeUpdate = balanceLogRepository.findAll().size();
        balanceLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBalanceLogMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(balanceLog)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBalanceLogWithPatch() throws Exception {
        // Initialize the database
        balanceLogRepository.saveAndFlush(balanceLog);

        int databaseSizeBeforeUpdate = balanceLogRepository.findAll().size();

        // Update the balanceLog using partial update
        BalanceLog partialUpdatedBalanceLog = new BalanceLog();
        partialUpdatedBalanceLog.setId(balanceLog.getId());

        partialUpdatedBalanceLog
            .balace(UPDATED_BALACE)
            .userId(UPDATED_USER_ID)
            .dateLastAddBalance(UPDATED_DATE_LAST_ADD_BALANCE)
            .dateLastMinusFromBalance(UPDATED_DATE_LAST_MINUS_FROM_BALANCE)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restBalanceLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBalanceLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBalanceLog))
            )
            .andExpect(status().isOk());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeUpdate);
        BalanceLog testBalanceLog = balanceLogList.get(balanceLogList.size() - 1);
        assertThat(testBalanceLog.getBalace()).isEqualTo(UPDATED_BALACE);
        assertThat(testBalanceLog.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBalanceLog.getFrostSum()).isEqualTo(DEFAULT_FROST_SUM);
        assertThat(testBalanceLog.getDateLastAddBalance()).isEqualTo(UPDATED_DATE_LAST_ADD_BALANCE);
        assertThat(testBalanceLog.getDateLastMinusFromBalance()).isEqualTo(UPDATED_DATE_LAST_MINUS_FROM_BALANCE);
        assertThat(testBalanceLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testBalanceLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testBalanceLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testBalanceLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testBalanceLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateBalanceLogWithPatch() throws Exception {
        // Initialize the database
        balanceLogRepository.saveAndFlush(balanceLog);

        int databaseSizeBeforeUpdate = balanceLogRepository.findAll().size();

        // Update the balanceLog using partial update
        BalanceLog partialUpdatedBalanceLog = new BalanceLog();
        partialUpdatedBalanceLog.setId(balanceLog.getId());

        partialUpdatedBalanceLog
            .balace(UPDATED_BALACE)
            .userId(UPDATED_USER_ID)
            .frostSum(UPDATED_FROST_SUM)
            .dateLastAddBalance(UPDATED_DATE_LAST_ADD_BALANCE)
            .dateLastMinusFromBalance(UPDATED_DATE_LAST_MINUS_FROM_BALANCE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restBalanceLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBalanceLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBalanceLog))
            )
            .andExpect(status().isOk());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeUpdate);
        BalanceLog testBalanceLog = balanceLogList.get(balanceLogList.size() - 1);
        assertThat(testBalanceLog.getBalace()).isEqualTo(UPDATED_BALACE);
        assertThat(testBalanceLog.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBalanceLog.getFrostSum()).isEqualTo(UPDATED_FROST_SUM);
        assertThat(testBalanceLog.getDateLastAddBalance()).isEqualTo(UPDATED_DATE_LAST_ADD_BALANCE);
        assertThat(testBalanceLog.getDateLastMinusFromBalance()).isEqualTo(UPDATED_DATE_LAST_MINUS_FROM_BALANCE);
        assertThat(testBalanceLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testBalanceLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testBalanceLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testBalanceLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testBalanceLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingBalanceLog() throws Exception {
        int databaseSizeBeforeUpdate = balanceLogRepository.findAll().size();
        balanceLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBalanceLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, balanceLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(balanceLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBalanceLog() throws Exception {
        int databaseSizeBeforeUpdate = balanceLogRepository.findAll().size();
        balanceLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBalanceLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(balanceLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBalanceLog() throws Exception {
        int databaseSizeBeforeUpdate = balanceLogRepository.findAll().size();
        balanceLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBalanceLogMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(balanceLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BalanceLog in the database
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBalanceLog() throws Exception {
        // Initialize the database
        balanceLogRepository.saveAndFlush(balanceLog);

        int databaseSizeBeforeDelete = balanceLogRepository.findAll().size();

        // Delete the balanceLog
        restBalanceLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, balanceLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BalanceLog> balanceLogList = balanceLogRepository.findAll();
        assertThat(balanceLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
