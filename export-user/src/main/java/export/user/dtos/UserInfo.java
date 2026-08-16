package export.user.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Данные пользователя")
public class UserInfo {

    @Schema(description = "Фамилия", example = "Иванов")
    private String lastName;

    @Schema(description = "Имя", example = "Иван")
    private String firstName;

    @Schema(description = "Отчество", example = "Иванович")
    private String middleName;

    @Schema(description = "Роль в системе", example = "Администратор")
    private String role;

    @Schema(description = "Почта", example = "example@mail.ru")
    private String email;

    @Schema(description = "Специализация", example = "Java разработчик")
    private String specialization;
}