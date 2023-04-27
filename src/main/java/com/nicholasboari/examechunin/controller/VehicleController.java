package com.nicholasboari.examechunin.controller;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.domain.enums.VehicleBrandEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleFuelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleModelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleTypeEnum;
import com.nicholasboari.examechunin.requests.VehiclePostRequestBody;
import com.nicholasboari.examechunin.requests.VehiclePutRequestBody;
import com.nicholasboari.examechunin.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    @Operation(summary = "List all vehicles paginated")
    public ResponseEntity<Page<Vehicle>> findAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(vehicleService.findAll(pageable));
    }

    @GetMapping("/name")
    public ResponseEntity<Page<Vehicle>> findByNameContaining(@RequestParam String name, @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(vehicleService.findByNameContaining(name, pageable));
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

    @GetMapping("/brand")
    public ResponseEntity<List<Vehicle>> findByBrand(@RequestParam VehicleBrandEnum brand) {
        return ResponseEntity.ok(vehicleService.findByVehicleBrand(brand));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Vehicle>> listAll() {
        return ResponseEntity.ok(vehicleService.findAllNonPageable());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Void> admin() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Vehicle> save(@RequestBody VehiclePostRequestBody vehiclePostRequestBody) {
        Vehicle vehicleSaved = vehicleService.save(vehiclePostRequestBody);
        return new ResponseEntity<>(vehicleSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "When Vehicle ID Does Not Exists"),
    })
    public ResponseEntity<Vehicle> replace(@PathVariable Long id, @RequestBody VehiclePutRequestBody vehiclePutRequestBody) {
        Vehicle replace = vehicleService.replace(vehiclePutRequestBody, id);
        return ResponseEntity.ok().body(replace);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(value = "http://localhost:5173", allowCredentials = "true")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "404", description = "When Vehicle ID Does Not Exists"),
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
