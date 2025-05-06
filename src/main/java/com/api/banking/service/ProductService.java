package com.api.banking.service;

import java.util.List;

import com.api.banking.entity.Product;
import com.api.banking.repository.ProductRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class ProductService {

	@Inject
	ProductRepository repo;

	public List<Product> getAllProducts() {
		return repo.listAll();
	}

	public Product getProductById(int id) {
		Product product = repo.findById(id);
		return product;
	}

	@Transactional
	public void createProduct(Product product) {
		repo.persist(product);
	}

	@Transactional
	public Product updateProduct(@PathParam("id") int id, Product p) {
		Product existingProduct = getProductById(id);
		if (null == p) {
			existingProduct.setName(p.getName());
			existingProduct.setDescription(p.getDescription());
			existingProduct.setPrice(p.getPrice());
			existingProduct.setQuantity(p.getQuantity());
		}
		return existingProduct;
	}

	@Transactional
	public boolean deleteProduct(int id) {
		boolean result = repo.deleteById(id);
		if (!result)
			throw new WebApplicationException("Product for id : " + id + "not found", 404);
		return result;
	}

	public boolean checkStockAvailability(int id, int count) {
		Product p = getProductById(id);
		if (null != p)
			return p.getQuantity() >= count ? true : false;
		return false;
	}

	public List<Product> getProductsSortedByPrice() {
		return repo.list("order by price asc");
	}

}
