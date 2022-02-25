package crud_items;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class RestAssured_Items {
    @Test
    public void createItem(){
        given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body("{\n" +
                        "  \"Content\":\"SantiagoRestAssuredItem\",\n" +
                        "}")
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json")
                .then()
                .log().all();
    }

    @Test
    public void createItemJSONObject(){
        JSONObject body = new JSONObject();
        body.put("Content","Item_1-VargasSantiago");
        given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body(body.toString())
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json")
                .then()
                .log().all();
    }


    @Test
    public void  createItemExternalFile(){
        String pathProject=new File("").getAbsolutePath();
        given()
                .auth()
                .preemptive()
                .basic("upb_api@api.com","12345")
                .body(new File(pathProject+"/src/test/resources/itemBody.json"))
                .log().all()
                .when()
                .post("https://todo.ly/api/items.json")
                .then()
                .log().all();
    }
}
