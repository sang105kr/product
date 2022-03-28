package com.kh.product.domain.web.form;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SaveForm {
  @NotBlank(message = "상품명 입력은 필수입니다.")
  private String pname;     //  PNAME	VARCHAR2(30 BYTE)
  @NotNull(message = "상품수량 입력은 필수입니다")
  @PositiveOrZero(message = "상품수량 입력은 0이상 이여야 합니다..")
  private Long quantity;    //  QUANTITY	NUMBER(10,0)
  @NotNull(message = "상품가격 입력은 필수입니다")
  @PositiveOrZero(message = "상품가격 입력은 0 이상 이어야 입니다.")
  private Long price;       //  PRICE	NUMBER(10,0)
}
