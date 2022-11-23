package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LinksByCategoryInTopLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LinksByCategoryInTopLog.class);
        LinksByCategoryInTopLog linksByCategoryInTopLog1 = new LinksByCategoryInTopLog();
        linksByCategoryInTopLog1.setId(1L);
        LinksByCategoryInTopLog linksByCategoryInTopLog2 = new LinksByCategoryInTopLog();
        linksByCategoryInTopLog2.setId(linksByCategoryInTopLog1.getId());
        assertThat(linksByCategoryInTopLog1).isEqualTo(linksByCategoryInTopLog2);
        linksByCategoryInTopLog2.setId(2L);
        assertThat(linksByCategoryInTopLog1).isNotEqualTo(linksByCategoryInTopLog2);
        linksByCategoryInTopLog1.setId(null);
        assertThat(linksByCategoryInTopLog1).isNotEqualTo(linksByCategoryInTopLog2);
    }
}
