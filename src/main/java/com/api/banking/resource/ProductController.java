package com.api.banking.resource;

import java.util.List;

import com.api.banking.entity.Product;
import com.api.banking.service.ProductService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

	@Inject
	ProductService service;

	/*
	 * check if the application is alive or not
	 * returns String message
	*/
	@GET
	@Path("/test")
	public String test() {
		return "I am alive";
	}
	
	/*
	 * get all the products available
	 * returns List of the products
	*/	
	@GET
	public Response getProductList() {
		List<Product> result = service.getAllProducts();
		return Response.ok(result).build();
	}

	/*
	 * give the product details correspond to the id
	 * @param id : id of the product to be retrived
	 * returns Response of product
	*/
	@GET
	@Path("/{id}")
	public Response getProductById(@PathParam("id") int id) {
		Product product = service.getProductById(id);
		return Response.ok(product).build();
	}

	/*
	 * add the new product
	 * return the response of product
	*/
	@POST
	@Transactional
	public Response addProduct(Product p) {
		service.createProduct(p);
		return Response.status(Response.Status.CREATED).entity(p).build();
	}

	/*
	 * update the existing product
	 * @param id : id of the product to be updated
	 * returns the updated product
	*/
	@PUT
	@Path("/{id}")
	public Product updateProduct(@PathParam("id") int id, Product p) {
		return service.updateProduct(id, p);
	}
	

	/*
	 * delete the product correspond to id
	 * @param id : id of the product to be deleted
	 * return the response in boolean form 
	*/
	@DELETE
	@Path("/{id}")
	public Response deleteProduct(@PathParam("id") int id) {
		boolean result = service.deleteProduct(id);
		return result ? Response.ok("product deleted successfully!").build()
				: Response.status(Status.NOT_FOUND).build();
	}
	
	/*
	 * check the availability of the product
	 * @param id : id of product to be checked for availability	
	 * returns the response with boolean result
	*/	
	@GET
	@Path("/available/{id}")
	public Response checkStockAvailability(@PathParam("id") int id, @QueryParam("count") int count) {

		boolean available = service.checkStockAvailability(id, count);
		if (available)
			return Response.ok(available).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}
	
	/*
	 * get the list of products in sorted form
	 * returns list of products in ascending order
	*/	
	@GET
	@Path("/sorted")
	public List<Product> getProductsSortedByPrice() {
		return service.getProductsSortedByPrice();
	}

}
