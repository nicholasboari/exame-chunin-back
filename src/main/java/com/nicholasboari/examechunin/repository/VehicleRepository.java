package com.nicholasboari.examechunin.repository;

import com.nicholasboari.examechunin.domain.Vehicle;
import com.nicholasboari.examechunin.domain.enums.VehicleBrandEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleFuelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleModelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    List<Vehicle> findByName(String name);
    List<Vehicle> findByVehicleModel(VehicleModelEnum model);
    List<Vehicle> findByVehicleType(VehicleTypeEnum type);
    List<Vehicle> findByVehicleFuel(VehicleFuelEnum fuel);
    List<Vehicle> findByVehicleBrand(VehicleBrandEnum brand);
}
