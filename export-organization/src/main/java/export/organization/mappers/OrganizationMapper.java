package export.organization.mappers;

import export.organization.entities.LegalType;
import export.organization.entities.Organization;
import export.organization.dtos.OrganizationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizationMapper {

    @Mapping(target = "description", source = "organization", qualifiedByName = "getDescription")
    OrganizationDto toOrganizationDto(Organization organization);

    @Named("getDescription")
    default String getDescription(Organization organization) {
        if (organization.getLegalProfile() == null) {
            return null;
        }

        LegalType typeLegalEntity = organization.getLegalProfile().getTypeLegalEntity();
        if (typeLegalEntity == null) {
            return null;
        }

        return typeLegalEntity.getDescription();
    }
}
