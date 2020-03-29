package com.zht.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zht.bean.Product;
import com.zht.bean.ShoppingCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ShoppingCart record);

    ShoppingCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShoppingCart record);

    int updateByPrimaryKey(ShoppingCart record);

    List<Product> listPageShoppingProduct(Page<Product> page, @Param("userId") Integer userId, @Param("status") Integer status);

}