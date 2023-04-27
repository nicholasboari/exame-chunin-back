package com.nicholasboari.examechunin.service;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.exception.BadRequestException;
import com.nicholasboari.examechunin.repository.VehicleRepository;
import com.nicholasboari.examechunin.util.VehicleCreator;
import com.nicholasboari.examechunin.util.VehiclePostRequestBodyCreator;
import com.nicholasboari.examechunin.util.VehiclePutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepository vehicleRepositoryMock;

    @BeforeEach
    void setup() {
        PageImpl<Vehicle> vehiclePage = new PageImpl<>(List.of(VehicleCreator.createValidVehicle()));
        BDDMockito.when(vehicleRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(vehiclePage);
        BDDMockito.when(vehicleRepositoryMock.findAll()).thenReturn(List.of(VehicleCreator.createValidVehicle()));
        BDDMockito.when(vehicleRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(VehicleCreator.createValidVehicle()));
        BDDMockito.when(vehicleRepositoryMock.findByNameContaining(ArgumentMatchers.anyString(), ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(vehiclePage);
        BDDMockito.when(vehicleRepositoryMock.save(ArgumentMatchers.any(Vehicle.class))).thenReturn(VehicleCreator.createValidVehicle());
        BDDMockito.doNothing().when(vehicleRepositoryMock).delete(ArgumentMatchers.any(Vehicle.class));
    }

    @Test
    @DisplayName("findAll returns list of vehicle inside page object when successful")
    void findAll_ReturnsListOfVehiclesInsideObject_WhenSuccessful() {
        String expectedName = VehicleCreator.createValidVehicle().getName();

        Page<Vehicle> vehiclePage = vehicleService.findAll(PageRequest.of(1, 1));

        Assertions.assertThat(vehiclePage).isNotNull();

        Assertions.assertThat(vehiclePage.toList()).isNotEmpty().hasSize(1);

        Assertions.assertThat(vehiclePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findAllNonPageable returns list of anime when successful")
    void findAllNonPageable_ReturnsListOfVehicles_WhenSuccessful() {
        String expectedName = VehicleCreator.createValidVehicle().getName();

        List<Vehicle> vehicleList = vehicleService.findAllNonPageable();

        Assertions.assertThat(vehicleList).isNotNull();

        Assertions.assertThat(vehicleList).isNotEmpty().hasSize(1);

        Assertions.assertThat(vehicleList.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException returns vehicle when successful")
    void findByIdOrThrowBadRequestException_ReturnsVehicle_WhenSuccessful() {
        Long expectedId = VehicleCreator.createValidVehicle().getId();

        Vehicle vehicle = vehicleService.findByIdOrThrowBadRequestException(expectedId);

        Assertions.assertThat(vehicle).isNotNull();

        Assertions.assertThat(vehicle.getId()).isEqualTo(vehicle.getId());
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when vehicle is not found")
    void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenVehicleIsNotFound() {
        BDDMockito.when(vehicleRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> this.vehicleService.findByIdOrThrowBadRequestException(1L));
    }

    @Test
    @DisplayName("findByNameContaining returns a page of vehicle when successful")
    void findByNameContaining_ReturnsListOfVehicle_WhenSuccessful() {
        String expectedName = VehicleCreator.createValidVehicle().getName();

        Page<Vehicle> vehicles = vehicleService.findByNameContaining("", PageRequest.of(1, 1));

        Assertions.assertThat(vehicles).isNotNull().isNotEmpty();

        Assertions.assertThat(vehicles.getContent().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("save returns vehicle when successful")
    void save_ReturnsVehicle_WhenSuccessful() {
        Vehicle vehicle = vehicleService.save(VehiclePostRequestBodyCreator.createVehicleToBeSaved());

        Assertions.assertThat(vehicle).isNotNull().isEqualTo(VehicleCreator.createValidVehicle());
    }

    @Test
    @DisplayName("replace updates vehicle when successful")
    void replace_UpdatesVehicle_WhenSuccessful() {
        Assertions.assertThatCode(() -> vehicleService.replace(VehiclePutRequestBodyCreator.createVehicleToBeSaved(), 1L))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete removes vehicle when successful")
    void delete_RemovesVehicle_WhenSuccessful() {
        Assertions.assertThatCode(() -> vehicleService.delete(1L)).doesNotThrowAnyException();
    }
}