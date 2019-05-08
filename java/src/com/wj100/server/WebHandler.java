package com.wj100.server;

import com.wj100.server.servlet.Entity;
import com.wj100.server.servlet.Mapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class WebHandler extends DefaultHandler {

    private List<Entity> entities;
    private List<Mapping> mappings;
    private Entity entity;
    private Mapping mapping;
    private String tag;
    private boolean isMapping = false;

    @Override
    public void startDocument() throws SAXException {
        entities = new ArrayList<>();
        mappings = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (null != qName) {
            tag = qName;
            if ("servlet".equals(tag)) {
                entity = new Entity();
                isMapping = false;
            } else if ("servlet-mapping".equals(tag)) {{
                mapping = new Mapping();
                isMapping = true;
            }}
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String contents = new String(ch,start,length).trim();
        if (null != tag) {
            if (isMapping) {
                if ("servlet-name".equals(tag)) {
                    mapping.setName(contents);
                } else if ("url-pattern".equals(tag)) {
                    mapping.addPatterns(contents);
                }
            } else {
                if ("servlet-name".equals(tag)) {
                    entity.setName(contents);
                } else if ("servlet-class".equals(tag)) {
                    entity.setClz(contents);
                }

            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (null != qName) {
            if ("servlet".equals(qName)) {
                entities.add(entity);
            } else if ("servlet-mapping".equals(qName)) {
                mappings.add(mapping);
            }
        }
        tag = null;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<Mapping> mappings) {
        this.mappings = mappings;
    }
}
