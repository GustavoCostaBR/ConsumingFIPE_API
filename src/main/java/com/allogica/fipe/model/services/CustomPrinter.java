package com.allogica.fipe.model.services;

import com.allogica.fipe.model.entities.Branch;
import com.allogica.fipe.model.entities.VehicleModel;

import java.util.List;

public class CustomPrinter {
    static public void printListOfBranchs(List<Branch> list){
        list.forEach(B-> {System.out.println(B.code() + " - " + B.name());});
    }

    static public void printListOfVehicleModels(List<VehicleModel> list){
        list.forEach(CM-> {System.out.println(CM.code() + " - " + CM.name());});
    }
}
