package com.allogica.fipe.model.services;

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

    public String carModelRequestPreparationAndRequest(String branch, String carModel){
        return Request.callApi(VEHICLE_MODEL_SELECTION.replace("  ", branch).replace("__", carModel));
    }

    public String carRequestPreparationAndRequest(String branch, String carModel, String car){
        return Request.callApi(VEHICLE_SELECTION.replace("  ", branch).replace("__", carModel).replace("**", car));
    }

}
