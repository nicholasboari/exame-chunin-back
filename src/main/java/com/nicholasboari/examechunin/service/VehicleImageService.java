package com.nicholasboari.examechunin.service;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.domain.VehicleImage;
import com.nicholasboari.examechunin.exception.BadRequestException;
import com.nicholasboari.examechunin.repository.VehicleImageRepository;
import com.nicholasboari.examechunin.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VehicleImageService {

    @Autowired
    private VehicleImageRepository vehicleImageRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    public Page<VehicleImage> findAll(Pageable pageable) {
        return vehicleImageRepository.findAll(pageable);
    }

    public List<VehicleImage> findAllNonPageable() {
        return vehicleImageRepository.findAll();
    }

    public VehicleImage findByIdOrThrowBadRequestException(Long id) {
        return vehicleImageRepository.findById(id).orElseThrow(() -> new BadRequestException("Vehicle ID not found"));
    }

    public VehicleImage save(Long vehicleId, MultipartFile file) {
        Vehicle vehicle = vehicleService.findByIdOrThrowBadRequestException(vehicleId);
        VehicleImage vehicleImage = VehicleImage.builder().build();

        try {
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                String imageName = file.getOriginalFilename();

                Path path = Paths.get("c:/imagensExameChunin/" + imageName);
                Files.write(path, bytes);

                vehicleImage.setName(imageName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        vehicleImage.setVehicle(vehicle);
        return vehicleImageRepository.save(vehicleImage);
    }

    public void delete(Long id) {
        VehicleImage vehicleImage = findByIdOrThrowBadRequestException(id);
        File folder = new File("c:/imagensExameChunin/");
        File[] files = folder.listFiles();

        for (File img : files) {
            if (img.getName().equals(vehicleImage.getName())) {
                img.delete();
            }
            vehicleImageRepository.delete(findByIdOrThrowBadRequestException(id));
        }
    }

    public VehicleImage replace(Long id, MultipartFile file) {
        VehicleImage vehicleImage = findByIdOrThrowBadRequestException(id);
        Long vehicleId = vehicleImage.getVehicle().getId();

        delete(id);
        return save(vehicleId, file);
    }
}
