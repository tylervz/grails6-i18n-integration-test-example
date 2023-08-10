# Grails 6.0.0 i18n Integration Test Example

An example Grails 6 project to demonstrate a bug with i18n messages not being resolved during the execution of integration tests.

## Running

Dependencies:
* JDK 11

To run the application, run `./gradlew bootRun`.

To run the integration tests, run `./gradlew bootRun`.

## Details

This Grails 6.0.0 application was created with the command `grails create-app helloworld`.

Then I added the [ExampleController](/grails-app/controllers/helloworld/ExampleController.groovy),
[ExampleService](/grails-app/services/helloworld/ExampleService.groovy), and added more test cases to
the integration test file [HelloworldSpec](/src/integration-test/groovy/helloworld/HelloworldSpec.groovy).
