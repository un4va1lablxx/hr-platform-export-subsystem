package export.user.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SpecializationType {

    JAVA_DEVELOPER("Java разработчик"),
    FRONTEND_DEVELOPER("Frontend разработчик"),
    PHP_DEVELOPER("PHP разработчик"),
    FULL_STACK_DEVELOPER("Full-stack разработчик"),
    CPP_DEVELOPER("С++ разработчик");

    private final String description;

}
