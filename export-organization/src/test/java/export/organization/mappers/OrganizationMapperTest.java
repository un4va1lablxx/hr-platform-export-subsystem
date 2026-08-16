package export.organization.mappers;

import export.organization.dtos.OrganizationDto;
import export.organization.entities.*;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationMapperTest {

    private final OrganizationMapper mapper = Mappers.getMapper(OrganizationMapper.class);

    @Test
    void toOrganizationDto_ShouldMapCorrectly() {
        // Arrange
        UUID orgId = UUID.randomUUID();
        Organization organization = new Organization();
        organization.setId(orgId);
        organization.setName("Test Org");
        organization.setType(WorkType.EXECUTOR);
        LegalProfile legalProfile = new LegalProfile();
        legalProfile.setTypeLegalEntity(LegalType.OOO);
        organization.setLegalProfile(legalProfile);

        // Act
        OrganizationDto dto = mapper.toOrganizationDto(organization);

        // Assert
        assertNotNull(dto);
        assertEquals(orgId, dto.getId());
        assertEquals("Test Org", dto.getName());
        assertEquals(WorkType.EXECUTOR, dto.getType());
        assertEquals("OOO", dto.getDescription());
    }

    @Test
    void toOrganizationDto_WhenLegalProfileNull_ShouldMapWithoutDescription() {
        // Arrange
        Organization organization = new Organization();
        organization.setId(UUID.randomUUID());
        organization.setName("Test Org");
        organization.setType(WorkType.CUSTOMER);
        organization.setLegalProfile(null);

        // Act
        OrganizationDto dto = mapper.toOrganizationDto(organization);

        // Assert
        assertNotNull(dto);
        assertEquals("Test Org", dto.getName());
        assertEquals(WorkType.CUSTOMER, dto.getType());
        assertNull(dto.getDescription());
    }
}