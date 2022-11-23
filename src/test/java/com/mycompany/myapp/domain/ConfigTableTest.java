package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ConfigTableTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConfigTable.class);
        ConfigTable configTable1 = new ConfigTable();
        configTable1.setId(1L);
        ConfigTable configTable2 = new ConfigTable();
        configTable2.setId(configTable1.getId());
        assertThat(configTable1).isEqualTo(configTable2);
        configTable2.setId(2L);
        assertThat(configTable1).isNotEqualTo(configTable2);
        configTable1.setId(null);
        assertThat(configTable1).isNotEqualTo(configTable2);
    }
}
