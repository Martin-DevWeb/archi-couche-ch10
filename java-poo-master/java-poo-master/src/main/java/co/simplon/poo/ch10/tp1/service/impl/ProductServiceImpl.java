package co.simplon.poo.ch10.tp1.service.impl;

import co.simplon.poo.ch10.tp1.model.Product;
import co.simplon.poo.ch10.tp1.repository.ProductRepository;
import co.simplon.poo.ch10.tp1.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private final ProductRepository products;

	public ProductServiceImpl(ProductRepository products) {
		this.products = products;
	}

	@Override
	public void changePrice(String productId, float newPrice) {
		Product targetProduct = products.getById(productId);

		targetProduct.setPrice(newPrice);
	}

	@Override
	public void changeDescription(String productId, String newDescription) {
		Product targetProduct = products.getById(productId);

		targetProduct.setDescription(newDescription);
	}

}
