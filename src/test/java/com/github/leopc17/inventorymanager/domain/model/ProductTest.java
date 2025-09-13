package com.github.leopc17.inventorymanager.domain.model;

import com.github.leopc17.inventorymanager.domain.enums.ProductCategory;
import com.github.leopc17.inventorymanager.domain.exceptions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private final Integer validIdExample = 1;
    private final String validLongDescriptionExample = "Lorem ipsum dolor sit amet, co";  // 30 caracteres
    private final String validShortDescriptionExample = "Lorem ipsu"; // 10 caracteres
    private final String validNameExample = "example";
    private final BigDecimal validPriceExample = new BigDecimal(500);
    private final Integer validQuantityExample = 10;
    private final ProductCategory validProductCategoryExample = ProductCategory.ELECTRONICS;

    private final Product validProductExample = new Product(validIdExample,
            validNameExample,
            validPriceExample,
            validQuantityExample,
            validLongDescriptionExample,
            validShortDescriptionExample,
            validProductCategoryExample);

    @Test
    @DisplayName("Deve instanciar e retornar atributos corretamente")
    void ProductCase1() {
        Product product = new Product(validIdExample,
                validNameExample,
                validPriceExample,
                validQuantityExample,
                validLongDescriptionExample,
                validShortDescriptionExample,
                validProductCategoryExample);

        assertEquals(validIdExample, product.getId());
        assertEquals(validNameExample, product.getName());
        assertEquals(validPriceExample, product.getPrice());
        assertEquals(validQuantityExample, product.getQuantity());
        assertEquals(validLongDescriptionExample, product.getLongDescription());
        assertEquals(validShortDescriptionExample, product.getShortDescription());
        assertEquals(validProductCategoryExample, product.getCategory());
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar com nome nulo")
    void ProductCase2() {
        assertThrows(ProductNameInvalidException.class, () ->{
            Product product = new Product(validIdExample,
                    null,
                    validPriceExample,
                    validQuantityExample,
                    validLongDescriptionExample,
                    validShortDescriptionExample,
                    validProductCategoryExample);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar com nome vazio")
    void ProductCase3() {
        assertThrows(ProductNameInvalidException.class, () ->{
            Product product = new Product(validIdExample,
                    "",
                    validPriceExample,
                    validQuantityExample,
                    validLongDescriptionExample,
                    validShortDescriptionExample,
                    validProductCategoryExample);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar com nome em branco")
    void ProductCase4() {
        assertThrows(ProductNameInvalidException.class, () ->{
            Product product = new Product(validIdExample,
                    "  ",
                    validPriceExample,
                    validQuantityExample,
                    validLongDescriptionExample,
                    validShortDescriptionExample,
                    validProductCategoryExample);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar com nome com menos de 3 caracteres")
    void ProductCase5() {
        assertThrows(ProductNameInvalidException.class, () ->{
            Product product = new Product(validIdExample,
                    "ab",
                    validPriceExample,
                    validQuantityExample,
                    validLongDescriptionExample,
                    validShortDescriptionExample,
                    validProductCategoryExample);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar com preço nulo")
    void ProductCase6() {
        assertThrows(ProductPriceInvalidException.class, ()->{
            Product product = new Product(validIdExample,
                    validNameExample,
                    null,
                    validQuantityExample,
                    validLongDescriptionExample,
                    validShortDescriptionExample,
                    validProductCategoryExample);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar com preço menor que 0")
    void ProductCase7() {
        assertThrows(ProductPriceInvalidException.class, ()->{
            Product product = new Product(validIdExample,
                    validNameExample,
                    new BigDecimal(-1),
                    validQuantityExample,
                    validLongDescriptionExample,
                    validShortDescriptionExample,
                    validProductCategoryExample);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar com preço igual a 0")
    void ProductCase8() {
        assertThrows(ProductPriceInvalidException.class, ()->{
            Product product = new Product(validIdExample,
                    validNameExample,
                    new BigDecimal(0),
                    validQuantityExample,
                    validLongDescriptionExample,
                    validShortDescriptionExample,
                    validProductCategoryExample);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar com quantidade menor que 0")
    void ProductCase9() {
        ProductQuantityInvalidException exception = assertThrows(ProductQuantityInvalidException.class, () ->{
            Product product = new Product(validIdExample,
                    validNameExample,
                    validPriceExample,
                    -1,
                    validLongDescriptionExample,
                    validShortDescriptionExample,
                    validProductCategoryExample);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar descrição longa com menos de 30 caracteres")
    void ProductCase10() {
        assertThrows(ProductDescriptionInvalidException.class, () ->{
            Product product = new Product(validIdExample,
                    validNameExample,
                    validPriceExample,
                    validQuantityExample,
                    "aaa",
                    validShortDescriptionExample,
                    validProductCategoryExample);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar descrição curta com menos de 10 caracteres")
    void ProductCase11() {
        assertThrows(ProductDescriptionInvalidException.class, () ->{
            Product product = new Product(validIdExample,
                    validNameExample,
                    validPriceExample,
                    validQuantityExample,
                    validLongDescriptionExample,
                    "aaa",
                    validProductCategoryExample);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao instanciar categoria nula")
    void ProductCase12() {
        assertThrows(ProductCategoryInvalidException.class, () ->{
            Product product = new Product(validIdExample,
                    validNameExample,
                    validPriceExample,
                    validQuantityExample,
                    validLongDescriptionExample,
                    validShortDescriptionExample,
                    null);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir nome nulo")
    void setNameCase1() {
        Product product = new Product();

        assertThrows(ProductNameInvalidException.class, () ->{
            product.setName(null);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir nome vazio")
    void setNameCase2() {
        Product product = new Product();

        assertThrows(ProductNameInvalidException.class, () ->{
            product.setName("");
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir nome em branco")
    void setNameCase3() {
        Product product = new Product();

        assertThrows(ProductNameInvalidException.class, () ->{
            product.setName(" ");
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir nome com menos de 3 caracteres")
    void setNameCase4() {
        Product product = new Product();
        assertThrows(ProductNameInvalidException.class, () ->{
            product.setName("ab");
        });
    }

    @Test
    @DisplayName("Deve atribuir e retornar nome corretamente")
    void setNameCase5() {
        Product product = new Product();
        assertDoesNotThrow(() -> {
            product.setName(validNameExample);
        });

        assertEquals(validNameExample, product.getName());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir preço nulo")
    void setPriceCase1() {
        Product product = new Product();

        assertThrows(ProductPriceInvalidException.class, () ->{
            product.setPrice(null);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir preço menor que 0")
    void setPriceCase2() {
        Product product = new Product();

        assertThrows(ProductPriceInvalidException.class, () ->{
            product.setPrice(new BigDecimal(-1));
        });
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir igual a 0")
    void setPriceCase3() {
        Product product = new Product();

        assertThrows(ProductPriceInvalidException.class, () ->{
            product.setPrice(new BigDecimal(0));
        });
    }

    @Test
    @DisplayName("Deve atribuir e retornar preço corretamente")
    void setPriceCase4() {
        Product product = new Product();
        assertDoesNotThrow(() -> {
            product.setPrice(validPriceExample);
        });

        assertEquals(validPriceExample, product.getPrice());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir quantidade menor que 0")
    void setQuantityCase1() {
        Product product = new Product();

        assertThrows(ProductQuantityInvalidException.class, () ->{
            product.setQuantity(-1);
        });
    }

    @Test
    @DisplayName("Deve atribuir e retornar quantidade corretamente")
    void setQuantityCase2() {
        Product product = new Product();

        assertDoesNotThrow(() -> {
            product.setQuantity(validQuantityExample);
        });

        assertEquals(validQuantityExample, product.getQuantity());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir descrição longa com menos de 30 caracteres")
    void setLongDescriptionCase1() {
        Product product = new Product();

        assertThrows(ProductDescriptionInvalidException.class, () ->{
            product.setLongDescription("aaa");
        });
    }

    @Test
    @DisplayName("Não deve lançar exceção ao tentar atribuir descrição longa válida")
    void setLongDescriptionCase2() {
        Product product = new Product();

        assertDoesNotThrow(() -> {
            product.setLongDescription(validLongDescriptionExample);
        });
    }

    @Test
    @DisplayName("Deve atribuir e retornar descrição longa corretamente")
    void setLongDescriptionCase3() {
        Product product = new Product();

        assertDoesNotThrow(() -> {
            product.setLongDescription(validLongDescriptionExample);
        });

        assertEquals(validLongDescriptionExample, product.getLongDescription());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir descrição curta com menos de 10 caracteres")
    void setShortDescriptionCase1() {
        Product product = new Product();

        assertThrows(ProductDescriptionInvalidException.class, () ->{
            product.setShortDescription("aaa");
        });
    }

    @Test
    @DisplayName("Não deve lançar exceção ao tentar atribuir descrição curta válida")
    void setShortDescriptionCase2() {
        Product product = new Product();

        assertDoesNotThrow(() -> {
            product.setShortDescription(validShortDescriptionExample);
        });
    }

    @Test
    @DisplayName("Deve atribuir e retornar descrição curta corretamente")
    void setShortDescriptionCase3() {
        Product product = new Product();

        assertDoesNotThrow(() -> {
            product.setShortDescription(validShortDescriptionExample);
        });

        assertEquals(validShortDescriptionExample, product.getShortDescription());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atribuir categoria nula")
    void setCategoryCase1() {
        Product product = new Product();

        assertThrows(ProductCategoryInvalidException.class, () ->{
            product.setCategory(null);
        });
    }

    @Test
    @DisplayName("Deve atribuir e retornar categoria corretamente")
    void setCategoryCase2() {
        Product product = new Product();

        assertDoesNotThrow(() -> {
            product.setCategory(validProductCategoryExample);
        });

        assertEquals(validProductCategoryExample, product.getCategory());
    }
}