package com.zht.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zht.bean.Product;
import com.zht.bean.User;

import java.util.List;

/**
 * @author ZHT
 */
public interface ProductService extends IService<Product> {


    List<Product> listPageProductAlready(User user);

    /**
     * 获取我的商品
     * @param user
     * @return
     */
    List<Product> myProduct(User user);

    /**
     * 所有的商品列表
     * @return
     */
    List<Product> listAllProduct();
}
