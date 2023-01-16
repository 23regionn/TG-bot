package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MessegePannelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MessegePannel.class);
        MessegePannel messegePannel1 = new MessegePannel();
        messegePannel1.setId(1L);
        MessegePannel messegePannel2 = new MessegePannel();
        messegePannel2.setId(messegePannel1.getId());
        assertThat(messegePannel1).isEqualTo(messegePannel2);
        messegePannel2.setId(2L);
        assertThat(messegePannel1).isNotEqualTo(messegePannel2);
        messegePannel1.setId(null);
        assertThat(messegePannel1).isNotEqualTo(messegePannel2);
    }
}
