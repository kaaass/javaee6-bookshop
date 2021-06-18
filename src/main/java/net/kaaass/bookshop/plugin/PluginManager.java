package net.kaaass.bookshop.plugin;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.eventhandle.EventManager;
import net.kaaass.bookshop.util.Constants;
import net.kaaass.bookshop.util.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
@Slf4j
public class PluginManager {

    private static final Set<String> idSet = new HashSet<>();

    @Inject
    private ObjectMapper objectMapper;

    public void loadPlugin(String id, String path) {
        idSet.add(id);
        log.info("加载插件 id = {}, path = {}", id, path);
        try {
            val listenersMap = loadListenerPaths(path);
            loadListeners(id, listenersMap, path);
        } catch (IOException e) {
            log.warn("插件监听器加载错误！", e);
        }
    }

    public void unloadPlugin(String id) {
        log.info("卸载插件 id = {}", id);
        EventManager.EVENT_BUS.unregister(id);
        idSet.remove(id);
    }

    /**
     * 返回事件类与处理文件的相对地址
     */
    private List<PluginConfig.ListenerDescriptor> loadListenerPaths(String path) throws IOException {
        String configStr = FileUtils.readAll(path + Constants.PLUGIN_PATH_CONFIG);
        val config = objectMapper.readValue(configStr, PluginConfig.class);
        log.info("加载路径 {} 配置 {}", path, config);
        return config.getListeners();
    }

    private void loadListeners(String id, List<PluginConfig.ListenerDescriptor> listeners, String pluginBase) throws IOException {
        for (val descriptor : listeners) {
            log.info("注册js事件：{}", descriptor);
            val codeStr = FileUtils.readAll(pluginBase + descriptor.getFile());
            EventManager.EVENT_BUS.register(id, descriptor.getEvent(), descriptor.getPriority(), codeStr);
        }
    }
}
