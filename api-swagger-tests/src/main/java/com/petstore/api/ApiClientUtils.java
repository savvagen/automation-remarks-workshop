package com.petstore.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import java.util.function.Supplier;

import static com.petstore.client.GsonObjectMapper.gson;
import static io.restassured.RestAssured.config;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;

public class ApiClientUtils {

    public static final String BASE_URL = "https://petstore.swagger.io/v2";

    public static Supplier<RequestSpecBuilder> suppliyer(){
        return ()-> new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setConfig(config.objectMapperConfig(objectMapperConfig().defaultObjectMapper(gson())))
                .setBaseUri(BASE_URL)
                .addFilter(new ErrorLoggingFilter()).addFilter(new ResponseLoggingFilter());
    }
}
