package export.organization.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import export.organization.entities.WorkType;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {

    private UUID id;

    private String name;

    private WorkType type;

    private String description;
}

