package export.user.mappers;

import export.user.dtos.OrganizationDto;
import export.user.dtos.UserInfo;
import export.user.dtos.UserOrganizationDto;
import export.user.dtos.UserOrganizationExportDto;
import export.user.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "specialization", source = "user.specialization.description")
    UserInfo toUserInfo(User user);

    UserOrganizationDto toUserOrganizationDto(UserInfo user, OrganizationDto organization);

    @Mapping(target = "fullName", source = "dto", qualifiedByName = "getFullName")
    @Mapping(target = "professionalRole", source = "dto.user.specialization")
    @Mapping(target = "legalEntity", source = "dto", qualifiedByName = "getLegalEntity")
    @Mapping(target = "legalEntityType", source = "dto.organization.type.description")
    UserOrganizationExportDto toUserOrganizationExportDto(UserOrganizationDto dto);

    List<UserOrganizationExportDto> toUserOrganizationExportDtoList(List<UserOrganizationDto> dtos);

    @Named("getFullName")
    default String getFullName(UserOrganizationDto dto) {
        return String.join(" ",
                dto.getUser().getLastName(),
                dto.getUser().getFirstName(),
                dto.getUser().getMiddleName()
        );
    }

    @Named("getLegalEntity")
    default String getLegalEntity(UserOrganizationDto dto) {
        return String.join(" ",
                dto.getOrganization().getDescription(),
                dto.getOrganization().getName()
        );
    }
}