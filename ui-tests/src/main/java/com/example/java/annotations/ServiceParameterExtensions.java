package com.example.java.annotations;


import com.example.java.extension_parameters.ServiceParameterResolver;
import com.example.java.extension_parameters.SiteServiceExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith({SiteServiceExtension.class, ServiceParameterResolver.class})
public @interface ServiceParameterExtensions {
}
