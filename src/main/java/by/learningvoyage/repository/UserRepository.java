package by.learningvoyage.repository;

import by.learningvoyage.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String username);

}
