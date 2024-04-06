package com.app.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author duyenthai
 */
public class SingletonHolder {
    private static final SingletonHolder INSTANCE = new SingletonHolder();

    private final Map<Class<?>, Object> beans = new HashMap<>();

    private SingletonHolder() {
    }

    public <T> void registerBean(Class<T> tClass, T object) {
        if (beans.containsKey(tClass)) {
            throw new RuntimeException("Bean already registered");
        }
        synchronized (beans) {
            beans.put(tClass, object);
        }
    }

    public <T> T getBean(Class<T> tClass) {
        Object o = beans.get(tClass);
        if (o.getClass().equals(tClass)) {
            return (T) o;
        }
        return null;
    }

    public void setConfig(AppConfig appConfig) {
        registerBean(AppConfig.class, appConfig);
    }

    public AppConfig getConfig() {
        return getBean(AppConfig.class);
    }

    public static SingletonHolder getInstance() {
        return INSTANCE;
    }
}
