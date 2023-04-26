package com.nicholasboari.examechunin.repository;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.domain.enums.VehicleBrandEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleFuelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleModelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Page<Vehicle> findByNameContaining(String name, Pageable pageable);
    List<Vehicle> findByVehicleModel(VehicleModelEnum model);
    List<Vehicle> findByVehicleType(VehicleTypeEnum type);
    List<Vehicle> findByVehicleFuel(VehicleFuelEnum fuel);
    List<Vehicle> findByVehicleBrand(VehicleBrandEnum brand);
}
