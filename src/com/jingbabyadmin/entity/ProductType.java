package com.jingbabyadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductType {

  private String id;
  private String productTypeName;
  private String productTypeDesc;
  private String productTypeIcon;

}
