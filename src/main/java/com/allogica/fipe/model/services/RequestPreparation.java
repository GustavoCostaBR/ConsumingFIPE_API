package com.allogica.fipe.model.services;

import com.allogica.fipe.model.entities.Vehicle;
import com.allogica.fipe.model.entities.VehicleYear;

import java.util.ArrayList;
import java.util.List;

public class RequestPreparation {
    static final String BRANCH_LIST = "https://parallelum.com.br/fipe/api/v1/___/marcas";
    static final String BRANCH_SELECTION = "https://parallelum.com.br/fipe/api/v1/___/marcas/  /modelos";

    static final String VEHICLE_MODEL_SELECTION = "https://parallelum.com.br/fipe/api/v1/___/marcas/  /modelos/__/anos";

    static final String VEHICLE_SELECTION = "https://parallelum.com.br/fipe/api/v1/___/marcas/  /modelos/__/anos/**";


    public String branchListRequest(String vehicleType){
        return Request.callApi(BRANCH_LIST.replace("___", vehicleType));
    }

    public String branchRequestPreparationAndRequest(String vehicleType, String branch){
        return Request.callApi(BRANCH_SELECTION.replace("___", vehicleType).replace("  ", branch));
    }

    public String vehicleYearRequestPreparationAndRequest(String vehicleType, String branchCode, String vehicleCode){
        return Request.callApi(VEHICLE_MODEL_SELECTION.replace("___", vehicleType).replace("  ", branchCode).replace("__", vehicleCode));
    }

    public List<String> vehicleRequestPreparationAndRequest(String vehicleType, String branchCode, String vehicleCode, List<VehicleYear> vehicleYears){
        List<String> vehiclesString = new ArrayList<>();
        for(VehicleYear vehicleYear : vehicleYears){
            vehiclesString.add(Request.callApi(VEHICLE_SELECTION.replace("___", vehicleType).replace("  ", branchCode).replace("__", vehicleCode).replace("**", vehicleYear.codeYear())));
        }
        return vehiclesString;
    }



}
