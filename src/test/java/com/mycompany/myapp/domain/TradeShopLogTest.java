package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TradeShopLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradeShopLog.class);
        TradeShopLog tradeShopLog1 = new TradeShopLog();
        tradeShopLog1.setId(1L);
        TradeShopLog tradeShopLog2 = new TradeShopLog();
        tradeShopLog2.setId(tradeShopLog1.getId());
        assertThat(tradeShopLog1).isEqualTo(tradeShopLog2);
        tradeShopLog2.setId(2L);
        assertThat(tradeShopLog1).isNotEqualTo(tradeShopLog2);
        tradeShopLog1.setId(null);
        assertThat(tradeShopLog1).isNotEqualTo(tradeShopLog2);
    }
}
