package gep.ma.intelligent.pv.Services;

import gep.ma.intelligent.pv.Models.Measures;
import gep.ma.intelligent.pv.Repos.MeasuresRepository;
import org.springframework.stereotype.Service;
import gep.ma.intelligent.pv.Models.PageResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import com.datastax.oss.driver.api.core.cql.PagingState;
import java.util.Base64;
import java.nio.ByteBuffer;
@Service
public class MeasuresService {

    private final MeasuresRepository measuresRepository;

    @Autowired
    public MeasuresService(MeasuresRepository measuresRepository) {
        this.measuresRepository = measuresRepository;
    }

    // Fetch all measures for a specific plantId with pagination
    public Slice<Measures> getMeasuresByPlantIdAndVariableType(int plantId, String variableType, Pageable pageable, String pagingState) {
        CassandraPageRequest pageRequest;

        // Check if the paging state is present and use it for the subsequent page
        if (pagingState != null && !pagingState.isEmpty()) {
            pageRequest = CassandraPageRequest.of(pageable, ByteBuffer.wrap(Base64.getDecoder().decode(pagingState)));
        } else {
            // For the first page
            pageRequest = CassandraPageRequest.first(pageable.getPageSize());
        }

        return measuresRepository.findByKeyPlantIdAndVariableType(plantId, variableType, pageRequest);
    }




}

//    @Async
//    public CompletableFuture<List<Measures>> getAllMeasuresAsync() {
//        return CompletableFuture.supplyAsync(() -> measuresRepository.findAll());
//    }}
