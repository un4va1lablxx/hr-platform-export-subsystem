package export.user.exporters;

import export.user.dtos.UserOrganizationExportDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExcelExporterTest {

    @Test
    void export_ShouldGenerateValidExcel() {
        // Arrange
        UserOrganizationExportDto dto = new UserOrganizationExportDto();
        dto.setFullName("Ivanov Ivan Ivanovich");
        dto.setSystemRole("Admin");

        ExcelExporter<UserOrganizationExportDto> exporter =
                new ExcelExporter<>(UserOrganizationExportDto.class);

        // Act
        byte[] result = exporter.export(List.of(dto));

        // Assert
        assertNotNull(result);
        assertTrue(result.length > 0);
    }
}