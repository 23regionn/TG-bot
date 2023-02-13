package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EditChannelsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EditChannels.class);
        EditChannels editChannels1 = new EditChannels();
        editChannels1.setId(1L);
        EditChannels editChannels2 = new EditChannels();
        editChannels2.setId(editChannels1.getId());
        assertThat(editChannels1).isEqualTo(editChannels2);
        editChannels2.setId(2L);
        assertThat(editChannels1).isNotEqualTo(editChannels2);
        editChannels1.setId(null);
        assertThat(editChannels1).isNotEqualTo(editChannels2);
    }
}
