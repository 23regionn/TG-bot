package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.LinksByCategoryInTopLog;
import com.mycompany.myapp.repository.LinksByCategoryInTopLogRepository;
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
 * Integration tests for the {@link LinksByCategoryInTopLogResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LinksByCategoryInTopLogResourceIT {

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE_DIAPOZON = 1D;
    private static final Double UPDATED_PRICE_DIAPOZON = 2D;

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final Long DEFAULT_CHANELL_ADMIN_ID = 1L;
    private static final Long UPDATED_CHANELL_ADMIN_ID = 2L;

    private static final ZonedDateTime DEFAULT_DATE_POST_LINK_START = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_POST_LINK_START = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_POST_LINK_END = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_POST_LINK_END = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_POSITION_BETWEEN_LINKS = 1L;
    private static final Long UPDATED_POSITION_BETWEEN_LINKS = 2L;

    private static final Boolean DEFAULT_SHOW_LINK = false;
    private static final Boolean UPDATED_SHOW_LINK = true;

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

    private static final String ENTITY_API_URL = "/api/links-by-category-in-top-logs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LinksByCategoryInTopLogRepository linksByCategoryInTopLogRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLinksByCategoryInTopLogMockMvc;

    private LinksByCategoryInTopLog linksByCategoryInTopLog;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LinksByCategoryInTopLog createEntity(EntityManager em) {
        LinksByCategoryInTopLog linksByCategoryInTopLog = new LinksByCategoryInTopLog()
            .category(DEFAULT_CATEGORY)
            .priceDiapozon(DEFAULT_PRICE_DIAPOZON)
            .link(DEFAULT_LINK)
            .chanellAdminId(DEFAULT_CHANELL_ADMIN_ID)
            .datePostLinkStart(DEFAULT_DATE_POST_LINK_START)
            .datePostLinkEnd(DEFAULT_DATE_POST_LINK_END)
            .positionBetweenLinks(DEFAULT_POSITION_BETWEEN_LINKS)
            .showLink(DEFAULT_SHOW_LINK)
            .isDelete(DEFAULT_IS_DELETE)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return linksByCategoryInTopLog;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LinksByCategoryInTopLog createUpdatedEntity(EntityManager em) {
        LinksByCategoryInTopLog linksByCategoryInTopLog = new LinksByCategoryInTopLog()
            .category(UPDATED_CATEGORY)
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .link(UPDATED_LINK)
            .chanellAdminId(UPDATED_CHANELL_ADMIN_ID)
            .datePostLinkStart(UPDATED_DATE_POST_LINK_START)
            .datePostLinkEnd(UPDATED_DATE_POST_LINK_END)
            .positionBetweenLinks(UPDATED_POSITION_BETWEEN_LINKS)
            .showLink(UPDATED_SHOW_LINK)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return linksByCategoryInTopLog;
    }

    @BeforeEach
    public void initTest() {
        linksByCategoryInTopLog = createEntity(em);
    }

    @Test
    @Transactional
    void createLinksByCategoryInTopLog() throws Exception {
        int databaseSizeBeforeCreate = linksByCategoryInTopLogRepository.findAll().size();
        // Create the LinksByCategoryInTopLog
        restLinksByCategoryInTopLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTopLog))
            )
            .andExpect(status().isCreated());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeCreate + 1);
        LinksByCategoryInTopLog testLinksByCategoryInTopLog = linksByCategoryInTopLogList.get(linksByCategoryInTopLogList.size() - 1);
        assertThat(testLinksByCategoryInTopLog.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testLinksByCategoryInTopLog.getPriceDiapozon()).isEqualTo(DEFAULT_PRICE_DIAPOZON);
        assertThat(testLinksByCategoryInTopLog.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testLinksByCategoryInTopLog.getChanellAdminId()).isEqualTo(DEFAULT_CHANELL_ADMIN_ID);
        assertThat(testLinksByCategoryInTopLog.getDatePostLinkStart()).isEqualTo(DEFAULT_DATE_POST_LINK_START);
        assertThat(testLinksByCategoryInTopLog.getDatePostLinkEnd()).isEqualTo(DEFAULT_DATE_POST_LINK_END);
        assertThat(testLinksByCategoryInTopLog.getPositionBetweenLinks()).isEqualTo(DEFAULT_POSITION_BETWEEN_LINKS);
        assertThat(testLinksByCategoryInTopLog.getShowLink()).isEqualTo(DEFAULT_SHOW_LINK);
        assertThat(testLinksByCategoryInTopLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testLinksByCategoryInTopLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testLinksByCategoryInTopLog.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testLinksByCategoryInTopLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testLinksByCategoryInTopLog.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testLinksByCategoryInTopLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createLinksByCategoryInTopLogWithExistingId() throws Exception {
        // Create the LinksByCategoryInTopLog with an existing ID
        linksByCategoryInTopLog.setId(1L);

        int databaseSizeBeforeCreate = linksByCategoryInTopLogRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLinksByCategoryInTopLogMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTopLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLinksByCategoryInTopLogs() throws Exception {
        // Initialize the database
        linksByCategoryInTopLogRepository.saveAndFlush(linksByCategoryInTopLog);

        // Get all the linksByCategoryInTopLogList
        restLinksByCategoryInTopLogMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(linksByCategoryInTopLog.getId().intValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].priceDiapozon").value(hasItem(DEFAULT_PRICE_DIAPOZON.doubleValue())))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].chanellAdminId").value(hasItem(DEFAULT_CHANELL_ADMIN_ID.intValue())))
            .andExpect(jsonPath("$.[*].datePostLinkStart").value(hasItem(sameInstant(DEFAULT_DATE_POST_LINK_START))))
            .andExpect(jsonPath("$.[*].datePostLinkEnd").value(hasItem(sameInstant(DEFAULT_DATE_POST_LINK_END))))
            .andExpect(jsonPath("$.[*].positionBetweenLinks").value(hasItem(DEFAULT_POSITION_BETWEEN_LINKS.intValue())))
            .andExpect(jsonPath("$.[*].showLink").value(hasItem(DEFAULT_SHOW_LINK.booleanValue())))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
    }

    @Test
    @Transactional
    void getLinksByCategoryInTopLog() throws Exception {
        // Initialize the database
        linksByCategoryInTopLogRepository.saveAndFlush(linksByCategoryInTopLog);

        // Get the linksByCategoryInTopLog
        restLinksByCategoryInTopLogMockMvc
            .perform(get(ENTITY_API_URL_ID, linksByCategoryInTopLog.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(linksByCategoryInTopLog.getId().intValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.priceDiapozon").value(DEFAULT_PRICE_DIAPOZON.doubleValue()))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
            .andExpect(jsonPath("$.chanellAdminId").value(DEFAULT_CHANELL_ADMIN_ID.intValue()))
            .andExpect(jsonPath("$.datePostLinkStart").value(sameInstant(DEFAULT_DATE_POST_LINK_START)))
            .andExpect(jsonPath("$.datePostLinkEnd").value(sameInstant(DEFAULT_DATE_POST_LINK_END)))
            .andExpect(jsonPath("$.positionBetweenLinks").value(DEFAULT_POSITION_BETWEEN_LINKS.intValue()))
            .andExpect(jsonPath("$.showLink").value(DEFAULT_SHOW_LINK.booleanValue()))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE.booleanValue()))
            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingLinksByCategoryInTopLog() throws Exception {
        // Get the linksByCategoryInTopLog
        restLinksByCategoryInTopLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLinksByCategoryInTopLog() throws Exception {
        // Initialize the database
        linksByCategoryInTopLogRepository.saveAndFlush(linksByCategoryInTopLog);

        int databaseSizeBeforeUpdate = linksByCategoryInTopLogRepository.findAll().size();

        // Update the linksByCategoryInTopLog
        LinksByCategoryInTopLog updatedLinksByCategoryInTopLog = linksByCategoryInTopLogRepository
            .findById(linksByCategoryInTopLog.getId())
            .get();
        // Disconnect from session so that the updates on updatedLinksByCategoryInTopLog are not directly saved in db
        em.detach(updatedLinksByCategoryInTopLog);
        updatedLinksByCategoryInTopLog
            .category(UPDATED_CATEGORY)
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .link(UPDATED_LINK)
            .chanellAdminId(UPDATED_CHANELL_ADMIN_ID)
            .datePostLinkStart(UPDATED_DATE_POST_LINK_START)
            .datePostLinkEnd(UPDATED_DATE_POST_LINK_END)
            .positionBetweenLinks(UPDATED_POSITION_BETWEEN_LINKS)
            .showLink(UPDATED_SHOW_LINK)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restLinksByCategoryInTopLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLinksByCategoryInTopLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLinksByCategoryInTopLog))
            )
            .andExpect(status().isOk());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeUpdate);
        LinksByCategoryInTopLog testLinksByCategoryInTopLog = linksByCategoryInTopLogList.get(linksByCategoryInTopLogList.size() - 1);
        assertThat(testLinksByCategoryInTopLog.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testLinksByCategoryInTopLog.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testLinksByCategoryInTopLog.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testLinksByCategoryInTopLog.getChanellAdminId()).isEqualTo(UPDATED_CHANELL_ADMIN_ID);
        assertThat(testLinksByCategoryInTopLog.getDatePostLinkStart()).isEqualTo(UPDATED_DATE_POST_LINK_START);
        assertThat(testLinksByCategoryInTopLog.getDatePostLinkEnd()).isEqualTo(UPDATED_DATE_POST_LINK_END);
        assertThat(testLinksByCategoryInTopLog.getPositionBetweenLinks()).isEqualTo(UPDATED_POSITION_BETWEEN_LINKS);
        assertThat(testLinksByCategoryInTopLog.getShowLink()).isEqualTo(UPDATED_SHOW_LINK);
        assertThat(testLinksByCategoryInTopLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testLinksByCategoryInTopLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testLinksByCategoryInTopLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testLinksByCategoryInTopLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testLinksByCategoryInTopLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testLinksByCategoryInTopLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingLinksByCategoryInTopLog() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopLogRepository.findAll().size();
        linksByCategoryInTopLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, linksByCategoryInTopLog.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTopLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLinksByCategoryInTopLog() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopLogRepository.findAll().size();
        linksByCategoryInTopLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopLogMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTopLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLinksByCategoryInTopLog() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopLogRepository.findAll().size();
        linksByCategoryInTopLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopLogMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTopLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLinksByCategoryInTopLogWithPatch() throws Exception {
        // Initialize the database
        linksByCategoryInTopLogRepository.saveAndFlush(linksByCategoryInTopLog);

        int databaseSizeBeforeUpdate = linksByCategoryInTopLogRepository.findAll().size();

        // Update the linksByCategoryInTopLog using partial update
        LinksByCategoryInTopLog partialUpdatedLinksByCategoryInTopLog = new LinksByCategoryInTopLog();
        partialUpdatedLinksByCategoryInTopLog.setId(linksByCategoryInTopLog.getId());

        partialUpdatedLinksByCategoryInTopLog
            .category(UPDATED_CATEGORY)
            .link(UPDATED_LINK)
            .chanellAdminId(UPDATED_CHANELL_ADMIN_ID)
            .isDelete(UPDATED_IS_DELETE)
            .date2(UPDATED_DATE_2)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restLinksByCategoryInTopLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLinksByCategoryInTopLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLinksByCategoryInTopLog))
            )
            .andExpect(status().isOk());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeUpdate);
        LinksByCategoryInTopLog testLinksByCategoryInTopLog = linksByCategoryInTopLogList.get(linksByCategoryInTopLogList.size() - 1);
        assertThat(testLinksByCategoryInTopLog.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testLinksByCategoryInTopLog.getPriceDiapozon()).isEqualTo(DEFAULT_PRICE_DIAPOZON);
        assertThat(testLinksByCategoryInTopLog.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testLinksByCategoryInTopLog.getChanellAdminId()).isEqualTo(UPDATED_CHANELL_ADMIN_ID);
        assertThat(testLinksByCategoryInTopLog.getDatePostLinkStart()).isEqualTo(DEFAULT_DATE_POST_LINK_START);
        assertThat(testLinksByCategoryInTopLog.getDatePostLinkEnd()).isEqualTo(DEFAULT_DATE_POST_LINK_END);
        assertThat(testLinksByCategoryInTopLog.getPositionBetweenLinks()).isEqualTo(DEFAULT_POSITION_BETWEEN_LINKS);
        assertThat(testLinksByCategoryInTopLog.getShowLink()).isEqualTo(DEFAULT_SHOW_LINK);
        assertThat(testLinksByCategoryInTopLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testLinksByCategoryInTopLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testLinksByCategoryInTopLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testLinksByCategoryInTopLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testLinksByCategoryInTopLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testLinksByCategoryInTopLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateLinksByCategoryInTopLogWithPatch() throws Exception {
        // Initialize the database
        linksByCategoryInTopLogRepository.saveAndFlush(linksByCategoryInTopLog);

        int databaseSizeBeforeUpdate = linksByCategoryInTopLogRepository.findAll().size();

        // Update the linksByCategoryInTopLog using partial update
        LinksByCategoryInTopLog partialUpdatedLinksByCategoryInTopLog = new LinksByCategoryInTopLog();
        partialUpdatedLinksByCategoryInTopLog.setId(linksByCategoryInTopLog.getId());

        partialUpdatedLinksByCategoryInTopLog
            .category(UPDATED_CATEGORY)
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .link(UPDATED_LINK)
            .chanellAdminId(UPDATED_CHANELL_ADMIN_ID)
            .datePostLinkStart(UPDATED_DATE_POST_LINK_START)
            .datePostLinkEnd(UPDATED_DATE_POST_LINK_END)
            .positionBetweenLinks(UPDATED_POSITION_BETWEEN_LINKS)
            .showLink(UPDATED_SHOW_LINK)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restLinksByCategoryInTopLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLinksByCategoryInTopLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLinksByCategoryInTopLog))
            )
            .andExpect(status().isOk());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeUpdate);
        LinksByCategoryInTopLog testLinksByCategoryInTopLog = linksByCategoryInTopLogList.get(linksByCategoryInTopLogList.size() - 1);
        assertThat(testLinksByCategoryInTopLog.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testLinksByCategoryInTopLog.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testLinksByCategoryInTopLog.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testLinksByCategoryInTopLog.getChanellAdminId()).isEqualTo(UPDATED_CHANELL_ADMIN_ID);
        assertThat(testLinksByCategoryInTopLog.getDatePostLinkStart()).isEqualTo(UPDATED_DATE_POST_LINK_START);
        assertThat(testLinksByCategoryInTopLog.getDatePostLinkEnd()).isEqualTo(UPDATED_DATE_POST_LINK_END);
        assertThat(testLinksByCategoryInTopLog.getPositionBetweenLinks()).isEqualTo(UPDATED_POSITION_BETWEEN_LINKS);
        assertThat(testLinksByCategoryInTopLog.getShowLink()).isEqualTo(UPDATED_SHOW_LINK);
        assertThat(testLinksByCategoryInTopLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testLinksByCategoryInTopLog.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testLinksByCategoryInTopLog.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testLinksByCategoryInTopLog.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testLinksByCategoryInTopLog.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testLinksByCategoryInTopLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingLinksByCategoryInTopLog() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopLogRepository.findAll().size();
        linksByCategoryInTopLog.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, linksByCategoryInTopLog.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTopLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLinksByCategoryInTopLog() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopLogRepository.findAll().size();
        linksByCategoryInTopLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopLogMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTopLog))
            )
            .andExpect(status().isBadRequest());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLinksByCategoryInTopLog() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopLogRepository.findAll().size();
        linksByCategoryInTopLog.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopLogMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTopLog))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LinksByCategoryInTopLog in the database
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLinksByCategoryInTopLog() throws Exception {
        // Initialize the database
        linksByCategoryInTopLogRepository.saveAndFlush(linksByCategoryInTopLog);

        int databaseSizeBeforeDelete = linksByCategoryInTopLogRepository.findAll().size();

        // Delete the linksByCategoryInTopLog
        restLinksByCategoryInTopLogMockMvc
            .perform(delete(ENTITY_API_URL_ID, linksByCategoryInTopLog.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LinksByCategoryInTopLog> linksByCategoryInTopLogList = linksByCategoryInTopLogRepository.findAll();
        assertThat(linksByCategoryInTopLogList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
