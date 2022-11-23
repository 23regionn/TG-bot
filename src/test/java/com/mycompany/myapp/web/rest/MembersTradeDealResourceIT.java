package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.MembersTradeDeal;
import com.mycompany.myapp.repository.MembersTradeDealRepository;
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
 * Integration tests for the {@link MembersTradeDealResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MembersTradeDealResourceIT {

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

    private static final String ENTITY_API_URL = "/api/members-trade-deals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MembersTradeDealRepository membersTradeDealRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMembersTradeDealMockMvc;

    private MembersTradeDeal membersTradeDeal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MembersTradeDeal createEntity(EntityManager em) {
        MembersTradeDeal membersTradeDeal = new MembersTradeDeal()
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
        return membersTradeDeal;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MembersTradeDeal createUpdatedEntity(EntityManager em) {
        MembersTradeDeal membersTradeDeal = new MembersTradeDeal()
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
        return membersTradeDeal;
    }

    @BeforeEach
    public void initTest() {
        membersTradeDeal = createEntity(em);
    }

    @Test
    @Transactional
    void createMembersTradeDeal() throws Exception {
        int databaseSizeBeforeCreate = membersTradeDealRepository.findAll().size();
        // Create the MembersTradeDeal
        restMembersTradeDealMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(membersTradeDeal))
            )
            .andExpect(status().isCreated());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeCreate + 1);
        MembersTradeDeal testMembersTradeDeal = membersTradeDealList.get(membersTradeDealList.size() - 1);
        assertThat(testMembersTradeDeal.getTgUserIdCurrent()).isEqualTo(DEFAULT_TG_USER_ID_CURRENT);
        assertThat(testMembersTradeDeal.getPriceOffer()).isEqualTo(DEFAULT_PRICE_OFFER);
        assertThat(testMembersTradeDeal.getCurrentDate()).isEqualTo(DEFAULT_CURRENT_DATE);
        assertThat(testMembersTradeDeal.getIsWinner()).isEqualTo(DEFAULT_IS_WINNER);
        assertThat(testMembersTradeDeal.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testMembersTradeDeal.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testMembersTradeDeal.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testMembersTradeDeal.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testMembersTradeDeal.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testMembersTradeDeal.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createMembersTradeDealWithExistingId() throws Exception {
        // Create the MembersTradeDeal with an existing ID
        membersTradeDeal.setId(1L);

        int databaseSizeBeforeCreate = membersTradeDealRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMembersTradeDealMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(membersTradeDeal))
            )
            .andExpect(status().isBadRequest());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMembersTradeDeals() throws Exception {
        // Initialize the database
        membersTradeDealRepository.saveAndFlush(membersTradeDeal);

        // Get all the membersTradeDealList
        restMembersTradeDealMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(membersTradeDeal.getId().intValue())))
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
    void getMembersTradeDeal() throws Exception {
        // Initialize the database
        membersTradeDealRepository.saveAndFlush(membersTradeDeal);

        // Get the membersTradeDeal
        restMembersTradeDealMockMvc
            .perform(get(ENTITY_API_URL_ID, membersTradeDeal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(membersTradeDeal.getId().intValue()))
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
    void getNonExistingMembersTradeDeal() throws Exception {
        // Get the membersTradeDeal
        restMembersTradeDealMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewMembersTradeDeal() throws Exception {
        // Initialize the database
        membersTradeDealRepository.saveAndFlush(membersTradeDeal);

        int databaseSizeBeforeUpdate = membersTradeDealRepository.findAll().size();

        // Update the membersTradeDeal
        MembersTradeDeal updatedMembersTradeDeal = membersTradeDealRepository.findById(membersTradeDeal.getId()).get();
        // Disconnect from session so that the updates on updatedMembersTradeDeal are not directly saved in db
        em.detach(updatedMembersTradeDeal);
        updatedMembersTradeDeal
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

        restMembersTradeDealMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMembersTradeDeal.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMembersTradeDeal))
            )
            .andExpect(status().isOk());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeUpdate);
        MembersTradeDeal testMembersTradeDeal = membersTradeDealList.get(membersTradeDealList.size() - 1);
        assertThat(testMembersTradeDeal.getTgUserIdCurrent()).isEqualTo(UPDATED_TG_USER_ID_CURRENT);
        assertThat(testMembersTradeDeal.getPriceOffer()).isEqualTo(UPDATED_PRICE_OFFER);
        assertThat(testMembersTradeDeal.getCurrentDate()).isEqualTo(UPDATED_CURRENT_DATE);
        assertThat(testMembersTradeDeal.getIsWinner()).isEqualTo(UPDATED_IS_WINNER);
        assertThat(testMembersTradeDeal.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testMembersTradeDeal.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testMembersTradeDeal.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testMembersTradeDeal.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testMembersTradeDeal.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testMembersTradeDeal.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingMembersTradeDeal() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealRepository.findAll().size();
        membersTradeDeal.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMembersTradeDealMockMvc
            .perform(
                put(ENTITY_API_URL_ID, membersTradeDeal.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(membersTradeDeal))
            )
            .andExpect(status().isBadRequest());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMembersTradeDeal() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealRepository.findAll().size();
        membersTradeDeal.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembersTradeDealMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(membersTradeDeal))
            )
            .andExpect(status().isBadRequest());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMembersTradeDeal() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealRepository.findAll().size();
        membersTradeDeal.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembersTradeDealMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(membersTradeDeal))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMembersTradeDealWithPatch() throws Exception {
        // Initialize the database
        membersTradeDealRepository.saveAndFlush(membersTradeDeal);

        int databaseSizeBeforeUpdate = membersTradeDealRepository.findAll().size();

        // Update the membersTradeDeal using partial update
        MembersTradeDeal partialUpdatedMembersTradeDeal = new MembersTradeDeal();
        partialUpdatedMembersTradeDeal.setId(membersTradeDeal.getId());

        partialUpdatedMembersTradeDeal
            .tgUserIdCurrent(UPDATED_TG_USER_ID_CURRENT)
            .currentDate(UPDATED_CURRENT_DATE)
            .isWinner(UPDATED_IS_WINNER)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .boolean1(UPDATED_BOOLEAN_1);

        restMembersTradeDealMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMembersTradeDeal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMembersTradeDeal))
            )
            .andExpect(status().isOk());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeUpdate);
        MembersTradeDeal testMembersTradeDeal = membersTradeDealList.get(membersTradeDealList.size() - 1);
        assertThat(testMembersTradeDeal.getTgUserIdCurrent()).isEqualTo(UPDATED_TG_USER_ID_CURRENT);
        assertThat(testMembersTradeDeal.getPriceOffer()).isEqualTo(DEFAULT_PRICE_OFFER);
        assertThat(testMembersTradeDeal.getCurrentDate()).isEqualTo(UPDATED_CURRENT_DATE);
        assertThat(testMembersTradeDeal.getIsWinner()).isEqualTo(UPDATED_IS_WINNER);
        assertThat(testMembersTradeDeal.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testMembersTradeDeal.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testMembersTradeDeal.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testMembersTradeDeal.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testMembersTradeDeal.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testMembersTradeDeal.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateMembersTradeDealWithPatch() throws Exception {
        // Initialize the database
        membersTradeDealRepository.saveAndFlush(membersTradeDeal);

        int databaseSizeBeforeUpdate = membersTradeDealRepository.findAll().size();

        // Update the membersTradeDeal using partial update
        MembersTradeDeal partialUpdatedMembersTradeDeal = new MembersTradeDeal();
        partialUpdatedMembersTradeDeal.setId(membersTradeDeal.getId());

        partialUpdatedMembersTradeDeal
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

        restMembersTradeDealMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMembersTradeDeal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMembersTradeDeal))
            )
            .andExpect(status().isOk());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeUpdate);
        MembersTradeDeal testMembersTradeDeal = membersTradeDealList.get(membersTradeDealList.size() - 1);
        assertThat(testMembersTradeDeal.getTgUserIdCurrent()).isEqualTo(UPDATED_TG_USER_ID_CURRENT);
        assertThat(testMembersTradeDeal.getPriceOffer()).isEqualTo(UPDATED_PRICE_OFFER);
        assertThat(testMembersTradeDeal.getCurrentDate()).isEqualTo(UPDATED_CURRENT_DATE);
        assertThat(testMembersTradeDeal.getIsWinner()).isEqualTo(UPDATED_IS_WINNER);
        assertThat(testMembersTradeDeal.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testMembersTradeDeal.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testMembersTradeDeal.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testMembersTradeDeal.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testMembersTradeDeal.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testMembersTradeDeal.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingMembersTradeDeal() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealRepository.findAll().size();
        membersTradeDeal.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMembersTradeDealMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, membersTradeDeal.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(membersTradeDeal))
            )
            .andExpect(status().isBadRequest());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMembersTradeDeal() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealRepository.findAll().size();
        membersTradeDeal.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembersTradeDealMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(membersTradeDeal))
            )
            .andExpect(status().isBadRequest());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMembersTradeDeal() throws Exception {
        int databaseSizeBeforeUpdate = membersTradeDealRepository.findAll().size();
        membersTradeDeal.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembersTradeDealMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(membersTradeDeal))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MembersTradeDeal in the database
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMembersTradeDeal() throws Exception {
        // Initialize the database
        membersTradeDealRepository.saveAndFlush(membersTradeDeal);

        int databaseSizeBeforeDelete = membersTradeDealRepository.findAll().size();

        // Delete the membersTradeDeal
        restMembersTradeDealMockMvc
            .perform(delete(ENTITY_API_URL_ID, membersTradeDeal.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MembersTradeDeal> membersTradeDealList = membersTradeDealRepository.findAll();
        assertThat(membersTradeDealList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
