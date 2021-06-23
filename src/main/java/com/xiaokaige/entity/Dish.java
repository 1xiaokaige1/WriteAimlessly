package com.xiaokaige.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zengkai
 * @date 2021/6/18 14:03
 */
@Data
@AllArgsConstructor
public class Dish {
    private boolean isVegetarian;

    private String name;

    private Integer calories;

    private String type;
}
