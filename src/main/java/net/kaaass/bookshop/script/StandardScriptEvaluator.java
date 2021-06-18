package net.kaaass.bookshop.script;

import net.kaaass.bookshop.exception.InternalErrorExeption;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

/**
 * 脚本执行器
 *
 * @author Juergen Hoeller
 * @author Costin Leau
 * @author kaaass
 */
public class StandardScriptEvaluator {

    private String engineName;

    private volatile Bindings globalBindings;

    private volatile ScriptEngineManager scriptEngineManager;


    public StandardScriptEvaluator() {
    }


    public StandardScriptEvaluator(ClassLoader classLoader) {
        this.scriptEngineManager = new ScriptEngineManager(classLoader);
    }

    public StandardScriptEvaluator(ScriptEngineManager scriptEngineManager) {
        this.scriptEngineManager = scriptEngineManager;
    }

    public void setLanguage(String language) {
        this.engineName = language;
    }

    public void setEngineName(String engineName) {
        this.engineName = engineName;
    }

    public void setGlobalBindings(Map<String, Object> globalBindings) {
        Bindings bindings = StandardScriptUtils.getBindings(globalBindings);
        this.globalBindings = bindings;
        ScriptEngineManager scriptEngineManager = this.scriptEngineManager;
        if (scriptEngineManager != null) {
            scriptEngineManager.setBindings(bindings);
        }
    }

    public void setBeanClassLoader(ClassLoader classLoader) {
        ScriptEngineManager scriptEngineManager = this.scriptEngineManager;
        if (scriptEngineManager == null) {
            scriptEngineManager = new ScriptEngineManager(classLoader);
            this.scriptEngineManager = scriptEngineManager;
            Bindings bindings = this.globalBindings;
            if (bindings != null) {
                scriptEngineManager.setBindings(bindings);
            }
        }
    }

    public Object evaluate(ScriptSource script) throws InternalErrorExeption {
        return evaluate(script, null);
    }

    public Object evaluate(ScriptSource script, Map<String, Object> argumentBindings) throws InternalErrorExeption {
        ScriptEngine engine = getScriptEngine(script);
        try {
            if (argumentBindings == null || argumentBindings.isEmpty()) {
                return engine.eval(script.getScriptAsString());
            } else {
                Bindings bindings = StandardScriptUtils.getBindings(argumentBindings);
                return engine.eval(script.getScriptAsString(), bindings);
            }
        } catch (ScriptException ex) {
            throw new InternalErrorExeption("无法执行脚本", ex);
        }
    }

    protected ScriptEngine getScriptEngine(ScriptSource script) {
        ScriptEngineManager scriptEngineManager = this.scriptEngineManager;
        if (scriptEngineManager == null) {
            scriptEngineManager = new ScriptEngineManager();
            this.scriptEngineManager = scriptEngineManager;
        }

        if (this.engineName != null && !this.engineName.isEmpty()) {
            return StandardScriptUtils.retrieveEngineByName(scriptEngineManager, this.engineName);
        } else {
            throw new IllegalStateException(
                    "No script language defined, and no resource associated with script: " + script);
        }
    }

}
