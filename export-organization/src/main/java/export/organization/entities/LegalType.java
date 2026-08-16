package export.organization.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LegalType {

    OOO("OOO"),
    AO("АО"),
    PAO("ПАО");

    private final String description;

}
