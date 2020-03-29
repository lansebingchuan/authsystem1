package com.zht.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zht.base.AbstractCrudService;
import com.zht.bean.Product;
import com.zht.bean.ShoppingCart;
import com.zht.bean.User;
import com.zht.constant.ConstantInterface;
import com.zht.dao.ProductMapper;
import com.zht.dao.ShoppingCartMapper;
import com.zht.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl extends AbstractCrudService<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {


    @Autowired
    ShoppingCartMapper shoppingCartMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    public Page listPageShoppingProduct(User user, Integer status, Integer current) {
        Page<Product> page = new Page<>();
        page.setCurrent(current);
        page.setSize(ConstantInterface.pageSize);
        List<Product> productList = shoppingCartMapper.listPageShoppingProduct(page, user.getId(), status);
        page.setRecords(productList);
        return page;
    }

    @Override
    public int updateShoppingCartStatus(ShoppingCart shoppingCart, Integer status) {
        List<ShoppingCart> shoppingCartList = shoppingCart.getShoppingCartList();
        int count = 0;
        if (shoppingCartList != null && shoppingCartList.size() > 0) {
            for (ShoppingCart shoppingCart1 : shoppingCartList) {
                Integer productId = shoppingCart1.getProductId();
                Product product = productMapper.selectById(productId);
                Integer buyCount = product.getBuyCount();
                if (buyCount == null){
                    buyCount = 1;
                }else {
                    buyCount++;
                }
                product.setBuyCount(buyCount);
                productMapper.updateById(product);

                ShoppingCart sc = new ShoppingCart();
                sc.setId(shoppingCart1.getId());
                sc.setStatus(status);
                sc.setCount(shoppingCart1.getCount());
                shoppingCartMapper.updateById(sc);
                count++;
            }
        }
        return count;
    }

}
