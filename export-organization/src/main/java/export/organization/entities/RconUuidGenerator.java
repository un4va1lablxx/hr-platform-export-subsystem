package export.organization.entities;

import jakarta.persistence.Id;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import java.util.UUID;

public class RconUuidGenerator implements IdentifierGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        Field field = getFirstFieldWithIdAnnotation(object);
        if (!UUID.class.equals(field.getType())) {
            throw new IllegalStateException("ID type must be UUID");
        }

        var id = getFieldValue(object, field);
        if (id != null) {
            return id;
        }

        return UUID.randomUUID();
    }

    private static Field getFirstFieldWithIdAnnotation(Object object) {
        Class<?> currentClass = object.getClass();
        while (currentClass != null && currentClass != Object.class) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    return field;
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        throw new NoSuchElementException("Field with @Id not found in class hierarchy");
    }

    private static Object getFieldValue(Object object, Field field) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}