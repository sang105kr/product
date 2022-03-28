package com.kh.product.domain.svc;

import com.kh.product.domain.dao.Product;
import com.kh.product.domain.dao.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC{

  private final ProductDAO productDAO;

  //등록
  @Override
  public Long save(Product product) {
    return productDAO.save(product);
  }

  //목록
  @Override
  public List<Product> findAll() {
    return productDAO.findAll();
  }

  //조회
  @Override
  public Product findByProductId(Long productId) {
    return productDAO.findByProductId(productId);
  }

  //수정
  @Override
  public int update(Long productId, Product product) {
    return productDAO.update(productId, product);
  }

  //삭제
  @Override
  public int deleteByProductId(Long productId) {
    return productDAO.deleteByProductId(productId);
  }
}
