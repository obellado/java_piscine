package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0L, "item1", 10000L),
            new Product(1L, "item2", 20000L),
            new Product(2L, "item3", 30000L),
            new Product(3L, "item4", 40000L),
            new Product(4L, "item5", 50000L)
    );

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(4L, "item5", 50000L);

    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "wow", 1000000L);

    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();

    private ProductsRepository productsRepository;
    private DataSource dataSource;

    @BeforeEach
    public void init() throws SQLException {
        System.out.println("init");
        dataSource = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        dataSource.getConnection();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void findByIdTest() throws SQLException {
        Optional<Product> optionalProduct = productsRepository.findById(4L);
        if (optionalProduct.isEmpty()) {
            Assertions.fail();
            return ;
        }
        Product actualProduct = optionalProduct.get();

        Assertions.assertEquals(actualProduct.getId(), EXPECTED_FIND_BY_ID_PRODUCT.getId());
        Assertions.assertEquals(actualProduct.getPrice(), EXPECTED_FIND_BY_ID_PRODUCT.getPrice());
        Assertions.assertEquals(actualProduct.getItem_name(), EXPECTED_FIND_BY_ID_PRODUCT.getItem_name());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void findAllTest(Integer id) throws SQLException {
        List<Product> productList = productsRepository.findAll();
        Assertions.assertEquals(productList.get(id).getId(), EXPECTED_FIND_ALL_PRODUCTS.get(id).getId());
        Assertions.assertEquals(productList.get(id).getItem_name(), EXPECTED_FIND_ALL_PRODUCTS.get(id).getItem_name());
        Assertions.assertEquals(productList.get(id).getPrice(), EXPECTED_FIND_ALL_PRODUCTS.get(id).getPrice());
    }

    @Test
    public void saveTest() throws SQLException {
        Product product = new Product(5L, "wwww", 123L);
        productsRepository.save(product);
        Optional<Product> optionalProduct = productsRepository.findById(5L);
        if (optionalProduct.isPresent()) {
            Assertions.assertEquals(optionalProduct.get().getItem_name(), product.getItem_name());
            Assertions.assertEquals(optionalProduct.get().getPrice(), product.getPrice());
        } else
            Assertions.fail();
    }

    @Test
    public void updateTest() throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Optional<Product> optionalProduct = productsRepository.findById(EXPECTED_UPDATED_PRODUCT.getId());
        if (optionalProduct.isPresent()) {
            Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT.getItem_name(), optionalProduct.get().getItem_name());
            Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT.getPrice(), optionalProduct.get().getPrice());
        } else {
            Assertions.fail();
        }
    }

    @Test
    public void deleteTest() throws SQLException {
        productsRepository.delete(4L);
        Optional<Product> product = productsRepository.findById(1L);
//        List<Product> productList = productsRepository.findAll();
//        System.out.println(productList);
    }
}
