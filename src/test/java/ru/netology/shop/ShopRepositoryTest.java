package ru.netology.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    Product product1 = new Product(123, "Bread", 10);
    Product product2 = new Product(48, "Milk", 18);
    Product product3 = new Product(3456, "Coffee", 5);
    Product productDuplicateId = new Product(48, "Cheese", 20);
    ShopRepository repo = new ShopRepository();

    @Test
    public void shouldRemoveItemById() {

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.remove(product3.id);

        Product[] expected = {product1, product2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGenerateExceptionNotFound() {

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(20);
        });
    }

    @Test
    public void shouldAddItem() {

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGenerateAlreadyExistException() {

        repo.add(product1);
        repo.add(product2);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(productDuplicateId);
        });
    }
}
