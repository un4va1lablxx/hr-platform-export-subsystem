package export.user.controllers;

import export.user.services.UserExportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserExportControllerTest {

    @Mock
    private UserExportService userExportService;

    @InjectMocks
    private UserExportController userExportController;

    @Test
    void exportUsersToCsv_ShouldReturnResponseEntity() {
        // Arrange
        byte[] csvData = "test,csv,data".getBytes();
        when(userExportService.exportAllUsersToCsv()).thenReturn(csvData);

        // Act
        ResponseEntity<Resource> response = userExportController.exportUsersToCsv();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("attachment; filename*=UTF-8''%D0%9F%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D0%B8.csv",
                response.getHeaders().getFirst("Content-Disposition"));
    }

    @Test
    void exportUsersToExcel_ShouldReturnResponseEntity() {
        // Arrange
        byte[] excelData = "test,excel,data".getBytes();
        when(userExportService.exportAllUsersToExcel()).thenReturn(excelData);

        // Act
        ResponseEntity<Resource> response = userExportController.exportUsersToExcel();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("attachment; filename*=UTF-8''%D0%9F%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D0%B8.xlsx",
                response.getHeaders().getFirst("Content-Disposition"));
    }
}