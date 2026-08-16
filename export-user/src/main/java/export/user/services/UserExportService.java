package export.user.services;

import export.user.dtos.UserOrganizationDto;
import export.user.dtos.UserOrganizationExportDto;
import export.user.exporters.CsvExporter;
import export.user.exporters.ExcelExporter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import export.user.mappers.UserMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserExportService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public byte[] exportAllUsersToExcel() {
        List<UserOrganizationDto> users = userService.findAllUsers();
        ExcelExporter<UserOrganizationExportDto> exporter = new ExcelExporter<>(UserOrganizationExportDto.class);
        return exporter.export(userMapper.toUserOrganizationExportDtoList(users));
    }

    @Transactional(readOnly = true)
    public byte[] exportAllUsersToCsv() {
        List<UserOrganizationDto> users = userService.findAllUsers();
        CsvExporter<UserOrganizationExportDto> exporter = new CsvExporter<>(UserOrganizationExportDto.class);
        return exporter.export(userMapper.toUserOrganizationExportDtoList(users));
    }
}
