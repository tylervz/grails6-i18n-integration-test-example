package helloworld

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import groovy.json.JsonSlurper
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.uri.UriBuilder
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See https://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class HelloworldSpec extends Specification {

    @Shared HttpClient client

    void setup() {
        // serverPort is automatically injected
        String baseUrl = "http://localhost:$serverPort"
        this.client = HttpClient.create(baseUrl.toURL())
    }

    @Unroll
    void "test /Example"() {
        given: "we build a URI for the controller index method with a specific locale as a query parameter"
        String uri = UriBuilder.of('/Example')
                .queryParam("lang", lang)
                .build()

        when: "we call the method being tested"
        HttpRequest request = HttpRequest.GET(uri)
        HttpResponse<String> resp = this.client.toBlocking().exchange(request, String)
        Map json = new JsonSlurper().parseText(resp.body()) as Map

        then: "we get a success status"
        resp.status == HttpStatus.OK
        json != null
        json.message != null

        and: "the message returned is the correct translation of Previous"
        json.message == result

        where:
        lang | result
        "en_US" | "Previous"
        "en_GB" | "Previous"
        "nl" | "Vorige"
        "es" | "Anterior"
    }

    void "test /Example/test"() {
        given: "we build a URI for the controller test method"
        String uri = UriBuilder.of('/Example/test')
                .build()

        when: "we call the method being tested"
        HttpRequest request = HttpRequest.GET(uri)
        HttpResponse<String> resp = this.client.toBlocking().exchange(request, String)
        Map json = new JsonSlurper().parseText(resp.body()) as Map

        then: "we get a success status and a message retrieved from an i18n file"
        resp.status == HttpStatus.OK
        json != null
        json.message != null
        json.message == "Next"
    }

    void "test /Example/useJsonViews"() {
        given: "we build a URI for the controller useJsonViews method"
        String uri = UriBuilder.of('/Example/useJsonViews')
                .build()

        when: "we call the method being tested"
        HttpRequest request = HttpRequest.GET(uri)
        HttpResponse<String> resp = this.client.toBlocking().exchange(request, String)
        Map json = new JsonSlurper().parseText(resp.body()) as Map

        then: "we get a success status and a message retrieved from an i18n file"
        resp.status == HttpStatus.OK
        json != null
        json.importantMessage != null
        json.importantMessage == "Next"
    }

    // void "test something"() {
    //     when:"The home page is visited"
    //         go '/'
    //
    //     then:"The title is correct"
    //         title == "Welcome to Grails"
    // }

}
