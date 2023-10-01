package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RelCategoryChannelsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelCategoryChannels.class);
        RelCategoryChannels relCategoryChannels1 = new RelCategoryChannels();
        relCategoryChannels1.setId(1L);
        RelCategoryChannels relCategoryChannels2 = new RelCategoryChannels();
        relCategoryChannels2.setId(relCategoryChannels1.getId());
        assertThat(relCategoryChannels1).isEqualTo(relCategoryChannels2);
        relCategoryChannels2.setId(2L);
        assertThat(relCategoryChannels1).isNotEqualTo(relCategoryChannels2);
        relCategoryChannels1.setId(null);
        assertThat(relCategoryChannels1).isNotEqualTo(relCategoryChannels2);
    }
}
