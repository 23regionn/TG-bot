package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ShowChannelsInCategoryLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShowChannelsInCategoryLog.class);
        ShowChannelsInCategoryLog showChannelsInCategoryLog1 = new ShowChannelsInCategoryLog();
        showChannelsInCategoryLog1.setId(1L);
        ShowChannelsInCategoryLog showChannelsInCategoryLog2 = new ShowChannelsInCategoryLog();
        showChannelsInCategoryLog2.setId(showChannelsInCategoryLog1.getId());
        assertThat(showChannelsInCategoryLog1).isEqualTo(showChannelsInCategoryLog2);
        showChannelsInCategoryLog2.setId(2L);
        assertThat(showChannelsInCategoryLog1).isNotEqualTo(showChannelsInCategoryLog2);
        showChannelsInCategoryLog1.setId(null);
        assertThat(showChannelsInCategoryLog1).isNotEqualTo(showChannelsInCategoryLog2);
    }
}
