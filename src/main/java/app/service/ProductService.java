package app.service;

import app.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductService extends CrudRepository<Product, Long> {
    List<Product> findAll();
}
