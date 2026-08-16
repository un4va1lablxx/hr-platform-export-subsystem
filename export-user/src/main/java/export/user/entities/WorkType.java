package export.user.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WorkType {

    EXECUTOR("Исполнитель"),
    CUSTOMER("Заказчик");

    private final String description;
}
