package com.github.leopc17.inventorymanager.infrastructure.adapter.input;

import com.github.leopc17.inventorymanager.application.ProductServiceImpl;
import com.github.leopc17.inventorymanager.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(value = "category", required = false) String category) {
        if (category != null && !category.isEmpty()) {
            List<Product> filteredProducts = productService.getByCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(filteredProducts);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        Product updatedProduct = productService.update(product, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping("/{id}/inventory")
    public ResponseEntity<Product> updateInventory(
            @PathVariable Integer id,
            @RequestParam("quantity") Integer quantity) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateInventory(id, quantity));
    }
}