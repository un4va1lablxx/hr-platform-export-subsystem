package export.user.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StatusType {

    NEW("Неподтвержден", false),

    PROJECT_SEARCH("Поиск проекта", true),

    BOOKED("Забронирован", true),

    ON_PROJECT("На проекте", true),

    IN_ARCHIVE("Архивирован", false);

    private final String description;

    private final boolean enabled;
}