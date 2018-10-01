package com.example.java.conditions;

import com.example.java.conditions.condition.*;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;

public class Conditions {

    public static StatusCodeCondions statusCode(int code){
        return new StatusCodeCondions(code);
    }

    public static ContentTypeCondition contentType(String contentType){
        return new ContentTypeCondition(contentType);
    }

    public static ContentTypeConditionObj contentType(ContentType contentType){
        return new ContentTypeConditionObj(contentType);
    }

    public static ResponseBodyCondition body(String path, Matcher matcher){
        return new ResponseBodyCondition(path, matcher);
    }

    public static ResponseBodyConditionArgs body(String path, Matcher matcher, Object additionalKeyMatcherPairs){
        return new ResponseBodyConditionArgs(path, matcher, additionalKeyMatcherPairs);
    }

    public static ResponseBodyConditionJson body(Matcher matcher){
        return new ResponseBodyConditionJson(matcher);
    }


}
