package com.example.java.extension_parameters;

import com.example.java.assertions.AssertableResponse;
import com.example.java.models.User;
import com.example.java.services.UserApiService;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.List;
import java.util.Map;


public class ServiceParameterResolver implements ParameterResolver {


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return (parameterContext.getParameter().getType() == Service.class);
    }

    @Override
    public Service resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new DefaultService(new UserApiService("/"));
    }

    private static class DefaultService implements Service {

        public UserApiService service;

        DefaultService(UserApiService userApiService){
            this.service = userApiService;
        }

        @Override
        public AssertableResponse getCustomers() {
            return service.getCustomers();
        }

        @Override
        public AssertableResponse registerCustomer(User user) {
            return service.registerCustomer(user);
        }

        @Override
        public AssertableResponse getCustomer(String id) {
            return service.getCustomer(id);
        }

        @Override
        public AssertableResponse deleteCustomer(String id) {
            return service.deleteCustomer(id);
        }

        @Override
        public void deleteCustomerWithName(String username) {
            List<Map> customers = service.getCustomers().response.extract().body()
                    .jsonPath().param("userName", username).get("_embedded.customer.findAll { customer -> customer.username = userName }");
            customers.forEach((customer)-> service.deleteCustomer(customer.get("id").toString()));
        }

        @Override
        public void deleteAllCustomers() {
            List<Map> customers = service.getCustomers().response.extract().body().jsonPath().get("_embedded.customer.findAll()");
            customers.forEach((customer)-> service.deleteCustomer(customer.get("id").toString()));
        }

        @Override
        public void deleteCustomersBesides(String username) {
            List<Map> customers = service.getCustomers().response.extract().body()
                    .jsonPath().param("userName", username).get("_embedded.customer.findAll { customer -> customer.username != userName }");
            customers.forEach((customer)-> service.deleteCustomer(customer.get("id").toString()));
        }
    }

}
