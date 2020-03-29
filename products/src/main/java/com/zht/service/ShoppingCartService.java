package com.zht.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zht.bean.ShoppingCart;
import com.zht.bean.User;

public interface ShoppingCartService extends IService<ShoppingCart> {

    Page listPageShoppingProduct(User user, Integer atcart, Integer current);

    int updateShoppingCartStatus(ShoppingCart productIds, Integer atsend);

}
