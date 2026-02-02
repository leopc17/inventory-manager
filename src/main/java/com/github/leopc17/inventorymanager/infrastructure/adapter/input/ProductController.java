package com.github.leopc17.inventorymanager.infrastructure.adapter.input;

import com.github.leopc17.inventorymanager.application.ProductServiceImpl;
import com.github.leopc17.inventorymanager.application.dto.ProductDto;
import com.github.leopc17.inventorymanager.domain.enums.ProductCategory;
import com.github.leopc17.inventorymanager.domain.model.Product;
import com.github.leopc17.inventorymanager.infrastructure.mapper.ProductMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product Management", description = "APIs for managing products")
public class ProductController {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Operation(summary = "Create a new product", description = "Add a new product to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product created successfully",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDto productDto){
        Product product = ProductMapper.product(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
    }

    @Operation(summary = "Get all products", description = "Retrieve a list of all products in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "products retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ProductDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(value = "category", required = false) ProductCategory category,
            @RequestParam(value = "minprice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxprice", required = false) BigDecimal maxPrice) {

        if (category != null) {
            List<Product> filteredProducts = productService.getByCategory(category);
            return ResponseEntity.status(HttpStatus.OK).body(filteredProducts);
        } else if (minPrice != null && maxPrice != null) {
            List<Product> filteredProducts = productService.getByPriceRange(minPrice, maxPrice);
            return ResponseEntity.status(HttpStatus.OK).body(filteredProducts);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAll());
        }
    }

    @Operation(summary = "Get product by ID", description = "Retrieve a product's details using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product found",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "404", description = "product not found",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getById(id));
    }

    @Operation(summary = "Delete a product", description = "Delete a product from the system using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "product not found",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Patch a product", description = "Patch a product from the system using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product update successfully"),
            @ApiResponse(responseCode = "404", description = "product not found",
                    content = @Content(schema = @Schema()))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        Product product = ProductMapper.product(productDto);
        Product updatedProduct = productService.update(product, id);
        return ResponseEntity.ok(updatedProduct);
    }

    @Operation(summary = "Patch a product", description = "Patch a product from the system using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "product Patched successfully"),
            @ApiResponse(responseCode = "404", description = "product not found",
                    content = @Content(schema = @Schema()))
    })
    @PatchMapping("/{id}/inventory")
    public ResponseEntity<Product> updateInventory(
            @PathVariable Integer id,
            @RequestParam("quantity") Integer quantity) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateInventory(id, quantity));
    }

    @GetMapping("/below")
    public ResponseEntity<List<Product>> getProductsBelow(
            @RequestParam(required = false)int quantity) {
        return ResponseEntity.ok(productService.getProductsBelow(quantity));
    }
}