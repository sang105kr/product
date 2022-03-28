package com.kh.product.domain.web;

import com.kh.product.domain.dao.Product;
import com.kh.product.domain.svc.ProductSVC;
import com.kh.product.domain.web.api.ApiResult;
import com.kh.product.domain.web.api.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class APIProductController {

  private final ProductSVC productSVC;

  //등록
  @PostMapping
  public ApiResult<Product> save(@RequestBody ProductRequest productRequest){

    Product product = new Product();
    BeanUtils.copyProperties(productRequest, product);
    Long productId = productSVC.save(product);

    Product findedProduct = null;
    if(productId > 0){
      //등록된 상품정보 가져오기
      findedProduct = productSVC.findByProductId(productId);
      //API에 매핑하기
      BeanUtils.copyProperties(findedProduct,product);
    }

    ApiResult<Product> result = new ApiResult<>("00", "success", product);

    return result;
  }
  //목록
  @GetMapping
  public ApiResult<List<Product>> findAll(){

    List<Product> products = productSVC.findAll();

    ApiResult<List<Product>> result = null;
    if(products.size() != 0) {
      result = new ApiResult<>("00", "success", products);
    }else{
      result = new ApiResult<>("99", "데이터 없음", products);
    }
    return result;
  }

  //조회
  @GetMapping("/{id}")
  public ApiResult<Product> findByProductId(@PathVariable("id") Long productId) {

    Product findedProduct = productSVC.findByProductId(productId);
    ApiResult<Product> result = null;
    if(findedProduct != null) {
      result = new ApiResult<>("00", "success", findedProduct);
    }else{
      result = new ApiResult<>("99", "조회하고자하는 데이터가 없습니다.", findedProduct);
    }
    return result;
  }

  //수정
  @PatchMapping("/{id}")
  public ApiResult<Product> update(
      @PathVariable("id") Long productId,
      @RequestBody ProductRequest ProductRequest){

    Product product = new Product();
    BeanUtils.copyProperties(ProductRequest,product);
    int affectedRow = productSVC.update(productId, product);

    Product updatedProduct = null;
    ApiResult<Product> result = null;
    if(affectedRow == 1){
      //수정후 상품가져오기
      updatedProduct = productSVC.findByProductId(productId);
      //API매핑하기
      BeanUtils.copyProperties(updatedProduct, product);

      result = new ApiResult<>("00","success", product);
    }else{
      result = new ApiResult<>("99","fail:"+productId, product);
    }
    return result;
  }

  //삭제
  @DeleteMapping("{id}")
  public ApiResult<Boolean> delete(@PathVariable("id") Long productId) {
    int affedtedRow = productSVC.deleteByProductId(productId);

    ApiResult<Boolean> result = null;
    if(affedtedRow == 1){
      result = new ApiResult<>("00", "success", true);
    }else{
      result = new ApiResult<>("99", "fail", false);
    }
    return result;
  }

}
