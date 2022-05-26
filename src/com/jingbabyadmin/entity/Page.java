package com.jingbabyadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Page<T> {
    private int page;       //第几页
    private int size;       //每页的条数
    private List<T> list;   //分页数据
    private int total;      //总条数

    public double getLocalPage(){
        return Math.ceil(total*1.0/size);
    }
}
