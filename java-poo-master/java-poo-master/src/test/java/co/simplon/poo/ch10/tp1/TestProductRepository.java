package co.simplon.poo.ch10.tp1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import co.simplon.poo.ch10.tp1.model.Product;
import co.simplon.poo.ch10.tp1.repository.impl.ProductRepositoryJson;

public class TestProductRepository {
	private ProductRepositoryJson products = new ProductRepositoryJson("data/json/products.json");

	@BeforeEach
	void beforeEachTest() throws IOException {
		products.deleteAll();
	}

	@Test
	void testCreateAndRetrieve() throws IOException {
		products.create(new Product("Smartphone", 499.99f, "Un smartphone récent et au top, il a même 5 caméra !"));
		products.create(
				new Product("Tablette", 299f, "Une tablette dernière génération à un prix des plus abordable !"));
		List<Product> testList = products.retrieve();
		assertEquals(2, testList.size());
	}

	@Test
	void testUpdate() throws Exception {
		Product smartphone = products
				.create(new Product("Smartphone", 499.99f, "Un smartphone récent et au top, il a même 5 caméra !"));
		assertEquals(499.99f, products.getByProductName("Smartphone").getPrice());
		smartphone.setPrice(449.99f);
		products.update(smartphone, smartphone.getId());
		assertEquals(449.99f, products.getByProductName("Smartphone").getPrice());
	}

	@Test
	void testDelete() throws IOException {
		Product smartphone = products
				.create(new Product("Smartphone", 499.99f, "Un smartphone récent et au top, il a même 5 caméra !"));
		assertEquals(1, products.count());

		try {
			products.delete(smartphone.getId());
			assertEquals(0, products.count());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testFindByProductName() throws IOException {
		products.create(new Product("Smartphone", 499.99f, "Un smartphone récent et au top, il a même 5 caméra !"));
		products.create(
				new Product("Tablette", 299f, "Une tablette dernière génération à un prix des plus abordable !"));

		assertNotNull(products.getByProductName("Smartphone"));
		assertNotNull(products.getByProductName("Tablette"));
		assertNull(products.getByProductName("smartphone"));
		assertNull(products.getByProductName("tablette"));
	}

	@Test
	void testFindById() throws IOException {
		String id = products
				.create(new Product("Smartphone", 499.99f, "Un smartphone récent et au top, il a même 5 caméra !"))
				.getId();
		assertEquals("Smartphone", products.getById(id).getProductName());
	}
}
