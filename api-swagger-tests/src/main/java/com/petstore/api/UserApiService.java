package com.petstore.api;

import com.petstore.client.ApiClient;
import com.petstore.client.model.User;
import io.restassured.response.Response;

import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;
import static com.petstore.api.ApiClientUtils.*;

public class UserApiService {


    public static final Function<Response, Response> RESPONSE_HANDLER = r -> {
        assertEquals(r.statusCode(), 200);
        return r;
    };


    public static final Function<Response, Response> RESPONSE_RESPONSE_HANDLER = r -> r;



    private final ApiClient apiClient;

    public UserApiService() {
        this.apiClient = ApiClient
                .api(ApiClient.Config
                        .apiConfig()
                        .reqSpecSupplier(suppliyer()));
    }

    public Response getUserByName(String userName){
        Response response = apiClient.user()
                .getUserByName()
                .usernamePath(userName)
                .execute(RESPONSE_RESPONSE_HANDLER);
        return response;
    }


    public void createUser(User user){
        apiClient.user()
                .createUser()
                .body(user)
                .execute(RESPONSE_HANDLER);
    }

    public void deleteUser(String userName){
        apiClient.user()
                .deleteUser()
                .usernamePath(userName)
                .execute(RESPONSE_HANDLER);
    }

    public Response loginUser(User user){
        Response response = apiClient.user().loginUser()
                .usernameQuery(user.getUsername())
                .passwordQuery(user.getPassword())
                .execute(RESPONSE_RESPONSE_HANDLER);
        return response;
    }

}
