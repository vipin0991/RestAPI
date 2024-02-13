package serialize;


import io.restassured.*;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.builder.RequestSpecBuilder;

import io.restassured.builder.ResponseSpecBuilder;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import io.restassured.specification.ResponseSpecification;

import pojo.LoginRequest;

import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

public class ECommerceAPITest {

	public static void main(String[] args) {

		// LOGIN API CALL

		RequestSpecification req = new RequestSpecBuilder().setContentType(ContentType.JSON)

				.setBaseUri("https://rahulshettyacademy.com/").build();

		ResponseSpecification rep = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
				.build();

		LoginRequest loginReq = new LoginRequest();

		loginReq.setUserEmail("vipin.dhiman21@gmail.com");

		loginReq.setUserPassword("@Vipin123");

		//Passed pojo object
		RequestSpecification reqLogin = given().log().all().spec(req).body(loginReq);

		//to get response as POJO
		LoginResponse r = reqLogin.when().post("api/ecom/auth/login").then().spec(rep).extract().response()
				.as(LoginResponse.class);
		
		
		String tkn = r.getToken();

		System.out.println(r.getUserId());

		System.out.println(r.getMessage());
		
		
		//Add Product - Not sending content as JSON in request
		RequestSpecification addProductBaseReq = new RequestSpecBuilder().addHeader("Authorization", r.getToken())
				.setBaseUri("https://rahulshettyacademy.com/").build();
		
		// include form=data params
		RequestSpecification addProduct = given().log().all().spec(addProductBaseReq).param("productName", "Laptop")
		.param("productAddedBy", r.getUserId())
		.param("productCategory", "Electronics")
		.param("productSubCategory", "Computers")
		.param("productPrice", "36000")
		.param("productDescription", "HP Laptops")
		.param("productFor", "ALL")
		.multiPart("productImage", new File("C://Users//91904//OneDrive//Desktop//Laptop.jpg"));
		
		String addProdResp = addProduct.when().post("api/ecom/product/add-product")
		.then().log().all().extract().response().asString();
		
		JsonPath js= new JsonPath(addProdResp); //Instead of creating another Pojo class,  using json, since there are 02 attributes only in response
		
		String prodId = js.get("productId");
		
		//CreateOrder
		RequestSpecification createOrderBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", tkn)
				.setContentType(ContentType.JSON).build();
		
		//How to pass data to pojo classes based on the request bodys
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setCountry("India");
		orderDetail.setProductOrderId(prodId);
		
		List<OrderDetail> ordDetailList = new ArrayList<OrderDetail>(); //Create list that will contain OrderDetail object
		ordDetailList.add(orderDetail);
		
		Orders od = new Orders();
		od.setOrders(ordDetailList);
		
		RequestSpecification createOrderReq = given().log().all().spec(createOrderBaseReq).body(od);
		String addOrderResp = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		System.out.println(addOrderResp+"************");
		
		//DeleteProduct
		RequestSpecification deleteProdBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", tkn).build();
		
		//Usage of Path Parameter
		RequestSpecification delProd = given().log().all().spec(deleteProdBaseReq).pathParam("productId", prodId);
		String rp = delProd.when().delete("api/ecom/product/delete-product/{productId}")
		.then().log().all().extract().response().asString();
		
		JsonPath js1 = new JsonPath(rp);
		
		
		System.out.println(rp);
		Assert.assertEquals(js1.get("message"), "Product Deleted Successfully");
	}

}
