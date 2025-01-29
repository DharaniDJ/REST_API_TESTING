package com.example.rest_api_testing;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JiraBugTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://dharani56525.atlassian.net";

        String createIssueResponse = given()
            .header("Content-Type", "application/json")
            .header("Authorization", "Basic ZGhhcmFuaTU2NTI1QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBVSGZuUTMyZjg2UFJlblZZR1kweFhUT3hXN3RCamxudXJFc0ZEMmxLRlV6YkJvTTQtUHN2YXA4RkpjMHJBX09MRWxQeVp4dWRjT1FnNDNSVkE4cXBHNkY0WFpwMjBSTnZVcDN6NkpZZ29UQUFCMi0yeUpVTFVrTGZBdnhIQzlWcENOQVVUb1RIZm5TamxCa09RU0tDTFRnaEhUWVFsbjJtYUpBREt6YkZBbUE9QUFGQ0JBMEU")
            .body("{\n" + //
                "    \"fields\": {\n" + //
                "       \"project\":\n" + //
                "       {\n" + //
                "          \"key\": \"RA\"\n" + //
                "       },\n" + //
                "       \"summary\": \"REST API Automation Testing\",\n" + //
                "       \"description\": \"Creating an issue via REST API Automation Testing\",\n" + //
                "       \"issuetype\": {\n" + //
                "          \"name\": \"Bug\"\n" + //
                "       }\n" + //
                "   }\n" + //
                "}")
            .log().all()
        .when()
            .post("/rest/api/3/issue")
        .then()
            .log().all()
            .assertThat().statusCode(201)
            .extract().response().asString();

        JsonPath js = new JsonPath(createIssueResponse);
        String issueId = js.getString("id");
        System.out.println(issueId);

        given()
            .header("Authorization","Basic ZGhhcmFuaTU2NTI1QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBVSGZuUTMyZjg2UFJlblZZR1kweFhUT3hXN3RCamxudXJFc0ZEMmxLRlV6YkJvTTQtUHN2YXA4RkpjMHJBX09MRWxQeVp4dWRjT1FnNDNSVkE4cXBHNkY0WFpwMjBSTnZVcDN6NkpZZ29UQUFCMi0yeUpVTFVrTGZBdnhIQzlWcENOQVVUb1RIZm5TamxCa09RU0tDTFRnaEhUWVFsbjJtYUpBREt6YkZBbUE9QUFGQ0JBMEU")
            .header("X-Atlassian-Token", "no-check")
            .multiPart("file", new java.io.File("location-of-your-file")).log().all()
        .when()
            .post("/rest/api/3/issue/" + issueId + "/attachments")
        .then()
            .log().all()
            .assertThat().statusCode(200);
    }
}
