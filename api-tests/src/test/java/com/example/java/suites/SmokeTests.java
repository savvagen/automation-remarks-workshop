package com.example.java.suites;

import com.example.java.api_tests.CatalogTests;
import com.example.java.api_tests.RegistrationTests;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({RegistrationTests.class, CatalogTests.class})
//@IncludeTags("positive")
@ExcludeTags("negative")
@SuiteDisplayName("My Test Suite")
@Execution(ExecutionMode.SAME_THREAD)
public class SmokeTests {
}



/*

MORE EXAMPLES

@RunWith(JUnitPlatform.class)
@SelectPackages({"com.howtodoinjava.junit5.examples.packageA","com.howtodoinjava.junit5.examples.packageB"})
public class JUnit5TestSuiteExample
{
}


----------------------
@IncludePackages Example
-----------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@IncludePackages("com.howtodoinjava.junit5.examples.packageC")
public class JUnit5TestSuiteExample
{
}



--------------------------------
@ExcludePackages Example
--------------------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@ExcludePackages("com.howtodoinjava.junit5.examples.packageC")
public class JUnit5TestSuiteExample
{
}



------------------------------
@IncludeClassNamePatterns Example
Include all tests classes with name ending with ATest or ATests.
-------------------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@IncludeClassNamePatterns({"^.*ATests?$"})
public class JUnit5TestSuiteExample
{
}



-------------------------------------
@ExcludeClassNamePatterns Example
Exclude all tests classes with name ending with ATest or ATests.
--------------------------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@ExcludeClassNamePatterns({"^.*ATests?$"})
public class JUnit5TestSuiteExample
{
}


------------------------------------------
@IncludeTags Example
This tests suite will run all tests tagged with production inside package com.howtodoinjava.junit5.examples (and it’s sub-packages).
-------------------------------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@IncludeTags("production")
public class JUnit5TestSuiteExample
{
}


-----------------------------------------
@ExcludeTags Example
This tests suite will exclude all tests tagged with development inside package com.howtodoinjava.junit5.examples (and it’s sub-packages).
-----------------------------------------

@RunWith(JUnitPlatform.class)
@SelectPackages("com.howtodoinjava.junit5.examples")
@ExcludeTags("development")
public class JUnit5TestSuiteExample
{
}




*/
