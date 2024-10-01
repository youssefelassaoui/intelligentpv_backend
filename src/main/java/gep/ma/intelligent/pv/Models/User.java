package gep.ma.intelligent.pv.Models;

import gep.ma.intelligent.pv.Models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import static org.springframework.data.cassandra.core.mapping.CassandraType.Name.*;

import java.util.Set;
import java.util.UUID;

@Table("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @PrimaryKey
    private UUID id;  // UUID as the primary key

    @CassandraType(type = TEXT)
    private String username;

    @CassandraType(type = TEXT)
    private String password;

    @CassandraType(type = SET, typeArguments = TEXT)  // Store roles as strings in Cassandra
    private Set<Role> roles;
}
