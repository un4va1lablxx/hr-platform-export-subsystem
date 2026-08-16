package export.user.services;

import export.user.dtos.UserOrganizationDto;
import export.user.dtos.UserOrganizationExportDto;
import export.user.exporters.CsvExporter;
import export.user.exporters.ExcelExporter;
import export.user.mappers.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserExportServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserExportService userExportService;

    @Test
    void exportAllUsersToExcel_ShouldReturnNonEmptyByteArray() {
        // Arrange
        UserOrganizationDto dto = new UserOrganizationDto();
        UserOrganizationExportDto exportDto = new UserOrganizationExportDto();

        when(userService.findAllUsers()).thenReturn(Collections.singletonList(dto));
        when(userMapper.toUserOrganizationExportDtoList(Collections.singletonList(dto)))
                .thenReturn(Collections.singletonList(exportDto));

        // Act
        byte[] result = userExportService.exportAllUsersToExcel();

        // Assert
        assertNotNull(result);
        assertTrue(result.length > 0);
    }

    @Test
    void exportAllUsersToCsv_ShouldReturnNonEmptyByteArray() {
        // Arrange
        UserOrganizationDto dto = new UserOrganizationDto();
        UserOrganizationExportDto exportDto = new UserOrganizationExportDto();

        when(userService.findAllUsers()).thenReturn(Collections.singletonList(dto));
        when(userMapper.toUserOrganizationExportDtoList(Collections.singletonList(dto)))
                .thenReturn(Collections.singletonList(exportDto));

        // Act
        byte[] result = userExportService.exportAllUsersToCsv();

        // Assert
        assertNotNull(result);
        assertTrue(result.length > 0);
    }
}