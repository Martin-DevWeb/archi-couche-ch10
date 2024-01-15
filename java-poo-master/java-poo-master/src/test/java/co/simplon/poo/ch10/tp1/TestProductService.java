package co.simplon.poo.ch10.tp1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.simplon.poo.ch10.tp1.model.Product;
import co.simplon.poo.ch10.tp1.repository.impl.ProductRepositoryJson;
import co.simplon.poo.ch10.tp1.service.ProductService;
import co.simplon.poo.ch10.tp1.service.impl.ProductServiceImpl;

public class TestProductService {

	private ProductRepositoryJson products = new ProductRepositoryJson("data/json/products.json");
	private ProductService productService = new ProductServiceImpl(products);

	@BeforeEach
	void beforeEachTest() throws IOException {
		products.deleteAll();
	}

	@Test
	void testChangePrice() throws Exception {
		products.create(new Product("Smartphone", 499.99f, "Un smartphone récent et au top, il a même 5 caméra !"));
		Product smartphone = products.getByProductName("Smartphone");

		productService.changePrice(smartphone.getId(), 449.99f);

		assertEquals(449.99f, smartphone.getPrice());
	}

	@Test
	void testChangeDescription() throws Exception {
		products.create(new Product("Smartphone", 499.99f, "Un smartphone récent et au top, il a même 5 caméra !"));
		Product smartphone = products.getByProductName("Smartphone");

		productService.changeDescription(smartphone.getId(),
				"Moins cher qu'un Iphone et mieux, que'est-ce tu veux de plus ?");

		assertEquals("Moins cher qu'un Iphone et mieux, que'est-ce tu veux de plus ?", smartphone.getDescription());
	}
}
