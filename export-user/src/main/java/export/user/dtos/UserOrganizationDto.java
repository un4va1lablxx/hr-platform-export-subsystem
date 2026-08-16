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
@Schema(description = "Данные о пользователе и организации")
public class UserOrganizationDto {

    @Schema(description = "Данные о пользователе")
    private UserInfo user;

    @Schema(description = "Данные об организации")
    private OrganizationDto organization;
}