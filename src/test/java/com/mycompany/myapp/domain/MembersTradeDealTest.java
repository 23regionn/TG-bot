package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MembersTradeDealTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MembersTradeDeal.class);
        MembersTradeDeal membersTradeDeal1 = new MembersTradeDeal();
        membersTradeDeal1.setId(1L);
        MembersTradeDeal membersTradeDeal2 = new MembersTradeDeal();
        membersTradeDeal2.setId(membersTradeDeal1.getId());
        assertThat(membersTradeDeal1).isEqualTo(membersTradeDeal2);
        membersTradeDeal2.setId(2L);
        assertThat(membersTradeDeal1).isNotEqualTo(membersTradeDeal2);
        membersTradeDeal1.setId(null);
        assertThat(membersTradeDeal1).isNotEqualTo(membersTradeDeal2);
    }
}
