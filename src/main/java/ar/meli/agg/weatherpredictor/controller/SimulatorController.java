package ar.meli.agg.weatherpredictor.controller;


import ar.meli.agg.weatherpredictor.service.SimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/simulator-service")
public class SimulatorController {

    @Value("${simulate.days}")
    private int days;

    private final SimulatorService simulatorService;

    @Autowired
    public SimulatorController(SimulatorService simulatorService) {
        this.simulatorService = simulatorService;
    }

    @GetMapping("/simulate")
    ResponseEntity<?> simulate(@RequestParam(name = "days", required = false) Integer days){
        Map<String, String> body = new HashMap<>();
        if(days != null) {
            simulatorService.startSimulation(days);
            body = buildBodyResponse(days);
        }
        else {
            simulatorService.startSimulation(this.days);
            buildBodyResponse(this.days);
        }
        return ResponseEntity.ok(body);
    }

    private Map<String,String> buildBodyResponse(Integer days) {
        Map<String, String> body = new HashMap<>();
        body.put("message", "success");
        body.put("simulated_days", days.toString());
        return body;
    }
}
