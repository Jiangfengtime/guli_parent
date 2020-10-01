package com.hjf.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Jiang锋时刻
 * @create 2020-10-01 8:36
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入时, 自动填充创建时间和修改时间
    @Override
    public void insertFill(MetaObject metaObject) {
        // 填的是实体类中的属性名, 而不是数据表中的字段名
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

    // 修改时, 自动填充修改时间
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
}
