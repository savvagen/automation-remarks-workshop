package com.example.java.api_tests;


import com.example.java.TestBase;
import com.example.java.extensions.annotations.Negative;
import com.example.java.extensions.annotations.Positive;
import com.example.java.extensions.annotations.TestOnLinux;
import com.example.java.extensions.listeners.TestLoggingListener;
import com.example.java.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;
import java.util.List;
import static com.example.java.conditions.Conditions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.condition.JRE.*;
import static org.junit.jupiter.api.condition.OS.*;

@Epic("SmokeTests")
@Feature("Catalog")

@DisplayName("Catalog tests")
@ExtendWith(TestLoggingListener.class)
// For Parallel execution
@Execution(ExecutionMode.SAME_THREAD)
public class CatalogTests extends TestBase {





    @Test
    @Positive
    @TestOnLinux
    @Story("Get catalog items with product and catalog validation")
    @DisplayName("Get Catalog items ╯°□°）╯ with wide validation")
    void getCatalogItemsWithMoreAssertions(){
        Product[] products =  gson.fromJson(catalogApiService.getCatalog()
                        .shouldHave(statusCode(200))
                        .shouldHave(contentType("text/plain; charset=utf-8"))
                        .response.extract().body().asString(), Product[].class);

        assertAll("Assert multiply conditions",
                ()-> {
                    assertAll("Validate list of products",
                            () -> assertTrue(products.length > 0),
                            () -> assertEquals(9, products.length)
                    );
                },
                () -> {
                    Product product = products[0];
                    assertAll("Validate tests product",
                            () -> assertTrue(product.tag.size() > 1),
                            () -> assertEquals(testProduct.id, product.getId()),
                            () -> assertEquals(testProduct.description, product.getDescription()),
                            () -> assertEquals(testProduct.getPrice(), product.price)
                    );

                }
        );
    }

    @Test
    @Negative
    @DisabledOnOs({LINUX, WINDOWS})
    //@Disabled
    @DisplayName("Disabled Test")
    void getCatalogItems(){
        List<Product> products = catalogApiService.getCatalog()
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .response.extract().body().jsonPath().getList(".");
        assertTrue(products.size() > 0);
        assertEquals(9, products.size());
        assertEquals("Holy", products.get(0).name);
        assertThat(products.size(), equalTo(9));
        assertThat(products.get(0).description, equalTo(testProduct.getDescription()));
        assertThat(products.get(0).id, is(testProduct.id));
    }




    @Test
    @Positive
    @EnabledOnOs({ LINUX, WINDOWS })
    @DisplayName("Get Product with invalid Content Type")
    void getProduct(){
        Throwable exception = assertThrows(AssertionError.class, ()-> {
            catalogApiService.getCatalogItem(testProduct.getId())
                    .shouldHave(statusCode(200))
                    .shouldHave(contentType("application/json; charset=utf-8"))
                    .shouldHave(body("$[0].name", equalTo("Holy")));
        });
        assertEquals("1 expectation failed.\n" +
                "Expected content-type \"application/json; charset=utf-8\" doesn't match actual content-type \"text/plain; charset=utf-8\".\n",
                exception.getMessage()
        );


    }


    @Test
    @EnabledOnJre({JAVA_8, JAVA_9, JAVA_10, JAVA_11})
    @DisplayName("Validate Catalog item with Gson Library \uD83D\uDE31")
    void getProductWithGsonLibraryMapping(){
        Product product = gson.fromJson(catalogApiService.getCatalogItem(testProduct.getId())
                        .shouldHave(statusCode(200))
                        .response.extract().response().body().asString(), Product.class);
        assertAll("Validate tests product",
                () -> assertTrue(product.tag.size() > 1),
                () -> assertEquals(testProduct.id, product.getId()),
                () -> assertEquals(testProduct.description, product.getDescription()),
                () -> assertEquals(testProduct.getPrice(), product.price)
        );


    }


    @Test
    @Positive
    void getProdcutWithJaksonLibraryMapping() throws IOException {
        Product product = objectMapper.readValue(catalogApiService.getCatalogItem(testProduct.id)
                .shouldHave(statusCode(200))
                .response.extract().body().asString(), Product.class);
        assertAll("Validate tests product",
                () -> assertTrue(product.tag.size() > 1),
                () -> assertEquals(testProduct.id, product.getId()),
                () -> assertEquals(testProduct.description, product.getDescription()),
                () -> assertEquals(testProduct.getPrice(), product.price)
        );
    }

    @Test
    @Negative
    void getProdcutsWithJaksonLibraryMapping() throws IOException {
        List<Product> products = objectMapper.readValue(catalogApiService.getCatalogItem(testProduct.id)
                .shouldHave(statusCode(200))
                .response.extract().body().asString(), new TypeReference<List<Product>>(){});
        assertAll("Validate tests product",
                () -> assertEquals(9, products.size()),
                () -> assertTrue(products.get(0).tag.size() > 1),
                () -> assertEquals(testProduct.id, products.get(0).getId()),
                () -> assertEquals(testProduct.description, products.get(0).getDescription()),
                () -> assertEquals(testProduct.getPrice(), products.get(0).price)
        );
    }

    @Test
    @Disabled
    @Negative
    void getProdcutsWithGsonLibraryMapping() throws IOException {
        Product[] products = gson.fromJson(catalogApiService.getCatalogItem(testProduct.id)
                .shouldHave(statusCode(200))
                .response.extract().body().asString(), Product[].class);
        assertAll("Validate tests product",
                () -> assertEquals(9, products.length),
                () -> assertTrue(products[0].tag.size() > 1),
                () -> assertEquals(testProduct.id, products[0].getId()),
                () -> assertEquals(testProduct.description, products[0].getDescription()),
                () -> assertEquals(testProduct.getPrice(), products[0].price)
        );
    }




}
