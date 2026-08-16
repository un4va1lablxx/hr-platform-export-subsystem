package export.organization.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organization")
public class Organization extends RconEntity {

    /* Наименование организации */
    @Column(nullable = false)
    private String name;

    /* Дата регистрации */
    private LocalDate registrationDate;

    /* Тип участника */
    @Enumerated(EnumType.STRING)
    private WorkType type;

    /* Электронная почта */
    @Column(unique = true)
    private String email;

    /* Статус регистрации */
    @Enumerated(EnumType.STRING)
    private RegistrationStatus status = RegistrationStatus.NEW;

    /* Логотип компании */
    @Lob
    private Blob logo;

    /* Признак наличия рейд карты */
    private boolean isRaidCard = false;

    /* Сайт компании */
    private String website;

    /* Юридический профиль организации (ИНН, КПП и т. д.) */
    @OneToOne(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private LegalProfile legalProfile;
}
