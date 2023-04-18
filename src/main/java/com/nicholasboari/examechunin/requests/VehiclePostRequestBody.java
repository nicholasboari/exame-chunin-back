package com.nicholasboari.examechunin.requests;

import com.nicholasboari.examechunin.domain.enums.VehicleBrandEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleFuelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleModelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleTypeEnum;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class VehiclePostRequestBody {

    private String name;
    private Integer year;
    private Double price;
    @Column(length = 500)
    private String description;
    private VehicleTypeEnum vehicleType;
    private VehicleModelEnum vehicleModel;
    private VehicleFuelEnum vehicleFuel;
    private VehicleBrandEnum vehicleBrand;
}
