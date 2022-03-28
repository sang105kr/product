package com.kh.product.domain.web.form;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class UpdateForm {
  private Long productId;
  @NotBlank(message = "상품명 입력은 필수입니다.")
  private String pname;     //  PNAME	VARCHAR2(30 BYTE)
  @NotNull(message = "상품수량 입력은 필수입니다")
  @PositiveOrZero(message = "상품수량 입력은 0이상 이여야 합니다..")
  private Long quantity;    //  QUANTITY	NUMBER(10,0)
  @NotNull(message = "상품가격 입력은 필수입니다")
  @PositiveOrZero(message = "상품가격 입력은 0 이상 이어야 입니다.")
  private Long price;       //  PRICE	NUMBER(10,0)
}
