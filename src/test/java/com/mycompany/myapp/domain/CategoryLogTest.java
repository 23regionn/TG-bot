package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CategoryLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CategoryLog.class);
        CategoryLog categoryLog1 = new CategoryLog();
        categoryLog1.setId(1L);
        CategoryLog categoryLog2 = new CategoryLog();
        categoryLog2.setId(categoryLog1.getId());
        assertThat(categoryLog1).isEqualTo(categoryLog2);
        categoryLog2.setId(2L);
        assertThat(categoryLog1).isNotEqualTo(categoryLog2);
        categoryLog1.setId(null);
        assertThat(categoryLog1).isNotEqualTo(categoryLog2);
    }
}
