package gep.ma.intelligent.pv.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gep.ma.intelligent.pv.Services.DevicesByPlantService;
import gep.ma.intelligent.pv.Models.DevicesByPlant;
import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DevicesByPlantController {

    private final DevicesByPlantService devicesByPlantService;

    public DevicesByPlantController(DevicesByPlantService devicesByPlantService) {
        this.devicesByPlantService = devicesByPlantService;
    }

    @GetMapping
    public List<DevicesByPlant> getAllDevices() {
        return devicesByPlantService.getAllDevices();
    }
}
