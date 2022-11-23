package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BalanceLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BalanceLog.class);
        BalanceLog balanceLog1 = new BalanceLog();
        balanceLog1.setId(1L);
        BalanceLog balanceLog2 = new BalanceLog();
        balanceLog2.setId(balanceLog1.getId());
        assertThat(balanceLog1).isEqualTo(balanceLog2);
        balanceLog2.setId(2L);
        assertThat(balanceLog1).isNotEqualTo(balanceLog2);
        balanceLog1.setId(null);
        assertThat(balanceLog1).isNotEqualTo(balanceLog2);
    }
}
