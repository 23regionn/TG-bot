/*
package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TGUser;
import com.mycompany.myapp.repository.TGUserRepository;
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

*/
/**
 * Integration tests for the {@link TGUserResource} REST controller.
 *//*

@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TGUserResourceIT {

    private static final Long DEFAULT_ID_TG_USER = 1L;
    private static final Long UPDATED_ID_TG_USER = 2L;

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_REGISTRATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_REGISTRATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_USER_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_USER_ROLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ADMIN = false;
    private static final Boolean UPDATED_IS_ADMIN = true;

    private static final Double DEFAULT_SCORE = 1D;
    private static final Double UPDATED_SCORE = 2D;

    private static final Boolean DEFAULT_IS_BLOCKED = false;
    private static final Boolean UPDATED_IS_BLOCKED = true;

    private static final Long DEFAULT_CHAT_ID = 1L;
    private static final Long UPDATED_CHAT_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/tg-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TGUserRepository tGUserRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTGUserMockMvc;

    private TGUser tGUser;

    */
/**
 * Create an entity for this test.
 *
 * This is a static method, as tests for other entities might also need it,
 * if they test an entity which requires the current entity.
 *//*

    public static TGUser createEntity(EntityManager em) {
        TGUser tGUser = new TGUser()
            .idTgUser(DEFAULT_ID_TG_USER)
            .firstName(DEFAULT_FIRST_NAME)
            .registrationDate(DEFAULT_REGISTRATION_DATE)
            .userRole(DEFAULT_USER_ROLE)
            .isAdmin(DEFAULT_IS_ADMIN)
            .score(DEFAULT_SCORE)
            .isBlocked(DEFAULT_IS_BLOCKED)
            .chatId(DEFAULT_CHAT_ID)
            .isDelete(DEFAULT_IS_DELETE)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return tGUser;
    }

    */
/**
 * Create an updated entity for this test.
 *
 * This is a static method, as tests for other entities might also need it,
 * if they test an entity which requires the current entity.
 *//*

    public static TGUser createUpdatedEntity(EntityManager em) {
        TGUser tGUser = new TGUser()
            .idTgUser(UPDATED_ID_TG_USER)
            .firstName(UPDATED_FIRST_NAME)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .userRole(UPDATED_USER_ROLE)
            .isAdmin(UPDATED_IS_ADMIN)
            .score(UPDATED_SCORE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .chatId(UPDATED_CHAT_ID)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return tGUser;
    }

    @BeforeEach
    public void initTest() {
        tGUser = createEntity(em);
    }

    @Test
    @Transactional
    void createTGUser() throws Exception {
        int databaseSizeBeforeCreate = tGUserRepository.findAll().size();
        // Create the TGUser
        restTGUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tGUser)))
            .andExpect(status().isCreated());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeCreate + 1);
        TGUser testTGUser = tGUserList.get(tGUserList.size() - 1);
        assertThat(testTGUser.getIdTgUser()).isEqualTo(DEFAULT_ID_TG_USER);
        assertThat(testTGUser.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testTGUser.getRegistrationDate()).isEqualTo(DEFAULT_REGISTRATION_DATE);
        assertThat(testTGUser.getUserRole()).isEqualTo(DEFAULT_USER_ROLE);
        assertThat(testTGUser.getIsAdmin()).isEqualTo(DEFAULT_IS_ADMIN);
        assertThat(testTGUser.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testTGUser.getIsBlocked()).isEqualTo(DEFAULT_IS_BLOCKED);
        assertThat(testTGUser.getChatId()).isEqualTo(DEFAULT_CHAT_ID);
        assertThat(testTGUser.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testTGUser.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testTGUser.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testTGUser.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testTGUser.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testTGUser.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createTGUserWithExistingId() throws Exception {
        // Create the TGUser with an existing ID
        tGUser.setId(1L);

        int databaseSizeBeforeCreate = tGUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTGUserMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tGUser)))
            .andExpect(status().isBadRequest());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTGUsers() throws Exception {
        // Initialize the database
        tGUserRepository.saveAndFlush(tGUser);

        // Get all the tGUserList
        restTGUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tGUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTgUser").value(hasItem(DEFAULT_ID_TG_USER.intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].registrationDate").value(hasItem(sameInstant(DEFAULT_REGISTRATION_DATE))))
            .andExpect(jsonPath("$.[*].userRole").value(hasItem(DEFAULT_USER_ROLE)))
            .andExpect(jsonPath("$.[*].isAdmin").value(hasItem(DEFAULT_IS_ADMIN.booleanValue())))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE.doubleValue())))
            .andExpect(jsonPath("$.[*].isBlocked").value(hasItem(DEFAULT_IS_BLOCKED.booleanValue())))
            .andExpect(jsonPath("$.[*].chatId").value(hasItem(DEFAULT_CHAT_ID.intValue())))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
    }

    @Test
    @Transactional
    void getTGUser() throws Exception {
        // Initialize the database
        tGUserRepository.saveAndFlush(tGUser);

        // Get the tGUser
        restTGUserMockMvc
            .perform(get(ENTITY_API_URL_ID, tGUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tGUser.getId().intValue()))
            .andExpect(jsonPath("$.idTgUser").value(DEFAULT_ID_TG_USER.intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.registrationDate").value(sameInstant(DEFAULT_REGISTRATION_DATE)))
            .andExpect(jsonPath("$.userRole").value(DEFAULT_USER_ROLE))
            .andExpect(jsonPath("$.isAdmin").value(DEFAULT_IS_ADMIN.booleanValue()))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE.doubleValue()))
            .andExpect(jsonPath("$.isBlocked").value(DEFAULT_IS_BLOCKED.booleanValue()))
            .andExpect(jsonPath("$.chatId").value(DEFAULT_CHAT_ID.intValue()))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE.booleanValue()))
            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingTGUser() throws Exception {
        // Get the tGUser
        restTGUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTGUser() throws Exception {
        // Initialize the database
        tGUserRepository.saveAndFlush(tGUser);

        int databaseSizeBeforeUpdate = tGUserRepository.findAll().size();

        // Update the tGUser
        TGUser updatedTGUser = tGUserRepository.findById(tGUser.getId()).get();
        // Disconnect from session so that the updates on updatedTGUser are not directly saved in db
        em.detach(updatedTGUser);
        updatedTGUser
            .idTgUser(UPDATED_ID_TG_USER)
            .firstName(UPDATED_FIRST_NAME)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .userRole(UPDATED_USER_ROLE)
            .isAdmin(UPDATED_IS_ADMIN)
            .score(UPDATED_SCORE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .chatId(UPDATED_CHAT_ID)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restTGUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTGUser.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTGUser))
            )
            .andExpect(status().isOk());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeUpdate);
        TGUser testTGUser = tGUserList.get(tGUserList.size() - 1);
        assertThat(testTGUser.getIdTgUser()).isEqualTo(UPDATED_ID_TG_USER);
        assertThat(testTGUser.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testTGUser.getRegistrationDate()).isEqualTo(UPDATED_REGISTRATION_DATE);
        assertThat(testTGUser.getUserRole()).isEqualTo(UPDATED_USER_ROLE);
        assertThat(testTGUser.getIsAdmin()).isEqualTo(UPDATED_IS_ADMIN);
        assertThat(testTGUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testTGUser.getIsBlocked()).isEqualTo(UPDATED_IS_BLOCKED);
        assertThat(testTGUser.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testTGUser.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testTGUser.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTGUser.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testTGUser.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTGUser.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testTGUser.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingTGUser() throws Exception {
        int databaseSizeBeforeUpdate = tGUserRepository.findAll().size();
        tGUser.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTGUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tGUser.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tGUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTGUser() throws Exception {
        int databaseSizeBeforeUpdate = tGUserRepository.findAll().size();
        tGUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTGUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tGUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTGUser() throws Exception {
        int databaseSizeBeforeUpdate = tGUserRepository.findAll().size();
        tGUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTGUserMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tGUser)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTGUserWithPatch() throws Exception {
        // Initialize the database
        tGUserRepository.saveAndFlush(tGUser);

        int databaseSizeBeforeUpdate = tGUserRepository.findAll().size();

        // Update the tGUser using partial update
        TGUser partialUpdatedTGUser = new TGUser();
        partialUpdatedTGUser.setId(tGUser.getId());

        partialUpdatedTGUser
            .idTgUser(UPDATED_ID_TG_USER)
            .firstName(UPDATED_FIRST_NAME)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .userRole(UPDATED_USER_ROLE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .chatId(UPDATED_CHAT_ID)
            .date1(UPDATED_DATE_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restTGUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTGUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTGUser))
            )
            .andExpect(status().isOk());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeUpdate);
        TGUser testTGUser = tGUserList.get(tGUserList.size() - 1);
        assertThat(testTGUser.getIdTgUser()).isEqualTo(UPDATED_ID_TG_USER);
        assertThat(testTGUser.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testTGUser.getRegistrationDate()).isEqualTo(UPDATED_REGISTRATION_DATE);
        assertThat(testTGUser.getUserRole()).isEqualTo(UPDATED_USER_ROLE);
        assertThat(testTGUser.getIsAdmin()).isEqualTo(DEFAULT_IS_ADMIN);
        assertThat(testTGUser.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testTGUser.getIsBlocked()).isEqualTo(UPDATED_IS_BLOCKED);
        assertThat(testTGUser.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testTGUser.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testTGUser.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTGUser.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testTGUser.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testTGUser.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testTGUser.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateTGUserWithPatch() throws Exception {
        // Initialize the database
        tGUserRepository.saveAndFlush(tGUser);

        int databaseSizeBeforeUpdate = tGUserRepository.findAll().size();

        // Update the tGUser using partial update
        TGUser partialUpdatedTGUser = new TGUser();
        partialUpdatedTGUser.setId(tGUser.getId());

        partialUpdatedTGUser
            .idTgUser(UPDATED_ID_TG_USER)
            .firstName(UPDATED_FIRST_NAME)
            .registrationDate(UPDATED_REGISTRATION_DATE)
            .userRole(UPDATED_USER_ROLE)
            .isAdmin(UPDATED_IS_ADMIN)
            .score(UPDATED_SCORE)
            .isBlocked(UPDATED_IS_BLOCKED)
            .chatId(UPDATED_CHAT_ID)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restTGUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTGUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTGUser))
            )
            .andExpect(status().isOk());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeUpdate);
        TGUser testTGUser = tGUserList.get(tGUserList.size() - 1);
        assertThat(testTGUser.getIdTgUser()).isEqualTo(UPDATED_ID_TG_USER);
        assertThat(testTGUser.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testTGUser.getRegistrationDate()).isEqualTo(UPDATED_REGISTRATION_DATE);
        assertThat(testTGUser.getUserRole()).isEqualTo(UPDATED_USER_ROLE);
        assertThat(testTGUser.getIsAdmin()).isEqualTo(UPDATED_IS_ADMIN);
        assertThat(testTGUser.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testTGUser.getIsBlocked()).isEqualTo(UPDATED_IS_BLOCKED);
        assertThat(testTGUser.getChatId()).isEqualTo(UPDATED_CHAT_ID);
        assertThat(testTGUser.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testTGUser.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testTGUser.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testTGUser.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testTGUser.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testTGUser.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingTGUser() throws Exception {
        int databaseSizeBeforeUpdate = tGUserRepository.findAll().size();
        tGUser.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTGUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tGUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tGUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTGUser() throws Exception {
        int databaseSizeBeforeUpdate = tGUserRepository.findAll().size();
        tGUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTGUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tGUser))
            )
            .andExpect(status().isBadRequest());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTGUser() throws Exception {
        int databaseSizeBeforeUpdate = tGUserRepository.findAll().size();
        tGUser.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTGUserMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(tGUser)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TGUser in the database
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTGUser() throws Exception {
        // Initialize the database
        tGUserRepository.saveAndFlush(tGUser);

        int databaseSizeBeforeDelete = tGUserRepository.findAll().size();

        // Delete the tGUser
        restTGUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, tGUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TGUser> tGUserList = tGUserRepository.findAll();
        assertThat(tGUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
*/
