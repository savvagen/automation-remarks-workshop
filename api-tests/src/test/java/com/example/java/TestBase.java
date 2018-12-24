package com.example.java;

import com.example.java.models.Product;
import com.example.java.models.User;
import com.example.java.services.CatalogApiService;
import com.example.java.services.UserApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;


public class TestBase {

    public static UserApiService userApiService;
    public static CatalogApiService catalogApiService;

    public static Faker ukFaker;
    public static Faker usFaker;

    //Json mapers
    public static Gson gson = new Gson();
    public static ObjectMapper objectMapper = new ObjectMapper();

    public static Product testProduct;
    public static User testUser;


    @BeforeAll
    static void setUpServices(){
        RestAssured.baseURI = "http://35.232.243.253";

        userApiService = new UserApiService("/");
        catalogApiService = new CatalogApiService("/catalogue");

        usFaker = new Faker(new Locale("en-US"));
        ukFaker = new Faker(new Locale("uk"));

        testProduct = new Product()
                .setName("Holly")
                .setId("03fef6ac-1896-4ce8-bd69-b798f85c6e0b")
                .setDescription("Socks fit for a Messiah. You too can experience walking in water with these special edition beauties. Each hole is lovingly proggled to leave smooth edges. The only sock approved by a higher power.")
                .setPrice(99.99)
                .setCount(1);
        testUser = new User().setFirstName("")
                .setLastName("")
                .setUsername("savva.genchevskiy")
                .setId("5bb0acefee11cb00018b0de3").setPassword("1234");
    }



}
