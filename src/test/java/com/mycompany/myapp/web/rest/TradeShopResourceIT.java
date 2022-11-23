package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TradeShop;
import com.mycompany.myapp.repository.TradeShopRepository;
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
 * Integration tests for the {@link TradeShopResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TradeShopResourceIT {

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

    private static final String ENTITY_API_URL = "/api/trade-shops";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TradeShopRepository tradeShopRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTradeShopMockMvc;

    private TradeShop tradeShop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeShop createEntity(EntityManager em) {
        TradeShop tradeShop = new TradeShop()
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
        return tradeShop;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeShop createUpdatedEntity(EntityManager em) {
        TradeShop tradeShop = new TradeShop()
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
        return tradeShop;
    }

    @BeforeEach
    public void initTest() {
        tradeShop = createEntity(em);
    }

    @Test
    @Transactional
    void createTradeShop() throws Exception {
        int databaseSizeBeforeCreate = tradeShopRepository.findAll().size();
        // Create the TradeShop
        restTradeShopMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tradeShop)))
            .andExpect(status().isCreated());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeCreate + 1);
        TradeShop testTradeShop = tradeShopList.get(tradeShopList.size() - 1);
        assertThat(testTradeShop.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testTradeShop.getPriceDiapozon()).isEqualTo(DEFAULT_PRICE_DIAPOZON);
        assertThat(testTradeShop.getCurrentPrice()).isEqualTo(DEFAULT_CURRENT_PRICE);
        assertThat(testTradeShop.getWhiceLineFromAllCountLines()).isEqualTo(DEFAULT_WHICE_LINE_FROM_ALL_COUNT_LINES);
        assertThat(testTradeShop.getTgUserIdWinner()).isEqualTo(DEFAULT_TG_USER_ID_WINNER);
        assertThat(testTradeShop.getInWhatDateWillPostThisLinks()).isEqualTo(DEFAULT_IN_WHAT_DATE_WILL_POST_THIS_LINKS);
        assertThat(testTradeShop.getDateFinishTorgs()).isEqualTo(DEFAULT_DATE_FINISH_TORGS);
        assertThat(testTradeShop.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testTradeShop.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testTradeShop.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testTradeShop.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testTradeShop.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testTradeShop.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createTradeShopWithExistingId() throws Exception {
        // Create the TradeShop with an existing ID
        tradeShop.setId(1L);

        int databaseSizeBeforeCreate = tradeShopRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTradeShopMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tradeShop)))
            .andExpect(status().isBadRequest());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTradeShops() throws Exception {
        // Initialize the database
        tradeShopRepository.saveAndFlush(tradeShop);

        // Get all the tradeShopList
        restTradeShopMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tradeShop.getId().intValue())))
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
    void getTradeShop() throws Exception {
        // Initialize the database
        tradeShopRepository.saveAndFlush(tradeShop);

        // Get the tradeShop
        restTradeShopMockMvc
            .perform(get(ENTITY_API_URL_ID, tradeShop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tradeShop.getId().intValue()))
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
    void getNonExistingTradeShop() throws Exception {
        // Get the tradeShop
        restTradeShopMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTradeShop() throws Exception {
        // Initialize the database
        tradeShopRepository.saveAndFlush(tradeShop);

        int databaseSizeBeforeUpdate = tradeShopRepository.findAll().size();

        // Update the tradeShop
        TradeShop updatedTradeShop = tradeShopRepository.findById(tradeShop.getId()).get();
        // Disconnect from session so that the updates on updatedTradeShop are not directly saved in db
        em.detach(updatedTradeShop);
        updatedTradeShop
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

        restTradeShopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTradeShop.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTradeShop))
            )
            .andExpect(status().isOk());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeUpdate);
        TradeShop testTradeShop = tradeShopList.get(tradeShopList.size() - 1);
        assertThat(testTradeShop.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTradeShop.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testTradeShop.getCurrentPrice()).isEqualTo(UPDATED_CURRENT_PRICE);
        assertThat(testTradeShop.getWhiceLineFromAllCountLines()).isEqualTo(UPDATED_WHICE_LINE_FROM_ALL_COUNT_LINES);
        assertThat(testTradeShop.getTgUserIdWinner()).isEqualTo(UPDATED_TG_USER_ID_WINNER);
        assertThat(testTradeShop.getInWhatDateWillPostThisLinks()).isEqualTo(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS);
        assertThat(testTradeShop.getDateFinishTorgs()).isEqualTo(UPDATED_DATE_FINISH_TORGS);
        assertThat(testTradeShop.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testTradeShop.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTradeShop.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testTradeShop.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTradeShop.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testTradeShop.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingTradeShop() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopRepository.findAll().size();
        tradeShop.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTradeShopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tradeShop.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tradeShop))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTradeShop() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopRepository.findAll().size();
        tradeShop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeShopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tradeShop))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTradeShop() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopRepository.findAll().size();
        tradeShop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeShopMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tradeShop)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTradeShopWithPatch() throws Exception {
        // Initialize the database
        tradeShopRepository.saveAndFlush(tradeShop);

        int databaseSizeBeforeUpdate = tradeShopRepository.findAll().size();

        // Update the tradeShop using partial update
        TradeShop partialUpdatedTradeShop = new TradeShop();
        partialUpdatedTradeShop.setId(tradeShop.getId());

        partialUpdatedTradeShop
            .category(UPDATED_CATEGORY)
            .tgUserIdWinner(UPDATED_TG_USER_ID_WINNER)
            .inWhatDateWillPostThisLinks(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS)
            .dateFinishTorgs(UPDATED_DATE_FINISH_TORGS)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1);

        restTradeShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTradeShop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTradeShop))
            )
            .andExpect(status().isOk());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeUpdate);
        TradeShop testTradeShop = tradeShopList.get(tradeShopList.size() - 1);
        assertThat(testTradeShop.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTradeShop.getPriceDiapozon()).isEqualTo(DEFAULT_PRICE_DIAPOZON);
        assertThat(testTradeShop.getCurrentPrice()).isEqualTo(DEFAULT_CURRENT_PRICE);
        assertThat(testTradeShop.getWhiceLineFromAllCountLines()).isEqualTo(DEFAULT_WHICE_LINE_FROM_ALL_COUNT_LINES);
        assertThat(testTradeShop.getTgUserIdWinner()).isEqualTo(UPDATED_TG_USER_ID_WINNER);
        assertThat(testTradeShop.getInWhatDateWillPostThisLinks()).isEqualTo(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS);
        assertThat(testTradeShop.getDateFinishTorgs()).isEqualTo(UPDATED_DATE_FINISH_TORGS);
        assertThat(testTradeShop.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testTradeShop.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testTradeShop.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testTradeShop.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTradeShop.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testTradeShop.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateTradeShopWithPatch() throws Exception {
        // Initialize the database
        tradeShopRepository.saveAndFlush(tradeShop);

        int databaseSizeBeforeUpdate = tradeShopRepository.findAll().size();

        // Update the tradeShop using partial update
        TradeShop partialUpdatedTradeShop = new TradeShop();
        partialUpdatedTradeShop.setId(tradeShop.getId());

        partialUpdatedTradeShop
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

        restTradeShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTradeShop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTradeShop))
            )
            .andExpect(status().isOk());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeUpdate);
        TradeShop testTradeShop = tradeShopList.get(tradeShopList.size() - 1);
        assertThat(testTradeShop.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testTradeShop.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testTradeShop.getCurrentPrice()).isEqualTo(UPDATED_CURRENT_PRICE);
        assertThat(testTradeShop.getWhiceLineFromAllCountLines()).isEqualTo(UPDATED_WHICE_LINE_FROM_ALL_COUNT_LINES);
        assertThat(testTradeShop.getTgUserIdWinner()).isEqualTo(UPDATED_TG_USER_ID_WINNER);
        assertThat(testTradeShop.getInWhatDateWillPostThisLinks()).isEqualTo(UPDATED_IN_WHAT_DATE_WILL_POST_THIS_LINKS);
        assertThat(testTradeShop.getDateFinishTorgs()).isEqualTo(UPDATED_DATE_FINISH_TORGS);
        assertThat(testTradeShop.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testTradeShop.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTradeShop.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testTradeShop.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTradeShop.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testTradeShop.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingTradeShop() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopRepository.findAll().size();
        tradeShop.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTradeShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tradeShop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tradeShop))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTradeShop() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopRepository.findAll().size();
        tradeShop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeShopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tradeShop))
            )
            .andExpect(status().isBadRequest());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTradeShop() throws Exception {
        int databaseSizeBeforeUpdate = tradeShopRepository.findAll().size();
        tradeShop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTradeShopMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tradeShop))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TradeShop in the database
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTradeShop() throws Exception {
        // Initialize the database
        tradeShopRepository.saveAndFlush(tradeShop);

        int databaseSizeBeforeDelete = tradeShopRepository.findAll().size();

        // Delete the tradeShop
        restTradeShopMockMvc
            .perform(delete(ENTITY_API_URL_ID, tradeShop.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TradeShop> tradeShopList = tradeShopRepository.findAll();
        assertThat(tradeShopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
