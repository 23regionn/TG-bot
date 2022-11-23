package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Review;
import com.mycompany.myapp.repository.ReviewRepository;
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
 * Integration tests for the {@link ReviewResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReviewResourceIT {

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final Long DEFAULT_USER_ID = 1L;
    private static final Long UPDATED_USER_ID = 2L;

    private static final String DEFAULT_LINK_TO_SALLER = "AAAAAAAAAA";
    private static final String UPDATED_LINK_TO_SALLER = "BBBBBBBBBB";

    private static final Long DEFAULT_ADMIN_ID = 1L;
    private static final Long UPDATED_ADMIN_ID = 2L;

    private static final Boolean DEFAULT_IS_NEGATIVE = false;
    private static final Boolean UPDATED_IS_NEGATIVE = true;

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

    private static final String ENTITY_API_URL = "/api/reviews";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReviewMockMvc;

    private Review review;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Review createEntity(EntityManager em) {
        Review review = new Review()
            .isActive(DEFAULT_IS_ACTIVE)
            .text(DEFAULT_TEXT)
            .userId(DEFAULT_USER_ID)
            .linkToSaller(DEFAULT_LINK_TO_SALLER)
            .adminId(DEFAULT_ADMIN_ID)
            .isNegative(DEFAULT_IS_NEGATIVE)
            .isDelete(DEFAULT_IS_DELETE)
            .date1(DEFAULT_DATE_1)
            .date2(DEFAULT_DATE_2)
            .long1(DEFAULT_LONG_1)
            .string1(DEFAULT_STRING_1)
            .boolean1(DEFAULT_BOOLEAN_1);
        return review;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Review createUpdatedEntity(EntityManager em) {
        Review review = new Review()
            .isActive(UPDATED_IS_ACTIVE)
            .text(UPDATED_TEXT)
            .userId(UPDATED_USER_ID)
            .linkToSaller(UPDATED_LINK_TO_SALLER)
            .adminId(UPDATED_ADMIN_ID)
            .isNegative(UPDATED_IS_NEGATIVE)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);
        return review;
    }

    @BeforeEach
    public void initTest() {
        review = createEntity(em);
    }

    @Test
    @Transactional
    void createReview() throws Exception {
        int databaseSizeBeforeCreate = reviewRepository.findAll().size();
        // Create the Review
        restReviewMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(review)))
            .andExpect(status().isCreated());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeCreate + 1);
        Review testReview = reviewList.get(reviewList.size() - 1);
        assertThat(testReview.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testReview.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testReview.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testReview.getLinkToSaller()).isEqualTo(DEFAULT_LINK_TO_SALLER);
        assertThat(testReview.getAdminId()).isEqualTo(DEFAULT_ADMIN_ID);
        assertThat(testReview.getIsNegative()).isEqualTo(DEFAULT_IS_NEGATIVE);
        assertThat(testReview.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testReview.getDate1()).isEqualTo(DEFAULT_DATE_1);
        assertThat(testReview.getDate2()).isEqualTo(DEFAULT_DATE_2);
        assertThat(testReview.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testReview.getString1()).isEqualTo(DEFAULT_STRING_1);
        assertThat(testReview.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void createReviewWithExistingId() throws Exception {
        // Create the Review with an existing ID
        review.setId(1L);

        int databaseSizeBeforeCreate = reviewRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReviewMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(review)))
            .andExpect(status().isBadRequest());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllReviews() throws Exception {
        // Initialize the database
        reviewRepository.saveAndFlush(review);

        // Get all the reviewList
        restReviewMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(review.getId().intValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].linkToSaller").value(hasItem(DEFAULT_LINK_TO_SALLER)))
            .andExpect(jsonPath("$.[*].adminId").value(hasItem(DEFAULT_ADMIN_ID.intValue())))
            .andExpect(jsonPath("$.[*].isNegative").value(hasItem(DEFAULT_IS_NEGATIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE.booleanValue())))
            .andExpect(jsonPath("$.[*].date1").value(hasItem(sameInstant(DEFAULT_DATE_1))))
            .andExpect(jsonPath("$.[*].date2").value(hasItem(sameInstant(DEFAULT_DATE_2))))
            .andExpect(jsonPath("$.[*].long1").value(hasItem(DEFAULT_LONG_1.intValue())))
            .andExpect(jsonPath("$.[*].string1").value(hasItem(DEFAULT_STRING_1)))
            .andExpect(jsonPath("$.[*].boolean1").value(hasItem(DEFAULT_BOOLEAN_1.booleanValue())));
    }

    @Test
    @Transactional
    void getReview() throws Exception {
        // Initialize the database
        reviewRepository.saveAndFlush(review);

        // Get the review
        restReviewMockMvc
            .perform(get(ENTITY_API_URL_ID, review.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(review.getId().intValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.linkToSaller").value(DEFAULT_LINK_TO_SALLER))
            .andExpect(jsonPath("$.adminId").value(DEFAULT_ADMIN_ID.intValue()))
            .andExpect(jsonPath("$.isNegative").value(DEFAULT_IS_NEGATIVE.booleanValue()))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE.booleanValue()))
            .andExpect(jsonPath("$.date1").value(sameInstant(DEFAULT_DATE_1)))
            .andExpect(jsonPath("$.date2").value(sameInstant(DEFAULT_DATE_2)))
            .andExpect(jsonPath("$.long1").value(DEFAULT_LONG_1.intValue()))
            .andExpect(jsonPath("$.string1").value(DEFAULT_STRING_1))
            .andExpect(jsonPath("$.boolean1").value(DEFAULT_BOOLEAN_1.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingReview() throws Exception {
        // Get the review
        restReviewMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewReview() throws Exception {
        // Initialize the database
        reviewRepository.saveAndFlush(review);

        int databaseSizeBeforeUpdate = reviewRepository.findAll().size();

        // Update the review
        Review updatedReview = reviewRepository.findById(review.getId()).get();
        // Disconnect from session so that the updates on updatedReview are not directly saved in db
        em.detach(updatedReview);
        updatedReview
            .isActive(UPDATED_IS_ACTIVE)
            .text(UPDATED_TEXT)
            .userId(UPDATED_USER_ID)
            .linkToSaller(UPDATED_LINK_TO_SALLER)
            .adminId(UPDATED_ADMIN_ID)
            .isNegative(UPDATED_IS_NEGATIVE)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restReviewMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReview.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedReview))
            )
            .andExpect(status().isOk());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeUpdate);
        Review testReview = reviewList.get(reviewList.size() - 1);
        assertThat(testReview.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testReview.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testReview.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testReview.getLinkToSaller()).isEqualTo(UPDATED_LINK_TO_SALLER);
        assertThat(testReview.getAdminId()).isEqualTo(UPDATED_ADMIN_ID);
        assertThat(testReview.getIsNegative()).isEqualTo(UPDATED_IS_NEGATIVE);
        assertThat(testReview.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testReview.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testReview.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testReview.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testReview.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testReview.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void putNonExistingReview() throws Exception {
        int databaseSizeBeforeUpdate = reviewRepository.findAll().size();
        review.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReviewMockMvc
            .perform(
                put(ENTITY_API_URL_ID, review.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(review))
            )
            .andExpect(status().isBadRequest());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReview() throws Exception {
        int databaseSizeBeforeUpdate = reviewRepository.findAll().size();
        review.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(review))
            )
            .andExpect(status().isBadRequest());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReview() throws Exception {
        int databaseSizeBeforeUpdate = reviewRepository.findAll().size();
        review.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(review)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReviewWithPatch() throws Exception {
        // Initialize the database
        reviewRepository.saveAndFlush(review);

        int databaseSizeBeforeUpdate = reviewRepository.findAll().size();

        // Update the review using partial update
        Review partialUpdatedReview = new Review();
        partialUpdatedReview.setId(review.getId());

        partialUpdatedReview
            .isActive(UPDATED_IS_ACTIVE)
            .text(UPDATED_TEXT)
            .linkToSaller(UPDATED_LINK_TO_SALLER)
            .isNegative(UPDATED_IS_NEGATIVE)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .string1(UPDATED_STRING_1);

        restReviewMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReview.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReview))
            )
            .andExpect(status().isOk());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeUpdate);
        Review testReview = reviewList.get(reviewList.size() - 1);
        assertThat(testReview.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testReview.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testReview.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testReview.getLinkToSaller()).isEqualTo(UPDATED_LINK_TO_SALLER);
        assertThat(testReview.getAdminId()).isEqualTo(DEFAULT_ADMIN_ID);
        assertThat(testReview.getIsNegative()).isEqualTo(UPDATED_IS_NEGATIVE);
        assertThat(testReview.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testReview.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testReview.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testReview.getLong1()).isEqualTo(DEFAULT_LONG_1);
        assertThat(testReview.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testReview.getBoolean1()).isEqualTo(DEFAULT_BOOLEAN_1);
    }

    @Test
    @Transactional
    void fullUpdateReviewWithPatch() throws Exception {
        // Initialize the database
        reviewRepository.saveAndFlush(review);

        int databaseSizeBeforeUpdate = reviewRepository.findAll().size();

        // Update the review using partial update
        Review partialUpdatedReview = new Review();
        partialUpdatedReview.setId(review.getId());

        partialUpdatedReview
            .isActive(UPDATED_IS_ACTIVE)
            .text(UPDATED_TEXT)
            .userId(UPDATED_USER_ID)
            .linkToSaller(UPDATED_LINK_TO_SALLER)
            .adminId(UPDATED_ADMIN_ID)
            .isNegative(UPDATED_IS_NEGATIVE)
            .isDelete(UPDATED_IS_DELETE)
            .date1(UPDATED_DATE_1)
            .date2(UPDATED_DATE_2)
            .long1(UPDATED_LONG_1)
            .string1(UPDATED_STRING_1)
            .boolean1(UPDATED_BOOLEAN_1);

        restReviewMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReview.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedReview))
            )
            .andExpect(status().isOk());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeUpdate);
        Review testReview = reviewList.get(reviewList.size() - 1);
        assertThat(testReview.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testReview.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testReview.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testReview.getLinkToSaller()).isEqualTo(UPDATED_LINK_TO_SALLER);
        assertThat(testReview.getAdminId()).isEqualTo(UPDATED_ADMIN_ID);
        assertThat(testReview.getIsNegative()).isEqualTo(UPDATED_IS_NEGATIVE);
        assertThat(testReview.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testReview.getDate1()).isEqualTo(UPDATED_DATE_1);
        assertThat(testReview.getDate2()).isEqualTo(UPDATED_DATE_2);
        assertThat(testReview.getLong1()).isEqualTo(UPDATED_LONG_1);
        assertThat(testReview.getString1()).isEqualTo(UPDATED_STRING_1);
        assertThat(testReview.getBoolean1()).isEqualTo(UPDATED_BOOLEAN_1);
    }

    @Test
    @Transactional
    void patchNonExistingReview() throws Exception {
        int databaseSizeBeforeUpdate = reviewRepository.findAll().size();
        review.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReviewMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, review.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(review))
            )
            .andExpect(status().isBadRequest());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReview() throws Exception {
        int databaseSizeBeforeUpdate = reviewRepository.findAll().size();
        review.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(review))
            )
            .andExpect(status().isBadRequest());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReview() throws Exception {
        int databaseSizeBeforeUpdate = reviewRepository.findAll().size();
        review.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReviewMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(review)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Review in the database
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReview() throws Exception {
        // Initialize the database
        reviewRepository.saveAndFlush(review);

        int databaseSizeBeforeDelete = reviewRepository.findAll().size();

        // Delete the review
        restReviewMockMvc
            .perform(delete(ENTITY_API_URL_ID, review.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
