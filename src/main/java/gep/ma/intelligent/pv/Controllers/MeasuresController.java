package gep.ma.intelligent.pv.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import gep.ma.intelligent.pv.Models.PageResult;
import gep.ma.intelligent.pv.Repos.MeasuresRepository;
import java.time.Instant;


import org.springframework.http.ResponseEntity;
import gep.ma.intelligent.pv.Services.MeasuresService;
import gep.ma.intelligent.pv.Models.Measures;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.concurrent.CompletableFuture;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.*;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
@RestController
@RequestMapping("/api/measures")
public class MeasuresController {

    private final MeasuresService measuresService;


    public MeasuresController(MeasuresService measuresService) {
        this.measuresService = measuresService;
    }


    @GetMapping("/paginated")
    public ResponseEntity<Map<String, Object>> getMeasuresPaginated(
            @RequestParam int plantId,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String pagingState,
            @RequestParam(required = false) String variableType,
            @RequestParam(required = false) String startDate, // Optional startDate
            @RequestParam(required = false) String endDate   // Optional endDate
    ) {
        Pageable pageable = PageRequest.of(page, size);

        // Convert startDate and endDate to Instant
        Instant startInstant = startDate != null ? Instant.parse(startDate) : Instant.EPOCH; // Default to epoch if not provided
        Instant endInstant = endDate != null ? Instant.parse(endDate) : Instant.now(); // Default to current time if not provided

        // Get the paginated data from the service
        Slice<Measures> measuresSlice = measuresService.getMeasuresByPlantIdAndVariableTypeAndDateRange(
                plantId, variableType, startInstant, endInstant, pageable, pagingState
        );

        // Prepare response with paging state
        Map<String, Object> response = new HashMap<>();
        response.put("measures", measuresSlice.getContent());
        response.put("hasNext", measuresSlice.hasNext());

        // Handling Cassandra-specific paging state
        if (measuresSlice.hasNext() && pageable instanceof CassandraPageRequest) {
            CassandraPageRequest cassandraPageRequest = (CassandraPageRequest) pageable;
            // Encode paging state to Base64 to return as a string
            String newPagingState = Base64.getEncoder().encodeToString(cassandraPageRequest.getPagingState().array());
            response.put("pagingState", newPagingState);
        } else {
            response.put("pagingState", null);
        }

        return ResponseEntity.ok(response);
    }






}
