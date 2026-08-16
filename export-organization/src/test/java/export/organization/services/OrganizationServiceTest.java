package export.organization.services;
import export.organization.dtos.OrganizationDto;
import export.organization.entities.*;
import export.organization.mappers.OrganizationMapper;
import export.organization.repositories.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceTest {

    @Mock
    private OrganizationRepository organizationRepository;

    @Mock
    private OrganizationMapper organizationMapper;

    @InjectMocks
    private OrganizationService organizationService;

    @Test
    void findAllOrganizations_ShouldReturnMappedDtos() {
        // Arrange
        UUID orgId1 = UUID.randomUUID();
        UUID orgId2 = UUID.randomUUID();

        Organization org1 = createTestOrganization(orgId1, "Org1", WorkType.EXECUTOR, LegalType.OOO);
        Organization org2 = createTestOrganization(orgId2, "Org2", WorkType.CUSTOMER, LegalType.AO);

        OrganizationDto dto1 = new OrganizationDto(orgId1, "Org1", WorkType.EXECUTOR, "OOO");
        OrganizationDto dto2 = new OrganizationDto(orgId2, "Org2", WorkType.CUSTOMER, "AO");

        when(organizationRepository.findAll()).thenReturn(List.of(org1, org2));
        when(organizationMapper.toOrganizationDto(org1)).thenReturn(dto1);
        when(organizationMapper.toOrganizationDto(org2)).thenReturn(dto2);

        // Act
        List<OrganizationDto> result = organizationService.findAllOrganizations();

        // Assert
        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));

        verify(organizationRepository, times(1)).findAll();
        verify(organizationMapper, times(1)).toOrganizationDto(org1);
        verify(organizationMapper, times(1)).toOrganizationDto(org2);
    }

    private Organization createTestOrganization(UUID id, String name, WorkType workType, LegalType legalType) {
        Organization org = new Organization();
        org.setId(id);
        org.setName(name);
        org.setType(workType);

        LegalProfile legalProfile = new LegalProfile();
        legalProfile.setTypeLegalEntity(legalType);
        legalProfile.setOrganization(org);
        org.setLegalProfile(legalProfile);

        return org;
    }
}