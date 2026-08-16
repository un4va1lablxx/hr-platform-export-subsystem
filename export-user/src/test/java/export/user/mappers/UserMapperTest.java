package export.user.mappers;

import export.user.dtos.*;
import export.user.entities.SpecializationType;
import export.user.entities.User;
import export.user.entities.WorkType;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void toUserInfo_ShouldMapCorrectly() {
        // Arrange
        User user = new User();
        user.setLastName("Ivanov");
        user.setFirstName("Ivan");
        user.setMiddleName("Ivanovich");
        user.setSpecialization(SpecializationType.JAVA_DEVELOPER);
        user.setEmail("test@example.com");

        // Act
        UserInfo result = mapper.toUserInfo(user);

        // Assert
        assertEquals("Ivanov", result.getLastName());
        assertEquals("Ivan", result.getFirstName());
        assertEquals("Ivanovich", result.getMiddleName());
        assertEquals("Java разработчик", result.getSpecialization());
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    void toUserOrganizationExportDto_ShouldMapCorrectly() {
        // Arrange
        UserInfo userInfo = new UserInfo();
        userInfo.setLastName("Ivanov");
        userInfo.setFirstName("Ivan");
        userInfo.setMiddleName("Ivanovich");

        OrganizationDto orgDto = new OrganizationDto();
        orgDto.setName("Test Org");
        orgDto.setType(WorkType.EXECUTOR);
        orgDto.setDescription("Desc");

        UserOrganizationDto dto = new UserOrganizationDto(userInfo, orgDto);

        // Act
        UserOrganizationExportDto result = mapper.toUserOrganizationExportDto(dto);

        // Assert
        assertEquals("Ivanov Ivan Ivanovich", result.getFullName());
        assertEquals("Desc Test Org", result.getLegalEntity());
        assertEquals("Исполнитель", result.getLegalEntityType());
    }
}