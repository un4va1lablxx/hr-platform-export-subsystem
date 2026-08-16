package export.organization.entities;

import export.organization.entities.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumsTest {

    @Test
    void workType_ShouldHaveCorrectDescriptions() {
        assertEquals("Исполнитель", WorkType.EXECUTOR.getDescription());
        assertEquals("Заказчик", WorkType.CUSTOMER.getDescription());
    }

    @Test
    void legalType_ShouldHaveCorrectDescriptions() {
        assertEquals("OOO", LegalType.OOO.getDescription());
        assertEquals("АО", LegalType.AO.getDescription());
        assertEquals("ПАО", LegalType.PAO.getDescription());
    }

    @Test
    void registrationStatus_ShouldHaveCorrectDescriptions() {
        assertEquals("Неподтвержден", RegistrationStatus.NEW.getDescription());
        assertEquals("Подтвержден", RegistrationStatus.APPROVED.getDescription());
    }
}