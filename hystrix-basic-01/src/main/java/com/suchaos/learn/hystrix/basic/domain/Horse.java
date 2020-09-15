package com.suchaos.learn.hystrix.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 马
 *
 * @author suchao
 * @date 2020/9/14
 */
@Data
@AllArgsConstructor
public class Horse {

    private String id;
    private String name;
}
