package com.github.leopc17.inventorymanager.application;

import com.github.leopc17.inventorymanager.domain.entity.Product;
import com.github.leopc17.inventorymanager.domain.input.ProductServicePort;
import com.github.leopc17.inventorymanager.infrastructure.entity.adapter.output.ProductReporitoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductServicePort {

    private final ProductReporitoryAdapter productReporitoryAdapter;

    @Autowired
    public ProductServiceImpl(ProductReporitoryAdapter productReporitoryAdapter) {
        this.productReporitoryAdapter = productReporitoryAdapter;
    }

    @Override
    public Product create(Product product) {
        return productReporitoryAdapter.create(product).get();
    }

    @Override
    public List<Product> getAllProducts() {
        var optional = productReporitoryAdapter.getAllProducts();
        List<Product> list = optional.get();
        return list;
    }

    @Override
    public Product getProductById(Integer id) {
        return productReporitoryAdapter.getProductById(id).get();
    }

    @Override
    public Product updateProduct(Product product, Integer id) {
        return productReporitoryAdapter.updateProduct(product, id)
                .orElseThrow(() -> new RuntimeException("ID:" + id + " não encontrado"));
    }

    @Override
    public void deleteProductById(Integer id) {
        if (productReporitoryAdapter.getProductById(id).isEmpty()) {
            throw new RuntimeException("ID:" + id + " não encontrado");
        }
        productReporitoryAdapter.deleteProductById(id);
    }
}
