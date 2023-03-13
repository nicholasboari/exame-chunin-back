package com.nicholasboari.examechunin.service;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.exception.BadRequestException;
import com.nicholasboari.examechunin.repository.VehicleRepository;
import com.nicholasboari.examechunin.requests.VehiclePostRequestBody;
import com.nicholasboari.examechunin.requests.VehiclePutRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Page<Vehicle> findAll(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }

    public List<Vehicle> findAllNonPageable() {
        return vehicleRepository.findAll();
    }

    public Vehicle findByIdOrThrowBadRequestException(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new BadRequestException("Vehicle ID not found"));
    }

    public Vehicle save(VehiclePostRequestBody vehiclePostRequestBody){
        Vehicle vehicle = Vehicle.builder()
                .vehicleModel(vehiclePostRequestBody.getVehicleModel())
                .vehicleType(vehiclePostRequestBody.getVehicleType())
                .vehicleFuel(vehiclePostRequestBody.getVehicleFuel())
                .price(vehiclePostRequestBody.getPrice())
                .year(vehiclePostRequestBody.getYear())
                .name(vehiclePostRequestBody.getName())
                .description(vehiclePostRequestBody.getDescription()).build();
        return vehicleRepository.save(vehicle);
    }

    public void delete(Long id){
        vehicleRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public Vehicle replace(VehiclePutRequestBody vehiclePutRequestBody){
        Vehicle vehicleSaved = findByIdOrThrowBadRequestException(vehiclePutRequestBody.getId());

        Vehicle vehicle = Vehicle.builder()
                .vehicleModel(vehicleSaved.getVehicleModel())
                .vehicleType(vehicleSaved.getVehicleType())
                .vehicleFuel(vehicleSaved.getVehicleFuel())
                .price(vehicleSaved.getPrice())
                .year(vehicleSaved.getYear())
                .name(vehicleSaved.getName())
                .description(vehicleSaved.getDescription())
                .id(vehicleSaved.getId())
                .build();
        return vehicleRepository.save(vehicle);
    }

}
