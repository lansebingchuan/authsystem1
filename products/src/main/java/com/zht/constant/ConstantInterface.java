package com.zht.constant;

/**
 * @author zht
 * @create 2019-11-21 22:09
 */
public interface ConstantInterface {

   /**
    * 上传文件总地址
    */
   String FILEPATH = "/upload";

   /**
    * 头像文件地址地址
    */
   String HEAD = "/head";


   /**
    * 商品保存位置
    */
   String PRODUCT = "/product";

   /**
    * 每页数量
    */
   Integer pageSize = 5;

   /**
    * 在购物车
    */
   Integer ATCART = 1;

   /**
    * 派送中
    */
   Integer ATSEND = 2;

   /**
    * 已购物
    */
   Integer ALREEADYSHOPPING = 3;

   /**
    * 放弃
    */
   Integer GIVEUP = 4;

}
