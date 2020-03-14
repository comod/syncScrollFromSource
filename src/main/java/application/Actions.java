package application;

import static helper.JavaScriptEval.evalJavaScript;

public class Actions {

    public static void jumpToLine() {
        // ...
    }

    public static String evalScript(String script, String path) {

        String customResolver = "path = path.replace('somePathSegment', 'some_path_segment');";
        String eval = "path = '" + path + "'; " + customResolver;
        String result = evalJavaScript(eval);
        System.out.println("result: " + result);
        return result;

    }

}
