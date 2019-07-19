package com.code.mybatismaven.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class CustomPlugin1 implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler=(StatementHandler)invocation.getTarget();
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);

        //获取sql
        String sql= String.valueOf(metaStatementHandler.getValue("delegate.boundSql.sql"));
        //添加limit条件
        sql="select * from (" + sql + ") as temp limit 1";
        //重新设置sql
        metaStatementHandler.setValue("delegate.boundSql.sql",sql);
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    public void setProperties(Properties properties) {
        properties.getProperty("dialect");
    }
}
