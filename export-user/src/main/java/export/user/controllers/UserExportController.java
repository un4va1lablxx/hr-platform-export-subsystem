package export.user.controllers;

import export.user.utils.ResponseUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import export.user.services.UserExportService;

@RestController
public class UserExportController
{

    private final UserExportService userExportService;

    public UserExportController(UserExportService userExportService) {
        this.userExportService = userExportService;
    }

    @GetMapping("/export/csv")
    public ResponseEntity<Resource> exportUsersToCsv() {
        return ResponseUtils.buildCsvResponse(userExportService.exportAllUsersToCsv(), "Пользователи.csv");
    }
    @GetMapping("/export/excel")
    public ResponseEntity<Resource> exportUsersToExcel() {
        return ResponseUtils.buildExcelResponse(userExportService.exportAllUsersToExcel(), "Пользователи.xlsx");
    }
}
