package com.jingbabyadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private String id;
  private String productName;
  private String productImage;
  private Double price;
  private String productType;
  private String productDesc;
  private java.sql.Timestamp createTime;
  private String productBrand;

}
