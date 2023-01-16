package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.MessegePannel;
import com.mycompany.myapp.repository.MessegePannelRepository;
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
 * Integration tests for the {@link MessegePannelResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MessegePannelResourceIT {

    private static final Long DEFAULT_ID_MESSAGE = 1L;
    private static final Long UPDATED_ID_MESSAGE = 2L;

    private static final Long DEFAULT_ID_CHANNEL = 1L;
    private static final Long UPDATED_ID_CHANNEL = 2L;

    private static final ZonedDateTime DEFAULT_DATE_CREATE_MESSAGE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_CREATE_MESSAGE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TEXT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_TEXT_MESSAGE = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_ADMIN = 1L;
    private static final Long UPDATED_ID_ADMIN = 2L;

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

    private static final String ENTITY_API_URL = "/api/messege-pannels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private MessegePannelRepository messegePannelRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMessegePannelMockMvc;

    private MessegePannel messegePannel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessegePannel createEntity(EntityManager em) {
        MessegePannel messegePannel = new MessegePannel()
            .idMessage(DEFAULT_ID_MESSAGE)
            .idChannel(DEFAULT_ID_CHANNEL)
            .dateCreateMessage(DEFAULT_DATE_CREATE_MESSAGE)
            .textMessage(DEFAULT_TEXT_MESSAGE)
            .idAdmin(DEFAULT_ID_ADMIN)
            .comment(DEFAULT_COMMENT)
            .status(DEFAULT_STATUS)
            .serviceField1(DEFAULT_SERVICE_FIELD_1)
            .serviceField2(DEFAULT_SERVICE_FIELD_2)
            .serviceField3(DEFAULT_SERVICE_FIELD_3);
        return messegePannel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MessegePannel createUpdatedEntity(EntityManager em) {
        MessegePannel messegePannel = new MessegePannel()
            .idMessage(UPDATED_ID_MESSAGE)
            .idChannel(UPDATED_ID_CHANNEL)
            .dateCreateMessage(UPDATED_DATE_CREATE_MESSAGE)
            .textMessage(UPDATED_TEXT_MESSAGE)
            .idAdmin(UPDATED_ID_ADMIN)
            .comment(UPDATED_COMMENT)
            .status(UPDATED_STATUS)
            .serviceField1(UPDATED_SERVICE_FIELD_1)
            .serviceField2(UPDATED_SERVICE_FIELD_2)
            .serviceField3(UPDATED_SERVICE_FIELD_3);
        return messegePannel;
    }

    @BeforeEach
    public void initTest() {
        messegePannel = createEntity(em);
    }

    @Test
    @Transactional
    void createMessegePannel() throws Exception {
        int databaseSizeBeforeCreate = messegePannelRepository.findAll().size();
        // Create the MessegePannel
        restMessegePannelMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messegePannel)))
            .andExpect(status().isCreated());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeCreate + 1);
        MessegePannel testMessegePannel = messegePannelList.get(messegePannelList.size() - 1);
        assertThat(testMessegePannel.getIdMessage()).isEqualTo(DEFAULT_ID_MESSAGE);
        assertThat(testMessegePannel.getIdChannel()).isEqualTo(DEFAULT_ID_CHANNEL);
        assertThat(testMessegePannel.getDateCreateMessage()).isEqualTo(DEFAULT_DATE_CREATE_MESSAGE);
        assertThat(testMessegePannel.getTextMessage()).isEqualTo(DEFAULT_TEXT_MESSAGE);
        assertThat(testMessegePannel.getIdAdmin()).isEqualTo(DEFAULT_ID_ADMIN);
        assertThat(testMessegePannel.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testMessegePannel.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMessegePannel.getServiceField1()).isEqualTo(DEFAULT_SERVICE_FIELD_1);
        assertThat(testMessegePannel.getServiceField2()).isEqualTo(DEFAULT_SERVICE_FIELD_2);
        assertThat(testMessegePannel.getServiceField3()).isEqualTo(DEFAULT_SERVICE_FIELD_3);
    }

    @Test
    @Transactional
    void createMessegePannelWithExistingId() throws Exception {
        // Create the MessegePannel with an existing ID
        messegePannel.setId(1L);

        int databaseSizeBeforeCreate = messegePannelRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMessegePannelMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messegePannel)))
            .andExpect(status().isBadRequest());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMessegePannels() throws Exception {
        // Initialize the database
        messegePannelRepository.saveAndFlush(messegePannel);

        // Get all the messegePannelList
        restMessegePannelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(messegePannel.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMessage").value(hasItem(DEFAULT_ID_MESSAGE.intValue())))
            .andExpect(jsonPath("$.[*].idChannel").value(hasItem(DEFAULT_ID_CHANNEL.intValue())))
            .andExpect(jsonPath("$.[*].dateCreateMessage").value(hasItem(sameInstant(DEFAULT_DATE_CREATE_MESSAGE))))
            .andExpect(jsonPath("$.[*].textMessage").value(hasItem(DEFAULT_TEXT_MESSAGE)))
            .andExpect(jsonPath("$.[*].idAdmin").value(hasItem(DEFAULT_ID_ADMIN.intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].serviceField1").value(hasItem(DEFAULT_SERVICE_FIELD_1)))
            .andExpect(jsonPath("$.[*].serviceField2").value(hasItem(DEFAULT_SERVICE_FIELD_2)))
            .andExpect(jsonPath("$.[*].serviceField3").value(hasItem(DEFAULT_SERVICE_FIELD_3)));
    }

    @Test
    @Transactional
    void getMessegePannel() throws Exception {
        // Initialize the database
        messegePannelRepository.saveAndFlush(messegePannel);

        // Get the messegePannel
        restMessegePannelMockMvc
            .perform(get(ENTITY_API_URL_ID, messegePannel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(messegePannel.getId().intValue()))
            .andExpect(jsonPath("$.idMessage").value(DEFAULT_ID_MESSAGE.intValue()))
            .andExpect(jsonPath("$.idChannel").value(DEFAULT_ID_CHANNEL.intValue()))
            .andExpect(jsonPath("$.dateCreateMessage").value(sameInstant(DEFAULT_DATE_CREATE_MESSAGE)))
            .andExpect(jsonPath("$.textMessage").value(DEFAULT_TEXT_MESSAGE))
            .andExpect(jsonPath("$.idAdmin").value(DEFAULT_ID_ADMIN.intValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.serviceField1").value(DEFAULT_SERVICE_FIELD_1))
            .andExpect(jsonPath("$.serviceField2").value(DEFAULT_SERVICE_FIELD_2))
            .andExpect(jsonPath("$.serviceField3").value(DEFAULT_SERVICE_FIELD_3));
    }

    @Test
    @Transactional
    void getNonExistingMessegePannel() throws Exception {
        // Get the messegePannel
        restMessegePannelMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewMessegePannel() throws Exception {
        // Initialize the database
        messegePannelRepository.saveAndFlush(messegePannel);

        int databaseSizeBeforeUpdate = messegePannelRepository.findAll().size();

        // Update the messegePannel
        MessegePannel updatedMessegePannel = messegePannelRepository.findById(messegePannel.getId()).get();
        // Disconnect from session so that the updates on updatedMessegePannel are not directly saved in db
        em.detach(updatedMessegePannel);
        updatedMessegePannel
            .idMessage(UPDATED_ID_MESSAGE)
            .idChannel(UPDATED_ID_CHANNEL)
            .dateCreateMessage(UPDATED_DATE_CREATE_MESSAGE)
            .textMessage(UPDATED_TEXT_MESSAGE)
            .idAdmin(UPDATED_ID_ADMIN)
            .comment(UPDATED_COMMENT)
            .status(UPDATED_STATUS)
            .serviceField1(UPDATED_SERVICE_FIELD_1)
            .serviceField2(UPDATED_SERVICE_FIELD_2)
            .serviceField3(UPDATED_SERVICE_FIELD_3);

        restMessegePannelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMessegePannel.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMessegePannel))
            )
            .andExpect(status().isOk());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeUpdate);
        MessegePannel testMessegePannel = messegePannelList.get(messegePannelList.size() - 1);
        assertThat(testMessegePannel.getIdMessage()).isEqualTo(UPDATED_ID_MESSAGE);
        assertThat(testMessegePannel.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testMessegePannel.getDateCreateMessage()).isEqualTo(UPDATED_DATE_CREATE_MESSAGE);
        assertThat(testMessegePannel.getTextMessage()).isEqualTo(UPDATED_TEXT_MESSAGE);
        assertThat(testMessegePannel.getIdAdmin()).isEqualTo(UPDATED_ID_ADMIN);
        assertThat(testMessegePannel.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testMessegePannel.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMessegePannel.getServiceField1()).isEqualTo(UPDATED_SERVICE_FIELD_1);
        assertThat(testMessegePannel.getServiceField2()).isEqualTo(UPDATED_SERVICE_FIELD_2);
        assertThat(testMessegePannel.getServiceField3()).isEqualTo(UPDATED_SERVICE_FIELD_3);
    }

    @Test
    @Transactional
    void putNonExistingMessegePannel() throws Exception {
        int databaseSizeBeforeUpdate = messegePannelRepository.findAll().size();
        messegePannel.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessegePannelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, messegePannel.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(messegePannel))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMessegePannel() throws Exception {
        int databaseSizeBeforeUpdate = messegePannelRepository.findAll().size();
        messegePannel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessegePannelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(messegePannel))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMessegePannel() throws Exception {
        int databaseSizeBeforeUpdate = messegePannelRepository.findAll().size();
        messegePannel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessegePannelMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(messegePannel)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMessegePannelWithPatch() throws Exception {
        // Initialize the database
        messegePannelRepository.saveAndFlush(messegePannel);

        int databaseSizeBeforeUpdate = messegePannelRepository.findAll().size();

        // Update the messegePannel using partial update
        MessegePannel partialUpdatedMessegePannel = new MessegePannel();
        partialUpdatedMessegePannel.setId(messegePannel.getId());

        partialUpdatedMessegePannel
            .idChannel(UPDATED_ID_CHANNEL)
            .idAdmin(UPDATED_ID_ADMIN)
            .status(UPDATED_STATUS)
            .serviceField1(UPDATED_SERVICE_FIELD_1)
            .serviceField2(UPDATED_SERVICE_FIELD_2)
            .serviceField3(UPDATED_SERVICE_FIELD_3);

        restMessegePannelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMessegePannel.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMessegePannel))
            )
            .andExpect(status().isOk());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeUpdate);
        MessegePannel testMessegePannel = messegePannelList.get(messegePannelList.size() - 1);
        assertThat(testMessegePannel.getIdMessage()).isEqualTo(DEFAULT_ID_MESSAGE);
        assertThat(testMessegePannel.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testMessegePannel.getDateCreateMessage()).isEqualTo(DEFAULT_DATE_CREATE_MESSAGE);
        assertThat(testMessegePannel.getTextMessage()).isEqualTo(DEFAULT_TEXT_MESSAGE);
        assertThat(testMessegePannel.getIdAdmin()).isEqualTo(UPDATED_ID_ADMIN);
        assertThat(testMessegePannel.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testMessegePannel.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMessegePannel.getServiceField1()).isEqualTo(UPDATED_SERVICE_FIELD_1);
        assertThat(testMessegePannel.getServiceField2()).isEqualTo(UPDATED_SERVICE_FIELD_2);
        assertThat(testMessegePannel.getServiceField3()).isEqualTo(UPDATED_SERVICE_FIELD_3);
    }

    @Test
    @Transactional
    void fullUpdateMessegePannelWithPatch() throws Exception {
        // Initialize the database
        messegePannelRepository.saveAndFlush(messegePannel);

        int databaseSizeBeforeUpdate = messegePannelRepository.findAll().size();

        // Update the messegePannel using partial update
        MessegePannel partialUpdatedMessegePannel = new MessegePannel();
        partialUpdatedMessegePannel.setId(messegePannel.getId());

        partialUpdatedMessegePannel
            .idMessage(UPDATED_ID_MESSAGE)
            .idChannel(UPDATED_ID_CHANNEL)
            .dateCreateMessage(UPDATED_DATE_CREATE_MESSAGE)
            .textMessage(UPDATED_TEXT_MESSAGE)
            .idAdmin(UPDATED_ID_ADMIN)
            .comment(UPDATED_COMMENT)
            .status(UPDATED_STATUS)
            .serviceField1(UPDATED_SERVICE_FIELD_1)
            .serviceField2(UPDATED_SERVICE_FIELD_2)
            .serviceField3(UPDATED_SERVICE_FIELD_3);

        restMessegePannelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMessegePannel.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedMessegePannel))
            )
            .andExpect(status().isOk());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeUpdate);
        MessegePannel testMessegePannel = messegePannelList.get(messegePannelList.size() - 1);
        assertThat(testMessegePannel.getIdMessage()).isEqualTo(UPDATED_ID_MESSAGE);
        assertThat(testMessegePannel.getIdChannel()).isEqualTo(UPDATED_ID_CHANNEL);
        assertThat(testMessegePannel.getDateCreateMessage()).isEqualTo(UPDATED_DATE_CREATE_MESSAGE);
        assertThat(testMessegePannel.getTextMessage()).isEqualTo(UPDATED_TEXT_MESSAGE);
        assertThat(testMessegePannel.getIdAdmin()).isEqualTo(UPDATED_ID_ADMIN);
        assertThat(testMessegePannel.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testMessegePannel.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMessegePannel.getServiceField1()).isEqualTo(UPDATED_SERVICE_FIELD_1);
        assertThat(testMessegePannel.getServiceField2()).isEqualTo(UPDATED_SERVICE_FIELD_2);
        assertThat(testMessegePannel.getServiceField3()).isEqualTo(UPDATED_SERVICE_FIELD_3);
    }

    @Test
    @Transactional
    void patchNonExistingMessegePannel() throws Exception {
        int databaseSizeBeforeUpdate = messegePannelRepository.findAll().size();
        messegePannel.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMessegePannelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, messegePannel.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(messegePannel))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMessegePannel() throws Exception {
        int databaseSizeBeforeUpdate = messegePannelRepository.findAll().size();
        messegePannel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessegePannelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(messegePannel))
            )
            .andExpect(status().isBadRequest());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMessegePannel() throws Exception {
        int databaseSizeBeforeUpdate = messegePannelRepository.findAll().size();
        messegePannel.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMessegePannelMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(messegePannel))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the MessegePannel in the database
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMessegePannel() throws Exception {
        // Initialize the database
        messegePannelRepository.saveAndFlush(messegePannel);

        int databaseSizeBeforeDelete = messegePannelRepository.findAll().size();

        // Delete the messegePannel
        restMessegePannelMockMvc
            .perform(delete(ENTITY_API_URL_ID, messegePannel.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MessegePannel> messegePannelList = messegePannelRepository.findAll();
        assertThat(messegePannelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
