


docker run --net="host" --rm -v ${PWD}:/local swaggerapi/swagger-codegen-cli generate \
    -i http://petstore.swagger.io/v2/swagger.json \
    -l java \
    -o /local/java \
    --library "rest-assured"
    