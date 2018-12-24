package com.example.java.suites;


import com.example.java.tests.ui_tests.LoginTests;
import com.example.java.tests.ui_tests.RegistrationTests;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@RunWith(JUnitPlatform.class)
@Tag("UiTests")
@Execution(CONCURRENT)
@SelectClasses({LoginTests.class, RegistrationTests.class})
//@SuiteDisplayName("My Test Suite")
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
