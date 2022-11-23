package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.MembersTradeDealLog;
import com.mycompany.myapp.repository.MembersTradeDealLogRepository;
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
 * Integration tests for the {@link MembersTradeDealLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MembersTradeDealLogResourceIT {

    private static final Long DEFAULT_TG_USER_ID_CURRENT = 1L;
    private static final Long UPDATED_TG_USER_ID_CURRENT = 2L;

    private static final Double DEFAULT_PRICE_OFFER = 1D;
    private static final Double UPDATED_PRICE_OFFER = 2D;

    private static final ZonedDateTime DEFAULT_CURRENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CURRENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Boolean DEFAULT_IS_WINNER = false;
    private static final Boolean UPDATED_IS_WINNER = true;

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

    private static final String ENTITY_API_URL = "/api/members-trade-deal-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MembersTradeDealLogRepository membersTradeDealLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMembersTradeDealLogMockMvc;

    private MembersTradeDealLog membersTradeDealLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MembersTradeDealLog createEntity(EntityManager em) {
        MembersTradeDealLog membersTradeDealLog = new MembersTradeDealLog()
            .tgUserIdCurrent(DEFAULT_TG_USER_ID_CURRENT)
            .priceOffer(DEFAULT_PRICE_OFFER)
            .currentDate(DEFAULT_CURRENT_DATE)
            .isWinner(DEFAULT_IS_WINNER)
            .isDelete(DEFAULT_IS_DELETE)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return membersTradeDealLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MembersTradeDealLog createUpdatedEntity(EntityManager em) {
        MembersTradeDealLog membersTradeDealLog = new MembersTradeDealLog()
            .tgUserIdCurrent(UPDATED_TG_USER_ID_CURRENT)
            .priceOffer(UPDATED_PRICE_OFFER)
            .currentDate(UPDATED_CURRENT_DATE)
            .isWinner(UPDATED_IS_WINNER)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return membersTradeDealLog;
    }

    @BeforeEach
    public void initTest() {
        membersTradeDealLog = createEntity(em);
    }

    @Test
    @Transactional
    void createMembersTradeDealLog() throws Exception {
        int databaseSizeBeforeCreate = membersTradeDealLogRepository.findAll().size();
        // Create the MembersTradeDealLog
        restMembersTradeDealLogMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(membersTradeDealLog))
            )
            .andExpect(status().isCreated());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeCreate + 1);
        MembersTradeDealLog testMembersTradeDealLog = membersTradeDealLogList.get(membersTradeDealLogList.size() - 1);
        assertThat(testMembersTradeDealLog.getTgUserIdCurrent()).isEqualTo(DEFAULT_TG_USER_ID_CURRENT);
        assertThat(testMembersTradeDealLog.getPriceOffer()).isEqualTo(DEFAULT_PRICE_OFFER);
        assertThat(testMembersTradeDealLog.getCurrentDate()).isEqualTo(DEFAULT_CURRENT_DATE);
        assertThat(testMembersTradeDealLog.getIsWinner()).isEqualTo(DEFAULT_IS_WINNER);
        assertThat(testMembersTradeDealLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testMembersTradeDealLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testMembersTradeDealLog.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testMembersTradeDealLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testMembersTradeDealLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testMembersTradeDealLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createMembersTradeDealLogWithExistingId() throws Exception {
        // Create the MembersTradeDealLog with an existing ID
        membersTradeDealLog.setId(1L);

        int databaseSizeBeforeCreate = membersTradeDealLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMembersTradeDealLogMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(membersTradeDealLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMembersTradeDealLogs() throws Exception {
        // Initialize the database
        membersTradeDealLogRepository.saveAndFlush(membersTradeDealLog);

        // Get all the membersTradeDealLogList
        restMembersTradeDealLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(membersTradeDealLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].tgUserIdCurrent").value(hasItem(DEFAULT_TG_USER_ID_CURRENT.intValue())))
            .andExpect(jsonPath("$.[*].priceOffer").value(hasItem(DEFAULT_PRICE_OFFER.doubleValue())))
            .andExpect(jsonPath("$.[*].currentDate").value(hasItem(sameInstant(DEFAULT_CURRENT_DATE))))
            .andExpect(jsonPath("$.[*].isWinner").value(hasItem(DEFAULT_IS_WINNER.booleanValue())))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
    }

    @Test
    @Transactional
    void getMembersTradeDealLog() throws Exception {
        // Initialize the database
        membersTradeDealLogRepository.saveAndFlush(membersTradeDealLog);

        // Get the membersTradeDealLog
        restMembersTradeDealLogMockMvc
            .perform(get(ENTITY_API_URL_ID, membersTradeDealLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(membersTradeDealLog.getId().intValue()))
            .andExpect(jsonPath("$.tgUserIdCurrent").value(DEFAULT_TG_USER_ID_CURRENT.intValue()))
            .andExpect(jsonPath("$.priceOffer").value(DEFAULT_PRICE_OFFER.doubleValue()))
            .andExpect(jsonPath("$.currentDate").value(sameInstant(DEFAULT_CURRENT_DATE)))
            .andExpect(jsonPath("$.isWinner").value(DEFAULT_IS_WINNER.booleanValue()))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE.booleanValue()))
            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingMembersTradeDealLog() throws Exception {
        // Get the membersTradeDealLog
        restMembersTradeDealLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewMembersTradeDealLog() throws Exception {
        // Initialize the database
        membersTradeDealLogRepository.saveAndFlush(membersTradeDealLog);

        int databaseSizeBeforeUpdate = membersTradeDealLogRepository.findAll().size();

        // Update the membersTradeDealLog
        MembersTradeDealLog updatedMembersTradeDealLog = membersTradeDealLogRepository.findById(membersTradeDealLog.getId()).get();
        // Disconnect from session so that the updates on updatedMembersTradeDealLog are not directly saved in db
        em.detach(updatedMembersTradeDealLog);
        updatedMembersTradeDealLog
            .tgUserIdCurrent(UPDATED_TG_USER_ID_CURRENT)
            .priceOffer(UPDATED_PRICE_OFFER)
            .currentDate(UPDATED_CURRENT_DATE)
            .isWinner(UPDATED_IS_WINNER)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restMembersTradeDealLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMembersTradeDealLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMembersTradeDealLog))
            )
            .andExpect(status().isOk());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeUpdate);
        MembersTradeDealLog testMembersTradeDealLog = membersTradeDealLogList.get(membersTradeDealLogList.size() - 1);
        assertThat(testMembersTradeDealLog.getTgUserIdCurrent()).isEqualTo(UPDATED_TG_USER_ID_CURRENT);
        assertThat(testMembersTradeDealLog.getPriceOffer()).isEqualTo(UPDATED_PRICE_OFFER);
        assertThat(testMembersTradeDealLog.getCurrentDate()).isEqualTo(UPDATED_CURRENT_DATE);
        assertThat(testMembersTradeDealLog.getIsWinner()).isEqualTo(UPDATED_IS_WINNER);
        assertThat(testMembersTradeDealLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testMembersTradeDealLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testMembersTradeDealLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testMembersTradeDealLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testMembersTradeDealLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testMembersTradeDealLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingMembersTradeDealLog() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealLogRepository.findAll().size();
        membersTradeDealLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMembersTradeDealLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, membersTradeDealLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(membersTradeDealLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMembersTradeDealLog() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealLogRepository.findAll().size();
        membersTradeDealLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembersTradeDealLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(membersTradeDealLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMembersTradeDealLog() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealLogRepository.findAll().size();
        membersTradeDealLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembersTradeDealLogMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(membersTradeDealLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMembersTradeDealLogWithPatch() throws Exception {
        // Initialize the database
        membersTradeDealLogRepository.saveAndFlush(membersTradeDealLog);

        int databaseSizeBeforeUpdate = membersTradeDealLogRepository.findAll().size();

        // Update the membersTradeDealLog using partial update
        MembersTradeDealLog partialUpdatedMembersTradeDealLog = new MembersTradeDealLog();
        partialUpdatedMembersTradeDealLog.setId(membersTradeDealLog.getId());

        partialUpdatedMembersTradeDealLog
            .isWinner(UPDATED_IS_WINNER)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restMembersTradeDealLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMembersTradeDealLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMembersTradeDealLog))
            )
            .andExpect(status().isOk());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeUpdate);
        MembersTradeDealLog testMembersTradeDealLog = membersTradeDealLogList.get(membersTradeDealLogList.size() - 1);
        assertThat(testMembersTradeDealLog.getTgUserIdCurrent()).isEqualTo(DEFAULT_TG_USER_ID_CURRENT);
        assertThat(testMembersTradeDealLog.getPriceOffer()).isEqualTo(DEFAULT_PRICE_OFFER);
        assertThat(testMembersTradeDealLog.getCurrentDate()).isEqualTo(DEFAULT_CURRENT_DATE);
        assertThat(testMembersTradeDealLog.getIsWinner()).isEqualTo(UPDATED_IS_WINNER);
        assertThat(testMembersTradeDealLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testMembersTradeDealLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testMembersTradeDealLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testMembersTradeDealLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testMembersTradeDealLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testMembersTradeDealLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateMembersTradeDealLogWithPatch() throws Exception {
        // Initialize the database
        membersTradeDealLogRepository.saveAndFlush(membersTradeDealLog);

        int databaseSizeBeforeUpdate = membersTradeDealLogRepository.findAll().size();

        // Update the membersTradeDealLog using partial update
        MembersTradeDealLog partialUpdatedMembersTradeDealLog = new MembersTradeDealLog();
        partialUpdatedMembersTradeDealLog.setId(membersTradeDealLog.getId());

        partialUpdatedMembersTradeDealLog
            .tgUserIdCurrent(UPDATED_TG_USER_ID_CURRENT)
            .priceOffer(UPDATED_PRICE_OFFER)
            .currentDate(UPDATED_CURRENT_DATE)
            .isWinner(UPDATED_IS_WINNER)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restMembersTradeDealLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMembersTradeDealLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMembersTradeDealLog))
            )
            .andExpect(status().isOk());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeUpdate);
        MembersTradeDealLog testMembersTradeDealLog = membersTradeDealLogList.get(membersTradeDealLogList.size() - 1);
        assertThat(testMembersTradeDealLog.getTgUserIdCurrent()).isEqualTo(UPDATED_TG_USER_ID_CURRENT);
        assertThat(testMembersTradeDealLog.getPriceOffer()).isEqualTo(UPDATED_PRICE_OFFER);
        assertThat(testMembersTradeDealLog.getCurrentDate()).isEqualTo(UPDATED_CURRENT_DATE);
        assertThat(testMembersTradeDealLog.getIsWinner()).isEqualTo(UPDATED_IS_WINNER);
        assertThat(testMembersTradeDealLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testMembersTradeDealLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testMembersTradeDealLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testMembersTradeDealLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testMembersTradeDealLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testMembersTradeDealLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingMembersTradeDealLog() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealLogRepository.findAll().size();
        membersTradeDealLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMembersTradeDealLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, membersTradeDealLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(membersTradeDealLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMembersTradeDealLog() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealLogRepository.findAll().size();
        membersTradeDealLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembersTradeDealLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(membersTradeDealLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMembersTradeDealLog() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealLogRepository.findAll().size();
        membersTradeDealLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembersTradeDealLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(membersTradeDealLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MembersTradeDealLog in the database
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMembersTradeDealLog() throws Exception {
        // Initialize the database
        membersTradeDealLogRepository.saveAndFlush(membersTradeDealLog);

        int databaseSizeBeforeDelete = membersTradeDealLogRepository.findAll().size();

        // Delete the membersTradeDealLog
        restMembersTradeDealLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, membersTradeDealLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MembersTradeDealLog> membersTradeDealLogList = membersTradeDealLogRepository.findAll();
        assertThat(membersTradeDealLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
