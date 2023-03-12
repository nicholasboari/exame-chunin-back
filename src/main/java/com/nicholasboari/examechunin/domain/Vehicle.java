package com.nicholasboari.examechunin.entities;

import com.nicholasboari.examechunin.entities.enums.VehicleModelEnum;
import com.nicholasboari.examechunin.entities.enums.VehicleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private Double year;
    private Double price;
    private String description;
    private List<String> imageUri;
    private VehicleTypeEnum vehicleType;
    private VehicleModelEnum vehicleModel;

}
