package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskResourceTest {

    private static Long createdTaskId;

    @Test
    @Order(1)
    public void testCreateTask() {
        Task task = new Task();
        task.title = "Integration Test Task";
        task.description = "Testing POST /tasks with @QuarkusTest";

        Number idResult = given()
            .contentType(ContentType.JSON)
            .body(task)
            .when()
            .post("/tasks")
            .then()
            .statusCode(201)
            .body("title", equalTo("Integration Test Task"))
            .body("completed", is(false))
            .extract()
            .path("id");

        createdTaskId = idResult.longValue();
    }

    @Test
    @Order(2)
    public void testGetAllTasks() {
        given()
            .when()
            .get("/tasks")
            .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(1));
    }

    @Test
    @Order(3)
    public void testDeleteTaskSuccess() {
        given()
            .pathParam("id", createdTaskId)
            .when()
            .delete("/tasks/{id}")
            .then()
            .statusCode(204);
    }

    @Test
    @Order(4)
    public void testDeleteTaskNotFound() {
        given()
            .pathParam("id", 999999L)
            .when()
            .delete("/tasks/{id}")
            .then()
            .statusCode(404);
    }
}