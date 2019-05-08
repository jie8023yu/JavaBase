package com.wj100.server.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {

    private List<Entity> entities;
    private List<Mapping> mappings;

    private Map<String,String> entityMap = new HashMap<>();
    private Map<String,String> mappingMap = new HashMap<>();

    public WebContext(List<Entity> entities, List<Mapping> mappings) {
        this.entities = entities;
        this.mappings = mappings;
        for (Entity entity : entities) {
            entityMap.put(entity.getName(),entity.getClz());
        }
        for (Mapping mapping : mappings) {
            for (String pattern : mapping.getPatterns()) {
                mappingMap.put(pattern,mapping.getName());
            }
        }
    }

    /**
     * 通过URL的路径找到了对应的Class
     * @param pattern
     * @return
     */
    public String getClz(String pattern) {
       String name = mappingMap.get(pattern);
       return entityMap.get(name);
    }
}
