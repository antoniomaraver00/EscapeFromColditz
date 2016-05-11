package com.jakemarsden.colditz.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * @author jakemarsden
 */
public final class Json {
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper(new YAMLFactory());
    }


    public static ObjectMapper getMapper() {
        return mapper;
    }


    private Json() {
        throw new UnsupportedOperationException();
    }
}
