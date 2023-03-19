package com.nicholasboari.examechunin.controller;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.domain.enums.VehicleFuelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleModelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleTypeEnum;
import com.nicholasboari.examechunin.requests.VehiclePostRequestBody;
import com.nicholasboari.examechunin.requests.VehiclePutRequestBody;
import com.nicholasboari.examechunin.service.VehicleService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<Page<Vehicle>> findAll(Pageable pageable) {
        return ResponseEntity.ok(vehicleService.findAll(pageable));
    }

    @GetMapping("/model")
    public ResponseEntity<List<Vehicle>> findByModel(@RequestParam VehicleModelEnum model) {
        return ResponseEntity.ok(vehicleService.findByVehicleModel(model));
    }

    @GetMapping("/type")
    public ResponseEntity<List<Vehicle>> findByType(@RequestParam VehicleTypeEnum type) {
        return ResponseEntity.ok(vehicleService.findByVehicleType(type));
    }

    @GetMapping("/fuel")
    public ResponseEntity<List<Vehicle>> findByFuel(@RequestParam VehicleFuelEnum fuel) {
        return ResponseEntity.ok(vehicleService.findByVehicleFuel(fuel));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> listAll() {
        return ResponseEntity.ok(vehicleService.findAllNonPageable());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<Vehicle> save(@RequestBody VehiclePostRequestBody vehiclePostRequestBody) {
        Vehicle vehicleSaved = vehicleService.save(vehiclePostRequestBody);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(vehicleSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(vehicleSaved);
    }

    @PutMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<Vehicle> replace(@RequestBody VehiclePutRequestBody vehiclePutRequestBody) {
        Vehicle replace = vehicleService.replace(vehiclePutRequestBody);
        return ResponseEntity.ok().body(replace);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
