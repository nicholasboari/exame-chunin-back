package com.nicholasboari.examechunin.service;

import com.nicholasboari.examechunin.domain.VehicleImage;
import com.nicholasboari.examechunin.exception.BadRequestException;
import com.nicholasboari.examechunin.repository.VehicleImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleImageService {

    @Autowired
    private VehicleImageRepository vehicleImageRepository;

    public Page<VehicleImage> findAll(Pageable pageable) {
        return vehicleImageRepository.findAll(pageable);
    }

    public List<VehicleImage> findAllNonPageable() {
        return vehicleImageRepository.findAll();
    }

    public VehicleImage findByIdOrThrowBadRequestException(Long id) {
        return vehicleImageRepository.findById(id).orElseThrow(() -> new BadRequestException("VehicleImage ID not found"));
    }



}
