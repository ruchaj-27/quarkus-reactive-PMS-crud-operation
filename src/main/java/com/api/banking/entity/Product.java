package com.api.banking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank(message = "Product name cannot be empty")
	private String name;

	private String description;

	@NotBlank(message = "price must be specified")
	@Min(value = 0, message = "price cannot be negative")
	private double price;

	@NotBlank(message = "quantity must be specified")
	@Min(value = 0, message = "quantity cannot be negative")
	private int quantity;

	public Product() {

	}

	public Product(int id, @NotBlank(message = "Product name cannot be empty") String name, String description,
			@NotBlank(message = "price must be specified") @Min(value = 0, message = "price cannot be negative") double price,
			@NotBlank(message = "quantity must be specified") @Min(value = 0, message = "quantity cannot be negative") int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + "]";
	}

}
