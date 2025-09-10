package com.github.leopc17.inventorymanager.infrastructure.entity.adapter.output;

import com.github.leopc17.inventorymanager.domain.entity.Product;
import com.github.leopc17.inventorymanager.domain.output.ProductRepositoryPort;
import com.github.leopc17.inventorymanager.infrastructure.entity.ProductEntity;
import com.github.leopc17.inventorymanager.infrastructure.entity.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductReporitoryAdapter implements ProductRepositoryPort {

    private final ProductRepositoryJpa productRepositoryJpa;

    @Autowired
    public ProductReporitoryAdapter(ProductRepositoryJpa productRepositoryJpa) {
        this.productRepositoryJpa = productRepositoryJpa;
    }

    @Override
    public Optional<Product> create(Product product) {
        ProductEntity productEntity = ProductMapper.productEntity(product);
        ProductEntity savedEntity = productRepositoryJpa.save(productEntity);
        return Optional.of(ProductMapper.product(savedEntity));
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        List<ProductEntity> produtosEntity = productRepositoryJpa.findAll();
        List<Product> produto = produtosEntity.stream().map(x-> ProductMapper.product(x)).toList();
        Optional<List<Product>> optional = Optional.of(produto);
        return optional;
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        Optional<ProductEntity> produtoEntity = productRepositoryJpa.findById(id);
        Optional<Product> product = Optional.of(ProductMapper.product(produtoEntity.get()));
        return product;
    }

    @Override
    public Optional<Product> updateProduct(Product product, Integer id) {
        product.setId(id);
        productRepositoryJpa.save(ProductMapper.productEntity(product));
        Optional<Product> optional = Optional.of(product);
        return optional;
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepositoryJpa.deleteById(id);
    }
}
