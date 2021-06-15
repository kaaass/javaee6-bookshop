package net.kaaass.bookshop.script;

import javax.script.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 脚本执行方法
 *
 * @author Juergen Hoeller1
 * @author kaaass
 */
public abstract class StandardScriptUtils {

    public static ScriptEngine retrieveEngineByName(ScriptEngineManager scriptEngineManager, String engineName) {
        ScriptEngine engine = scriptEngineManager.getEngineByName(engineName);
        if (engine == null) {
            Set<String> engineNames = new LinkedHashSet<>();
            for (ScriptEngineFactory engineFactory : scriptEngineManager.getEngineFactories()) {
                List<String> factoryNames = engineFactory.getNames();
                if (factoryNames.contains(engineName)) {
                    // Special case: getEngineByName returned null but engine is present...
                    // Let's assume it failed to initialize (which ScriptEngineManager silently swallows).
                    // If it happens to initialize fine now, alright, but we really expect an exception.
                    try {
                        engine = engineFactory.getScriptEngine();
                        engine.setBindings(scriptEngineManager.getBindings(), ScriptContext.GLOBAL_SCOPE);
                    } catch (Throwable ex) {
                        throw new IllegalStateException("Script engine with name '" + engineName +
                                "' failed to initialize", ex);
                    }
                }
                engineNames.addAll(factoryNames);
            }
            throw new IllegalArgumentException("Script engine with name '" + engineName +
                    "' not found; registered engine names: " + engineNames);
        }
        return engine;
    }

    static Bindings getBindings(Map<String, Object> bindings) {
        return (bindings instanceof Bindings ? (Bindings) bindings : new SimpleBindings(bindings));
    }
}
