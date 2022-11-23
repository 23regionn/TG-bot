package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OfferFromCostumersTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferFromCostumers.class);
        OfferFromCostumers offerFromCostumers1 = new OfferFromCostumers();
        offerFromCostumers1.setId(1L);
        OfferFromCostumers offerFromCostumers2 = new OfferFromCostumers();
        offerFromCostumers2.setId(offerFromCostumers1.getId());
        assertThat(offerFromCostumers1).isEqualTo(offerFromCostumers2);
        offerFromCostumers2.setId(2L);
        assertThat(offerFromCostumers1).isNotEqualTo(offerFromCostumers2);
        offerFromCostumers1.setId(null);
        assertThat(offerFromCostumers1).isNotEqualTo(offerFromCostumers2);
    }
}
