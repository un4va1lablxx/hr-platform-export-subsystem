package export.organization.controllers;
import export.organization.dtos.OrganizationDto;
import export.organization.entities.WorkType;
import export.organization.services.OrganizationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizationExportControllerTest {

    @Mock
    private OrganizationService organizationService;

    @InjectMocks
    private OrganizationExportController organizationExportController;

    @Test
    void exportOrganization_ShouldReturnListOfOrganizations() {
        // Arrange
        OrganizationDto org1 = new OrganizationDto(UUID.randomUUID(), "Org1", WorkType.EXECUTOR, "OOO");
        OrganizationDto org2 = new OrganizationDto(UUID.randomUUID(), "Org2", WorkType.CUSTOMER, "AO");
        when(organizationService.findAllOrganizations()).thenReturn(List.of(org1, org2));

        // Act
        ResponseEntity<List<OrganizationDto>> response = organizationExportController.exportOrganization();

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
        verify(organizationService, times(1)).findAllOrganizations();
    }

    @Test
    void exportOrganization_WhenEmpty_ShouldReturnEmptyList() {
        // Arrange
        when(organizationService.findAllOrganizations()).thenReturn(List.of());

        // Act
        ResponseEntity<List<OrganizationDto>> response = organizationExportController.exportOrganization();

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertTrue(response.getBody().isEmpty());
    }
}