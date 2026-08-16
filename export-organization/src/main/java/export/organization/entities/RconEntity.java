package export.organization.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@MappedSuperclass
public class RconEntity implements RconPersistable<UUID> {

    @Id
    @RconIdGenerator
    private UUID id;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        RconEntity o = (RconEntity) obj;
        return Objects.equals(id, o.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

