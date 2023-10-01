package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RelCategoryCityChannelsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelCategoryCityChannels.class);
        RelCategoryCityChannels relCategoryCityChannels1 = new RelCategoryCityChannels();
        relCategoryCityChannels1.setId(1L);
        RelCategoryCityChannels relCategoryCityChannels2 = new RelCategoryCityChannels();
        relCategoryCityChannels2.setId(relCategoryCityChannels1.getId());
        assertThat(relCategoryCityChannels1).isEqualTo(relCategoryCityChannels2);
        relCategoryCityChannels2.setId(2L);
        assertThat(relCategoryCityChannels1).isNotEqualTo(relCategoryCityChannels2);
        relCategoryCityChannels1.setId(null);
        assertThat(relCategoryCityChannels1).isNotEqualTo(relCategoryCityChannels2);
    }
}
