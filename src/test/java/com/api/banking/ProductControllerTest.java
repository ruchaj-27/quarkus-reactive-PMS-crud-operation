package com.api.banking;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import com.api.banking.entity.Product;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class ProductControllerTest {

	@Test
	public void testGetAllProducts() {
		given().when().get("/product").then().statusCode(200).contentType(MediaType.APPLICATION_JSON);
	}

	@Test
	public void testCreateProduct() {
		String product = """
				{
				    "name": "watch",
				    "description": "This is a Rado watch",
				    "price": 100.0,
				    "quantity": 50
				}""";
		given().contentType(ContentType.JSON).body(product).when().post("/product").then().statusCode(201)
				.body("name", equalTo("watch")).body("description", equalTo("This is a Rado watch"))
				.body("price", equalTo(100.00)).body("quantity", equalTo(50));
	}

	@Test
	public void testGetProductById() {

		Product p = new Product(1, "shoes", "This is a pair of nike shoes", 123.80, 50);
		given().contentType(ContentType.JSON).when().get("/product/" + p.getId()).then().statusCode(200).body("name",
				equalTo("shoes"));
	}

	@Test
	public void TestUpdateProduct() {
		Product updatedProduct = new Product(1, "watch", "This is a Rado watch", 100.50, 20);

		given().contentType(ContentType.JSON).when().put("/product/" + updatedProduct.getId()).then().statusCode(200)
				.body("name", equalTo("watch")).body("description", equalTo("This is an amazing watch"))
				.body("price", equalTo(120.00)).body("quantity", equalTo(200));

	}

	@Test
	public void TestdeleteProduct() {
		Product p = new Product(1, "Shirt", "This is a Raymond shirt", 100.60, 20);
		given().when().delete("/product/" + p.getId()).then().statusCode(200);
	}

	@Test
	public void TestGetProductsSortedByPrice() {
		Product p1 = new Product(1, "watch", "This is a rado watch", 23.98, 5);
		Product p2 = new Product(2, "shoes", "This is a Nike shoes", 30.00, 10);
		Product p3 = new Product(3, "shirt", "This is a Raymond shirt", 50.98, 20);

		given().contentType(ContentType.JSON).when().get("/product/sorted").then().statusCode(200)
				.body("name", equalTo("watch")).body("name", equalTo("shoes")).body("name", equalTo("shirt"));
	}

}
