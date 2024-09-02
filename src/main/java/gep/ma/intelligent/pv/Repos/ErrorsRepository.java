package gep.ma.intelligent.pv.Repos;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import gep.ma.intelligent.pv.Models.Errors;
import gep.ma.intelligent.pv.Models.ErrorsKey;

@Repository
public interface ErrorsRepository extends CassandraRepository<Errors, ErrorsKey> {
}
