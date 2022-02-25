package crud_items;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CRUD_Items {
    @Test
    public void crudItem(){
        // CREATE
        JSONObject body = new JSONObject();
        body.put("Content","SantiagoItem");
        Response response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("SantiagoItem"))
                .log().all();

        int idProject =response.then().extract().path("Id");


        // READ
        response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .log().all()
                .when()
                .get("https://todo.ly/api/items/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("SantiagoItem"))
                .log().all();


        // UPDATE
        body.put("Content","SantiagoUpdate");
        response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .put("https://todo.ly/api/items/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("SantiagoUpdate"))
                .log().all();


        // DELETE
        response=given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .log().all()
                .when()
                .delete("https://todo.ly/api/items/"+idProject+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("SantiagoUpdate"))
                .body("Deleted",equalTo(true))
                .log().all();
    }
}
