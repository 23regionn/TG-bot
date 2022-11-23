package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MembersTradeDealLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MembersTradeDealLog.class);
        MembersTradeDealLog membersTradeDealLog1 = new MembersTradeDealLog();
        membersTradeDealLog1.setId(1L);
        MembersTradeDealLog membersTradeDealLog2 = new MembersTradeDealLog();
        membersTradeDealLog2.setId(membersTradeDealLog1.getId());
        assertThat(membersTradeDealLog1).isEqualTo(membersTradeDealLog2);
        membersTradeDealLog2.setId(2L);
        assertThat(membersTradeDealLog1).isNotEqualTo(membersTradeDealLog2);
        membersTradeDealLog1.setId(null);
        assertThat(membersTradeDealLog1).isNotEqualTo(membersTradeDealLog2);
    }
}
