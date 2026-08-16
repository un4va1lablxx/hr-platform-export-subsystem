package export.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import export.user.entities.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> { }
