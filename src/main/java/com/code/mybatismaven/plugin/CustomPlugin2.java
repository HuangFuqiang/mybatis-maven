package com.code.mybatismaven.plugin;

import com.code.mybatismaven.annotation.CreateTime;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class CustomPlugin2 implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // 获取 SQL 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 获取参数
        Object parameter = invocation.getArgs()[1];

        if (parameter != null) {
            // 获取成员变量
            Field[] declaredFields = parameter.getClass().getDeclaredFields();

            for (Field field : declaredFields) {
                if (field.getAnnotation(CreateTime.class) != null) {
                    if (SqlCommandType.INSERT.equals(sqlCommandType)) { // insert 语句插入 createTime
                        field.setAccessible(true);
                        if (field.get(parameter) == null) {
                            field.set(parameter, new Date());
                        }
                    }
                }
            }
        }
        return invocation.proceed();
    }

    public Object plugin(Object o) {
        return Plugin.wrap(o,this);
    }

    public void setProperties(Properties properties) {

    }
}
