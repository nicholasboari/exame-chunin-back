package com.nicholasboari.examechunin.util;

import com.nicholasboari.examechunin.requests.VehiclePutRequestBody;

public class VehiclePutRequestBodyCreator {
    public static VehiclePutRequestBody createVehicleToBeSaved() {
        return VehiclePutRequestBody.builder().name("Nivus").build();
    }
}
