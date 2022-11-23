package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Balance;
import com.mycompany.myapp.repository.BalanceRepository;
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
 * Integration tests for the {@link BalanceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BalanceResourceIT {

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

    private static final String ENTITY_API_URL = "/api/balances";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBalanceMockMvc;

    private Balance balance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Balance createEntity(EntityManager em) {
        Balance balance = new Balance()
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
        return balance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Balance createUpdatedEntity(EntityManager em) {
        Balance balance = new Balance()
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
        return balance;
    }

    @BeforeEach
    public void initTest() {
        balance = createEntity(em);
    }

    @Test
    @Transactional
    void createBalance() throws Exception {
        int databaseSizeBeforeCreate = balanceRepository.findAll().size();
        // Create the Balance
        restBalanceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(balance)))
            .andExpect(status().isCreated());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeCreate + 1);
        Balance testBalance = balanceList.get(balanceList.size() - 1);
        assertThat(testBalance.getBalace()).isEqualTo(DEFAULT_BALACE);
        assertThat(testBalance.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testBalance.getFrostSum()).isEqualTo(DEFAULT_FROST_SUM);
        assertThat(testBalance.getDateLastAddBalance()).isEqualTo(DEFAULT_DATE_LAST_ADD_BALANCE);
        assertThat(testBalance.getDateLastMinusFromBalance()).isEqualTo(DEFAULT_DATE_LAST_MINUS_FROM_BALANCE);
        assertThat(testBalance.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testBalance.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testBalance.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testBalance.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testBalance.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createBalanceWithExistingId() throws Exception {
        // Create the Balance with an existing ID
        balance.setId(1L);

        int databaseSizeBeforeCreate = balanceRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBalanceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(balance)))
            .andExpect(status().isBadRequest());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBalances() throws Exception {
        // Initialize the database
        balanceRepository.saveAndFlush(balance);

        // Get all the balanceList
        restBalanceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(balance.getId().intValue())))
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
    void getBalance() throws Exception {
        // Initialize the database
        balanceRepository.saveAndFlush(balance);

        // Get the balance
        restBalanceMockMvc
            .perform(get(ENTITY_API_URL_ID, balance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(balance.getId().intValue()))
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
    void getNonExistingBalance() throws Exception {
        // Get the balance
        restBalanceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBalance() throws Exception {
        // Initialize the database
        balanceRepository.saveAndFlush(balance);

        int databaseSizeBeforeUpdate = balanceRepository.findAll().size();

        // Update the balance
        Balance updatedBalance = balanceRepository.findById(balance.getId()).get();
        // Disconnect from session so that the updates on updatedBalance are not directly saved in db
        em.detach(updatedBalance);
        updatedBalance
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

        restBalanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBalance.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBalance))
            )
            .andExpect(status().isOk());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeUpdate);
        Balance testBalance = balanceList.get(balanceList.size() - 1);
        assertThat(testBalance.getBalace()).isEqualTo(UPDATED_BALACE);
        assertThat(testBalance.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBalance.getFrostSum()).isEqualTo(UPDATED_FROST_SUM);
        assertThat(testBalance.getDateLastAddBalance()).isEqualTo(UPDATED_DATE_LAST_ADD_BALANCE);
        assertThat(testBalance.getDateLastMinusFromBalance()).isEqualTo(UPDATED_DATE_LAST_MINUS_FROM_BALANCE);
        assertThat(testBalance.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testBalance.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testBalance.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testBalance.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testBalance.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingBalance() throws Exception {
        int databaseSizeBeforeUpdate = balanceRepository.findAll().size();
        balance.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBalanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, balance.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(balance))
            )
            .andExpect(status().isBadRequest());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBalance() throws Exception {
        int databaseSizeBeforeUpdate = balanceRepository.findAll().size();
        balance.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBalanceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(balance))
            )
            .andExpect(status().isBadRequest());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBalance() throws Exception {
        int databaseSizeBeforeUpdate = balanceRepository.findAll().size();
        balance.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBalanceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(balance)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBalanceWithPatch() throws Exception {
        // Initialize the database
        balanceRepository.saveAndFlush(balance);

        int databaseSizeBeforeUpdate = balanceRepository.findAll().size();

        // Update the balance using partial update
        Balance partialUpdatedBalance = new Balance();
        partialUpdatedBalance.setId(balance.getId());

        partialUpdatedBalance
            .balace(UPDATED_BALACE)
            .userId(UPDATED_USER_ID)
            .frostSum(UPDATED_FROST_SUM)
            .date1(UPDATED_DATE_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restBalanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBalance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBalance))
            )
            .andExpect(status().isOk());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeUpdate);
        Balance testBalance = balanceList.get(balanceList.size() - 1);
        assertThat(testBalance.getBalace()).isEqualTo(UPDATED_BALACE);
        assertThat(testBalance.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBalance.getFrostSum()).isEqualTo(UPDATED_FROST_SUM);
        assertThat(testBalance.getDateLastAddBalance()).isEqualTo(DEFAULT_DATE_LAST_ADD_BALANCE);
        assertThat(testBalance.getDateLastMinusFromBalance()).isEqualTo(DEFAULT_DATE_LAST_MINUS_FROM_BALANCE);
        assertThat(testBalance.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testBalance.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testBalance.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testBalance.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testBalance.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateBalanceWithPatch() throws Exception {
        // Initialize the database
        balanceRepository.saveAndFlush(balance);

        int databaseSizeBeforeUpdate = balanceRepository.findAll().size();

        // Update the balance using partial update
        Balance partialUpdatedBalance = new Balance();
        partialUpdatedBalance.setId(balance.getId());

        partialUpdatedBalance
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

        restBalanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBalance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBalance))
            )
            .andExpect(status().isOk());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeUpdate);
        Balance testBalance = balanceList.get(balanceList.size() - 1);
        assertThat(testBalance.getBalace()).isEqualTo(UPDATED_BALACE);
        assertThat(testBalance.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBalance.getFrostSum()).isEqualTo(UPDATED_FROST_SUM);
        assertThat(testBalance.getDateLastAddBalance()).isEqualTo(UPDATED_DATE_LAST_ADD_BALANCE);
        assertThat(testBalance.getDateLastMinusFromBalance()).isEqualTo(UPDATED_DATE_LAST_MINUS_FROM_BALANCE);
        assertThat(testBalance.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testBalance.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testBalance.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testBalance.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testBalance.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingBalance() throws Exception {
        int databaseSizeBeforeUpdate = balanceRepository.findAll().size();
        balance.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBalanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, balance.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(balance))
            )
            .andExpect(status().isBadRequest());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBalance() throws Exception {
        int databaseSizeBeforeUpdate = balanceRepository.findAll().size();
        balance.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBalanceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(balance))
            )
            .andExpect(status().isBadRequest());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBalance() throws Exception {
        int databaseSizeBeforeUpdate = balanceRepository.findAll().size();
        balance.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBalanceMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(balance)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Balance in the database
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBalance() throws Exception {
        // Initialize the database
        balanceRepository.saveAndFlush(balance);

        int databaseSizeBeforeDelete = balanceRepository.findAll().size();

        // Delete the balance
        restBalanceMockMvc
            .perform(delete(ENTITY_API_URL_ID, balance.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Balance> balanceList = balanceRepository.findAll();
        assertThat(balanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
