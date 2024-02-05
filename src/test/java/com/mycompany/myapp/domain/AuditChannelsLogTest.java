package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AuditChannelsLogTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuditChannelsLog.class);
        AuditChannelsLog auditChannelsLog1 = new AuditChannelsLog();
        auditChannelsLog1.setId(1L);
        AuditChannelsLog auditChannelsLog2 = new AuditChannelsLog();
        auditChannelsLog2.setId(auditChannelsLog1.getId());
        assertThat(auditChannelsLog1).isEqualTo(auditChannelsLog2);
        auditChannelsLog2.setId(2L);
        assertThat(auditChannelsLog1).isNotEqualTo(auditChannelsLog2);
        auditChannelsLog1.setId(null);
        assertThat(auditChannelsLog1).isNotEqualTo(auditChannelsLog2);
    }
}
