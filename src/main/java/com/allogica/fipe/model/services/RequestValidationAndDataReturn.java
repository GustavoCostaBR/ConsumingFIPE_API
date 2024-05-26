package com.allogica.fipe.model.services;

import com.allogica.fipe.model.entities.Branch;
import com.allogica.fipe.model.entities.Vehicle;
import com.allogica.fipe.model.entities.VehicleModel;
import com.allogica.fipe.model.entities.VehicleYear;

import java.util.ArrayList;
import java.util.List;

public class RequestValidationAndDataReturn {
    private RequestPreparation requestPreparation;
    private DataConversion dataConversion;

    public List<Branch> getBranchs() {
        return branchs;
    }

    private List<Branch> branchs;

    public List<VehicleModel> getVehicleModels() {
        return vehicleModels;
    }

    private List<VehicleModel> vehicleModels;

    public List<VehicleModel> getVehicleModelsReduced() {
        return vehicleModelsReduced;
    }
    private List<VehicleModel> vehicleModelsReduced;

    public List<VehicleYear> getVehicleYears() {
        return vehicleYears;
    }

    private List<VehicleYear> vehicleYears;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    private List<Vehicle> vehicles;


    public static Boolean branchCodeIsInList(String number, List<Branch> list) {
        return list.stream().anyMatch(n -> n.code().equals(number));
    }

    public static  Boolean vehicleModelCodeIsInList(String number, List<VehicleModel> list) {
        return list.stream().anyMatch(n -> n.code().equals(number));
    }
    public List<VehicleModel> getVehicleModelReducedList(String name) {
        this.vehicleModelsReduced = this.vehicleModels.stream().filter(n -> n.name().toUpperCase().contains(name.toUpperCase())).toList();
        return this.vehicleModelsReduced;
    }

    public RequestValidationAndDataReturn() {
        this.requestPreparation = new RequestPreparation();
        this.dataConversion = new DataConversionImpl();
        this.branchs = new ArrayList<>();
        this.vehicleModels = new ArrayList<>();
        this.vehicles = new ArrayList<>();
    }

    public List<Branch> branchListRequestValidationAndRequest(String vehicleType) {
        if (branchs.size() < 10) {
            String branchsString = null;
            Branch[] branchArray;
            try {
                branchsString = requestPreparation.branchListRequest(vehicleType);
                branchArray = dataConversion.convertData(branchsString, Branch[].class);
            }
            catch (Exception e){
                System.out.println("API is not answering!");
                return null;
            }
//            System.out.println(branchArray);
            this.branchs = List.of(branchArray);
        }
        return branchs;
    }

    public List<VehicleModel> branchRequestValidationAndRequest(String vehicleType, String number) {
        String vehicleModelsString = requestPreparation.branchRequestPreparationAndRequest(vehicleType, number);
        VehicleModel[] vehicleModelArray = dataConversion.getSpecificKeyAndConvertData(vehicleModelsString, VehicleModel[].class);
        this.vehicleModels = List.of(vehicleModelArray);
        return vehicleModels;
    }

    public List<Vehicle> vehicleRequestValidationAndRequest(String vehicleType, String branchCode, String vehicleCode){
        String vehicleYearString = requestPreparation.vehicleYearRequestPreparationAndRequest(vehicleType, branchCode, vehicleCode);
        List<String> vehiclesString;
        if (!vehicleYearString.isEmpty()){
            VehicleYear[] vehicleYears1 = dataConversion.convertData(vehicleYearString, VehicleYear[].class);
            this.vehicleYears = List.of(vehicleYears1);
            vehiclesString = requestPreparation.vehicleRequestPreparationAndRequest(vehicleType, branchCode, vehicleCode, this.vehicleYears);
            if (!vehiclesString.isEmpty()){
                for(String vehicleString : vehiclesString){
                    Vehicle vehicle = dataConversion.convertData(vehicleString, Vehicle.class);
                    this.vehicles.add(vehicle);
                }
                return this.vehicles;
            }
            return null;
        }
        else {
            return null;
        }
    }


}
