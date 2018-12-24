package com.example.java.extension_parameters;

import com.example.java.services.UserApiService;
import org.junit.jupiter.api.extension.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;

public class SiteServiceExtension implements ParameterResolver {


    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface WebService {
    }


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(WebService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return getWebService(parameterContext.getParameter());
    }

    private Object getWebService(Parameter parameter){
        Class<?> type = parameter.getType();
        if (UserApiService.class.equals(type)) {
            return new UserApiService("/");
        }
        throw new ParameterResolutionException("No random generator implemented for " + type);
    }

}
