package export.user.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Table(name = "\"user\"")
public class User extends RconEntity {

    /* Идентификатор организации */
    @Column(nullable = false)
    private UUID organizationId;

    /* Фамилия */
    @Column(nullable = false)
    private String lastName;

    /* Имя */
    @Column(nullable = false)
    private String firstName;

    /* Отчество */
    private String middleName;

    /* Специализация */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SpecializationType specialization;

    /* Отдел */
    private String department;

    /* Почта */
    @Column(nullable = false, unique = true)
    private String email;

    /* Статус */
    @Enumerated(EnumType.STRING)
    private StatusType status = StatusType.NEW;

    /* Телефон */
    private String phoneNumber;
}
