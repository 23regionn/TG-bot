package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RelCategoryCityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelCategoryCity.class);
        RelCategoryCity relCategoryCity1 = new RelCategoryCity();
        relCategoryCity1.setId(1L);
        RelCategoryCity relCategoryCity2 = new RelCategoryCity();
        relCategoryCity2.setId(relCategoryCity1.getId());
        assertThat(relCategoryCity1).isEqualTo(relCategoryCity2);
        relCategoryCity2.setId(2L);
        assertThat(relCategoryCity1).isNotEqualTo(relCategoryCity2);
        relCategoryCity1.setId(null);
        assertThat(relCategoryCity1).isNotEqualTo(relCategoryCity2);
    }
}
