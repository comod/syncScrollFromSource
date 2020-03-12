package helper;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptEval {

    public static String eval() {

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        try {
            Object result = engine.eval("function hello(name) {return ('Hello, ' + name);} hello('test');");
            return (String) result;

        } catch (ScriptException e) {
            System.out.println("ERR");
        }

        return "";

    }

}
