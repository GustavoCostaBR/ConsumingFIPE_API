package com.allogica.fipe.model.services;

public interface DataConversion {
    <T> T  convertData(String json, Class<T> clazz);
    public <T> T getSpecificKeyAndConvertData(String json, Class<T> clazz);
}
