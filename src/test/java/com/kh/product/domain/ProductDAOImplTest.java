package com.kh.product.domain;

import com.kh.product.domain.dao.Product;
import com.kh.product.domain.dao.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;


@Slf4j
@SpringBootTest
class ProductDAOImplTest {

  @Autowired
  private ProductDAO productDAO;

  @Test
  @DisplayName("등록")
  void save() {
    Product product = new Product();
    product.setPname("노트북");
    product.setQuantity(3l);
    product.setPrice(2000000l);

    Long productId = productDAO.save(product);
    Product findedProduct = productDAO.findByProductId(productId);

    Assertions.assertThat(findedProduct.getPname()).isEqualTo("노트북");
    Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(3l);
    Assertions.assertThat(findedProduct.getPrice()).isEqualTo(2000000l);
  }

  @Test
  @DisplayName("목록")
  void findAll() {

    List<Product> products = productDAO.findAll();
    log.info("products={}", products);
    Assertions.assertThat(products.size()).isEqualTo(4);

  }

  @Test
  @DisplayName("조회")
  void findByProductId() {
    Long productId = 2l;
    Product findedProduct = productDAO.findByProductId(productId);
    Assertions.assertThat(findedProduct.getProductId()).isEqualTo(productId);
  }

  @Test
  @DisplayName("변경")
  void update() {
    Long productId = 3l;
    Product findedProduct = productDAO.findByProductId(productId);

    findedProduct.setPname("삼성노트북");
    findedProduct.setQuantity(100L);
    findedProduct.setPrice(2500000L);

    productDAO.update(productId, findedProduct);

    Product updatedProduct = productDAO.findByProductId(productId);
    Assertions.assertThat(updatedProduct.getPname()).isEqualTo("삼성노트북");
    Assertions.assertThat(updatedProduct.getQuantity()).isEqualTo(100L);
    Assertions.assertThat(updatedProduct.getPrice()).isEqualTo(2500000L);

  }

  @Test
  @DisplayName("삭제")
 void deleteByProductId() {
    Long productId = 3L;
    productDAO.deleteByProductId(productId);
    //삭제후 EmptyResultDataAccessException.class 예외체크
    //queryForObject 메소드는 대응되는 레코드를 못찾을경우 EmptyResultDataAccessException예외 발생
    Assertions.catchThrowableOfType(
        () -> productDAO.findByProductId(productId),  //실행코드
        EmptyResultDataAccessException.class);        //예외타입
  }
}