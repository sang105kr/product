package com.kh.product.domain.web.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 응답구조
 */
//{ rcd:00, --응답코드
//    rtmsg:success, --응답메시지
//    data:{ ... } --데이터
//    }
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> {
  private String rtcd;      //응답코드
  private String rtmsg;     //응답메세지
  private T data;           //응답데이터
}
