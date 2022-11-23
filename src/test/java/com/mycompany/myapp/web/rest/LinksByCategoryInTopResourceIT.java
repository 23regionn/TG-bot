package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.LinksByCategoryInTop;
import com.mycompany.myapp.repository.LinksByCategoryInTopRepository;
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
 * Integration tests for the {@link LinksByCategoryInTopResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LinksByCategoryInTopResourceIT {

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

    private static final String ENTITY_API_URL = "/api/links-by-category-in-tops";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LinksByCategoryInTopRepository linksByCategoryInTopRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLinksByCategoryInTopMockMvc;

    private LinksByCategoryInTop linksByCategoryInTop;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LinksByCategoryInTop createEntity(EntityManager em) {
        LinksByCategoryInTop linksByCategoryInTop = new LinksByCategoryInTop()
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
        return linksByCategoryInTop;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LinksByCategoryInTop createUpdatedEntity(EntityManager em) {
        LinksByCategoryInTop linksByCategoryInTop = new LinksByCategoryInTop()
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
        return linksByCategoryInTop;
    }

    @BeforeEach
    public void initTest() {
        linksByCategoryInTop = createEntity(em);
    }

    @Test
    @Transactional
    void createLinksByCategoryInTop() throws Exception {
        int databaseSizeBeforeCreate = linksByCategoryInTopRepository.findAll().size();
        // Create the LinksByCategoryInTop
        restLinksByCategoryInTopMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTop))
            )
            .andExpect(status().isCreated());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeCreate + 1);
        LinksByCategoryInTop testLinksByCategoryInTop = linksByCategoryInTopList.get(linksByCategoryInTopList.size() - 1);
        assertThat(testLinksByCategoryInTop.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testLinksByCategoryInTop.getPriceDiapozon()).isEqualTo(DEFAULT_PRICE_DIAPOZON);
        assertThat(testLinksByCategoryInTop.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testLinksByCategoryInTop.getChanellAdminId()).isEqualTo(DEFAULT_CHANELL_ADMIN_ID);
        assertThat(testLinksByCategoryInTop.getDatePostLinkStart()).isEqualTo(DEFAULT_DATE_POST_LINK_START);
        assertThat(testLinksByCategoryInTop.getDatePostLinkEnd()).isEqualTo(DEFAULT_DATE_POST_LINK_END);
        assertThat(testLinksByCategoryInTop.getPositionBetweenLinks()).isEqualTo(DEFAULT_POSITION_BETWEEN_LINKS);
        assertThat(testLinksByCategoryInTop.getShowLink()).isEqualTo(DEFAULT_SHOW_LINK);
        assertThat(testLinksByCategoryInTop.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testLinksByCategoryInTop.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testLinksByCategoryInTop.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testLinksByCategoryInTop.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testLinksByCategoryInTop.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testLinksByCategoryInTop.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createLinksByCategoryInTopWithExistingId() throws Exception {
        // Create the LinksByCategoryInTop with an existing ID
        linksByCategoryInTop.setId(1L);

        int databaseSizeBeforeCreate = linksByCategoryInTopRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLinksByCategoryInTopMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTop))
            )
            .andExpect(status().isBadRequest());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLinksByCategoryInTops() throws Exception {
        // Initialize the database
        linksByCategoryInTopRepository.saveAndFlush(linksByCategoryInTop);

        // Get all the linksByCategoryInTopList
        restLinksByCategoryInTopMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(linksByCategoryInTop.getId().intValue())))
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
    void getLinksByCategoryInTop() throws Exception {
        // Initialize the database
        linksByCategoryInTopRepository.saveAndFlush(linksByCategoryInTop);

        // Get the linksByCategoryInTop
        restLinksByCategoryInTopMockMvc
            .perform(get(ENTITY_API_URL_ID, linksByCategoryInTop.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(linksByCategoryInTop.getId().intValue()))
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
    void getNonExistingLinksByCategoryInTop() throws Exception {
        // Get the linksByCategoryInTop
        restLinksByCategoryInTopMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLinksByCategoryInTop() throws Exception {
        // Initialize the database
        linksByCategoryInTopRepository.saveAndFlush(linksByCategoryInTop);

        int databaseSizeBeforeUpdate = linksByCategoryInTopRepository.findAll().size();

        // Update the linksByCategoryInTop
        LinksByCategoryInTop updatedLinksByCategoryInTop = linksByCategoryInTopRepository.findById(linksByCategoryInTop.getId()).get();
        // Disconnect from session so that the updates on updatedLinksByCategoryInTop are not directly saved in db
        em.detach(updatedLinksByCategoryInTop);
        updatedLinksByCategoryInTop
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

        restLinksByCategoryInTopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLinksByCategoryInTop.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedLinksByCategoryInTop))
            )
            .andExpect(status().isOk());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeUpdate);
        LinksByCategoryInTop testLinksByCategoryInTop = linksByCategoryInTopList.get(linksByCategoryInTopList.size() - 1);
        assertThat(testLinksByCategoryInTop.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testLinksByCategoryInTop.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testLinksByCategoryInTop.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testLinksByCategoryInTop.getChanellAdminId()).isEqualTo(UPDATED_CHANELL_ADMIN_ID);
        assertThat(testLinksByCategoryInTop.getDatePostLinkStart()).isEqualTo(UPDATED_DATE_POST_LINK_START);
        assertThat(testLinksByCategoryInTop.getDatePostLinkEnd()).isEqualTo(UPDATED_DATE_POST_LINK_END);
        assertThat(testLinksByCategoryInTop.getPositionBetweenLinks()).isEqualTo(UPDATED_POSITION_BETWEEN_LINKS);
        assertThat(testLinksByCategoryInTop.getShowLink()).isEqualTo(UPDATED_SHOW_LINK);
        assertThat(testLinksByCategoryInTop.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testLinksByCategoryInTop.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testLinksByCategoryInTop.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testLinksByCategoryInTop.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testLinksByCategoryInTop.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testLinksByCategoryInTop.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingLinksByCategoryInTop() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopRepository.findAll().size();
        linksByCategoryInTop.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, linksByCategoryInTop.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTop))
            )
            .andExpect(status().isBadRequest());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLinksByCategoryInTop() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopRepository.findAll().size();
        linksByCategoryInTop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTop))
            )
            .andExpect(status().isBadRequest());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLinksByCategoryInTop() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopRepository.findAll().size();
        linksByCategoryInTop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTop))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLinksByCategoryInTopWithPatch() throws Exception {
        // Initialize the database
        linksByCategoryInTopRepository.saveAndFlush(linksByCategoryInTop);

        int databaseSizeBeforeUpdate = linksByCategoryInTopRepository.findAll().size();

        // Update the linksByCategoryInTop using partial update
        LinksByCategoryInTop partialUpdatedLinksByCategoryInTop = new LinksByCategoryInTop();
        partialUpdatedLinksByCategoryInTop.setId(linksByCategoryInTop.getId());

        partialUpdatedLinksByCategoryInTop
            .priceDiapozon(UPDATED_PRICE_DIAPOZON)
            .link(UPDATED_LINK)
            .datePostLinkEnd(UPDATED_DATE_POST_LINK_END)
            .showLink(UPDATED_SHOW_LINK)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1);

        restLinksByCategoryInTopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLinksByCategoryInTop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLinksByCategoryInTop))
            )
            .andExpect(status().isOk());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeUpdate);
        LinksByCategoryInTop testLinksByCategoryInTop = linksByCategoryInTopList.get(linksByCategoryInTopList.size() - 1);
        assertThat(testLinksByCategoryInTop.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testLinksByCategoryInTop.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testLinksByCategoryInTop.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testLinksByCategoryInTop.getChanellAdminId()).isEqualTo(DEFAULT_CHANELL_ADMIN_ID);
        assertThat(testLinksByCategoryInTop.getDatePostLinkStart()).isEqualTo(DEFAULT_DATE_POST_LINK_START);
        assertThat(testLinksByCategoryInTop.getDatePostLinkEnd()).isEqualTo(UPDATED_DATE_POST_LINK_END);
        assertThat(testLinksByCategoryInTop.getPositionBetweenLinks()).isEqualTo(DEFAULT_POSITION_BETWEEN_LINKS);
        assertThat(testLinksByCategoryInTop.getShowLink()).isEqualTo(UPDATED_SHOW_LINK);
        assertThat(testLinksByCategoryInTop.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testLinksByCategoryInTop.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testLinksByCategoryInTop.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testLinksByCategoryInTop.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testLinksByCategoryInTop.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testLinksByCategoryInTop.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateLinksByCategoryInTopWithPatch() throws Exception {
        // Initialize the database
        linksByCategoryInTopRepository.saveAndFlush(linksByCategoryInTop);

        int databaseSizeBeforeUpdate = linksByCategoryInTopRepository.findAll().size();

        // Update the linksByCategoryInTop using partial update
        LinksByCategoryInTop partialUpdatedLinksByCategoryInTop = new LinksByCategoryInTop();
        partialUpdatedLinksByCategoryInTop.setId(linksByCategoryInTop.getId());

        partialUpdatedLinksByCategoryInTop
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

        restLinksByCategoryInTopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLinksByCategoryInTop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLinksByCategoryInTop))
            )
            .andExpect(status().isOk());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeUpdate);
        LinksByCategoryInTop testLinksByCategoryInTop = linksByCategoryInTopList.get(linksByCategoryInTopList.size() - 1);
        assertThat(testLinksByCategoryInTop.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testLinksByCategoryInTop.getPriceDiapozon()).isEqualTo(UPDATED_PRICE_DIAPOZON);
        assertThat(testLinksByCategoryInTop.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testLinksByCategoryInTop.getChanellAdminId()).isEqualTo(UPDATED_CHANELL_ADMIN_ID);
        assertThat(testLinksByCategoryInTop.getDatePostLinkStart()).isEqualTo(UPDATED_DATE_POST_LINK_START);
        assertThat(testLinksByCategoryInTop.getDatePostLinkEnd()).isEqualTo(UPDATED_DATE_POST_LINK_END);
        assertThat(testLinksByCategoryInTop.getPositionBetweenLinks()).isEqualTo(UPDATED_POSITION_BETWEEN_LINKS);
        assertThat(testLinksByCategoryInTop.getShowLink()).isEqualTo(UPDATED_SHOW_LINK);
        assertThat(testLinksByCategoryInTop.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testLinksByCategoryInTop.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testLinksByCategoryInTop.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testLinksByCategoryInTop.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testLinksByCategoryInTop.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testLinksByCategoryInTop.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingLinksByCategoryInTop() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopRepository.findAll().size();
        linksByCategoryInTop.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, linksByCategoryInTop.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTop))
            )
            .andExpect(status().isBadRequest());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLinksByCategoryInTop() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopRepository.findAll().size();
        linksByCategoryInTop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTop))
            )
            .andExpect(status().isBadRequest());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLinksByCategoryInTop() throws Exception {
        int databaseSizeBeforeUpdate = linksByCategoryInTopRepository.findAll().size();
        linksByCategoryInTop.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLinksByCategoryInTopMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(linksByCategoryInTop))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LinksByCategoryInTop in the database
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLinksByCategoryInTop() throws Exception {
        // Initialize the database
        linksByCategoryInTopRepository.saveAndFlush(linksByCategoryInTop);

        int databaseSizeBeforeDelete = linksByCategoryInTopRepository.findAll().size();

        // Delete the linksByCategoryInTop
        restLinksByCategoryInTopMockMvc
            .perform(delete(ENTITY_API_URL_ID, linksByCategoryInTop.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LinksByCategoryInTop> linksByCategoryInTopList = linksByCategoryInTopRepository.findAll();
        assertThat(linksByCategoryInTopList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
