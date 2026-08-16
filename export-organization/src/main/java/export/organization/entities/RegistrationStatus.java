package export.organization.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RegistrationStatus {

    NEW("Неподтвержден"),
    APPROVED("Подтвержден");

    private final String description;
}