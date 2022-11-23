package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TradeShopLog;
import com.mycompany.myapp.repository.TradeShopLogRepository;
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
 * Integration tests for the {@link TradeShopLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TradeShopLogResourceIT {

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE_DIAPOZON = 1D;
    private static final Double UPDATED_PRICE_DIAPOZON = 2D;

    private static final Double DEFAULT_CURRENT_PRICE = 1D;
    private static final Double UPDATED_CURRENT_PRICE = 2D;

    private static final Long DEFAULT_WHICE_LINE_FROM_ALL_COUNT_LINES = 1L;
    private static final Long UPDATED_WHICE_LINE_FROM_ALL_COUNT_LINES = 2L;

    private static final Long DEFAULT_TG_USER_ID_WINNER = 1L;
    private static final Long UPDATED_TG_USER_ID_WINNER = 2L;

    private static final Long DEFAULT_IN_WHAT_DATE_WILL_POST_THIS_LINKS = 1L;
    private static final Long UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS = 2L;

    private static final ZonedDateTime DEFAULT_DATE_FINISH_TORGS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_FINISH_TORGS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

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

    private static final String ENTITY_API_URL = "/api/trade-shop-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TradeShopLogRepository tradeShopLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTradeShopLogMockMvc;

    private TradeShopLog tradeShopLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeShopLog createEntity(EntityManager em) {
        TradeShopLog tradeShopLog = new TradeShopLog()
            .category(DEFAULT_CATEGORY)
            .priceDiapozon(DEFAULT_PRICE_DIAPOZON)
            .currentPrice(DEFAULT_CURRENT_PRICE)
            .whiceLineFromAllCountLines(DEFAULT_WHICE_LINE_FROM_ALL_COUNT_LINES)
            .tgUserIdWinner(DEFAULT_TG_USER_ID_WINNER)
            .inWhatDateWillPostThisLinks(DEFAULT_IN_WHAT_DATE_WILL_POST_THIS_LINKS)
            .dateFinishTorgs(DEFAULT_DATE_FINISH_TORGS)
            .isDelete(DEFAULT_IS_DELETE)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return tradeShopLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeShopLog createUpdatedEntity(EntityManager em) {
        TradeShopLog tradeShopLog = new TradeShopLog()
            .category(UPDATED_CATEGORY)
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .currentPrice(UPDATED_CURRENT_PRICE)
            .whiceLineFromAllCountLines(UPDATED_WHICE_LINE_FROM_ALL_COUNT_LINES)
            .tgUserIdWinner(UPDATED_TG_USER_ID_WINNER)
            .inWhatDateWillPostThisLinks(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS)
            .dateFinishTorgs(UPDATED_DATE_FINISH_TORGS)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return tradeShopLog;
    }

    @BeforeEach
    public void initTest() {
        tradeShopLog = createEntity(em);
    }

    @Test
    @Transactional
    void createTradeShopLog() throws Exception {
        int databaseSizeBeforeCreate = tradeShopLogRepository.findAll().size();
        // Create the TradeShopLog
        restTradeShopLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tradeShopLog)))
            .andExpect(status().isCreated());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeCreate + 1);
        TradeShopLog testTradeShopLog = tradeShopLogList.get(tradeShopLogList.size() - 1);
        assertThat(testTradeShopLog.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testTradeShopLog.getPriceDiapozon()).isEqualTo(DEFAULT_PRICE_DIAPOZON);
        assertThat(testTradeShopLog.getCurrentPrice()).isEqualTo(DEFAULT_CURRENT_PRICE);
        assertThat(testTradeShopLog.getWhiceLineFromAllCountLines()).isEqualTo(DEFAULT_WHICE_LINE_FROM_ALL_COUNT_LINES);
        assertThat(testTradeShopLog.getTgUserIdWinner()).isEqualTo(DEFAULT_TG_USER_ID_WINNER);
        assertThat(testTradeShopLog.getInWhatDateWillPostThisLinks()).isEqualTo(DEFAULT_IN_WHAT_DATE_WILL_POST_THIS_LINKS);
        assertThat(testTradeShopLog.getDateFinishTorgs()).isEqualTo(DEFAULT_DATE_FINISH_TORGS);
        assertThat(testTradeShopLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testTradeShopLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testTradeShopLog.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testTradeShopLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testTradeShopLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testTradeShopLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createTradeShopLogWithExistingId() throws Exception {
        // Create the TradeShopLog with an existing ID
        tradeShopLog.setId(1L);

        int databaseSizeBeforeCreate = tradeShopLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTradeShopLogMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tradeShopLog)))
            .andExpect(status().isBadRequest());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTradeShopLogs() throws Exception {
        // Initialize the database
        tradeShopLogRepository.saveAndFlush(tradeShopLog);

        // Get all the tradeShopLogList
        restTradeShopLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tradeShopLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].priceDiapozon").value(hasItem(DEFAULT_PRICE_DIAPOZON.doubleValue())))
            .andExpect(jsonPath("$.[*].currentPrice").value(hasItem(DEFAULT_CURRENT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].whiceLineFromAllCountLines").value(hasItem(DEFAULT_WHICE_LINE_FROM_ALL_COUNT_LINES.intValue())))
            .andExpect(jsonPath("$.[*].tgUserIdWinner").value(hasItem(DEFAULT_TG_USER_ID_WINNER.intValue())))
            .andExpect(jsonPath("$.[*].inWhatDateWillPostThisLinks").value(hasItem(DEFAULT_IN_WHAT_DATE_WILL_POST_THIS_LINKS.intValue())))
            .andExpect(jsonPath("$.[*].dateFinishTorgs").value(hasItem(sameInstant(DEFAULT_DATE_FINISH_TORGS))))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
    }

    @Test
    @Transactional
    void getTradeShopLog() throws Exception {
        // Initialize the database
        tradeShopLogRepository.saveAndFlush(tradeShopLog);

        // Get the tradeShopLog
        restTradeShopLogMockMvc
            .perform(get(ENTITY_API_URL_ID, tradeShopLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tradeShopLog.getId().intValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.priceDiapozon").value(DEFAULT_PRICE_DIAPOZON.doubleValue()))
            .andExpect(jsonPath("$.currentPrice").value(DEFAULT_CURRENT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.whiceLineFromAllCountLines").value(DEFAULT_WHICE_LINE_FROM_ALL_COUNT_LINES.intValue()))
            .andExpect(jsonPath("$.tgUserIdWinner").value(DEFAULT_TG_USER_ID_WINNER.intValue()))
            .andExpect(jsonPath("$.inWhatDateWillPostThisLinks").value(DEFAULT_IN_WHAT_DATE_WILL_POST_THIS_LINKS.intValue()))
            .andExpect(jsonPath("$.dateFinishTorgs").value(sameInstant(DEFAULT_DATE_FINISH_TORGS)))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE.booleanValue()))
            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTradeShopLog() throws Exception {
        // Get the tradeShopLog
        restTradeShopLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTradeShopLog() throws Exception {
        // Initialize the database
        tradeShopLogRepository.saveAndFlush(tradeShopLog);

        int databaseSizeBeforeUpdate = tradeShopLogRepository.findAll().size();

        // Update the tradeShopLog
        TradeShopLog updatedTradeShopLog = tradeShopLogRepository.findById(tradeShopLog.getId()).get();
        // Disconnect from session so that the updates on updatedTradeShopLog are not directly saved in db
        em.detach(updatedTradeShopLog);
        updatedTradeShopLog
            .category(UPDATED_CATEGORY)
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .currentPrice(UPDATED_CURRENT_PRICE)
            .whiceLineFromAllCountLines(UPDATED_WHICE_LINE_FROM_ALL_COUNT_LINES)
            .tgUserIdWinner(UPDATED_TG_USER_ID_WINNER)
            .inWhatDateWillPostThisLinks(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS)
            .dateFinishTorgs(UPDATED_DATE_FINISH_TORGS)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restTradeShopLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTradeShopLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTradeShopLog))
            )
            .andExpect(status().isOk());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeUpdate);
        TradeShopLog testTradeShopLog = tradeShopLogList.get(tradeShopLogList.size() - 1);
        assertThat(testTradeShopLog.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTradeShopLog.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testTradeShopLog.getCurrentPrice()).isEqualTo(UPDATED_CURRENT_PRICE);
        assertThat(testTradeShopLog.getWhiceLineFromAllCountLines()).isEqualTo(UPDATED_WHICE_LINE_FROM_ALL_COUNT_LINES);
        assertThat(testTradeShopLog.getTgUserIdWinner()).isEqualTo(UPDATED_TG_USER_ID_WINNER);
        assertThat(testTradeShopLog.getInWhatDateWillPostThisLinks()).isEqualTo(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS);
        assertThat(testTradeShopLog.getDateFinishTorgs()).isEqualTo(UPDATED_DATE_FINISH_TORGS);
        assertThat(testTradeShopLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testTradeShopLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTradeShopLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testTradeShopLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTradeShopLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testTradeShopLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingTradeShopLog() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopLogRepository.findAll().size();
        tradeShopLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTradeShopLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tradeShopLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tradeShopLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTradeShopLog() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopLogRepository.findAll().size();
        tradeShopLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeShopLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tradeShopLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTradeShopLog() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopLogRepository.findAll().size();
        tradeShopLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeShopLogMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tradeShopLog)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTradeShopLogWithPatch() throws Exception {
        // Initialize the database
        tradeShopLogRepository.saveAndFlush(tradeShopLog);

        int databaseSizeBeforeUpdate = tradeShopLogRepository.findAll().size();

        // Update the tradeShopLog using partial update
        TradeShopLog partialUpdatedTradeShopLog = new TradeShopLog();
        partialUpdatedTradeShopLog.setId(tradeShopLog.getId());

        partialUpdatedTradeShopLog
            .category(UPDATED_CATEGORY)
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .whiceLineFromAllCountLines(UPDATED_WHICE_LINE_FROM_ALL_COUNT_LINES)
            .tgUserIdWinner(UPDATED_TG_USER_ID_WINNER)
            .inWhatDateWillPostThisLinks(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS)
            .dateFinishTorgs(UPDATED_DATE_FINISH_TORGS)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1);

        restTradeShopLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTradeShopLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTradeShopLog))
            )
            .andExpect(status().isOk());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeUpdate);
        TradeShopLog testTradeShopLog = tradeShopLogList.get(tradeShopLogList.size() - 1);
        assertThat(testTradeShopLog.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTradeShopLog.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testTradeShopLog.getCurrentPrice()).isEqualTo(DEFAULT_CURRENT_PRICE);
        assertThat(testTradeShopLog.getWhiceLineFromAllCountLines()).isEqualTo(UPDATED_WHICE_LINE_FROM_ALL_COUNT_LINES);
        assertThat(testTradeShopLog.getTgUserIdWinner()).isEqualTo(UPDATED_TG_USER_ID_WINNER);
        assertThat(testTradeShopLog.getInWhatDateWillPostThisLinks()).isEqualTo(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS);
        assertThat(testTradeShopLog.getDateFinishTorgs()).isEqualTo(UPDATED_DATE_FINISH_TORGS);
        assertThat(testTradeShopLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testTradeShopLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTradeShopLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testTradeShopLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTradeShopLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testTradeShopLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateTradeShopLogWithPatch() throws Exception {
        // Initialize the database
        tradeShopLogRepository.saveAndFlush(tradeShopLog);

        int databaseSizeBeforeUpdate = tradeShopLogRepository.findAll().size();

        // Update the tradeShopLog using partial update
        TradeShopLog partialUpdatedTradeShopLog = new TradeShopLog();
        partialUpdatedTradeShopLog.setId(tradeShopLog.getId());

        partialUpdatedTradeShopLog
            .category(UPDATED_CATEGORY)
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .currentPrice(UPDATED_CURRENT_PRICE)
            .whiceLineFromAllCountLines(UPDATED_WHICE_LINE_FROM_ALL_COUNT_LINES)
            .tgUserIdWinner(UPDATED_TG_USER_ID_WINNER)
            .inWhatDateWillPostThisLinks(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS)
            .dateFinishTorgs(UPDATED_DATE_FINISH_TORGS)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restTradeShopLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTradeShopLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTradeShopLog))
            )
            .andExpect(status().isOk());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeUpdate);
        TradeShopLog testTradeShopLog = tradeShopLogList.get(tradeShopLogList.size() - 1);
        assertThat(testTradeShopLog.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTradeShopLog.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testTradeShopLog.getCurrentPrice()).isEqualTo(UPDATED_CURRENT_PRICE);
        assertThat(testTradeShopLog.getWhiceLineFromAllCountLines()).isEqualTo(UPDATED_WHICE_LINE_FROM_ALL_COUNT_LINES);
        assertThat(testTradeShopLog.getTgUserIdWinner()).isEqualTo(UPDATED_TG_USER_ID_WINNER);
        assertThat(testTradeShopLog.getInWhatDateWillPostThisLinks()).isEqualTo(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS);
        assertThat(testTradeShopLog.getDateFinishTorgs()).isEqualTo(UPDATED_DATE_FINISH_TORGS);
        assertThat(testTradeShopLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testTradeShopLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTradeShopLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testTradeShopLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTradeShopLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testTradeShopLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingTradeShopLog() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopLogRepository.findAll().size();
        tradeShopLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTradeShopLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tradeShopLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tradeShopLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTradeShopLog() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopLogRepository.findAll().size();
        tradeShopLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeShopLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tradeShopLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTradeShopLog() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopLogRepository.findAll().size();
        tradeShopLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeShopLogMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tradeShopLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TradeShopLog in the database
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTradeShopLog() throws Exception {
        // Initialize the database
        tradeShopLogRepository.saveAndFlush(tradeShopLog);

        int databaseSizeBeforeDelete = tradeShopLogRepository.findAll().size();

        // Delete the tradeShopLog
        restTradeShopLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, tradeShopLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TradeShopLog> tradeShopLogList = tradeShopLogRepository.findAll();
        assertThat(tradeShopLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
