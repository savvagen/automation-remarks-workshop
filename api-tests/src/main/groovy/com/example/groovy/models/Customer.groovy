package com.example.groovy.models

import org.codehaus.jackson.annotate.JsonIgnoreProperties
import org.codehaus.jackson.annotate.JsonProperty


@JsonIgnoreProperties(ignoreUnknown = true)
class Customer {

    @JsonProperty("firstName")
    String firstName

    @JsonProperty("lastName")
    String lastName

    @JsonProperty("username")
    String username

    @JsonProperty("email")
    String email

    @JsonProperty("password")
    String password

    @JsonProperty("id")
    String id

}
