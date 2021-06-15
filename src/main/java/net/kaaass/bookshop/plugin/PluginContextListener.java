package net.kaaass.bookshop.plugin;

import lombok.extern.slf4j.Slf4j;
import net.kaaass.bookshop.service.PluginService;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
public class PluginContextListener implements ServletContextListener {

    @Inject
    private PluginService pluginService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("开始启动模组加载");
        pluginService.mountAll();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
