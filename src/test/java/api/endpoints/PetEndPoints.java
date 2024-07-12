package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {
	
	public static Response createPet(Pet payload) {
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.pet_post_url);
		return response;
	}
	
	public static Response readPet(int petId) {
		Response response = given()
		.pathParam("petid", petId)
		
		.when()
		.get(Routes.pet_get_url);
		return response;
	}
	
	public static Response updatePet(Pet pet) {
		Response response = given()
		.accept(ContentType.JSON)
		.contentType(ContentType.JSON)
		.body(pet)
		
		.when()
		.put(Routes.pet_update_url);
		return response;
	}
	
	public static Response deletePet(int petId) {
		Response response = given()
		.pathParam("petid", petId)
		
		.when()
		.delete(Routes.pet_delete_url);
		return response;
	}

}
