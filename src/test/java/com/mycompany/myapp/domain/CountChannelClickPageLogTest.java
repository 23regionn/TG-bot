package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CountChannelClickPageLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CountChannelClickPageLog.class);
        CountChannelClickPageLog countChannelClickPageLog1 = new CountChannelClickPageLog();
        countChannelClickPageLog1.setId(1L);
        CountChannelClickPageLog countChannelClickPageLog2 = new CountChannelClickPageLog();
        countChannelClickPageLog2.setId(countChannelClickPageLog1.getId());
        assertThat(countChannelClickPageLog1).isEqualTo(countChannelClickPageLog2);
        countChannelClickPageLog2.setId(2L);
        assertThat(countChannelClickPageLog1).isNotEqualTo(countChannelClickPageLog2);
        countChannelClickPageLog1.setId(null);
        assertThat(countChannelClickPageLog1).isNotEqualTo(countChannelClickPageLog2);
    }
}
