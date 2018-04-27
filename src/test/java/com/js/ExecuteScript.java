package com.js;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

public class ExecuteScript {
    /**
     * 从给定的js文件中获取指定接口中的方法的实例
     * @param fileLoacation js文件路径
     * @param clazz 接口的class
     * @return 返回一个指定接口方法的实例
     */
    public <T> T getMethod (String fileLoacation,Class<T> clazz) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            String path = ExecuteScript.class.getResource("").getPath();
            System.out.println(path);
            // FileReader的参数为所要执行的js文件的路径
            engine.eval(new FileReader(fileLoacation));
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                T executeMethod = invocable.getInterface(clazz);
                return executeMethod;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}