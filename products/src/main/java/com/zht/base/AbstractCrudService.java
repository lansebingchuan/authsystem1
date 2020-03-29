package com.zht.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 * service 基础类
 * 该类继承mybatis plus组件提供的通用service实现类，封装了基础的CRUD操作
 * </p>
 *
 * @author wanghongshuang
 * @since 2019-06-21
 */
public abstract class AbstractCrudService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
