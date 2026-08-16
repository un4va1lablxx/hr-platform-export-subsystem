package export.organization.controllers;

import export.organization.dtos.OrganizationDto;
import export.organization.services.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrganizationExportController {

    private final OrganizationService organizationService;

    @GetMapping("/export-organization")
    public ResponseEntity<List<OrganizationDto>> exportOrganization() {
        return ResponseEntity.ok(organizationService.findAllOrganizations());
    }
}
