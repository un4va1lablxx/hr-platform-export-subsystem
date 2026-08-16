package export.organization.entities;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class OrganizationEntityTest {

    @Test
    void organizationEntity_ShouldWorkCorrectly() {
        // Arrange
        UUID orgId = UUID.randomUUID();
        Organization org = new Organization();
        org.setId(orgId);
        org.setName("Test Org");
        org.setType(WorkType.EXECUTOR);
        org.setStatus(RegistrationStatus.APPROVED);
        LegalProfile legalProfile = new LegalProfile();
        legalProfile.setInn("1234567890");
        legalProfile.setOgrn("1234567890123");
        legalProfile.setLegalAddress("Test Address");
        legalProfile.setTypeLegalEntity(LegalType.OOO);
        org.setLegalProfile(legalProfile);

        // Act & Assert
        assertEquals(orgId, org.getId());
        assertEquals("Test Org", org.getName());
        assertEquals(WorkType.EXECUTOR, org.getType());
        assertEquals(RegistrationStatus.APPROVED, org.getStatus());
        assertNotNull(org.getLegalProfile());
        assertEquals("1234567890", org.getLegalProfile().getInn());
        assertEquals(LegalType.OOO, org.getLegalProfile().getTypeLegalEntity());
    }

    @Test
    void equalsAndHashCode_ShouldWorkCorrectly() {
        // Arrange
        UUID orgId = UUID.randomUUID();
        Organization org1 = new Organization();
        org1.setId(orgId);
        Organization org2 = new Organization();
        org2.setId(orgId);
        Organization org3 = new Organization();
        org3.setId(UUID.randomUUID());

        // Act & Assert
        assertEquals(org1, org2);
        assertNotEquals(org1, org3);
        assertEquals(org1.hashCode(), org2.hashCode());
        assertNotEquals(org1.hashCode(), org3.hashCode());
    }
}