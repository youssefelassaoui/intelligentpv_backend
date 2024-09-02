package gep.ma.intelligent.pv.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import gep.ma.intelligent.pv.Services.KpisService;
import gep.ma.intelligent.pv.Models.Kpis;

@RestController
@RequestMapping("/api/kpis")
public class KpisController {

    private final KpisService kpisService;

    public KpisController(KpisService kpisService) {
        this.kpisService = kpisService;
    }

    @GetMapping
    public List<Kpis> getAllKpis() {
        return kpisService.getAllKpis();
    }
}
