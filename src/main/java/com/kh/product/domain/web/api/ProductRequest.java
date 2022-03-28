package com.kh.product.domain.web.api;

import lombok.Data;

/**
 * Rest API 커맨드객체
 */
@Data
public class ProductRequest {
  private String pname;     //  PNAME	VARCHAR2(30 BYTE)
  private Long quantity;    //  QUANTITY	NUMBER(10,0)
  private Long price;       //  PRICE	NUMBER(10,0)
}
