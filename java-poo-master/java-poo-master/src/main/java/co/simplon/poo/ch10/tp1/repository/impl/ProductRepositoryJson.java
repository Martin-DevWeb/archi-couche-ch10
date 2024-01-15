package co.simplon.poo.ch10.tp1.repository.impl;

import co.simplon.poo.ch10.tp1.model.Product;
import co.simplon.poo.ch10.tp1.repository.ProductRepository;
import co.simplon.poo.ch10.tp1.utils.persistence.GenericRepository;

public class ProductRepositoryJson extends GenericRepository<Product> implements ProductRepository {

	public ProductRepositoryJson(String jsonFilePath) {
		super(jsonFilePath);
	}

	public Product getByProductName(String productName) {
		return this.retrieve().stream().filter(p -> p.getProductName().equals(productName)).findFirst().orElse(null);
	}
}
