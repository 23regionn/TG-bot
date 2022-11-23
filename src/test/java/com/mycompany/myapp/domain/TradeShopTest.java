package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TradeShopTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradeShop.class);
        TradeShop tradeShop1 = new TradeShop();
        tradeShop1.setId(1L);
        TradeShop tradeShop2 = new TradeShop();
        tradeShop2.setId(tradeShop1.getId());
        assertThat(tradeShop1).isEqualTo(tradeShop2);
        tradeShop2.setId(2L);
        assertThat(tradeShop1).isNotEqualTo(tradeShop2);
        tradeShop1.setId(null);
        assertThat(tradeShop1).isNotEqualTo(tradeShop2);
    }
}
