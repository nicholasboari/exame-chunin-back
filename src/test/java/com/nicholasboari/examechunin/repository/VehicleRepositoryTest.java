package com.nicholasboari.examechunin.repository;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.util.VehicleCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @DisplayName("Save creates vehicle when Successful")
    @Test
    void save_PersistVehicle_WhenSuccessful() {
        Vehicle vehicleToBeSaved = VehicleCreator.createVehicleToBeSaved();

        Vehicle vehicleSaved = this.vehicleRepository.save(vehicleToBeSaved);

        Assertions.assertThat(vehicleSaved).isNotNull();

        Assertions.assertThat(vehicleSaved.getId()).isNotNull();

        Assertions.assertThat(vehicleSaved.getName()).isEqualTo(vehicleSaved.getName());
    }

    @DisplayName("Save updates vehicle when Successful")
    @Test
    void update_ReplaceVehicle_WhenSuccessful() {
        Vehicle vehicleToBeSaved = VehicleCreator.createVehicleToBeSaved();

        Vehicle vehicleSaved = this.vehicleRepository.save(vehicleToBeSaved);

        vehicleSaved.setName("Nivus");

        Vehicle vehicleUpdated = this.vehicleRepository.save(vehicleSaved);

        Assertions.assertThat(vehicleUpdated).isNotNull();

        Assertions.assertThat(vehicleUpdated.getId()).isNotNull();

        Assertions.assertThat(vehicleUpdated.getName()).isEqualTo(vehicleSaved.getName());
    }

    @DisplayName("Delete removes vehicle when Successful")
    @Test
    void delete_RemovesVehicle_WhenSuccessful() {
        Vehicle vehicleToBeSaved = VehicleCreator.createVehicleToBeSaved();

        Vehicle vehicleSaved = this.vehicleRepository.save(vehicleToBeSaved);

        this.vehicleRepository.delete(vehicleSaved);

        Optional<Vehicle> vehicleOptional = this.vehicleRepository.findById(vehicleSaved.getId());

        Assertions.assertThat(vehicleOptional).isNotPresent();
    }
}