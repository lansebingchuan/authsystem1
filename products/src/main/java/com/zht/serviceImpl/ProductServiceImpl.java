package com.zht.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zht.base.AbstractCrudService;
import com.zht.bean.Product;
import com.zht.bean.User;
import com.zht.dao.ProductMapper;
import com.zht.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends AbstractCrudService<ProductMapper,Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public List<Product> listPageProductAlready(User user) {

        return null;
    }

    @Override
    public List<Product> myProduct(User user) {
        QueryWrapper<Product> wrapper = new QueryWrapper();
        wrapper.eq("userId" , user.getId());
        List<Product> productList = productMapper.selectList(wrapper);
        return productList;
    }

    @Override
    public List<Product> listAllProduct() {
        return this.list();
    }
}
