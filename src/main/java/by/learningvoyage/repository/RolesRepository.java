package by.learningvoyage.repository;

import by.learningvoyage.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Roles, Long> {
}
