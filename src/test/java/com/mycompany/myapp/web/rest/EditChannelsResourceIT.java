package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.EditChannels;
import com.mycompany.myapp.repository.EditChannelsRepository;
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
 * Integration tests for the {@link EditChannelsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EditChannelsResourceIT {

    private static final Long DEFAULT_ID_MESSAGE = 1L;
    private static final Long UPDATED_ID_MESSAGE = 2L;

    private static final Long DEFAULT_ID_CHANNEL = 1L;
    private static final Long UPDATED_ID_CHANNEL = 2L;

    private static final ZonedDateTime DEFAULT_DATE_CREATE_MESSAGE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CREATE_MESSAGE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LAST_NAME_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_NEW_NAME_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_NEW_NAME_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_IS_APROVE_CHANGE = "AAAAAAAAAA";
    private static final String UPDATED_IS_APROVE_CHANGE = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_LINK_TO_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_LAST_LINK_TO_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_NEWLAST_LINK_TO_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_NEWLAST_LINK_TO_CHANNEL = "BBBBBBBBBB";

    private static final Double DEFAULT_LAST_PRICE_CHANNEL = 1D;
    private static final Double UPDATED_LAST_PRICE_CHANNEL = 2D;

    private static final Double DEFAULT_NEW_PRICE_CHANNEL = 1D;
    private static final Double UPDATED_NEW_PRICE_CHANNEL = 2D;

    private static final String DEFAULT_ADD_DESCRIPTION_ABOUT_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_ADD_DESCRIPTION_ABOUT_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_DESCRIPTION_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_DESCRIPTION_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_ADD_REGION_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_ADD_REGION_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_EDIT_REGION_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_EDIT_REGION_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_ADD_CITY_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_ADD_CITY_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_EDIT_CITY_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_EDIT_CITY_CHANNEL = "BBBBBBBBBB";

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_APPROVED_CHANHES = false;
    private static final Boolean UPDATED_IS_APPROVED_CHANHES = true;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_FIELD_3 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/edit-channels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EditChannelsRepository editChannelsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEditChannelsMockMvc;

    private EditChannels editChannels;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EditChannels createEntity(EntityManager em) {
        EditChannels editChannels = new EditChannels()
            .idMessage(DEFAULT_ID_MESSAGE)
            .idChannel(DEFAULT_ID_CHANNEL)
            .dateCreateMessage(DEFAULT_DATE_CREATE_MESSAGE)
            .lastNameChannel(DEFAULT_LAST_NAME_CHANNEL)
            .newNameChannel(DEFAULT_NEW_NAME_CHANNEL)
            .isAproveChange(DEFAULT_IS_APROVE_CHANGE)
            .lastLinkToChannel(DEFAULT_LAST_LINK_TO_CHANNEL)
            .newlastLinkToChannel(DEFAULT_NEWLAST_LINK_TO_CHANNEL)
            .lastPriceChannel(DEFAULT_LAST_PRICE_CHANNEL)
            .newPriceChannel(DEFAULT_NEW_PRICE_CHANNEL)
            .addDescriptionAboutChannel(DEFAULT_ADD_DESCRIPTION_ABOUT_CHANNEL)
            .currentDescriptionChannel(DEFAULT_CURRENT_DESCRIPTION_CHANNEL)
            .addRegionChannel(DEFAULT_ADD_REGION_CHANNEL)
            .editRegionChannel(DEFAULT_EDIT_REGION_CHANNEL)
            .addCityChannel(DEFAULT_ADD_CITY_CHANNEL)
            .editCityChannel(DEFAULT_EDIT_CITY_CHANNEL)
            .userId(DEFAULT_USER_ID)
            .userName(DEFAULT_USER_NAME)
            .isApprovedChanhes(DEFAULT_IS_APPROVED_CHANHES)
            .comment(DEFAULT_COMMENT)
            .status(DEFAULT_STATUS)
            .serviceField1(DEFAULT_SERVICE_FIELD_1)
            .serviceField2(DEFAULT_SERVICE_FIELD_2)
            .serviceField3(DEFAULT_SERVICE_FIELD_3);
        return editChannels;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EditChannels createUpdatedEntity(EntityManager em) {
        EditChannels editChannels = new EditChannels()
            .idMessage(UPDATED_ID_MESSAGE)
            .idChannel(UPDATED_ID_CHANNEL)
            .dateCreateMessage(UPDATED_DATE_CREATE_MESSAGE)
            .lastNameChannel(UPDATED_LAST_NAME_CHANNEL)
            .newNameChannel(UPDATED_NEW_NAME_CHANNEL)
            .isAproveChange(UPDATED_IS_APROVE_CHANGE)
            .lastLinkToChannel(UPDATED_LAST_LINK_TO_CHANNEL)
            .newlastLinkToChannel(UPDATED_NEWLAST_LINK_TO_CHANNEL)
            .lastPriceChannel(UPDATED_LAST_PRICE_CHANNEL)
            .newPriceChannel(UPDATED_NEW_PRICE_CHANNEL)
            .addDescriptionAboutChannel(UPDATED_ADD_DESCRIPTION_ABOUT_CHANNEL)
            .currentDescriptionChannel(UPDATED_CURRENT_DESCRIPTION_CHANNEL)
            .addRegionChannel(UPDATED_ADD_REGION_CHANNEL)
            .editRegionChannel(UPDATED_EDIT_REGION_CHANNEL)
            .addCityChannel(UPDATED_ADD_CITY_CHANNEL)
            .editCityChannel(UPDATED_EDIT_CITY_CHANNEL)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .isApprovedChanhes(UPDATED_IS_APPROVED_CHANHES)
            .comment(UPDATED_COMMENT)
            .status(UPDATED_STATUS)
            .serviceField1(UPDATED_SERVICE_FIELD_1)
            .serviceField2(UPDATED_SERVICE_FIELD_2)
            .serviceField3(UPDATED_SERVICE_FIELD_3);
        return editChannels;
    }

    @BeforeEach
    public void initTest() {
        editChannels = createEntity(em);
    }

    @Test
    @Transactional
    void createEditChannels() throws Exception {
        int databaseSizeBeforeCreate = editChannelsRepository.findAll().size();
        // Create the EditChannels
        restEditChannelsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(editChannels)))
            .andExpect(status().isCreated());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeCreate + 1);
        EditChannels testEditChannels = editChannelsList.get(editChannelsList.size() - 1);
        assertThat(testEditChannels.getIdMessage()).isEqualTo(DEFAULT_ID_MESSAGE);
        assertThat(testEditChannels.getIdChannel()).isEqualTo(DEFAULT_ID_CHANNEL);
        assertThat(testEditChannels.getDateCreateMessage()).isEqualTo(DEFAULT_DATE_CREATE_MESSAGE);
        assertThat(testEditChannels.getLastNameChannel()).isEqualTo(DEFAULT_LAST_NAME_CHANNEL);
        assertThat(testEditChannels.getNewNameChannel()).isEqualTo(DEFAULT_NEW_NAME_CHANNEL);
        assertThat(testEditChannels.getIsAproveChange()).isEqualTo(DEFAULT_IS_APROVE_CHANGE);
        assertThat(testEditChannels.getLastLinkToChannel()).isEqualTo(DEFAULT_LAST_LINK_TO_CHANNEL);
        assertThat(testEditChannels.getNewlastLinkToChannel()).isEqualTo(DEFAULT_NEWLAST_LINK_TO_CHANNEL);
        assertThat(testEditChannels.getLastPriceChannel()).isEqualTo(DEFAULT_LAST_PRICE_CHANNEL);
        assertThat(testEditChannels.getNewPriceChannel()).isEqualTo(DEFAULT_NEW_PRICE_CHANNEL);
        assertThat(testEditChannels.getAddDescriptionAboutChannel()).isEqualTo(DEFAULT_ADD_DESCRIPTION_ABOUT_CHANNEL);
        assertThat(testEditChannels.getCurrentDescriptionChannel()).isEqualTo(DEFAULT_CURRENT_DESCRIPTION_CHANNEL);
        assertThat(testEditChannels.getAddRegionChannel()).isEqualTo(DEFAULT_ADD_REGION_CHANNEL);
        assertThat(testEditChannels.getEditRegionChannel()).isEqualTo(DEFAULT_EDIT_REGION_CHANNEL);
        assertThat(testEditChannels.getAddCityChannel()).isEqualTo(DEFAULT_ADD_CITY_CHANNEL);
        assertThat(testEditChannels.getEditCityChannel()).isEqualTo(DEFAULT_EDIT_CITY_CHANNEL);
        assertThat(testEditChannels.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testEditChannels.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testEditChannels.getIsApprovedChanhes()).isEqualTo(DEFAULT_IS_APPROVED_CHANHES);
        assertThat(testEditChannels.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testEditChannels.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testEditChannels.getServiceField1()).isEqualTo(DEFAULT_SERVICE_FIELD_1);
        assertThat(testEditChannels.getServiceField2()).isEqualTo(DEFAULT_SERVICE_FIELD_2);
        assertThat(testEditChannels.getServiceField3()).isEqualTo(DEFAULT_SERVICE_FIELD_3);
    }

    @Test
    @Transactional
    void createEditChannelsWithExistingId() throws Exception {
        // Create the EditChannels with an existing ID
        editChannels.setId(1L);

        int databaseSizeBeforeCreate = editChannelsRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEditChannelsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(editChannels)))
            .andExpect(status().isBadRequest());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEditChannels() throws Exception {
        // Initialize the database
        editChannelsRepository.saveAndFlush(editChannels);

        // Get all the editChannelsList
        restEditChannelsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(editChannels.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMessage").value(hasItem(DEFAULT_ID_MESSAGE.intValue())))
            .andExpect(jsonPath("$.[*].idChannel").value(hasItem(DEFAULT_ID_CHANNEL.intValue())))
            .andExpect(jsonPath("$.[*].dateCreateMessage").value(hasItem(sameInstant(DEFAULT_DATE_CREATE_MESSAGE))))
            .andExpect(jsonPath("$.[*].lastNameChannel").value(hasItem(DEFAULT_LAST_NAME_CHANNEL)))
            .andExpect(jsonPath("$.[*].newNameChannel").value(hasItem(DEFAULT_NEW_NAME_CHANNEL)))
            .andExpect(jsonPath("$.[*].isAproveChange").value(hasItem(DEFAULT_IS_APROVE_CHANGE)))
            .andExpect(jsonPath("$.[*].lastLinkToChannel").value(hasItem(DEFAULT_LAST_LINK_TO_CHANNEL)))
            .andExpect(jsonPath("$.[*].newlastLinkToChannel").value(hasItem(DEFAULT_NEWLAST_LINK_TO_CHANNEL)))
            .andExpect(jsonPath("$.[*].lastPriceChannel").value(hasItem(DEFAULT_LAST_PRICE_CHANNEL.doubleValue())))
            .andExpect(jsonPath("$.[*].newPriceChannel").value(hasItem(DEFAULT_NEW_PRICE_CHANNEL.doubleValue())))
            .andExpect(jsonPath("$.[*].addDescriptionAboutChannel").value(hasItem(DEFAULT_ADD_DESCRIPTION_ABOUT_CHANNEL)))
            .andExpect(jsonPath("$.[*].currentDescriptionChannel").value(hasItem(DEFAULT_CURRENT_DESCRIPTION_CHANNEL)))
            .andExpect(jsonPath("$.[*].addRegionChannel").value(hasItem(DEFAULT_ADD_REGION_CHANNEL)))
            .andExpect(jsonPath("$.[*].editRegionChannel").value(hasItem(DEFAULT_EDIT_REGION_CHANNEL)))
            .andExpect(jsonPath("$.[*].addCityChannel").value(hasItem(DEFAULT_ADD_CITY_CHANNEL)))
            .andExpect(jsonPath("$.[*].editCityChannel").value(hasItem(DEFAULT_EDIT_CITY_CHANNEL)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].isApprovedChanhes").value(hasItem(DEFAULT_IS_APPROVED_CHANHES.booleanValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].serviceField1").value(hasItem(DEFAULT_SERVICE_FIELD_1)))
            .andExpect(jsonPath("$.[*].serviceField2").value(hasItem(DEFAULT_SERVICE_FIELD_2)))
            .andExpect(jsonPath("$.[*].serviceField3").value(hasItem(DEFAULT_SERVICE_FIELD_3)));
    }

    @Test
    @Transactional
    void getEditChannels() throws Exception {
        // Initialize the database
        editChannelsRepository.saveAndFlush(editChannels);

        // Get the editChannels
        restEditChannelsMockMvc
            .perform(get(ENTITY_API_URL_ID, editChannels.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(editChannels.getId().intValue()))
            .andExpect(jsonPath("$.idMessage").value(DEFAULT_ID_MESSAGE.intValue()))
            .andExpect(jsonPath("$.idChannel").value(DEFAULT_ID_CHANNEL.intValue()))
            .andExpect(jsonPath("$.dateCreateMessage").value(sameInstant(DEFAULT_DATE_CREATE_MESSAGE)))
            .andExpect(jsonPath("$.lastNameChannel").value(DEFAULT_LAST_NAME_CHANNEL))
            .andExpect(jsonPath("$.newNameChannel").value(DEFAULT_NEW_NAME_CHANNEL))
            .andExpect(jsonPath("$.isAproveChange").value(DEFAULT_IS_APROVE_CHANGE))
            .andExpect(jsonPath("$.lastLinkToChannel").value(DEFAULT_LAST_LINK_TO_CHANNEL))
            .andExpect(jsonPath("$.newlastLinkToChannel").value(DEFAULT_NEWLAST_LINK_TO_CHANNEL))
            .andExpect(jsonPath("$.lastPriceChannel").value(DEFAULT_LAST_PRICE_CHANNEL.doubleValue()))
            .andExpect(jsonPath("$.newPriceChannel").value(DEFAULT_NEW_PRICE_CHANNEL.doubleValue()))
            .andExpect(jsonPath("$.addDescriptionAboutChannel").value(DEFAULT_ADD_DESCRIPTION_ABOUT_CHANNEL))
            .andExpect(jsonPath("$.currentDescriptionChannel").value(DEFAULT_CURRENT_DESCRIPTION_CHANNEL))
            .andExpect(jsonPath("$.addRegionChannel").value(DEFAULT_ADD_REGION_CHANNEL))
            .andExpect(jsonPath("$.editRegionChannel").value(DEFAULT_EDIT_REGION_CHANNEL))
            .andExpect(jsonPath("$.addCityChannel").value(DEFAULT_ADD_CITY_CHANNEL))
            .andExpect(jsonPath("$.editCityChannel").value(DEFAULT_EDIT_CITY_CHANNEL))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.isApprovedChanhes").value(DEFAULT_IS_APPROVED_CHANHES.booleanValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.serviceField1").value(DEFAULT_SERVICE_FIELD_1))
            .andExpect(jsonPath("$.serviceField2").value(DEFAULT_SERVICE_FIELD_2))
            .andExpect(jsonPath("$.serviceField3").value(DEFAULT_SERVICE_FIELD_3));
    }

    @Test
    @Transactional
    void getNonExistingEditChannels() throws Exception {
        // Get the editChannels
        restEditChannelsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewEditChannels() throws Exception {
        // Initialize the database
        editChannelsRepository.saveAndFlush(editChannels);

        int databaseSizeBeforeUpdate = editChannelsRepository.findAll().size();

        // Update the editChannels
        EditChannels updatedEditChannels = editChannelsRepository.findById(editChannels.getId()).get();
        // Disconnect from session so that the updates on updatedEditChannels are not directly saved in db
        em.detach(updatedEditChannels);
        updatedEditChannels
            .idMessage(UPDATED_ID_MESSAGE)
            .idChannel(UPDATED_ID_CHANNEL)
            .dateCreateMessage(UPDATED_DATE_CREATE_MESSAGE)
            .lastNameChannel(UPDATED_LAST_NAME_CHANNEL)
            .newNameChannel(UPDATED_NEW_NAME_CHANNEL)
            .isAproveChange(UPDATED_IS_APROVE_CHANGE)
            .lastLinkToChannel(UPDATED_LAST_LINK_TO_CHANNEL)
            .newlastLinkToChannel(UPDATED_NEWLAST_LINK_TO_CHANNEL)
            .lastPriceChannel(UPDATED_LAST_PRICE_CHANNEL)
            .newPriceChannel(UPDATED_NEW_PRICE_CHANNEL)
            .addDescriptionAboutChannel(UPDATED_ADD_DESCRIPTION_ABOUT_CHANNEL)
            .currentDescriptionChannel(UPDATED_CURRENT_DESCRIPTION_CHANNEL)
            .addRegionChannel(UPDATED_ADD_REGION_CHANNEL)
            .editRegionChannel(UPDATED_EDIT_REGION_CHANNEL)
            .addCityChannel(UPDATED_ADD_CITY_CHANNEL)
            .editCityChannel(UPDATED_EDIT_CITY_CHANNEL)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .isApprovedChanhes(UPDATED_IS_APPROVED_CHANHES)
            .comment(UPDATED_COMMENT)
            .status(UPDATED_STATUS)
            .serviceField1(UPDATED_SERVICE_FIELD_1)
            .serviceField2(UPDATED_SERVICE_FIELD_2)
            .serviceField3(UPDATED_SERVICE_FIELD_3);

        restEditChannelsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEditChannels.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedEditChannels))
            )
            .andExpect(status().isOk());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeUpdate);
        EditChannels testEditChannels = editChannelsList.get(editChannelsList.size() - 1);
        assertThat(testEditChannels.getIdMessage()).isEqualTo(UPDATED_ID_MESSAGE);
        assertThat(testEditChannels.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testEditChannels.getDateCreateMessage()).isEqualTo(UPDATED_DATE_CREATE_MESSAGE);
        assertThat(testEditChannels.getLastNameChannel()).isEqualTo(UPDATED_LAST_NAME_CHANNEL);
        assertThat(testEditChannels.getNewNameChannel()).isEqualTo(UPDATED_NEW_NAME_CHANNEL);
        assertThat(testEditChannels.getIsAproveChange()).isEqualTo(UPDATED_IS_APROVE_CHANGE);
        assertThat(testEditChannels.getLastLinkToChannel()).isEqualTo(UPDATED_LAST_LINK_TO_CHANNEL);
        assertThat(testEditChannels.getNewlastLinkToChannel()).isEqualTo(UPDATED_NEWLAST_LINK_TO_CHANNEL);
        assertThat(testEditChannels.getLastPriceChannel()).isEqualTo(UPDATED_LAST_PRICE_CHANNEL);
        assertThat(testEditChannels.getNewPriceChannel()).isEqualTo(UPDATED_NEW_PRICE_CHANNEL);
        assertThat(testEditChannels.getAddDescriptionAboutChannel()).isEqualTo(UPDATED_ADD_DESCRIPTION_ABOUT_CHANNEL);
        assertThat(testEditChannels.getCurrentDescriptionChannel()).isEqualTo(UPDATED_CURRENT_DESCRIPTION_CHANNEL);
        assertThat(testEditChannels.getAddRegionChannel()).isEqualTo(UPDATED_ADD_REGION_CHANNEL);
        assertThat(testEditChannels.getEditRegionChannel()).isEqualTo(UPDATED_EDIT_REGION_CHANNEL);
        assertThat(testEditChannels.getAddCityChannel()).isEqualTo(UPDATED_ADD_CITY_CHANNEL);
        assertThat(testEditChannels.getEditCityChannel()).isEqualTo(UPDATED_EDIT_CITY_CHANNEL);
        assertThat(testEditChannels.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testEditChannels.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testEditChannels.getIsApprovedChanhes()).isEqualTo(UPDATED_IS_APPROVED_CHANHES);
        assertThat(testEditChannels.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testEditChannels.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEditChannels.getServiceField1()).isEqualTo(UPDATED_SERVICE_FIELD_1);
        assertThat(testEditChannels.getServiceField2()).isEqualTo(UPDATED_SERVICE_FIELD_2);
        assertThat(testEditChannels.getServiceField3()).isEqualTo(UPDATED_SERVICE_FIELD_3);
    }

    @Test
    @Transactional
    void putNonExistingEditChannels() throws Exception {
        int databaseSizeBeforeUpdate = editChannelsRepository.findAll().size();
        editChannels.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEditChannelsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, editChannels.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(editChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEditChannels() throws Exception {
        int databaseSizeBeforeUpdate = editChannelsRepository.findAll().size();
        editChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEditChannelsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(editChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEditChannels() throws Exception {
        int databaseSizeBeforeUpdate = editChannelsRepository.findAll().size();
        editChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEditChannelsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(editChannels)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEditChannelsWithPatch() throws Exception {
        // Initialize the database
        editChannelsRepository.saveAndFlush(editChannels);

        int databaseSizeBeforeUpdate = editChannelsRepository.findAll().size();

        // Update the editChannels using partial update
        EditChannels partialUpdatedEditChannels = new EditChannels();
        partialUpdatedEditChannels.setId(editChannels.getId());

        partialUpdatedEditChannels
            .idMessage(UPDATED_ID_MESSAGE)
            .dateCreateMessage(UPDATED_DATE_CREATE_MESSAGE)
            .newNameChannel(UPDATED_NEW_NAME_CHANNEL)
            .lastLinkToChannel(UPDATED_LAST_LINK_TO_CHANNEL)
            .lastPriceChannel(UPDATED_LAST_PRICE_CHANNEL)
            .addDescriptionAboutChannel(UPDATED_ADD_DESCRIPTION_ABOUT_CHANNEL)
            .status(UPDATED_STATUS)
            .serviceField1(UPDATED_SERVICE_FIELD_1);

        restEditChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEditChannels.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEditChannels))
            )
            .andExpect(status().isOk());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeUpdate);
        EditChannels testEditChannels = editChannelsList.get(editChannelsList.size() - 1);
        assertThat(testEditChannels.getIdMessage()).isEqualTo(UPDATED_ID_MESSAGE);
        assertThat(testEditChannels.getIdChannel()).isEqualTo(DEFAULT_ID_CHANNEL);
        assertThat(testEditChannels.getDateCreateMessage()).isEqualTo(UPDATED_DATE_CREATE_MESSAGE);
        assertThat(testEditChannels.getLastNameChannel()).isEqualTo(DEFAULT_LAST_NAME_CHANNEL);
        assertThat(testEditChannels.getNewNameChannel()).isEqualTo(UPDATED_NEW_NAME_CHANNEL);
        assertThat(testEditChannels.getIsAproveChange()).isEqualTo(DEFAULT_IS_APROVE_CHANGE);
        assertThat(testEditChannels.getLastLinkToChannel()).isEqualTo(UPDATED_LAST_LINK_TO_CHANNEL);
        assertThat(testEditChannels.getNewlastLinkToChannel()).isEqualTo(DEFAULT_NEWLAST_LINK_TO_CHANNEL);
        assertThat(testEditChannels.getLastPriceChannel()).isEqualTo(UPDATED_LAST_PRICE_CHANNEL);
        assertThat(testEditChannels.getNewPriceChannel()).isEqualTo(DEFAULT_NEW_PRICE_CHANNEL);
        assertThat(testEditChannels.getAddDescriptionAboutChannel()).isEqualTo(UPDATED_ADD_DESCRIPTION_ABOUT_CHANNEL);
        assertThat(testEditChannels.getCurrentDescriptionChannel()).isEqualTo(DEFAULT_CURRENT_DESCRIPTION_CHANNEL);
        assertThat(testEditChannels.getAddRegionChannel()).isEqualTo(DEFAULT_ADD_REGION_CHANNEL);
        assertThat(testEditChannels.getEditRegionChannel()).isEqualTo(DEFAULT_EDIT_REGION_CHANNEL);
        assertThat(testEditChannels.getAddCityChannel()).isEqualTo(DEFAULT_ADD_CITY_CHANNEL);
        assertThat(testEditChannels.getEditCityChannel()).isEqualTo(DEFAULT_EDIT_CITY_CHANNEL);
        assertThat(testEditChannels.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testEditChannels.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testEditChannels.getIsApprovedChanhes()).isEqualTo(DEFAULT_IS_APPROVED_CHANHES);
        assertThat(testEditChannels.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testEditChannels.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEditChannels.getServiceField1()).isEqualTo(UPDATED_SERVICE_FIELD_1);
        assertThat(testEditChannels.getServiceField2()).isEqualTo(DEFAULT_SERVICE_FIELD_2);
        assertThat(testEditChannels.getServiceField3()).isEqualTo(DEFAULT_SERVICE_FIELD_3);
    }

    @Test
    @Transactional
    void fullUpdateEditChannelsWithPatch() throws Exception {
        // Initialize the database
        editChannelsRepository.saveAndFlush(editChannels);

        int databaseSizeBeforeUpdate = editChannelsRepository.findAll().size();

        // Update the editChannels using partial update
        EditChannels partialUpdatedEditChannels = new EditChannels();
        partialUpdatedEditChannels.setId(editChannels.getId());

        partialUpdatedEditChannels
            .idMessage(UPDATED_ID_MESSAGE)
            .idChannel(UPDATED_ID_CHANNEL)
            .dateCreateMessage(UPDATED_DATE_CREATE_MESSAGE)
            .lastNameChannel(UPDATED_LAST_NAME_CHANNEL)
            .newNameChannel(UPDATED_NEW_NAME_CHANNEL)
            .isAproveChange(UPDATED_IS_APROVE_CHANGE)
            .lastLinkToChannel(UPDATED_LAST_LINK_TO_CHANNEL)
            .newlastLinkToChannel(UPDATED_NEWLAST_LINK_TO_CHANNEL)
            .lastPriceChannel(UPDATED_LAST_PRICE_CHANNEL)
            .newPriceChannel(UPDATED_NEW_PRICE_CHANNEL)
            .addDescriptionAboutChannel(UPDATED_ADD_DESCRIPTION_ABOUT_CHANNEL)
            .currentDescriptionChannel(UPDATED_CURRENT_DESCRIPTION_CHANNEL)
            .addRegionChannel(UPDATED_ADD_REGION_CHANNEL)
            .editRegionChannel(UPDATED_EDIT_REGION_CHANNEL)
            .addCityChannel(UPDATED_ADD_CITY_CHANNEL)
            .editCityChannel(UPDATED_EDIT_CITY_CHANNEL)
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .isApprovedChanhes(UPDATED_IS_APPROVED_CHANHES)
            .comment(UPDATED_COMMENT)
            .status(UPDATED_STATUS)
            .serviceField1(UPDATED_SERVICE_FIELD_1)
            .serviceField2(UPDATED_SERVICE_FIELD_2)
            .serviceField3(UPDATED_SERVICE_FIELD_3);

        restEditChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEditChannels.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEditChannels))
            )
            .andExpect(status().isOk());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeUpdate);
        EditChannels testEditChannels = editChannelsList.get(editChannelsList.size() - 1);
        assertThat(testEditChannels.getIdMessage()).isEqualTo(UPDATED_ID_MESSAGE);
        assertThat(testEditChannels.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testEditChannels.getDateCreateMessage()).isEqualTo(UPDATED_DATE_CREATE_MESSAGE);
        assertThat(testEditChannels.getLastNameChannel()).isEqualTo(UPDATED_LAST_NAME_CHANNEL);
        assertThat(testEditChannels.getNewNameChannel()).isEqualTo(UPDATED_NEW_NAME_CHANNEL);
        assertThat(testEditChannels.getIsAproveChange()).isEqualTo(UPDATED_IS_APROVE_CHANGE);
        assertThat(testEditChannels.getLastLinkToChannel()).isEqualTo(UPDATED_LAST_LINK_TO_CHANNEL);
        assertThat(testEditChannels.getNewlastLinkToChannel()).isEqualTo(UPDATED_NEWLAST_LINK_TO_CHANNEL);
        assertThat(testEditChannels.getLastPriceChannel()).isEqualTo(UPDATED_LAST_PRICE_CHANNEL);
        assertThat(testEditChannels.getNewPriceChannel()).isEqualTo(UPDATED_NEW_PRICE_CHANNEL);
        assertThat(testEditChannels.getAddDescriptionAboutChannel()).isEqualTo(UPDATED_ADD_DESCRIPTION_ABOUT_CHANNEL);
        assertThat(testEditChannels.getCurrentDescriptionChannel()).isEqualTo(UPDATED_CURRENT_DESCRIPTION_CHANNEL);
        assertThat(testEditChannels.getAddRegionChannel()).isEqualTo(UPDATED_ADD_REGION_CHANNEL);
        assertThat(testEditChannels.getEditRegionChannel()).isEqualTo(UPDATED_EDIT_REGION_CHANNEL);
        assertThat(testEditChannels.getAddCityChannel()).isEqualTo(UPDATED_ADD_CITY_CHANNEL);
        assertThat(testEditChannels.getEditCityChannel()).isEqualTo(UPDATED_EDIT_CITY_CHANNEL);
        assertThat(testEditChannels.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testEditChannels.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testEditChannels.getIsApprovedChanhes()).isEqualTo(UPDATED_IS_APPROVED_CHANHES);
        assertThat(testEditChannels.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testEditChannels.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testEditChannels.getServiceField1()).isEqualTo(UPDATED_SERVICE_FIELD_1);
        assertThat(testEditChannels.getServiceField2()).isEqualTo(UPDATED_SERVICE_FIELD_2);
        assertThat(testEditChannels.getServiceField3()).isEqualTo(UPDATED_SERVICE_FIELD_3);
    }

    @Test
    @Transactional
    void patchNonExistingEditChannels() throws Exception {
        int databaseSizeBeforeUpdate = editChannelsRepository.findAll().size();
        editChannels.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEditChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, editChannels.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(editChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEditChannels() throws Exception {
        int databaseSizeBeforeUpdate = editChannelsRepository.findAll().size();
        editChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEditChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(editChannels))
            )
            .andExpect(status().isBadRequest());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEditChannels() throws Exception {
        int databaseSizeBeforeUpdate = editChannelsRepository.findAll().size();
        editChannels.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEditChannelsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(editChannels))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EditChannels in the database
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEditChannels() throws Exception {
        // Initialize the database
        editChannelsRepository.saveAndFlush(editChannels);

        int databaseSizeBeforeDelete = editChannelsRepository.findAll().size();

        // Delete the editChannels
        restEditChannelsMockMvc
            .perform(delete(ENTITY_API_URL_ID, editChannels.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EditChannels> editChannelsList = editChannelsRepository.findAll();
        assertThat(editChannelsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
