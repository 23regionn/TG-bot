package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SearchTypeLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SearchTypeLog.class);
        SearchTypeLog searchTypeLog1 = new SearchTypeLog();
        searchTypeLog1.setId(1L);
        SearchTypeLog searchTypeLog2 = new SearchTypeLog();
        searchTypeLog2.setId(searchTypeLog1.getId());
        assertThat(searchTypeLog1).isEqualTo(searchTypeLog2);
        searchTypeLog2.setId(2L);
        assertThat(searchTypeLog1).isNotEqualTo(searchTypeLog2);
        searchTypeLog1.setId(null);
        assertThat(searchTypeLog1).isNotEqualTo(searchTypeLog2);
    }
}
