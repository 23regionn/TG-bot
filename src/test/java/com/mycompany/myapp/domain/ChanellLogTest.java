package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChanellLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChanellLog.class);
        ChanellLog chanellLog1 = new ChanellLog();
        chanellLog1.setId(1L);
        ChanellLog chanellLog2 = new ChanellLog();
        chanellLog2.setId(chanellLog1.getId());
        assertThat(chanellLog1).isEqualTo(chanellLog2);
        chanellLog2.setId(2L);
        assertThat(chanellLog1).isNotEqualTo(chanellLog2);
        chanellLog1.setId(null);
        assertThat(chanellLog1).isNotEqualTo(chanellLog2);
    }
}
