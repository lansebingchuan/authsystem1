package com.zht.controller;

import com.zht.bean.Product;
import com.zht.bean.User;
import com.zht.service.ProductService;
import com.zht.util.controller.BaseController;
import com.zht.util.controller.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    ProductService productService;

    /**
     * 获取已加入购物车的货物信息
     * @return
     */
    public Object listPageProductAlready(HttpSession session){
        User user = SessionUtil.getUser(session);
        List<Product> productList = productService.listPageProductAlready(user);
        return null;
    }

    @RequestMapping("/addProductPage")
    public String addProductPage(){
        return "/main/product/addProduct";
    }

    /**
     * 我的商品
     * @return
     */
    @RequestMapping("/myProduct")
    public String myProduct(){
        return "/main/user/myProduct";
    }

    @ResponseBody
    @RequestMapping("/addProduct")
    public Object addProduct(HttpSession session, Product product){
        start();
        User user = SessionUtil.getUser(session);
        product.setUserId(user.getId());
        boolean flage = productService.saveOrUpdate(product);
        if (flage){
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    /**
     * 我的商品
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/myListProduct")
    public Object myListProduct(HttpSession session){
        start();
        User user = SessionUtil.getUser(session);
        List<Product> productList = productService.myProduct(user);
        put("productList", productList);
        flage(true);
        return end();
    }

    /**
     * 我的商品
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/listAllProduct")
    public Object listAllProduct(HttpSession session){
        start();
        User user = SessionUtil.getUser(session);
        List<Product> productList = productService.listAllProduct();
        put("productList", productList);
        flage(true);
        return end();
    }


}
