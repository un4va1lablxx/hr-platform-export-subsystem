package export.organization.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import export.organization.entities.Organization;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> { }
