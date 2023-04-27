package com.nicholasboari.examechunin.util;

import com.nicholasboari.examechunin.requests.VehiclePostRequestBody;

public class VehiclePostRequestBodyCreator {
    public static VehiclePostRequestBody createVehicleToBeSaved() {
        return VehiclePostRequestBody.builder().name(VehicleCreator.createVehicleToBeSaved().getName()).build();
    }
}
