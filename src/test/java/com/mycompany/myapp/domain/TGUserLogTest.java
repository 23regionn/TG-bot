package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TGUserLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TGUserLog.class);
        TGUserLog tGUserLog1 = new TGUserLog();
        tGUserLog1.setId(1L);
        TGUserLog tGUserLog2 = new TGUserLog();
        tGUserLog2.setId(tGUserLog1.getId());
        assertThat(tGUserLog1).isEqualTo(tGUserLog2);
        tGUserLog2.setId(2L);
        assertThat(tGUserLog1).isNotEqualTo(tGUserLog2);
        tGUserLog1.setId(null);
        assertThat(tGUserLog1).isNotEqualTo(tGUserLog2);
    }
}
