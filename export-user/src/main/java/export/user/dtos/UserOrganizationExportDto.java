package export.user.dtos;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import export.user.annotations.ExcelColumn;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserOrganizationExportDto {

    @ExcelColumn(name = "Фамилия Имя Отчество")
    @CsvBindByName(column = "Фамилия Имя Отчество")
    private String fullName;

    @ExcelColumn(name = "Роль в системе")
    @CsvBindByName(column = "Роль в системе")
    private String systemRole;

    @ExcelColumn(name = "Профессиональная роль")
    @CsvBindByName(column = "Профессиональная роль")
    private String professionalRole;

    @ExcelColumn(name = "Юридическое лицо")
    @CsvBindByName(column = "Юридическое лицо")
    private String legalEntity;

    @ExcelColumn(name = "Тип юридического лица")
    @CsvBindByName(column = "Тип юридического лица")
    private String legalEntityType;
}
