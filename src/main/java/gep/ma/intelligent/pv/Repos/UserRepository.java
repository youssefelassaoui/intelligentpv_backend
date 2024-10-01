package gep.ma.intelligent.pv.Repos;

import gep.ma.intelligent.pv.Models.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.cassandra.repository.Query;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {
    @Query("SELECT * FROM users WHERE username = ?0 ALLOW FILTERING")
    Optional<User> findByUsername(String username);

    // Check if a user exists by username
    boolean existsByUsername(String username);
}
