package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.ConfigTable;
import com.mycompany.myapp.repository.ConfigTableRepository;
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
 * Integration tests for the {@link ConfigTableResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ConfigTableResourceIT {

    private static final ZonedDateTime DEFAULT_DATE_ONE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_ONE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATE_TWO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE_TWO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_LONG_ONE = 1L;
    private static final Long UPDATED_LONG_ONE = 2L;

    private static final String DEFAULT_STRING_ONE = "AAAAAAAAAA";
    private static final String UPDATED_STRING_ONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_BOOLEAN_ONE = false;
    private static final Boolean UPDATED_BOOLEAN_ONE = true;

    private static final Boolean DEFAULT_BOOLEAN_TWO = false;
    private static final Boolean UPDATED_BOOLEAN_TWO = true;

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

    private static final String ENTITY_API_URL = "/api/config-tables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ConfigTableRepository configTableRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConfigTableMockMvc;

    private ConfigTable configTable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConfigTable createEntity(EntityManager em) {
        ConfigTable configTable = new ConfigTable()
            .dateOne(DEFAULT_DATE_ONE)
            .dateTwo(DEFAULT_DATE_TWO)
            .longOne(DEFAULT_LONG_ONE)
            .stringOne(DEFAULT_STRING_ONE)
            .booleanOne(DEFAULT_BOOLEAN_ONE)
            .booleanTwo(DEFAULT_BOOLEAN_TWO)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return configTable;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConfigTable createUpdatedEntity(EntityManager em) {
        ConfigTable configTable = new ConfigTable()
            .dateOne(UPDATED_DATE_ONE)
            .dateTwo(UPDATED_DATE_TWO)
            .longOne(UPDATED_LONG_ONE)
            .stringOne(UPDATED_STRING_ONE)
            .booleanOne(UPDATED_BOOLEAN_ONE)
            .booleanTwo(UPDATED_BOOLEAN_TWO)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return configTable;
    }

    @BeforeEach
    public void initTest() {
        configTable = createEntity(em);
    }

    @Test
    @Transactional
    void createConfigTable() throws Exception {
        int databaseSizeBeforeCreate = configTableRepository.findAll().size();
        // Create the ConfigTable
        restConfigTableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(configTable)))
            .andExpect(status().isCreated());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeCreate + 1);
        ConfigTable testConfigTable = configTableList.get(configTableList.size() - 1);
        assertThat(testConfigTable.getDateOne()).isEqualTo(DEFAULT_DATE_ONE);
        assertThat(testConfigTable.getDateTwo()).isEqualTo(DEFAULT_DATE_TWO);
        assertThat(testConfigTable.getLongOne()).isEqualTo(DEFAULT_LONG_ONE);
        assertThat(testConfigTable.getStringOne()).isEqualTo(DEFAULT_STRING_ONE);
        assertThat(testConfigTable.getBooleanOne()).isEqualTo(DEFAULT_BOOLEAN_ONE);
        assertThat(testConfigTable.getBooleanTwo()).isEqualTo(DEFAULT_BOOLEAN_TWO);
        assertThat(testConfigTable.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testConfigTable.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testConfigTable.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testConfigTable.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testConfigTable.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createConfigTableWithExistingId() throws Exception {
        // Create the ConfigTable with an existing ID
        configTable.setId(1L);

        int databaseSizeBeforeCreate = configTableRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfigTableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(configTable)))
            .andExpect(status().isBadRequest());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllConfigTables() throws Exception {
        // Initialize the database
        configTableRepository.saveAndFlush(configTable);

        // Get all the configTableList
        restConfigTableMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configTable.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateOne").value(hasItem(sameInstant(DEFAULT_DATE_ONE))))
            .andExpect(jsonPath("$.[*].dateTwo").value(hasItem(sameInstant(DEFAULT_DATE_TWO))))
            .andExpect(jsonPath("$.[*].longOne").value(hasItem(DEFAULT_LONG_ONE.intValue())))
            .andExpect(jsonPath("$.[*].stringOne").value(hasItem(DEFAULT_STRING_ONE)))
            .andExpect(jsonPath("$.[*].booleanOne").value(hasItem(DEFAULT_BOOLEAN_ONE.booleanValue())))
            .andExpect(jsonPath("$.[*].booleanTwo").value(hasItem(DEFAULT_BOOLEAN_TWO.booleanValue())))
            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
    }

    @Test
    @Transactional
    void getConfigTable() throws Exception {
        // Initialize the database
        configTableRepository.saveAndFlush(configTable);

        // Get the configTable
        restConfigTableMockMvc
            .perform(get(ENTITY_API_URL_ID, configTable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(configTable.getId().intValue()))
            .andExpect(jsonPath("$.dateOne").value(sameInstant(DEFAULT_DATE_ONE)))
            .andExpect(jsonPath("$.dateTwo").value(sameInstant(DEFAULT_DATE_TWO)))
            .andExpect(jsonPath("$.longOne").value(DEFAULT_LONG_ONE.intValue()))
            .andExpect(jsonPath("$.stringOne").value(DEFAULT_STRING_ONE))
            .andExpect(jsonPath("$.booleanOne").value(DEFAULT_BOOLEAN_ONE.booleanValue()))
            .andExpect(jsonPath("$.booleanTwo").value(DEFAULT_BOOLEAN_TWO.booleanValue()))
            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingConfigTable() throws Exception {
        // Get the configTable
        restConfigTableMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewConfigTable() throws Exception {
        // Initialize the database
        configTableRepository.saveAndFlush(configTable);

        int databaseSizeBeforeUpdate = configTableRepository.findAll().size();

        // Update the configTable
        ConfigTable updatedConfigTable = configTableRepository.findById(configTable.getId()).get();
        // Disconnect from session so that the updates on updatedConfigTable are not directly saved in db
        em.detach(updatedConfigTable);
        updatedConfigTable
            .dateOne(UPDATED_DATE_ONE)
            .dateTwo(UPDATED_DATE_TWO)
            .longOne(UPDATED_LONG_ONE)
            .stringOne(UPDATED_STRING_ONE)
            .booleanOne(UPDATED_BOOLEAN_ONE)
            .booleanTwo(UPDATED_BOOLEAN_TWO)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restConfigTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedConfigTable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedConfigTable))
            )
            .andExpect(status().isOk());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeUpdate);
        ConfigTable testConfigTable = configTableList.get(configTableList.size() - 1);
        assertThat(testConfigTable.getDateOne()).isEqualTo(UPDATED_DATE_ONE);
        assertThat(testConfigTable.getDateTwo()).isEqualTo(UPDATED_DATE_TWO);
        assertThat(testConfigTable.getLongOne()).isEqualTo(UPDATED_LONG_ONE);
        assertThat(testConfigTable.getStringOne()).isEqualTo(UPDATED_STRING_ONE);
        assertThat(testConfigTable.getBooleanOne()).isEqualTo(UPDATED_BOOLEAN_ONE);
        assertThat(testConfigTable.getBooleanTwo()).isEqualTo(UPDATED_BOOLEAN_TWO);
        assertThat(testConfigTable.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testConfigTable.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testConfigTable.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testConfigTable.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testConfigTable.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingConfigTable() throws Exception {
        int databaseSizeBeforeUpdate = configTableRepository.findAll().size();
        configTable.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConfigTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, configTable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(configTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchConfigTable() throws Exception {
        int databaseSizeBeforeUpdate = configTableRepository.findAll().size();
        configTable.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restConfigTableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(configTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamConfigTable() throws Exception {
        int databaseSizeBeforeUpdate = configTableRepository.findAll().size();
        configTable.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restConfigTableMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(configTable)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateConfigTableWithPatch() throws Exception {
        // Initialize the database
        configTableRepository.saveAndFlush(configTable);

        int databaseSizeBeforeUpdate = configTableRepository.findAll().size();

        // Update the configTable using partial update
        ConfigTable partialUpdatedConfigTable = new ConfigTable();
        partialUpdatedConfigTable.setId(configTable.getId());

        partialUpdatedConfigTable.stringOne(UPDATED_STRING_ONE).date1(UPDATED_DATE_1).string1(UPDATED_STRING_1);

        restConfigTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedConfigTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedConfigTable))
            )
            .andExpect(status().isOk());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeUpdate);
        ConfigTable testConfigTable = configTableList.get(configTableList.size() - 1);
        assertThat(testConfigTable.getDateOne()).isEqualTo(DEFAULT_DATE_ONE);
        assertThat(testConfigTable.getDateTwo()).isEqualTo(DEFAULT_DATE_TWO);
        assertThat(testConfigTable.getLongOne()).isEqualTo(DEFAULT_LONG_ONE);
        assertThat(testConfigTable.getStringOne()).isEqualTo(UPDATED_STRING_ONE);
        assertThat(testConfigTable.getBooleanOne()).isEqualTo(DEFAULT_BOOLEAN_ONE);
        assertThat(testConfigTable.getBooleanTwo()).isEqualTo(DEFAULT_BOOLEAN_TWO);
        assertThat(testConfigTable.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testConfigTable.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testConfigTable.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testConfigTable.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testConfigTable.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateConfigTableWithPatch() throws Exception {
        // Initialize the database
        configTableRepository.saveAndFlush(configTable);

        int databaseSizeBeforeUpdate = configTableRepository.findAll().size();

        // Update the configTable using partial update
        ConfigTable partialUpdatedConfigTable = new ConfigTable();
        partialUpdatedConfigTable.setId(configTable.getId());

        partialUpdatedConfigTable
            .dateOne(UPDATED_DATE_ONE)
            .dateTwo(UPDATED_DATE_TWO)
            .longOne(UPDATED_LONG_ONE)
            .stringOne(UPDATED_STRING_ONE)
            .booleanOne(UPDATED_BOOLEAN_ONE)
            .booleanTwo(UPDATED_BOOLEAN_TWO)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restConfigTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedConfigTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedConfigTable))
            )
            .andExpect(status().isOk());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeUpdate);
        ConfigTable testConfigTable = configTableList.get(configTableList.size() - 1);
        assertThat(testConfigTable.getDateOne()).isEqualTo(UPDATED_DATE_ONE);
        assertThat(testConfigTable.getDateTwo()).isEqualTo(UPDATED_DATE_TWO);
        assertThat(testConfigTable.getLongOne()).isEqualTo(UPDATED_LONG_ONE);
        assertThat(testConfigTable.getStringOne()).isEqualTo(UPDATED_STRING_ONE);
        assertThat(testConfigTable.getBooleanOne()).isEqualTo(UPDATED_BOOLEAN_ONE);
        assertThat(testConfigTable.getBooleanTwo()).isEqualTo(UPDATED_BOOLEAN_TWO);
        assertThat(testConfigTable.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testConfigTable.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testConfigTable.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testConfigTable.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testConfigTable.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingConfigTable() throws Exception {
        int databaseSizeBeforeUpdate = configTableRepository.findAll().size();
        configTable.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConfigTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, configTable.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(configTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchConfigTable() throws Exception {
        int databaseSizeBeforeUpdate = configTableRepository.findAll().size();
        configTable.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restConfigTableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(configTable))
            )
            .andExpect(status().isBadRequest());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamConfigTable() throws Exception {
        int databaseSizeBeforeUpdate = configTableRepository.findAll().size();
        configTable.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restConfigTableMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(configTable))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ConfigTable in the database
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteConfigTable() throws Exception {
        // Initialize the database
        configTableRepository.saveAndFlush(configTable);

        int databaseSizeBeforeDelete = configTableRepository.findAll().size();

        // Delete the configTable
        restConfigTableMockMvc
            .perform(delete(ENTITY_API_URL_ID, configTable.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ConfigTable> configTableList = configTableRepository.findAll();
        assertThat(configTableList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
