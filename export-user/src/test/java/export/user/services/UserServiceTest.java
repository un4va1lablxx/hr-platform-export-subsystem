package export.user.services;

import export.user.clients.OrganizationClient;
import export.user.dtos.OrganizationDto;
import export.user.dtos.UserInfo;
import export.user.dtos.UserOrganizationDto;
import export.user.entities.SpecializationType;
import export.user.entities.StatusType;
import export.user.entities.User;
import export.user.mappers.UserMapper;
import export.user.repositories.UserRepository;
import feign.FeignException;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private OrganizationClient organizationClient;

    @InjectMocks
    private UserService userService;

    @Test
    void findAllUsers_ShouldReturnListOfUserOrganizationDto() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID orgId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);
        user.setOrganizationId(orgId);
        user.setLastName("Ivanov");
        user.setFirstName("Ivan");
        user.setSpecialization(SpecializationType.JAVA_DEVELOPER);
        user.setStatus(StatusType.NEW);
        user.setEmail("test@example.com");

        OrganizationDto orgDto = new OrganizationDto();
        orgDto.setId(orgId);
        orgDto.setName("Test Org");

        UserInfo userInfo = new UserInfo();
        userInfo.setLastName("Ivanov");
        userInfo.setFirstName("Ivan");

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(organizationClient.getAllOrganizations()).thenReturn(List.of(orgDto));
        when(userMapper.toUserInfo(user)).thenReturn(userInfo);
        when(userMapper.toUserOrganizationDto(userInfo, orgDto)).thenReturn(
                new UserOrganizationDto(userInfo, orgDto)
        );

        // Act
        List<UserOrganizationDto> result = userService.findAllUsers();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Ivanov", result.getFirst().getUser().getLastName());
        assertEquals("Test Org", result.getFirst().getOrganization().getName());

        verify(userRepository).findAll();
        verify(organizationClient).getAllOrganizations();
        verify(userMapper).toUserInfo(user);
        verify(userMapper).toUserOrganizationDto(userInfo, orgDto);
    }

    @Test
    void findAllOrganizations_WhenServiceUnavailable_ShouldReturnUsersWithErrorInfo() {
        // Arrange
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setOrganizationId(UUID.randomUUID());

        UserInfo userInfo = new UserInfo();

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userMapper.toUserInfo(user)).thenReturn(userInfo);
        when(organizationClient.getAllOrganizations()).thenThrow(mock(FeignException.class));
        // Act
        List<UserOrganizationDto> result = userService.findAllUsers();

        // Assert
        assertEquals(1, result.size());
        UserOrganizationDto dto = result.getFirst();
        assertNotNull(dto);
        assertNotNull(dto.getOrganization());
        assertEquals("Сервис организаций недоступен", dto.getOrganization().getDescription());
        assertEquals("N/A", dto.getOrganization().getName());
    }
}