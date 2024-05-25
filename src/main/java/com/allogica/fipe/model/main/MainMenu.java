package com.allogica.fipe.model.main;

import com.allogica.fipe.model.entities.Branch;
import com.allogica.fipe.model.entities.VehicleModel;
import com.allogica.fipe.model.services.CustomPrinter;
import com.allogica.fipe.model.services.RequestValidationAndDataReturn;

import java.util.*;

public class MainMenu {
    private Scanner teclado = new Scanner(System.in);
    private RequestValidationAndDataReturn requestValidationAndDataReturn = new RequestValidationAndDataReturn();


    public String makeFirstOption() {
        Map<String, String> vehicleMap = new HashMap<>();
        vehicleMap.put("1", "carros");
        vehicleMap.put("2", "caminhoes");
        vehicleMap.put("3", "motos");
        vehicleMap.forEach((k, v) -> System.out.println(k + " - " + v));
        List<String> vehicleList = new ArrayList<>(vehicleMap.values());
        String option1 = receiveAndCheck(" of the above categories: ", vehicleList);
        return vehicleMap.get(option1);
    }
    private <T> Boolean codeIsIntegerAndIsInList(String numberString, List<T> list) {
        int number;
        try {
            number = Integer.parseInt(numberString);
        } catch (Exception e) {
            System.out.println("Please, enter a valid integer as input!");
            return false;
        }
        if (!list.isEmpty()){
            if (list.get(0) instanceof Branch){
                return RequestValidationAndDataReturn.branchCodeIsInList(numberString, (List<Branch>)list);
            }
            else if (list.get(0) instanceof VehicleModel){
                return RequestValidationAndDataReturn.vehicleModelCodeIsInList(numberString, (List<VehicleModel>)list);
            }
            else if (list.get(0) instanceof String){
                return number > 0 && number <= list.size() ;
            }
//            else if (list.get(0) instanceof Car){
//                return RequestValidationAndDataReturn.carCodeIsInList(number, (List<Car>)list);
//            }
        }
        return null;
    }
    private <T> Boolean partialVehicleNameIsInList(String vehicleString){
        requestValidationAndDataReturn.getVehicleModelReducedList(vehicleString);
        if (!requestValidationAndDataReturn.getVehicleModelsReduced().isEmpty()){
            System.out.println("Vehicle was found!");
            return true;
        }
        else{
            System.out.println("Vehicle was not found!");
            return false;
        }
    }

    public <T> String receiveAndCheck(String message, List<T> list) {
        String numberString;
        do {
            System.out.println("Choose the number of one " + message);
            numberString = teclado.nextLine();
        } while (!codeIsIntegerAndIsInList(numberString, list));
        return numberString;
    }

    public List<VehicleModel> receiveVehicleNameAndCheck() {
        String vehicleName;
        do {
            System.out.println("Choose the name or partial name of a vehicle: ");
            vehicleName = teclado.nextLine();
        } while (!partialVehicleNameIsInList(vehicleName));
        return requestValidationAndDataReturn.getVehicleModelsReduced();
    }

    public void menu() {
        System.out.println("Welcome to the FIPE app!");
        String option = makeFirstOption();
        List<Branch> listBranch = requestValidationAndDataReturn.branchListRequestValidationAndRequest(option);
        if (listBranch != null) {
            CustomPrinter.printListOfBranchs(listBranch);
            String branchCode;
            List<VehicleModel> vehicleModelList;
            String carCode;
            branchCode = receiveAndCheck("branch: ", requestValidationAndDataReturn.getBranchs());
            CustomPrinter.printListOfVehicleModels(requestValidationAndDataReturn.branchRequestValidationAndRequest(option ,branchCode));
            vehicleModelList = receiveVehicleNameAndCheck();
            CustomPrinter.printListOfVehicleModels(vehicleModelList);
            carCode = receiveAndCheck("car: ", requestValidationAndDataReturn.getVehicleModelsReduced());

            //            CustomPrinter.printListOfCarModels(requestValidationAndDataReturn.requestCarModelList(branchCode))
        } else {
            System.out.println("App closed!");
        }

//        String branchCode;
//        String carModelCode;
//        String carCode;
//        branchCode = receiveAndCheck("branch: ", requestValidationAndDataReturn.getBranchs());
//        CustomPrinter.printListOfCarModels(requestValidationAndDataReturn.requestCarModelList(branchCode));
//        carModelCode = receiveAndCheck("car model: ", requestValidationAndDataReturn.getCarModels());
//
//
//        CustomPrinter.printListOfCarModels(requestValidationAndDataReturn.requestCarModelList(branchCode));
//        do {
//            System.out.println("Choose the number of one model");
//            branchCode = teclado.nextLine();
//        } while (!codeIsIntegerLessOrEqualBranchSize(branchCode));


    }
}
