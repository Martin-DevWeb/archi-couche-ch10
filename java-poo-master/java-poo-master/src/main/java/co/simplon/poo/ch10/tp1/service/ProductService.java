package co.simplon.poo.ch10.tp1.service;

public interface ProductService {
	void changePrice(String productId, float newPrice);

	void changeDescription(String productId, String newDescription);
}
