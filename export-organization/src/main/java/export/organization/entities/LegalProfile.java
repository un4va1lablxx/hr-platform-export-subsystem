package export.organization.entities;


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
@Table(name = "legal_profile")
public class LegalProfile extends RconEntity {

    /* Ответственный менеджер */
    private UUID managerId;

    /* ИНН */
    @Column(nullable = false, unique = true)
    private String inn;

    /* КПП */
    @Column(unique = true)
    private String kpp;

    /* ОГРН */
    @Column(nullable = false, unique = true)
    private String ogrn;

    /* Юридический адрес */
    @Column(nullable = false)
    private String legalAddress;

    /* Фактический адрес */
    @Column(nullable = false)
    private String actualAddress;

    /* Описание деятельности */
    private String description;

    /* Почтовый адрес */
    private String mailingAddress;

    /* Тип юридического лица */
    @Enumerated(EnumType.STRING)
    private LegalType typeLegalEntity;

    /* Организация (название, тип участника, контактные данные и тд) */
    @OneToOne
    @JoinColumn(name = "id", nullable = false, unique = true)
    private Organization organization;
}
