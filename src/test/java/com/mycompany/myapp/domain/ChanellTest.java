package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChanellTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chanell.class);
        Chanell chanell1 = new Chanell();
        chanell1.setId(1L);
        Chanell chanell2 = new Chanell();
        chanell2.setId(chanell1.getId());
        assertThat(chanell1).isEqualTo(chanell2);
        chanell2.setId(2L);
        assertThat(chanell1).isNotEqualTo(chanell2);
        chanell1.setId(null);
        assertThat(chanell1).isNotEqualTo(chanell2);
    }
}
