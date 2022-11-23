package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LinksByCategoryInTopTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LinksByCategoryInTop.class);
        LinksByCategoryInTop linksByCategoryInTop1 = new LinksByCategoryInTop();
        linksByCategoryInTop1.setId(1L);
        LinksByCategoryInTop linksByCategoryInTop2 = new LinksByCategoryInTop();
        linksByCategoryInTop2.setId(linksByCategoryInTop1.getId());
        assertThat(linksByCategoryInTop1).isEqualTo(linksByCategoryInTop2);
        linksByCategoryInTop2.setId(2L);
        assertThat(linksByCategoryInTop1).isNotEqualTo(linksByCategoryInTop2);
        linksByCategoryInTop1.setId(null);
        assertThat(linksByCategoryInTop1).isNotEqualTo(linksByCategoryInTop2);
    }
}
