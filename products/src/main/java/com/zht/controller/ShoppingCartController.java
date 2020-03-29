package com.zht.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zht.bean.ShoppingCart;
import com.zht.bean.User;
import com.zht.constant.ConstantInterface;
import com.zht.service.ShoppingCartService;
import com.zht.util.controller.BaseController;
import com.zht.util.controller.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author ZHT
 */
@Controller
@RequestMapping("/main")
public class ShoppingCartController extends BaseController {

    @Autowired
    ShoppingCartService shoppingCartService;

    /**
     * 购物车货物页面
     * @return
     */
    @RequestMapping("/atCartProduct")
    public String atCartProduct(){
        return "main/shoppingCart/atCartProduct";
    }

    /**
     * 正在发货中页面
     * @return
     */
    @RequestMapping("/atSendProduct")
    public String atSendProduct(){
        return "main/shoppingCart/atSendProduct";
    }


    /**
     * 已完成订单
     * @return
     */
    @RequestMapping("/completeProduct")
    public String completeProduct(){
        return "main/shoppingCart/completeProduct";
    }


    /**
     * 购物车列表
     * @param session
     * @param status
     * @param current
     * @return
     */
    @ResponseBody
    @RequestMapping("/listPageAlreadyShoppingProduct")
    public Object listPageAlreadyShoppingProduct(HttpSession session, Integer status, Integer current){
        User user = SessionUtil.getUser(session);
        Page page = shoppingCartService.listPageShoppingProduct(user, status, current);
        return page;
    }

    /**
     * 下单购买
     * @param session
     * @param productIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/toBuyProduct")
    public Object toBuyProduct(HttpSession session, ShoppingCart shoppingCart){
        start();
        User user = SessionUtil.getUser(session);
        Integer i = shoppingCartService.updateShoppingCartStatus(shoppingCart, ConstantInterface.ATSEND);
        if (i > 0){
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    @ResponseBody
    @RequestMapping("/confirmShopping")
    public Object confirmShopping(HttpSession session, ShoppingCart shoppingCart){
        start();
        User user = SessionUtil.getUser(session);
        boolean b = shoppingCartService.updateById(shoppingCart);
        if (b){
            flage(true);
        }else {
            flage(false);
        }
        return end();
    }

    @ResponseBody
    @RequestMapping("/addProduct")
    public Object addProduct(HttpSession session, ShoppingCart shoppingCart){
        start();
        User user = SessionUtil.getUser(session);
        if (user == null){
            flage(false);
            put("user", "n");
            return end();
        }
        shoppingCart.setUserId(user.getId());
        shoppingCart.setStatus(1);
        shoppingCart.setCount(1);
        shoppingCartService.saveOrUpdate(shoppingCart);
        flage(true);
        return end();
    }

}
