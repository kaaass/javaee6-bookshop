package net.kaaass.bookshop;

import net.kaaass.bookshop.conf.ExceptionHandler;
import net.kaaass.bookshop.controller.TestController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class RestfulApiApplication extends Application {

    private final Set<Object> singletons = new HashSet<>();
    private final Set<Class<?>> classes = new HashSet<>();

    @SuppressWarnings("MapOrSetKeyShouldOverrideHashCodeEquals")
    public RestfulApiApplication() {
        // 注册服务
        singletons.add(new TestController());
        // 注册配置类
        classes.add(ExceptionHandler.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
