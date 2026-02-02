package com.github.leopc17.inventorymanager.infrastructure.adapter.output;

import com.github.leopc17.inventorymanager.domain.enums.ProductCategory;
import com.github.leopc17.inventorymanager.domain.model.Product;
import com.github.leopc17.inventorymanager.domain.output.ProductRepositoryPort;
import com.github.leopc17.inventorymanager.infrastructure.entity.ProductEntity;
import com.github.leopc17.inventorymanager.infrastructure.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
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
    public Optional<Product> save(Product product) {
        ProductEntity productEntity = ProductMapper.productEntity(product);
        ProductEntity savedEntity = productRepositoryJpa.save(productEntity);
        return Optional.of(ProductMapper.product(savedEntity));
    }

    @Override
    public Optional<List<Product>> getALl() {
        List<ProductEntity> produtosEntity = productRepositoryJpa.findAll();
        List<Product> produto = produtosEntity.stream().map(x-> ProductMapper.product(x)).toList();
        Optional<List<Product>> optional = Optional.of(produto);
        return optional;
    }

    @Override
    public Optional<Product> getById(Integer id) {
        Optional<ProductEntity> produtoEntity = productRepositoryJpa.findById(id);
        Optional<Product> product = Optional.of(ProductMapper.product(produtoEntity.get()));
        return product;
    }

    @Override
    public Optional<Product> updateById(Product product, Integer id) {
        product.setId(id);
        productRepositoryJpa.save(ProductMapper.productEntity(product));
        Optional<Product> optional = Optional.of(product);
        return optional;
    }

    @Override
    public void deleteById(Integer id) {
        productRepositoryJpa.deleteById(id);
    }

    @Override
    public Optional<List<Product>> getByCategory(ProductCategory category) {
        List<ProductEntity> produtosEntity = productRepositoryJpa.findByCategory(category);
        List<Product> produtos = produtosEntity.stream().map(ProductMapper::product).toList();
        return Optional.of(produtos);
    }

    @Override
    public Optional<List<Product>> getByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) { // Tipo alterado
        List<ProductEntity> produtosEntity = productRepositoryJpa.findByPriceBetween(minPrice, maxPrice);
        List<Product> produtos = produtosEntity.stream().map(ProductMapper::product).toList();
        return Optional.of(produtos);
    }

    @Override
    public Optional<List<Product>> getProductsBelow(Integer quantity) {
        List<ProductEntity> productsBelow = productRepositoryJpa.findProductsBelow(quantity);
        List<Product> products = productsBelow.stream().map(ProductMapper::product).toList();
        return Optional.of(products);
    }

    @Override
    public Optional<List<Product>> getAllOrder(Sort sort) {
        List<ProductEntity> entity = productRepositoryJpa.findAll(sort);
        List<Product> products = entity.stream().map(ProductMapper::product).toList();
        return Optional.of(products);
    }

}
