package com.allogica.fipe.model.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConversionImpl implements DataConversion {

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T convertData(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getSpecificKeyAndConvertData(String json, Class<T> clazz) {
        try {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode modelsNode = rootNode.get("modelos");
            return mapper.convertValue(modelsNode, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



//    public <T> T convertData(String json, TypeReference<T> typeRef) {
//        try {
//            return mapper.readValue(json, typeRef);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }


}
