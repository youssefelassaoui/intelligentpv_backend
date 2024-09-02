package gep.ma.intelligent.pv.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import gep.ma.intelligent.pv.Services.MeasuresService;
import gep.ma.intelligent.pv.Models.Measures;

@RestController
@RequestMapping("/api/measures")
public class MeasuresController {

    private final MeasuresService measuresService;

    public MeasuresController(MeasuresService measuresService) {
        this.measuresService = measuresService;
    }

    @GetMapping
    public List<Measures> getAllMeasures() {
        return measuresService.getAllMeasures();
    }
}
