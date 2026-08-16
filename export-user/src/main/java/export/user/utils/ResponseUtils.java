package export.user.utils;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ResponseUtils {

    private static final MediaType CSV_MEDIA_TYPE = MediaType.parseMediaType("text/csv; charset=UTF-8");
    private static final MediaType EXCEL_MEDIA_TYPE = MediaType.parseMediaType
            ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");


    private ResponseUtils() { }

    public static ResponseEntity<Resource> buildCsvResponse(byte[] fileContent, String fileName) {
        return buildFileResponse(fileContent, fileName, CSV_MEDIA_TYPE);
    }

    public static ResponseEntity<Resource> buildExcelResponse(byte[] fileContent, String fileName) {
        return buildFileResponse(fileContent, fileName, EXCEL_MEDIA_TYPE);
    }

    public static ResponseEntity<Resource> buildFileResponse(byte[] fileContent, String fileName, MediaType mediaType) {
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename*=UTF-8''" + encodedFileName;
        ByteArrayResource resource = new ByteArrayResource(fileContent);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .contentLength(fileContent.length)
                .body(resource);
    }
}
