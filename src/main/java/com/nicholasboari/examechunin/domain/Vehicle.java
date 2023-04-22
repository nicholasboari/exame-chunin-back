package com.nicholasboari.examechunin.domain;

import com.nicholasboari.examechunin.domain.enums.VehicleBrandEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleFuelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleModelEnum;
import com.nicholasboari.examechunin.domain.enums.VehicleTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "tb_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "model_year")
    private Integer year;
    private Double price;
    @Column(length = 450)
    private String description;
    @Column(length = 65555)
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private VehicleTypeEnum vehicleType;
    @Enumerated(EnumType.STRING)
    private VehicleModelEnum vehicleModel;
    @Enumerated(EnumType.STRING)
    private VehicleFuelEnum vehicleFuel;
    @Enumerated(EnumType.STRING)
    private VehicleBrandEnum vehicleBrand;

}
