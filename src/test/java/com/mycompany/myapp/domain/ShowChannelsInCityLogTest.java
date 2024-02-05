package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ShowChannelsInCityLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShowChannelsInCityLog.class);
        ShowChannelsInCityLog showChannelsInCityLog1 = new ShowChannelsInCityLog();
        showChannelsInCityLog1.setId(1L);
        ShowChannelsInCityLog showChannelsInCityLog2 = new ShowChannelsInCityLog();
        showChannelsInCityLog2.setId(showChannelsInCityLog1.getId());
        assertThat(showChannelsInCityLog1).isEqualTo(showChannelsInCityLog2);
        showChannelsInCityLog2.setId(2L);
        assertThat(showChannelsInCityLog1).isNotEqualTo(showChannelsInCityLog2);
        showChannelsInCityLog1.setId(null);
        assertThat(showChannelsInCityLog1).isNotEqualTo(showChannelsInCityLog2);
    }
}
