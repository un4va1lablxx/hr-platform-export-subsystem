package export.user.utils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

class ResponseUtilsTest {

    @Test
    void buildCsvResponse_ShouldReturnCorrectHeaders() {
        // Arrange
        byte[] data = "test,data".getBytes();

        // Act
        var response = ResponseUtils.buildCsvResponse(data, "test.csv");

        // Assert
        assertEquals(MediaType.parseMediaType("text/csv; charset=UTF-8"), response.getHeaders().getContentType());
        assertTrue(response.getHeaders().getContentDisposition().toString().contains("test.csv"));
        assertInstanceOf(ByteArrayResource.class, response.getBody());
    }

    @Test
    void buildExcelResponse_ShouldReturnCorrectHeaders() {
        // Arrange
        byte[] data = "test,data".getBytes();

        // Act
        var response = ResponseUtils.buildExcelResponse(data, "test.xlsx");

        // Assert
        assertEquals(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
                response.getHeaders().getContentType());
        assertTrue(response.getHeaders().getContentDisposition().toString().contains("test.xlsx"));
    }
}