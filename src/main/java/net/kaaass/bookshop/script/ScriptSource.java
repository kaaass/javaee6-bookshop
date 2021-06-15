package net.kaaass.bookshop.script;

import lombok.ToString;

/**
 * 脚本源
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @author kaaass
 */
@ToString
public class ScriptSource {

    private String script = "";

    private boolean modified;

    private String className;

    public ScriptSource(String script) {
        setScript(script);
    }

    public ScriptSource(String script, String className) {
        setScript(script);
        this.className = className;
    }

    public synchronized void setScript(String script) {
        this.modified = !script.equals(this.script);
        this.script = script;
    }

    public synchronized String getScriptAsString() {
        this.modified = false;
        return this.script;
    }

    public synchronized boolean isModified() {
        return this.modified;
    }

    public String suggestedClassName() {
        return this.className;
    }

}
