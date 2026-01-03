package com.vaibhav.project.service.base;

import org.springframework.beans.BeanUtils;

public class GenericService<K,E,D> {

    public D toDTO(E e,D d,String ... ignoreProperties){
        BeanUtils.copyProperties(e,d,ignoreProperties);
        return d;
    }
}
