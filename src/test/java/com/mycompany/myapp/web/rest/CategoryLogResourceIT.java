//package com.mycompany.myapp.web.rest;
//
//import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.hasItem;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import com.mycompany.myapp.IntegrationTest;
//import com.mycompany.myapp.domain.CategoryLog;
//import com.mycompany.myapp.repository.CategoryLogRepository;
//import java.time.Instant;
//import java.time.ZoneId;
//import java.time.ZoneOffset;
//import java.time.ZonedDateTime;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicLong;
//import javax.persistence.EntityManager;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Integration tests for the {@link CategoryLogResource} REST controller.
// */
//@IntegrationTest
//@AutoConfigureMockMvc
//@WithMockUser
//class CategoryLogResourceIT {
//
//    private static final String DEFAULT_NAME = "AAAAAAAAAA";
//    private static final String UPDATED_NAME = "BBBBBBBBBB";
//
//    private static final Long DEFAULT_COUNT_CHANELL_IN_CATEGORY = 1L;
//    private static final Long UPDATED_COUNT_CHANELL_IN_CATEGORY = 2L;
//
//    private static final Boolean DEFAULT_IS_DELETE = false;
//    private static final Boolean UPDATED_IS_DELETE = true;
//
//    private static final ZonedDateTime DEFAULT_DATE_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
//    private static final ZonedDateTime UPDATED_DATE_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
//
//    private static final ZonedDateTime DEFAULT_DATE_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
//    private static final ZonedDateTime UPDATED_DATE_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
//
//    private static final Long DEFAULT_LONG_1 = 1L;
//    private static final Long UPDATED_LONG_1 = 2L;
//
//    private static final String DEFAULT_STRING_1 = "AAAAAAAAAA";
//    private static final String UPDATED_STRING_1 = "BBBBBBBBBB";
//
//    private static final Boolean DEFAULT_BOOLEAN_1 = false;
//    private static final Boolean UPDATED_BOOLEAN_1 = true;
//
//    private static final String ENTITY_API_URL = "/api/category-logs";
//    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
//
//    private static Random random = new Random();
//    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
//
//    @Autowired
//    private CategoryLogRepository categoryLogRepository;
//
//    @Autowired
//    private EntityManager em;
//
//    @Autowired
//    private MockMvc restCategoryLogMockMvc;
//
//    private CategoryLog categoryLog;
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static CategoryLog createEntity(EntityManager em) {
//        CategoryLog categoryLog = new CategoryLog()
//            .name(DEFAULT_NAME)
////            .countChanellInCategory(DEFAULT_COUNT_CHANELL_IN_CATEGORY)
//            .isDelete(DEFAULT_IS_DELETE)
//            .date1(DEFAULT_DATE_1)
//            .date2(DEFAULT_DATE_2)
//            .long1(DEFAULT_LONG_1)
//            .string1(DEFAULT_STRING_1)
//            .boolean1(DEFAULT_BOOLEAN_1);
//        return categoryLog;
//    }
//
//    /**
//     * Create an updated entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static CategoryLog createUpdatedEntity(EntityManager em) {
//        CategoryLog categoryLog = new CategoryLog()
//            .name(UPDATED_NAME)
////            .countChanellInCategory(UPDATED_COUNT_CHANELL_IN_CATEGORY)
////            .isDelete(UPDATED_IS_DELETE)
////            .date1(UPDATED_DATE_1)
////            .date2(UPDATED_DATE_2)
////            .long1(UPDATED_LONG_1)
////            .string1(UPDATED_STRING_1)
//            .boolean1(UPDATED_BOOLEAN_1);
//        return categoryLog;
//    }
//
//    @BeforeEach
//    public void initTest() {
//        categoryLog = createEntity(em);
//    }
//
//    @Test
//    @Transactional
//    void createCategoryLog() throws Exception {
//        int databaseSizeBeforeCreate = categoryLogRepository.findAll().size();
//        // Create the CategoryLog
//        restCategoryLogMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(categoryLog)))
//            .andExpect(status().isCreated());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeCreate + 1);
//        CategoryLog testCategoryLog = categoryLogList.get(categoryLogList.size() - 1);
//        assertThat(testCategoryLog.getName()).isEqualTo(DEFAULT_NAME);
//        assertThat(testCategoryLog.getCountChanellInCategory()).isEqualTo(DEFAULT_COUNT_CHANELL_IN_CATEGORY);
//        assertThat(testCategoryLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
//        assertThat(testCategoryLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
//        assertThat(testCategoryLog.getDate2()).isEqualTo(DEFAULT_DATE_2);
//        assertThat(testCategoryLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
//        assertThat(testCategoryLog.getString1()).isEqualTo(DEFAULT_STRING_1);
//        assertThat(testCategoryLog.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
//    }
//
//    @Test
//    @Transactional
//    void createCategoryLogWithExistingId() throws Exception {
//        // Create the CategoryLog with an existing ID
//        categoryLog.setId(1L);
//
//        int databaseSizeBeforeCreate = categoryLogRepository.findAll().size();
//
//        // An entity with an existing ID cannot be created, so this API call must fail
//        restCategoryLogMockMvc
//            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(categoryLog)))
//            .andExpect(status().isBadRequest());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeCreate);
//    }
//
//    @Test
//    @Transactional
//    void getAllCategoryLogs() throws Exception {
//        // Initialize the database
//        categoryLogRepository.saveAndFlush(categoryLog);
//
//        // Get all the categoryLogList
//        restCategoryLogMockMvc
//            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.[*].id").value(hasItem(categoryLog.getId().intValue())))
//            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
//            .andExpect(jsonPath("$.[*].countChanellInCategory").value(hasItem(DEFAULT_COUNT_CHANELL_IN_CATEGORY.intValue())))
//            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE.booleanValue())))
//            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
//            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
//            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
//            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
//            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
//    }
//
//    @Test
//    @Transactional
//    void getCategoryLog() throws Exception {
//        // Initialize the database
//        categoryLogRepository.saveAndFlush(categoryLog);
//
//        // Get the categoryLog
//        restCategoryLogMockMvc
//            .perform(get(ENTITY_API_URL_ID, categoryLog.getId()))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(jsonPath("$.id").value(categoryLog.getId().intValue()))
//            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
//            .andExpect(jsonPath("$.countChanellInCategory").value(DEFAULT_COUNT_CHANELL_IN_CATEGORY.intValue()))
//            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE.booleanValue()))
//            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
//            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
//            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
//            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
//            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
//    }
//
//    @Test
//    @Transactional
//    void getNonExistingCategoryLog() throws Exception {
//        // Get the categoryLog
//        restCategoryLogMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void putNewCategoryLog() throws Exception {
//        // Initialize the database
//        categoryLogRepository.saveAndFlush(categoryLog);
//
//        int databaseSizeBeforeUpdate = categoryLogRepository.findAll().size();
//
//        // Update the categoryLog
//        CategoryLog updatedCategoryLog = categoryLogRepository.findById(categoryLog.getId()).get();
//        // Disconnect from session so that the updates on updatedCategoryLog are not directly saved in db
//        em.detach(updatedCategoryLog);
//        updatedCategoryLog
//            .name(UPDATED_NAME)
//            .countChanellInCategory(UPDATED_COUNT_CHANELL_IN_CATEGORY)
//            .isDelete(UPDATED_IS_DELETE)
//            .date1(UPDATED_DATE_1)
//            .date2(UPDATED_DATE_2)
//            .long1(UPDATED_LONG_1)
//            .string1(UPDATED_STRING_1)
//            .boolean1(UPDATED_BOOLEAN_1);
//
//        restCategoryLogMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, updatedCategoryLog.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(updatedCategoryLog))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeUpdate);
//        CategoryLog testCategoryLog = categoryLogList.get(categoryLogList.size() - 1);
//        assertThat(testCategoryLog.getName()).isEqualTo(UPDATED_NAME);
//        assertThat(testCategoryLog.getCountChanellInCategory()).isEqualTo(UPDATED_COUNT_CHANELL_IN_CATEGORY);
//        assertThat(testCategoryLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
//        assertThat(testCategoryLog.getDate1()).isEqualTo(UPDATED_DATE_1);
//        assertThat(testCategoryLog.getDate2()).isEqualTo(UPDATED_DATE_2);
//        assertThat(testCategoryLog.getLong1()).isEqualTo(UPDATED_LONG_1);
//        assertThat(testCategoryLog.getString1()).isEqualTo(UPDATED_STRING_1);
//        assertThat(testCategoryLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
//    }
//
//    @Test
//    @Transactional
//    void putNonExistingCategoryLog() throws Exception {
//        int databaseSizeBeforeUpdate = categoryLogRepository.findAll().size();
//        categoryLog.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restCategoryLogMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, categoryLog.getId())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(categoryLog))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithIdMismatchCategoryLog() throws Exception {
//        int databaseSizeBeforeUpdate = categoryLogRepository.findAll().size();
//        categoryLog.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restCategoryLogMockMvc
//            .perform(
//                put(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(TestUtil.convertObjectToJsonBytes(categoryLog))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void putWithMissingIdPathParamCategoryLog() throws Exception {
//        int databaseSizeBeforeUpdate = categoryLogRepository.findAll().size();
//        categoryLog.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restCategoryLogMockMvc
//            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(categoryLog)))
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void partialUpdateCategoryLogWithPatch() throws Exception {
//        // Initialize the database
//        categoryLogRepository.saveAndFlush(categoryLog);
//
//        int databaseSizeBeforeUpdate = categoryLogRepository.findAll().size();
//
//        // Update the categoryLog using partial update
//        CategoryLog partialUpdatedCategoryLog = new CategoryLog();
//        partialUpdatedCategoryLog.setId(categoryLog.getId());
//
//        partialUpdatedCategoryLog
//            .countChanellInCategory(UPDATED_COUNT_CHANELL_IN_CATEGORY)
//            .date2(UPDATED_DATE_2)
//            .boolean1(UPDATED_BOOLEAN_1);
//
//        restCategoryLogMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedCategoryLog.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCategoryLog))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeUpdate);
//        CategoryLog testCategoryLog = categoryLogList.get(categoryLogList.size() - 1);
//        assertThat(testCategoryLog.getName()).isEqualTo(DEFAULT_NAME);
//        assertThat(testCategoryLog.getCountChanellInCategory()).isEqualTo(UPDATED_COUNT_CHANELL_IN_CATEGORY);
//        assertThat(testCategoryLog.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
//        assertThat(testCategoryLog.getDate1()).isEqualTo(DEFAULT_DATE_1);
//        assertThat(testCategoryLog.getDate2()).isEqualTo(UPDATED_DATE_2);
//        assertThat(testCategoryLog.getLong1()).isEqualTo(DEFAULT_LONG_1);
//        assertThat(testCategoryLog.getString1()).isEqualTo(DEFAULT_STRING_1);
//        assertThat(testCategoryLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
//    }
//
//    @Test
//    @Transactional
//    void fullUpdateCategoryLogWithPatch() throws Exception {
//        // Initialize the database
//        categoryLogRepository.saveAndFlush(categoryLog);
//
//        int databaseSizeBeforeUpdate = categoryLogRepository.findAll().size();
//
//        // Update the categoryLog using partial update
//        CategoryLog partialUpdatedCategoryLog = new CategoryLog();
//        partialUpdatedCategoryLog.setId(categoryLog.getId());
//
//        partialUpdatedCategoryLog
//            .name(UPDATED_NAME)
//            .countChanellInCategory(UPDATED_COUNT_CHANELL_IN_CATEGORY)
//            .isDelete(UPDATED_IS_DELETE)
//            .date1(UPDATED_DATE_1)
//            .date2(UPDATED_DATE_2)
//            .long1(UPDATED_LONG_1)
//            .string1(UPDATED_STRING_1)
//            .boolean1(UPDATED_BOOLEAN_1);
//
//        restCategoryLogMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, partialUpdatedCategoryLog.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCategoryLog))
//            )
//            .andExpect(status().isOk());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeUpdate);
//        CategoryLog testCategoryLog = categoryLogList.get(categoryLogList.size() - 1);
//        assertThat(testCategoryLog.getName()).isEqualTo(UPDATED_NAME);
//        assertThat(testCategoryLog.getCountChanellInCategory()).isEqualTo(UPDATED_COUNT_CHANELL_IN_CATEGORY);
//        assertThat(testCategoryLog.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
//        assertThat(testCategoryLog.getDate1()).isEqualTo(UPDATED_DATE_1);
//        assertThat(testCategoryLog.getDate2()).isEqualTo(UPDATED_DATE_2);
//        assertThat(testCategoryLog.getLong1()).isEqualTo(UPDATED_LONG_1);
//        assertThat(testCategoryLog.getString1()).isEqualTo(UPDATED_STRING_1);
//        assertThat(testCategoryLog.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
//    }
//
//    @Test
//    @Transactional
//    void patchNonExistingCategoryLog() throws Exception {
//        int databaseSizeBeforeUpdate = categoryLogRepository.findAll().size();
//        categoryLog.setId(count.incrementAndGet());
//
//        // If the entity doesn't have an ID, it will throw BadRequestAlertException
//        restCategoryLogMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, categoryLog.getId())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(categoryLog))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithIdMismatchCategoryLog() throws Exception {
//        int databaseSizeBeforeUpdate = categoryLogRepository.findAll().size();
//        categoryLog.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restCategoryLogMockMvc
//            .perform(
//                patch(ENTITY_API_URL_ID, count.incrementAndGet())
//                    .contentType("application/merge-patch+json")
//                    .content(TestUtil.convertObjectToJsonBytes(categoryLog))
//            )
//            .andExpect(status().isBadRequest());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void patchWithMissingIdPathParamCategoryLog() throws Exception {
//        int databaseSizeBeforeUpdate = categoryLogRepository.findAll().size();
//        categoryLog.setId(count.incrementAndGet());
//
//        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
//        restCategoryLogMockMvc
//            .perform(
//                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(categoryLog))
//            )
//            .andExpect(status().isMethodNotAllowed());
//
//        // Validate the CategoryLog in the database
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeUpdate);
//    }
//
//    @Test
//    @Transactional
//    void deleteCategoryLog() throws Exception {
//        // Initialize the database
//        categoryLogRepository.saveAndFlush(categoryLog);
//
//        int databaseSizeBeforeDelete = categoryLogRepository.findAll().size();
//
//        // Delete the categoryLog
//        restCategoryLogMockMvc
//            .perform(delete(ENTITY_API_URL_ID, categoryLog.getId()).accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isNoContent());
//
//        // Validate the database contains one less item
//        List<CategoryLog> categoryLogList = categoryLogRepository.findAll();
//        assertThat(categoryLogList).hasSize(databaseSizeBeforeDelete - 1);
//    }
//}
