package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OfferFromCostumersLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OfferFromCostumersLog.class);
        OfferFromCostumersLog offerFromCostumersLog1 = new OfferFromCostumersLog();
        offerFromCostumersLog1.setId(1L);
        OfferFromCostumersLog offerFromCostumersLog2 = new OfferFromCostumersLog();
        offerFromCostumersLog2.setId(offerFromCostumersLog1.getId());
        assertThat(offerFromCostumersLog1).isEqualTo(offerFromCostumersLog2);
        offerFromCostumersLog2.setId(2L);
        assertThat(offerFromCostumersLog1).isNotEqualTo(offerFromCostumersLog2);
        offerFromCostumersLog1.setId(null);
        assertThat(offerFromCostumersLog1).isNotEqualTo(offerFromCostumersLog2);
    }
}
