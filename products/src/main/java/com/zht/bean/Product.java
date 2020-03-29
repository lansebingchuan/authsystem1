package com.zht.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author ZHT
 */
@TableName("product")
public class Product {

    /**
     * id跟随数据库自动生成
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("placeOrigin")
    private String placeOrigin;

    @TableField("depict")
    private String depict;

    @TableField("price")
    private Double price;

    @TableField("buyCount")
    private Integer buyCount;

    @TableField("icon")
    private String icon;

    @TableField("userId")
    private Integer userId;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private Integer shoppingCartId;

    /**
     * 商品数量
     */
    @TableField(exist = false)
    private Integer count;

    /**
     * 价格合计
     */
    @TableField(exist = false)
    private double totalMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin == null ? null : placeOrigin.trim();
    }

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBuyCount() {
        return buyCount = buyCount == null? 0 : buyCount ;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount == null? 0 : buyCount ;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Integer shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getTotalMoney() {
        if (this.count != null && this.price != null){
            return this.count*this.price;
        }
        return 0;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Product() {
    }
}