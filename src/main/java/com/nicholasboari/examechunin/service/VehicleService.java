package com.nicholasboari.examechunin.service;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.domain.enums.VehicleBrandEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleFuelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleModelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleTypeEnum;
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

    public Page<Vehicle> findByNameContaining(String name, Pageable pageable) {
        return vehicleRepository.findByNameContaining(name, pageable);
    }


    public List<Vehicle> findByVehicleModel(VehicleModelEnum model) {
        return vehicleRepository.findByVehicleModel(model);
    }

    public List<Vehicle> findByVehicleType(VehicleTypeEnum type) {
        return vehicleRepository.findByVehicleType(type);
    }

    public List<Vehicle> findByVehicleFuel(VehicleFuelEnum fuel) {
        return vehicleRepository.findByVehicleFuel(fuel);
    }

    public List<Vehicle> findByVehicleBrand(VehicleBrandEnum brand) {
        return vehicleRepository.findByVehicleBrand(brand);
    }

    public Vehicle findByIdOrThrowBadRequestException(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new BadRequestException("Vehicle ID not found"));
    }

    public Vehicle save(VehiclePostRequestBody vehiclePostRequestBody) {
        Vehicle vehicle = Vehicle.builder()
                .vehicleModel(vehiclePostRequestBody.getVehicleModel())
                .vehicleType(vehiclePostRequestBody.getVehicleType())
                .vehicleFuel(vehiclePostRequestBody.getVehicleFuel())
                .vehicleBrand(vehiclePostRequestBody.getVehicleBrand())
                .price(vehiclePostRequestBody.getPrice())
                .year(vehiclePostRequestBody.getYear())
                .name(vehiclePostRequestBody.getName())
                .description(vehiclePostRequestBody.getDescription())
                .imageUrl(vehiclePostRequestBody.getImageUrl())
                .build();
        return vehicleRepository.save(vehicle);
    }

    public void delete(Long id) {
        vehicleRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public Vehicle replace(VehiclePutRequestBody vehiclePutRequestBody, Long id) {
        Vehicle vehicleSaved = findByIdOrThrowBadRequestException(id);

        vehicleSaved.setPrice(vehiclePutRequestBody.getPrice());
        vehicleSaved.setYear(vehiclePutRequestBody.getYear());
        vehicleSaved.setName(vehiclePutRequestBody.getName());
        vehicleSaved.setDescription(vehiclePutRequestBody.getDescription());
        vehicleSaved.setVehicleModel(vehiclePutRequestBody.getVehicleModel());
        vehicleSaved.setVehicleBrand(vehiclePutRequestBody.getVehicleBrand());
        vehicleSaved.setVehicleFuel(vehiclePutRequestBody.getVehicleFuel());
        vehicleSaved.setVehicleType(vehiclePutRequestBody.getVehicleType());
        vehicleSaved.setImageUrl(vehiclePutRequestBody.getImageUrl());
        return vehicleRepository.save(vehicleSaved);
    }

}
