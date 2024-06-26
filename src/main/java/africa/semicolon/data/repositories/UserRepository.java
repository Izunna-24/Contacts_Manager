package africa.semicolon.data.repositories;

import africa.semicolon.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
    User findUserById(String id);
    User findUserByEmail(String email);

    boolean existsByEmail(String email);
}
