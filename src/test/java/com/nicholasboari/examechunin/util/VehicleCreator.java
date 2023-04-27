package com.nicholasboari.examechunin.util;

import com.nicholasboari.examechunin.domain.Vehicle;

public class VehicleCreator {
    public static Vehicle createVehicleToBeSaved() {
        return Vehicle.builder().name("T-cross").build();
    }

    public static Vehicle createValidVehicle() {
        return Vehicle.builder().name("T-cross").id(1L).build();
    }
}
