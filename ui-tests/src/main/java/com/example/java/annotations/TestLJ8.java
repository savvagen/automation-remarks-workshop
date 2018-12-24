package com.example.java.annotations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Test on OS Linux & JDK 8
 */


@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Test
@EnabledOnOs(OS.LINUX)
@DisabledOnOs(OS.WINDOWS)
@EnabledOnJre(JRE.JAVA_8)
public @interface TestLJ8 {
}
