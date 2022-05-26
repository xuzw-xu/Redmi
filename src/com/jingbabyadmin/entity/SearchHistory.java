package com.jingbabyadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistory {

  private String id;
  private String searchWords;
  private Integer num;
  private java.sql.Timestamp searchTime;

}
