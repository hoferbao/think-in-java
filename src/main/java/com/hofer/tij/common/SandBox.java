package com.hofer.tij.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hofer.bhf
 * created on 2018/05/19 5:28 PM
 */
public class SandBox {
    public static void main(String[] args) {
        List<Long> x = new ArrayList<>();
        x.add(1L);
        x.add(999L);
        System.out.println(x.stream().map(Object::toString).collect(Collectors.toList()));
    }
}
