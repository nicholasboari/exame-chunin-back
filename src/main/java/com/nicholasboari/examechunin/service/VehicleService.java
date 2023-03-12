package com.nicholasboari.examechunin.service;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.exception.BadRequestException;
import com.nicholasboari.examechunin.repository.VehicleRepository;
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


}
