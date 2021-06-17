package net.kaaass.bookshop.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型常用方法
 *
 * @author kaaass
 */
public class GenericUtils {

    /**
     * 获得父类泛型参数
     *
     * @param clazz 当前类
     */
    public static Class<?> getSuperClassGenericType(Class<?> clazz, int index) {
        Type superClazz = clazz.getGenericSuperclass();
        if (!(superClazz instanceof ParameterizedType)) {
            superClazz = clazz.getSuperclass().getGenericSuperclass();
            if (!(superClazz instanceof ParameterizedType)) {
                return null;
            }
        }

        final ParameterizedType genericSuper = (ParameterizedType) superClazz;
        return (Class<?>) genericSuper.getActualTypeArguments()[index];
    }
}
