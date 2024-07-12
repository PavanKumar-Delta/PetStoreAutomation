package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Order;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {

	
	public static Response createStore(Order payload) {
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.store_post_url);
		return response;
	}
	
	public static Response readStore(int orderId) {
		Response response = given()
				.pathParam("orderid", orderId)
		
		.when()
		.get(Routes.store_get_url);
		return response;
	}
	
	public static Response deleteStore(int orderId) {
		Response response = given()
				.pathParam("orderid", orderId)
		
		.when()
		.get(Routes.store_delete_url);
		return response;
	}
}
