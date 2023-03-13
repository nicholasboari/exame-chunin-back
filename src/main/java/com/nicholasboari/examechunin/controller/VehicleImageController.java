package com.nicholasboari.examechunin.controller;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.domain.VehicleImage;
import com.nicholasboari.examechunin.service.VehicleImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/images")
public class VehicleImageController {

    @Autowired
    private VehicleImageService vehicleImageService;

    @GetMapping
    public ResponseEntity<Page<VehicleImage>> findAll(Pageable pageable) {
        return ResponseEntity.ok(vehicleImageService.findAll(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleImage>> listAll() {
        return ResponseEntity.ok(vehicleImageService.findAllNonPageable());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleImage> findById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleImageService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<VehicleImage> save(@RequestParam("vehicleId") Long id, @RequestParam MultipartFile file) {
        VehicleImage save = vehicleImageService.save(id, file);
        return ResponseEntity.ok().body(save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleImage> replace(@RequestParam Long id, @RequestParam MultipartFile file) {
        VehicleImage replace = vehicleImageService.replace(id, file);
        return ResponseEntity.ok().body(replace);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleImageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
