package export.organization.services;

import export.organization.mappers.OrganizationMapper;
import export.organization.dtos.OrganizationDto;
import export.organization.repositories.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Transactional(readOnly = true)
    public List<OrganizationDto> findAllOrganizations() {
        return organizationRepository.findAll()
                                     .stream()
                                     .map(organizationMapper::toOrganizationDto)
                                     .collect(Collectors.toList());

    }
}
