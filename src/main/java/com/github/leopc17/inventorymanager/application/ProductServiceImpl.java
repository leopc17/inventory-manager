package com.github.leopc17.inventorymanager.application;

import com.github.leopc17.inventorymanager.application.exception.ProductNotFoundException;
import com.github.leopc17.inventorymanager.domain.exceptions.ProductQuantityInvalidException;
import com.github.leopc17.inventorymanager.domain.model.Product;
import com.github.leopc17.inventorymanager.domain.input.ProductServicePort;
import com.github.leopc17.inventorymanager.infrastructure.adapter.output.ProductReporitoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        return productReporitoryAdapter.save(product).get();
    }

    @Override
    public List<Product> getAll() {
        var optional = productReporitoryAdapter.getALl();

        if (optional.isEmpty()) {
            throw new ProductNotFoundException("Nenhum produto encontrado.");
        }

        return optional.get();
    }

    @Override
    public Product getById(Integer id) {
        var optional = productReporitoryAdapter.getById(id);

        if (optional.isEmpty()) {
            throw new ProductNotFoundException("Nenhum produto encontrado.");
        }

        return optional.get();
    }

    @Override
    public Product update(Product product, Integer id) {
        return productReporitoryAdapter.updateById(product, id)
                .orElseThrow(() -> new ProductNotFoundException("ID:" + id + " não encontrado"));
    }

    @Override
    public void deleteById(Integer id) {
        if (productReporitoryAdapter.getById(id).isEmpty()) {
            throw new ProductNotFoundException("ID:" + id + " não encontrado");
        }
        productReporitoryAdapter.deleteById(id);
    }

    @Override
    public Product updateInventory(Integer id, Integer quantity) {
        var optional = productReporitoryAdapter.getById(id);

        Product product = optional.orElseThrow(() -> new ProductNotFoundException("ID:" + id + " não encontrado"));

        Integer newQuantity = product.getQuantity() + quantity;

        if (newQuantity < 0) {
            throw new ProductQuantityInvalidException("A quantidade não pode ser negativa para o produto:" + id);
        }

        product.setQuantity(newQuantity);

        return productReporitoryAdapter.save(product)
                .orElseThrow(() -> new ProductNotFoundException("Erro ao atualizar quantidade do produto ID:" + id));
    }

    @Override
    public List<Product> getByCategory(String category) {
        var optional = productReporitoryAdapter.getByCategory(category);
        
        if (optional.isEmpty()) {
            throw new ProductNotFoundException("Nenhum produto encontrado na categoria: " + category);
        }
        
        return optional.get();
    }

    @Override
    public List<Product> getByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) { // Tipo alterado
        var optional = productReporitoryAdapter.getByPriceRange(minPrice, maxPrice);

        if (optional.isEmpty()) {
            throw new ProductNotFoundException("Nenhum produto encontrado na faixa de preço.");
        }

        return optional.get();
    }

}
