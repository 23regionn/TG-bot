package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TGUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TGUser.class);
        TGUser tGUser1 = new TGUser();
        tGUser1.setId(1L);
        TGUser tGUser2 = new TGUser();
        tGUser2.setId(tGUser1.getId());
        assertThat(tGUser1).isEqualTo(tGUser2);
        tGUser2.setId(2L);
        assertThat(tGUser1).isNotEqualTo(tGUser2);
        tGUser1.setId(null);
        assertThat(tGUser1).isNotEqualTo(tGUser2);
    }
}
