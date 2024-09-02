package gep.ma.intelligent.pv.Services;

import org.springframework.stereotype.Service;
import java.util.List;
import gep.ma.intelligent.pv.Repos.DevicesByPlantRepository;
import gep.ma.intelligent.pv.Models.DevicesByPlant;
@Service
public class DevicesByPlantService {

    private final DevicesByPlantRepository devicesByPlantRepository;

    public DevicesByPlantService(DevicesByPlantRepository devicesByPlantRepository) {
        this.devicesByPlantRepository = devicesByPlantRepository;
    }

    public List<DevicesByPlant> getAllDevices() {
        return devicesByPlantRepository.findAll();
    }
}
