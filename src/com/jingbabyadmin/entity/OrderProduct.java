package com.jingbabyadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {

  private String id;
  private String orderId;
  private String productId;
  private Integer productNum;

}
