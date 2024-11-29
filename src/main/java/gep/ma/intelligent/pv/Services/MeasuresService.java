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
import java.time.Instant;
import gep.ma.intelligent.pv.Repos.DevicesByPlantRepository;
import java.util.*;
import java.util.stream.Collectors;
import gep.ma.intelligent.pv.Models.DevicesByPlant;
@Service
public class MeasuresService {

    private final MeasuresRepository measuresRepository;
    private final DevicesByPlantRepository devicesByPlantRepository; // Add the new repository


    @Autowired
    public MeasuresService(MeasuresRepository measuresRepository, DevicesByPlantRepository devicesByPlantRepository) {
        this.measuresRepository = measuresRepository;
        this.devicesByPlantRepository = devicesByPlantRepository; // Assign the new repository
    }


    // Fetch all measures for a specific plantId with pagination
    public Slice<Measures> getMeasuresByPlantIdVariableTypeVariableAndDateRange(
            int plantId,
            String variableType,  // Add the variableType parameter
            String variable,       // Add the variable parameter
            Instant startDate,
            Instant endDate,
            Pageable pageable,
            String pagingState
    ) {
        CassandraPageRequest pageRequest;

        // Check if the paging state is present and use it for the subsequent page
        if (pagingState != null && !pagingState.isEmpty()) {
            pageRequest = CassandraPageRequest.of(pageable, ByteBuffer.wrap(Base64.getDecoder().decode(pagingState)));
        } else {
            // For the first page
            pageRequest = CassandraPageRequest.first(pageable.getPageSize());
        }

        // Use the repository method to filter by variableType, variable, and date range
        return measuresRepository.findByKeyPlantIdAndVariableTypeAndVariableAndDatetimeBetween(
                plantId, variableType, variable, startDate, endDate, pageRequest
        );
    }
    public Slice<Map<String, Object>> getMeasuresWithDeviceStatus(
            int plantId,
            Instant startDate,
            Instant endDate,
            Pageable pageable,
            String pagingState
    ) {
        String variableType = "Status";  // Fixed to "Status"
        String variable = "Status";  // Fixed to "Status"

        // Handling Cassandra paging
        CassandraPageRequest pageRequest;
        if (pagingState != null && !pagingState.isEmpty()) {
            pageRequest = CassandraPageRequest.of(pageable, ByteBuffer.wrap(Base64.getDecoder().decode(pagingState)));
        } else {
            pageRequest = CassandraPageRequest.first(pageable.getPageSize());
        }

        // Fetch the measures for the given plant, Status variable, and date range
        Slice<Measures> statusMeasures = measuresRepository.findByKeyPlantIdAndVariableTypeAndVariableAndDatetimeBetween(
                plantId, variableType, variable, startDate, endDate, pageRequest);

        // Extract unique device IDs from the measures
        List<Integer> deviceIds = statusMeasures.getContent().stream()
                .map(measure -> measure.getKey().getDeviceId())  // Get deviceId from Measures
                .distinct()
                .collect(Collectors.toList());

        // Fetch the associated devices by device IDs and plantId
        List<DevicesByPlant> devices = devicesByPlantRepository.findByKeyPlantIdAndKeyDeviceIdIn(plantId, deviceIds);

        // Create a map of deviceId to DevicesByPlant object for easier lookup
        Map<Integer, DevicesByPlant> devicesById = devices.stream()
                .collect(Collectors.toMap(
                        device -> device.getKey().getDeviceId(),  // Access deviceId through the key
                        device -> device
                ));

        // Map the measures with the corresponding device information
        Slice<Map<String, Object>> resultSlice = statusMeasures.map(measure -> {
            Integer deviceId = measure.getKey().getDeviceId();
            DevicesByPlant deviceInfo = devicesById.get(deviceId);
            return Map.of(
                    "measure", measure,
                    "deviceInfo", deviceInfo
            );
        });

        return resultSlice;
    }






}

//    @Async
//    public CompletableFuture<List<Measures>> getAllMeasuresAsync() {
//        return CompletableFuture.supplyAsync(() -> measuresRepository.findAll());
//    }}
