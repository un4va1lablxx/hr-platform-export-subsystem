package export.user.exporters;

import export.user.dtos.UserOrganizationExportDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvExporterTest {

    @Test
    void export_ShouldGenerateValidCsv() {
        // Arrange
        UserOrganizationExportDto dto = new UserOrganizationExportDto();
        dto.setFullName("Ivanov Ivan Ivanovich");
        dto.setSystemRole("Admin");

        CsvExporter<UserOrganizationExportDto> exporter =
                new CsvExporter<>(UserOrganizationExportDto.class);

        // Act
        byte[] result = exporter.export(List.of(dto));

        // Assert
        assertNotNull(result);
        assertTrue(result.length > 0);
        String csv = new String(result);
        assertTrue(csv.contains("Фамилия Имя Отчество"));
        assertTrue(csv.contains("Ivanov Ivan Ivanovich"));
    }
}
