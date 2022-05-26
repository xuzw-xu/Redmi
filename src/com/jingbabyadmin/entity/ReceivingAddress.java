package com.jingbabyadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceivingAddress {

  private String id;
  private String receivingAddress;
  private String receivingPerson;
  private Long mobilePhone;
  private String userId;
  private Integer isDefault;

}
