package com.nicholasboari.examechunin.requests;

import com.nicholasboari.examechunin.domain.enums.VehicleBrandEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleFuelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleModelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleTypeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehiclePostRequestBody {

    @NotBlank(message = "Campo obrigatório")
    private String name;
    @NotBlank(message = "Campo obrigatório")
    private Integer year;
    @NotBlank(message = "Campo obrigatório")
    private Double price;
    @NotBlank(message = "Campo obrigatório")
    private String description;
    private String imageUrl;
    @NotBlank(message = "Campo obrigatório")
    private VehicleTypeEnum vehicleType;
    @NotBlank(message = "Campo obrigatório")
    private VehicleModelEnum vehicleModel;
    @NotBlank(message = "Campo obrigatório")
    private VehicleFuelEnum vehicleFuel;
    @NotBlank(message = "Campo obrigatório")
    private VehicleBrandEnum vehicleBrand;
}
